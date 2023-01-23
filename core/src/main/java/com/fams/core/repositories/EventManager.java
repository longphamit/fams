package com.fams.core.repositories;

import com.fams.core.dtos.request.AddEventActionRequest;
import com.fams.core.entities.EventEntity;
import com.fams.core.entities.GroupEntity;
import com.fams.core.models.FindEventParamModel;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventManager extends BaseManager<EventEntity> {
    public boolean addMemberToEvent(String eventId, List<String> members) {
        Query query = Query.query(Criteria.where("id").is(new ObjectId(eventId)));
        Update update = new Update();
        for (String member : members) {
            update.addToSet("members", member);
        }
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, EventEntity.class);
        return updateResult.getModifiedCount() > 0;
    }

    public List<EventEntity> find(FindEventParamModel findEventParamModel) {
        if (findEventParamModel.isAdmin()) {
            return mongoTemplate.findAll(EventEntity.class);
        } else {
            Query findEventQuery = buildQuery(findEventParamModel);
            return mongoTemplate.find(findEventQuery, EventEntity.class);
        }
    }

    public Query buildQuery(FindEventParamModel findEventParamModel) {
        if (StringUtils.hasLength(findEventParamModel.getGroupId())) {
            return Query.query(Criteria.where("groupId").is(findEventParamModel.getGroupId()));
        }
        Query findGroups = Query.query(Criteria.where("members").all(findEventParamModel.getAccountId()));
        List<GroupEntity> groups = mongoTemplate.find(findGroups, GroupEntity.class);
        List<String> groupIds = groups.stream().map(GroupEntity::getId).collect(Collectors.toList());
        Query findEventQuery = Query.query(Criteria.where("groupId").in(groupIds));
        if (findEventParamModel.isCreator()) {
            findEventQuery.addCriteria(Criteria.where("creator").is(findEventParamModel.getAccountId()));
        }
        if (findEventParamModel.isMember()) {
            findEventQuery.addCriteria(Criteria.where("members").all(findEventParamModel.getAccountId()));
        } else {
            findEventQuery.addCriteria(Criteria.where("members").not().all(findEventParamModel.getAccountId()));
        }
        return findEventQuery;
    }

    public long count(FindEventParamModel findEventParamModel) {
        if (findEventParamModel.isAdmin()) {
            return mongoTemplate.findAll(EventEntity.class).size();
        } else {
            Query countEventQuery = buildQuery(findEventParamModel);
            return mongoTemplate.count(countEventQuery, EventEntity.class);
        }
    }

    public List<EventEntity> findEventByCreator(String creator) {
        Query query = Query.query(Criteria.where("creator").in(creator));
        return mongoTemplate.find(query, EventEntity.class);
    }

    public boolean addEventAction(String eventId, AddEventActionRequest addEventActionRequest) {
        Query query = Query.query(Criteria.where("id").is(new ObjectId(eventId)));
        Update update = new Update();
        update.addToSet("actions", addEventActionRequest);
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, EventEntity.class);
        return updateResult.getModifiedCount() > 0;
    }
    public List<EventEntity> getEventActionByType(String eventId, String type){
        Query query = Query.query(Criteria.where("id").is(new ObjectId(eventId)));
        query.addCriteria(Criteria.where("type").is(type));
        return mongoTemplate.find(query, EventEntity.class);
    }
}

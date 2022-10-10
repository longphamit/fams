package com.fams.manager.repositories;

import com.fams.manager.entities.AccountEntity;
import com.fams.manager.entities.EventElementEntity;
import com.fams.manager.entities.EventEntity;
import com.fams.manager.entities.GroupEntity;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class GroupManager extends BaseManager<GroupEntity> {
    public List<GroupEntity> findByMemberId(String memberId) {
        Query query = Query.query(Criteria.where("members").all(memberId));
        return mongoTemplate.find(query, GroupEntity.class);
    }

    public long countJoinedGroupExistName(String memberId, String name) {
        Query query = Query.query(Criteria.where("members").all(memberId));
        query.addCriteria(Criteria.where("name").all(name));
        return mongoTemplate.count(query, GroupEntity.class);
    }

    public long countJoinedGroup(String memberId) {
        Query query = Query.query(Criteria.where("members").all(memberId));
        return mongoTemplate.count(query, GroupEntity.class);
    }

    public boolean addEventElement(String groupId, EventElementEntity eventElement) {
        eventElement.setId(new ObjectId().toHexString());
        Query query = Query.query(Criteria.where("id").is(new ObjectId(groupId)));
        Update update = new Update();
        update.addToSet("eventElements", eventElement);
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, GroupEntity.class);
        return updateResult.getModifiedCount() > 0;
    }

    public boolean addMember(String groupId, List<String> accounts) {
        Query query = Query.query(Criteria.where("id").is(new ObjectId(groupId)));
        Update update = new Update();
        for(String account:accounts){
            update.addToSet("members",account);
        }
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, GroupEntity.class);
        return updateResult.getModifiedCount() > 0;
    }

    public List<String> getMembers(String groupId) {
        Query query = Query.query(Criteria.where("id").is(new ObjectId(groupId)));
        query.fields().include("members");
        GroupEntity groupEntity = mongoTemplate.findOne(query, GroupEntity.class);
        if (groupEntity == null) {
            return null;
        }
        return groupEntity.getMembers();
    }

    public List<EventElementEntity> getEventElements(String groupId, List<String> eventElements) {
        Query query = Query.query(Criteria.where("id").is(new ObjectId(groupId)));
        query.addCriteria(Criteria.where("eventElements.id").in(eventElements));
        query.fields().include("eventElements");
        GroupEntity groupEntity = mongoTemplate.findOne(query, GroupEntity.class);
        if (groupEntity == null) {
            return null;
        }
        return groupEntity.getEventElements().stream().filter(e->eventElements.contains(e.getId())).collect(Collectors.toList());
    }
}

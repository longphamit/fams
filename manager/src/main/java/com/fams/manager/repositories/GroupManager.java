package com.fams.manager.repositories;

import com.fams.manager.entities.GroupEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupManager extends BaseManager<GroupEntity> {
    public List<GroupEntity> findByMemberId(String memberId){
        Query query= Query.query(Criteria.where("members.id").is(new ObjectId(memberId)));
        return mongoTemplate.find(query,GroupEntity.class);
    }
}

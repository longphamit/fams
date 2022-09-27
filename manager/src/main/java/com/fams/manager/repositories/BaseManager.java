package com.fams.manager.repositories;

import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import javassist.NotFoundException;
import lombok.SneakyThrows;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseManager<T> {
    @Autowired
    protected MongoTemplate mongoTemplate;
    private Class<T> cClass;

    public BaseManager() {
        this.cClass = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), BaseManager.class);
    }

    public void update(String id, String name, Object value, String updateBy) throws NotFoundException {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put(name, value);
        update(id, hashMap, updateBy);
    }

    @SneakyThrows
    public boolean update(String id, Map<String, Object> hashMap, String updateBy) {
        Update update = new Update();
        hashMap.forEach(update::set);
        UpdateResult updateResult = mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(id)), update, cClass);
        return updateResult.getModifiedCount() > 0;
    }

    @SneakyThrows
    public T save(T object) {
        Field createdAtField = object.getClass().getDeclaredField("createdAt");
        createdAtField.setAccessible(true);
        createdAtField.set(object, Calendar.getInstance().getTime());
        T result = mongoTemplate.save(object);
        return result;
    }

    protected List<T> find(Criteria criteria) {
        return mongoTemplate.find(Query.query(criteria), cClass);
    }

    protected T findOne(Criteria criteria) {
        return mongoTemplate.findOne(Query.query(criteria), cClass);
    }

    public T findById(String id) {
        return mongoTemplate.findById(id, cClass);
    }

    public List<T> findAll() {
        return mongoTemplate.findAll(cClass);
    }

    public boolean removeById(String id) {
        DeleteResult result = mongoTemplate.remove(Query.query(Criteria.where("id").is(new ObjectId(id))));
        if (result.getDeletedCount() == 0) {
            return true;
        }
        return false;
    }

    public boolean saveBatch(List<T> collects) {
        BulkOperations bulkOps = mongoTemplate.bulkOps(BulkOperations.BulkMode.ORDERED, cClass);
        for (T entity : collects) {
            bulkOps.insert(entity);
        }
        BulkWriteResult rs = bulkOps.execute();
        return rs.getModifiedCount() > 0;
    }
}

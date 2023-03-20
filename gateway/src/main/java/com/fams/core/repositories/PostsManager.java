package com.fams.core.repositories;

import com.fams.core.dtos.request.BaseSort;
import com.fams.core.dtos.request.GetPostsRequest;
import com.fams.core.entities.PostsEntity;
import com.fams.core.dtos.response.GetPostsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Long PC
 * 20/03/2023| 14:56 | 2023
 **/
@Repository
public class PostsManager {
    @Autowired
    MongoTemplate mongoTemplate;
    public PostsEntity create(PostsEntity posts) {
        return mongoTemplate.save(posts);
    }

    public Page<GetPostsResponse> get(GetPostsRequest getPostsRequest){
        // pagination
        Pageable pageable = PageRequest.of(getPostsRequest.getPageNumber(), getPostsRequest.getPageSize());
        Criteria criteria = new Criteria();
        List<Criteria> matchers= new ArrayList<>();
        if(StringUtils.hasLength(getPostsRequest.getTitle())){
            matchers.add(Criteria.where("title").is(getPostsRequest.getTitle()));
        }
        if(StringUtils.hasLength(getPostsRequest.getAuthor())){
            matchers.add(Criteria.where("author").is(getPostsRequest.getAuthor()));
        }
        criteria.andOperator(matchers);
        List<AggregationOperation> operations = new ArrayList<>();
        operations.add(Aggregation.match(criteria));
        //sort
        if (getPostsRequest.getSort() != null && !getPostsRequest.getSort().isEmpty()) {
            List<Sort.Order> sortOrders = new ArrayList<>();
            for (BaseSort sort : getPostsRequest.getSort()) {
                String fieldSort = sort.getKey().substring(sort.getKey().lastIndexOf('.') + 1);
                if (sort.getAsc()) {
                    sortOrders.add(new Sort.Order(Sort.Direction.ASC, fieldSort));
                } else {
                    sortOrders.add(new Sort.Order(Sort.Direction.DESC, fieldSort));
                }
            }
            operations.add(Aggregation.sort(Sort.by(sortOrders)));
        }
        Query query = new Query();
        if (getPostsRequest.isPagination()) {
            query.with(pageable);
        }
        query.addCriteria(criteria);
        Aggregation aggregation = Aggregation.newAggregation(operations);
        List<GetPostsResponse> map = mongoTemplate.aggregate(aggregation, PostsEntity.class, GetPostsResponse.class).getMappedResults();
        long countAll = mongoTemplate.count(Query.of(query).limit(-1).skip(-1), PostsEntity.class);
        return PageableExecutionUtils.getPage(map, pageable, () -> countAll);
    }
}

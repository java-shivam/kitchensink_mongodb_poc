package com.kitchensink.demo.data.mongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;

import org.springframework.stereotype.Component;

import com.kitchensink.demo.model.Member;



@Component
public class MongoDBIndexInitializer {

    @Autowired
    private MongoTemplate mongoTemplate;

    @jakarta.annotation.PostConstruct
    public void ensureIndexes() {
        mongoTemplate.indexOps(Member.class)
            .ensureIndex(new Index().on("email", Direction.ASC).unique());
    }
}

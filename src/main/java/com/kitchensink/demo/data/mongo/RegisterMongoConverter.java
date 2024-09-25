//package com.kitchensink.demo.data.mongo;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
//import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
//
//import java.util.Arrays;
//
//@Configuration
//public class RegisterMongoConverter extends AbstractMongoClientConfiguration {
//
//    @Override
//    public MongoCustomConversions customConversions() {
//        return new MongoCustomConversions(Arrays.asList(
//                new LongToObjectIdConverter(),
//                new ObjectIdToLongConverter()
//        ));
//    }
//
//    @Override
//    protected String getDatabaseName() {
//        return "kitchensink";
//    }
//}

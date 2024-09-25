/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kitchensink.demo.data;

//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
//import java.util.NoSuchElementException;

//import com.eyecare.migration.loader.service.impl.Value;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;

import com.kitchensink.demo.data.mongo.MemberMongoRepository;
//import com.kitchensink.demo.data.mongo.MemberMongoRepository;
import com.kitchensink.demo.model.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

//@ApplicationScoped
@Repository
public class MemberRepository {

    //@Inject
	@PersistenceContext
    private EntityManager em;
	
	@Value("${save.entity.with.jpa:true}")
	private boolean emJPA;
	
    @Autowired
    private MemberMongoRepository memberMongoRepository;
    
    @Autowired
    private MongoTemplate mongoTemplate;

    public Member findById(String id) {
    	if(emJPA) {
    		return em.find(Member.class, id);
    	}
    	else {
    		return mongoTemplate.findById(id, Member.class);
//    		return memberMongoRepository.findById(id).get();
//    		return memberMongoRepository.findById(id)
//    		        .orElseThrow(() -> new NoSuchElementException("Member not found with id: " + id));
    	}
    		
        
    }

    public Member findByEmail(String email) {
    	if(emJPA) {
	        CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
	        Root<Member> member = criteria.from(Member.class);
	        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
	        // feature in JPA 2.0
	        // criteria.select(member).where(cb.equal(member.get(Member_.email), email));
	        criteria.select(member).where(cb.equal(member.get("email"), email));
	        System.out.println(criteria.getRestriction().getExpressions());
	        TypedQuery<Member> m =em.createQuery(criteria);
	        //System.out.println("m"+m);
	        return em.createQuery(criteria).getSingleResult();
    	}else {
	    	 Query query = new Query();
	         query.addCriteria(Criteria.where("email").is(email));
			 return mongoTemplate.findOne(query, Member.class);
	         //return memberMongoRepository.findByEmail(email);
    	}
        
       
        
    }

    public List<Member> findAllOrderedByName() {
    	if(emJPA) {
	        CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
	        Root<Member> member = criteria.from(Member.class);
	        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
	        // feature in JPA 2.0
	        // criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
	        criteria.select(member).orderBy(cb.asc(member.get("name")));
	        return em.createQuery(criteria).getResultList();
    	}else {
            Query query = new Query();
            query.with(Sort.by(Sort.Order.asc("name")));
            return mongoTemplate.find(query, Member.class);
	        // return memberMongoRepository.findAllByOrderByNameAsc();
    	}
    }
}

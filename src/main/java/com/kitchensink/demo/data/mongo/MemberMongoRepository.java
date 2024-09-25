package com.kitchensink.demo.data.mongo;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.kitchensink.demo.model.Member;

@Repository
public interface MemberMongoRepository extends MongoRepository<Member, String> {
    // Derived query method for finding by email
	// Find a single member by name
    Member findByName(String name);
    
 // Find a single member by email
    Member findByEmail(String email);
    
    
    // Find all members with a given name (useful if there are multiple members with the same name)
    List<Member> findAllByName(String name);
    
    // Find all members ordered by name in ascending order
    List<Member> findAllByOrderByNameAsc();
    
    // Find all members ordered by name in descending order
    List<Member> findAllByOrderByNameDesc();
    
    // Find members whose name contains a substring (for partial matching)
    List<Member> findByNameContaining(String partialName);
    
    // Find members whose name starts with a specific prefix
    List<Member> findByNameStartingWith(String prefix);
    
    // Find members whose name ends with a specific suffix
    List<Member> findByNameEndingWith(String suffix);
    
}

package com.shaadi.shaadi.Repository;

import com.shaadi.shaadi.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByMobile(String mobile);
}

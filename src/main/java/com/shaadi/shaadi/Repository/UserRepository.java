package com.shaadi.shaadi.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.shaadi.shaadi.Model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByMobile(String mobile);
}

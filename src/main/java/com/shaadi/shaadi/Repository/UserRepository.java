package com.shaadi.shaadi.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shaadi.shaadi.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByMobile(String mobile);
}
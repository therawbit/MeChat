package com.therawbit.MM.repository;

import com.therawbit.MM.entity.Status;
import com.therawbit.MM.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    List<User> findAllByStatus(Status status);
    Optional<User> findByUsername(String username);
}

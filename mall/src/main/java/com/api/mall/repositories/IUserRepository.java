package com.api.mall.repositories;

import com.api.mall.models.UserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends CrudRepository<UserModel, Long> {

    Optional<UserModel> findByUsername(String username);

   @Query("select u from UserModel u where u.username = ?1")
    Optional<UserModel> getName(String username);
}

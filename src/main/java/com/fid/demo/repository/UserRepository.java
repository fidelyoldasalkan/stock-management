package com.fid.demo.repository;


import com.fid.demo.entity.MoneyFlow;
import com.fid.demo.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends BaseRepository<User> {

    List<User> findAllByUsername(String username);

    @Query("select u.id from User u where u.username= :username ")
    Integer findByUsername(@Param("username") String username);

    List<User> findAllByCreatedBy(Integer cUser);

}

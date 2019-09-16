package com.codeup.springblog.repos;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername (String username);


}

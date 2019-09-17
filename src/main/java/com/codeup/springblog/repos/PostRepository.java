package com.codeup.springblog.repos;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
//    @Query("from blogs b where b.id like ? ")
//    Post getPostById(long id);
    Iterable<Post> findByUser (User user);
}

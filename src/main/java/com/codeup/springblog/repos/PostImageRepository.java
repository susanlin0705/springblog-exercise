package com.codeup.springblog.repos;

import com.codeup.springblog.models.PostImage;
import org.springframework.data.repository.CrudRepository;
public interface PostImageRepository extends CrudRepository <PostImage, Long> {

    //add file
}


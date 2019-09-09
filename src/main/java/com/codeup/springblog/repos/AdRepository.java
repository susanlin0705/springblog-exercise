package com.codeup.springblog.repos;

import com.codeup.springblog.models.Ad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepository  extends CrudRepository<Ad, Long> {

}

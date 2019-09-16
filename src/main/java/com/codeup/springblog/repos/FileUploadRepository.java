package com.codeup.springblog.repos;

import com.codeup.springblog.models.FileUpload;
import org.springframework.data.repository.CrudRepository;
public interface FileUploadRepository extends CrudRepository <FileUpload, Long> {
}


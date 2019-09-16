package com.codeup.springblog.controllers;

import com.codeup.springblog.models.FileUpload;
import com.codeup.springblog.repos.FileUploadRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@Controller
public class FileUploadController {
//    @Value("${file-upload-path}")
//    private String uploadPath;

    private final FileUploadRepository loadDao;

    public FileUploadController( FileUploadRepository uploadRepository){
        this.loadDao = uploadRepository;
    }

    @GetMapping("/fileupload")
    public String showUploadFileForm() {
        return "/fileupload";
    }

    @PostMapping("/fileupload")
    public String saveFile(
            @RequestParam(name = "file") MultipartFile uploadedFile,
            Model model
    ) {
        String filename = uploadedFile.getOriginalFilename();
        String filepath = Paths.get(loadDao.toString()).toString();
        File destinationFile = new File(filepath);
        try {
            uploadedFile.transferTo(destinationFile);
            model.addAttribute("message", "File successfully uploaded!");
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Oops! Something went wrong! " + e);
        }
        return "posts/create";
    }
}

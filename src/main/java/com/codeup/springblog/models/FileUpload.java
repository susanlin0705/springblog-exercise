package com.codeup.springblog.models;


import javax.persistence.*;


@Entity
@Table(name="upload")
public class FileUpload {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String filepath;

    @Column(nullable = false)
    private String filename;

    public FileUpload() {
    }

    public FileUpload(long id, String filepath, String filename) {
        this.id = id;
        this.filepath = filepath;
        this.filename = filename;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}

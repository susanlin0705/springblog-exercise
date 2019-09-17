package com.codeup.springblog.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="post_image")
public class PostImage {
    @Id
    @GeneratedValue
    private long id;

    @Column (nullable = false)
    private String path;

    @ManyToOne
    @JoinColumn(name= "post_id")
    @JsonIgnore
    private Post post;

    public PostImage(long id, String path, Post post) {
        this.id = id;
        this.path = path;
        this.post = post;
    }

    public PostImage(String path, Post post) {
        this.path = path;
        this.post = post;
    }

    //always need to add a constructor
    public PostImage() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}

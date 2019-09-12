package com.codeup.springblog.models;

import javax.persistence.*;

@Entity
@Table(name="post_image")
public class AdImage {
    @Id
    @GeneratedValue
    private long id;

    @Column (nullable = false)
    private String path;

    @ManyToOne
    @JoinColumn(name= "post_id")
    private Post post;

    public AdImage(long id,String path, Post post) {
        this.id = id;
        this.path = path;
        this.post = post;
    }

    //always need to add a constructor
    public AdImage() {
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
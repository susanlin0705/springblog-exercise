package com.codeup.springblog.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="blogs")
public class Post {
    @Id @GeneratedValue
    private long id;

    @Column(nullable = false , length= 100)
    @NotBlank(message = "Posts must have a title")
    @Size(min = 3, message = "A title must be at least 3 characters.")
    private String title;

    @Column(nullable = false)
    @NotBlank(message = "Posts must have a description")
    private String body;

    @ManyToOne
    @JsonManagedReference
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<AdImage> images;

    public Post(String title, String body, long id, User user, List<AdImage>images) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.user = user;
        this.images =images;

    }
    // Useful to create a new instance of the Post
    public Post() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<AdImage> getImages() {
        return images;
    }

    public void setImages(List<AdImage> images) {
        this.images = images;
    }

}

package com.codeup.springblog.models;

import javax.persistence.*;

@Entity(name = "ads")
public class Ad {

    @Id @GeneratedValue
    private long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private String description;


    public Ad(String title, String description,Long id ) {
        this.title = title;
        this.description = description;
        this.id = id;
    }

    public Ad() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

package com.example.stock.dao.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class Category {
    @Id
    private String id;
    private String name;
    private String categoryid;
    public Category() {}

    public Category(String id, String name,String categoryid) {
        this.id = id;
        this.name = name;
        this.categoryid = categoryid;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryid() {
        return categoryid;
    }
    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetfinaljpaws.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "documentations")
public class Documentation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INTEGER")
    private Long id;
    private String name;
    private String url;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Documentation(String name, String url, Category category) {
        this.name = name;
        this.url = url;
        this.category = category;
    }

    public Documentation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setNotNullData(Documentation newDocumentationData) {
        if (newDocumentationData.getName() != null) {
            this.setName(newDocumentationData.getName());
        }
        if (newDocumentationData.getUrl() != null) {
            this.setUrl(newDocumentationData.getUrl());
        }
        if (newDocumentationData.getCategory() != null) {
            this.setCategory(newDocumentationData.getCategory());
        }
    }

}

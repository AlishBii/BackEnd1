package com.shop.printshop.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Shapee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title, imagelink, color, material, size, description, clientContact;

    public Shapee() {
    }

    public Shapee(String title, String imagelink, String color, String material, String size, String description, String clientContact) {
        this.title = title;
        this.imagelink = imagelink;
        this.color = color;
        this.material = material;
        this.size = size;
        this.description = description;
        this.clientContact = clientContact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClientContact() {
        return clientContact;
    }

    public void setClientContact(String clientContact) {
        this.clientContact = clientContact;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, imagelink, color, material, size, description, clientContact);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }


}


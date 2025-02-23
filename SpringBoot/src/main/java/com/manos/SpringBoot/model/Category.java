package com.manos.SpringBoot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="categories")
@Data
@NoArgsConstructor//αυτοματη δημιουργια constructor χωρις arguments//default constructor
@AllArgsConstructor//allargumentsconstructor//αυτοματη δημιουργια constructor με arguments
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @NotBlank//εξασφαλιζει οτι δεν ειναι κενο,null value
    @Size(min= 5,message = "category name must contain at least 5 characters")
    private String categoryName;

}

/*@NoArgsConstructor
    public Category() {
    }

    @AllArgsConstructor
    public Category(Long categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
    */

    /*@Data
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;


    }
    */
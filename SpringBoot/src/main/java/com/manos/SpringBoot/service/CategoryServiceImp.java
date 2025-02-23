package com.manos.SpringBoot.service;

import com.manos.SpringBoot.exceptions.ApiException;
import com.manos.SpringBoot.exceptions.ResourceNotFoundException;
import com.manos.SpringBoot.model.Category;
import com.manos.SpringBoot.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService {

    //private List<Category> categories=new ArrayList<>();
    //1L=1Long
    //private Long nextId=1L;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
        List<Category>categories=categoryRepository.findAll();
        if(categories.isEmpty())
            throw new ApiException("No category created");
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        //ελεγχος αν υπαρχει κατηγορια με το ιδιο ονομα
        Category categorySaved = categoryRepository.findByCategoryName(category.getCategoryName());
        if (categorySaved != null) {
            throw new ApiException("Category with the name" + category.getCategoryName() + " already exists");
        }
        //του ζητας να σου δωσει νεο id
        //category.setCategoryId(nextId++);
        //categories.add(category);
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId){
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","categoryid",categoryId));
        categoryRepository.delete(category);

        return "Category with CategoryId :" + categoryId + " deleted successfully";
    }

    @Override
    public Category updateCategory(Category category,Long categoryId){

        Category saveCategory =categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","categoryid",categoryId));

        category.getCategoryId();
        saveCategory=categoryRepository.save(category);

        return saveCategory;
    }
}

/*
        //convert list to stream για φιλτραρισμα δεδομεμων πο ευκολα
        List<Category> categories = categoryRepository.findAll();
        Category category=categories.stream()

                //Κρατάει μόνο τις κατηγορίες που έχουν categoryId ίσο με το categoryId που λάβαμε ως παράμετρο
                .filter(c->c.getCategoryId().equals(categoryId))
                //Επιστρέφει την πρώτη κατηγορία που ταιριάζει στο φίλτρο.
                .findFirst()
                //πεταει exception αν δεν βρει κατηγορια
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource Not Found")) ;

        //categories.remove(category);

         */
//List<Category> categories = categoryRepository.findAll();
//Optional<Category> saveCategoryOptional=categoryRepository.findById(categoryId);

 /*
        Optional<Category>  optionalCategory=categories.stream()
                .filter(c->c.getCategoryId().equals(categoryId))
                .findFirst();

        if(optionalCategory.isPresent()){
           Category existingCategory=optionalCategory.get();
           existingCategory.setCategoryName(category.getCategoryName());
           Category saveCategory=categoryRepository.save(existingCategory);
           return saveCategory;
       }else{
           throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category Not Found") ;
        }

         */
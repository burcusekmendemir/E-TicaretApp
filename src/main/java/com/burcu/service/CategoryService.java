package com.burcu.service;

import com.burcu.dto.request.ProductAddRequestDto;
import com.burcu.entity.Category;
import com.burcu.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category save(List<ProductAddRequestDto.ProductAndCategory> categories) {
        Optional<Category> optionalCategory= categoryRepository.findByName(categories.get(0).getName());
        if (optionalCategory.isEmpty()){
            Category category = new Category();
            category.setName(categories.get(0).getName());
            categoryRepository.save(category);
            return category;
        }
        return optionalCategory.get();
    }


    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(Long id) {
        Optional<Category> optionalCategory= categoryRepository.findById(id);
        if (optionalCategory.isEmpty()){
            throw new NullPointerException("Category not found");
        }
        return optionalCategory;
    }



}

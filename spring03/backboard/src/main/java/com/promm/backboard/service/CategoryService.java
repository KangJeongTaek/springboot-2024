package com.promm.backboard.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.promm.backboard.common.NotFoundException;
import com.promm.backboard.entity.Category;
import com.promm.backboard.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category categorysave(String title){
        Category category = Category.builder().title(title).createDate(LocalDateTime.now()).build();
        return categoryRepository.save(category);
    }

    public Category findByTitle(String title){
        Optional<Category> cate = categoryRepository.findByTitle(title);
        if(cate.isEmpty()){
            cate = Optional.ofNullable(categorysave(title)); //DB에 해당 카테고리를 생성해준다.
        }

        if(cate.isPresent()){
            return cate.get();
        }else{
            throw new NotFoundException("카테고리를 찾을 수 없습니다.");
        }
    }
}

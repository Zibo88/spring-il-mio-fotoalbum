package com.corsojava.album.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corsojava.album.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}

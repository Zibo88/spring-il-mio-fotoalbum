package com.corsojava.album.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.corsojava.album.model.Category;
import com.corsojava.album.repository.CategoryRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@GetMapping
	public String index(Model model) {
		
		List<Category> elencoCategorie =  categoryRepository.findAll();
		
		model.addAttribute("elencoCategorie",elencoCategorie);
		return "categories/index";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		Category newCategory = new Category();
		model.addAttribute("newCategory",newCategory);
		return "categories/create";
	}
	
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("newCategory") Category formCategory,BindingResult bindigResult, Model model) {
		
		if(bindigResult.hasErrors()) {
			return "categories/create";
		}
		categoryRepository.save(formCategory);
		return "redirect:/categories";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		Category categoryToEdit = categoryRepository.getReferenceById(id);
		model.addAttribute("categoryToEdit",categoryToEdit);
		return "categories/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("categoryToEdit") Category formCategoryToEdit, BindingResult bindigResult, Model model) {
		if(bindigResult.hasErrors()) {
			return "categories/edit";
		}
		categoryRepository.save(formCategoryToEdit);
		
		return "redirect:/categories";
	}
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id, Model model) {
		
		categoryRepository.deleteById(id);
		
		return "redirect:/categories";
	}
}

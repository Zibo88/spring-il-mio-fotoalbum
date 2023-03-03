package com.corsojava.album.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.corsojava.album.model.Category;
import com.corsojava.album.model.Foto;
import com.corsojava.album.repository.CategoryRepository;
import com.corsojava.album.repository.FotoRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/foto")
public class FotoController {
	
	@Autowired
	FotoRepository fotoRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	
	@GetMapping
	public String index(@RequestParam(name="keyword", required = false) String keyword, Model model) {
		List<Foto> elencoFoto;
		if(keyword == null) {
			elencoFoto = fotoRepository.findAll();
		}else {
			elencoFoto = fotoRepository.findByTitoloLike("%"+keyword+"%");
		}
		
		model.addAttribute("elencoFoto",elencoFoto);
		return "foto/index";
	}
	
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id")Integer id, Model model) {
		
		Foto foto = fotoRepository.getReferenceById(id);
		
		
		model.addAttribute("foto",foto);
		return "foto/show";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		Foto foto = new Foto();
		List<Category> elencoCategorie = categoryRepository.findAll();
		model.addAttribute("foto" ,foto);
		model.addAttribute("elencoCategorie" ,elencoCategorie);
		return "foto/create";
	}
	
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("foto") Foto formFoto, BindingResult bindingResult ,Model model ) {
		
		if(bindingResult.hasErrors()) {
			return "foto/create";
		}
		
		fotoRepository.save(formFoto);
		
		return "redirect:/foto";
	}

	//edit
		@GetMapping("/edit/{id}")
		public String edit(@PathVariable("id") Integer id, Model model) {
			Foto fotoToUpdate;
			List<Category> elencoCategorie = categoryRepository.findAll();
			fotoToUpdate = fotoRepository.getReferenceById(id);
			model.addAttribute("elencoCategorie" ,elencoCategorie);
			model.addAttribute("fotoToUpdate",fotoToUpdate);
			return "foto/edit";
		}
		
		@PostMapping("/edit/{id}")
		public String update(@Valid @ModelAttribute("fotoToUpdate") Foto fotoEditForm,BindingResult bindingResult, Model model) {
			
			if(bindingResult.hasErrors()) {
				return "foto/edit";
			}
			
			fotoRepository.save(fotoEditForm);
			
			return "redirect:/foto";
		}
		
		@PostMapping("/delete/{id}")
		public String delete(@PathVariable("id") Integer id) {
			
			fotoRepository.deleteById(id);
			return "redirect:/foto";
		}
	
}

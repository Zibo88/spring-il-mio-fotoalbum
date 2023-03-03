package com.corsojava.album.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corsojava.album.model.Foto;


public interface FotoRepository extends JpaRepository<Foto, Integer> {
	
	public List<Foto> findByTitoloLike(String titolo);
}

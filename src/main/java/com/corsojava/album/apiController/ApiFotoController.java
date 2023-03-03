package com.corsojava.album.apiController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.corsojava.album.model.Foto;
import com.corsojava.album.model.Message;
import com.corsojava.album.repository.FotoRepository;
import com.corsojava.album.repository.MessageRepository;


@RestController
@CrossOrigin
@RequestMapping("/api/foto")
public class ApiFotoController {
	
	@Autowired
	FotoRepository fotoRepository;
	
	@Autowired
	MessageRepository messaggeRepository;
	
	@GetMapping
	public List<Foto> index(@RequestParam(name="titolo", required=false) String titolo, Model model){
		if(titolo == null) {
			return fotoRepository.findAll();
		}else {
			return fotoRepository.findByTitoloLike("%"+titolo+"%");
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Foto> show(@PathVariable("id") Integer id){
		Optional<Foto> foto = fotoRepository.findById(id);
		if(foto.isPresent()) {
			return new ResponseEntity<Foto>(foto.get(), HttpStatus.OK);
		}else {
			return  new ResponseEntity<Foto>(foto.get(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/message/{id}")
	public ResponseEntity<Message> showMessage(@PathVariable("id") Integer id){
		Optional<Message> message = messaggeRepository.findById(id);
		if(message.isPresent()) {
			return new ResponseEntity<Message>(message.get(), HttpStatus.OK);
		}else {
			return  new ResponseEntity<Message>(message.get(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/message")
	public Message create(@RequestBody Message message) {
		
		return messaggeRepository.save(message);
	}
	
	
	@PostMapping("/{id}/message")
	public ResponseEntity<Message> create( @PathVariable("id") Integer id, @RequestBody Message message) {
	    Optional<Foto> foto = fotoRepository.findById(id);
	    if(foto.isPresent()) {
	        message.setFoto(foto.get()); // Aggiunge il messaggio all'elenco dei messaggi associati all'oggetto Foto
	        messaggeRepository.save(message); // Salva l'oggetto Messaggio aggiornato
	        return new ResponseEntity<Message>(message, HttpStatus.CREATED);
	    }else {
	        return new ResponseEntity<Message>(HttpStatus.NOT_FOUND);
	    }
	}
	
	

	

	
	
	

}

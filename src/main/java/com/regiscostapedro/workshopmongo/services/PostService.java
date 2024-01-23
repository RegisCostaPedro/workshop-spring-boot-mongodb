package com.regiscostapedro.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.regiscostapedro.workshopmongo.domain.Post;
import com.regiscostapedro.workshopmongo.repository.PostRepository;
import com.regiscostapedro.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repo;
	
	
	public Post findById(String id) {
	Optional<Post> obj = repo.findById(id);
	return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	//"Buscar posts contendo um dado string no título"
	public List<Post> findByTitle(String text){
		return repo.searchTitle(text);
	}
}

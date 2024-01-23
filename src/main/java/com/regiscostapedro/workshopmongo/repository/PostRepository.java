package com.regiscostapedro.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.regiscostapedro.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
//QUERY METHODS
//"Buscar posts contendo um dado string no título"
	
	@Query("{ 'title':  {$regex: ?0, $options : i} }")
	List<Post> searchTitle(String text);
	List<Post> findByTitleContainingIgnoreCase(String text);
	
}


	
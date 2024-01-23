package com.regiscostapedro.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.regiscostapedro.workshopmongo.domain.Post;
import com.regiscostapedro.workshopmongo.domain.User;
import com.regiscostapedro.workshopmongo.dto.AuthorDTO;
import com.regiscostapedro.workshopmongo.repository.PostRepository;
import com.regiscostapedro.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
//INSTANCIAR OS OBJETOS E SALVAR NO BANCO DE DADOS
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	 
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		//criar primeiro os users para assim depois ele ter a cópia para o DTO post
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null,sdf.parse("21/01/2018"),"Partiu viagem!","Vou viajar para São Paulo abraços!",new AuthorDTO(maria));
		Post post2 = new Post(null,sdf.parse("23/01/2018"),"Bom dia!","/acordei feliz hoje LMAO!",new AuthorDTO(maria));
		
		
		postRepository.saveAll(Arrays.asList(post1,post2));
		
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		userRepository.save(maria);
	}

}

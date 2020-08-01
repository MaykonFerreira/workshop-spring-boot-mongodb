package com.maykon.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.maykon.workshopmongo.domain.Post;
import com.maykon.workshopmongo.domain.User;
import com.maykon.workshopmongo.dto.AuthorDTO;
import com.maykon.workshopmongo.dto.CommentDTO;
import com.maykon.workshopmongo.repository.PostRepository;
import com.maykon.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;	
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		User maykon = new User(null, "Maykon Ferreira", "maykon@gmail.com");
		User ana = new User(null, "Ana Ferreira", "ana@gmail.com");
				
		userRepository.saveAll(Arrays.asList(maria,alex,bob));
		
		Post p1 = new Post(null,sdf.parse("21/03/2020"),"Partiu Viagem", "Vou viajar para SP",new AuthorDTO(maria));
		Post p2 = new Post(null,sdf.parse("23/03/2020"),"Bom Dia", "Acordei Feliz Hoje",new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Boa Viagem Mano !!!",sdf.parse("21/03/2020"),new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite !!!",sdf.parse("21/03/2020"),new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Bom Dia",sdf.parse("21/03/2020"),new AuthorDTO(alex));
		
		p1.getComments().addAll(Arrays.asList(c1,c2));
		p2.getComments().addAll(Arrays.asList(c3));
		
		
		postRepository.saveAll(Arrays.asList(p1,p2));
		
		maria.getPosts().addAll(Arrays.asList(p1,p2));
		
		userRepository.save(maria);
		
	}

	
	
}

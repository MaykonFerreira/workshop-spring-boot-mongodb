package com.maykon.workshopmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.maykon.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	// Referencia para o mongoDB search é do BD e Title é o seu Titulo
	// modo JSon 
	//{ <field>: { $regex: /pattern/, $options: '<options>' } }
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }") 
	List<Post> searchTitle(String text);
	// ex de busca : http://localhost:8080/posts/titlesearch?text=dia
	// Essa linha ela ignora Ucase e Lcase
	List<Post> findByTitleContainingIgnoreCase(String text);
	
	// Agora ficou show
	// Essa consulta é muito dinamica com varias condições
	// ex de Busca http://localhost:8080/posts/fullsearch?text=viaj&minDate=2020-01-01&maxDate=2020-03-31
	@Query("{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
}

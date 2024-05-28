package com.springboot.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.entities.Category;
import com.springboot.entities.Post;
import com.springboot.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
	org.springframework.data.domain.Page<Post> findByUser(User user,Pageable pageble); 
	
	org.springframework.data.domain.Page<Post> findByCategory(Category category,Pageable pageble);

	
	//performs this %key%
	@Query("select p from Post p where p.title like :key")
	List<Post> searchByTitle(@Param("key")String title);
}

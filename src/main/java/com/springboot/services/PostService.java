package com.springboot.services;

import java.util.List;

import com.springboot.payloads.PostDto;
import com.springboot.payloads.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto, Integer UserId, Integer CategoryId);

	PostDto updatePost(PostDto postDto,Integer Id);
	
	void deletePost(Integer Id);
	
	PostDto getPostById(Integer Id);
	
	PostResponse getAllPost(Integer PageNumber,Integer PageSize, String sortby,String sortDir);
	
	
	//Getting all posts by categoryId
	PostResponse getPostsByCategory(Integer categoryId, Integer pageNum, Integer pageSize);
	
	//Getting all posts by UserId
	PostResponse getPostsByUser(Integer postId,Integer pageNum, Integer pageSize);
	
	List<PostDto> searchPosts(String title);
	

}

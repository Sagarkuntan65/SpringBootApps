package com.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.payloads.ApiResponse;
import com.springboot.payloads.PostDto;
import com.springboot.payloads.PostResponse;
import com.springboot.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable(name = "userId") Integer userId,
			@PathVariable(name="categoryId") Integer categoryId) {
		PostDto postdto = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(postdto,HttpStatus.ACCEPTED);

	}

//	@PostMapping("/user/{userId}/category/{categoryId}/posts")
//	public ResponseEntity<PostDto> CreatePost(@RequestBody PostDto postDto, @PathVariable Integer categoryid,
//			@PathVariable Integer userId) {
//		PostDto createPost = this.postService.createPost(postDto, userId, categoryid);
//		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
//	}
	
	@GetMapping("allposts/")
	public ResponseEntity<PostResponse> getPost(@RequestBody PostDto postDto,
			@RequestParam(name = "pagenumber",defaultValue = "1",required = false) Integer pagenumber,
			@RequestParam(name="pagesize",defaultValue = "4",required = false) Integer pagesize,
			@RequestParam(name="sortby", defaultValue = "postId",required = false)String sortby,
			@RequestParam(name="sortDir", defaultValue = "asc",required = false)String sortDir){
		
		PostResponse postResponse = this.postService.getAllPost(pagenumber,pagesize, sortby, sortDir);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId ){
		PostDto postById = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postById,HttpStatus.OK);		
	}
	
	@GetMapping("Category/{categoryId}/posts/")
	public ResponseEntity<PostResponse> getPostsByCategory(@PathVariable Integer categoryId,
			@RequestParam(name = "pagenum",defaultValue = "1",required = false) Integer pagenum,
			@RequestParam(name = "pagesize", defaultValue = "3",required = false) Integer pagesize){
		PostResponse postsresponse = this.postService.getPostsByCategory(categoryId, pagenum, pagesize);
		return new ResponseEntity<PostResponse>(postsresponse,HttpStatus.OK);
	}
	
	@GetMapping("User/{userId}/posts/")
	public ResponseEntity<PostResponse> getPostsByUser(@PathVariable Integer userId,
			@RequestParam(name = "pagenum",defaultValue = "1",required = false) Integer pagenum,
			@RequestParam(name = "pagesize", defaultValue = "3",required = false) Integer pagesize){
		PostResponse postResponse = this.postService.getPostsByUser(userId,pagenum,pagesize);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	
	@PutMapping("/post/{postId}/")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId)
	{
		PostDto post=this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(post,HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("posts/{postId}/")
	public ApiResponse deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ApiResponse("this post has been deleted",true,postId);
	}
	
	@GetMapping("posts/search/{keywords}/")
	public ResponseEntity<List<PostDto>> searchByPosts(@PathVariable("keywords") String keywords ){
		List<PostDto> searchPosts = this.postService.searchPosts(keywords);
		return new ResponseEntity<List<PostDto>>(searchPosts,HttpStatus.OK);
		
	}
}

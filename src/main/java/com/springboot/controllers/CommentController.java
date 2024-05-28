package com.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.payloads.ApiResponse;
import com.springboot.payloads.CommentDto;
import com.springboot.services.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {
	

	
	@Autowired
	CommentService commentService;
	
	@PostMapping("posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment,@PathVariable (name="postId") Integer postId){
		
		CommentDto createdcomment = this.commentService.createcomment(comment, postId);
		return new ResponseEntity<CommentDto>(createdcomment,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
		this.commentService.DeleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfully ", true,commentId ),HttpStatus.OK);
	}
	

}

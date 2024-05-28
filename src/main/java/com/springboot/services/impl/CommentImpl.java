package com.springboot.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.Exceptions.ResourceNotFoundException;
import com.springboot.entities.Comment;
import com.springboot.entities.Post;
import com.springboot.payloads.CommentDto;
import com.springboot.repositories.CommentRepo;
import com.springboot.repositories.PostRepo;
import com.springboot.services.CommentService;

@Service
public class CommentImpl implements CommentService{
	
	@Autowired
	CommentRepo commentRepo;
	
	@Autowired
	PostRepo postRepo;
	
	@Autowired
	ModelMapper mapper;

	@Override
	public CommentDto createcomment(CommentDto commentDto, Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Postid", postId));
		Comment comment = this.mapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment savedComment = this.commentRepo.save(comment);
		return this.mapper.map(savedComment, CommentDto.class);
		
		
	}

	@Override
	public void DeleteComment(int commentid) {
		Comment comment=this.commentRepo.findById(commentid).orElseThrow(()->new ResourceNotFoundException("Comment", "commentid", commentid));
		this.commentRepo.delete(comment);		
	}

}

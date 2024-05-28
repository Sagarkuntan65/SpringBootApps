package com.springboot.services;

import com.springboot.payloads.CommentDto;

public interface CommentService {
	
CommentDto createcomment(CommentDto commentDto,Integer postId);
	
	void DeleteComment(int commentid);

}

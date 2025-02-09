package com.springboot.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	private int postid;

	private String title;

	private String content;

	private String imageName;

	private Date addDate;

	private CategoryDto category;

	private UserDto user;
	
	private Set<CommentDto> comment=new HashSet<>();
}

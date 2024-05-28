package com.springboot.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Posts")
@Setter
@Getter
@NoArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;
	
	@Column(length = 1000 , name = "PostTiltle")
	private String title;
	
	@Column(length = 1000 , name = "PostContent")
	private String content;
	
	@Column(length = 1000 , name = "PostImage")
	private String imageName;
	
	private Date addDate;
	
	/*
	 * Many to one marks that many posts can have one category
	 * */ 
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	/*
	 * Many to one marks that many posts can have one user
	 * */ 
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	/*
	 * One to many marks that one post can have multiple Comment
	 * */
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private Set<Comment> comments=new HashSet<>();
	
	
	

}

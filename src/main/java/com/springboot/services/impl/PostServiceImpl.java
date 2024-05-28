package com.springboot.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.Exceptions.ResourceNotFoundException;
import com.springboot.entities.Category;
import com.springboot.entities.Post;
import com.springboot.entities.User;
import com.springboot.payloads.PostDto;
import com.springboot.payloads.PostResponse;
import com.springboot.repositories.CategoryRepo;
import com.springboot.repositories.PostRepo;
import com.springboot.repositories.UserRepo;
import com.springboot.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private UserRepo userRepo;

//	@Override
//	public PostDto createPost(PostDto postDto, Integer UserId, Integer CategoryId) {
//		Post post=this.modelMapper.map(postDto, Post.class);
//		Category category=this.categoryRepo.findById(CategoryId).orElseThrow(()->new ResourceNotFoundException("CAtegory", "CategoryId", CategoryId));
//		User user=this.userRepo.findById(UserId).orElseThrow(()->new ResourceNotFoundException("User", "UserID", UserId));
//		post.setAddDate(new Date());
//		post.setImageName("image.png");
//		post.setUser(user);
//		post.setCategory(category);	
//		Post newPost=this.postRepo.save(post);
//		return this.modelMapper.map(newPost, PostDto.class);
//	}

	@Override
	public PostDto createPost(PostDto postDto, Integer UserId, Integer CategoryId) {
		Post post = this.modelMapper.map(postDto, Post.class);
		User user = this.userRepo.findById(UserId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "UserId", UserId));
		Category category = this.categoryRepo.findById(CategoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", CategoryId));

		post.setImageName("default.png");
		post.setAddDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post UpdatedPost = this.postRepo.save(post);
		return this.modelMapper.map(UpdatedPost, PostDto.class);

	}

	@Override
	public PostDto getPostById(Integer Id) {
		Post post = this.postRepo.findById(Id).orElseThrow(() -> new ResourceNotFoundException("Post", "Postid", Id));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer PageSize, String sortby, String sortDir) {

		Sort sort = sortDir.equalsIgnoreCase("asc")?Sort.by(sortby).ascending():Sort.by(sortby).descending();
		
		Pageable pageable = PageRequest.of(pageNumber, PageSize, sort);
		Page<Post> postsbypage = this.postRepo.findAll(pageable);
		List<Post> Posts = postsbypage.getContent();
		List<PostDto> postDtos = Posts.stream().map((Post) -> this.modelMapper.map(Post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(postsbypage.getNumber());
		postResponse.setPageSize(postsbypage.getSize());
		postResponse.setTotalElements(postsbypage.getTotalElements());
		postResponse.setTotalPages(postsbypage.getTotalPages());
		postResponse.setLastpage(postsbypage.isLast());
		return postResponse;
	}

	@Override
	public PostResponse getPostsByCategory(Integer categoryId, Integer pageNum, Integer pageSize) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Categoryid", categoryId));

		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<Post> postsAll = this.postRepo.findByCategory(category, pageable);

		List<PostDto> postDtos = postsAll.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(postsAll.getNumber());
		postResponse.setPageSize(postsAll.getSize());
		postResponse.setTotalElements(postsAll.getTotalElements());
		postResponse.setTotalPages(postsAll.getTotalPages());
		postResponse.setLastpage(postsAll.isLast());
		return postResponse;
	}

	@Override
	public PostResponse getPostsByUser(Integer postId, Integer pageNum, Integer pageSize) {

		User user = this.userRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Userid", postId));
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<Post> Posts = this.postRepo.findByUser(user, pageable);
		List<PostDto> postdtos = Posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postdtos);
		postResponse.setPageNumber(Posts.getNumber());
		postResponse.setPageSize(Posts.getSize());
		postResponse.setTotalElements(Posts.getTotalElements());
		postResponse.setTotalPages(Posts.getTotalPages());
		postResponse.setLastpage(Posts.isLast());
		return postResponse;

	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer Id) {

		Post post = this.postRepo.findById(Id).orElseThrow(() -> new ResourceNotFoundException("Post", "Postid", Id));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		Post savepost = this.postRepo.save(post);
		return this.modelMapper.map(savepost, PostDto.class);

	}

//	@Override
//	public List<PostDto> searchPosts(String keyword) {
//		List<Post> post=this.postRepo.findby
//		return null;
//	}


	@Override
	public void deletePost(Integer Id) {
		Post post = this.postRepo.findById(Id).orElseThrow(() -> new ResourceNotFoundException("Post", "postid", Id));
		this.postRepo.delete(post);
	}

	@Override
	public List<PostDto> searchPosts(String title) {
		List<Post> Posts = this.postRepo.searchByTitle("%"+title+"%");
		List<PostDto> postdtos=Posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postdtos;
	}

}

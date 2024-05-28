package com.springboot.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	private Integer categoryId;

	@NotBlank
	@Size(min = 4, message = "Minimum size should be between 4-20 characters")
	private String categoryTitle;
	@NotBlank
	@Size(min = 10, message = "Minimum size should be between 5-20 characters")
	private String categoryDescription;
}

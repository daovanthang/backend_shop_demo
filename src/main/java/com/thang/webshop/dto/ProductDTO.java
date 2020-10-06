package com.thang.webshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	
	private Long id;
	
	 private String name;
	 
	 private String nameSeo;
	 
	 private Integer productStatus;
	 
	 private Long oldPrice;
	 
	 private Long nowPrice;
	 
	 private String description;
	 
	 private String[] jsonImg;
	 
	 private String categoryName;
	 private Long categoryId;
	 private String categoryNameSeo;

}

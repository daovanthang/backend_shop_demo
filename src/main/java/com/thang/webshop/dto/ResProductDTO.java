package com.thang.webshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResProductDTO {
	private Long id;
	private Boolean isActive;
	private String name;
	private String nameSeo;
	private Long oldPrice;
	private Long nowPrice;
	private String description;
	private String[] jsonImg;
	private ResCategoryDTO category;
	

}

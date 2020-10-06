package com.thang.webshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
	
	private Long id;
	private Boolean isActive;
	
	private String name;
	private String nameSeo;
	private Boolean onMenu;
	private String description;
	
	private String parentNameSeo;
	private String parentName;
	private String path;

}

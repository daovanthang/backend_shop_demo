package com.thang.webshop.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResCategoryDTO {
	
	private Long id;
	private Boolean isActive;
	
	private String name;
	private String nameSeo;
	private String description;
	
	private Boolean onMenu;
	
	
	
	private List<ResCategoryDTO> subCategorys;

}

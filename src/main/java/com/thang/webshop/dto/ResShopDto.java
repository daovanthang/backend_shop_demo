package com.thang.webshop.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResShopDto {
	
	private List<ResProductDTO> resProductDto;
	private ResCategoryDTO resCategoryDto;

}

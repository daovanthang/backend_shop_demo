package com.thang.webshop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.thang.webshop.common.ConstantsRegex;
import com.thang.webshop.dto.CategoryDTO;
import com.thang.webshop.dto.ResCategoryDTO;
import com.thang.webshop.entity.Category;
import com.thang.webshop.payload.ApiResponse;
import com.thang.webshop.service.CategoryService;

@RestController
public class CategoryController {

	private CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@PostMapping("/admin/category/create")
	public ResponseEntity<ApiResponse> createCategory(@RequestBody CategoryDTO categoryDto) {
		Category category = categoryService.createCategory(categoryDto);
		ApiResponse apiResponse = new ApiResponse(true, "Thêm loại sản phẩm thành công",category.getId());
		return ResponseEntity.ok(apiResponse);
	}
	@PostMapping("/admin/category/edit")
	public ResponseEntity<ApiResponse> editCategory(@RequestBody CategoryDTO categoryDto) {
		Category category = categoryService.editCategory(categoryDto);
		ApiResponse apiResponse = new ApiResponse(true, "Edit Category thành công",category.getId());
		return ResponseEntity.ok(apiResponse);
	}
	@GetMapping("/admin/category/switch/{id:" + ConstantsRegex.ID + "}")
	public ResponseEntity<ApiResponse> disableCategory(@PathVariable Long id) {
		Category category = categoryService.switchStatus(id);
		ApiResponse apiResponse = new ApiResponse(true, "Bỏ kích hoạt thành công",category.getId());
		return ResponseEntity.ok(apiResponse);
	}
	@GetMapping("/admin/category/delete/{id:" + ConstantsRegex.ID + "}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
		ApiResponse apiResponse = new ApiResponse(true, "Xóa Category thành công",id);
		return ResponseEntity.ok(apiResponse);
	}
	@GetMapping(value = "/category/findById/{id:" + ConstantsRegex.ID + "}")
	public ResponseEntity<CategoryDTO> findCategoryById(@PathVariable Long id) {
		// Find and error if not found
		Category result = this.categoryService.findById(id);
		return ResponseEntity.ok(convertCategoryDto(result));
	}
	
	
	
	@GetMapping("/category")
	public ResponseEntity<List<ResCategoryDTO>> findAllCategory(){
		return ResponseEntity.ok(categoryService.findAllCategory().stream().map(obj -> getSubCategory(obj)).collect(Collectors.toList())); 
	}
	@GetMapping("/category/findByIsActive")
	public ResponseEntity<List<CategoryDTO>> findByIsActive(){
		return ResponseEntity.ok(categoryService.findCategoryByIsActive(true).stream().map(obj -> convertCategoryDto(obj)).collect(Collectors.toList())); 
	}
	
	@GetMapping("/category/findByAllChild/{path:" + ConstantsRegex.PATH + "}")
	public ResponseEntity<List<CategoryDTO>> findAllChild (@PathVariable String path){
		return ResponseEntity.ok(categoryService.findAllChild(path).stream().map(obj->convertCategoryDto(obj)).collect(Collectors.toList())); 
	}
	
	public static CategoryDTO convertCategoryDto(Category category) {
		CategoryDTO categoryDto = new CategoryDTO();
		categoryDto.setId(category.getId());
		categoryDto.setIsActive(category.getIsActive());
		categoryDto.setName(category.getName());
		categoryDto.setNameSeo(category.getNameSeo());
		categoryDto.setPath(category.getPath());
		if(category.getParent()!=null) {
			categoryDto.setParentNameSeo(category.getParent().getNameSeo());
			categoryDto.setParentName(category.getParent().getNameSeo());
		}
			
		return categoryDto;
	}
	public static  ResCategoryDTO convertResCategoryDTO(Category category) {
		ResCategoryDTO resCategoryDto = new ResCategoryDTO();
		resCategoryDto.setId(category.getId());
		resCategoryDto.setIsActive(category.getIsActive());
		resCategoryDto.setName(category.getName());
		resCategoryDto.setNameSeo(category.getNameSeo());
		resCategoryDto.setDescription(category.getDescription());
		
		
		
		if(!category.getSubCategorys().isEmpty()) {
			List<ResCategoryDTO> listResCateDto = new ArrayList<ResCategoryDTO>();
			for (Category subCategory : category.getSubCategorys()) {
				ResCategoryDTO subResCategoryDto = new ResCategoryDTO();
				subResCategoryDto.setId(subCategory.getId());
				subResCategoryDto.setIsActive(subCategory.getIsActive());
				subResCategoryDto.setName(subCategory.getName());
				subResCategoryDto.setNameSeo(subCategory.getNameSeo());
				listResCateDto.add(subResCategoryDto);
			}
			resCategoryDto.setSubCategorys(listResCateDto);
		}else
			resCategoryDto.setSubCategorys(null);
		
		return resCategoryDto;
	}
	 
	public static ResCategoryDTO getSubCategory(Category category) {
		ResCategoryDTO resCategoryDto = new ResCategoryDTO();
		ModelMapper modelMapper = new ModelMapper();
		// user here is a prepopulated User instance
		 resCategoryDto = modelMapper.map(category, ResCategoryDTO.class);
			return resCategoryDto;
		}
		
	
}



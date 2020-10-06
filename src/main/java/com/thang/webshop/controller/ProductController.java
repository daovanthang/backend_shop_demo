package com.thang.webshop.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thang.webshop.common.ConstantsRegex;
import com.thang.webshop.dto.ProductDTO;
import com.thang.webshop.dto.ResCategoryDTO;
import com.thang.webshop.dto.ResProductDTO;
import com.thang.webshop.dto.ResShopDto;
import com.thang.webshop.entity.Category;
import com.thang.webshop.entity.Product;
import com.thang.webshop.payload.ApiResponse;
import com.thang.webshop.service.CategoryService;
import com.thang.webshop.service.ProductService;

@RestController
public class ProductController {

	private ProductService productService;
	private CategoryService categoryService;

	@Autowired
	public ProductController(ProductService productService,CategoryService categoryService) {
		this.productService = productService;
		this.categoryService = categoryService;
	}

	@PostMapping("/admin/product/create")
	public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDTO productDto) {
		productService.createProduct(productDto);
		ApiResponse apiResponse = new ApiResponse(true, "Thêm sản phẩm thành công");
		return ResponseEntity.ok(apiResponse);
	}
	
	
	@PostMapping("/admin/product/edit")
	public ResponseEntity<ApiResponse> editProduct(@RequestBody ProductDTO productDto) {
		productService.editProduct(productDto);
		ApiResponse apiResponse = new ApiResponse(true, "Thêm sản phẩm thành công");
		return ResponseEntity.ok(apiResponse);
	}
	@GetMapping("/admin/product/delete/{id:" + ConstantsRegex.ID + "}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
		productService.deleteById(id);
		ApiResponse apiResponse = new ApiResponse(true, "Xóa sản phẩm thành công");
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/product")
	public ResponseEntity<List<ResProductDTO>> findAllProduct(){
		return ResponseEntity.ok(productService.findAllProduct().stream().map(obj -> convertProduct(obj)).collect(Collectors.toList())); 
	}
	
	@GetMapping("/product/findByNameSeo/{sp:" +ConstantsRegex.SINGLE_TEXT + "}")
	public ResponseEntity<ProductDTO> findByNameSeo(@PathVariable(name = "sp") String nameSeo){
		Product product = productService.findByNameSeo(nameSeo);
		return ResponseEntity.ok(convertEditProduct(product)); 
	}
	@GetMapping("/product/findByState/{state:" +ConstantsRegex.ID + "}")
	public ResponseEntity<List<ResProductDTO>> findByNameSeo(@PathVariable(name = "state") Integer state){
		return ResponseEntity.ok(productService.findByState(state).stream().map(obj -> convertProduct(obj)).collect(Collectors.toList())); 
	}
	@GetMapping("/product/findByCategory/{categoryNameSeo:" +ConstantsRegex.SINGLE_TEXT + "}")
	public ResponseEntity<ResShopDto> findByCategoryNameSeo(@PathVariable(name = "categoryNameSeo") String categoryNameSeo){
		categoryService.findByNameSeo(categoryNameSeo);
		productService.findByCategory(categoryNameSeo);
		return ResponseEntity.ok(convertToShop(categoryService.findByNameSeo(categoryNameSeo), productService.findByCategory(categoryNameSeo))); 
	}
	@GetMapping("/product/findByContainName")
	public ResponseEntity<ResShopDto> findProductByName(@RequestParam(name = "name") String name){
		return ResponseEntity.ok(convertToShop(categoryService.findByNameTitle(name), productService.findByNameContain(name)));
		
	}
	
	
	
	
	public static ResProductDTO convertProduct(Product product) {
		ResProductDTO productDto = new ResProductDTO();
		
		//convert string to array json
		String[] array = product.getJsonImg().split(",");
		productDto.setId(product.getId());
		productDto.setJsonImg(array);
		productDto.setIsActive(product.getIsActive());
		productDto.setName(product.getName());
		productDto.setNameSeo(product.getNameSeo());
		productDto.setNowPrice(product.getNowPrice());
		productDto.setOldPrice(product.getOldPricel());
		productDto.setDescription(product.getDescription());
//		if(product.getCategory()!=null) {
//			productDto.setCategory(getSubCategory(product.getCategory()));
//		}
		return productDto;
	}
	public static ProductDTO convertEditProduct(Product product) {
		ProductDTO productDto = new ProductDTO();
		
		//convert string to array json
		productDto.setId(product.getId());
		String[] array = product.getJsonImg().split(",");
		productDto.setJsonImg(array);
		productDto.setName(product.getName());
		productDto.setNameSeo(product.getNameSeo());
		productDto.setNowPrice(product.getNowPrice());
		productDto.setOldPrice(product.getOldPricel());
		productDto.setDescription(product.getDescription());
		productDto.setProductStatus(product.getState().getId());
		productDto.setCategoryNameSeo(product.getCategory().getNameSeo());
		productDto.setCategoryName(product.getCategory().getName());
//		if(product.getCategory()!=null) {
//			productDto.setCategory(getSubCategory(product.getCategory()));
//		}
		return productDto;
	}
	public static ResCategoryDTO getSubCategory(Category category) {
		ResCategoryDTO resCategoryDto = new ResCategoryDTO();
		ModelMapper modelMapper = new ModelMapper();
		// user here is a prepopulated User instance
		 resCategoryDto = modelMapper.map(category, ResCategoryDTO.class);
			return resCategoryDto;
		}
	
	public static ResShopDto convertToShop(Category category, List<Product> product) {
		
		ResCategoryDTO resCategoryDto = CategoryController.convertResCategoryDTO(category);
		System.out.println(resCategoryDto);
		List<ResProductDTO>  resProductDtos =product.stream().map(obj ->convertProduct(obj)).collect(Collectors.toList());
		ResShopDto resShopDto = new ResShopDto(resProductDtos, resCategoryDto);
		return resShopDto;
	}
		
	

}

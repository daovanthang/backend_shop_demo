package com.thang.webshop.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thang.webshop.common.message.MsgResponseException;
import com.thang.webshop.common.message.MsgsModel;
import com.thang.webshop.dto.ProductDTO;
import com.thang.webshop.entity.Category;
import com.thang.webshop.entity.Product;
import com.thang.webshop.entity.type.EnumConstants.ProductStatus;
import com.thang.webshop.repository.CategoryRepository;
import com.thang.webshop.repository.ProductRepository;

@Service
public class ProductService {

	private ProductRepository productRepository;
	private CategoryService categoryService;

	@Autowired
	public ProductService(ProductRepository productRepository, CategoryService categoryService) {
		this.productRepository = productRepository;
		this.categoryService = categoryService;
	}

	/**
	 * Create Product
	 * 
	 * @param productDto
	 * @return
	 */
	public Product createProduct(ProductDTO productDto) {

		Optional<Product> opProduct = productRepository.findByNameSeo(productDto.getNameSeo());

		if (opProduct.isPresent()) {
			throw new MsgResponseException(new MsgsModel("MSG003", "Product"));
		}

		Product product = new Product();
		product.setName(productDto.getName());
		product.setNameSeo(productDto.getNameSeo());
		product.setNowPrice(productDto.getNowPrice());
		product.setOldPricel(productDto.getOldPrice());
		product.setDescription(productDto.getDescription());
		product.setIsActive(true);
		product.setState(ProductStatus.getFromId(productDto.getProductStatus()));

		// Json process Image
		System.out.println(productDto.getJsonImg().length + "tostring");

		String jsonImg = Arrays.toString(productDto.getJsonImg());
//		

		String[] arrayImg = jsonImg.split(",");
		if (arrayImg.length != 1)
			arrayImg[0] = arrayImg[0].substring(1);
		arrayImg[arrayImg.length - 1] = arrayImg[arrayImg.length - 1].substring(1,
				arrayImg[arrayImg.length - 1].length() - 1);

		StringBuilder str = new StringBuilder();
		for (int i = 0; i < arrayImg.length; i++) {
			if (i == arrayImg.length - 1)
				str.append(arrayImg[i].trim());
			else
				str.append(arrayImg[i].trim() + ",");
		}
		product.setJsonImg(str.toString());
		// Find Category

		Category category = categoryService.findByNameSeo(productDto.getCategoryNameSeo());
		;

		product.setCategory(category);

		return productRepository.save(product);
	}

	public Product editProduct(ProductDTO productDto) {
		Optional<Product> opProduct = productRepository.findById(productDto.getId());

		if (opProduct.isEmpty()) {
			throw new MsgResponseException(new MsgsModel("MSG003", "Product"));
		}

		opProduct.get().setName(productDto.getName());
		opProduct.get().setNameSeo(productDto.getNameSeo());
		opProduct.get().setNowPrice(productDto.getNowPrice());
		opProduct.get().setOldPricel(productDto.getOldPrice());
		opProduct.get().setDescription(productDto.getDescription());
		opProduct.get().setIsActive(true);
		opProduct.get().setState(ProductStatus.getFromId(productDto.getProductStatus()));

		// Json process Image

		String jsonImg = Arrays.toString(productDto.getJsonImg());
//		

		String[] arrayImg = jsonImg.split(",");
		if (arrayImg.length != 1)
			arrayImg[0] = arrayImg[0].substring(1);
		arrayImg[arrayImg.length - 1] = arrayImg[arrayImg.length - 1].substring(1,
				arrayImg[arrayImg.length - 1].length() - 1);

		StringBuilder str = new StringBuilder();
		for (int i = 0; i < arrayImg.length; i++) {
			if (i == arrayImg.length - 1)
				str.append(arrayImg[i].trim());
			else
				str.append(arrayImg[i].trim() + ",");
		}
		opProduct.get().setJsonImg(str.toString());
		// Find Category

		Category category = categoryService.findByNameSeo(productDto.getCategoryNameSeo());
		;

		opProduct.get().setCategory(category);
		return productRepository.save(opProduct.get());

	}

	/**
	 * Find All Product
	 * 
	 * @return
	 */
	public List<Product> findAllProduct() {
		return productRepository.findAll();
	}

	/**
	 * Delete a product
	 * 
	 * @param CategoryId
	 */
	public void deleteById(Long id) {

		productRepository.deleteById(findById(id).getId());
	}

	/**
	 * FindById
	 * 
	 * @param id
	 * @return
	 */
	public Product findById(Long id) {
		Optional<Product> opProduct = productRepository.findById(id);

		if (opProduct.isEmpty())
			throw new MsgResponseException(new MsgsModel("MSG002", "Product"));

		return opProduct.get();

	}

	public Product findByNameSeo(String nameSeo) {
		Optional<Product> opProduct = productRepository.findByNameSeo(nameSeo);
		if (opProduct.isEmpty())
			throw new MsgResponseException(new MsgsModel("MSG002", "Product"));
		return opProduct.get();

	}

	public List<Product> findByState(Integer state) {
		return productRepository.findByState(ProductStatus.getFromId(state));
	}

	public List<Product> findByCategory(String categoryNameSeo) {
		Category category = categoryService.findByNameSeo(categoryNameSeo);

		return category.getProducts();

	}

	public List<Product> findByNameContain(String name) {
		return productRepository.findByNameContaining(name);
	}

}

package com.thang.webshop.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thang.webshop.common.message.MsgResponseException;
import com.thang.webshop.common.message.MsgsModel;
import com.thang.webshop.dto.CategoryDTO;
import com.thang.webshop.entity.Category;
import com.thang.webshop.repository.CategoryRepository;

@Service
public class CategoryService {

	private CategoryRepository categoryRepository;

	@Autowired
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	/**
	 * Create category
	 * 
	 * @param categoryDto
	 * @return
	 */
	@Transactional
	public Category createCategory(CategoryDTO categoryDto) {

		Optional<Category> opCategory = categoryRepository.findByNameSeo(categoryDto.getNameSeo());
		
		if (opCategory.isPresent()) {
			throw new MsgResponseException(new MsgsModel("MSG003", "Category"));
		}

		Category category = new Category();
		category.setName(categoryDto.getName());
		category.setNameSeo(categoryDto.getNameSeo());
		category.setIsActive(true);
		
		//create category with parent
		if(categoryDto.getParentNameSeo() !=null && categoryDto.getParentNameSeo().trim().length()>0) {
			
			Optional<Category> categoryOp = categoryRepository.findByNameSeo(categoryDto.getParentNameSeo());
			
			if (categoryOp.isEmpty()) {
				throw new MsgResponseException(new MsgsModel("MSG002", "Category"));
			}
			category.setParent(categoryOp.get());
			category.setOnMenu(false);
			category.setDescription(categoryDto.getDescription());
			
			
		}
		else
			category.setOnMenu(true);
		
		return addPath(categoryRepository.save(category));
	}
	
	public Category editCategory(CategoryDTO categoryDto) {

		Optional<Category> opCategory = categoryRepository.findById(categoryDto.getId());
		
		if (opCategory.isEmpty()) {
			throw new MsgResponseException(new MsgsModel("MSG003", "Category"));
		}

		
		opCategory.get().setName(categoryDto.getName());
		opCategory.get().setNameSeo(categoryDto.getNameSeo());
		opCategory.get().setIsActive(categoryDto.getIsActive());
		
		//create category parent
		if(categoryDto.getParentNameSeo() !=null && categoryDto.getParentNameSeo().trim().length()>0) {
			
			Optional<Category> categoryOp = categoryRepository.findByNameSeo(categoryDto.getParentNameSeo());
			if (categoryOp.isEmpty()) {
				throw new MsgResponseException(new MsgsModel("MSG002", "Category"));
			}
			opCategory.get().setParent(categoryOp.get());
			opCategory.get().setOnMenu(false);
		}
		else
			opCategory.get().setOnMenu(true);
		
		return categoryRepository.save(opCategory.get());
	}

	/**
	 * Find All Category
	 * 
	 * @return
	 */
	public List<Category> findAllCategory() {
		System.out.println(categoryRepository.findAll());
		return categoryRepository.findAll();
	}

	/**
	 * Switch a category
	 * 
	 * @param CategoryId
	 */
	public Category switchStatus(Long id) {

		Category category = findById(id);
		category.setIsActive(!category.getIsActive());
		return categoryRepository.save(category);
	}
	
	public void deleteCategory(Long id) {

		Category category = findById(id);
		 categoryRepository.delete(category);
	}
	
	public List<Category> findCategoryByIsActive(Boolean isActive) {
		return categoryRepository.findByIsActive(isActive);
	}
	
	
	
	/**
	 * FindById
	 * @param id
	 * @return
	 */
	public Category findById(Long id) {
		Optional<Category> opCategory = categoryRepository.findById(id);

		if (opCategory.isEmpty())
			throw new MsgResponseException(new MsgsModel("MSG002", "category"));
		
		return opCategory.get();
		
	}
	
	public Category findByNameSeo(String nameSeo) {
		Optional<Category> opCategory = categoryRepository.findByNameSeo(nameSeo);
		
		if (opCategory.isEmpty())
			throw new MsgResponseException(new MsgsModel("MSG002", "category"));
		
		return opCategory.get();
	}
	public Category findByNameTitle(String name) {
		
		Optional<Category> opCategory = categoryRepository.findByName(name);
		
		if (opCategory.isEmpty())
			throw new MsgResponseException(new MsgsModel("MSG002", "category"));
		
		return opCategory.get();
	}
	@Transactional
	public Category addPath(Category category) {
		System.out.println("running Add PAth");
		if(category.getParent() == null)
			category.setPath(category.getId()+"-");
		else
			category.setPath(category.getParent().getPath()+ category.getId()+"-"); 
		return category;

	}
	
	public List<Category> findAllChild(String path){
		return categoryRepository.findByPathContaining(path);
	}

}

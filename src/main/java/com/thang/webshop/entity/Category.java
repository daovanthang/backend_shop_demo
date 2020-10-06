package com.thang.webshop.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.thang.webshop.model.audit.UserDateAudit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "category")
public class Category extends UserDateAudit {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "is_active", nullable = false)
	private Boolean isActive;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "name_seo", nullable = false)
	private String nameSeo;
	
	@Column(name = "on_menu", nullable = false)
	private Boolean onMenu;
	
	@Column(name = "description", columnDefinition = "TEXT", nullable = true)
	private String description;
	@Column(name = "path",nullable = true)
	private String path;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private List<Product> products;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id", nullable = true)
	private Category parent;

	@OneToMany(fetch = FetchType.EAGER,mappedBy = "parent")
	private List<Category> subCategorys;
	


	
}

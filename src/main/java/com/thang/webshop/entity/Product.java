package com.thang.webshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.thang.webshop.entity.type.EnumConstants.ProductStatus;
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
@Table(name = "product")
public class Product extends UserDateAudit {

	/**
	* 
	*/
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

	@Column(name = "old_price", nullable = false)
	private Long oldPricel;

	@Column(name = "now_price", nullable = false)
	private Long nowPrice;

	@Column(name = "description", columnDefinition = "TEXT", nullable = false)
	private String description;

	@Column(name = "json_img", nullable = true ,columnDefinition = "TEXT")
	private String jsonImg;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;
	
	@Column(name = "status" , nullable =false)
	private ProductStatus state;
	
	

}

package com.thang.webshop.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
	
	
    public ApiResponse(Boolean success, String message) {
		this.success = success;
		this.message = message;
	}
	private Boolean success;
    private String message;
    private Long id;

  
}
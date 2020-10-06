package com.thang.webshop.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageModel {

	private String code;

	private String message;

	public MessageModel(String code) {
		this.code = code;
		this.message = MsgUtil.getMsg(code);
	}

	public MessageModel(String code, Object... objects) {
		this.code = code;
		this.message = MsgUtil.getMsg(code, objects);
	}
}

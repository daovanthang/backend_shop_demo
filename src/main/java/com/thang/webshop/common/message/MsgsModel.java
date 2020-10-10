package com.thang.webshop.common.message;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class MsgsModel {

	private List<MessageModel> messages = new ArrayList<>(0);

	public MsgsModel() {
	}

	/**
	 * create message with parameter
	 * 
	 * @param msgCode String
	 * @param param   Object[]
	 */
	public MsgsModel(String msgCode, Object... param) {
		messages.add(new MessageModel(msgCode, param));
	}

	/**
	 * create messge only
	 * 
	 * @param msgCode String
	 */
	public MsgsModel(String msgCode) {
		messages.add(new MessageModel(msgCode, MsgUtil.getMsg(msgCode)));
	}

	/**
	 * add message with parameter
	 * 
	 * @param msgCode String
	 * @param param   Object[]
	 * @return
	 */
	public MsgsModel addMsg(String msgCode, Object... param) {
		messages.add(new MessageModel(msgCode, param));
		return this;
	}

	/**
	 * add message only
	 * 
	 * @param msgCode
	 * @return
	 */
	public MsgsModel addMsg(String msgCode) {
		messages.add(new MessageModel(msgCode, MsgUtil.getMsg(msgCode)));
		return this;
	}

	/**
	 * check has message in list
	 * 
	 * @return boolean
	 */
	public Boolean hasMsg() {
		return this.messages.size() > 0;
	}
}

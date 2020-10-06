package com.thang.webshop.message;

/**
 * @author thiep.nguyen
 */
public abstract class MsgException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MsgException() {
		super();
	}

	public abstract MsgsModel getMsgsModel();
}

package com.thang.webshop.message;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class MsgResponseException extends MsgException {

	private MsgsModel msgModel;

	private static final long serialVersionUID = 1L;

	private MsgsModel[] msgEntities = new MsgsModel[0];

	public MsgResponseException(MsgsModel msgModel) {
		super();
		this.msgModel = msgModel;
	}

	public MsgResponseException() {
		super();
	}

	@Override
	public MsgsModel getMsgsModel() {
		return msgModel;
	}

}

package com.thang.webshop.common;

public interface ConstantsRegex {
	public static final String ID_ALPHABET = "0123456789abcdefghjkmnpqrstuvwxyz";
	public static final String ID_NUMBER = "0123456789";

	public static final String DATE_HOUR_MINUTE = "yyyy-MM-dd'T'HH:mm";

	public static final String DATE_FORMAT = "yyyy-MM-dd";

	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";

	public static final String SINGLE_NUMBER = "[0-9]+";

	public static final String ID = "[" + ID_NUMBER + "]+";

	public static final String SINGLE_TEXT = "[a-zA-Z0-9-]+";
	public static final String PATH = "[0-9-]+";

	public static final String PARTNUMBER_CODE_TEXT = "[a-zA-Z0-9-_/#)(,.]+";

	public static final String SINGLE_TEXT_WITH_SPACE = "[a-zA-Z0-9-!@#$%^&*()+'_:></.\\[\\], ]+";
}

package task.validator;

public enum ValidatorEnum {
	
	POSTFIELDS("[A-Z][A-Za-z-\\x20-\\x7E]{3,250}+$", "err_code", "A capital letter and minimum 4 characters required"),
	COMMENTAUTHORFIELD("[aA-Za-z-\\x20-\\x7E]{3,20}", "err_code", "Minimum 3 characters required"),
	COMMENTCONTENTFIELD("[aA-Za-z-\\x20-\\x7E]{2,250}+$", "err_code", "Minimum 3 characters required");
	
	private String pattern;
	private String errCode;
	private String errMessage;
	
	private ValidatorEnum(String pattern, String errCode, String errMessage) {
		this.pattern = pattern;
		this.errCode = errCode;
		this.errMessage = errMessage;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

}

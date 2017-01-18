package com.leelab.blogproject;

public class CommonResponseVO {
	
	public static final int RESPONSE_CODE_SUCCESS = 1;
	public static final int RESPONSE_CODE_FAIL = 0;
	
	private int code;
	private Object responseVO;
	
	public CommonResponseVO(){}
	
	public CommonResponseVO(int code) {
		super();
		this.code = code;
	}
	
	public CommonResponseVO(int code, Object responseVO) {
		super();
		this.code = code;
		this.responseVO = responseVO;
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Object getResponseVO() {
		return responseVO;
	}
	public void setResponseVO(Object responseVO) {
		this.responseVO = responseVO;
	}

	@Override
	public String toString() {
		return "CommonResponseVO [code=" + code + ", responseVO=" + responseVO + "]";
	}

}

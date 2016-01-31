package com.hequalab.rai.api.params;


public class QueryParam {

	private String[] params;
	
	public QueryParam(String input) {
		int paramsCount = input.split(",").length;
		params = new String[paramsCount];
		
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

	
}
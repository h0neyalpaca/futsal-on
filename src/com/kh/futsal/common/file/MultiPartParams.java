package com.kh.futsal.common.file;


import java.util.List;
import java.util.Map;

public class MultiPartParams {
	
	private Map<String,List> params;

	public MultiPartParams(Map<String, List> params) {
		this.params = params;
	}

	public String getParameter(String name) {
		if(name.equals("com.kh.futsal.files")) {
			throw new RuntimeException("com.kh.futsal.files는 사용할 수 없는 파라미터명 입니다");
		}
		return (String) params.get(name).get(0);
	}
	
	
	public String[] getParameterValues(String name) {
		
		if(name.equals("com.kh.futsal.files")) {
			throw new RuntimeException("com.kh.futsal.files는 사용할 수 없는 파라미터명 입니다");
		}
		List<String> res = params.get(name);
		return res.toArray(new String[res.size()]);
	}


	public List<FileDTO> getFilesInfo() {
		return params.get("com.kh.futsal.files");
	}

	
	
}

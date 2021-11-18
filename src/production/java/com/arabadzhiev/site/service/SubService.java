package com.arabadzhiev.site.service;

import java.util.List;

import com.arabadzhiev.site.entity.Sub;

public interface SubService {
	
	public Sub getSub(String subUrl);
	public List<Sub> getSubs();

}

package com.arabadzhiev.site.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arabadzhiev.site.entity.Sub;

public interface SubService {
	
	Sub getSub(String subUrl);
	List<Sub> getSubs();
	Page<Sub> getSubs(Pageable pageable);

}

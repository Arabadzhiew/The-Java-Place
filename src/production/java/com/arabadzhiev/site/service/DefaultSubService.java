package com.arabadzhiev.site.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arabadzhiev.site.entity.Sub;
import com.arabadzhiev.site.repository.SubRepository;

@Service
@Transactional
public class DefaultSubService implements SubService {
	
	@Autowired private SubRepository subRepository;
	
	@Override
	public Sub getSub(String subUrl) {
		
		return subRepository.findByUrl(subUrl);
	}
	
	@Override
	public List<Sub> getSubs() {
		List<Sub> subs = new ArrayList<>();
		for(Sub sub : subRepository.findAll()) {
			subs.add(sub);
		}
		return subs;
	}

	@Override
	public Page<Sub> getSubs(Pageable pageable) {
		return subRepository.findAll(pageable);
	}

}

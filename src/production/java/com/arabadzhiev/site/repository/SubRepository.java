package com.arabadzhiev.site.repository;

import org.springframework.data.repository.CrudRepository;

import com.arabadzhiev.site.entity.Sub;

public interface SubRepository extends CrudRepository<Sub, Long>{
	
	public Sub findByUrl(String subUrl);
}

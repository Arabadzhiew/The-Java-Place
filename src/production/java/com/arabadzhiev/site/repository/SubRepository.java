package com.arabadzhiev.site.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.arabadzhiev.site.entity.Sub;

public interface SubRepository extends CrudRepository<Sub, Long>{
	
	Sub findByUrl(String subUrl);
	Page<Sub> findAll(Pageable pageable);
}

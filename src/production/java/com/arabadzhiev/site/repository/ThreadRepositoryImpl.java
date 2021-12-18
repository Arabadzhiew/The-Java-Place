package com.arabadzhiev.site.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.arabadzhiev.site.entity.Thread;

public class ThreadRepositoryImpl implements SearchableRepository<Thread>{
	
	@PersistenceContext EntityManager entityManager;

	@Override
	public Page<Thread> search(String query, Pageable pageable) {
		String matchThread = "MATCH(t.Title, t.Body) AGAINST(?1)";
		String matchComment = "MATCH(c.Body) AGAINST(?1)";
		
		long total = ((Number)this.entityManager.createNativeQuery(
				"SELECT COUNT(DISTINCT t.Id) FROM Thread t "
				+ "LEFT OUTER JOIN Thread_Comment c ON c.Thread_Id = t.Id "
				+ "WHERE " + matchThread + " OR " + matchComment)
				.setParameter(1, query).getSingleResult()).longValue();
		
		@SuppressWarnings("unchecked")
		List<Object []> results = this.entityManager.createNativeQuery(
				"SELECT DISTINCT t.*, (" + matchThread + " + " +  matchComment + ") "
				+ "AS _ft_scoreColumn FROM Thread t "
				+ "LEFT OUTER JOIN Thread_Comment c ON t.Id = c. Thread_Id "
				+ "WHERE (" + matchThread + " OR " + matchComment + ") GROUP BY t.Id" 
				+ " ORDER BY _ft_scoreColumn ASC, t.Id DESC", "searchResultMapping.thread").setParameter(1, query)
					.setFirstResult((int)pageable.getOffset())
					.setMaxResults(pageable.getPageSize())
					.getResultList();
		
		List<Thread> list = new ArrayList<>();
		results.forEach( o -> list.add((Thread)o[0]));
		
		return new PageImpl<>(list, pageable, total);
	}

}

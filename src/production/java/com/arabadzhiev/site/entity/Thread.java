package com.arabadzhiev.site.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Thread implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String title;
	private String body;
	private List<ThreadComment> comments = new ArrayList<>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "Thread_Id")
	public List<ThreadComment> getComments() {
		return comments;
	}
	public void setComments(List<ThreadComment> comments) {
		this.comments = comments;
	}
	
	public void addComment(ThreadComment comment) {
		this.comments.add(comment);
	}
	
	
}

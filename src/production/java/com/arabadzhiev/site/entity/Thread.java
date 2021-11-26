package com.arabadzhiev.site.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Thread implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String subUrl;
	private String title;
	private String body;
	private int commentCount;
	private LocalDateTime dateCreated;
	private User user;
	private List<ThreadComment> comments = new ArrayList<>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Column(name = "Sub_Url")
	public String getSubUrl() {
		return subUrl;
	}
	public void setSubUrl(String subUrl) {
		this.subUrl = subUrl;
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
	@Column(name = "Comment_Count", insertable = false)
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	@Column(name = "Date_Created", insertable = false, updatable = false)
	public LocalDateTime getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "User_Id")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@OneToMany(mappedBy = "thread", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Sub implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String name;
	private String url;
	private long totalThreads;
	private long totalComments;
	private LocalDateTime dateCreated;
	private List<Thread> threads = new ArrayList<>();
	private Thread lastActiveThread;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Column(unique = true)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(unique = true)
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name = "Total_Threads")
	public long getTotalThreads() {
		return totalThreads;
	}
	public void setTotalThreads(long totalThreads) {
		this.totalThreads = totalThreads;
	}
	@Column(name = "Total_Comments")
	public long getTotalComments() {
		return totalComments;
	}
	public void setTotalComments(long totalComments) {
		this.totalComments = totalComments;
	}
	@Column(name = "Date_Created", insertable = false, updatable = false)
	public LocalDateTime getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}
	@OneToMany(mappedBy = "sub", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
	public List<Thread> getThreads() {
		return threads;
	}
	public void setThreads(List<Thread> threads) {
		this.threads = threads;
	}
	public void removeThread(Thread thread) {
		this.threads.remove(thread);
	}
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Thread_Id")
	public Thread getLastActiveThread() {
		return lastActiveThread;
	}
	public void setLastActiveThread(Thread lastActiveThread) {
		this.lastActiveThread = lastActiveThread;
	}

}

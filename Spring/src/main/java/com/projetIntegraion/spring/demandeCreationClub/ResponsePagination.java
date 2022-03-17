package com.projetIntegraion.spring.demandeCreationClub;

import java.util.List;

public class ResponsePagination {
	private List<DemandeCreationClub> data;
	private String next_page_url,prev_page_url;
	public String getNext_page_url() {
		return next_page_url;
	}
	public void setNext_page_url(String next_page_url) {
		this.next_page_url = next_page_url;
	}
	public String getPrev_page_url() {
		return prev_page_url;
	}
	public void setPrev_page_url(String prev_page_url) {
		this.prev_page_url = prev_page_url;
	}
	private int current_page,last_page;
	public ResponsePagination(List<DemandeCreationClub> data, String next_page_url, String prev_page_url,
			int current_page, int last_page) {
		super();
		this.data = data;
		this.next_page_url = next_page_url;
		this.prev_page_url = prev_page_url;
		this.current_page = current_page;
		this.last_page = last_page;
	}
	public List<DemandeCreationClub> getData() {
		return data;
	}
	public void setData(List<DemandeCreationClub> data) {
		this.data = data;
	}
	public int getCurrent_page() {
		return current_page;
	}
	public void setCurrent_page(int current_page) {
		this.current_page = current_page;
	}
	public int getLast_page() {
		return last_page;
	}
	public void setLast_page(int last_page) {
		this.last_page = last_page;
	}
	public ResponsePagination() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}

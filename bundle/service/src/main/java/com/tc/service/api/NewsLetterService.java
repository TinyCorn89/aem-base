package com.tc.service.api;


public interface NewsLetterService {
	public void subscribe(String emailId, String listName);
	public void unSubscribe(String emailId, String listName);
	public void getSubscriptions(String emailId);
	public void broadcast(String subject, String htmlContent, String mailListName);
}

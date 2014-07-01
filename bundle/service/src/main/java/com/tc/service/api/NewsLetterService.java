package com.tc.service.api;

import java.util.List;


public interface NewsLetterService {
	public void subscribe(String emailId, String listName);
	public void unSubscribe(String emailId, String listName);
	public void getSubscriptions(String emailId, List<String> mailList);
	public void broadcast(String subject, String htmlContent, String mailListName);
}

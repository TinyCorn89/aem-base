package com.tc.service.impl;

import java.io.IOException;

import javax.jcr.RepositoryException;

import org.xml.sax.Attributes;

import com.day.cq.retriever.RetrieverStorage;

public class ThindataEmailRetrieverStorage implements RetrieverStorage {
	private String content;
	@Override
	public String maybeDownloadResource(String arg0, String arg1, String arg2,
			Attributes arg3) throws IOException, RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeContent(String arg0) throws IOException,
			RepositoryException {
		this.content = arg0;
		
	}
	
	public String getContent() {
		return this.content;
	}

}

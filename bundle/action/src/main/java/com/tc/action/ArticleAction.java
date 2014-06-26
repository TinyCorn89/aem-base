/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.action;

import javax.jcr.Node;
import org.apache.sling.api.resource.Resource;
import com.day.cq.wcm.foundation.Image;
import com.tc.action.BaseAction;
import com.tc.model.ArticleBean;
import com.tc.util.Constants;



public class ArticleAction extends BaseAction {

	/**
	 * The method is used to retrieve the article details.
	 * @return
	 */
	public ArticleBean getArticleInfo() {
		ArticleBean articleBean = new ArticleBean();

		try {
		Resource resource = getCurrentResource();
		Node node = getCurrentNode();

		Image image = new Image(resource, "image");

		if (image.hasContent()) {
			articleBean.setImageUrl(image.getHref());
		}

		if (node.hasProperty(Constants.ARTICLE.TITLE)) {
			articleBean.setTitle(node.getProperty(Constants.ARTICLE.TITLE).getString());
		}

		if (node.hasProperty(Constants.ARTICLE.DESCRIPTION)) {
			articleBean.setDescription(node.getProperty(Constants.ARTICLE.DESCRIPTION)
					.getString());
		}
		} catch(Exception e) {
			
		}
		
		return articleBean;

	}


}

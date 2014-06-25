package com.domain.cq.company.common.tags;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.jsp.JspException;

import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import com.day.cq.wcm.api.Page;


@SuppressWarnings("serial")
public class GetPagesByTagTag extends CQBaseTag {
	
	protected static Logger log = LoggerFactory.getLogger(GetPagesByTagTag.class);
	protected String tag;
	
	@Override
	public int doStartTag() throws JspException {
		ArrayList<Page> pages = new ArrayList<Page>();
		TagManager tm = resourceResolver.adaptTo(TagManager.class);
		
		Tag finderTag = tm.resolve(tag);

		if (finderTag != null) {
			Iterator<Resource> resourceIter = finderTag.find();
			while (resourceIter.hasNext()) {
				Resource r = resourceIter.next();
				if (r != null) {
					Page p = r.getParent().adaptTo(Page.class);
					if (p != null) {
						pages.add(p);
					}
				}
			}
		
		pageContext.setAttribute("taggedPages",pages);
		}
        return SKIP_BODY;
	}
	
	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
	
	public void setTag(String tag) {
		this.tag = tag;
	}
}

package com.domain.cq.company.common.tags;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.tagging.Tag;


public class GetTagsForPageTag extends CQBaseTag {
        private static final long serialVersionUID = 1L;
        protected static Logger log = LoggerFactory.getLogger(GetTagsForPageTag.class);
        protected String pagepath;
        protected String tagType;
        
        @Override
        public int doStartTag() throws JspException {
                ArrayList<Tag> tags = new ArrayList<Tag>();
                
                Tag[] pageTags = null;
                if (pagepath != null) {
                        pageTags = pageManager.getPage(pagepath).getTags();
                }
                else {
                        pageTags = currentPage.getTags();
                }
                
                Pattern p;
                Matcher m;
                for (Tag tag : pageTags) {
                        if (tagType == null) {
                                tags.add(tag);
                        }
                        else {
                                String tagID = tag.getTagID();
                                p = Pattern.compile("(AEM-56:" + tagType + "/[^/]+)");
                                m = p.matcher(tagID);
                                if (m.find()) {
                                        tags.add(tag);
                                }
                        }
                }
                if (tagType == null) {
                        pageContext.setAttribute("tagList",tags);
                }
                else {
                        pageContext.setAttribute(tagType + "List",tags);
                }
                
        return SKIP_BODY;
        }
        
        @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
        
        public void setPagepath(String pagepath) {
                this.pagepath = pagepath;
        }
        public void setTagType(String tagType) {
                this.tagType = tagType;
        }
}
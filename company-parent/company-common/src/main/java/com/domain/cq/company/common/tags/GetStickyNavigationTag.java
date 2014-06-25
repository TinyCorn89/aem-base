package com.domain.cq.company.common.tags;

import java.util.ArrayList;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Value;
import javax.servlet.jsp.JspException;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetStickyNavigationTag extends CQBaseTag {

    protected static Logger log = LoggerFactory.getLogger(GetStickyNavigationTag.class);

    @Override
    public int doStartTag() throws JspException {

        pageContext.setAttribute("sections", this.getMenuItems("sections", currentNode));

        return EVAL_BODY_INCLUDE;
    }

    protected ArrayList getMenuItemConfig(String jsonString) {

        ArrayList item = new ArrayList<String>();

        try {
            JSONObject menuItemConfig = new JSONObject(jsonString);
            item.add(menuItemConfig.getString("resType"));
            item.add(menuItemConfig.getString("link"));
            item.add(menuItemConfig.getString("name"));
        } catch (JSONException e) {
            log.error("JSON error", e);
        }

        return item;
    }

    protected ArrayList getMenuItems(String propertyName, Node node) {
        ArrayList items = new ArrayList<ArrayList>();
        try {
            if (node.hasProperty(propertyName) == true) {
                if (node.getProperty(propertyName).getDefinition().isMultiple() == true) {
                    Value[] menuItems = node.getProperty(propertyName).getValues();

                    for (Value menuItem : menuItems) {
                        items.add(this.getMenuItemConfig(menuItem.getString()));
                    }
                } else {
                    String menuItem = node.getProperty(propertyName).getString();
                    items.add(this.getMenuItemConfig(menuItem));
                }
            }
        } catch (RepositoryException e) {
            log.error("Retrieving multi-list error", e);
        } catch (IllegalStateException e) {
            log.error("Retrieving multi-list error", e);
        }
        return items;
    }
}

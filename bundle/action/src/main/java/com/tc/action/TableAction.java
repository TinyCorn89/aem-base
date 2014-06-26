/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.foundation.Placeholder;
import com.tc.action.BaseAction;
import com.tc.model.TableBean;

/**
 * The Class TableAction.
 *
 * @author gdinakar
 */
public class TableAction extends BaseAction {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(TableAction.class);
	
	/**
	 * Gets the table details.
	 *
	 * @return the table details
	 */
	public TableBean getTableDetails() {
		LOG.info("Entered getTableDetails method");
		
		TableBean tableBean = new TableBean();
		
		String classicPlaceholder = "<img src=\"/libs/cq/ui/resources/0.gif\" class=\"cq-table-placeholder\" alt=\"\">";
		
		tableBean.setPlaceHolder(Placeholder.getDefaultPlaceholder(getSlingRequest(), getComponent(), classicPlaceholder));
		tableBean.setComponentPath(getCurrentResource().getResourceType());
		
		return tableBean;
	}
}

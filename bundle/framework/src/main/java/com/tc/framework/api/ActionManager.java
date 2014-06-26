/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.framework.api;

import javax.servlet.jsp.PageContext;

public interface ActionManager {

    public Object invokeAction(Object sourceTag, String actionClassName, String actionName, PageContext pageContext);

}

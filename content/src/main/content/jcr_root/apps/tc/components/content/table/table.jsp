<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>

<%@page session="false"%>
<%@include file="/apps/tc/global/global.jsp"%>
<action:action actionClassName="com.tc.action.TableAction" bean="tableBean" actionName="getTableDetails" />

<cq:text property="tableData"
               escapeXml="false"
               placeholder="${tableBean.placeHolder}"
    />

<%@include file="/apps/tc/components/content/table/tracking-js.jsp"%>
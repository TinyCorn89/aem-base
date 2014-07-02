<%--
  ~ Copyright 1997-2010 Day Management AG
  ~ Barfuesserplatz 6, 4001 Basel, Switzerland
  ~ All Rights Reserved.
  ~
  ~ This software is the confidential and proprietary information of
  ~ Day Management AG, ("Confidential Information"). You shall not
  ~ disclose such Confidential Information and shall use it only in
  ~ accordance with the terms of the license agreement you entered into
  ~ with Day.
  --%>
<%@page session="false" import="com.day.cq.i18n.I18n,
                com.day.cq.wcm.api.WCMMode,
                com.day.cq.wcm.api.components.Toolbar" %><%!
%>

<script type="text/javascript">
    var curPageUrl = "<%=request.getRequestURI()%>";
</script>


<%@include file="/libs/foundation/global.jsp" %>

<cq:includeClientLib categories="tc.components.thindata"/>

<%
    // draw the 'edit' bar explicitly
    if (editContext != null) {
%><table cellpadding="0" cellspacing="0" width="560" border="0"
         style="margin: 0 auto;"><tbody><tr>
    <td><div><%
        editContext.getComponentContext().setDecorate(false);
        editContext.getEditConfig().setOrderable(false);
        Toolbar tb = editContext.getEditConfig().getToolbar();
        tb.remove(0);
        tb.add(new Toolbar.Label(I18n.get(slingRequest, "Thindata Newsletter")));
        tb.add(new Toolbar.Separator());

        tb.add(new Toolbar.Button(I18n.get(slingRequest, "Send..."),
                "function() { CQ.mcm.utils.Newsletter.openDialog(this, \"/apps/tc/components/newsletterpage/thindata/dialog.infinity.json\"); }",
                false,
                I18n.get(slingRequest, "Send the newsletter")));




        //required to have the editbar into the table.
        out.flush();
        editContext.includeEpilog(slingRequest, slingResponse, WCMMode.EDIT);
%></div></td></tr></tbody></table><%
    }
%>

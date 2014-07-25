<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
    
<%@include file="/libs/foundation/global.jsp"%>
<%%>
<cq:includeClientLib categories="cq.widgets"/>
<script type="text/javascript">
    CQ.Ext.onReady(function() {
        var appsConfigform = new CQ.Ext.form.FormPanel( {
            items : [ {
                xtype : 'adsconfigmanager'

            } ],
           listeners : {
                render : function() {
                    canModifyAppsconfig = true;
                 
                }
            }
        });

        appsConfigform.render(CQ.Ext.getBody());

    });

</script>
<body bgcolor="#D3E1F1">
</body>



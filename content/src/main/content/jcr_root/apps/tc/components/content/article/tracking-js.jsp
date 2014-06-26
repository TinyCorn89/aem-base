<%--
  Copyright 1997-2008 Day Management AG
  Barfuesserplatz 6, 4001 Basel, Switzerland
  All Rights Reserved.

  This software is the confidential and proprietary information of
  Day Management AG, ("Confidential Information"). You shall not
  disclose such Confidential Information and shall use it only in
  accordance with the terms of the license agreement you entered into
  with Day.

  ==============================================================================

  JS tracking code for linked images



--%>


<script type="text/javascript">
    function tracknav(target) {
            if (CQ_Analytics.Sitecatalyst) {
                CQ_Analytics.record({
                    event: 'articleClick',
                    values: {
                        articleLink: "${properties['link']}",
                        articleAsset: "${articleBean.imageUrl}", 
                        articleTitle: "${articleBean.title}"  

                    },
                    componentPath: '<%=resource.getResourceType()%>'
                });
            }
    }
</script>  
<script type="text/javascript">
    s.usePlugins=true;
function s_doPlugins(s) {
    //add your custom code here
}
s.doPlugins=s_doPlugins;
</script> 
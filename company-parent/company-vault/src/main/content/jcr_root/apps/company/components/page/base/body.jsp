<%@ include file="/apps/company/global.jsp"%>
<body>
	
	<div class="site-wrapper">
		<cq:include script="header.jsp" />
		<cq:include script="content.jsp" />
		<cq:include script="footer.jsp" />
	</div>
	
	<cq:include path="clientcontext" resourceType="cq/personalization/components/clientcontext" />
	
	
	<cq:include script="clientlibBody.jsp" />

	<cq:include path="cloudservices" resourceType="cq/cloudserviceconfigs/components/servicecomponents"/>
</body>
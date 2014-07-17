<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:sling="http://sling.apache.org/jcr/sling/1.0" xmlns:cq="http://www.day.com/jcr/cq/1.0" xmlns:jcr="http://www.jcp.org/jcr/1.0" xmlns:nt="http://www.jcp.org/jcr/nt/1.0">
	<xsl:output method="text" omit-xml-declaration="yes" indent="no"/>
	<xsl:template match="/">
		<xsl:copy>
			<xsl:apply-templates select="NewsML/NewsItem" />
			<xsl:apply-templates select="article" />
		</xsl:copy>
	</xsl:template>

	<xsl:template match="NewsItem">
		
		<!-- create headline component node -->
		<xsl:apply-templates select="./NewsComponent/NewsLines" />
			
		<!-- create image component-->
		<xsl:apply-templates
			select="./NewsComponent/ContentItem/DataContent/CPOnlineFile/CPLink" />
			
		<!-- create text components for all paras-->	
			<xsl:apply-templates
				select="./NewsComponent/ContentItem/DataContent/CPOnlineFile/CPStory" />
				
	</xsl:template>
		
	

	<!-- image component mapping for CPLink -->
	<xsl:template
		match="NewsComponent/ContentItem/DataContent/CPOnlineFile/CPLink">
		<xsl:value-of select="@Caption" />
	</xsl:template>

	<!-- text component template for mapping CPStory -->
	<xsl:template match="NewsComponent/ContentItem/DataContent/CPOnlineFile/CPStory">
		<xsl:for-each select="CPStoryPara">
			<xsl:value-of select="." />
		</xsl:for-each>
	</xsl:template>

	<!-- headline component template for mapping NewsLines -->
	<xsl:template match="NewsComponent/NewsLines">
		<xsl:value-of select="./ByLine" />
		<xsl:value-of select="./CopyrightLine" />
		<xsl:value-of select="./DateLine" />
		<xsl:value-of select="./HeadLine" />
		<xsl:value-of select="./HeadLineShort" />
	</xsl:template>
	
<!--  this template generates plain text for article xml file -->	
	<xsl:template match="article">
		<xsl:output method="text"/>
		<xsl:value-of select="title" />
		<xsl:value-of select="fulltext" />
		<xsl:value-of select="chapters/item/text" />
	</xsl:template>	
	

</xsl:stylesheet>
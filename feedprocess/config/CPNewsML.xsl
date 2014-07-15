<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:sling="http://sling.apache.org/jcr/sling/1.0" xmlns:cq="http://www.day.com/jcr/cq/1.0" xmlns:jcr="http://www.jcp.org/jcr/1.0" xmlns:nt="http://www.jcp.org/jcr/nt/1.0">

	<xsl:output indent="yes" />
	<xsl:param name="tags" />

	<xsl:template match="/">
		<xsl:copy>
			<xsl:apply-templates select="NewsML/NewsItem" />
		</xsl:copy>
	</xsl:template>

	<xsl:template match="NewsItem">
		<xsl:output method="xml" indent="yes" />
		
		<xsl:element name="jcr:root">
			<xsl:copy-of
					select="document('')/xsl:stylesheet/namespace::*[name()!='xsl']" />
			<xsl:attribute name="jcr:primaryType">cq:Page</xsl:attribute>

			<xsl:element name="jcr:content">
				<xsl:attribute name="cq:template">/apps/tc/templates/article</xsl:attribute>
				<xsl:attribute name="jcr:primaryType">cq:PageContent</xsl:attribute>
				<xsl:attribute name="jcr:title"><xsl:value-of select="./NewsComponent/NewsLines/HeadLineShort" /></xsl:attribute>
				<xsl:attribute name="sling:resourceType">/apps/tc/components/page/article</xsl:attribute>
				<xsl:attribute name="newsId"><xsl:value-of select="./Identification/NewsIdentifier/NewsItemId" /></xsl:attribute>
				<xsl:attribute name="createdDate"><xsl:value-of select="./NewsManagement/FirstCreated" /></xsl:attribute>
				<xsl:if test="$tags">
    				<xsl:attribute name="cq:tags"><xsl:value-of select="$tags" /></xsl:attribute>
				</xsl:if>
				

				<!--  add content region -->
				<xsl:element name="content-region">
					<xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
					<xsl:attribute name="sling:resourceType">foundation/components/parsys</xsl:attribute>
					
					<!--  add article component -->
					<xsl:element name="article">
						<xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
						<xsl:attribute name="sling:resourceType">tc/components/content/article</xsl:attribute>
						<xsl:attribute name="description"><xsl:value-of select="./NewsComponent/NewsLines/HeadLine" /></xsl:attribute>
						<xsl:attribute name="textIsRich">{Boolean}true</xsl:attribute>
						<xsl:attribute name="title"><xsl:value-of select="./NewsComponent/NewsLines/HeadLineShort" /></xsl:attribute>
						
						<!-- create headline component node -->
						<xsl:apply-templates select="./NewsComponent/NewsLines" />
						
						<!-- create image component-->
						<xsl:apply-templates
							select="./NewsComponent/ContentItem/DataContent/CPOnlineFile/CPLink" />
					</xsl:element>
					
					<!-- create text components for all paras-->	
						<xsl:apply-templates
							select="./NewsComponent/ContentItem/DataContent/CPOnlineFile/CPStory" />
					
				</xsl:element>
			</xsl:element>
		</xsl:element>
		
		
		
	</xsl:template>

	<!-- image component mapping for CPLink -->
	<xsl:template
		match="NewsComponent/ContentItem/DataContent/CPOnlineFile/CPLink">
		<xsl:element name="cplink">
			<xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
			<xsl:attribute name="caption"><xsl:value-of select="@Caption" /></xsl:attribute>
			<xsl:attribute name="height"><xsl:value-of select="@SourceHeight" /></xsl:attribute>
			<xsl:attribute name="width"><xsl:value-of select="@SourceWidth" /></xsl:attribute>
			<xsl:attribute name="image"><xsl:value-of select="@SourceFilePath" /></xsl:attribute>
		</xsl:element>
	</xsl:template>

	<!-- text component template for mapping CPStory -->
	<xsl:template match="NewsComponent/ContentItem/DataContent/CPOnlineFile/CPStory">
		<xsl:for-each select="CPStoryPara">
			<xsl:element name="text{position()}">
				<xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
				<xsl:attribute name="sling:resourceType">tc/components/content/text</xsl:attribute>
				<xsl:attribute name="text"><xsl:value-of select="." /></xsl:attribute>
			</xsl:element>
		</xsl:for-each>
	</xsl:template>

	<!-- headline component template for mapping NewsLines -->
	<xsl:template match="NewsComponent/NewsLines">
		<xsl:element name="headlineTitle">
			<xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
			<xsl:attribute name="sling:resourceType">/apps/tc/components/headlineTitle</xsl:attribute>
			<xsl:attribute name="byLine"><xsl:value-of select="./ByLine" /></xsl:attribute>
			<xsl:attribute name="copyrightLine"><xsl:value-of select="./CopyrightLine" /></xsl:attribute>
			<xsl:attribute name="dateLine"><xsl:value-of select="./DateLine" /></xsl:attribute>
			<xsl:attribute name="headline"><xsl:value-of select="./HeadLine" /></xsl:attribute>
			<xsl:attribute name="headlineShort"><xsl:value-of select="./HeadLineShort" /></xsl:attribute>
		</xsl:element>
	</xsl:template>

</xsl:stylesheet>
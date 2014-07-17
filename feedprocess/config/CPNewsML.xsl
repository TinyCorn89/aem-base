<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:sling="http://sling.apache.org/jcr/sling/1.0" xmlns:cq="http://www.day.com/jcr/cq/1.0" xmlns:jcr="http://www.jcp.org/jcr/1.0" xmlns:nt="http://www.jcp.org/jcr/nt/1.0">

	<xsl:output indent="yes" />
	<xsl:param name="tags" />
	<xsl:param name="damFolder"/>

	<xsl:template match="/">
		<xsl:copy>
			<xsl:apply-templates select="NewsML/NewsItem" />
		</xsl:copy>
	</xsl:template>
		<xsl:variable name="year"><xsl:value-of select="substring(NewsML/NewsItem/NewsManagement/FirstCreated,1,4)" /></xsl:variable>
		<xsl:variable name="month"><xsl:value-of select="substring(NewsML/NewsItem/NewsManagement/FirstCreated,6,2)" /></xsl:variable>
		<xsl:variable name="day"><xsl:value-of select="substring(NewsML/NewsItem/NewsManagement/FirstCreated,9,2)" /></xsl:variable>
		<xsl:variable name="newsId"><xsl:value-of select="NewsML/NewsItem/Identification/NewsIdentifier/NewsItemId" /></xsl:variable>
		<xsl:variable name="fullDamPath"><xsl:value-of select="$damFolder"/>/<xsl:value-of select="$year"/>/<xsl:value-of select="$month"/>/<xsl:value-of select="$day"/>/<xsl:value-of select="$newsId"/></xsl:variable>

	<xsl:template match="NewsItem">
		<xsl:output method="xml" indent="yes" />
		<xsl:element name="jcr:root">
			<xsl:copy-of
					select="document('')/xsl:stylesheet/namespace::*[name()!='xsl']" />
			<xsl:attribute name="jcr:primaryType">cq:Page</xsl:attribute>

			<xsl:element name="jcr:content">
				<xsl:attribute name="cq:template">/apps/tc/templates/news</xsl:attribute>
				<xsl:attribute name="jcr:primaryType">cq:PageContent</xsl:attribute>
				<xsl:attribute name="jcr:title"><xsl:value-of select="./NewsComponent/NewsLines/HeadLineShort" /></xsl:attribute>
				<xsl:attribute name="sling:resourceType">/apps/tc/components/page/news</xsl:attribute>
				<xsl:attribute name="newsId"><xsl:value-of select="./Identification/NewsIdentifier/NewsItemId" /></xsl:attribute>
				<xsl:attribute name="createdDate"><xsl:value-of select="./NewsManagement/FirstCreated" /></xsl:attribute>
				<xsl:if test="$tags">
    				<xsl:attribute name="cq:tags"><xsl:value-of select="$tags" /></xsl:attribute>
				</xsl:if>
				
				<!--  add article component -->
				<xsl:element name="article">
					<xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
					<xsl:attribute name="sling:resourceType">/apps/tc/components/content/cp/news-article</xsl:attribute>
					<xsl:attribute name="description"><xsl:value-of select="./NewsComponent/NewsLines/HeadLine" /></xsl:attribute>
					<xsl:attribute name="articleId"><xsl:value-of select="./Identification/NewsIdentifier/NewsItemId" /></xsl:attribute>
					<xsl:attribute name="title"><xsl:value-of select="./NewsComponent/NewsLines/HeadLineShort" /></xsl:attribute>
					<xsl:attribute name="publicationDate"><xsl:value-of select="./NewsManagement/FirstCreated" /></xsl:attribute>
					<xsl:attribute name="expirationDate"></xsl:attribute>
				    <xsl:attribute name="SlugLine"><xsl:value-of select="./NewsComponent/NewsLines/HeadLine" /></xsl:attribute>
					<xsl:attribute name="tagSections"></xsl:attribute>
					<xsl:attribute name="tagShare"></xsl:attribute>
				
				</xsl:element>
				<xsl:element name="content-multi">
					<xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
					<xsl:attribute name="sling:resourceType">foundation/components/parsys</xsl:attribute>
					<!--  there can be multiple ContentItem -->					
						<xsl:apply-templates
							select="./NewsComponent" />
				</xsl:element>
			</xsl:element>
		</xsl:element>
		
		
	</xsl:template>
	
	<xsl:template match="NewsItem/NewsComponent">
		<xsl:for-each select="./ContentItem/DataContent/CPOnlineFile/CPLink">
			<xsl:apply-templates select="." >
			<!-- For multiple CPLinks -->
				<!-- <xsl:with-param name="imageId" select="position()"/> -->
				<xsl:with-param name="imageId" select="1"/>
			</xsl:apply-templates>
		</xsl:for-each>
		
		<xsl:for-each select="./ContentItem/DataContent/CPOnlineFile/CPStory">
			<xsl:apply-templates select="." >
				<xsl:with-param name="storyId" select="position()"/>
			</xsl:apply-templates>
		</xsl:for-each>
	</xsl:template>
	
	<!-- image component mapping for CPLink -->
	<xsl:template
		match="NewsComponent/ContentItem/DataContent/CPOnlineFile/CPLink">
		<xsl:param name="imageId"/>
		<xsl:if test="contains(@SourceFilePath, '.jpg')"> 
			<xsl:element name="image{$imageId}">
				<xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
				<xsl:attribute name="caption"><xsl:value-of select="@Caption" /></xsl:attribute>
				<xsl:attribute name="height"><xsl:value-of select="@SourceHeight" /></xsl:attribute>
				<xsl:attribute name="width"><xsl:value-of select="@SourceWidth" /></xsl:attribute>
				<xsl:variable name="ref"><xsl:value-of select="@SourceFilePath" /></xsl:variable>
				<xsl:if test="starts-with($ref, './')">
					<xsl:attribute name="fileReference"><xsl:copy-of select="$fullDamPath" />/<xsl:copy-of select="substring($ref, 3)" /></xsl:attribute>
				</xsl:if>
				
				<xsl:if test="not(starts-with($ref, './'))">
					<xsl:attribute name="fileReference"><xsl:copy-of select="$fullDamPath" />/<xsl:copy-of select="$ref"/></xsl:attribute>
				</xsl:if>
				
				<xsl:attribute name="sling:resourceType">tc/components/content/image</xsl:attribute>
			</xsl:element>
		</xsl:if>
	</xsl:template>

	<!-- text component template for mapping CPStory -->
	<xsl:template match="NewsComponent/ContentItem/DataContent/CPOnlineFile/CPStory">
		<xsl:param name="storyId"/>
		<xsl:for-each select="CPStoryPara">
			<xsl:element name="text{$storyId}{position()}">
				<xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
				<xsl:attribute name="sling:resourceType">tc/components/content/text</xsl:attribute>
				<xsl:attribute name="text"><xsl:value-of select="." /></xsl:attribute>
			</xsl:element>
		</xsl:for-each>
	</xsl:template>

</xsl:stylesheet>
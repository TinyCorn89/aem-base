<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:sling="http://sling.apache.org/jcr/sling/1.0" xmlns:cq="http://www.day.com/jcr/cq/1.0" xmlns:jcr="http://www.jcp.org/jcr/1.0" xmlns:nt="http://www.jcp.org/jcr/nt/1.0">

	<xsl:output indent="yes" />
	<xsl:param name="tags" />
	<xsl:param name="damFolder"/>

	<xsl:template match="/">
		<xsl:copy>
			<xsl:apply-templates select="article" />
		</xsl:copy>
	</xsl:template>
		<xsl:variable name="year"><xsl:value-of select="substring(publicationDate,1,4)" /></xsl:variable>
		<xsl:variable name="month"><xsl:value-of select="substring(publicationDate,6,2)" /></xsl:variable>
		<xsl:variable name="day"><xsl:value-of select="substring(publicationDate,9,2)" /></xsl:variable>
		<xsl:variable name="articleId"><xsl:value-of select="id" /></xsl:variable>
		<xsl:variable name="fullDamPath"><xsl:value-of select="$damFolder"/>/<xsl:value-of select="$year"/>/<xsl:value-of select="$month"/>/<xsl:value-of select="$day"/>/<xsl:value-of select="$articleId"/></xsl:variable>

	<xsl:template match="article">
		<xsl:output method="xml" indent="yes" />
		<xsl:element name="jcr:root">
			<xsl:copy-of
					select="document('')/xsl:stylesheet/namespace::*[name()!='xsl']" />
			<xsl:attribute name="jcr:primaryType">cq:Page</xsl:attribute>

			<xsl:element name="jcr:content">
				<xsl:attribute name="cq:template">/apps/tc/templates/article</xsl:attribute>
				<xsl:attribute name="jcr:primaryType">cq:PageContent</xsl:attribute>
				<xsl:attribute name="jcr:title"><xsl:value-of select="title" /></xsl:attribute>
				<xsl:attribute name="sling:resourceType">/apps/tc/components/page/article</xsl:attribute>
				<xsl:attribute name="articleId"><xsl:value-of select="id" /></xsl:attribute>
				<xsl:attribute name="createdDate"><xsl:value-of select="publicationDate" /></xsl:attribute>
				<xsl:if test="$tags">
    				<xsl:attribute name="cq:tags"><xsl:value-of select="$tags" /></xsl:attribute>
				</xsl:if>
				
				<!--  add article component -->
				<xsl:element name="article">
					<xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
					<xsl:attribute name="sling:resourceType">tc/components/content/article</xsl:attribute>
					<xsl:attribute name="description"><xsl:value-of select="fulltext" /></xsl:attribute>
					<xsl:attribute name="articleId"><xsl:value-of select="id" /></xsl:attribute>
					<xsl:attribute name="title"><xsl:value-of select="title" /></xsl:attribute>
					<xsl:attribute name="publicationDate"><xsl:value-of select="publicationDate" /></xsl:attribute>
					<xsl:attribute name="expirationDate"></xsl:attribute>
				
				</xsl:element>
				<xsl:element name="content-region">
					<xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
					<xsl:attribute name="sling:resourceType">foundation/components/parsys</xsl:attribute>
					<xsl:element name="text">
						<xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
						<xsl:attribute name="sling:resourceType">tc/components/content/text</xsl:attribute>
						<xsl:attribute name="text"><xsl:value-of select="chapters/item/text" /></xsl:attribute>
						<xsl:attribute name="textIsRich">true</xsl:attribute>
					</xsl:element>
				</xsl:element>
			</xsl:element>
		</xsl:element>
		
		
	</xsl:template>
	

</xsl:stylesheet>
<xsl:stylesheet version="1.0" xmlns:java="java" xmlns:article="com.tc.process.handler.ArticleImageProcessor" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:sling="http://sling.apache.org/jcr/sling/1.0" xmlns:cq="http://www.day.com/jcr/cq/1.0" xmlns:jcr="http://www.jcp.org/jcr/1.0" xmlns:nt="http://www.jcp.org/jcr/nt/1.0" exclude-result-prefixes="xmlns:jcr">
	<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" />
		<xsl:param name="damFolder"/>
		<xsl:param name="createdDate"/>
		<xsl:param name="articleId"/>
		<xsl:variable name="year"><xsl:value-of select="substring($createdDate,1,4)" /></xsl:variable>
		<xsl:variable name="month"><xsl:value-of select="substring($createdDate,6,2)" /></xsl:variable>
		<xsl:variable name="day"><xsl:value-of select="substring($createdDate,9,2)" /></xsl:variable>
		<xsl:variable name="fullDamPath"><xsl:value-of select="$damFolder"/>/<xsl:value-of select="$year"/>/<xsl:value-of select="$month"/>/<xsl:value-of select="$day"/>/<xsl:value-of select="$articleId"/></xsl:variable>

	<xsl:template match="/">
		<xsl:apply-templates select="photo" />
	</xsl:template>	
	<xsl:template match="photo">
			<xsl:element name="image">
				<xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
				<xsl:attribute name="jcr:title"><xsl:value-of select="fulltext" /></xsl:attribute>
				<xsl:variable name="ref">export_photo_<xsl:value-of select="id"/>.jpg</xsl:variable>
				<xsl:if test="starts-with($ref, './')">
					<xsl:attribute name="fileReference"><xsl:copy-of select="$fullDamPath" />/<xsl:copy-of select="substring($ref, 3)" /></xsl:attribute>
				</xsl:if>
				
				<xsl:if test="not(starts-with($ref, './'))">
					<xsl:attribute name="fileReference"><xsl:copy-of select="$fullDamPath" />/<xsl:copy-of select="$ref"/></xsl:attribute>
				</xsl:if>
				
				<xsl:attribute name="sling:resourceType">tc/components/content/image</xsl:attribute>
			</xsl:element>
	</xsl:template>
</xsl:stylesheet>
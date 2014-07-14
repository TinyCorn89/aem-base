<?xml version="1.0"?>

<xsl:stylesheet version="1.0"
	xmlns:social="http://www.adobe.com/social/1.0" xmlns:dex="http://ns.optimasc.com/dex/1.0/" xmlns:granite="http://www.adobe.com/jcr/granite/1.0" xmlns:xmpPLUS="http://ns.adobe.com/xap/1.0/PLUS/" xmlns:mix="http://www.jcp.org/jcr/mix/1.0" xmlns:nt="http://www.jcp.org/jcr/nt/1.0" xmlns:idPriv="http://ns.adobe.com/xmp/InDesign/private" xmlns:adobe_dam="http://www.adobe.com/adobe_dam/1.0" xmlns:xmpNote="http://ns.adobe.com/xmp/note/" xmlns:album="http://ns.adobe.com/album/1.0/" xmlns:scg="http://www.adobe.com/social/scg/1.0" xmlns:tiff="http://ns.adobe.com/tiff/1.0/" xmlns:crxde="http://www.day.com/jcr/crxde/1.0" xmlns:stMfs="http://ns.adobe.com/xap/1.0/sType/ManifestItem#" xmlns:xmpGImg="http://ns.adobe.com/xap/1.0/g/img/" xmlns:xmpTPg="http://ns.adobe.com/xap/1.0/t/pg/" xmlns:exif="http://ns.adobe.com/exif/1.0/" xmlns:Iptc4xmpCore="http://iptc.org/std/Iptc4xmpCore/1.0/xmlns/" xmlns:Iptc4xmpExt="http://iptc.org/std/Iptc4xmpExt/2008-02-29/" xmlns:MP="http://ns.microsoft.com/photo/1.2/" xmlns:mediapro="http://ns.iview-multimedia.com/mediapro/1.0/" xmlns:crs="http://ns.adobe.com/camera-raw-settings/1.0/" xmlns:stEvt="http://ns.adobe.com/xap/1.0/sType/ResourceEvent#" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xmpBJ="http://ns.adobe.com/xap/1.0/bj/" xmlns:oauth="http://oauth.net/" xmlns:prl="http://prismstandard.org/namespaces/prl/2.1/" xmlns:vlt="http://www.day.com/jcr/vault/1.0" xmlns:s7sitecatalyst="http://www.day.com/s7sitecatalyst/1.0/" xmlns:pdfx="http://ns.adobe.com/pdfx/1.3/" xmlns:dam="http://www.day.com/dam/1.0" xmlns:prism="http://prismstandard.org/namespaces/basic/2.1/" xmlns:viewerpreset="http://www.day.com/viewerpreset/1.0/" xmlns:xmpMM="http://ns.adobe.com/xap/1.0/mm/" xmlns:xmpDM="http://ns.adobe.com/xmp/1.0/DynamicMedia/" xmlns:s7userdata="http://www.day.com/s7userdata/1.0/" xmlns:pdf="http://ns.adobe.com/pdf/1.3/" xmlns:sling="http://sling.apache.org/jcr/sling/1.0" xmlns:exifEX="http://cipa.jp/exif/1.0/" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:psAux="http://ns.adobe.com/exif/1.0/aux/" xmlns:acdsee="http://ns.acdsee.com/iptc/1.0/" xmlns:MicrosoftPhoto="http://ns.microsoft.com/photo/1.0" xmlns:sv="http://www.jcp.org/jcr/sv/1.0" xmlns:oak="http://jackrabbit.apache.org/oak/ns/1.0" xmlns:rep="internal" xmlns:h="http://www.onehippo.org/jcr/xmlimport" xmlns:crx="http://www.day.com/crx/1.0" xmlns:DICOM="http://ns.adobe.com/DICOM/" xmlns:stRef="http://ns.adobe.com/xap/1.0/sType/ResourceRef#" xmlns:stFNT="http://ns.adobe.com/xap/1.0/sType/Font#" xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:xmp="http://ns.adobe.com/xap/1.0/" xmlns:xmpRights="http://ns.adobe.com/xap/1.0/rights/" xmlns:plus="http://ns.useplus.org/ldf/xmp/1.0/" xmlns:prismusagerights="http://prismstandard.org/namespaces/prismusagerights/2.1/" xmlns:cc="http://creativecommons.org/ns#" xmlns:slingevent="http://sling.apache.org/jcr/event/1.0" xmlns:jcr="http://www.jcp.org/jcr/1.0" xmlns:cq="http://www.day.com/jcr/cq/1.0" xmlns:photoshop="http://ns.adobe.com/photoshop/1.0/" xmlns:lr="http://ns.adobe.com/lightroom/1.0/" xmlns:xmpG="http://ns.adobe.com/xap/1.0/g/">

	<xsl:output indent="yes" />

	<xsl:template match="/">
		<xsl:copy>
			<xsl:apply-templates select="NewsML/NewsItem" />
		</xsl:copy>
	</xsl:template>

	<xsl:template match="NewsItem">
		<xsl:output method="xml" indent="yes" />
		<xsl:element name="sv:node">
			<xsl:copy-of
				select="document('')/xsl:stylesheet/namespace::*[name()!='xsl']" />
				<!-- 1,4 -->
			<xsl:attribute name="sv:name">y_<xsl:value-of select="substring(./NewsManagement/FirstCreated, 1, 4)" /></xsl:attribute>
			<xsl:attribute name="h:merge">combine</xsl:attribute>
			
			<sv:property sv:name="jcr:primaryType" sv:type="Name"><sv:value>nt:unstructured</sv:value></sv:property>
			
			<!-- 6,2 -->
			<xsl:element name="sv:node">
				<xsl:attribute name="sv:name">m_<xsl:value-of select="substring(./NewsManagement/FirstCreated, 6, 2)" /></xsl:attribute>
				<xsl:attribute name="h:merge">combine</xsl:attribute>
				<sv:property sv:name="jcr:primaryType" sv:type="Name"><sv:value>nt:unstructured</sv:value></sv:property>
				<!-- 9, 2-->
				<xsl:element name="sv:node">
					<xsl:attribute name="sv:name">d_<xsl:value-of select="substring(./NewsManagement/FirstCreated, 9, 2)" /></xsl:attribute>
					<xsl:attribute name="h:merge">combine</xsl:attribute>
					<sv:property sv:name="jcr:primaryType" sv:type="Name"><sv:value>nt:unstructured</sv:value></sv:property>
					<!-- create the page object with type as cq:Page -->
					<xsl:element name="sv:node">
						<xsl:attribute name="sv:name">n_<xsl:value-of select="./Identification/NewsIdentifier/NewsItemId" /></xsl:attribute>
						<xsl:attribute name="h:merge">combine</xsl:attribute>
						<sv:property sv:name="jcr:primaryType" sv:type="Name"><sv:value>cq:Page</sv:value></sv:property>

						<!-- create jcr:content node for the page -->
						<xsl:element name="sv:node">
							<xsl:attribute name="sv:name">jcr:content</xsl:attribute>
							<xsl:attribute name="h:merge">combine</xsl:attribute>
							<sv:property sv:name="jcr:primaryType" sv:type="Name"><sv:value>cq:PageContent</sv:value></sv:property>
							<sv:property sv:name="cq:template" sv:type="String"><sv:value>/apps/tc/templates/article</sv:value></sv:property>
							<sv:property sv:name="jcr:title" sv:type="String"><sv:value>><xsl:value-of select="./NewsComponent/NewsLines/HeadLineShort" /></sv:value></sv:property>
							<sv:property sv:name="sling:resourceType" sv:type="String"><sv:value>/apps/tc/components/page/article</sv:value></sv:property>
						
						
								<!-- create content-region parsys -->
									
									<xsl:element name="sv:node">
										<xsl:attribute name="sv:name">content-region</xsl:attribute>
										<xsl:attribute name="h:merge">combine</xsl:attribute>
										<sv:property sv:name="jcr:primaryType" sv:type="Name"><sv:value>nt:unstructured</sv:value></sv:property>
										<sv:property sv:name="sling:resourceType" sv:type="String"><sv:value>foundation/components/parsys</sv:value></sv:property>

										<!-- create article node -->
									<xsl:element name="sv:node">
										<xsl:attribute name="sv:name">article</xsl:attribute>
										<xsl:attribute name="h:merge">combine</xsl:attribute>
										<sv:property sv:name="jcr:primaryType" sv:type="Name"><sv:value>nt:unstructured</sv:value></sv:property>
										<sv:property sv:name="sling:resourceType" sv:type="String"><sv:value>tc/components/content/article</sv:value></sv:property>
										<sv:property sv:name="description" sv:type="String"><sv:value><xsl:value-of select="./NewsComponent/NewsLines/HeadLine" /></sv:value></sv:property>
										<sv:property sv:name="textIsRich" sv:type="Boolean"><sv:value>true</sv:value></sv:property>
										<sv:property sv:name="title" sv:type="String"><sv:value><xsl:value-of select="./NewsComponent/NewsLines/HeadLineShort" /></sv:value></sv:property>
										<sv:property sv:name="textIsRich" sv:type="Boolean"><sv:value>true</sv:value></sv:property>

											
											<!-- create headline component node -->
											<xsl:apply-templates select="./NewsComponent/NewsLines" />
											
											<!-- create image component-->
											<xsl:apply-templates
												select="./NewsComponent/ContentItem/DataContent/CPOnlineFile/CPLink" />
												
											<!-- create text components for all paras-->	
											<xsl:apply-templates
												select="./NewsComponent/ContentItem/DataContent/CPOnlineFile/CPStory" />
											
										</xsl:element>
									</xsl:element>
						</xsl:element>
					</xsl:element>
				</xsl:element>
			</xsl:element>
		</xsl:element>
	</xsl:template>

	<!-- image component mapping for CPLink -->
	<xsl:template
		match="NewsComponent/ContentItem/DataContent/CPOnlineFile/CPLink">

		<xsl:element name="sv:node">
			<xsl:attribute name="sv:name">cplink</xsl:attribute>
			<xsl:attribute name="h:merge">combine</xsl:attribute>
			<sv:property sv:name="height" sv:type="String"><sv:value><xsl:value-of select="@SourceHeight" /></sv:value></sv:property>
			<sv:property sv:name="width" sv:type="String"><sv:value><xsl:value-of select="@SourceWidth" /></sv:value></sv:property>
			<sv:property sv:name="caption" sv:type="String"><sv:value><xsl:value-of select="@Caption" /></sv:value></sv:property>
			
			<sv:property sv:name="image" sv:type="String"><sv:value><xsl:value-of select="@SourceFilePath" /></sv:value></sv:property>
		</xsl:element>
	</xsl:template>

	<!-- text component template for mapping CPStory -->
	<xsl:template match="NewsComponent/ContentItem/DataContent/CPOnlineFile/CPStory">
		<xsl:for-each select="CPStoryPara">
		
		<xsl:element name="sv:node">
			<xsl:attribute name="h:merge">combine</xsl:attribute>
			<xsl:attribute name="sv:name">text<xsl:value-of select="position()" /></xsl:attribute>
			<sv:property sv:name="jcr:primaryType" sv:type="Name"><sv:value>nt:unstructured</sv:value></sv:property>
			<sv:property sv:name="text" sv:type="String"><sv:value><xsl:value-of select="." /></sv:value></sv:property>
			<sv:property sv:name="sling:resourceType" sv:type="String"><sv:value>tc/components/content/text</sv:value></sv:property>
		</xsl:element>
			
		</xsl:for-each>
	</xsl:template>

	<!-- headline component template for mapping NewsLines -->
	<xsl:template match="NewsComponent/NewsLines">
	
		<xsl:element name="sv:node">
			<xsl:attribute name="sv:name">headlineTitle</xsl:attribute>
			<xsl:attribute name="h:merge">combine</xsl:attribute>
			<sv:property sv:name="jcr:primaryType" sv:type="Name"><sv:value>nt:unstructured</sv:value></sv:property>
			<sv:property sv:name="sling:resourceType" sv:type="String"><sv:value>/apps/tc/components/headlineTitle</sv:value></sv:property>
			<sv:property sv:name="headline" sv:type="String"><sv:value><xsl:value-of select="./HeadLine" /></sv:value></sv:property>
			<sv:property sv:name="headlineShort" sv:type="String"><sv:value><xsl:value-of select="./HeadLineShort" /></sv:value></sv:property>
			<sv:property sv:name="byLine" sv:type="String"><sv:value><xsl:value-of select="./ByLine" /></sv:value></sv:property>
			<sv:property sv:name="dateLine" sv:type="String"><sv:value><xsl:value-of select="./DateLine" /></sv:value></sv:property>
			<sv:property sv:name="copyrightLine" sv:type="String"><sv:value><xsl:value-of select="./CopyrightLine" /></sv:value></sv:property>
			
		</xsl:element>
	</xsl:template>

</xsl:stylesheet>
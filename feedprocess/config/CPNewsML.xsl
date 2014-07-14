<?xml version="1.0"?>

<!-- * Licensed to the Apache Software Foundation (ASF) under one * or more 
	contributor license agreements. See the NOTICE file * distributed with this 
	work for additional information * regarding copyright ownership. The ASF 
	licenses this file * to you under the Apache License, Version 2.0 (the * 
	"License"); you may not use this file except in compliance * with the License. 
	You may obtain a copy of the License at * * http://www.apache.org/licenses/LICENSE-2.0 
	* * Unless required by applicable law or agreed to in writing, * software 
	distributed under the License is distributed on an * "AS IS" BASIS, WITHOUT 
	WARRANTIES OR CONDITIONS OF ANY * KIND, either express or implied. See the 
	License for the * specific language governing permissions and limitations 
	* under the License. -->

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:social="http://www.adobe.com/social/1.0"
	xmlns:dex="http://ns.optimasc.com/dex/1.0/" xmlns:granite="http://www.adobe.com/jcr/granite/1.0"
	xmlns:xmpPLUS="http://ns.adobe.com/xap/1.0/PLUS/" xmlns:mix="http://www.jcp.org/jcr/mix/1.0"
	xmlns:nt="http://www.jcp.org/jcr/nt/1.0" xmlns:idPriv="http://ns.adobe.com/xmp/InDesign/private"
	xmlns:adobe_dam="http://www.adobe.com/adobe_dam/1.0" xmlns:xmpNote="http://ns.adobe.com/xmp/note/"
	xmlns:album="http://ns.adobe.com/album/1.0/" xmlns:scg="http://www.adobe.com/social/scg/1.0"
	xmlns:tiff="http://ns.adobe.com/tiff/1.0/" xmlns:crxde="http://www.day.com/jcr/crxde/1.0"
	xmlns:stMfs="http://ns.adobe.com/xap/1.0/sType/ManifestItem#"
	xmlns:xmpGImg="http://ns.adobe.com/xap/1.0/g/img/" xmlns:xmpTPg="http://ns.adobe.com/xap/1.0/t/pg/"
	xmlns:exif="http://ns.adobe.com/exif/1.0/" xmlns:Iptc4xmpCore="http://iptc.org/std/Iptc4xmpCore/1.0/xmlns/"
	xmlns:Iptc4xmpExt="http://iptc.org/std/Iptc4xmpExt/2008-02-29/"
	xmlns:MP="http://ns.microsoft.com/photo/1.2/" xmlns:mediapro="http://ns.iview-multimedia.com/mediapro/1.0/"
	xmlns:crs="http://ns.adobe.com/camera-raw-settings/1.0/" xmlns:stEvt="http://ns.adobe.com/xap/1.0/sType/ResourceEvent#"
	xmlns:xmpBJ="http://ns.adobe.com/xap/1.0/bj/" xmlns:oauth="http://oauth.net/"
	xmlns:prl="http://prismstandard.org/namespaces/prl/2.1/" xmlns:vlt="http://www.day.com/jcr/vault/1.0"
	xmlns:s7sitecatalyst="http://www.day.com/s7sitecatalyst/1.0/"
	xmlns:pdfx="http://ns.adobe.com/pdfx/1.3/" xmlns:dam="http://www.day.com/dam/1.0"
	xmlns:prism="http://prismstandard.org/namespaces/basic/2.1/"
	xmlns:viewerpreset="http://www.day.com/viewerpreset/1.0/" xmlns:xmpMM="http://ns.adobe.com/xap/1.0/mm/"
	xmlns:xmpDM="http://ns.adobe.com/xmp/1.0/DynamicMedia/"
	xmlns:s7userdata="http://www.day.com/s7userdata/1.0/" xmlns:pdf="http://ns.adobe.com/pdf/1.3/"
	xmlns:sling="http://sling.apache.org/jcr/sling/1.0" xmlns:exifEX="http://cipa.jp/exif/1.0/"
	xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:psAux="http://ns.adobe.com/exif/1.0/aux/"
	xmlns:acdsee="http://ns.acdsee.com/iptc/1.0/" xmlns:MicrosoftPhoto="http://ns.microsoft.com/photo/1.0"
	xmlns:sv="http://www.jcp.org/jcr/sv/1.0" xmlns:oak="http://jackrabbit.apache.org/oak/ns/1.0"
	xmlns:rep="internal" xmlns:crx="http://www.day.com/crx/1.0"
	xmlns:DICOM="http://ns.adobe.com/DICOM/" xmlns:stRef="http://ns.adobe.com/xap/1.0/sType/ResourceRef#"
	xmlns:stFNT="http://ns.adobe.com/xap/1.0/sType/Font#" xmlns:dc="http://purl.org/dc/elements/1.1/"
	xmlns:xmp="http://ns.adobe.com/xap/1.0/" xmlns:xmpRights="http://ns.adobe.com/xap/1.0/rights/"
	xmlns:plus="http://ns.useplus.org/ldf/xmp/1.0/" xmlns:prismusagerights="http://prismstandard.org/namespaces/prismusagerights/2.1/"
	xmlns:cc="http://creativecommons.org/ns#" xmlns:slingevent="http://sling.apache.org/jcr/event/1.0"
	xmlns:jcr="http://www.jcp.org/jcr/1.0" xmlns:cq="http://www.day.com/jcr/cq/1.0"
	xmlns:photoshop="http://ns.adobe.com/photoshop/1.0/" xmlns:lr="http://ns.adobe.com/lightroom/1.0/"
	xmlns:xmpG="http://ns.adobe.com/xap/1.0/g/">

	<xsl:output indent="yes" />

	<xsl:template match="/">
		<xsl:copy>
			<xsl:apply-templates select="NewsML/NewsItem" />
		</xsl:copy>
	</xsl:template>

	<xsl:template match="NewsItem">
		<xsl:output method="xml" indent="yes" />
		<xsl:element name="y_{substring(./NewsManagement/FirstCreated,1,4)}"
			jcr:primaryType="nt:unstructured">
			<xsl:copy-of
				select="document('')/xsl:stylesheet/namespace::*[name()!='xsl']" />
			<xsl:element name="m_{substring(./NewsManagement/FirstCreated,6,2)}"
				jcr:primaryType="nt:unstructured">
				<xsl:element name="d_{substring(./NewsManagement/FirstCreated,9,2)}"
					jcr:primaryType="nt:unstructured">
					<xsl:element name="n_{./Identification/NewsIdentifier/NewsItemId}"
						jcr:primaryType="nt:unstructured">
						<xsl:attribute name="id">
				    			<xsl:value-of select="./Identification/NewsIdentifier/NewsItemId" />
				  			</xsl:attribute>
						<xsl:attribute name="createdDate">
				    			<xsl:value-of select="./NewsManagement/FirstCreated" />
				  			</xsl:attribute>

						<xsl:apply-templates select="./NewsComponent/NewsLines" />
						<xsl:apply-templates
							select="./NewsComponent/ContentItem/DataContent/CPOnlineFile/CPLink" />
							
						<xsl:apply-templates
							select="./NewsComponent/ContentItem/DataContent/CPOnlineFile/CPStory" />
					</xsl:element>
				</xsl:element>
			</xsl:element>
		</xsl:element>
	</xsl:template>

	<xsl:template
		match="NewsComponent/ContentItem/DataContent/CPOnlineFile/CPLink">
		<cplink>
			<xsl:attribute name="height">
    			<xsl:value-of select="@SourceHeight" />
  			</xsl:attribute>

			<xsl:attribute name="width">
    			<xsl:value-of select="@SourceWidth" />
  			</xsl:attribute>
			<xsl:attribute name="caption">
    			<xsl:value-of select="@Caption" />
  			</xsl:attribute>

			<xsl:attribute name="image">
    			<xsl:value-of select="@SourceFilePath" />
  			</xsl:attribute>


		</cplink>
	</xsl:template>

	<xsl:template match="NewsComponent/ContentItem/DataContent/CPOnlineFile/CPStory">
		<xsl:for-each select="CPStoryPara">
			<xsl:element name="para{position()}">
				<xsl:attribute name="text"><xsl:value-of select="." /></xsl:attribute>
			</xsl:element>
			
		</xsl:for-each>
	</xsl:template>

	<xsl:template match="NewsComponent/NewsLines">
		<xsl:element name="newsLines">
			<xsl:attribute name="headLine">
    			<xsl:value-of select="./HeadLine" />
  			</xsl:attribute>
			<xsl:attribute name="headLineShort">
    			<xsl:value-of select="./HeadLineShort" />
  			</xsl:attribute>
			<xsl:attribute name="byLine">
    			<xsl:value-of select="./ByLine" />
  			</xsl:attribute>
			<xsl:attribute name="dateLine">
    			<xsl:value-of select="./DateLine" />
  			</xsl:attribute>
			<xsl:attribute name="copyrightLine">
    			<xsl:value-of select="./CopyrightLine" />
  			</xsl:attribute>
		</xsl:element>
	</xsl:template>


</xsl:stylesheet>
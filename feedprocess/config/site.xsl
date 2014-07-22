<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:sling="http://sling.apache.org/jcr/sling/1.0" xmlns:cq="http://www.day.com/jcr/cq/1.0" xmlns:jcr="http://www.jcp.org/jcr/1.0" xmlns:nt="http://www.jcp.org/jcr/nt/1.0" xmlns:xalan="http://xml.apache.org/xalan"
                xmlns:str="xalan://java.lang.String">

<xsl:output indent="yes" />
	
<xsl:template match="/">
<xsl:element name="jcr:root">
        <xsl:attribute name="jcr:primaryType">cq:Page</xsl:attribute>
			
		<xsl:copy>
			<xsl:apply-templates select="site" />
		</xsl:copy>
        </xsl:element>
	</xsl:template>
    
    
	 <xsl:template match="site">
     <xsl:output method="xml" indent="yes" />
		
        <xsl:element name="jcr:content">
        <xsl:attribute name="cq:template">/apps/tc/templates/appsconfigpage</xsl:attribute>
        <xsl:attribute name="jcr:primaryType">cq:PageContent</xsl:attribute>
        <xsl:attribute name="jcr:title"><xsl:value-of select="/site/title" /></xsl:attribute>
        <xsl:attribute name="sling:resourceType">/apps/tc/components/appsconfig/appsconfig</xsl:attribute>

        <xsl:apply-templates select="*"/>
        </xsl:element>
	</xsl:template>
		
		

        <xsl:template match="*">
        <xsl:output method="xml" indent="yes" />
        <xsl:variable name="LocalName" select="local-name()"/>
        <xsl:variable name="varvalue"><xsl:value-of select="."/></xsl:variable>
        
        <xsl:variable name="varvalue1"><xsl:value-of select="str:replaceAll(
        str:new($varvalue),
        '&lt;a',
        '&lt;t')"/></xsl:variable>
                 
                 <xsl:if  test="$varvalue!=''">
                    <xsl:element name="{$LocalName}">
                    <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
                    <xsl:attribute name="author"><xsl:value-of select="$varvalue1"/></xsl:attribute>
                    <xsl:attribute name="publish"><xsl:value-of select="$varvalue1"/></xsl:attribute>
                    </xsl:element>
                        </xsl:if>	
                    
        <xsl:apply-templates select="node()"/>

        </xsl:template>
	
    
           
     
        <xsl:template match="text()|@*"></xsl:template>
          
        <xsl:template match="site/properties">
            
        <xsl:for-each select="socialMedia/*">

        <xsl:variable name="LocalName" select="concat('site.properties.',local-name())"/>
        <xsl:variable name="show" select="concat($LocalName,'.show')"/>
        <xsl:variable name="url" select="concat($LocalName,'.url')"/>
        <xsl:element name="{$show}">
                    <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
                    <xsl:attribute name="author"><xsl:value-of select="./show"/></xsl:attribute>
                    <xsl:attribute name="publish"><xsl:value-of select="./show"/></xsl:attribute>
                    </xsl:element>
             <xsl:element name="{$url}">
                    <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
                    <xsl:attribute name="author"><xsl:value-of select="./url"/></xsl:attribute>
                    <xsl:attribute name="publish"><xsl:value-of select="./url"/></xsl:attribute>
                    </xsl:element>       
                    
         </xsl:for-each>
         
         <xsl:for-each select="newsletterbut">
         
         <xsl:element name="site.properties.newsletterbut.url">
                    <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
                    <xsl:attribute name="author"><xsl:value-of select="./url"/></xsl:attribute>
                    <xsl:attribute name="publish"><xsl:value-of select="./url"/></xsl:attribute>
                    </xsl:element>
                    <xsl:element name="site.properties.newsletterbut.Text">
                    <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
                    <xsl:attribute name="author"><xsl:value-of select="./Text"/></xsl:attribute>
                    <xsl:attribute name="publish"><xsl:value-of select="./Text"/></xsl:attribute>
                    </xsl:element>       
            </xsl:for-each>
         
         <xsl:for-each select="whatsHotOptions">
        
         <xsl:element name="site.properties.whatsHotOptions.activate">
                    <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
                    <xsl:attribute name="author"><xsl:value-of select="./activate"/></xsl:attribute>
                    <xsl:attribute name="publish"><xsl:value-of select="./activate"/></xsl:attribute>
                    </xsl:element>
         </xsl:for-each>
         
         
         <xsl:for-each select="whatsHot/*">
         <xsl:variable name="LocalName" select="local-name()"/>
         <xsl:element name="site.properties.whatsHot.item.class">
                    <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
                    <xsl:attribute name="author"><xsl:value-of select="./class"/></xsl:attribute>
                    <xsl:attribute name="publish"><xsl:value-of select="./class"/></xsl:attribute>
                    </xsl:element>
                    <xsl:element name="site.properties.whatsHot.item.id">
                    <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
                    <xsl:attribute name="author"><xsl:value-of select="./id"/></xsl:attribute>
                    <xsl:attribute name="publish"><xsl:value-of select="./id"/></xsl:attribute>
                    </xsl:element>  
                    <xsl:element name="site.properties.whatsHot.item.external">
                    <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
                    <xsl:attribute name="author"><xsl:value-of select="./external"/></xsl:attribute>
                    <xsl:attribute name="publish"><xsl:value-of select="./external"/></xsl:attribute>
                    </xsl:element>
                    <xsl:element name="site.properties.whatsHot.item.infotag">
                    <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
                    <xsl:attribute name="author"><xsl:value-of select="./infotag"/></xsl:attribute>
                    <xsl:attribute name="publish"><xsl:value-of select="./infotag"/></xsl:attribute>
                    </xsl:element>
                    <xsl:element name="site.properties.whatsHot.item.title">
                    <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
                    <xsl:attribute name="author"><xsl:value-of select="./title"/></xsl:attribute>
                    <xsl:attribute name="publish"><xsl:value-of select="./tile"/></xsl:attribute>
                    </xsl:element>
                    <xsl:element name="site.properties.whatsHot.item.url">
                    <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
                    <xsl:attribute name="author"><xsl:value-of select="./url"/></xsl:attribute>
                    <xsl:attribute name="publish"><xsl:value-of select="./url"/></xsl:attribute>
                    </xsl:element> 
                    <xsl:element name="site.properties.whatsHot.item.placeholder">
                    <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
                    <xsl:attribute name="author"><xsl:value-of select="./placeholder"/></xsl:attribute>
                    <xsl:attribute name="publish"><xsl:value-of select="./placeholder"/></xsl:attribute>
                    </xsl:element>                     
            </xsl:for-each>
         
        </xsl:template>
        
        
        


</xsl:stylesheet>
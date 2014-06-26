package com.tc.action;


import com.day.cq.wcm.foundation.Chart;

import com.day.cq.wcm.foundation.Placeholder;
import java.util.ResourceBundle;
import org.apache.sling.api.resource.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.designer.Design;
import com.day.cq.wcm.api.components.Component;
import com.tc.action.BaseAction;
import com.tc.model.ChartBean;

/**
 * @author syamsundarv
 *
 */

public class ChartAction extends BaseAction {

	
	 String chartUrl=null;
	 String legendUrl=null;
	 String chartType = null;
	 String pieChartType = null;
	 String alt = null;	
	
		 
    /**
     *  The log.
     */
    private Logger LOG = LoggerFactory.getLogger(ChartAction.class);
    
    /**
     *  This method contains the logic to build chartBean which is going to be
     *  displayed in chart component.
     * 
     */
   
    public ChartBean getChartInfo(){
    	
	ResourceBundle bundle = getSlingRequest().getResourceBundle(null);
	ChartBean chartBean=new ChartBean();
    try{
    	
    	Resource resource=getSlingRequest().getResource();
    	String msg=bundle.getString("No chart type selected.");
    	chartBean.setMsg(msg);
     
    	Chart chart = new Chart(resource);
    	
	    pieChartType=Chart.PIE_CHART_TYPE;
	    chartBean.setPieChartType(pieChartType);
	    	   
	    chartType=getProperty(Chart.PN_CHART_TYPE);
	    chartBean.setChartType(chartType);
	   
	    alt = chart.getAlt();
	    chartBean.setAlt(alt);
	  	  	   	   
	    if (chart.hasData()) {
	    	
	    	 final Design currentDesign=getCurrentDesign();
	    	 final Design resourceDesign=getResourceDesign();

	 	  	 String suffix = currentDesign.equals(resourceDesign) ? "" : "/" + currentDesign.getId();
            
	    	// add mod timestamp to avoid client-side caching of updated images
           
            String  tstamp=null;
            if(getProperty("jcr:lastModified")!=null){
            	tstamp = getProperty("jcr:lastModified");
            }
            else if(getProperty("jcr:created")!=null){
            	tstamp = getProperty("jcr:created");
            }
            else{
            	tstamp=String.valueOf(System.currentTimeMillis());
            }
          
           	 suffix += "/" + tstamp + ".png";
	         
	    	 chartUrl = resource.getPath() + ".img.png" + suffix;
	         chartBean.setChartUrl(chartUrl);
	         
	         legendUrl = resource.getPath() + ".legend.png" + suffix;
	         chartBean.setLegendUrl(legendUrl);
	        
	      } else {
	        
	    	 chartUrl = "/etc/designs/default/0.gif";
	         chartBean.setChartUrl(chartUrl);

	         String classicPlaceholder = "<img src=\"" + chartUrl +"\" class=\"cq-chart-placeholder\"/>";
	         Component component = null;
			 String placeholder = Placeholder.getDefaultPlaceholder(getSlingRequest(),component, classicPlaceholder);
	   	     chartBean.setPlaceholder(placeholder);
	        
	    }
	  	 
    }
    catch(Exception e){
	String msg1= bundle.getString("Chart could not be generated:") + " " + e.getLocalizedMessage() ;
	LOG.info("MSG"+msg1);
    }

	return chartBean;

	}
   
}

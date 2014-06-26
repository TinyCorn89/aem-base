package com.tc.model;

import java.io.Serializable;

public class ChartBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String chartUrl;
    private String legendUrl;
    private String chartType;
    private String pieChartType;
    private String msg;
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	private String alt;
    private String placeholder;
	
    
    
    
    public String getPieChartType() {
		return pieChartType;
	}

	public void setPieChartType(String pieChartType) {
		this.pieChartType = pieChartType;
	}

    public String getChartType() {
		return chartType;
	}

	public void setChartType(String chartType) {
		this.chartType = chartType;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public String getChartUrl() {
		return chartUrl;
	}
	
	public void setChartUrl(String chartUrl) {
		this.chartUrl = chartUrl;
	}
	public String getLegendUrl() {
		return legendUrl;
	}
	public void setLegendUrl(String legendUrl) {
		this.legendUrl = legendUrl;
	}

	@Override
	public String toString() {
		return "ChartBean [chartUrl=" + chartUrl + ", legendUrl=" + legendUrl
				+ ", chartType=" + chartType + ", pieChartType=" + pieChartType
				+ ", msg=" + msg + ", alt=" + alt + ", placeholder="
				+ placeholder + "]";
	}

	


}

<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>

<%@include file="/apps/tc/global/global.jsp"%>

<action:action actionClassName="com.tc.action.ChartAction" bean="chartBean" actionName="getChartInfo" />

<c:choose> 
				<c:when test="${chartBean.legendUrl != null}">
					<c:choose> 
						<c:when test="${chartBean.chartType == null}">
							<div>
                                ${chartBean.msg}
							</div>
						</c:when> 
						<c:when test="${chartBean.chartType==pieChartType}"> 
							<div style="text-align:center">
								<img src="${chartBean.chartUrl}" alt="${chartBean.alt}" />
								<img src="${chartBean.legendUrl}"/>
							</div> 
						</c:when> 
						<c:otherwise> 
							<table cellspacing="4px">
								<tbody>
									<tr>
										<td><img src="${chartBean.chartUrl}" alt="${chartBean.alt}"/></td>
										<td><img src="${chartBean.legendUrl}"/></td>
									</tr>
								</tbody>
							</table>
						</c:otherwise> 
					</c:choose>
				</c:when> 
				<c:otherwise> 
                    ${chartBean.placeholder} 
				</c:otherwise> 

</c:choose>

<%@include file="/apps/tc/components/content/chart/tracking-js.jsp"%>

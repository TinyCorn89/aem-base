<%--
  Copyright 1997-2008 Day Management AG
  Barfuesserplatz 6, 4001 Basel, Switzerland
  All Rights Reserved.

  This software is the confidential and proprietary information of
  Day Management AG, ("Confidential Information"). You shall not
  disclose such Confidential Information and shall use it only in
  accordance with the terms of the license agreement you entered into
  with Day.

  ==============================================================================

  Form 'action' component

  Handle POST by forwarding to Sling POST servlet

--%><%
%><%@ page session="false" %><%
%><%@ page import="com.day.cq.wcm.foundation.forms.FormResourceEdit,
                   com.day.cq.wcm.foundation.forms.FormsHelper,
                   org.apache.sling.api.request.RequestDispatcherOptions,
                  com.tc.service.api.NewsLetterService,
                    org.osgi.framework.BundleContext,
                   org.osgi.framework.FrameworkUtil,
                  org.osgi.framework.ServiceReference,java.util.ArrayList" %><%
%><%@ taglib prefix="sling" uri="http://sling.apache.org/taglibs/sling/1.0" %><%
%><sling:defineObjects/><%

BundleContext bundleContext = FrameworkUtil.getBundle(
			          NewsLetterService.class).getBundleContext();
			ServiceReference serviceReference = bundleContext
			          .getServiceReference(NewsLetterService.class.getName());
			NewsLetterService service = (NewsLetterService) bundleContext
			          .getService(serviceReference);

    if (FormResourceEdit.isSingleResourcePost(slingRequest)) {
        // simply use form forwarding here
      final String []  tempmailList = request.getParameterValues("mailList");
        String [] mailList = {"UNIS List 1","UNIS List 2"};
final String   emailId = request.getParameter("email");
        if(null != tempmailList){
        for (int i = 0; i < mailList.length; i++){

                  for(String listName1 :tempmailList){
                      if(listName1.equals(mailList[i])){

                       mailList[i] =null;
                      }

                  }

                  //System.out.println("listName==="+listName);
                  // service.unSubscribe(emailId,listName);
        }
             for(String listName :tempmailList ){
              if(null!=listName){
                  //System.out.println("listName=="+listName);
                  // subscribing the news letter
                   service.subscribe(emailId,listName);
              }
        }
        }


          for(String listName :mailList ){
              if(null!=listName){
                  //System.out.println("unSubscribe==listName==="+listName);
                   service.unSubscribe(emailId,listName);
              }
        }
      


      FormsHelper.setForwardPath(slingRequest, FormResourceEdit.getPostResourcePath(slingRequest), true);
    }

    // in case of multiple resources, we let the post.POST.jsp do the job
%>
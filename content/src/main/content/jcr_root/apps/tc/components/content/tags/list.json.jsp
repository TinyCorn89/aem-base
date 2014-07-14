<%@page import="java.util.*,
                com.day.cq.tagging.TagManager,
                com.day.cq.tagging.Tag" %><%
%><%@include file="/libs/foundation/global.jsp"%>

<%
/*
    Temporary component for the poc
*/

%>
<%

 class TagComp implements Comparable<TagComp>{
    private String id = null;
    private String title = null;
    
    public TagComp(String id, String title){
        this.id = id;
        this.title=title;
    }
    public int compareTo(TagComp t) {
        return this.title.compareTo(t.getTitle());
    }
    public String getId(){
        return this.id;
    }
    public String getTitle(){
        return this.title;
    }
}


response.setCharacterEncoding("UTF-8");
response.setContentType("application/json");
String path = request.getParameter("path");
String otherValue = request.getParameter("extra")!=null?request.getParameter("extra"): "";
String noneValue = request.getParameter("none")!=null&&request.getParameter("none").equals("true")?request.getParameter("none"):"";
Tag tag = resourceResolver.getResource(path).adaptTo(Tag.class);
Iterator<Tag> iter = tag.listChildren(); 

List<TagComp> tcList = new ArrayList<TagComp>();
while(iter.hasNext()){
    Tag t = iter.next();
    tcList.add(new TagComp(t.getTagID(),t.getTitle()));
}
Collections.sort(tcList);
%>
 
    [<%
    String delim = ""; 
    if(!otherValue.equals("")){
        %>{<%
            %>"value":"<%=  otherValue %>",<% 
            %>"text":"<%= otherValue %>",<% 
        %>}, <%
    }
    if(!noneValue.equals("")){
        %>{
            "value":"","text":"&nbsp;"
          }, 
        <%
    }    
    for(TagComp t : tcList ){
        %><%= delim %><% 
        %>{<%
        %>"value":"<%=  t.getId() %>",<% 
        %>"text":"<%= t.getTitle() %>",<% 
        %>}<%
        if ("".equals(delim)) { 
            delim = ","; 
        }
    }

    %>]
/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

function getInfo(keywordName,val)
{
    document.searchForm.method="post";
    document.searchForm.action="/bin/searchResultsServlet?"+keywordName+"="+val;
    document.searchForm.submit();
}

function getPageSubmit()
{
    if(document.searchForm.keyword.value!= "" || document.searchForm.author.value!= "" || document.searchForm.sectionTab.value!="" || document.searchForm.contentType.value!="" || document.searchForm.timeRange.value!="")
	{
    	pageSubmit();	
	}
    else
    {
        return false;
    }
}

function getSimplePageSubmit()
{
    if(document.forms[0].q.value!= "")
	{
    	pageSubmit();	
	}
    else
    {
        return false;
    }
}

function pageSubmit()
{

    document.forms[0].method="get";
	document.forms[0].action="/bin/searchResultsServlet";
	document.forms[0].submit();	
}

function cmbo(opn)
{
    if(opn == 2)
    {
        $("#dterge").show()
    }
    else
    {
        $("#dterge").hide()
    }
}

function radioAutoselected(val)
{
    if(val == '-7d'){
        document.getElementById("timeRange7D").checked = true;
    }
    else if(val == '-1M'){
        document.getElementById("timeRange1M").checked = true;
    }
    else if(val == '-3M'){
        document.getElementById("timeRange3M").checked = true;
    }
    else if(val == '-1Y'){
        document.getElementById("timeRange1Y").checked = true;
    }
    else if(val == 'DD'){
        document.getElementById("timeRangeDD").checked = true;
        cmbo(2)
    }
    else if(val == '-15Y'){
        document.getElementById("timeRange5Y").checked = true;
    }
}
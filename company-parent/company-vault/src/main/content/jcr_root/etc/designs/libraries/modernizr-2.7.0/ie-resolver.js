(function() {
	'use strict';
	
	var v;

	function getInternetExplorerVersion() {
		var rv = -1;
		if (navigator.appName == 'Microsoft Internet Explorer') {
			var ua = navigator.userAgent;
			var re = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");
			if (re.exec(ua) != null)
				rv = parseFloat(RegExp.$1);
		} else if (navigator.appName == 'Netscape') {
			var ua = navigator.userAgent;
			var re = new RegExp("Trident/.*rv:([0-9]{1,}[\.0-9]{0,})");
			if (re.exec(ua) != null)
				rv = parseFloat(RegExp.$1);
		}
		
		return rv;
	}
	
	v = getInternetExplorerVersion();
	
	
	if (v > 0) {
		document.getElementsByTagName('html')[0].className += ' ie ie' + v;
	}
}());
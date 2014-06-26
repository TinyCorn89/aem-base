/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

$(document).ready(function(){
var $container = $('#content');
$container.isotope({
	filter:'*',
	getSortData : {
                    title : function ( $elem ) {
                        return $elem.find('.title').text();
                }
                },
                sortBy : 'title',

	animationOptions: {
     duration: 750,
     easing: 'linear',
     queue: false,
   }/*,
    layoutMode: 'cellsByColumn',
    cellsByColumn: {
        rowHeight: 300,
        columnWidth: 400
    }*/

});

$('#nav a').click(function(){
  var selector = $(this).attr('data-filter');
    var navEle = $(this).closest("#nav");

    $container = $(navEle).next("div#content");
    $container.isotope({ 
	filter: selector,
        getSortData : {
                    title : function ( $elem ) {
                        return $elem.find('.title').text();
                }
                },
                sortBy : 'title',
	animationOptions: {
     duration: 750,
     easing: 'linear',
     queue: false, 
   },
         layoutMode: 'fitRows',


  });
  return false;
});

});


/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

$(document).ready(function(){
    var temp = heroDiv.getAttribute("x");
    var y = heroDiv.getAttribute("y");
    var id2='#camera5253e0286f4cf';
    y = id2.concat(y);
  var x;
    if(temp == "true"){
     x = true;
    }else{
        x = false;
    }
    jQuery(function() {
                   var myCamera = jQuery(y);
                      if (!myCamera.hasClass('motopress-camera')) {
                        myCamera.addClass('motopress-camera');
                        myCamera.camera({
                          alignment           : 'topCenter', //topLeft, topCenter, topRight, centerLeft, center, centerRight, bottomLeft, bottomCenter, bottomRight
                          autoAdvance         : x,   //true, false
                          mobileAutoAdvance   : x, //true, false. Auto-advancing for mobile devices
                          barDirection        : 'leftToRight',    //'leftToRight', 'rightToLeft', 'topToBottom', 'bottomToTop'
                          barPosition         : 'bottom',    //'bottom', 'left', 'top', 'right'
                          cols                : 12,
                          easing              : 'easeOutQuad',  //for the complete list http://jqueryui.com/demos/effect/easing.html
                          mobileEasing        : '',   //leave empty if you want to display the same easing on mobile devices and on desktop etc.
                          fx                  : 'simpleFade',    //'random','simpleFade', 'curtainTopLeft', 'curtainTopRight', 'curtainBottomLeft',          'curtainBottomRight', 'curtainSliceLeft', 'curtainSliceRight', 'blindCurtainTopLeft', 'blindCurtainTopRight', 'blindCurtainBottomLeft', 'blindCurtainBottomRight', 'blindCurtainSliceBottom', 'blindCurtainSliceTop', 'stampede', 'mosaic', 'mosaicReverse', 'mosaicRandom', 'mosaicSpiral', 'mosaicSpiralReverse', 'topLeftBottomRight', 'bottomRightTopLeft', 'bottomLeftTopRight', 'bottomLeftTopRight'
                          //you can also use more than one effect, just separate them with commas: 'simpleFade, scrollRight, scrollBottom'
                          mobileFx            : '',   //leave empty if you want to display the same effect on mobile devices and on desktop etc.
                          gridDifference      : 250,  //to make the grid blocks slower than the slices, this value must be smaller than transPeriod
                          height              : '55.325%', //here you can type pixels (for instance '300px'), a percentage (relative to the width of the slideshow, for instance '50%') or 'auto'
						  hover               : false,
                          imagePath           : 'images/',    //he path to the image folder (it serves for the blank.gif, when you want to display videos)
                          loader              : 'bar',    //pie, bar, none (even if you choose "pie", old browsers like IE8- can't display it... they will display always a loading bar)
                          loaderColor         : '#ffffff',
                          loaderBgColor       : '#eb8a7c',
                          loaderOpacity       : 1,    //0, .1, .2, .3, .4, .5, .6, .7, .8, .9, 1
                          loaderPadding       : 0,    //how many empty pixels you want to display between the loader and its background
                          loaderStroke        : 6,    //the thickness both of the pie loader and of the bar loader. Remember: for the pie, the loader thickness must be less than a half of the pie diameter
                          minHeight           : '147px',  //you can also leave it blank
                          navigation          : x, //true or false, to display or not the navigation buttons
                          navigationHover     : false,    //if true the navigation button (prev, next and play/stop buttons) will be visible on hover state only, if false they will be visible alway
                          pagination          : true,
                          playPause           : x,   //true or false, to display or not the play/pause buttons
                          pieDiameter         : 33,
                          piePosition         : 'rightTop',   //'rightTop', 'leftTop', 'leftBottom', 'rightBottom'
                          portrait            : true, //true, false. Select true if you don't want that your images are cropped
                          rows                : 8,
                          slicedCols          : 12,
                          slicedRows          : 8,
                          thumbnails          : false,
                          time                : 7000,   //milliseconds between the end of the sliding effect and the start of the next one
                          transPeriod         : 1500, //lenght of the sliding effect in milliseconds
                          
                          ////////callbacks
                          
                          onEndTransition     : function() {
                            jQuery('.cameraContent .category').fadeIn().animate({
                              top:"0px"}
                                                                                , 830);
                          }
                          ,  //this callback is invoked when the transition effect ends
                          onLoaded            : function() {
                            
                          }
                          ,  //this callback is invoked when the image on a slide has completely loaded
                          onStartLoading      : function() {

                          }
                          ,  //this callback is invoked when the image on a slide start loading
                          onStartTransition   : function() {
                            jQuery('.cameraContent .category').fadeOut().animate({
                              top:"-45px"}
                                                                                 , 832);
                          }
                          //this callback is invoked when the transition effect starts
                        }
                                       );
                      }

                    }
                          );
    

});

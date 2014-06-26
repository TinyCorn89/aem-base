/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

$(document).ready(function(){

  $( ".open_video" ).click(function() {
        var child=$(this).parent().children('.play_video');
        $(child).show();
        $(child).children('video').get(0).play();
        $(".camera_commands, .camera_pag,.camera_prev,.camera_next").css('z-index','0');
    });

    $( ".close_video" ).click(function() {
        var child=$(this).parent();
        $(child).hide();
        $(child).children('video').get(0).pause();
        if(($(child).children('video').get(0).currentTime) !=0 ) {
            $(child).children('video').get(0).currentTime =0;
		}
        $(".camera_commands, .camera_pag,.camera_prev,.camera_next").css('z-index','99');
    });


});
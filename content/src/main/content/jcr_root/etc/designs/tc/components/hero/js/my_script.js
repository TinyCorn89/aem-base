/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

$(window).load(function(){
    $('.cameraContent').each(function(num){
        var th=$(this).find('.camera_link');
        $(this).find('.camera_caption .category').appendTo(th);
    });
    
    $('.mini-posts-grid li:nth-child(4n)').addClass('nomargin4n');
    $('.mini-posts-grid li:nth-child(3n)').addClass('nomargin3n');
});
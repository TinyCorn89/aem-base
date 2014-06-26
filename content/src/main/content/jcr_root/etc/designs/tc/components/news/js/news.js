/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

function newsFeedScroll(id, num) {
    setTimeout(function () {
        $(".news_scroll" + id).each(function () {
            console.log($(this));
            $(this).find(".feedEkList").carouFredSel({
                direction: "up",
                items: {
                    visible: num,
                    start: 1
                },

                scroll: {
                    items: 1,
                    //  duration: 1500
                },
                auto: false,
                prev: $(this).find(".rssprev"),
                next: $(this).find(".rssnext")

            });
        });
    }, 1000);
}

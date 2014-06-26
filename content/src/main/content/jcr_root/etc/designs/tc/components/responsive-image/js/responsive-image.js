/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 *
 * Responsive Image Component
 * @author Virtusa
 * &copy;2014 Virtusa, Inc. All rights reserved.
 * <br />
 * Example:<br />
 * <xmp>
         // Create the JS instance of the component.
         var instance = new $.ResponsiveImage('#test-image', [ 480, 768 ], [ 'images/logo-sm.png',
                                                                      'images/logo-med.png', 'images/logo-lg.png' ]);
       // clean up
        instance.dispose();
 * </xmp>
 */

(function($) {
    'use strict';
    /**
     * @private
     */
    function deepCss(who, css, ps) {
        var sty, dv = document.defaultView;
        if (document.body.currentStyle) {
            sty = css.replace(/\-([a-z])/g, function(a, b) {
                return b.toUpperCase();
            });
            return who.currentStyle[sty];
        }
        if (dv) {
            dv = document.defaultView.getComputedStyle(who, ps);
            return dv.getPropertyValue(css);
        }
        return '';
    }
    /**
     * @private
     */
    function getWindowDimension(d) {
        var firstChar = d.charAt(0).toUpperCase();
        var chars = d.split('');
        chars[0] = firstChar;
        d = chars.join('');
        if (typeof window.innerWidth != 'undefined') {
            return window['inner' + d];
        } else {
            if (typeof document.documentElement != 'undefined' && typeof document.documentElement.clientWidth != 'undefined' && document.documentElement.clientWidth !== 0) {
                return document.documentElement['client' + d];
            }
        }
        return 0;
    }
    /**
     * @private
     */
    function getWindowSizeEx(unitOfMeasurement) {
        if (unitOfMeasurement === 'px') {
            return getWindowDimension('width');
        }
        var fontSizeInPx = deepCss(document.body, 'font-size');
        if (fontSizeInPx.indexOf('px') === -1) {
            var fontSizeValue = parseInt(fontSizeInPx, 10) / 100;
            var numericValue = 16 * fontSizeValue;
            fontSizeInPx = numericValue + 'px';
        }
        var fontSize = parseInt(fontSizeInPx, 10);
        var width = getWindowDimension('width');
        var widthInEms = Math.floor(width / fontSize);
        return widthInEms;
    }

    /**
     * The handler used by each component when the window changes size. Be sure
     * to remove this when you are done!
     *
     * @private
     */
    function resizeHandler(evt) {
        var self = evt.data;
        var resizeTimeout = null;
        var period = 250;
        if (resizeTimeout !== null) {
            window.clearTimeout(resizeTimeout);
            resizeTimeout = window.setTimeout(function() {
                self.render();
                resizeTimeout = null;
            }, period);
        } else {
            self.render();
            resizeTimeout = window.setTimeout(function() {
                resizeTimeout = null;
            }, period);
        }
    }

    /**
     * @constructor
     */
    function ResponsiveImage() {
        this.units = 'px';
        if (arguments.length === 3) {
            this.init.apply(this, arguments);
        }
    }
    /**
     * @param {String|DOMElement|jQueryElement} el
     * @param {Array<Number>} breakpoints Should be 2 breakpoints, but may work with more.
     * @param {Array<String>} srcs Array of image path strings. The length of this array should breakpoints.length + 1.
     */
    ResponsiveImage.prototype.init = function(el, breakpoints, srcs) {
        breakpoints.unshift(0);
        this.el = $(el);
        this.breakpoints = breakpoints;
        this.srcs = srcs;
        this.breakpointsLen = this.breakpoints.length;
        this.render();
        $(window).on('resize', this, resizeHandler);
    };
    /**
     * Clean up the component, especially the handler.
     */
    ResponsiveImage.prototype.dispose = function() {
        $(window).off('resize', resizeHandler);
        this.el = null;
        this.breakpoints = null;
        this.srcs = null;
        this.breakpointsLen = null;
    };
    /**
     * Renders the image according to the rules.
     */
    ResponsiveImage.prototype.render = function() {
        var winSize = getWindowSizeEx(this.units);
        var src = '';
        for (var index = 0; index < this.breakpointsLen; index++) {
            var breakpoint = this.breakpoints[index];
            if (winSize >= breakpoint) {
                src = this.srcs[index];
            } else {
                break;
            }
        }
        this.el.attr('src', src);
    };

    $.ResponsiveImage = ResponsiveImage;

    // This will happen later.
    $(function() {

        /**
         * Description
         * @param {String} path A path passed in by jcr.
         * @return {Boolean} If path is valid.
         */
        function validImagePath(path) {
            return (path.length > 0) && (path !== 'Placeholder');
        }

        /**
         * Description
         * @param {DOMElement} parentDiv Wrapper for a "responsive-image" component. This wrapper contains the div declared in JSP.
         */
        function initResponsiveImage(parentDiv) {
            parentDiv = $($(parentDiv).find('div')[0]);
            // TODO: add params for breakpoint array
            var mobileImage = parentDiv.attr('mobileImagePath');
            var tabletImage = parentDiv.attr('tabletImagePath');
            var desktopImage = parentDiv.attr('desktopImagePath');
            var selector = $(parentDiv.find('img')[0]);
            if (validImagePath(mobileImage) && validImagePath(tabletImage) && validImagePath(desktopImage)) {
                if (!selector.data('initialized')) {
                    var instance = new $.ResponsiveImage(selector, [480, 768], [mobileImage, tabletImage,
                        desktopImage
                    ]);
                    instance.render();
                    selector.data('initialized', true);
                }
            } else {
                selector.css('min-height', '64px');
                selector.css('min-width', '64px');
            }
        }
        var components = $(".responsive-image");
        // Loop through every component of class 'responsive-image'.
        for (var index = 0; index < components.length; index++) {
            var component = components[index];
            initResponsiveImage(component);
        }
    });

    return ResponsiveImage;
}(window.jQuery));

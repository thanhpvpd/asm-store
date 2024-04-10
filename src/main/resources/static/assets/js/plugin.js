jQuery(document).ready(function () {

    window.jssor_1_slider_init = function () {

        var jssor_1_SlideoTransitions = [
            [{ b: 0, d: 1000, x: -460, e: { x: 7 } }],
            [{ b: 500, d: 1000, y: 265, e: { y: 7 } }],
            [{ b: 0, d: 1000, y: 360, e: { y: 7 } }],
            [{ b: -1, d: 1, x: 30, p: { x: { o: 33, r: 0.5 } } }, { b: 1000, d: 500, o: 1, p: { o: { o: 32, r: 0.5 } } }, { b: 1500, d: 500, x: 0, o: 1, e: { x: 3 }, p: { x: { dl: 0 } } }],
            [{ b: -1, d: 1, x: 30, p: { x: { o: 33, r: 0.5 } } }, { b: 2000, d: 500, o: 1, p: { o: { o: 32, r: 0.5 } } }, { b: 2500, d: 500, x: 0, o: 1, e: { x: 3 }, p: { x: { dl: 0 } } }],
            [{ b: -1, d: 1, sX: 2, sY: 2 }, { b: 0, d: 600, o: 1, sX: 1, sY: 1, e: { sX: 6, sY: 6 } }],
            [{ b: 300, d: 600, o: 1 }],
            [{ b: 0, d: 600, y: 87, e: { y: 6 } }],
            [{ b: 600, d: 600, o: 1 }],
            [{ b: 600, d: 600, rp: 1 }],
            [{ b: -1, d: 1, x: -10 }, { b: 600, d: 600, x: 0, o: 1, e: { x: 6 } }],
            [{ b: -1, d: 1, ls: -0.5 }, { b: 600, d: 600, ls: 0, e: { ls: 6 } }],
            [{ b: -1, d: 1, x: -10 }, { b: 1100, d: 600, x: 0, o: 1, e: { x: 6 } }],
            [{ b: -1, d: 1, ls: -0.5 }, { b: 1100, d: 600, ls: 0, e: { ls: 6 } }],
            [{ b: -1, d: 1, x: -10 }, { b: 1600, d: 600, x: 0, o: 1, e: { x: 6 } }],
            [{ b: -1, d: 1, ls: -0.5 }, { b: 1600, d: 600, ls: 0, e: { ls: 6 } }],
            [{ b: 0, d: 1000, y: -151, e: { y: 7 } }],
            [{ b: -1, d: 1, x: 50 }, { b: 1000, d: 500, x: 0, o: 1, e: { x: 7, o: 7 }, p: { x: { dl: 0.8 }, o: { dl: 0.8 } } }],
            [{ b: -1, d: 1, x: 50 }, { b: 1500, d: 500, x: 0, o: 1, e: { x: 7, o: 7 }, p: { x: { dl: 0.8 }, o: { dl: 0.8 } } }],
            [{ b: 1500, d: 500, x: 540 }]
        ];

        var jssor_1_options = {
            $AutoPlay: 1,
            $SlideDuration: 800,
            $SlideEasing: $Jease$.$OutQuint,
            $CaptionSliderOptions: {
                $Class: $JssorCaptionSlideo$,
                $Transitions: jssor_1_SlideoTransitions
            },
            $ArrowNavigatorOptions: {
                $Class: $JssorArrowNavigator$
            },
            $BulletNavigatorOptions: {
                $Class: $JssorBulletNavigator$
            }
        };

        var jssor_1_slider = new $JssorSlider$("jssor_1", jssor_1_options);

        /*#region responsive code begin*/

        var MAX_WIDTH = 1280;

        function ScaleSlider() {
            var containerElement = jssor_1_slider.$Elmt.parentNode;
            var containerWidth = containerElement.clientWidth;

            if (containerWidth) {

                var expectedWidth = Math.min(MAX_WIDTH || containerWidth, containerWidth);

                jssor_1_slider.$ScaleWidth(expectedWidth);
            }
            else {
                window.setTimeout(ScaleSlider, 30);
            }
        }

        ScaleSlider();

        $Jssor$.$AddEvent(window, "load", ScaleSlider);
        $Jssor$.$AddEvent(window, "resize", ScaleSlider);
        $Jssor$.$AddEvent(window, "orientationchange", ScaleSlider);
        /*#endregion responsive code end*/
    };

    $('.loop').owlCarousel({
        center: false,
        items: 1,
        loop: false,
        margin: 10,
        responsive: {
            1100: {
                items: 3
            },
            1000: {
                items: 2
            },
            600: {
                items: 1
            },
            500: {
                items: 1
            },
            400: {
                items: 1
            },
            300: {
                items: 1
            },
            200: {
                items: 1
            },
            100: {
                items: 1
            }

        }
    });

    var owl = $('.owl-carousel');
    owl.owlCarousel({
        margin: 15,
        items: 5,
        loop: false,
        responsive: {
            1280: {
                items: 4
            },
            1050: {
                items: 3
            },
            1000: {
                items: 2
            },
            600: {
                items: 2
            },
            100: {
                items: 1
            }


        }
    })


});

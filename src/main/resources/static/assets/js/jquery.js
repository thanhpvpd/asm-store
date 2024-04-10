jQuery(document).ready(function () {
    // carosul slider
    jssor_1_slider_init();
    // menu-------------------------------------------------
    jQuery(".Ciseco-menu-show").click(function () {
        jQuery(".Ciseco-index").slideToggle();
    });
    jQuery(".Ciseco-menu-hide").click(function () {
        jQuery(".Ciseco-index").fadeOut();
    });
    // product----------------------------------
    jQuery(".love").click(function () {
        jQuery(this).toggleClass("heard");
    });

    jQuery(".color-product").click(function () {
        jQuery(this).toggleClass("color-product-border");
    });
    jQuery(".color-product-details").click(function () {
        jQuery(this).toggleClass("color-product-details-border");
    });





    $('.border-cacio').click(function () {
        $(this).css({ "border": "2px red solid", "border-radius": "10px" })
    });


});

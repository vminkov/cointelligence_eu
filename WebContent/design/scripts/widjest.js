
$(function () {
    $("#accordion").accordion();
});

$(function () {
    // TODO refactor into a widget and get rid of these plugin methods
    $.fn.left = function (using) {
        return this.position({
            my: "right middle",
            at: "left+25 middle",
            of: "#container",
            collision: "none",
            using: using
        });
    };
    $.fn.right = function (using) {
        return this.position({
            my: "left middle",
            at: "right-25 middle",
            of: "#container",
            collision: "none",
            using: using
        });
    };
    $.fn.center = function (using) {
        return this.position({
            my: "center middle",
            at: "center middle",
            of: "#container",
            using: using
        });
    };

    $(".imglider:eq(0)").left();
    $(".imglider:eq(1)").center();
    $(".imglider:eq(2)").right();

    function animate(to) {
        $(this).stop(true, false).animate(to);
    }
    function next(event) {
        event.preventDefault();
        $(".imglider:eq(2)").center(animate);
        $(".imglider:eq(1)").left(animate)
        $(".imglider:eq(0)").right().appendTo("#container");
    }
    function previous(event) {
        event.preventDefault();
        $(".imglider:eq(0)").center(animate);
        $(".imglider:eq(1)").right(animate);
        $(".imglider:eq(2)").left().prependTo("#container");
    }
    $("#previous").click(previous);
    $("#next").click(next);

    $(".imglider").click(function (event) {
        $(".imglider").index(this) === 0 ? previous(event) : next(event);
    });

    $(window).resize(function () {
        $(".imglider:eq(0)").left(animate);
        $(".imglider:eq(1)").center(animate);
        $(".imglider:eq(2)").right(animate);
    });
});

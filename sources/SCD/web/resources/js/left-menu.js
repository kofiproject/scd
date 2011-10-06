$(document).ready(function() {
    $('ul#my-menu ul').each(function(i) { // Check each submenu:

        $(this).hide().prev().removeClass('expanded').addClass('collapsed'); // Hide it

        $(this).prev().addClass('collapsible').click(function() { // Attach an event listener
            var this_i = $('ul#my-menu ul').index($(this).next()); // The index of the submenu of the clicked link
            if ($(this).next().css('display') == 'none') {
                $(this).next().slideDown(200, function () { // Show submenu:
                    $(this).prev().removeClass('collapsed').addClass('expanded');
                });
            } else {
                $(this).next().slideUp(200, function () { // Hide submenu:
                    $(this).prev().removeClass('expanded').addClass('collapsed');
                    $(this).find('ul').each(function() {
                        $(this).hide().prev().removeClass('expanded').addClass('collapsed');
                    });
                });
            }
            return false; // Prohibit the browser to follow the link address
        });
    });
});

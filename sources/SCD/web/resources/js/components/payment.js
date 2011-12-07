$(document).ready(function() {
    jQuery('[id $= paymentSum]').live('keyup', function () {
        var limitVal = parseInt(jQuery('[id $= arrearSum]').html());
        var curr = jQuery(this);
        var btnEl = jQuery('[id $= paymentPaybtn]');
        if (curr.val() > limitVal) {
            curr.addClass('errorMsg');
            btnEl.attr('disabled', 'disabled');
        } else {
            curr.removeClass('errorMsg');
            btnEl.attr('disabled', '');
        }
    });
});

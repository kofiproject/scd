$(document).ready(function() {
    jQuery('[id $= paymentSum]').live('keyup', function () {
        var limitVal = parseInt(jQuery('[id $= arrearSum]').html());
        var curr = jQuery(this);
        var btnEl = jQuery('[id $= paymentPaybtn]');
        var currVal = parseInt(curr.val());
        if (!currVal || currVal > limitVal || currVal <= 0) {
            curr.addClass('errorMsg');
            btnEl.attr('disabled', 'disabled');
        } else {
            curr.removeClass('errorMsg');
            btnEl.attr('disabled', '');
        }
    });
});

$(document).ready(function() {
    jQuery('.numeric').keypress(function (event) {
        var controlKeys = [0, 8, 9, 13, 35, 36, 37, 39];
        // IE doesn't support indexOf
        var isControlKey = controlKeys.join(",").match(new RegExp(event.which));

        if (isControlKey) {
            return;
        }
        if (this.value.length > 18 && (event.which)) {
            event.preventDefault();
            return false;
        }

        if(event.which > 57 || event.which < 48) {
            event.preventDefault();
            return false;
        }
    });
});

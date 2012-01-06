$(document).ready(function() {
    jQuery('.numeric').live('keypress', function (event) {
        // Allow only backspace and delete
        var controlKeys = [0, 8, 9, 13, 35, 36, 37, 39];
        // IE doesn't support indexOf
        var isControlKey = controlKeys.join(",").match(new RegExp(event.which));

        if (isControlKey) {
            return;
        }

        var currVal = $(this).val();

        if (currVal.length > 15) {
            return false;
        }

        if (event.which == 46) {
            if (currVal.length == 0) {
                return false;
            }
            var existDot = currVal.match(new RegExp(/\./g));
            if (existDot) {
                return false;
            }
        }

        else {
            if (event.which > 57 || event.which < 48) {
                event.preventDefault();
                return false;
            }

        }
        /*

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

         if (event.which > 57 || event.which < 48) {
         event.preventDefault();
         return false;
         }
         */
    });


    jQuery('.word').live('keypress', function (event) {
        // Allow only backspace and delete
        if (event.which <= 57 && event.which >= 48) {
            event.preventDefault();
            return false;
        }
    });
});

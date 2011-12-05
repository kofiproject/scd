$(document).ready(function() {
    jQuery('.numeric').live('keypress', function (event) {
// Allow only backspace and delete
        if (event.which == 46 || event.which == 8) {
            // let it happen, don't do anything
        }
        else {
            // Ensure that it is a number and stop the keypress
            if ((event.which < 48 || event.which > 57) && (event.which < 96 || event.which > 105 )) {
                event.preventDefault();
            }
            if(this.value.length> 8) {
                event.preventDefault();
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
});

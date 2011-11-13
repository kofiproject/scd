if (typeof by == "undefined") {
    by = {};
}

if (!by.scd) {
    by.scd = {};
}

by.scd.textArea = '.rejectDescription';
by.scd.msgId = '.errorMsg';

by.scd.check = function() {
    var text = $.trim($(by.scd.textArea).val());
    if(text) {
        $(by.scd.msgId).addClass('invisible');
        return true;
    }

    $(by.scd.msgId).removeClass('invisible');
    return false;
};





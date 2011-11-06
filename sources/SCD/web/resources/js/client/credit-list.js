if (typeof by == "undefined") {
    by = {};
}

if (!by.scd) {
    by.scd = {};
}

by.scd.formId = 'credit-list-form';
by.scd.creditLinkSelector = '.show-description';
by.scd.creditDescrSelector = '.credit-description-content-wrapper';

$(document).ready(function() {
    $(by.scd.creditLinkSelector).click(function(e) {
        var panelEl = $(e.target).next(by.scd.creditDescrSelector);
        panelEl.slideToggle('slow');
        panelEl.toggleClass('invisible');
    });
});

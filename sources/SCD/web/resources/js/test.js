if (typeof by == "undefined") {
    var by = {};
}

if (typeof by.kofi == "undefined") {
    by.kofi = {};
}


by.kofi.showAlert = function() {
    alert(location.href);
}

jQuery(document).ready(by.kofi.showAlert);
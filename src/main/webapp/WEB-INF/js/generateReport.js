function generateReport() {
    var url = "/admin/generateReport";
    var formData = {
        format: $('#format').val(),
        currency: $('#currency').val(),
        dateFrom: $('#dateFrom').val(),
        dateTo: $('#dateTo').val(),
        emailTo: $('#emailTo').val()
    }
    $.post(url, formData);
}


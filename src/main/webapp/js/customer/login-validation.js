document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('cpf').addEventListener('input', function(event) {
        event.target.value = maskCPF(event.target.value);
    });

    document.querySelector('#login-form').addEventListener('submit', function(event) {
        var cpfInput = document.querySelector('#cpf');
        cpfInput.value = removerFormatacao(cpfInput.value);
    });
});

function maskCPF(value) {
    value = value.replace(/\D/g, '');
    if (value.length <= 11) {
        value = value.replace(/(\d{3})(\d)/, '$1.$2');
        value = value.replace(/(\d{3})(\d)/, '$1.$2');
        value = value.replace(/(\d{3})(\d{1,2})$/, '$1-$2');
    }
    return value;
}

function removerFormatacao(value) {
    return value.replace(/\D/g, '');
}
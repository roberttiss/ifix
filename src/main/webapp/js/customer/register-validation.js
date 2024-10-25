document.addEventListener('DOMContentLoaded', function() {

    document.getElementById('cpf').addEventListener('input', function(event) {
        event.target.value = maskCPF(event.target.value);
    });

    document.getElementById('phone').addEventListener('input', function(event) {
        event.target.value = maskPhone(event.target.value);
    });

    document.querySelector('#customer-form').addEventListener('submit', function(event) {
        const cpfInput = document.querySelector('#cpf');
        const phoneInput = document.querySelector('#phone');
        const cep = document.querySelector('#zipCode');

        cpfInput.value = removerFormatacao(cpfInput.value);
        phoneInput.value = removerFormatacao(phoneInput.value);
        cep.value = removerFormatacao(cep.value);
    });

    document.getElementById('customer-form').addEventListener('submit', function(event) {
        if (!this.checkValidity()) {
            event.preventDefault();
            event.stopPropagation();
        }
        this.classList.add('was-validated');
    });

    document.getElementById('continue-button').addEventListener('click', function(event) {
        validateForm(event);
    });

    document.getElementById('register-button').addEventListener('click', function(event) {
        validateForm(event);
    });
});

function validateTrimmedInputs() {
    const inputs = document.querySelectorAll('#customer-form input[required]');
    for (let input of inputs) {
        if (input.value.trim().length === 0) {
            input.setCustomValidity('Este campo não pode estar vazio.');
            return false;
        } else {
            input.setCustomValidity('');
        }
    }
    return true;
}

function maskCPF(value) {
    value = value.replace(/\D/g, '');
    if (value.length <= 11) {
        value = value.replace(/(\d{3})(\d)/, '$1.$2');
        value = value.replace(/(\d{3})(\d)/, '$1.$2');
        value = value.replace(/(\d{3})(\d{1,2})$/, '$1-$2');
    }
    return value;
}

function maskPhone(value) {
    value = value.replace(/\D/g, '');
    if (value.length <= 11) {
        value = value.replace(/(\d{2})(\d)/, '($1) $2');
        value = value.replace(/(\d{5})(\d)/, '$1-$2');
    }
    return value;
}

function removerFormatacao(value) {
    return value.replace(/\D/g, '');
}
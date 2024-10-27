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

    document.getElementById('continue-button').addEventListener('click', function(event) {
        if (!validatePersonalData()) {
            event.preventDefault();
            event.stopPropagation();
        } else {
            showAddressFields();
        }
    });

    document.getElementById('register-button').addEventListener('click', function(event) {
        if (!validateAddress()) {
            event.preventDefault();
            event.stopPropagation();
        } else {
            document.getElementById('customer-form').submit();
        }
    });
});

function validatePersonalData(){
    const { value: name } = document.getElementById('name');
    const { value: cpf } = document.getElementById('cpf');
    const { value: phone } = document.getElementById('phone');
    const { value: email } = document.getElementById('email');

    const trimmedName = name.trim();
    const trimmedCpf = cpf.trim();
    const trimmedPhone = phone.trim();
    const trimmedEmail = email.trim();

    if (name.length < 3 || cpf.length < 11 || phone.length < 10 || email.length < 10) {
        alert("Nome deve ter no mínimo 3 caracteres. CPF, telefone e e-mail não podem ser vazios.");
        return false;
    }
    return true;
}

function validateAddress() {
    const { value: zipCode } = document.getElementById('zipCode');
    const { value: street } = document.getElementById('street');
    const { value: number } = document.getElementById('number');
    const { value: neighborhood } = document.getElementById('neighborhood');
    const { value: city } = document.getElementById('city');
    const { value: state } = document.getElementById('state');

    const trimmedZipCode = zipCode.trim();
    const trimmedStreet = street.trim();
    const trimmedNumber = number.trim();
    const trimmedNeighborhood = neighborhood.trim();
    const trimmedCity = city.trim();
    const trimmedState = state.trim();

    if (trimmedZipCode.length < 8 || trimmedStreet.length < 3 || trimmedNumber.length < 1 || trimmedNeighborhood.length < 3 || trimmedCity.length < 3 || trimmedState.length < 2) {
        alert("CEP deve ter no mínimo 8 caracteres. Rua, número, bairro, cidade e estado não podem ser vazios.");
        return false;
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
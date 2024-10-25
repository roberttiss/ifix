document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('continue-button').addEventListener('click', showAddressFields);
    document.getElementById('zipCode').addEventListener('input', function(event) {
        event.target.value = maskCEP(event.target.value);
    });
});

function maskCEP(value) {
    value = value.replace(/\D/g, '');
    if (value.length <= 9) {
        value = value.replace(/(\d{5})(\d)/, '$1-$2');
    }
    return value;
}

function showAddressFields() {
    document.getElementById('registerSection').style.display = 'none';
    document.getElementById('streetSection').style.display = 'block';
}

document.getElementById('zipCode').addEventListener('input', function() {
    if (this.value.length === 9) {
        activeAddress();
    }
});

async function activeAddress() {
    const cep = document.getElementById('zipCode').value;

    if (cep.length === 9) {
        const response = await fetch(`https://viacep.com.br/ws/${cep}/json/`);
        const data = await response.json();

        if (data.erro) {
            alert('CEP não encontrado');
            return;
        }

        document.getElementById('street').value = data.logradouro;
        document.getElementById('neighborhood').value = data.bairro;
        document.getElementById('city').value = data.localidade;
        document.getElementById('state').value = data.uf;
        document.getElementById('complement').disabled = false;

        document.getElementById('number').focus();
        document.getElementById('nextBtn').style.display = 'none';
    } else {
        alert('CEP inválido');
    }
}



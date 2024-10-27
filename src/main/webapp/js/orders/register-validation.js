document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById("createOrder");

    form.addEventListener("submit", function(event) {
        const descriptionInput = document.getElementById('description');
        const observationInput = document.getElementById('observation');
        const priceInput = document.getElementById('price');
        const paymentMethodInput = document.getElementById('payment');

        const description = descriptionInput.value.trim();
        const observation = observationInput.value.trim();
        const price = priceInput.value.trim();
        const paymentMethod = paymentMethodInput.value.trim();


        if (description.length < 10 || observation.length < 10 || price.length < 1 || paymentMethod.length < 1) {
            event.preventDefault();
            alert("Descrição e observação devem ter no mínimo 10 caracteres não vazios. Preço e método de pagamento não podem ser vazios.");
        }
    });
});
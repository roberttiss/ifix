document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById("updateOrder");

    form.addEventListener("submit", function(event) {
        const description = document.getElementById("description").value.trim();
        const observation = document.getElementById("observation").value.trim();

        if (description.length < 10 || observation.length < 10) {
            event.preventDefault();
            alert("Descrição e observação devem ter no mínimo 10 caracteres não vazios.");
        }
    });
});
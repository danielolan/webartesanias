document.getElementById('productForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Previene el comportamiento por defecto del formulario

    let isValid = true;
    const inputs = document.querySelectorAll('#productForm .form-control');

    inputs.forEach(input => {
        const errorSpan = input.nextElementSibling;
        if (!input.value.trim()) {
            input.style.borderColor = "red";
            errorSpan.style.display = "inline";
            isValid = false;
        } else {
            input.style.borderColor = "";
            errorSpan.style.display = "none";
        }
    });

    if (isValid) {
        const formData = new FormData(event.target);
        // No establezcas 'Content-Type' manualmente para 'multipart/form-data'
        fetch('http://localhost:8081/api/products/add', { // Actualiza esta URL
            method: 'POST',
            body: formData
        })
        .then(response => {
            if (!response.ok) {
                return response.text().then(text => { throw new Error(text) });
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            window.location.href = './index.html';
        })
        .catch(error => {
            console.error('Detailed error:', error);
        });
    }
});

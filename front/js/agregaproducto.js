document.getElementById('productForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Previene el comportamiento por defecto del formulario
    
    const formData = new FormData(event.target);
    const data = {};
    formData.forEach((value, key) => {
        data[key] = value;
    });

    fetch('http://localhost:8081/api/products', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        // Asegúrate de que la respuesta es exitosa
        if (response.ok) {
            return response.json();
        } else {
            // Maneja la respuesta fallida
            throw new Error('Something went wrong on api server!');
        }
    })
    .then(data => {
        console.log(data);
        // Redirección a una nueva página, reemplaza 'path/to/your/success-page.html' con tu URL
        window.location.href = './index.html';
    })
    .catch(error => {
        console.error('Error:', error);
        // Aquí puedes manejar errores o mostrar alguna alerta al usuario
    });
});

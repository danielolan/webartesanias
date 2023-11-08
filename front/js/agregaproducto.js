document.getElementById('productForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Previene el comportamiento por defecto del formulario

    const formData = new FormData(event.target);

    // No establezcas 'Content-Type' manualmente para 'multipart/form-data'
    fetch('http://localhost:8081/api/products/upload', { // Actualiza esta URL
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

});

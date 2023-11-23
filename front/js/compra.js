window.onload = function() {
    const productosElegidos = JSON.parse(localStorage.getItem('carrito'));

    if (productosElegidos && productosElegidos.length > 0) {
        let totalCompra = productosElegidos.reduce((total, producto) => total + producto.purchase_total, 0);
        document.getElementById('purchase_total').value = totalCompra;

        let fechaHoy = new Date().toISOString().split('T')[0];
        document.getElementById('purchase_date').value = fechaHoy;
    }
};
document.querySelector('.button-enviar').addEventListener('click', function(e) {
    e.preventDefault();

    const productosElegidos = JSON.parse(localStorage.getItem('carrito'));
    if (productosElegidos && productosElegidos.length > 0) {
        productosElegidos.forEach(item => {
            const productId = item.product.id;
            const quantityPurchased = item.amount_product;
            actualizarInventario(productId, quantityPurchased);
        });
    }

    // Aquí iría el código para finalizar la compra
});

function actualizarInventario(productId, quantityPurchased) {
    fetch(`http://localhost:8081/api/products/${productId}`, {
        method: 'PATCH', // Cambiar a PATCH
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ product_inventory: quantityPurchased })
    })
    .then(response => response.json())
    .then(data => console.log('Inventario actualizado para el producto:', productId))
    .catch(error => console.error('Error:', error));
}



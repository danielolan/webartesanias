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
        // Realizar todas las actualizaciones de inventario
        Promise.all(productosElegidos.map(item => {
            return actualizarInventario(item.product.id, item.amount_product);
        })).then(() => {
            // Todas las actualizaciones de inventario han sido exitosas
            console.log("Todas las actualizaciones de inventario han sido exitosas");
            // Aquí llamar a una función para eliminar todos los elementos de la tabla purchase
            eliminarTablaPurchase();
        }).catch(error => {
            console.error("Error en actualización de inventario:", error);
        });
    }

    // Aquí iría el código para finalizar la compra
});

function actualizarInventario(productId, quantityPurchased) {
    return fetch(`http://localhost:8081/api/products/${productId}`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ product_inventory: quantityPurchased })
    })
    .then(response => response.json())
    .then(data => console.log('Inventario actualizado para el producto:', productId));
}

function eliminarTablaPurchase() {
    // Realizar solicitud para eliminar todos los registros en la tabla purchase
    fetch(`http://localhost:8081/api/purchase/all`, {
        method: 'DELETE' // Asumiendo que tienes un endpoint DELETE configurado en tu backend
    })
    .then(response => {
        if (response.ok) {
            console.log("Todos los registros de purchase han sido eliminados");
            // Aquí podrías realizar acciones adicionales como vaciar el carrito en el frontend
        } else {
            console.error("Error al intentar eliminar registros de purchase");
        }
    })
    .catch(error => console.error("Error al eliminar tabla purchase:", error));
}
function initMap() {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(function(position) {
        var pos = {
          lat: position.coords.latitude,
          lng: position.coords.longitude
        };
  
        var map = new google.maps.Map(document.getElementById('map'), {
          center: pos,
          zoom: 15
        });
  
        var marker = new google.maps.Marker({position: pos, map: map});
      }, function() {
        handleLocationError(true, map, map.getCenter());
      });
    } else {
      // El navegador no soporta Geolocalización
      handleLocationError(false, map, map.getCenter());
    }
  }
  


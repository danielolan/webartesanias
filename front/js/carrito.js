const contenedorProductos = document.querySelector("#contenedor-carrito");
let productosElegidosGlobal = [];
fetch('http://localhost:8081/api/purchase')
    .then(response => response.json())
    .then(data => {
        cargarProductosback(data)
        console.log("me esta llegando", data);
    })
    .catch(error => console.error('Error fetching data:', error));

function cargarProductosback(productosElegidos) {
    productosElegidosGlobal = productosElegidos;
    contenedorProductos.innerHTML = "";
    let totalCompra = 0;
    productosElegidos.forEach(producto => {
        // Acceder a los detalles del producto
        const productDetails = producto.product;

        const div = document.createElement("div");
        div.classList.add("carrito-producto");
        div.innerHTML = `
        <img class="carrito-producto-imagen" src="${productDetails.product_image}" alt="${productDetails.productName}">
        <div class="carrito-producto-titulo">
            <small>Titulo</small>
            <h3>${productDetails.productName}</h3>

        </div>
        <div class="carrito-producto-cantidad">
            <small>Cantidad</small>
            <p>${producto.amount_product}</p>
        </div>
        <div class="carrito-producto-precio">
            <small>Precio</small>
            <p>$${producto.unit_price}</p>
        </div>
        <div class="carrito-producto-subtotal">
            <small>Subtotal</small>
            <p>$${producto.purchase_total}</p>
        </div>
        <button id="botonborrarproducto-${producto.purchase_id}" class="carrito-producto-eliminar"><i class="bi bi-trash-fill"></i></button>
        `;
        console.log("necesito esto ", producto.purchase_id)

        // Agregar evento de clic al div del producto

        // Agregar el botón de eliminar al div del producto
      // Agregar el botón de eliminar al div del producto
      const botonEliminar = div.querySelector(`#botonborrarproducto-${producto.purchase_id}`);
      botonEliminar.addEventListener('click', function(e) {
          e.stopPropagation(); // Para prevenir que el evento click se propague al div del producto
          eliminarProductoDelCarrito(producto.purchase_id);
      });
        totalCompra += producto.purchase_total;

        contenedorProductos.append(div);


    });

    document.getElementById('total').textContent = totalCompra;
}

function eliminarProductoDelCarrito(purchaseId) {
    console.log("borra esta ", purchaseId)
    fetch(`http://localhost:8081/api/purchase?id=${purchaseId}`, {
        method: 'DELETE',
    })
    .then(response => {
        if(response.ok) {
            // Vuelve a cargar la lista de productos
            fetch('http://localhost:8081/api/purchase')
              .then(response => response.json())
              .then(data => cargarProductosback(data))
              .catch(error => console.error('Error fetching data:', error));
        }
    })
    .catch(error => console.error('Error:', error));
}
document.querySelector('.carrito-acciones-vaciar').addEventListener('click', function() {
    fetch('http://localhost:8081/api/purchase/all', {
        method: 'DELETE'
    })
    .then(response => {
        if(response.ok) {
            // Vuelve a cargar la lista de productos o limpia el carrito en la interfaz de usuario
            cargarProductosback([]); // Suponiendo que cargarProductosback puede manejar una lista vacía
        }
    })
    .catch(error => console.error('Error:', error));
});

document.getElementById('botonComprarAhora').addEventListener('click', function() {
    // Guardar los productos seleccionados en localStorage antes de redirigir
    localStorage.setItem('carrito', JSON.stringify(productosElegidosGlobal));
console.log("tengo estos elegidos pa ", productosElegidosGlobal)
   window.location.href = './compra.html';
});




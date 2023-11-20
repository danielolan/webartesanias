const contenedorProductos = document.querySelector("#contenedor-carrito");

fetch('http://localhost:8081/api/purchase')
  .then(response => response.json())
  .then(data => {
    cargarProductosback(data)
    console.log("me esta llegando", data);
  })
  .catch(error => console.error('Error fetching data:', error));

  function cargarProductosback(productosElegidos) {
    contenedorProductos.innerHTML = "";
   
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
            <p>${producto.unit_price}</p>
        </div>
        <div class="carrito-producto-subtotal">
            <small>Subtotal</small>
            <p>${producto.purchase_total}</p>
        </div>
        <button class="carrito-producto-eliminar"><i class="bi bi-trash-fill"></i></button>
        `;

        // Agregar evento de clic al div del producto
        div.addEventListener('click', () => {
            window.location.href = `product.html?productId=${productDetails.id}`;
        });

        contenedorProductos.append(div);
    });
}



                        
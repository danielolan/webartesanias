// Obtener el ID del producto de la URL
const urlParams = new URLSearchParams(window.location.search);
const productId = urlParams.get('productId');
const contenedorProductos = document.querySelector("#producto-contain");
// Usar el productId para hacer una solicitud al backend y obtener los detalles del producto
fetch(`http://localhost:8081/api/products/${productId}`)
    .then(response => response.json())
    .then(product => {
        cargarProducto(product);
    })
    .catch(error => console.error('Error fetching product details:', error));

function cargarProducto(producto) {
    contenedorProductos.innerHTML = "";

    const div = document.createElement("div");
    div.classList.add("row");
    div.innerHTML = `
        <div class="col-md-6">
            <img src="${producto.product_image}" alt="Imagen del producto" class="img-fluid imagen-product">
        </div>
    
        <div class="col-md-6">
            <h3 class="mb-3">${producto.productName}</h3>
            <p><strong>Código EAN:</strong> ${producto.product_ean_code}</p>
            <p><strong>Marca:</strong> ${producto.product_brand}</p>
            <p><strong>Descripción:</strong> ${producto.product_description}</p>
            <p><strong>Inventario:</strong> ${producto.product_inventory} unidades</p>
            <p><strong>Precio:</strong> $${producto.product_price}</p>
            
            <div class="d-flex justify-content-center g-3">
                <button class="btn btn-primary button-enviar">Añadir al carrito</button>
            </div>
        </div>
    `;

    contenedorProductos.append(div);
}

// Obtener el ID del producto de la URL
const urlParams = new URLSearchParams(window.location.search);
const productId = urlParams.get('productId');
const contenedorProductos = document.querySelector("#producto-container");
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
            <div class="col-md-6 my-3">
                <img src="${producto.product_image}" alt="Imagen del producto" class="img-fluid imagen-product">
            </div>
        
            <div class="col-md-6 d-flex flex-column justify-content-center my-3">
                <h3 class="mb-3">${producto.productName}</h3>
                <p class="my-2"><strong>Código EAN:</strong> ${producto.product_ean_code}</p>
                <p class="my-2"><strong>Marca:</strong> ${producto.product_brand}</p>
                <p class="my-2"><strong>Descripción:</strong> ${producto.product_description}</p>
                <p class="unidades-disponibles my-2">${producto.product_inventory} unidades disponibles</p>
                <p class="precio-text my-2"><strong>Precio:</strong> $${producto.product_price}</p>
    
                <div class="d-flex align-items-center my-4">
                    <!-- Selector de unidades sin margen adicional -->
                    <select class="form-select" id="cantidadProducto">
                        ${Array.from({ length: producto.product_inventory }, (_, i) => 
                          `<option value="${i + 1}">${i + 1}</option>`
                        ).join('')}
                    </select>
                    <button class="btn btn-primary button-enviar ml-2">Añadir al carrito</button>
                </div>
            </div>
        `;
    
        contenedorProductos.append(div);
    }
    


    
// Este código debería ejecutarse una vez que `contenedorProductos` esté en el DOM.
document.addEventListener('DOMContentLoaded', () => {
    contenedorProductos.addEventListener('click', function(e) {
        if (e.target.classList.contains('button-enviar')) {
            const cantidadSeleccionada = document.getElementById('cantidadProducto').value;
            console.log('Cantidad seleccionada:', cantidadSeleccionada); // Aquí haces lo que necesitas con la cantidad
        }
    });
});

    
document.querySelector('.button-enviar').addEventListener('click', function() {
    // Obtener el comentario del usuario
    const userCommentTextarea = document.getElementById('userComment');
    const userComment = userCommentTextarea.value;

    // Obtener el product ID de la URL
    const urlParams = new URLSearchParams(window.location.search);
    const productId = urlParams.get('productId');

    // Crear el objeto con los datos a enviar
    const data = {
        description: userComment,
        product: {
            id: parseInt(productId)
        }
    };

    // Hacer la petición POST al backend
    fetch('http://localhost:8081/api/comments', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(data => {
        console.log('Comentario guardado:', data);

        // Limpiar el textarea si la petición fue exitosa
        userCommentTextarea.value = '';

        // Recargar los comentarios después de agregar uno nuevo
        return fetch(`http://localhost:8081/api/comments`);
    })
    .then(response => response.json())
    .then(comentarios => {
        cargarComentario(comentarios);
    })
    .catch(error => {
        console.error('Error:', error);
    });
});

const contenedorComentarios = document.querySelector("#contenedor-comentarios");

function cargarComentario(comentarios) {
    contenedorComentarios.innerHTML = "";
    const nuevoarreglo = comentarios.filter(com => {
        return +com.product.id == +productId;
    });

    nuevoarreglo.forEach(commentario => {
        const div = document.createElement("div");
        div.classList.add("contenedor-comment");
        div.innerHTML = `
        <img src="https://via.placeholder.com/64" alt="Imagen de usuario" class="mr-3 rounded-circle">
        <div class="media-body">
            <h5 class="mt-0">Nombre del usuario</h5>
            ${commentario.description}
            <h6>${commentario.createdAt}</h6>
        </div>
        `;

        contenedorComentarios.append(div);
    });
}

// Cargar los comentarios al cargar la página
fetch(`http://localhost:8081/api/comments`)
    .then(response => response.json())
    .then(comentarios => {
        cargarComentario(comentarios);
    })
    .catch(error => console.error('Error fetching product details:', error));

    
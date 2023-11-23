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
            <select class="form-select" id="cantidadProducto">
                ${Array.from({ length: producto.product_inventory }, (_, i) =>
                    `<option value="${i + 1}">${i + 1}</option>`
                ).join('')}
            </select>
            <button class="btn btn-primary button-enviar ml-2" ${producto.product_inventory === 0 ? 'disabled' : ''}>Añadir al carrito</button>
        </div>
    `;

    contenedorProductos.append(div);

    // Mostrar o ocultar el mensaje de no stock
    const mensajeNoStock = document.querySelector('.text-danger');
    if (producto.product_inventory === 0) {
        mensajeNoStock.style.display = 'block';
    } else {
        mensajeNoStock.style.display = 'none';
    }
}

function mostrarNotificacion(mensaje) {
    // Crear el div de la notificación
    const notificacion = document.createElement('div');
    notificacion.className = 'alert alert-success alert-dismissible fade show';
    notificacion.role = 'alert';
    notificacion.style.position = 'fixed';
    notificacion.style.top = '20px';
    notificacion.style.right = '20px';
    notificacion.style.zIndex = '1000';
    notificacion.textContent = mensaje;

    // Botón para cerrar la notificación
    const botonCerrar = document.createElement('button');
    botonCerrar.type = 'button';
    botonCerrar.className = 'close';
    botonCerrar.setAttribute('data-dismiss', 'alert');
    botonCerrar.innerHTML = '&times;';

    notificacion.appendChild(botonCerrar);

    // Añadir la notificación al body
    document.body.appendChild(notificacion);

    // Tiempo para que la notificación desaparezca automáticamente
    setTimeout(() => {
        $(notificacion).alert('close');
    }, 3000);
}




document.addEventListener('DOMContentLoaded', () => {
    contenedorProductos.addEventListener('click', function (e) {

        if (e.target.classList.contains('button-enviar')) {
            e.preventDefault(); // Prevenir la recarga por defecto del formulario
            const cantidadSeleccionada = document.getElementById('cantidadProducto').value;



            crearproductocarrito(productId, cantidadSeleccionada)
            console.log('aaaaaaaaaaaaaaa',cantidadSeleccionada)


            // Redirigir a index.html con los parámetros de consulta
            // window.location.href = `carrito.html?cantidad=${encodeURIComponent(cantidadSeleccionada)}&productId=${encodeURIComponent(productId)}`;
        }


    });
});


// Este código debería ejecutarse una vez que `contenedorProductos` esté en el DOM.


function crearproductocarrito(productId,cantidadSeleccionada) {

   

    fetch(`http://localhost:8081/api/products/${productId}`)
        .then(response => response.json())
        .then(productcarrito => {
            let totalproducto;
            totalproducto =  productcarrito.product_price * cantidadSeleccionada;
            console.log('Millso campeon',productcarrito.product_price)
            console.log('porque 7',cantidadSeleccionada)
            console.log('Millso campeon',totalproducto)


            // Crear el objeto con los datos
            let dataToSend = {
                unit_price: productcarrito.product_price,
                amount_product: cantidadSeleccionada,
                purchase_total: totalproducto, // Asumiendo que solo necesitas enviar el ID del usuario
                product: { id: productId } // Asumiendo que solo necesitas enviar el ID del producto
            };

            // Realizar la solicitud POST
            fetch('http://localhost:8081/api/purchase', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    // Otros encabezados si son necesarios
                },
                body: JSON.stringify(dataToSend)
            })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                mostrarNotificacion('Su producto se ha agregado al carrito con éxito');
            })
                .catch(error => console.error('Error:', error));










        })
        .catch(error => console.error('Error fetching product details:', error));


}
document.querySelector('.button-enviar').addEventListener('click', function () {
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



const contenedorProductos = document.querySelector("#contenedor-productos");
const botonesCategorias = document.querySelectorAll(".boton-categoria");
const tituloPrincipal = document.querySelector("#titulo-principal");

let currentPage = 1;
const itemsPerPage = 8;
let totalItems;
document.addEventListener('DOMContentLoaded', function() {
    actualizarCantidadCarrito();
});

fetch('http://localhost:8081/api/products')
    .then(response => response.json())
    .then(data => {
        productosElegidos = data;
        cargarProductosback(productosElegidos, currentPage);
        crearPaginacion(productosElegidos.length);
    })
    .catch(error => console.error('Error fetching data:', error));


    function cargarProductosback(productosElegidos, pagina) {
        const startIndex = (pagina - 1) * itemsPerPage;
        const endIndex = pagina * itemsPerPage;
        contenedorProductos.innerHTML = "";  // Limpiar el contenedor de productos
    
        // Utilizar slice para obtener solo los productos de la página actual
        const productosPaginaActual = productosElegidos.slice(startIndex, endIndex);
    
        console.log("Productos de la página actual:", productosPaginaActual);  // Log de los productos de la página actual
    
        productosPaginaActual.forEach(producto => {
            const div = document.createElement("div");
            div.classList.add("producto");
            div.innerHTML = `
                <img class="producto-imagen" src="${producto.product_image}" alt="${producto.productName}">
                <div class="producto-detalles">
                    <h3 class="producto-titulo">${producto.productName}</h3>
                    <p class="producto-precio">${producto.product_price}</p>
                    <button class="producto-agregar" id="${producto.id}">Agregar</button>
                </div>
            `;
    
            // Agregar evento de clic al div del producto para redireccionar
            div.addEventListener('click', () => {
                window.location.href = `product.html?productId=${producto.id}`;
            });
    
            // Evento para detectar si se mantiene presionado
            div.addEventListener('mousedown', (event) => {
                event.stopPropagation(); // Previene que el evento click también se dispare
        
                // Configura un temporizador para detectar la presión prolongada
                const pressTimer = window.setTimeout(() => {
                    mostrarModal(producto.id, div);
                }, 1000); // Tiempo en milisegundos que consideras "mantener oprimido"
        
                // Limpia el temporizador si se suelta el botón antes de tiempo
                div.addEventListener('mouseup', () => {
                    clearTimeout(pressTimer);
                });
                div.addEventListener('mouseleave', () => {
                    clearTimeout(pressTimer);
                });
            });
    
            contenedorProductos.append(div);  // Agregar el producto al contenedor de productos
        });
    }
    function eliminarProducto(productId, productDiv) {
        // Realiza una solicitud de eliminación al servidor
        fetch(`http://localhost:8081/api/products?id=${productId}`, {
            method: 'DELETE'
        })
        .then(response => {
            if(response.ok) {
                // Elimina el producto del DOM si la respuesta es exitosa
                productDiv.remove();
                console.log(`Producto con ID ${productId} eliminado.`);
            } else {
                // Manejo de errores en caso de que algo vaya mal con la respuesta
                console.error(`No se pudo eliminar el producto con ID ${productId}.`, response);
            }
        })
        .catch(error => {
            // Manejo de errores de la red (problemas de conexión, etc.)
            console.error('Hubo un problema con la petición fetch:', error);
        });
    }
    
    
 
    function mostrarModal(productId, productDiv) {
        const modal = document.getElementById("modalConfirmacionEliminar");
        const confirmDeleteBtn = document.getElementById("confirmDelete");
        const cancelDeleteBtn = document.getElementById("cancelDelete");
        const closeBtn = modal.querySelector(".close");
    
        // Muestra el modal
        modal.style.display = "block";
    
        // Si se presiona "Eliminar"
        confirmDeleteBtn.onclick = function() {
            eliminarProducto(productId, productDiv);
            modal.style.display = "none";
        };
    
        // Si se presiona "Cancelar" o "X"
        cancelDeleteBtn.onclick = closeBtn.onclick = function() {
            modal.style.display = "none";
        };
    
        // Si se hace clic en cualquier lugar fuera del modal, se cierra
        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        };
    }
    
    function crearPaginacion(totalProductos) {
        const totalPages = Math.ceil(totalProductos / itemsPerPage);
        const paginationDiv = document.createElement('div');
        paginationDiv.classList.add('pagination');
        for (let i = 1; i <= totalPages; i++) {
            const pageButton = document.createElement('button');
            pageButton.innerText = i;
            pageButton.addEventListener('click', () => {
                currentPage = i;
                cargarProductosback(productosElegidos, currentPage);
            });
            paginationDiv.append(pageButton);
        }
        
        // Buscar el elemento main
        const mainElement = document.querySelector('main');
    
        // Asegúrate de que el div de paginación se coloque solo una vez
        const existingPaginationDiv = mainElement.querySelector('.pagination');
        if (existingPaginationDiv) {
            mainElement.replaceChild(paginationDiv, existingPaginationDiv);
        } else {
            mainElement.append(paginationDiv);
        }
    }
    



/*[
    {
        "id": 1,
        "productName": "pantalon",
        "product_ean_code": 24546456465,
        "product_brand": "hombre",
        "product_description": "ropa",
        "product_inventory": 50,
        "product_price": 2000,
        "product_image"
        "created_at": "2023-10-02T04:19:43.369+00:00",
        "update_at": "2023-10-02T04:19:43.370+00:00"
    }

*/
/*
function cargarProductos(productosElegidos){ 
    contenedorProductos.innerHTML= ""

    productosElegidos.forEach(producto => {
        const div = document.createElement("div");
        div.classList.add("producto");
        div.innerHTML= `
                    <img class="producto-imagen" src="${producto.imagen}" alt="${producto.titulo}">
                    <div class="producto-detalles">
                        <h3 class="producto-titulo">${producto.titulo}</h3>
                        <p class="producto-precio">${producto.precio}</p>
                        <button class="producto-agregar" id="${producto.id}">Agregar</button>
                    </div>
        ` ;

        contenedorProductos.append(div);
    })
}
cargarProductos(productos);


*/
const divBuscador = document.querySelector(".fondo");
botonesCategorias.forEach(boton => {
    boton.addEventListener("click", (e) => {
        botonesCategorias.forEach(boton => boton.classList.remove("active"))
        e.currentTarget.classList.add("active");
        
        if(e.currentTarget.id != "Todos") {
            const productoCategoria = productos.find(producto => producto.categoria.id === e.currentTarget.id);
            tituloPrincipal.innerText = productoCategoria.categoria.nombre;
            const productosBoton = productos.filter(producto => producto.categoria.id === e.currentTarget.id);
            cargarProductos(productosBoton);

            // Ocultar el div del buscador
            divBuscador.style.display = "none";
        } else {
            tituloPrincipal.innerText = "Todos los productos";
            cargarProductos(productos);

            // Mostrar el div del buscador
            divBuscador.style.display = "flex";
        }
    })
});
const inputBuscar = document.getElementById('input-buscar');
// Asegúrate de que este ID exista en tu HTML

// Evento para detectar cuando el usuario escribe en el campo de búsqueda
inputBuscar.addEventListener('input', function() {
    const textoBusqueda = inputBuscar.value.toLowerCase(); // Convertir el texto a minúsculas para una búsqueda no sensible a mayúsculas/minúsculas

    fetch('http://localhost:8081/api/products')
        .then(response => response.json())
        .then(data => {
            // Filtrar los productos basados en el texto de búsqueda y los campos mencionados
            const productosFiltrados = data.filter(producto => 
                producto.productName.toLowerCase().includes(textoBusqueda) ||
                producto.product_ean_code.toString().includes(textoBusqueda) ||
                producto.id.toString().includes(textoBusqueda) ||
                producto.product_brand.toLowerCase().includes(textoBusqueda) ||
                producto.product_description.toLowerCase().includes(textoBusqueda) ||
                producto.product_inventory.toString().includes(textoBusqueda) ||
                producto.product_price.toString().includes(textoBusqueda)
            );
            cargarProductosback(productosFiltrados);
        })
        .catch(error => console.error('Error fetching data:', error));
});

// Función para actualizar la cantidad de elementos en el carrito
function actualizarCantidadCarrito() {
    fetch('http://localhost:8081/api/purchase')
        .then(response => response.json())
        .then(data => {
            // Actualizar el elemento con la cantidad de elementos en el carrito
            const cantidadCarrito = data.length; // Suponiendo que data es un array de elementos purchase
            document.querySelector('.numerito').textContent = cantidadCarrito;
        })
        .catch(error => console.error('Error fetching data:', error));
}

// Llamar a la función cuando se carga la página
document.addEventListener('DOMContentLoaded', actualizarCantidadCarrito);
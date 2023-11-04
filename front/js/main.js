
const contenedorProductos = document.querySelector("#contenedor-productos");
const botonesCategorias = document.querySelectorAll(".boton-categoria");
const tituloPrincipal = document.querySelector("#titulo-principal");

let currentPage = 1;
const itemsPerPage = 8;
let totalItems;

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
    
         // Cambia esto a mousedown y mouseup para detectar si se mantiene presionado
         div.addEventListener('mousedown', (event) => {
            event.stopPropagation(); // Previene que el evento click también se dispare

            // Configura un temporizador para detectar la presión prolongada
            const pressTimer = window.setTimeout(() => {
                if (window.confirm("¿Desea eliminar el producto?")) {
                    eliminarProducto(producto.id, div);
                }
            }, 1000); // Tiempo en milisegundos que consideras "mantener oprimido", por ejemplo 1 segundo

            // Limpia el temporizador si se suelta el botón antes de tiempo
            div.addEventListener('mouseup', () => {
                clearTimeout(pressTimer);
            });
            div.addEventListener('mouseleave', () => {
                clearTimeout(pressTimer);
            });
        });
    
        console.log("me esta llegando", productosPaginaActual);  // Log de los productos de la página actual
    
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
    
            // Agregar evento de clic al div del producto
            div.addEventListener('click', () => {
                window.location.href = `product.html?productId=${producto.id}`;
            });
    
            contenedorProductos.append(div);  // Agregar el producto al contenedor de productos
        });
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

function eliminarProducto(productId, productDiv) {
    fetch(`http://localhost:8081/api/products/${productId}`, {
        method: 'DELETE'
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al eliminar el producto');
        }
        return response.json();
    })
    .then(() => {
        productDiv.remove(); // Elimina el producto del DOM
        console.log('Producto eliminado correctamente');
    })
    .catch(error => {
        console.error('Error eliminando producto:', error);
    });
}
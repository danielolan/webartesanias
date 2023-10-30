// PRODUCTOS
const productos = [
    // Abrigos
    {
        id: "abrigo-01",
        titulo: "Abrigo 01",
        imagen: "./img/abrigos/01.jpg",
        categoria: {
            nombre: "Abrigos",
            id: "abrigos"
        },
        precio: 1000
    },
    {
        id: "abrigo-02",
        titulo: "Abrigo 02",
        imagen: "./img/abrigos/02.jpg",
        categoria: {
            nombre: "Abrigos",
            id: "abrigos"
        },
        precio: 1000
    },
    {
        id: "abrigo-03",
        titulo: "Abrigo 03",
        imagen: "./img/abrigos/03.jpg",
        categoria: {
            nombre: "Abrigos",
            id: "abrigos"
        },
        precio: 1000
    },
    {
        id: "abrigo-04",
        titulo: "Abrigo 04",
        imagen: "./img/abrigos/04.jpg",
        categoria: {
            nombre: "Abrigos",
            id: "abrigos"
        },
        precio: 1000
    },
    {
        id: "abrigo-05",
        titulo: "Abrigo 05",
        imagen: "./img/abrigos/05.jpg",
        categoria: {
            nombre: "Abrigos",
            id: "abrigos"
        },
        precio: 1000
    },
    // Camisetas
    {
        id: "camiseta-01",
        titulo: "Camiseta 01",
        imagen: "./img/camisetas/01.jpg",
        categoria: {
            nombre: "Camisetas",
            id: "camisetas"
        },
        precio: 1000
    },
    {
        id: "camiseta-02",
        titulo: "Camiseta 02",
        imagen: "./img/camisetas/02.jpg",
        categoria: {
            nombre: "Camisetas",
            id: "camisetas"
        },
        precio: 1000
    },
    {
        id: "camiseta-03",
        titulo: "Camiseta 03",
        imagen: "./img/camisetas/03.jpg",
        categoria: {
            nombre: "Camisetas",
            id: "camisetas"
        },
        precio: 1000
    },
    {
        id: "camiseta-04",
        titulo: "Camiseta 04",
        imagen: "./img/camisetas/04.jpg",
        categoria: {
            nombre: "Camisetas",
            id: "camisetas"
        },
        precio: 1000
    },
    {
        id: "camiseta-05",
        titulo: "Camiseta 05",
        imagen: "./img/camisetas/05.jpg",
        categoria: {
            nombre: "Camisetas",
            id: "camisetas"
        },
        precio: 1000
    },
    {
        id: "camiseta-06",
        titulo: "Camiseta 06",
        imagen: "./img/camisetas/06.jpg",
        categoria: {
            nombre: "Camisetas",
            id: "camisetas"
        },
        precio: 1000
    },
    {
        id: "camiseta-07",
        titulo: "Camiseta 07",
        imagen: "./img/camisetas/07.jpg",
        categoria: {
            nombre: "Camisetas",
            id: "camisetas"
        },
        precio: 1000
    },
    {
        id: "camiseta-08",
        titulo: "Camiseta 08",
        imagen: "./img/camisetas/08.jpg",
        categoria: {
            nombre: "Camisetas",
            id: "camisetas"
        },
        precio: 1000
    },
    // Pantalones
    {
        id: "pantalon-01",
        titulo: "Pantalón 01",
        imagen: "./img/pantalones/01.jpg",
        categoria: {
            nombre: "Pantalones",
            id: "pantalones"
        },
        precio: 1000
    },
    {
        id: "pantalon-02",
        titulo: "Pantalón 02",
        imagen: "./img/pantalones/02.jpg",
        categoria: {
            nombre: "Pantalones",
            id: "pantalones"
        },
        precio: 1000
    },
    {
        id: "pantalon-03",
        titulo: "Pantalón 03",
        imagen: "./img/pantalones/03.jpg",
        categoria: {
            nombre: "Pantalones",
            id: "pantalones"
        },
        precio: 1000
    },
    {
        id: "pantalon-04",
        titulo: "Pantalón 04",
        imagen: "./img/pantalones/04.jpg",
        categoria: {
            nombre: "Pantalones",
            id: "pantalones"
        },
        precio: 1000
    },
    {
        id: "pantalon-05",
        titulo: "Pantalón 05",
        imagen: "./img/pantalones/05.jpg",
        categoria: {
            nombre: "Pantalones",
            id: "pantalones"
        },
        precio: 1000
    }
];
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
    
        // Utilizar slice para obtener solo los productos de la página actual
        const productosPaginaActual = productosElegidos.slice(startIndex, endIndex);
    
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


document.addEventListener('DOMContentLoaded', (event) => {
    const urlParams = new URLSearchParams(window.location.search);
    const cantidadSeleccionada = urlParams.get('cantidad');
    const productId = urlParams.get('productId');

    console.log('Cantidad seleccionada recibida:', cantidadSeleccionada);
    console.log('Product ID recibido:', productId);
    // Hacer algo con cantidadSeleccionada y productId...
});
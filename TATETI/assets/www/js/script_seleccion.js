/**CODIGO JAVASCRIPT**/

/**
 *@author Pablo taddei
 * 
 * SCRIPT DE LA PANTALLA DE SELECCION DE FICHAS. 
 */
$(function(){
	
	 //TODO: EVENTO CLICK DE LAS FICHAS, AL SER SELECCIONADAS SE EJECUTA ESTE EVENTO
	 //TODO: LO QUE SE HACE AQUI ES DETERMINAR QUE FICHA A SELECCIONADO EL JUGADOR Y PASARSELA A
	 //TODO: LA SIGUIENTE VENTANA.
	$('#containerSeleccion').on('click','button',function(){
		var tag_name = $(this).attr('id');

		if(tag_name != "flecha"){
			window.Seleccion_fichas_individualController.seleccionFichas_onClick(tag_name);	
		}else{
			window.Seleccion_fichas_individualController.volver_inicio();
		}
	});

});
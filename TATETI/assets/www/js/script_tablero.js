/**CODIGO JAVASCRIPT*/

/**
 *@author Pablo Taddei
 * 
 * SCRIPT DE LA PANTALLA DEL JUEGO, AQUI SE EJECUTAN TODAS LAS ACCIONES DEL JUEGO.
 *  
 */
$(function(){

	/*
	 *AQUI SE CARGA EL MENSJAE DE COMIENZO DEL JUEGO
	 *
	*/
	window.Tablero_individualController.mostrar_comienzo();

	/*
	 *AQUI SE OBTIENE LA FICHA SELECCIONADA POR EL JUGADOR
	 * Y LUEGO SE PROCEDE A MOSTRARLA EN LA PANTALLA
	*/
	var ficha_seleccionada = window.Tablero_individualController.getFicha_seleccionada();
	ficha_Seleccionada(ficha_seleccionada);
	
    
    /**
     *AQUI SE RETROCEDE HASTA LA VENTANA DE SELECCION NUEVAMENTE. 
     */
	$('#flecha_selec').click(function(){
		window.Tablero_individualController.volverVentanaSeleccion_onclick();
	});

	/**
	 *AQUI SE PRODUCE EL REINICIO DEL JUEGO
	 */
	$('#recagarJ').click(function(){
		var url_img_jugador = "../img/blank.png";
		window.Tablero_individualController.RecargarJuego();
		window.Tablero_individualController.mostrar_comienzo();
		$( "#juego tbody tr td input[type='image']" ).each( function(){ 
        	$("#juego tbody tr td input[type='image']").attr('src',url_img_jugador);
        	$("input[type=image]").prop('disabled', false);
        });
	});

	/**
	 * EVENTO DEL INPUT IMAGE 
	**/
	$('#juego').on('click',"input[type='image']",function(){
		var parentId = $(this).parent();
		var id = $(parentId).attr('id');
		
		cargar_ficha_tablero(ficha_seleccionada,id);
		window.Tablero_individualController.poner_Ficha_onclick(id);
		var posicion_Ficha_oponenete = window.Tablero_individualController.getIndexTablero();
		var ficha_oponente = window.Tablero_individualController.getFicha_oponente();
		cargar_ficha_tablero(ficha_oponente,posicion_Ficha_oponenete);

		if(window.Tablero_individualController.getFinPartida()){
			if(window.Tablero_individualController.getGanador() == 1){
				window.Tablero_individualController.mostrar_Ganador(1);
				$("input[type=image]").prop('disabled', true);
			}else if(window.Tablero_individualController.getGanador() == 2){
				window.Tablero_individualController.mostrar_Ganador(2);
				$("input[type=image]").prop('disabled', true);
			}else{
				window.Tablero_individualController.mostrar_Ganador(0);
				$("input[type=image]").prop('disabled', true);
			}
		}
	});
});

/**
 * EN ESTA FUNCION SE DETERMINA QUE FICHA FUE LA QUE SELECCIONO EL JUGADOR, Y
 * LA MUESTRA EN LA PANTALLA. 
 */
function ficha_Seleccionada(fichaseleccionada){
	$('#template').css('display','none');
	var img_jugador = $('#template').clone();
	var img_Maquina = $('#template').clone();
	
	img_jugador.removeAttr('id');
	img_jugador.removeAttr('style');
	img_Maquina.removeAttr('id');
	img_Maquina.removeAttr('style');	
	
	var url_img_jugador = fichaseleccionada == "cruz" ? "../img/iconoCrus.png" : "../img/iconoCirculo.png";
	var url_img_oponente = fichaseleccionada != "circulo" ? "../img/iconoCirculo.png" : "../img/iconoCrus.png";
	
	img_jugador.attr('src',url_img_jugador);
	img_Maquina.attr('src',url_img_oponente);

	$('.jugador').append(img_jugador);
	$('.oponente').append(img_Maquina);
}

/**
 * CARGA DE FICHA EN EL TABLERO DE JUEGO
 */
function cargar_ficha_tablero(ficha_select, idtd){	
	var img_black =  $('#'+idtd).find('[type=image]');
	var url_img_jugador = ficha_select == "cruz" ? "../img/cruz_juego.png" : "../img/circulo_juego.png";
	img_black.attr('src',url_img_jugador);
}
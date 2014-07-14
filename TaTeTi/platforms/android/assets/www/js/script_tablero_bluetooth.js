/**
 * SCRIPT DEL TABLERO MULTIJUGADOR
 */

var myVar;
var tablero;

/**
 * 
 */
$(function(){
	
	
	var fichaSeleccionada;
	var fichaOponente;

	activar_actualizacion();
	
	actualizarTablero();
	
	$('#tabla').on('click',"input[type='image']",function(){
		var parentId = $(this).parent();
		var id = $(parentId).attr('id');
		fichaSeleccionada = window.Tablero_BluetoothController.get_mi_ficha();
		cargar_ficha_tablero(fichaSeleccionada,id);
		window.Tablero_BluetoothController.mandar_mensaje(id);
		ganadores();
	});
	
	$('#flecha_selec').click(function(){
		window.Tablero_BluetoothController.cerrar_ventana();
	});
	
});



/**
 * 
 */
function activar_actualizacion(){
	myVar = setTimeout(function(){activar_actualizacion();},500);
	if(window.Tablero_BluetoothController.get_nombre_Dispositivo() != null){
		$('#oponente').text(window.Tablero_BluetoothController.get_nombre_Dispositivo());
		fichaSeleccionada =  window.Tablero_BluetoothController.get_mi_ficha();
		fichaOponente = window.Tablero_BluetoothController.get_ficha_oponente();
		if(fichaSeleccionada != null && fichaOponente != null){
			ficha_Seleccionada(fichaSeleccionada);
			turno_inicio();
			desactivar_actualizacion();
		}
	}
}

/**
 * 
 */
function desactivar_actualizacion(){
	clearTimeout(myVar);
}


/**
 * EN ESTA FUNCION SE DETERMINA QUE FICHA FUE LA QUE SELECCIONO EL JUGADOR, Y
 * LA MUESTRA EN LA PANTALLA. 
 **/
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
	$('#nombre').text("YO");
}

/**
 * 
 * @param {Object} ficha_select
 * @param {Object} idtd
 */
function cargar_ficha_tablero(ficha_select, idtd){	
	var img_black =  $('#'+idtd).find('[type=image]');
	var url_img_jugador = ficha_select == "cruz" ? "../img/cruz_juego.png" : "../img/circulo_juego.png";
	img_black.attr('src',url_img_jugador);	
}


/**
 * 
 */
function actualizarTablero(){
	tablero = setTimeout(function(){actualizarTablero();},500);
	var posicion_oponente = window.Tablero_BluetoothController.get_index_rival();
	var ficha_oponente = window.Tablero_BluetoothController.get_ficha_oponente();
		
	if(posicion_oponente != null){
		cargar_ficha_tablero(ficha_oponente, posicion_oponente);
		turno_inicio();
	}
	
	
	
}

/**
 * 
 */
function desactivar_actualizacion_tablero(){
	clearTimeout(tablero);
}


function turno_inicio(){
	var bandera = window.Tablero_BluetoothController.get_bandera();
	if(bandera == 1){
		$('#turno').text("tu turno");
	}else if(bandera == 2){
		$('#turno').text("turno del oponente");
	}else if(bandera == 3){
		$('#turno').text("tu turno");
	}
}

/**
 *
 **/
function ganadores(){
	var evaluar = window.Tablero_BluetoothController.get_ganador();
	if(evaluar == 1){
		alert('usted gano');
	}else if(evaluar == 2){
		alert('a ganado el rival');
	}else{

	}
}


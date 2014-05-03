/**CODIGO JAVASCRIPT**/
/**
 * @author pablo taddei
 * SCRIPT PARA LA PANTALLA PRINCIPAL DEL TA-TE-TI 
 * DESDE AQUI SE MANEJAN LOS EVENTOS DE LOS CONTROLES DE ESTA VENTANA
 * EVENTOS COMO LOS BOTONES
 */
$(function(){

	//TODO : evento click del boton que nos lleva a la partida individual
	$('#btnI').click(function(){
		window.TatetiController.abrirVentana_onclick();
	});

	//TODO : evento click del boton que nos permite cerrar la aplicacion
	$('#btnSalir').click(function(){
		window.TatetiController.cerrar_app();
	});

	//TODO: evento click del boton que nos lleva a la partida multijugador	
	$('#btnBluetooth').click(function(){
		window.TatetiController.abrirVentanaOponente_onclick();
	});

})();
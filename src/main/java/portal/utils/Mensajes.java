package portal.utils;

class Mensajes {
	static String msjFallo = " " + Parametros.fecha + " " + Parametros.hora;
	static String msjSkip = "Skip por error no relacionado al objetivo del test";
	static String msjConexionNoSegura = "Se produjo una advertencia por conexión no segura";
	static String msjErrorReintentoPortal;
	static String reintentosPortalAux;
	static String msjCriterioBuscarUsuario;
	static String msjCriterioBuscarServicio;
	
	static void CrearMensajes_BuscarUsuario (BusquedaUsuario.CriterioUsuario criterio){
    	switch(criterio){
        case NUMERO_SOCIO:
        	Mensajes.msjCriterioBuscarUsuario = "utilizando el criterio de Número de Socio";
          break;
        case NOMBRE:
        	Mensajes.msjCriterioBuscarUsuario = "utilizando el criterio de Nombre de Socio";
          break;
        case NUMERO_DNI:
        	Mensajes.msjCriterioBuscarUsuario = "utilizando el criterio de Número de DNI";
          break;
      }
      Mensajes.msjOkBuscarUsuario = Mensajes.msjOkBuscarUsuario + " " + Mensajes.msjCriterioBuscarUsuario;
      Mensajes.msjErrorBuscarUsuario = Mensajes.msjErrorBuscarUsuario + " " + Mensajes.msjCriterioBuscarUsuario;
    }
	
	static void CrearMensajes_BuscarServicio (BusquedaServicio.CriterioServicio criterio, String servicio){
    	switch(criterio){
        case BUSCADOR:
        	msjCriterioBuscarServicio = "utilizando el buscador";
          break;
        case ARBOL:
        	msjCriterioBuscarServicio = "utilizando el arbol de servicios";
          break;
        case FAVORITOS:
        	msjCriterioBuscarServicio = "utilizando los servicios favoritos";
          break;
      }
      msjOkBuscarUsuario = msjOkBuscarServicio + " " + servicio + " " + msjCriterioBuscarServicio;
      msjErrorBuscarServicio = msjErrorBuscarServicio + " " + servicio + " " + msjCriterioBuscarServicio;
    }

	// OK
	static String msjOkIngresoUrl = "El test ingresó a la url " + Parametros.urlIntranet + " correctamente";
	static String msjOkIngresoUrlIntra = "El test ingresó a la página Intranet correctamente";
	static String msjOkIngresarUser = "El test ingresó el usuario correctamente";
	static String msjOkIngresarPass = "El test ingresó la contraseña correctamente";
	static String msjOkBtnLogIn = "El test hizo click en el botón de 'Ingresar' correctamente";
	static String msjOkBtnEnviarDeTodosModos = "El test hizo click en el botón de 'Enviar de todos modos' correctamente";

	static String msjOkIngresoIntranet = "El test ingresó a la Intranet correctamente";
	static String msjOkIngresoMisHerramientas = "El test ingresó a 'Mis Herramientas' correctamente";
	static String msjOkIngresoPortal = "El test ingresó a la aplicación 'Portal Corporativo' correctamente";
	static String msjOkBuscarUsuario = "El test buscó al socio correctamente";
	static String msjOkBuscarServicio= "El test buscó el servicio correctamente";

	// ERROR
	static String msjErrorIntranet = "El test falló al ingresar a Intranet";
	static String msjErrorEnviarDeTodosModos = "El test falló al hacer click en 'Enviar de todos modos' correctamente";
	static String msjErrorIngresoMisHerramientas = "El test falló al ingresar a 'Mis herramientas'";
	static String msjErrorIngresoAPortal = "El test falló al ingresar a 'Portal Corporativo'";
	static String msjErrorIntentosPortal = "Se excedió el límite de intentos para acceder al 'Portal Corporativo'";
	static String msjErrorBuscarUsuario = "El test falló al buscar al socio";
	static String msjErrorBuscarServicio = "El test falló al buscar el servicio";
	static String msjErrorVentanaPortal = "No se encontró la ventana de 'Portal Corporativo'";
	
	
	

}

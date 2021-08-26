package portal.utils;

import org.openqa.selenium.By;

class Identificadores {
	// IDENTIFICADORES INTRANET
	static String frameHome = "home";
	static String frameBotonera = "botonera";
	static String frameMisHerramientas = "frmMisHerr";
	static By CampoUsuario = By.id("usuario");
	static By CampoPass = By.id("password");
	static By BtnIngresar = By.name("submit");
	static By BtnEnviarDeTodosModos = By.id("proceed-button");
	static By MisHerramientas = By.xpath("//a[@onclick='showMisHerr();']");
	static By PortalCorporativo = By.xpath("//b[contains(.,'Portal Corporativo')]");
	static By Botonera = By.xpath("//a[@href='home.asp']");

	// IDENTIFICADORES PORTAL
	static By InputConsulta = By.xpath("//input[@id='queryStringInput']");
	static By LupaConsulta = By.id("lupaSearchDiv");
	static By UltimosBuscados = By
			.xpath("//span[@class='portalWidgetHeaderTitle' and contains(text(), 'Últimos Sujetos Identificados')]");
	static By BtnGestionInterna = By.xpath("//span[contains(.,'Gestión Interna')]");
	static By BtnContraer = By
			.xpath("//div[@id=\"suaUltimosSujetosIdentificadosWidget\"]//span[@class=\"ui-icon ui-icon-minusthick\"]");
	static By NombreSocio = By.id("resultViewSocioNyA");
	
	static By BtnMenuColapsableServicios = By.id("collapsableLeftContainer");
	static By InputBuscadorDeServicios = By.id("servicesSearchInput");
	static By ListaBuscador = By.xpath("//li[@role='presentation']//parent::ul");
	static By BtnWidgetArbol = By.id("iconsuaBuscadorServiciosWidget");
	static By BtnWidgetArbolAbrir = By.id("servicesTreeButtonOpen");
	static By BtnWidgetArbolCerrar = By.id("servicesTreeButtonClose");
	static By RaizArbol = By.xpath("//div[@id='servicesTree']/div");
	static By FavoritosIcon = By.id("iconsuaServiciosFavoritosWidget");
	static By ServiciosFavoritos = By.id("favoritesTable");
	
	static By Servicio (String servicio) { return By.xpath("//span[@class='portalWidgetHeaderTitle' and contains(text(),'" +  servicio + "')]");}

	// SECCIONES UI
	static String ventanaIntranet = "Intranet";
	static String ventanaHerramientas = "Mis Herramientas";
	static String ventanaPortal = "Portal Corporativo";
	static String sectorServicios = "Arbol Servicios";
	
}

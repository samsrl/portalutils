package portal.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sam.utils.ListasUtils;

class Parametros {
	// TODO cambiar mesaje de busqueda usuraio para que sea variable
	static WebDriverWait wait;
	static LocalDate fecha = LocalDate.now();
	static Calendar cal = Calendar.getInstance();
	static SimpleDateFormat horario = new SimpleDateFormat("HH.mm.ss");
	static String hora = horario.format(cal.getTime());
	static File photo;
	static String photoDirectoryPath = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "screenshotsFail" + File.separator;
	static String photoFilePath;
	static ListasUtils pasos;
	static int timeOut = 60;
	static String testBusqNumSocio = "Portal de Atención - Búsqueda por Número de Socio";
	static int ContadorIntentosIngresoPortal = 1;

	// FLAGS
	static Boolean FlagArbol = false;

	// Listas
	static List<ListasUtils> listaPasos = new ArrayList<ListasUtils>();
	static List<String> email = new ArrayList<String>();
	static List<ListasUtils> listaSkip = new ArrayList<ListasUtils>();
	static List<String> emailSkip = new ArrayList<String>();

	// URLs
	static String urlIntranet = "https://intranet.osde/IV3/login.asp";
	static String urlPortal = "http://portal.intranet.osde:8880/portalFrontend/portal/index.do#Portal";

	// --------------------ELEMENTO CLICKEABLE--------------------\\
	// Metodo que busca si un elemento este clickeable\\
	static void ElemClickeable(By elemento, WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			wait.until(ExpectedConditions.elementToBeClickable(elemento));
		}catch(Exception e) {
			throw e;
		}
		
	}

	// --------------------ELEMENTO VISIBLE--------------------\\
	// Metodo que busca si un elemento este clickeable\\
	static void ElemVisible(By elemento, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.visibilityOfElementLocated(elemento));
	}

	// --------------------ELEMENTO PRNSENTE--------------------\\
	static void ElemPresente(By elemento, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(elemento));
	}
}

package portal.utils;

import static org.testng.Assert.fail;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import sam.utils.ListasUtils;
import sam.utils.ScreenshotsUtils;

public class BusquedaServicio {
 	public static enum CriterioServicio {
		BUSCADOR, ARBOL, FAVORITOS
	}

	public static void BuscarServicio(CriterioServicio criterio, String servicio, WebDriver driver, List<ListasUtils> listaPasos) throws InterruptedException, IOException {
		try{
			Mensajes.CrearMensajes_BuscarServicio(criterio, servicio);
			switch (criterio) {
			case BUSCADOR:
				BuscarServicioPorBuscador(servicio, driver, listaPasos);
				break;
			case ARBOL:
				BuscarServicioPorArbol(servicio, driver, listaPasos);
				break;
			case FAVORITOS:
				BuscarServicioPorFavoritos(servicio, driver, listaPasos);
				break;
			}
			Parametros.pasos = new ListasUtils("", Mensajes.msjOkBuscarServicio, "", null, null, null);
			listaPasos.add(Parametros.pasos);
			System.out.println(Mensajes.msjOkBuscarServicio);
			
		}catch(Exception e) {
			System.out.println(Mensajes.msjErrorBuscarServicio);
			System.out.println("Error" + e);
			String obtenerURL = driver.getCurrentUrl();
			Parametros.photoFilePath = ScreenshotsUtils.TomarCapturaDePantalla("Busqueda de Servicio", Parametros.photoDirectoryPath, driver);
			Parametros.pasos = new ListasUtils(obtenerURL, "", Mensajes.msjErrorBuscarServicio, 
					Parametros.photoFilePath,"Busqueda de Servicio", e);
			listaPasos.add(Parametros.pasos);
			fail(Mensajes.msjFallo + e);
		}
	}

	@SuppressWarnings("unused")
	private static void BuscarServicioPorBuscador(String servicio, WebDriver driver, List<ListasUtils> listaPasos) throws Exception {
			Parametros.ElemClickeable(Identificadores.BtnMenuColapsableServicios, driver);
			driver.findElement(Identificadores.BtnMenuColapsableServicios).click();
			Parametros.ElemClickeable(Identificadores.InputBuscadorDeServicios, driver);
			driver.findElement(Identificadores.InputBuscadorDeServicios).sendKeys(servicio);

			Parametros.ElemPresente(Identificadores.ListaBuscador, driver);
			List<WebElement> resultados = driver.findElement(Identificadores.ListaBuscador).findElements(By.tagName("a"));
			String inner = new String();
			WebElement element = new RemoteWebElement();
			for (WebElement elem : resultados) {
				inner = elem.getAttribute("innerHTML");
				inner = inner.replace("<b>", "");
				inner = inner.replace("</b>", "");

				if (inner.equals(servicio)) {element = elem; break;}
			}
			if (element == null) throw new Exception();
			element.click();
	}

	private static void BuscarServicioPorArbol(String servicio, WebDriver driver, List<ListasUtils> listaPasos) throws InterruptedException, IOException {	
			Parametros.ElemClickeable(Identificadores.BtnWidgetArbol, driver);
			driver.findElement(Identificadores.BtnWidgetArbol).click();
			Parametros.ElemClickeable(Identificadores.BtnWidgetArbolAbrir, driver);
			driver.findElement(Identificadores.BtnWidgetArbolAbrir).click();

			WebElement elem = driver.findElement(Identificadores.RaizArbol).findElement(By.xpath("//span[text()=\"" + servicio + "\"]"));

			RecorrerArbol(elem);
	}

	private static void RecorrerArbol(WebElement elem) throws InterruptedException {
		WebElement padre = elem.findElement(By.xpath("./parent::div"));
		Boolean desplegable = padre.getAttribute("role") != null && !elem.getAttribute("id").equals("accordionMenuApi") || padre.getAttribute("class").equals("treeMenuElement ellipsis_text");
		if (desplegable) {
			RecorrerArbol(padre);
			Thread.sleep(1000);
			if (elem.getTagName().equals("span")) {
				elem.click();
			} else {
				String id = elem.getAttribute("aria-labelledby");
				if (id != null) elem.findElement(By.xpath("./preceding-sibling::*[@id='" + id + "']")).click();
			}
		}
	}

	private static void BuscarServicioPorFavoritos(String servicio, WebDriver driver, List<ListasUtils> listaPasos) throws IOException {
			Parametros.ElemClickeable(Identificadores.FavoritosIcon, driver);
			driver.findElement(Identificadores.FavoritosIcon).click();
			Parametros.ElemPresente(Identificadores.ServiciosFavoritos, driver);

			WebElement tablaServiciosFavoritos = driver.findElement(Identificadores.ServiciosFavoritos);
			tablaServiciosFavoritos.findElement(By.xpath("//span[text()='" + servicio + "']")).click();
	}

}

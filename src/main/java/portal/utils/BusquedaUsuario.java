package portal.utils;

import static org.testng.Assert.fail;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;

import sam.utils.ListasUtils;
import sam.utils.ScreenshotsUtils;

public class BusquedaUsuario {
	public static enum CriterioUsuario { NUMERO_SOCIO, NOMBRE, NUMERO_DNI}
	public static void BuscarUsuario(CriterioUsuario criterio, String socioQuery, WebDriver driver, List<ListasUtils> listaPasos) throws IOException {
		try {
  			Mensajes.CrearMensajes_BuscarUsuario(criterio);
  			driver.findElement(Identificadores.InputConsulta).sendKeys(socioQuery);
  			driver.findElement(Identificadores.LupaConsulta).click();
  			Parametros.ElemVisible(Identificadores.NombreSocio, driver);

  			System.out.println(Mensajes.msjOkBuscarUsuario);
  			Parametros.pasos = new ListasUtils("", Mensajes.msjOkBuscarUsuario, "", null, null, null);
  			listaPasos.add(Parametros.pasos);

  			Thread.sleep(3000);
  		} catch (Exception e) {
  			System.out.println(Mensajes.msjErrorBuscarUsuario);
  			String obtenerURL = driver.getCurrentUrl();
  			Parametros.photoFilePath = ScreenshotsUtils.TomarCapturaDePantalla(Identificadores.ventanaPortal, Parametros.photoDirectoryPath, driver);
  			Parametros.pasos = new ListasUtils(obtenerURL, "", Mensajes.msjErrorBuscarUsuario,
  					Parametros.photoFilePath, "Portal Corporativo", e);
  			listaPasos.add(Parametros.pasos);

  			fail(Mensajes.msjFallo + e);
  			System.out.println(e);
  		}
  	}

    
}

package portal.utils;

import static org.testng.Assert.fail;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;

import sam.utils.ListasUtils;
import sam.utils.ScreenshotsUtils;

public class Ingreso {
		public static void IngresoPortalCompleto(String usuario, String pass, WebDriver driver, List<ListasUtils> listaPasos, List<ListasUtils> listaSkip) throws InterruptedException, IOException, MessagingException {
			IngresoIntranet(usuario, pass, driver, listaPasos, listaSkip);
			IngresoMisHerramientas(driver, listaPasos, listaSkip);
			IngresoPortalCorporativo(driver, listaPasos);
		}
		
		// Metodo que Ingresa a la intranet de osde
		public static void IngresoIntranet(String usuario, String pass, WebDriver driver, List<ListasUtils> listaPasos, List<ListasUtils> listaSkip)
				throws InterruptedException, IOException, MessagingException {
			try {
				Parametros.wait = new WebDriverWait(driver, Parametros.timeOut);
				driver.manage().window().maximize();
				driver.get(Parametros.urlIntranet);
				Parametros.pasos = new ListasUtils("", Mensajes.msjOkIngresoUrlIntra, "", null, null, null);
				listaPasos.add(Parametros.pasos);
				System.out.println(Mensajes.msjOkIngresoUrl);
				
				Parametros.ElemClickeable(Identificadores.CampoUsuario, driver);
				driver.findElement(Identificadores.CampoUsuario).clear();
				driver.findElement(Identificadores.CampoUsuario).sendKeys(usuario);
				Parametros.pasos = new ListasUtils("", Mensajes.msjOkIngresarUser, "", null, null, null);
				listaPasos.add(Parametros.pasos);
				System.out.println(Mensajes.msjOkIngresarUser);
				
				Thread.sleep(3000);
				
				Parametros.ElemClickeable(Identificadores.CampoPass, driver);
				driver.findElement(Identificadores.CampoPass).click();
				driver.findElement(Identificadores.CampoPass).sendKeys(pass);
				
				Parametros.pasos = new ListasUtils("", Mensajes.msjOkIngresarPass, "", null, null, null);
				listaPasos.add(Parametros.pasos);
				System.out.println(Mensajes.msjOkIngresarPass);
				
				Thread.sleep(3000);

				Parametros.ElemClickeable(Identificadores.BtnIngresar, driver);
				driver.findElement(Identificadores.BtnIngresar).click();
				Parametros.pasos = new ListasUtils("", Mensajes.msjOkBtnLogIn, "", null, null, null);
				listaPasos.add(Parametros.pasos);
				System.out.println(Mensajes.msjOkBtnLogIn);
				
				WebElement insecure;
				try {insecure = driver.findElement(By.className("insecure-form"));
				}catch(org.openqa.selenium.NoSuchElementException e) {insecure = null;}
				if (insecure != null) 
					EnviardeTodosModos(driver, listaPasos, listaSkip);
				
				Thread.sleep(5000);
				validarIntranet(driver);
				Parametros.pasos = new ListasUtils("", Mensajes.msjOkIngresoIntranet, "", null, null, null);
				listaPasos.add(Parametros.pasos);
				System.out.println(Mensajes.msjOkIngresoIntranet);

			} catch (Exception e) {
				System.out.println(Mensajes.msjSkip);
				System.out.println(Mensajes.msjErrorIntranet);
				System.out.println("Error" + e);
				String obtenerURL = driver.getCurrentUrl();
				Parametros.photoFilePath = ScreenshotsUtils.TomarCapturaDePantalla("Intranet", Parametros.photoDirectoryPath, driver);
				Parametros.pasos = new ListasUtils(obtenerURL, "", Mensajes.msjErrorIntranet, 
						Parametros.photoFilePath,"Intranet", e);
				listaSkip.add(Parametros.pasos);
				throw new SkipException(Mensajes.msjSkip);
			}

		}
		
		private static void EnviardeTodosModos(WebDriver driver, List<ListasUtils> listaPasos, List<ListasUtils> listaSkip) throws IOException {
			try {
				Parametros.pasos = new ListasUtils("", Mensajes.msjConexionNoSegura, "", null, null, null);
				listaPasos.add(Parametros.pasos);
				System.out.println(Mensajes.msjConexionNoSegura);
				
				WebDriverWait wait = new WebDriverWait(driver, Parametros.timeOut);
				wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(Identificadores.BtnEnviarDeTodosModos))).click();
				Parametros.pasos = new ListasUtils("", Mensajes.msjOkBtnEnviarDeTodosModos, "", null, null, null);
				listaPasos.add(Parametros.pasos);
				System.out.println(Mensajes.msjOkBtnEnviarDeTodosModos);

			} catch (Exception e) {
				System.out.println(Mensajes.msjSkip);
				System.out.println(Mensajes.msjErrorEnviarDeTodosModos);
				System.out.println("Error" + e);
				String obtenerURL = driver.getCurrentUrl();
				Parametros.photoFilePath = ScreenshotsUtils.TomarCapturaDePantalla("Intranet", Parametros.photoDirectoryPath, driver);
				Parametros.pasos = new ListasUtils(obtenerURL, "", Mensajes.msjErrorEnviarDeTodosModos, 
													Parametros.photoFilePath, "Intranet", e);
				listaSkip.add(Parametros.pasos);
				throw new SkipException(Mensajes.msjSkip);
			}
		}
		
		private static void validarIntranet(WebDriver driver) {
			// OJO QUE ES INESTABLE
			//driver.switchTo().frame(Identificadores.frameBotonera);
			//Parametros.ElemClickeable(Identificadores.Botonera, driver);
			//driver.switchTo().parentFrame();
			
			System.out.println("SWITCH A FRAME HOME");
			driver.switchTo().frame(Identificadores.frameHome);
			System.out.println("ELEM CLICKEABLE MIS HERRAMIENTAS");
			Parametros.ElemClickeable(Identificadores.MisHerramientas, driver);
			System.out.println("VUELTA A PARENT FRAME");
			driver.switchTo().parentFrame();
		}
		
		

		// Metodo que Ingresa Mis Herramientas desde la Intranet
		public static void IngresoMisHerramientas(WebDriver driver, List<ListasUtils> listaPasos, List<ListasUtils> listaSkip) throws InterruptedException, IOException, MessagingException {
			try {
				System.out.println("SWITCH A MIS HERRAMIENTAS");
				driver.switchTo().frame(Identificadores.frameHome);
				Parametros.ElemClickeable(Identificadores.MisHerramientas, driver);
				driver.findElement(Identificadores.MisHerramientas).click();

				Thread.sleep(3000);
				driver.switchTo().frame(Identificadores.frameMisHerramientas);
				Thread.sleep(3000);

				Parametros.pasos = new ListasUtils("", Mensajes.msjOkIngresoMisHerramientas, "", null, null, null);
				listaPasos.add(Parametros.pasos);
				System.out.println(Mensajes.msjOkIngresoMisHerramientas);

			} catch (Exception e) {
				System.out.println(Mensajes.msjSkip);
				System.out.println(Mensajes.msjErrorIngresoMisHerramientas);
				System.out.println("Error" + e);
				String obtenerURL = driver.getCurrentUrl();
				Parametros.photoFilePath = ScreenshotsUtils.TomarCapturaDePantalla(Identificadores.ventanaHerramientas, Parametros.photoDirectoryPath, driver);
				Parametros.pasos = new ListasUtils(obtenerURL, "", Mensajes.msjErrorIngresoMisHerramientas,
						Parametros.photoFilePath, "MisHerramientas", e);
				listaPasos.add(Parametros.pasos);
				listaSkip.add(Parametros.pasos);
				throw new SkipException(Mensajes.msjSkip);
			}
		}

		// ---------------PORTAL CORPORATIVO---------------\\
		private static void validarCargaDePortalCorporativo(WebDriver driver, List<ListasUtils> listaPasos) throws Exception {
			Parametros.ElemClickeable(Identificadores.UltimosBuscados, driver);
			Parametros.ElemClickeable(Identificadores.InputConsulta, driver);
			Parametros.ElemClickeable(Identificadores.LupaConsulta, driver);
			Parametros.ElemClickeable(Identificadores.BtnGestionInterna, driver);
		}
		
		private static void DeMisHerramientasAPortal(WebDriver driver) throws Exception {
			Parametros.ElemClickeable(Identificadores.PortalCorporativo, driver);
			driver.findElement(Identificadores.PortalCorporativo).click();
									
			Thread.sleep(3000);
			java.util.Set<java.lang.String> windowHandles = driver.getWindowHandles();
			int windowsNumber = windowHandles.size();
			int windowsCounter = 1;
			for (String window : windowHandles) {
				System.out.println(window);
				driver.switchTo().window(window);
				Thread.sleep(3000);
				if (driver.getCurrentUrl().equals(Parametros.urlPortal)) {
					driver.manage().window().maximize();	
				}else if (windowsCounter == windowsNumber){
					System.out.println(Mensajes.msjErrorVentanaPortal);
					throw new Exception(Mensajes.msjErrorVentanaPortal);
				}
				windowsCounter++;
			}
		}
		
		private static void MensajeReintentosPortal(List<ListasUtils> listaPasos) {
			Mensajes.msjErrorReintentoPortal = String.format(
					"Intento #%d para entrar al 'Portal Corporativo' fallido, " + Mensajes.reintentosPortalAux,
					Parametros.ContadorIntentosIngresoPortal);
			System.out.println(Mensajes.msjErrorIngresoAPortal);
			System.out.println(Mensajes.msjErrorReintentoPortal);

			Parametros.pasos = new ListasUtils("", Mensajes.msjErrorReintentoPortal, "", null, null, null);
			listaPasos.add(Parametros.pasos);
		}
		
		private static void IntentosPortal(WebDriver driver, List<ListasUtils> listaPasos) throws Exception {
			try {
				if (Parametros.ContadorIntentosIngresoPortal <= 3) {
					validarCargaDePortalCorporativo(driver, listaPasos);
					System.out.println(Mensajes.msjOkIngresoPortal);
					Parametros.pasos = new ListasUtils("", Mensajes.msjOkIngresoPortal, "", null, null, null);
					listaPasos.add(Parametros.pasos);
					
				}else throw new Exception();
			} catch (Exception e) {
				if (Parametros.ContadorIntentosIngresoPortal < 3) {
					Mensajes.reintentosPortalAux = "reintentando...";
					MensajeReintentosPortal(listaPasos);
					Parametros.ContadorIntentosIngresoPortal++;
					driver.get(Parametros.urlPortal);
					Thread.sleep(5000);
					IntentosPortal(driver, listaPasos);
					
				} else {
					Mensajes.reintentosPortalAux = "abortando...";
					MensajeReintentosPortal(listaPasos);
					throw new Exception();
				}
			}
		}

		public static void IngresoPortalCorporativo(WebDriver driver, List<ListasUtils> listaPasos) throws IOException {
			try {
				DeMisHerramientasAPortal(driver);
				IntentosPortal(driver, listaPasos);
				} catch (Exception e) {
				System.out.println(Mensajes.msjErrorIngresoAPortal);
				String obtenerURL = driver.getCurrentUrl();
				Parametros.photoFilePath = ScreenshotsUtils.TomarCapturaDePantalla(Identificadores.ventanaPortal, Parametros.photoDirectoryPath, driver);
				Parametros.pasos = new ListasUtils(obtenerURL, "", Mensajes.msjErrorIngresoAPortal,
						Parametros.photoFilePath, "Portal Corporativo", e);
				listaPasos.add(Parametros.pasos);

				fail(Mensajes.msjFallo + e);
				System.out.println(e);
			}
		}
}

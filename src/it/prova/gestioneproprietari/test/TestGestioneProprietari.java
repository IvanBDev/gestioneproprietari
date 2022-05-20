package it.prova.gestioneproprietari.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import it.prova.gestioneproprietari.dao.EntityManagerUtil;
import it.prova.gestioneproprietari.model.Automobile;
import it.prova.gestioneproprietari.model.Proprietario;
import it.prova.gestioneproprietari.service.MyServiceFactory;
import it.prova.gestioneproprietari.service.automobile.AutomobileService;
import it.prova.gestioneproprietari.service.proprietario.ProprietarioService;

public class TestGestioneProprietari {

	public static void main(String[] args) {

		ProprietarioService proprietarioService = MyServiceFactory.getProprietarioServiceInstance();
		AutomobileService automobileService = MyServiceFactory.getAutomobileServiceInstance();

		try {

			// ora con il service posso fare tutte le invocazioni che mi servono

			/*testInserisciProprietario(proprietarioService);
			System.out.println(
					"In tabella Proprietario ci sono " + proprietarioService.listAllProprietari().size() + " elementi");*/
			
			testInserisciAutomobile(automobileService, proprietarioService);
			System.out.println(
					"In tabella Automobile ci sono " + automobileService.listAllAutomobili().size() + " elementi");
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			

		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			// questa è necessaria per chiudere tutte le connessioni quindi rilasciare il
			// main
			EntityManagerUtil.shutdown();
		}

	}

	public static void testInserisciProprietario(ProprietarioService proprietarioService) throws Exception {
		System.out.println(
				"................................... testInserisciProprietario: Inizio..................................");

		Date dataNascitaProprietario = null;
		dataNascitaProprietario = new SimpleDateFormat("dd/MM/yyyy").parse("19/04/2000");

		Proprietario nuovoProprietario = new Proprietario("Luigi", "Verdi", "VRELGU00J19O952D",
				dataNascitaProprietario);
		if (nuovoProprietario.getId() != null)
			throw new RuntimeException("testInserisciProprietario fallito: record già presente ");

		proprietarioService.inserisciNuovo(nuovoProprietario);

		if (nuovoProprietario.getId() == null)
			throw new Exception("testInserisciProprietario fallito ");

		System.out.println(
				"................................... testInserisciProprietario: Fine..................................");
	}

	public static void testInserisciAutomobile(AutomobileService automobileService,
			ProprietarioService proprietarioService) throws Exception {
		System.out.println(
				"................................... testInserisciAutomobile: Inizio..................................");

		List<Proprietario> listaProprietrai = proprietarioService.listAllProprietari();
		if (listaProprietrai.isEmpty())
			throw new RuntimeException("testInserisciAutomobile fallito: non ci sono proprietari a cui collegarci ");

		Date dataImmatricolazioneAuto = null;
		dataImmatricolazioneAuto = new SimpleDateFormat("dd/MM/yyyy").parse("26/11/2021");

		Automobile nuovaAutomobile = new Automobile("Alfa Romeo", "Long", "SW450-LP", dataImmatricolazioneAuto);
		nuovaAutomobile.setProprietario(listaProprietrai.get(2));

		automobileService.inserisciNuovo(nuovaAutomobile);

		// (NOVITA' RISPETTO AL PASSATO!!!)
		if (nuovaAutomobile.getId() == null)
			throw new RuntimeException("testInserisciAutomobile fallito ");

		// il test fallisce anche se non è riuscito a legare i due oggetti
		if (nuovaAutomobile.getProprietario() == null)
			throw new RuntimeException("testInserisciAutomobile fallito: non ha collegato il proprietario ");

		System.out.println(
				"................................... testInserisciAutomobile: Fine..................................");
	}

}

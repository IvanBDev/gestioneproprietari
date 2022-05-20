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
			
			System.out.println(
					"In tabella Proprietario ci sono " + proprietarioService.listAllProprietari().size() + " elementi");
			System.out.println(
					"In tabella Automobile ci sono " + automobileService.listAllAutomobili().size() + " elementi");

			testInserisciProprietario(proprietarioService);
			System.out.println(
					"In tabella Proprietario ci sono " + proprietarioService.listAllProprietari().size() + " elementi");
			
			/*testInserisciAutomobile(automobileService, proprietarioService);
			System.out.println(
					"In tabella Automobile ci sono " + automobileService.listAllAutomobili().size() + " elementi");*/
			
			//testCaricaSingoloProprietario(proprietarioService);
			
			//testCaricaSingolaAutomobile(automobileService);
			
			//testAggiornaRecordProprietario(proprietarioService);
			
			
			
			
			
			
			
			
			
			

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
		dataNascitaProprietario = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2022");

		Proprietario nuovoProprietario = new Proprietario("Prova", "ProvaProva", "PPPPPPPPPPPPPP",
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
	
	public static void testCaricaSingoloProprietario(ProprietarioService proprietarioService) throws Exception{
		System.out.println(
				"................................... testCaricaSingoloProprietario: Inzio..................................");
		
		List<Proprietario> listaProprietari = proprietarioService.listAllProprietari();
		if(listaProprietari.isEmpty())
			throw new RuntimeException("Non ci sono proprietari nel DB");
		
		Long idRicercaProprietario = 2L;
		
		System.out.println(proprietarioService.caricaSingoloProprietario(idRicercaProprietario));
		
		System.out.println(
				"................................... testCaricaSingoloProprietario: Fine..................................");
	}
	
	public static void testCaricaSingolaAutomobile(AutomobileService automobileService) throws Exception{
		System.out.println(
				"................................... testcaricaSingolaAutomobile: Inzio..................................");
		
		List<Automobile> listaAutomobili = automobileService.listAllAutomobili();
		if(listaAutomobili.isEmpty())
			throw new RuntimeException("Non ci sono automobili nel DB");
		
		Long idRicercaAutomobile = 6L;
		
		System.out.println(automobileService.caricaSingolaAutomobile(idRicercaAutomobile));
		
		System.out.println(
				"................................... testcaricaSingolaAutomobile: Fine..................................");
	}
	
	public static void testAggiornaRecordProprietario(ProprietarioService proprietarioService) throws Exception{
		System.out.println(
				"................................... testAggiornaRecordProprietario: Inzio..................................");
		
		List<Proprietario> listaProprietari = proprietarioService.listAllProprietari();
		if(listaProprietari.isEmpty())
			throw new RuntimeException("Non ci sono proprietari nel DB");
		
		Date dataAggiornaProprietario = null;
		dataAggiornaProprietario = new SimpleDateFormat("dd/MM/yyyy").parse("18/04/2006");
		
		int idAggiornaProprietario = 2;
		Proprietario aggiornaProprietaro = listaProprietari.get(idAggiornaProprietario);	
		//Qui si puà decidere cosa modificare
		//aggiornaProprietaro.setNome(null);
		//aggiornaProprietaro.setCognome(null);
		//aggiornaProprietaro.setCodiceFiscale(null);
		aggiornaProprietaro.setDataNascita(dataAggiornaProprietario);
		
		proprietarioService.aggiorna(aggiornaProprietaro);
		
		System.out.println(
				"................................... testAggiornaRecordProprietario: Inzio..................................");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

package it.prova.gestioneproprietari.test;


import java.text.SimpleDateFormat;
import java.util.Date;

import it.prova.gestioneproprietari.dao.EntityManagerUtil;
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
			
			testInserisciProprietario(proprietarioService);
			System.out.println("In tabella Proprietario ci sono "+ proprietarioService.listAllProprietari().size() + " elementi");


		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			// questa è necessaria per chiudere tutte le connessioni quindi rilasciare il
			// main
			EntityManagerUtil.shutdown();
		}
		
	}
	
	public static void testInserisciProprietario(ProprietarioService proprietarioService) throws Exception{
		System.out.println("................................... testInserisciProprietario: Inizio..................................");
		
		Date dataNascitaProprietario = null; 
		dataNascitaProprietario = new SimpleDateFormat("dd/MM/yyyy").parse("14/08/1983");
		
		Proprietario nuovoProprietario = new Proprietario("Gianluca", "Canves", "CNSGNC83S14W298V", dataNascitaProprietario);
		if(nuovoProprietario.getId() != null) 
			throw new RuntimeException("testInserisciProprietario fallito: record già presente ");
		
		proprietarioService.inserisciNuovo(nuovoProprietario);
		
		if(nuovoProprietario.getId() == null)
			throw new Exception("testInserisciProprietario fallito ");
		
		System.out.println("................................... testInserisciProprietario: Fine..................................");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

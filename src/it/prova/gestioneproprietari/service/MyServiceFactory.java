package it.prova.gestioneproprietari.service;


import it.prova.gestioneproprietari.dao.MyDaoFactory;
import it.prova.gestioneproprietari.service.automobile.AutomobileService;
import it.prova.gestioneproprietari.service.automobile.AutomobileServiceImpl;
import it.prova.gestioneproprietari.service.proprietario.ProprietarioService;
import it.prova.gestioneproprietari.service.proprietario.ProprietarioServiceImpl;


public class MyServiceFactory {
	
	// rendiamo le istanze restituite SINGLETON
	private static ProprietarioService proprietarioServiceInstance = null;
	private static AutomobileService automobileServiceInstance = null;

	public static ProprietarioService getProprietarioServiceInstance() {
		if (proprietarioServiceInstance == null) {
			proprietarioServiceInstance = new ProprietarioServiceImpl();
			try {
				proprietarioServiceInstance.setProprietarioDAO(MyDaoFactory.getProprietarioDAOInstance());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return proprietarioServiceInstance;
	}

	public static AutomobileService getAutomobileServiceInstance() {
		if (automobileServiceInstance == null) {
			automobileServiceInstance = new AutomobileServiceImpl();
			try {
				automobileServiceInstance.setAutomobileDAO(MyDaoFactory.getAutomobileDAOInstance());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return automobileServiceInstance;
	}
	
}

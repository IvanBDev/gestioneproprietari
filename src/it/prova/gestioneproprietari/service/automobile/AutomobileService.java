package it.prova.gestioneproprietari.service.automobile;

import java.util.List;

import it.prova.gestioneproprietari.dao.automobile.AutomobileDAO;
import it.prova.gestioneproprietari.model.Automobile;

public interface AutomobileService {

	public List<Automobile> listAllAutomobili() throws Exception;

	public Automobile caricaSingolaAutomobile(Long id) throws Exception;

	public void aggiorna(Automobile automobile) throws Exception;

	public void inserisciNuovo(Automobile automobile) throws Exception;

	public void rimuovi(Automobile automobile) throws Exception;

	public List<Automobile> trovaTutteLeVettureConIProprietariConCFCheIniziaPer(String inizialeCF) throws Exception;

	public List<Automobile> trovaProprietariMinorenni() throws Exception;
	
	public void setAutomobileDAO(AutomobileDAO automobileDAO) throws Exception;

}

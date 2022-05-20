package it.prova.gestioneproprietari.dao.automobile;

import java.util.List;

import it.prova.gestioneproprietari.dao.IBaseDAO;
import it.prova.gestioneproprietari.model.Automobile;

public interface AutomobileDAO extends IBaseDAO<Automobile>{
	
	public List<Automobile> findAllOwnersThatHaveCFStartsWith(String inizialeCF) throws Exception;
	
	public List<Automobile> findAllOwnersUnderAge() throws Exception;
	
}

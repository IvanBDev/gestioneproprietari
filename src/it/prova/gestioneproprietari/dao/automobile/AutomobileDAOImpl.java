package it.prova.gestioneproprietari.dao.automobile;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneproprietari.model.Automobile;

public class AutomobileDAOImpl implements AutomobileDAO{

	@Override
	public List<Automobile> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Automobile get(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Automobile automobile) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Automobile automobile) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Automobile automobile) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Automobile> findAllOwnersThatHaveCFStartsWith(String inizialeCF) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean findErrors() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
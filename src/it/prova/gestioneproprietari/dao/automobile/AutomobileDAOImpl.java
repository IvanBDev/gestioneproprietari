package it.prova.gestioneproprietari.dao.automobile;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.reflect.Typed;

import it.prova.gestioneproprietari.model.Automobile;

public class AutomobileDAOImpl implements AutomobileDAO {

	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Automobile> list() throws Exception {
		// TODO Auto-generated method stub
		return entityManager.createQuery("FROM Automobile", Automobile.class).getResultList();
	}

	@Override
	public Automobile get(Long id) throws Exception {
		// TODO Auto-generated method stub
		return entityManager.find(Automobile.class, id);
	}

	@Override
	public void update(Automobile automobileInstance) throws Exception {
		// TODO Auto-generated method stub
		if(automobileInstance == null)
			throw new Exception("Valore in input non valido");
		
		entityManager.merge(automobileInstance);
	}

	@Override
	public void insert(Automobile automobileInstance) throws Exception {
		// TODO Auto-generated method stub
		if(automobileInstance == null)
			throw new Exception("Valore in input non valido");
		
		entityManager.persist(automobileInstance);
	}

	@Override
	public void delete(Automobile automobileInstance) throws Exception {
		// TODO Auto-generated method stub
		if(automobileInstance == null)
			throw new Exception("Valore in input non valido");
		
		entityManager.remove(entityManager.merge(automobileInstance));
	}

	@Override
	public List<Automobile> findAllOwnersThatHaveCFStartsWith(String inizialeCF) throws Exception {
		// TODO Auto-generated method stub
		TypedQuery<Automobile> query = entityManager.createQuery("SELECT DISTINCT a FROM Automobile a JOIN a.proprietario p WHERE p.codiceFiscale = ?1", Automobile.class);
		return query.setParameter(1, inizialeCF).getResultList();
	}

	@Override
	public boolean findErrors() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}

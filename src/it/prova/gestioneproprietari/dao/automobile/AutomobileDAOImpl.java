package it.prova.gestioneproprietari.dao.automobile;

import java.text.SimpleDateFormat;
import java.util.Date;
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
		if (automobileInstance == null)
			throw new Exception("Valore in input non valido");

		entityManager.merge(automobileInstance);
	}

	@Override
	public void insert(Automobile automobileInstance) throws Exception {
		// TODO Auto-generated method stub
		if (automobileInstance == null)
			throw new Exception("Valore in input non valido");

		entityManager.persist(automobileInstance);
	}

	@Override
	public void delete(Automobile automobileInstance) throws Exception {
		// TODO Auto-generated method stub
		if (automobileInstance == null)
			throw new Exception("Valore in input non valido");

		entityManager.remove(entityManager.merge(automobileInstance));
	}

	@Override
	public List<Automobile> findAllOwnersThatHaveCFStartsWith(String inizialeCF) throws Exception {
		// TODO Auto-generated method stub
		TypedQuery<Automobile> query = entityManager.createQuery(
				"SELECT DISTINCT  a FROM Automobile a JOIN a.proprietario p WHERE p.codiceFiscale LIKE ?1",
				Automobile.class);
		return query.setParameter(1, inizialeCF + "%").getResultList();
	}

	@Override
	public List<Automobile> findAllOwnersUnderAge() throws Exception {
		Date annoMinorenni = null;
		annoMinorenni = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2004");
		
		TypedQuery<Automobile> query = entityManager.createQuery("SELECT DISTINCT a FROM Automobile a JOIN a.proprietario p WHERE p.dataNascita <= ?1", Automobile.class);
		return query.setParameter(1, annoMinorenni).getResultList();
	}

}

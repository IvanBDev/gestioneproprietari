package it.prova.gestioneproprietari.dao.proprietario;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestioneproprietari.model.Proprietario;

public class ProprietarioDAOImpl implements ProprietarioDAO{
	
	private EntityManager entityManager;
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Proprietario> list() throws Exception {
		// TODO Auto-generated method stub
		return entityManager.createQuery("FROM Proprietario", Proprietario.class).getResultList();
	}

	@Override
	public Proprietario get(Long id) throws Exception {
		// TODO Auto-generated method stub
		return entityManager.find(Proprietario.class, id);
	}

	@Override
	public void update(Proprietario proprietarioInstance) throws Exception {
		// TODO Auto-generated method stub
		if(proprietarioInstance == null)
			throw new Exception("Valore in input non valido");
		
		entityManager.merge(proprietarioInstance);
	}

	@Override
	public void insert(Proprietario proprietarioInstance) throws Exception {
		// TODO Auto-generated method stub
		if(proprietarioInstance == null)
			throw new Exception("Valore in input non valido");
		
		entityManager.persist(proprietarioInstance);
	}

	@Override
	public void delete(Proprietario proprietarioInstance) throws Exception {
		// TODO Auto-generated method stub
		if(proprietarioInstance == null)
			throw new Exception("Valore in input non valido");
		
		entityManager.remove(entityManager.merge(proprietarioInstance));
	}

	@Override
	public List<Proprietario> findAllOwnersHaveCarsImmatricolataDa(Date dataInput) throws Exception {
		// TODO Auto-generated method stub
		TypedQuery<Proprietario> query = entityManager.createQuery("FROM Automobile a JOIN a.automobile WHERE a.annoImmatricolazione > ?1", Proprietario.class);
		
		return query.setParameter(1, dataInput).getResultList();
	}

}

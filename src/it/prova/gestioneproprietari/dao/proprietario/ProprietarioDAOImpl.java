package it.prova.gestioneproprietari.dao.proprietario;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

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
		return null;
	}

	@Override
	public void update(Proprietario proprietario) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Proprietario proprietario) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Proprietario proprietario) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Proprietario> findAllOwnersHaveCarsImmatricolataDa(Date dataInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

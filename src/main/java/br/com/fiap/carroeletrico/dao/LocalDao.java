package br.com.fiap.carroeletrico.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.fiap.carroeletrico.model.Local;

public class LocalDao {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("local");
	EntityManager manager = factory.createEntityManager();

    public void insert(Local local) {
		manager.getTransaction().begin();
		manager.persist(local);
		manager.getTransaction().commit();
	}

	public List<Local> showAll() {
		return manager
				.createQuery("SELECT l FROM Local l", Local.class)
				.getResultList();
	}

	public List<Local> orderByStates() {
		return manager
				.createQuery("SELECT e FROM Local e ORDER BY e.estado", Local.class)
				.getResultList();
	}
	
	public void delete(Long localId) {
		Local local = manager.find(Local.class, localId);
		manager.getTransaction().begin();
		manager.remove(local);
		manager.getTransaction().commit();
	}
}

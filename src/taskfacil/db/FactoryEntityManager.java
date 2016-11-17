package taskfacil.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactoryEntityManager {
	private static EntityManagerFactory factory = 
			Persistence.createEntityManagerFactory("TaskFacil");
	
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

}

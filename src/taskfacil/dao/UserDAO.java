package taskfacil.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import taskfacil.db.FactoryEntityManager;
import taskfacil.models.User;

public class UserDAO {
	private EntityManager manager;

	public UserDAO() {
		manager = FactoryEntityManager.getEntityManager(); 
	}
	
	public boolean authUser(User pUser){
		Query query = manager.createQuery("SELECT * FROM User WHERE email = '" + pUser.getEmail() + "' AND senha = " + pUser.getSenha());
		
		if(query.getFirstResult() > 0){
			return true;
		}
		return false;
	}
}

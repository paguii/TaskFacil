package taskfacil.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import taskfacil.db.FactoryEntityManager;
import taskfacil.models.User;

public class UserDAO {
	private EntityManager manager;

	public UserDAO() {
		manager = FactoryEntityManager.getEntityManager();
	}

	public User authUser(User pUser){
		Query query = manager.createQuery("SELECT u FROM User u WHERE email = '" + pUser.getEmail() + "' AND password = " + pUser.getPassword());
		List<User> list = query.getResultList();

		if(list.isEmpty()){
			return null;
		}else{
			User user = list.get(0);
			System.out.println(user.getName());
			return user;
		}
	}

	public void insert(User newUser) {
		manager.getTransaction().begin();
		manager.persist(newUser);
		manager.getTransaction().commit();
		manager.close();
	}

	public User getCollaborator(User pUser){
		Query query = manager.createQuery("SELECT u FROM User u WHERE email = '" + pUser.getEmail() + "'");
		List<User> list = query.getResultList();
		if(list.size() > 0){
			return list.get(0);
		}else{
			return null;
		}

	}

}

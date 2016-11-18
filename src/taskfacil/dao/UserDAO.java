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
		Query query = manager.createQuery("SELECT u FROM User u WHERE email = '" + pUser.getEmail() + "' AND senha = " + pUser.getSenha());
		List<User> list = query.getResultList();

		if(list.isEmpty()){
			return null;
		}else{
			User user = list.get(0);
			System.out.println(user.getName());
			return user;
		}
	}
}

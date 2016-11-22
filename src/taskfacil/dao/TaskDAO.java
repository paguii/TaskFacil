package taskfacil.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import taskfacil.db.FactoryEntityManager;
import taskfacil.models.Task;
import taskfacil.models.User;

public class TaskDAO {
	private EntityManager manager;

	public TaskDAO() {
		manager = FactoryEntityManager.getEntityManager();
	}

	public List<Task> getUserTasks(User pUser){
		Query query = manager.createQuery("SELECT t FROM Task t WHERE owner = " + pUser.getId());
		List<Task> list = query.getResultList();

		if(list.isEmpty()){
			return null;
		}else{
			System.out.println(list.get(0).getId());
			return list;
		}
	}
}

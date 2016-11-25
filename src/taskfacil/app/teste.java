package taskfacil.app;

import java.util.Date;

import javax.persistence.EntityManager;

import taskfacil.db.FactoryEntityManager;
import taskfacil.models.Task;
import taskfacil.models.User;

public class teste {
	public static void main(String[] args) {
		EntityManager manager = FactoryEntityManager.getEntityManager();
		Date data = new Date();

		manager.getTransaction().begin();

		User usuario1 = manager.find(User.class, 1);

//		User usuario1 = new User();
//		usuario1.setName("Guilherme");
//		usuario1.setPassword("123456");
//		usuario1.setEmail("teste@teste.com");
//		manager.persist(usuario1);

		Task tarefa1 = new Task();
		tarefa1.setTitle("ahua");
		tarefa1.setLocal("Londres");
		tarefa1.setDate(data);
		tarefa1.setDescription("dasdsa teste!");
		tarefa1.setOwner(usuario1);

		manager.persist(tarefa1);

//		Task tarefa2 = new Task();
//
//		tarefa2 = manager.find(Task.class, 2);
//
//		System.out.println(tarefa2.getOwner().getName());

		manager.getTransaction().commit();
		manager.close();
	}
}

package taskfacil.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import taskfacil.dao.TaskDAO;
import taskfacil.dao.UserDAO;
import taskfacil.models.Task;
import taskfacil.models.User;

public class TaskFacilAdicionarColaborator implements Initializable {

	@FXML
	private TextField txtEmailColaborador;

	@FXML
	private Button btnAddColaborador;

	private User newCollaborator;
	private Task task;
	private UserDAO userDAO;
	private TaskDAO taskDAO;

	public void handlerBtnAddColaborador(){
		newCollaborator.setEmail(txtEmailColaborador.getText());
		newCollaborator = userDAO.getCollaborator(newCollaborator);

		if(newCollaborator == null){
			Alert alert = new Alert(AlertType.WARNING);
			alert.setHeaderText("Opa!");
			alert.setTitle("TaskFacil - Adicionar Colaborador");
			alert.setContentText("Email nao encontrado!");
			alert.showAndWait();

			this.txtEmailColaborador.clear();
		}else{
			this.task.addCollab(this.newCollaborator);
			try {
				taskDAO.updateTask(task);

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("Adicionar Colaborador");
				alert.setTitle("TaskFacil - Adicionar Colaborador");
				alert.setContentText("Colaborador adicionado com sucesso.");
				alert.showAndWait();

			} catch (Exception e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Adicionar Colaborador");
				alert.setTitle("TaskFacil - Adicionar Colaborador");
				alert.setContentText("Falha ao adicionar colaborador.");
				alert.showAndWait();
			}
		}
	}

	public void initData(Task pTask, User pUser){
		this.task = pTask;
		this.newCollaborator = pUser;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		userDAO = new UserDAO();
		taskDAO = new TaskDAO();
	}

}

package taskfacil.controllers;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import taskfacil.dao.TaskDAO;
import taskfacil.models.Task;
import taskfacil.models.User;

public class TaskFacilAdicionarTaskController implements Initializable{

	@FXML
	private TextField txtTituloTask;
	@FXML
	private TextField txtLocalTask;
	@FXML
	private DatePicker dpDataTask;
	@FXML
	private TextArea txtAreaDescricaoTask;
	@FXML
	private Button btnAdicionarTask;

	private User user;
	private Task newTask;
	private TaskDAO taskDAO;
	private Alert alert;

	public void handlerAddTask(){
		newTask = new Task();

		newTask.setTitle(txtTituloTask.getText());
		newTask.setLocal(txtLocalTask.getText());
		newTask.setDescription(txtAreaDescricaoTask.getText());

		LocalDate localDate = dpDataTask.getValue();
		Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
		Date date = Date.from(instant);
		newTask.setDate(date);

		newTask.setOwner(this.user);

		try{
			taskDAO.insertTask(newTask);

			alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Cadastro de Tarefa");
			alert.setTitle("TaskFacil - Cadastro de Tarefa");
			alert.setContentText("Sua tarefa foi cadastrada com sucesso!");
			alert.showAndWait();

			Stage stage = (Stage) btnAdicionarTask.getScene().getWindow();
			stage.close();
		}catch (Exception e) {
			alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Cadastro de Tarefa");
			alert.setTitle("TaskFacil - Cadastro de Tarefa");
			alert.setContentText("Não foi possível cadastrar esta tarefa!");
			alert.showAndWait();
		}
	}

	public void initData(User pUser){
		this.user = pUser;

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.user = new User();
		this.taskDAO = new TaskDAO();
	}
}

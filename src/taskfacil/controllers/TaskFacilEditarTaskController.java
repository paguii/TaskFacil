package taskfacil.controllers;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import taskfacil.dao.TaskDAO;
import taskfacil.models.Task;

public class TaskFacilEditarTaskController implements Initializable {

	@FXML
	private TextField txtEditTituloTask;
	@FXML
	private TextField txtEditLocalTask;
	@FXML
	private DatePicker dpEditDataTask;
	@FXML
	private TextArea txtEditAreaDescricaoTask;
	@FXML
	private Button btnEditarTask;
	@FXML
	private Text lblCodTarefa;

	private Task editTask;
	private TaskDAO taskDAO;
	private Alert alert;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.taskDAO = new TaskDAO();
	}

	public void initData(Task task){
		this.txtEditTituloTask.setText(task.getTitle());
		this.txtEditLocalTask.setText(task.getLocal());
		this.txtEditAreaDescricaoTask.setText(task.getDescription());

		Instant instant = Instant.ofEpochMilli(task.getDate().getTime());
		LocalDate data = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
		dpEditDataTask.setValue(data);

		Integer codTarefa = task.getId();
		this.lblCodTarefa.setText("Número da Tarefa: " + codTarefa.toString());

		this.editTask = task;
	}

	public void handlerEditTask(){
		if(validateFormEdit()){
			this.editTask.setTitle(txtEditTituloTask.getText());
			this.editTask.setLocal(txtEditLocalTask.getText());
			this.editTask.setDescription(txtEditAreaDescricaoTask.getText());

			LocalDate localDate = dpEditDataTask.getValue();
			Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			Date date = Date.from(instant);
			this.editTask.setDate(date);

			try {
				taskDAO.updateTask(this.editTask);

				alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("Editor de Tarefa");
				alert.setTitle("TaskFacil - Editar Tarefa");
				alert.setContentText("Sua tarefa foi atualizada com sucesso!");
				alert.showAndWait();

				Stage stage = (Stage) btnEditarTask.getScene().getWindow();
				stage.close();


			} catch (Exception e) {
				alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("Editor de Tarefa");
				alert.setTitle("TaskFacil - Editar Tarefa");
				alert.setContentText("Houve algum problema tente novamente!");
				alert.showAndWait();
			}
		}
	}


	private boolean validateFormEdit(){

		if(this.txtEditTituloTask.getText().equals("")){
			return false;
		}

		if(this.txtEditLocalTask.getText().equals("")){
			return false;
		}

		if(this.txtEditAreaDescricaoTask.getText().equals("")){
			return false;
		}

		if(dpEditDataTask.getValue() == null){
			return false;
		}

		return true;
	}
}

package taskfacil.controllers;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import taskfacil.dao.TaskDAO;
import taskfacil.models.Task;
import taskfacil.models.User;

public class TaskFacilPortalController implements Initializable {

	@FXML
	private Button btnFinalizarTask;
	@FXML
	private Button btnCancelarTask;
	@FXML
	private TextArea txtAreaDescTask;
	@FXML
	private Label lblTituloTask;
	@FXML
	private Label lblCodTask;
	@FXML
	private Label lblDataPrevistaTask;
	@FXML
	private Label lblQtdColaboradores;
	@FXML
	private Text lblUser;
	@FXML
	private TableView<Task> tblTasks;
	@FXML
	private TableColumn<Task, String> tblColTituloTask;
	@FXML
	private TableColumn<Task, String> tblColDescTask;
	@FXML
	private TableColumn<Task, String> tblColLocalTask;
	@FXML
	private TableColumn<Task, Date> tblColDataTask;
	@FXML
	private MenuItem menuItemAdicionarTask;
	@FXML
	private MenuItem menuItemEditarTask;

//	private Alert alert;
	private TaskDAO taskDAO;
	private User user;
	private ObservableList<Task> taskObservableList;

	private List<Task> loadUserTask(){
		return taskDAO.getUserTasks(getUser());
	}

	private void setUser(User pUser){
		this.user = pUser;
	}

	private User getUser() {
		return user;
	}

	public void handlerBtnFinalizar(){
		Task selectedTask = this.tblTasks.getSelectionModel().getSelectedItem();

		if(selectedTask == null){
			Alert alert = new Alert(AlertType.WARNING);
			alert.setHeaderText("Erro");
			alert.setTitle("TaskFacil - Erro");
			alert.setContentText("Selecione uma tarefa para editar.");
			alert.showAndWait();
		}else{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Finalizar Tarefa");
			alert.setTitle("TaskFacil - Finalizar Tarefa");
			alert.setContentText("Deseja realmente finalizar esta tarefa?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				try {
					taskDAO.deleteTask(selectedTask);

					alert.getButtonTypes().remove(1);
					alert.setAlertType(AlertType.CONFIRMATION);
					alert.setHeaderText("Tarefa Concluída");
					alert.setTitle("TaskFacil - Tarefa Concluída");
					alert.setContentText("Tarefa concluída com sucesso.");
					alert.showAndWait();

					setTableTasks();

				} catch (Exception e) {
					alert.getButtonTypes().remove(1);
					alert.setAlertType(AlertType.ERROR);
					alert.setHeaderText("Erro");
					alert.setTitle("TaskFacil - Erro");
					alert.setContentText("Não foi possível concluir esta tarefa.");
					alert.showAndWait();
				}
			}
		}
	}

	public void handlerBtnCancelar(){
		Task selectedTask = this.tblTasks.getSelectionModel().getSelectedItem();

		if(selectedTask == null){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Erro");
			alert.setTitle("TaskFacil - Erro");
			alert.setContentText("Selecione uma tarefa para editar.");
			alert.showAndWait();
		}else{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Cancelar Tarefa");
			alert.setTitle("TaskFacil - Cancelar Tarefa");
			alert.setContentText("Deseja realmente cancelar esta tarefa?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				try {
					taskDAO.deleteTask(selectedTask);

					alert.getButtonTypes().remove(1);
					alert.setAlertType(AlertType.INFORMATION);
					alert.setHeaderText("Tarefa Cancelada");
					alert.setTitle("TaskFacil - Tarefa Cancelada");
					alert.setContentText("Tarefa cancelada com sucesso.");
					alert.showAndWait();

					setTableTasks();

				} catch (Exception e) {
					alert.getButtonTypes().remove(1);
					alert.setAlertType(AlertType.ERROR);
					alert.setHeaderText("Erro");
					alert.setTitle("TaskFacil - Erro");
					alert.setContentText("Não foi possível concluir esta tarefa.");
					alert.showAndWait();
				}
			}


		}
	}

	public void setTableTasks(){
		if(this.taskObservableList != null){
			this.taskObservableList.clear();
		}


		this.tblColTituloTask.setCellValueFactory(new PropertyValueFactory<>("title"));
		this.tblColDescTask.setCellValueFactory(new PropertyValueFactory<>("description"));
		this.tblColLocalTask.setCellValueFactory(new PropertyValueFactory<>("local"));
		this.tblColDataTask.setCellValueFactory(new PropertyValueFactory<>("date"));

		List<Task> taskList = loadUserTask();

		if(taskList != null){
			this.taskObservableList = FXCollections.observableArrayList(taskList);
			this.tblTasks.setItems(this.taskObservableList);
		}else{
//			alert.setTitle("InformaÃ§Ã£o");
//			alert.setHeaderText("TaskFacil - Primeiro Uso");
//			alert.setContentText("Parece que voc� nunca usou o TaskFacil, comece a usar criando suas tarefas no menu TaskFacil");
//			alert.showAndWait();
		}

	}

	private void selectItemTableView(Task task) {
		if (task != null) {
			this.lblCodTask.setText(String.valueOf(task.getId()));
			this.lblTituloTask.setText(task.getTitle());

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String time = sdf.format(task.getDate());

			this.lblDataPrevistaTask.setText(time.toString());
			this.txtAreaDescTask.setText(task.getDescription());

			List<User> collaborators = task.getCollaborators();
			Integer quantidade = 0;

			if(collaborators != null){
				quantidade = collaborators.size();
			}

			this.lblQtdColaboradores.setText(quantidade.toString());
		}
	}

	public void initData(User pUser){
		setUser(pUser);

		this.lblCodTask.setText("");
		this.lblTituloTask.setText("");
		this.lblDataPrevistaTask.setText("");
		this.lblQtdColaboradores.setText("");

		this.lblUser.setText(getUser().getName());

		setTableTasks();

        this.tblTasks.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectItemTableView(newValue));
	}

	public void handlerMenuItemEditTask() throws IOException{
		Task selectedTask = this.tblTasks.getSelectionModel().getSelectedItem();

		if(selectedTask == null){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Erro");
			alert.setTitle("TaskFacil - Erro");
			alert.setContentText("Selecione uma tarefa para editar.");
			alert.showAndWait();
		}else{
			FXMLLoader loader = new FXMLLoader();

			loader.setLocation(TaskFacilPortalController.class.getResource("/taskfacil/views/TaskFacilEditarTask.fxml"));

			VBox page = (VBox) loader.load();

			Scene scene = new Scene(page);

			Stage stageAddTask = new Stage();
			stageAddTask.setScene(scene);

			TaskFacilEditarTaskController controllerAddTask = loader.getController();
			controllerAddTask.initData(selectedTask);

			stageAddTask.setTitle("TaskFacil - Editar Tarefa");
			stageAddTask.showAndWait();

			setTableTasks();
		}
	}

	public void handlerMenuItemAddTask() throws IOException{

		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(TaskFacilPortalController.class.getResource("/taskfacil/views/TaskFacilAdicionarTask.fxml"));

		VBox page = (VBox) loader.load();

		Scene scene = new Scene(page);

		Stage stageAddTask = new Stage();
		stageAddTask.setScene(scene);

		TaskFacilAdicionarTaskController controllerAddTask = loader.getController();
		controllerAddTask.initData(this.user);

		stageAddTask.setTitle("TaskFacil - Adicionar Task");
		stageAddTask.showAndWait();

		setTableTasks();
	}

	public void handlerMenuItemFechar(){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Sair");
		alert.setTitle("Sair - TaskFacil");
		alert.setContentText("Deseja realmente sair do applicativo?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			Platform.exit();
		}

	}

	public void handlerBtnAddCollaborator() throws IOException{

		Task selectedTask = this.tblTasks.getSelectionModel().getSelectedItem();

		if(selectedTask == null){

			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Erro");
			alert.setTitle("TaskFacil - Erro");
			alert.setContentText("Selecione uma tarefa para adicionar colaboradores.");
			alert.showAndWait();

		}else{

			FXMLLoader loader = new FXMLLoader();

			loader.setLocation(getClass().getResource("/taskfacil/views/TaskFacilAddCollaborator.fxml"));

			VBox page = (VBox) loader.load();

			Scene scene = new Scene(page);

			Stage stageAddColab = new Stage();
			stageAddColab.setScene(scene);

			TaskFacilAdicionarColaborator controllerAddColab = loader.getController();
			controllerAddColab.initData(selectedTask, this.user);

			stageAddColab.setTitle("TaskFacil - Adicionar Colaborador");
			stageAddColab.showAndWait();

		}
	}





	@Override
	public void initialize(URL location, ResourceBundle resources) {
		user = new User();
		taskDAO = new TaskDAO();

	}



}

package taskfacil.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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

	public void setTableTasks(){
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
			this.lblDataPrevistaTask.setText(String.valueOf(task.getDate()));
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

	public void handlerMenuItemAddTask() throws IOException{

		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(TaskFacilPortalController.class.getResource("/taskfacil/views/TaskFacilAdicionarTask.fxml"));

		VBox page = (VBox) loader.load();

		Scene scene = new Scene(page);

		Stage stageAddTask = new Stage();
		stageAddTask.setScene(scene);

		TaskFacilAdicionarTaskController controller = loader.getController();
		controller.initData(this.user);

		stageAddTask.setTitle("TaskFacil - Adicionar Task");
		stageAddTask.showAndWait();

		setTableTasks();
	}

	public void handlerMenuItemFechar(){

	}

	public void handlerBtnAddCollaborator(){

	}

	public void handlerMenuItemEditTask(){
		Task selectedTask = this.tblTasks.getSelectionModel().getSelectedItem();

		if(selectedTask == null){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Erro");
			alert.setTitle("TaskFacil - Erro");
			alert.setContentText("Selecione uma tarefa para editar.");
			alert.showAndWait();
		}else{
			//Editar
		}
	}



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		user = new User();
		taskDAO = new TaskDAO();

	}



}

package taskfacil.controllers;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
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

	private void setTableTasks(){
		this.tblColTituloTask.setCellValueFactory(new PropertyValueFactory<>("title"));
		this.tblColDescTask.setCellValueFactory(new PropertyValueFactory<>("description"));
		this.tblColLocalTask.setCellValueFactory(new PropertyValueFactory<>("local"));
		this.tblColDataTask.setCellValueFactory(new PropertyValueFactory<>("date"));

		this.taskObservableList = FXCollections.observableArrayList(loadUserTask());
		this.tblTasks.setItems(this.taskObservableList);
	}

	public void initData(User pUser){
		setUser(pUser);

		this.lblCodTask.setText("");
		this.lblTituloTask.setText("");
		this.lblDataPrevistaTask.setText("");

		this.lblUser.setText(getUser().getName());

		setTableTasks();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		user = new User();
		taskDAO = new TaskDAO();
	}



}

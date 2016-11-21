package taskfacil.controllers;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println(this.user.getName());
	}
	
	private List<Task> loadUserTask(User pUser){
		taskDAO.getUserTasks(pUser);
		return null;
	}
	
	public void setUser(User pUser){
		this.user = pUser;
	}
	
}

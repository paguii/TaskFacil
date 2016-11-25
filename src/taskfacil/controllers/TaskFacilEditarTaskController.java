package taskfacil.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
	private Button btnEditAdicionarTask;

	private Task editTask;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}


}

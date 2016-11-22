package taskfacil.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TaskFacilAdicionarTaskController implements Initializable{
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
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
	
}

package taskfacil.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TaskFacilLoginController implements Initializable{

	@FXML
	private Button btnLogin;
	@FXML
	private Button btnCadastrar;
	@FXML
	private TextField txtUser;
	@FXML
	private TextField txtSenha;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	public void handlerSignUp() throws IOException{
		FXMLLoader loader = new FXMLLoader();

	}

}

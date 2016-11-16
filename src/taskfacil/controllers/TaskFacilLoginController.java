package taskfacil.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.stage.Stage;
import taskfacil.models.User;

public class TaskFacilLoginController implements Initializable{

	@FXML
	private Button btnLogin;
	@FXML
	private Button btnCadastrar;
	@FXML
	private TextField txtUser;
	@FXML
	private PasswordField txtSenha;

	private User userLogin;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	public void handlerSignUp() throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("/taskfacil/views/TaskFacilCadastrar.fxml"));
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		
		stage.setScene(scene);
		stage.setTitle("TaskFacil - Cadastro");
		stage.show();
	}
	
	@FXML
	public void handlerSignIn() throws IOException{
		String email = txtUser.getText();
		String senha = txtSenha.getText();
	
	}
	
}

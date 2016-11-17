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
import javafx.stage.Stage;
import taskfacil.dao.UserDAO;
import taskfacil.models.User;

public class TaskFacilLoginController implements Initializable{

	@FXML
	private Button btnLogin;
	@FXML
	private Button btnCadastrar;
	@FXML
	private TextField txtUser;
	@FXML
	private PasswordField txtSenhaLogin;

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
		stage.showAndWait();
	}
	
	@FXML
	public void handlerSignIn() throws IOException{
		String email = txtUser.getText();
		String senha = txtSenhaLogin.getText();
		
		if(validateLogin(email)){
			UserDAO userDao = new UserDAO();
			
			User userLogin = new User();
			userLogin.setEmail(email);
			userLogin.setSenha(senha);
			
			userDao.authUser(userLogin);
		} 
	}
	
	private boolean validateLogin(String pEmail){
		if(!User.isEmail(pEmail)){
			System.out.println("Usu�rio Invalido.");
			return false;
		}
		
		if(txtSenhaLogin.getLength() < 4){
			System.out.println("Senha Inv�lida.");
			return false;
		}
		
		return true;
	}
	
}

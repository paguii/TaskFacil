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
import javafx.scene.control.Label;
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
	@FXML
	private Label lblErro;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	public void handlerSignUp() throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("/taskfacil/views/TaskFacilCadastrar.fxml"));
		Scene scene = new Scene(root);
		Stage stageSignUp = new Stage();

		stageSignUp.setScene(scene);
		stageSignUp.setTitle("TaskFacil - Cadastro");
		stageSignUp.showAndWait();
	}

	@FXML
	public void handlerSignIn() throws IOException{
		lblErro.setVisible(false);

		String email = txtUser.getText();
		String senha = txtSenhaLogin.getText();

		if(validateLogin(email)){
			UserDAO userDao = new UserDAO();

			User userLogin = new User();
			userLogin.setEmail(email);
			userLogin.setPassword(senha);

			userLogin = userDao.authUser(userLogin);

			if(userLogin == null){
				lblErro.setText("E-mail ou senha inválidos.");
				lblErro.setVisible(true);

				txtSenhaLogin.clear();
			}else{
				Stage stage  = (Stage) btnLogin.getScene().getWindow();
				stage.close();
				
				Parent root = FXMLLoader.load(getClass().getResource("/taskfacil/views/TaskFacilPortal.fxml"));
				Scene scene = new Scene(root);
				Stage stagePortal = new Stage();
				
				TaskFacilPortalController controller = FXMLLoader.load(getClass().getResource("/taskfacil/views/TaskFacilPortal.fxml"));
				controller.setUser(userLogin);
				
				stagePortal.setScene(scene);
				stagePortal.setTitle("TaskFacil - Portal");
				stagePortal.show();
			}
		}
	}

	private boolean validateLogin(String pEmail){
		if(!User.isEmail(pEmail)){
			System.out.println("Usuário Invalido.");
			return false;
		}

		if(txtSenhaLogin.getLength() < 4){
			System.out.println("Senha Inválida.");
			return false;
		}

		return true;
	}

}

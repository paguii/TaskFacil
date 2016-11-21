package taskfacil.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import taskfacil.db.FactoryEntityManager;
import taskfacil.models.User;

public class TaskFacilCadastrarController implements Initializable{

	@FXML
	private Button btnCadastrar;
	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtEmail;
	@FXML
	private PasswordField txtSenha;

	private User newUser;
	private Alert alert;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("InformaÃ§Ã£o");
		alert.setHeaderText("Cadastro de novo usuÃ¡rio");
	}

	@FXML
	public void registerNewUser(){
		newUser = new User();
		newUser.setName(txtNome.getText());
		newUser.setEmail(txtEmail.getText());
		newUser.setPassword(txtSenha.getText());

		if(validateCadastro(newUser)){
			EntityManager manager = FactoryEntityManager.getEntityManager();

			try {
				manager.getTransaction().begin();
				manager.persist(newUser);
				manager.getTransaction().commit();
				manager.close();
				
				alert.setContentText("Seu usuÃ¡rio foi cadastrado com sucesso.");
				alert.showAndWait();

				txtNome.clear();
				txtEmail.clear();
				txtSenha.clear();

				Stage stage  = (Stage) btnCadastrar.getScene().getWindow();
				stage.close();
			} catch (PersistenceException e) {
				alert.setAlertType(AlertType.ERROR);
				alert.setContentText("O e-mail inserido já foi cadastrado");
				alert.showAndWait();
				
				alert.setAlertType(AlertType.INFORMATION);
			}


		}
	}

	private boolean validateCadastro(User pNewUser){
		if(txtNome.getLength() < 5){
			alert.setContentText("Nome digitado Ã© muito curto.");
			alert.showAndWait();

			System.out.println("Nome muito curto.");
			return false;
		}

		if(!User.isEmail(txtEmail.getText())){
			alert.setContentText("Digite um email vÃ¡lido.\nExemplo: samuka@batmacampineiro.com");
			alert.showAndWait();
			System.out.println("Email Invalido.");
			return false;
		} else{

		}

		if(txtSenha.getLength() < 4){
			alert.setContentText("Senha deve conter pelo menos 4 caracteres.\nExemplo: 1234");
			alert.showAndWait();
			System.out.println("Senha Curta.");
			return false;
		}
		return true;
	}
}

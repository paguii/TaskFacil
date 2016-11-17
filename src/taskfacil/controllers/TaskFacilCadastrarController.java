package taskfacil.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import taskfacil.ex.DuplicatedValues;
import taskfacil.db.FactoryEntityManager;
import taskfacil.ex.DuplicatedValues;
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	
	@FXML
	public void registerNewUser(){
		newUser = new User();
		newUser.setName(txtNome.getText());
		newUser.setEmail(txtEmail.getText());
		newUser.setSenha(txtSenha.getText());
		
		if(validateCadastro(newUser)){
			EntityManager manager = FactoryEntityManager.getEntityManager();
			
			try {
				manager.getTransaction().begin();
				manager.persist(newUser);
				manager.getTransaction().commit();
			} catch (DuplicatedValues e) {
				
			}
			
			manager.close();
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Informação");
			alert.setHeaderText("Cadastro de novo usuário");
			alert.setContentText("Seu usuário foi cadastrado com sucesso.");

			alert.showAndWait();
			
			txtNome.clear();
			txtEmail.clear();
			txtSenha.clear();
		}
	}

	private boolean validateCadastro(User pNewUser){
		if(txtNome.getLength() < 5){
			System.out.println("Nome muito curto.");
			return false;
		}
		
		if(!User.isEmail(txtEmail.getText())){
			System.out.println("Email Invalido.");
			return false;
		} else{

		}
		
		if(txtSenha.getLength() < 4){
			System.out.println("Senha Curta.");
			return false;
		}
		return true;
	}
}

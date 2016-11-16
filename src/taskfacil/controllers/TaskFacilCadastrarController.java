package taskfacil.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
		if(validateCadastro()){
			
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("TaskFacil");
			EntityManager manager = factory.createEntityManager();
			
			newUser.setName(txtNome.getText());
			newUser.setEmail(txtEmail.getText());
			newUser.setSenha(txtSenha.getText());
			
			manager.getTransaction().begin();
			manager.persist(newUser);
			manager.getTransaction().commit();
			
			manager.close();
		}
	}

	private boolean validateCadastro(){
		if(txtNome.getLength() < 5){
			System.out.println("Nome muito curto.");
			return false;
		}
		
//		if(newUser.isEmail(txtEmail.getText())){
//			System.out.println("Email Certo.");
//			return false;
//		}
		
		if(txtSenha.getLength() < 4){
			System.out.println("Senha Curta.");
			return false;
		}
		return true;
	}
}

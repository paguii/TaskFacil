package taskfacil.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import taskfacil.models.User;

public class TaskFacilCadastrarController implements Initializable{

	@FXML
	private Button btnCadastrar;
	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtSenha;

	private Stage dialogStage;
	private boolean buttonCadastrarClicked = false;
	private User usuario;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean isButtonCadastrarClicked() {
		return buttonCadastrarClicked;
	}

	public void setButtonCadastrarClicked(boolean buttonConfirmarClicked) {
		this.buttonCadastrarClicked = buttonConfirmarClicked;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

}

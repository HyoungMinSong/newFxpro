package ex5;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class Opener {
	private Stage primaryStage;
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	public void regOpen() {
		
	Stage regStage = new Stage(); //매개변수로 받는 스테이지가 없으므로 스테이지 생성.
	FXMLLoader loader = new FXMLLoader(getClass().getResource("regForm.fxml"));
	Parent regForm=null;
	try {
		regForm = loader.load();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	RegController regCon = loader.getController(); //회원등록 컨트롤러에 해당 Parent를 넣어야 하기 때문에 컨트롤러 찾으면서
	regCon.setRegForm(regForm);	//세터를 통해 Parent 넣기.
//	regCon.setStage(regStage); //걍 스테이지 넣어도 되네 ?
	
	ComboBox<String> cb = (ComboBox<String>)regForm.lookup("#ageCombo");
	cb.getItems().addAll("10대 이하","20대","30대","40대","50대","60대 이상");
	Scene scene = new Scene(regForm);
	regStage.setTitle("회원가입 화면");
	regStage.setScene(scene);
	regStage.show();
	
}
	
	
	public void menuOpen() {
		//로그인 화면의 Stage에 Menu.fxml 화면을 실행하겠다.
		
				FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
				Parent menuForm = null;
				
				try {
					menuForm = loader.load();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				Scene scene = new Scene(menuForm);  //원래 있던 스테이지(메인 스테이지)에 따른 씬 넣기.
				primaryStage.setTitle("메인 화면");
				primaryStage.setScene(scene);
				primaryStage.show();
				
	}
			
}

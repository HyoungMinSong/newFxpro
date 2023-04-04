package seatView;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class seatViewService {
	private seatViewDAO dao;
	private String seatInfoData;
	
	public seatViewService() {
		seatViewDAO dao = new seatViewDAO();
		this.dao = dao ;
	}
	
	public ArrayList<seatViewDTO> selectUseSeat() {
		return dao.selectUseSeat();
	}
	//Fx:id 를 추출하는 메소드
	public String idExtract(ActionEvent e) {
		String idData = e.getSource().toString();
		System.out.println(e.getSource());
		String tmp[] = idData.split("=");
		String b = tmp[1];
		String tmp2[] = b.split(",");
		String fxId= "#"+tmp2[0]; 
		System.out.println("fx:id는 : " +fxId);
		
		return fxId;
	}
	public void startSeat(Parent seatView,String member_id) {
		//
		ArrayList<seatViewDTO> dataList = selectUseSeat(); //사용중인 좌석을 가져와서
		for(int i = 1 ; i <25;i++) {
			String seat = "#s"+i;
			Button btn =(Button)seatView.lookup(seat);
			btn.setStyle("-fx-background-color:#D3D3D3;"+"-fx-border-color:BLACK");
			btn.setPrefSize(70, 70);
		}
		
		for(seatViewDTO data : dataList) {
			//사용중이지 않은 데이터를 가져와야함.
			String seatName = data.getSeat_num();
			String useSeat = "#"+seatName;
			String memberNum = data.getMember_id();
			String member_time = data.getMember_time();
			
			Button btn2 =(Button)seatView.lookup(useSeat);
			btn2.setStyle("-fx-background-color:ORANGE;"+"-fx-border-color:BLACK");
			btn2.setText(member_time+"분");
			btn2.setPrefSize(70, 70);
		//	btn2.setText();
		}
		
		TextField member_id_field = (TextField)seatView.lookup("#member_id_field");
		TextField member_time_info = (TextField)seatView.lookup("#member_time_info");
		member_id_field.setText(member_id);
		
		String member_time =dao.getMember_time(member_id);
		System.out.println(member_time);
		member_time_info.setText(member_time);
	}//startSeat

	public void buttonSelect(ActionEvent e,Parent seatView) {
		ArrayList<seatViewDTO> dataList = selectUseSeat();//arrayList
		for(int i = 1 ; i <25;i++) {
			String seat = "#s"+i;
			Button btn =(Button)seatView.lookup(seat);
			btn.setStyle("-fx-background-color:#D3D3D3;"+"-fx-border-color:BLACK");
			btn.setPrefSize(70, 70);
		}
		
		for(seatViewDTO data : dataList) {
			//사용중이지 않은 데이터를 가져와야함.
			String useSeat = "#"+data.getSeat_num();
			System.out.println("useSeat 는 ? " + useSeat);
			Button btn2 =(Button)seatView.lookup(useSeat);
			btn2.setStyle("-fx-background-color:ORANGE;"+"-fx-border-color:BLACK");
			btn2.setPrefSize(70, 70);
		}
		
		String ButtonfxId = idExtract(e);
		Button btn = (Button)seatView.lookup(ButtonfxId);
		btn.setStyle("-fx-background-color:#6699CC;"+"-fx-border-color:BLACK");
		btn.setPrefSize(70, 70);
		//우측 하단 TextField (seatInfo) 에 나오게 함
		TextField seatInfo = (TextField)seatView.lookup("#seatInfo");
		ButtonfxId =  ButtonfxId.replaceAll("#", "");
		seatInfo.setText(ButtonfxId);
		
		//////
		this.seatInfoData = ButtonfxId;
		
	}
	
	public void seatNext(Parent seatView) {
		/*
		TextField member_id_field = (TextField)seatView.lookup("#member_id_field");
		TextField seatInfo = (TextField)seatView.lookup("seatInfo");
		*/
		
		String YN = dao.checkSeatUse(seatInfoData); //buttonSelect 메소드를 실행하면 seatInfoData 값이 바뀜 
		if(YN.equals("Y")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			String contentText = "이미 사용중인 좌석입니다.";
			alert.setHeaderText("알림");
			alert.setContentText(contentText);
			alert.show();
			
			
		}else if(YN.equals("N")) {
			/*
			 opener 구현할 곳  
			 next 버튼을 눌렀을때 사용중일경우 위의 조건식이 실행되어 알림메세지가 뜨고, 
			 자리가 비었다면 해당 창이 실행 됨 
			 */
			
		}
	}

}


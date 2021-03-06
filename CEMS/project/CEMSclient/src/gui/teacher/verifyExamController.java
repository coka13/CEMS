package gui.teacher;

import java.util.ArrayList;

import client.App_client;
import common.DataPacket;
import common.ExamDone;
import control.ClientControl;
import control.GetCopyOfExamControl;
import control.PageProperties;
import control.SceneController;
import control.StudentControl;
import control.UserControl;
import control.examDoneControl;
import gui.TableEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class verifyExamController {
	SceneController sceen;
	@FXML // fx:id="ap"
	private AnchorPane ap;
	private ArrayList<ExamDone> examDoneList;
	private ArrayList<Button> buttonList = new ArrayList<>();
	@FXML
	private AnchorPane gradeAnchorPane;
	@FXML
	private Label gradeCommentsLbl;
	@FXML
	private TextArea gradeTextArea;
	@FXML
	private Label seccessLbl;
	@FXML
	private Button gradeSend;
	@FXML
	private TextField newGrade;
	@FXML
	private Label errorLbl;
	@FXML
	private TextField studentIDTxt;
	@FXML
	private Button editGrade;
	@FXML
	private TextField examID;
	@FXML
	private TableView<TableEntry> tableView;
	@FXML
	public TableColumn<TableEntry, ?> colExamID;
	@FXML
	public TableColumn<TableEntry, ?> colStudentID;
	@FXML
	public TableColumn<TableEntry, ?> colStartTime;
	@FXML
	public TableColumn<TableEntry, ?> colEndTime;
	@FXML
	public TableColumn<TableEntry, ?> colGrade;
	@FXML
	public TableColumn<TableEntry, ?> colGetCopy;
	@FXML
	public TableColumn<TableEntry, ?> colStatus;
	@FXML
	public TableColumn<TableEntry, ?> colCheating;
	private int index = -1;

	@FXML
	public void initialize() {
		sceen = new SceneController(PageProperties.Page.VERIFY_EXAM, ap);
		sceen.AnimateSceen(SceneController.ANIMATE_ON.LOAD);
		// tableView.getColumns().addAll(colExamID,colStudentID,colStartTime,colEndTime,colGrade,colGetCopy,colStatus);
		gradeAnchorPane.setVisible(false);
		errorLbl.setVisible(false);
		seccessLbl.setVisible(false);
		colExamID.setCellValueFactory(new PropertyValueFactory<>("col1"));
		colStudentID.setCellValueFactory(new PropertyValueFactory<>("col2"));
		colStartTime.setCellValueFactory(new PropertyValueFactory<>("col3"));
		colEndTime.setCellValueFactory(new PropertyValueFactory<>("col4"));
		colGrade.setCellValueFactory(new PropertyValueFactory<>("col5"));
		colGetCopy.setCellValueFactory(new PropertyValueFactory<>("col6"));
		colStatus.setCellValueFactory(new PropertyValueFactory<>("col7"));
		colCheating.setCellValueFactory(new PropertyValueFactory<>("col8"));
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(UserControl.ConnectedUser);
		DataPacket dataPacket = new DataPacket(DataPacket.SendTo.SERVER, DataPacket.Request.GET_FOR_VERIFY, parameters,
				null, true);
		ClientControl.getInstance().accept(dataPacket);
		if (examDoneControl.getExamDoneLIst() != null) {
			System.out.println("workkkkkkkk");
			examDoneList = examDoneControl.getExamDoneLIst();
			examDoneControl.setExamDoneLIst(null);
		} else
			System.out.println("doesnt work");
		tableView.setItems(getRequests());
	}

	public void handleOnAction(MouseEvent event) {
		errorLbl.setVisible(false);
		seccessLbl.setVisible(false);
		if (event.getSource() == gradeSend) {
			if (studentIDTxt.getText().toString().equals("")|| newGrade.getText().toString().equals("")
					|| Integer.parseInt(newGrade.getText().toString())<0||Integer.parseInt(newGrade.getText().toString())>100
					|| examID.getText().toString().equals(""))
				errorLbl.setVisible(true);
			else {
				ArrayList<Object> parameters = new ArrayList<>();
				parameters.add(studentIDTxt.getText().toString());
				parameters.add(newGrade.getText().toString());
				parameters.add(gradeTextArea.getText());/// need to change in serverrr
				parameters.add(examID.getText().toString());
				DataPacket dataPacket = new DataPacket(DataPacket.SendTo.SERVER, DataPacket.Request.EDIT_GRADE,
						parameters, null, true);
				ClientControl.getInstance().accept(dataPacket);
				if(StudentControl.gradeChanged==1) {
					gradeAnchorPane.setVisible(false);
					seccessLbl.setVisible(true);
				}
			}
		}
		if(event.getSource()==editGrade) {
			gradeAnchorPane.setVisible(true);
		}
	}

	public ObservableList<TableEntry> getRequests() {
		ObservableList<TableEntry> requests = FXCollections.observableArrayList();
		// LocalDate date = LocalDate.of(1995, 10, 28);
		for (ExamDone entry : examDoneList) {
			// create a HBox
			final HBox status = new HBox();
			final Button getCopy = new Button("get copy");
			buttonList.add(getCopy);
			// final Button getcopy=new Button();
			try {
				// getcopy.setAlignment(Pos.CENTER);
				// setAlignment
				status.setAlignment(Pos.CENTER);
				getCopy.setAlignment(Pos.CENTER);

				// create a label
				Label label = new Label("Approve?");
				status.getChildren().add(label);
				Button Y = new Button("Y");
				Button N = new Button("N");
				EventHandler<ActionEvent> getCopyHandler = new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						System.out.println("the size is " + buttonList.size());
						for (int i = 0; i < buttonList.size(); i++) {
							if (buttonList.get(i).equals(getCopy)) {
								index = i;
								System.out.println("the index is " + index);
								break;
							}

						}
						GetCopyOfExamControl.studentID = Integer.valueOf(examDoneList.get(index).getuID());
						// System.out.println(examDoneList.get(index).getuID()+"----");
						System.out.println(GetCopyOfExamControl.studentID + " eeeeeeeeeeeeeeee");
						GetCopyOfExamControl.eID = Integer.parseInt(entry.getEiID());
						AnchorPane page = SceneController.getPage(PageProperties.Page.GET_COPY_OF_EXAM);
						App_client.pageContainer.setCenter(page);
					}
				};
				EventHandler<ActionEvent> YHandler = new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						System.out.println("Approved");
						label.setText("Approved");
						label.setStyle(("-fx-text-fill: green;-fx-font-weight: bold"));
						status.getChildren().remove(2);
						status.getChildren().remove(1);

						String edID = entry.getEdID();
						ArrayList<Object> parameter = new ArrayList<Object>();
						parameter.add(edID);
						DataPacket datapacket = new DataPacket(DataPacket.SendTo.SERVER,
								DataPacket.Request.APPROVED_GRADE, parameter, null, true);
						ClientControl.getInstance().accept(datapacket);

					}
				};
				EventHandler<ActionEvent> NHandler = new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						System.out.println("Denied");
						label.setText("Denied");
						label.setStyle(("-fx-text-fill: red;-fx-font-weight: bold"));
						status.getChildren().remove(2);
						status.getChildren().remove(1);
						String edID = entry.getEdID();
						ArrayList<Object> parameter = new ArrayList<Object>();
						parameter.add(edID);
						DataPacket datapacket = new DataPacket(DataPacket.SendTo.SERVER,
								DataPacket.Request.DISAPPROVED_GRADE, parameter, null, true);
						ClientControl.getInstance().accept(datapacket);
					}
				};
				Y.setOnAction(YHandler);
				N.setOnAction(NHandler);
				getCopy.setOnAction(getCopyHandler);

				// add buttons to HBox
				status.getChildren().add(Y);
				status.getChildren().add(N);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			requests.add(new TableEntry(entry.getEiID(), entry.getuID(), entry.getStartTime(), entry.getEndTime(),
					entry.getGrade(), getCopy, status, entry.getIsCheating()));

		}

		return requests;
	}

}
package gui;

import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.Timer;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;

import client.App_client;
import common.DataPacket;
import common.Principal;
import common.Student;
import common.Teacher;
import control.ClientControl;
import control.ExamControl;
import control.ManageOngoingExams;
import control.PageProperties;
import control.PrincipalControl;
import control.SceneController;
import control.UserControl;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class MainPageController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="button_menu"
	private AnchorPane ap; // Value injected by FXMLLoader

	@FXML // fx:id="button_menu"
	private ImageView button_menu; // Value injected by FXMLLoader

	@FXML
	private Label label_bar_welcome;

	@FXML
	private Label label_bar_roletype;

	@FXML
	private BorderPane page_box;

	@FXML
	private AnchorPane box_left_of_menu;

	@FXML
	private AnchorPane menu_left_box;

	@FXML
	private Pane menu_box;

	@FXML
	private Pane box_left;
	@FXML
	private Pane box_right;

	@FXML
	private Pane bar_left_box;

	@FXML
	private Pane bar_right_box;

	@FXML
	private JFXHamburger hamBurger = new JFXHamburger();

	@FXML
	private JFXButton displayStatisticalReport = new JFXButton();
	@FXML
	private JFXButton checkRequest = new JFXButton();
	@FXML
	private JFXButton information = new JFXButton();
	@FXML
	private JFXButton createExam = new JFXButton();

	@FXML
	private JFXButton startExam = new JFXButton();

	@FXML
	private JFXButton homePage = new JFXButton();

	@FXML
	private JFXButton displayStatisticalReportTeacher = new JFXButton();

	@FXML
	private JFXButton takeExam = new JFXButton();
	@FXML
	private JFXButton historyOfExams = new JFXButton();
	@FXML
	private JFXButton verifyExam = new JFXButton();

	@FXML
	private JFXButton manageExams = new JFXButton();

	@FXML
	private JFXButton manageQuestions = new JFXButton();
///////////////////////////////////////////////DANIEL///////////////////////////////////////////////
	@FXML
	private JFXButton ManageOngoingExam = new JFXButton();
///////////////////////////////////////////////DANIEL///////////////////////////////////////////////
	/*
	 * @FXML private MenuButton main_menu;
	 * 
	 * @FXML private MenuItem displayStatisticalReport;
	 * 
	 * @FXML private MenuItem checkRequest;
	 * 
	 * @FXML private MenuItem information;
	 * 
	 * @FXML private MenuItem createExam;
	 * 
	 * @FXML private MenuItem displayStatisticalReportTeacher;
	 * 
	 * @FXML private MenuItem takeExam;
	 * 
	 * @FXML private MenuItem manageExams;
	 * 
	 * @FXML private MenuItem historyOfExams;
	 * 
	 * @FXML private MenuItem verifyExam;
	 * 
	 * @FXML private MenuItem manageQuestions;
	 * 
	 * @FXML private MenuItem startExam;
	 * ///////////////////////////////////////////////DANIEL////////////////////////
	 * ///////////////////////
	 * 
	 * @FXML private MenuItem ManageOngoingExam;
	 */
///////////////////////////////////////////////DANIEL///////////////////////////////////////////////
	// rostik v10
	@FXML
	private Label label_notification;
	@FXML
	private ImageView image_notification;
	// rostik v10

	@FXML // This method is called sby the FXMLLoader when initialization is complete
	void initialize() {
		SceneController.primaryStage.setMinWidth(1193);
		  SceneController.primaryStage.setMinHeight(868);
		  
		  SceneController.primaryStage.setMaxWidth(1193);
		  SceneController.primaryStage.setMaxHeight(868);
		  SceneController.primaryStage.setWidth(1193);
		  SceneController.primaryStage.setHeight(868);
		/*// set sizes
		SceneController.primaryStage.setMinWidth(850);
		SceneController.primaryStage.setMinHeight(750);

		SceneController.primaryStage.setMaxWidth(850);
		SceneController.primaryStage.setMaxHeight(750);
		SceneController.primaryStage.setWidth(850);
		SceneController.primaryStage.setHeight(750);*/
		App_client.pageContainer = page_box; // set page center container to load inside it other pagess

		displayStatisticalReport.setManaged(false);
		checkRequest.setManaged(false);
		information.setManaged(false);
		createExam.setManaged(false);
		displayStatisticalReportTeacher.setManaged(false);
		takeExam.setManaged(false);
		historyOfExams.setManaged(false);
		verifyExam.setManaged(false);
		ManageOngoingExam.setManaged(false);
		manageExams.setManaged(false);
		manageQuestions.setManaged(false);
		startExam.setManaged(false);

		// rostik v10
		label_notification.setVisible(false);
		image_notification.setVisible(false);
		// rostik v10

		// Hamburger tranlate JFoenix
		HamburgerSlideCloseTransition trans = new HamburgerSlideCloseTransition(hamBurger);
		trans.setRate(-1);
		box_left.setTranslateX(-235); // width of slider is 235 so translate to hide

		hamBurger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
			if (trans.getRate() == 1) {
				TranslateTransition slide = new TranslateTransition(); // Smooth transition of the Menu items
				slide.setDuration(Duration.seconds(0.4));
				slide.setNode(box_left);
				slide.setToX(-235);
				slide.play();
				box_left.setTranslateX(0);
			} else {
				TranslateTransition slide = new TranslateTransition();
				slide.setDuration(Duration.seconds(0.4));
				slide.setNode(box_left);
				slide.setToX(0);
				slide.play();
				box_left.setTranslateX(-235);
			}
			trans.setRate(trans.getRate() * -1);
			trans.play();
		});

		// animate page on load
		SceneController sceen = new SceneController(PageProperties.Page.MAIN_PAGE, ap);
		sceen.AnimateSceen(SceneController.ANIMATE_ON.LOAD);

		ChangeListener<Number> listener = new ChangeListener<Number>() {
			private Point2D stageSize = null;
			private Point2D previousStageSize = new Point2D(SceneController.primaryStage.getWidth(),
					SceneController.primaryStage.getHeight());

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				if (stageSize == null) {
					Platform.runLater(() -> {
						System.out.printf("Old: (%.1f, %.1f); new: (%.1f, %.1f)%n", previousStageSize.getX(),
								previousStageSize.getY(), stageSize.getX(), stageSize.getY());
						previousStageSize = stageSize;
						// box_left.setMinSize((stageSize.getX() - 800) / 4, 100);
						// box_right.setMinSize((stageSize.getX() - 800) / 2, 100);
						// bar_left_box.setMinSize((stageSize.getX() - 800) / 4, 100);
						// box_left_of_menu.setMinSize((stageSize.getX() - 800) / 4, 100);
						// bar_right_box.setMinSize((stageSize.getX() - 800) / 2, 100);
						stageSize = null;
					});

				}
				stageSize = new Point2D(SceneController.primaryStage.getWidth(),
						SceneController.primaryStage.getHeight());
			}
		};

		SceneController.primaryStage.widthProperty().addListener(listener);
		SceneController.primaryStage.heightProperty().addListener(listener);

//////////////////////////////////////////////rostik v10 /////////////////////////////////
// check for notifications to show notification icon
		Timer tm = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						if (UserControl.ConnectedUser instanceof Principal
								&& PrincipalControl.isExtraTimeRequest_Recived()) {
							label_notification.setText(UserControl.getNotipications() + "");
							label_notification.setVisible(true);
							image_notification.setVisible(true);

						} else if (UserControl.ConnectedUser instanceof Teacher
								&& ExamControl.isNotifiedAboutExtraTime()) {
							System.out.println("Showeddd ");
							label_notification.setText(UserControl.getNotipications() + "");
							label_notification.setVisible(true);
							image_notification.setVisible(true);
						} else {
//label_notification.setText(UserControl.setNotipications(UserControl.getNotipications()-1) + "");
							label_notification.setVisible(false);
							image_notification.setVisible(false);
						}

						if (UserControl.ConnectedUser instanceof Teacher && ManageOngoingExams.isOngoingExams != null
								&& ManageOngoingExams.isOngoingExams == true) {
							startExam.setManaged(false);
							startExam.setVisible(false);
						} else if (UserControl.ConnectedUser instanceof Teacher)
						{
							startExam.setVisible(true);
							startExam.setManaged(true);
						}
						
					}
				});
			}
		});
		tm.start();
// rostik v10 ///////////////////////////////////////////////////////////

		if (UserControl.ConnectedUser instanceof Student) {
			SceneController.primaryStage.setTitle("Cems: Student - home page");
			takeExam.setManaged(true);
	
			historyOfExams.setManaged(true);
			label_bar_welcome.setText("Welcome back, " + UserControl.ConnectedUser.getFirstName() + " "
					+ UserControl.ConnectedUser.getLastName());
			label_bar_roletype.setText("(Student)");
			

			// load Student home page
			AnchorPane page = SceneController.getPage(PageProperties.Page.HomePage_Student);
			page_box.setCenter(page);

		} else if (UserControl.ConnectedUser instanceof Teacher) {
			SceneController.primaryStage.setTitle("Cems: Teacher - home page");

			displayStatisticalReportTeacher.setManaged(true);

			verifyExam.setManaged(true);
			manageQuestions.setManaged(true);
			ManageOngoingExam.setManaged(true);
			manageExams.setManaged(true);

			if (ManageOngoingExams.isOngoingExams != null && ManageOngoingExams.isOngoingExams == true) {
			} else
				startExam.setManaged(true);

			label_bar_welcome.setText("Welcome back, " + UserControl.ConnectedUser.getFirstName() + " "
					+ UserControl.ConnectedUser.getLastName());
			label_bar_roletype.setText("(Teacher)");

			// load Teacher home page
			AnchorPane page = SceneController.getPage(PageProperties.Page.HomePage_Teacher);
			page_box.setCenter(page);

		} else if (UserControl.ConnectedUser instanceof Principal) {
			SceneController.primaryStage.setTitle("Cems: Principal - home page");
			displayStatisticalReport.setManaged(true);
			checkRequest.setManaged(true);
			information.setManaged(true);
			label_bar_welcome.setText("Welcome back, " + UserControl.ConnectedUser.getFirstName() + " "
					+ UserControl.ConnectedUser.getLastName());
			label_bar_roletype.setText("(Principal)");

			// load Principal home page
			AnchorPane page = SceneController.getPage(PageProperties.Page.HomePage_Principal);
			page_box.setCenter(page);
		}
	}

	@FXML
	void button_homepage_clicked(ActionEvent event) {
		if (UserControl.ConnectedUser instanceof Student) {
			// load Student home page
			AnchorPane page = SceneController.getPage(PageProperties.Page.HomePage_Student);
			page_box.setCenter(page);

		} else if (UserControl.ConnectedUser instanceof Teacher) {

			// load Teacher home page
			AnchorPane page = SceneController.getPage(PageProperties.Page.HomePage_Teacher);
			page_box.setCenter(page);

		} else if (UserControl.ConnectedUser instanceof Principal) {

			// load Principal home page
			AnchorPane page = SceneController.getPage(PageProperties.Page.HomePage_Principal);
			page_box.setCenter(page);
		}
	}

	// teacher
	@FXML
	void button_create_exam(ActionEvent event) {
		System.out.println("clicked_createExam");
		AnchorPane page = SceneController.getPage(PageProperties.Page.CHOSSE_EXAM_TYPE);
		page_box.setCenter(page);
	}

	// student
	@FXML
	void button_history_of_exams_student_clicked(ActionEvent event) {
		AnchorPane page = SceneController.getPage(PageProperties.Page.History_Of_Exams_Student);
		page_box.setCenter(page);

	}

	@FXML
	public void button_take_exam_clicked(ActionEvent event) {
		System.out.println("clicked");
		AnchorPane page = SceneController.getPage(PageProperties.Page.GET_EXAM);
		// Pane screen = object.Sc();
		page_box.setCenter(page);

		// page_box.setCenter(page);

	}

	@FXML
	public void button_manage_exams_clicked(ActionEvent event) {
		System.out.println("clicked");
		AnchorPane page = SceneController.getPage(PageProperties.Page.MANAGE_EXAMS);
		// Pane screen = object.Sc();
		page_box.setCenter(page);
	}

	@FXML
	public void button_verify_exam_clicked(ActionEvent event) {
		System.out.println("clicked");
		AnchorPane page = SceneController.getPage(PageProperties.Page.VERIFY_EXAM);
		// Pane screen = object.Sc();
		page_box.setCenter(page);
	}

	@FXML
	void buttton_statistical_report(ActionEvent event) {
		AnchorPane page = SceneController.getPage(PageProperties.Page.STATISTICAL_REPORTS);
		// Pane screen = object.Sc();
		page_box.setCenter(page);

	}

	@FXML
	void button_menu_clicked(MouseEvent event) {
		System.out.println("clicked");
		// AnchorPane page =
		// SceneController.getPage(PageProperties.Page.ADD_NEW_QUESTION);
		// Pane screen = object.Sc();
		// page_box.setCenter(page);
		// System.out.println(page_box.getCenter());
		// SceneController.primaryStage.setMinWidth(800);
		// SceneController.primaryStage.setMinHeight(700);
	}

	@FXML
	void button_information(ActionEvent event) {
		AnchorPane page = SceneController.getPage(PageProperties.Page.INFO_PAGE);
		page_box.setCenter(page);

	}

	@FXML
	void button_extra_time_requests(ActionEvent event) {
		AnchorPane page = SceneController.getPage(PageProperties.Page.EXTRA_TIME_REQUESTS);
		page_box.setCenter(page);
	}

	@FXML
	void buttton_statistical_teacher_report(ActionEvent event) {
		AnchorPane page = SceneController.getPage(PageProperties.Page.HISTOGRAM);
		page_box.setCenter(page);

	}

	@FXML
	void button_start_exam(ActionEvent event) {
		System.out.println("clicked_startExam");
		AnchorPane page = SceneController.getPage(PageProperties.Page.START_EXAM);
		page_box.setCenter(page);

	}

	@FXML
	public void button_manage_questions_clicked(ActionEvent event) {
		System.out.println("manage_questions_clicked");
		AnchorPane page = SceneController.getPage(PageProperties.Page.MANAGE_QUESTIONS);
		page_box.setCenter(page);

		// page_box.setCenter(page);

	}

///////////////////////////////////////////////DANIEL///////////////////////////////////////////////
	@FXML
	void manage_ongoing_exam(ActionEvent event) {
		AnchorPane page = SceneController.getPage(PageProperties.Page.MANAGE_ONGOING_EXAM);
		page_box.setCenter(page);
	}
///////////////////////////////////////////////DANIEL///////////////////////////////////////////////

	@FXML
	void button_logout_clicked(MouseEvent event) {
		System.out.println("clicked to logout");
		ArrayList<Object> parameter = new ArrayList<>();
		parameter.add(UserControl.ConnectedUser);
		DataPacket dataPacket = new DataPacket(DataPacket.SendTo.SERVER, DataPacket.Request.LOGOUT, parameter, null,
				true);
		ClientControl.getInstance().accept(dataPacket);// send and wait for response from server
		// will recive message from server and set user to null
		UserControl.resetAll();
		// break the connection with the server
		ClientControl.getInstance().destroyInstance();

		// make animation and than load page
		SceneController sceen = new SceneController(PageProperties.Page.LOGIN, ap);
		sceen.LoadSceen(SceneController.ANIMATE_ON.UNLOAD);
	}

	@FXML
	void button_logout_entered(MouseEvent event) {

	}

	@FXML
	void button_logout_exited(MouseEvent event) {

	}
}

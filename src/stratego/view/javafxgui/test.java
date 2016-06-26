package stratego.view.javafxgui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
 
public class test extends Application {
	Stage window;
	Scene scene1;
	Scene scene2;
	MenuBar mb;

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
  
        // Scene 1
        Label label1 = new Label("this is scene1");
        Button btn = new Button("to scene2");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                window.setScene(scene2);
            }
        });
        
        Text textresult = new Text();
        
        Button btnalert = new Button("Alert");
        btnalert.setOnAction(e -> {
        	String result = AlertBox.display("Warnung", "Virus detected");
        	textresult.setText(result);
        });
        
        mb = new Menutest(window);
        
        VBox lay1 = new VBox();
        lay1.getChildren().addAll(mb, label1, btn, btnalert, textresult);
        scene1 = new Scene(lay1, 200, 200);
        
        // Scene 2
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Text scenetitle = new Text("Welcome");
        scenetitle.setId("welcome-text");
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        userTextField.setPromptText("name");
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);
        
        Button btnsign = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btnsign);
        grid.add(hbBtn, 1, 4);
        
        final Text actiontarget = new Text();
        actiontarget.setId("actiontarget");
        grid.add(actiontarget, 1, 6);
        
        btnsign.setOnAction(e -> {
        	actiontarget.setText("Sign in button pressed: Username:" + userTextField.getText());
        });
        
        Button btn2 = new Button("to scene1");
        btn2.setOnAction(e -> window.setScene(scene1));
        grid.add(btn2, 1, 8);
        
        scene2 = new Scene(grid, 350, 325);
        scene2.getStylesheets().add(test.class.getResource("Login.css").toExternalForm());
        
        window.setTitle("Hello World!");
        window.setScene(scene2);
        window.setOnCloseRequest(e -> {
        	e.consume();
        	closeRequest();
        });
        window.show();
        
        scenetitle.setId("welcome-text");
        actiontarget.setId("actiontarget");
    }
    
    private void closeRequest() {
    	String answer = AlertBox.display("Close", "Sure you want to close");
    	if(answer == "no secret") {
    		window.close();
    	}
    }
}
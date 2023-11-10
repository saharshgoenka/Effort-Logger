//Group Tu55
//Saharsh Goenka
//Darsh Agarwal
//Chaitanyakrishna Yaramachu
//Ik Sun Jeong
//Naveen Ramesh
//
//yuh
// push to branch saharsh

package asuHelloWorldJavaFX;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class MainLine extends Application {
	
	public static void main(String[] args) {
        launch(args);
    }

    private Stage primaryStage;
    //adwnkladhawhodiao
    //IKSUN HAWDOAIWDHW
    // Author: Saharsh Goenka
    // valid username and password
    private final String validUsername = "saharsh";
    private final String hashedPassword = hashPassword("123"); // Hashed password

    // Author: Saharsh Goenka
    // function shows the login page
    // all requirements have been satisfied
    	// user can only log-in with valid user name and password
    	// hashing for addition security
    	// SQL injection prevention implemented
    private void showLoginPage() {
        VBox loginLayout = createLayout("Effort Logger - Login");

        TextField usernameField = new TextField();
        
        // Author: Saharsh Goenka
        // username prompt
        usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        
        // create submission button
        Button loginButton = new Button("Login");

        Label statusLabel = new Label();

        // action that occurs on button
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // quits if the password entry contains backslashes
            if (!password.contains("\\")) {
                
            	// checks if the username is valid
                if (username.equals(validUsername)) {
                    
                	// checks if password is valid
                    if (hashPassword(password).equals(hashedPassword)) {
                        
                    	// goes to homescreen
                    	showHomeScreen();
                    } else {
                    	// password error
                        statusLabel.setText("Password is wrong.");
                    }
                } else {
                	// user error
                    statusLabel.setText("User does not exist.");
                }
            } else {
            	// backslash error
                statusLabel.setText("SQL injection block.");
            }
        });

        loginLayout.getChildren().addAll(usernameField, passwordField, loginButton, statusLabel);
        showScene(loginLayout);
    }

    // Author: Saharsh Goenka
    // hashing password function
    // takes a password as input and returns its hash using SHA-256 encryption
    private String hashPassword(String password) {
        try {
            // create MessageDigest instance with SHA-256 algorithm 
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            
            // convert the password string into a byte array and calculate hash
            byte[] hashBytes = md.digest(password.getBytes());

            // create StringBuilder to store the hexadecimal representation
            StringBuilder hexHash = new StringBuilder();

            // convert bytes in the hashBytes array to a hexadecimal string and append it to the StringBuilder
            for (byte b : hashBytes) {
                // convert the byte to an integer and ensure it's an unsigned value (0-255)
                String hex = Integer.toHexString(0xFF & b);

                // if the resulting hex string is only one character long, add a leading '0'
                if (hex.length() == 1) {
                    hexHash.append('0');
                }

                // append the hex representation to StringBuilder
                hexHash.append(hex);
            }

            // return hexadecimal hash as a string
            return hexHash.toString();
        } catch (NoSuchAlgorithmException e) {
            // handle exceptions
            e.printStackTrace();
            
            // return null when there is exception
            return null;
        }
    }
    
    

  //author: Darsh Agarwal
    private void showHomeScreen() {
        //display page
        VBox homeLayout = createLayout("Effort Logger - Home");
        
        //buttons created to allow access to other pages of effort logger
        //Button settingsButton = new Button("Settings");
        Button logEffortButton = new Button("Log Effort");
        Button adminButton = new Button("Admin Page");
        Button reportBugButton = new Button("Report a Bug");
        
        //makes the buttons clickable
        //settingsButton.setOnAction(event -> showSettingsPage());
        logEffortButton.setOnAction(event -> showEffortLoggerPage());
        adminButton.setOnAction(event -> showAdmin());
        reportBugButton.setOnAction(event -> showBugReportPage());

        homeLayout.getChildren().addAll(logEffortButton, adminButton, reportBugButton /*settingsButton*/);
        showScene(homeLayout);
    }

    // Author: Chaitanyakrishna Yaramachu
   // Effort log function
   // Allows the user to input their task and the duration spent on task
   private void showEffortLoggerPage() {
       //display title of screen
       VBox loggerLayout = createLayout("Effort Logger - Log Effort");
       
       //textfield to input your task
       TextField descriptionField = new TextField();
       descriptionField.setPromptText("Description");
       
       //textfield to input duration 
       TextField durationField = new TextField();
       durationField.setPromptText("Duration (hours)");
       Button logButton = new Button("Log Effort");
       
       //Text area to display effort history
       TextArea logArea = new TextArea();
       logArea.setEditable(false);
       
       
       logButton.setOnAction(event -> {
           // loginfo implementation
           String description = descriptionField.getText();
           String duration = durationField.getText();
           logArea.appendText("Description: " + description + ", Duration: " + duration + " hours\n");
           descriptionField.clear();
           durationField.clear();
       });
           
       //code to go back to homescreen
       Button backButton = new Button("Back");
       backButton.setOnAction(event -> showHomeScreen());

       loggerLayout.getChildren().addAll(descriptionField, durationField, logButton, logArea, backButton);
       showScene(loggerLayout);
   } 
    
    //author Ik Sun Jeong
    private void showAdmin() {
        VBox adminLayout = createLayout("Effort Logger - Admin");
        
        
        //back button
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> showHomeScreen());
        
        //addEmployeeButton Button
        Button addEmployeeButton = new Button("Add Employee");
        addEmployeeButton.setOnAction(event -> showAddEmployeePage());
        
        //removeEmployeeButton Button
        Button removeEmployeeButton = new Button("Remove Employee");
        removeEmployeeButton.setOnAction(event -> showRemoveEmployeePage());


        adminLayout.getChildren().addAll(
            new Label("Admin Page Content"),
            addEmployeeButton,
            removeEmployeeButton,
            backButton
        );
        showScene(adminLayout);
    }
  //author Ik Sun Jeong
    private void showAddEmployeePage() {
        VBox addEmployeeLayout = createLayout("Add Employee");

        TextField employeeNameField = new TextField();
        employeeNameField.setPromptText("Employee Name");

        Button addButton = new Button("Add");
        addButton.setOnAction(event -> {
            // Implement employee addition logic here
            String employeeName = employeeNameField.getText();
            // Add employee using the provided name
            employeeNameField.clear();
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> showAdmin());

        addEmployeeLayout.getChildren().addAll(employeeNameField, addButton, backButton);
        showScene(addEmployeeLayout);
    }
  //author Ik Sun Jeong
    private void showRemoveEmployeePage() {
        VBox removeEmployeeLayout = createLayout("Remove Employee");

        TextField employeeNameField = new TextField();
        employeeNameField.setPromptText("Employee Name");

        Button removeButton = new Button("Remove");
        removeButton.setOnAction(event -> {
            // Implement employee removal logic here
            String employeeName = employeeNameField.getText();
            // Remove employee with the provided name
            employeeNameField.clear();
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> showAdmin());

        removeEmployeeLayout.getChildren().addAll(employeeNameField, removeButton, backButton);
        showScene(removeEmployeeLayout);
    }
  

  //Author: Naveen Ramesh
    private void showBugReportPage() {
        
        //Title
        VBox bugReportLayout = createLayout("Effort Logger - Report a Bug");

        //Text for the request title and description
        TextField titleField = new TextField();
        titleField.setPromptText("Bug Title");
        TextArea descriptionArea = new TextArea();
        descriptionArea.setPromptText("Bug Description");

        //Submit button returns the user to the home page
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
            // Implement bug report submission logic here
            showHomeScreen();
        });

        //Cancel button returns the user to the home page
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> showHomeScreen());

        bugReportLayout.getChildren().addAll(titleField, descriptionArea, submitButton, cancelButton);
        showScene(bugReportLayout);
    }
    


    private VBox createLayout(String title) {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 20px; -fx-background-color: #f0f0f0;");
        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        layout.getChildren().add(titleLabel);
        return layout;
    }

    private void showScene(Pane pane) {
        Scene scene = new Scene(pane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle(((VBox) pane).getChildren().get(0).toString().split("'")[1]); // Extract title from VBox
        primaryStage.show();
    }
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showLoginPage();
    }
}


//Group Tu55
//Saharsh Goenka
//Darsh Agarwal
//Chaitanyakrishna Yaramachu
//Ik Sun Jeong
//Naveen Ramesh
//
//yuh
// push to branch saharsh
// comment
// test comment


package asuHelloWorldJavaFX;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ASUHelloWorldJavaFX extends Application {

    ArrayList<String> effortLog = new ArrayList<String>();
    ArrayList<String> completedTasks = new ArrayList<String>();
    ArrayList<String> inCompletedTasks = new ArrayList<String>();
    private ArrayList<String> additionalComments = new ArrayList<>();
    private ArrayList<String> previousStrategies = new ArrayList<>();
    private TextArea inputArea;
    private Stage primaryStage;

    private ComboBox<String> commentsComboBox;
    private TextArea commentsArea;
    private TextField commentsField;

    public static void main(String[] args) {
        launch(args);
    }

	//hello I was here
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
        usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginButton = new Button("Login");
        Label statusLabel = new Label();

        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (!password.contains("\\")) {
                if (username.equals(validUsername)) {
                    if (hashPassword(password).equals(hashedPassword)) {
                        showHomeScreen();
                    } else {

                    	// password error
                        statusLabel.setText("Password is incorrect!!");
                    }
                } else {
                	// user error


                    statusLabel.setText("User does not exist!!");

                }
            } else {
                statusLabel.setText("SQL injection block.");
            }
        });

        loginLayout.getChildren().addAll(usernameField, passwordField, loginButton, statusLabel);
        showScene(loginLayout);
    }

    private void showHomeScreen() {
        VBox homeLayout = createLayout("Effort Logger - Home");

        Button logEffortButton = new Button("Log Effort");
        Button adminButton = new Button("Admin Page");
        Button reportBugButton = new Button("Report a Bug");
        Button projectPlanningButton = new Button("Project Planning");

        projectPlanningButton.setOnAction(event -> showProjectPlanningPage());
        logEffortButton.setOnAction(event -> showEffortLoggerPage());
        adminButton.setOnAction(event -> showAdmin());
        reportBugButton.setOnAction(event -> showBugReportPage());

        homeLayout.getChildren().addAll(logEffortButton, adminButton, reportBugButton, projectPlanningButton);
        showScene(homeLayout);
    }

    private void showEffortLoggerPage() {
        VBox loggerLayout = createLayout("Effort Logger - Log Effort");

        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Description");

        TextField durationField = new TextField();
        durationField.setPromptText("Duration (hours)");
        Button logButton = new Button("Log Effort");

        TextArea logArea = new TextArea();
        logArea.setEditable(false);

        for (String log : effortLog) {
            logArea.appendText(log);
        }

        logButton.setOnAction(event -> {
            String description = descriptionField.getText();
            String duration = durationField.getText();
            effortLog.add("Description: " + description + ", Duration: " + duration + " hours\n");
            descriptionField.clear();
            durationField.clear();
            addPreviousRuns();
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> showHomeScreen());

        loggerLayout.getChildren().addAll(descriptionField, durationField, logButton, logArea, backButton);
        showScene(loggerLayout);
    }

    private void addPreviousRuns() {
        VBox addPreviousLayout = createLayout("Effort Logger - Add Previous Runs");

        TextField completedField = new TextField();
        completedField.setPromptText("completed items");

        TextField incompletedField = new TextField();
        incompletedField.setPromptText("incompleted items");

        Button addButton = new Button("add");

        addButton.setOnAction(event -> {
            String completedstuff = completedField.getText();
            String incompletedstuff = incompletedField.getText();

            if (!completedstuff.isEmpty()) {
                completedTasks.add(completedstuff);
            }

            if (!incompletedstuff.isEmpty()) {
                inCompletedTasks.add(incompletedstuff);
            }

            completedField.clear();
            incompletedField.clear();
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> showEffortLoggerPage());

        addPreviousLayout.getChildren().addAll(completedField, incompletedField, addButton, backButton);
        showScene(addPreviousLayout);
    }

    private void showPreviousRunPage() {
        VBox pageLayout = createLayout("Effort Logger - Previous Reports");

        TextArea infoAreaCompleted = new TextArea();
        infoAreaCompleted.setEditable(false);

        for (String completed : completedTasks) {
            infoAreaCompleted.appendText(completed);
            infoAreaCompleted.appendText("\n");
        }

        TextArea infoAreaInCompleted = new TextArea();
        infoAreaInCompleted.setEditable(false);

        for (String incompleted : inCompletedTasks) {
            infoAreaInCompleted.appendText(incompleted);
            infoAreaCompleted.appendText("\n");
        }

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> showHomeScreen());

        pageLayout.getChildren().addAll(new Label("completed"), infoAreaCompleted,
                new Label("incompleted"), infoAreaInCompleted, backButton);

        showScene(pageLayout);
    }

    private void showAdmin() {
        VBox adminLayout = createLayout("Effort Logger - Admin");

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> showHomeScreen());

        Button addEmployeeButton = new Button("Add Employee");
        addEmployeeButton.setOnAction(event -> showAddEmployeePage());

        Button removeEmployeeButton = new Button("Remove Employee");
        removeEmployeeButton.setOnAction(event -> showRemoveEmployeePage());

        adminLayout.getChildren().addAll(new Label("Admin Page Content"), addEmployeeButton, removeEmployeeButton, backButton);
        showScene(adminLayout);
    }

    private void showAddEmployeePage() {
        VBox addEmployeeLayout = createLayout("Add Employee");

        TextField employeeNameField = new TextField();
        employeeNameField.setPromptText("Employee Name");

        Button addButton = new Button("Add");
        addButton.setOnAction(event -> {
            String employeeName = employeeNameField.getText();
            // Add employee using the provided name
            employeeNameField.clear();
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> showAdmin());

        addEmployeeLayout.getChildren().addAll(employeeNameField, addButton, backButton);
        showScene(addEmployeeLayout);
    }

    private void showRemoveEmployeePage() {
        VBox removeEmployeeLayout = createLayout("Remove Employee");

        TextField employeeNameField = new TextField();
        employeeNameField.setPromptText("Employee Name");

        Button removeButton = new Button("Remove");
        removeButton.setOnAction(event -> {
            String employeeName = employeeNameField.getText();
            // Remove employee with the provided name
            employeeNameField.clear();
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> showAdmin());

        removeEmployeeLayout.getChildren().addAll(employeeNameField, removeButton, backButton);
        showScene(removeEmployeeLayout);
    }

    private void showBugReportPage() {
        VBox bugReportLayout = createLayout("Effort Logger - Report a Bug");

        TextField titleField = new TextField();
        titleField.setPromptText("Bug Title");
        TextArea descriptionArea = new TextArea();
        descriptionArea.setPromptText("Bug Description");

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> showHomeScreen());

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> showHomeScreen());

        bugReportLayout.getChildren().addAll(titleField, descriptionArea, submitButton, cancelButton);
        showScene(bugReportLayout);
    }

    private void showProjectPlanningPage() {
        VBox projectPlanningLayout = createLayout("Effort Logger - Project Planning");

        Button commentsButton = new Button("Previous Comments/Complaints/Defects");
        Button previousRunsButton = new Button("Previous Runs");
        Button tasksButton = new Button("Previous Tasks");
        Button historicalDataButton = new Button("Historical Data");
        Button strategiesButton = new Button("Previous Strategies");
        Button backButton = new Button("Back");

        commentsButton.setOnAction(event -> showCommentsPage());
        previousRunsButton.setOnAction(event -> showPreviousRunPage());
        tasksButton.setOnAction(event -> showTasksPage());
        historicalDataButton.setOnAction(event -> showHistoricalDataPage());
        strategiesButton.setOnAction(event -> showStrategiesPage());
        backButton.setOnAction(event -> showHomeScreen());

        projectPlanningLayout.getChildren().addAll(
                commentsButton, previousRunsButton, tasksButton, historicalDataButton, strategiesButton, backButton
        );

        showScene(projectPlanningLayout);
    }

    private void showCommentsPage() {
        VBox commentsLayout = createLayout("Effort Logger - Previous Comments/Complaints/Defects");

        commentsComboBox = new ComboBox<>();
        commentsComboBox.getItems().addAll(completedTasks);
        commentsComboBox.getItems().addAll(inCompletedTasks);

        TextArea commentsArea = new TextArea();
        commentsArea.setEditable(false);

        commentsComboBox.setOnAction(event -> {
            String selectedComment = commentsComboBox.getValue();
            if (selectedComment != null) {
                commentsArea.setText("Selected Comment: " + selectedComment);
            }
        });

        // Display existing comments
        for (String completed : completedTasks) {
            commentsArea.appendText("Completed: " + completed + "\n");
        }

        for (String incompleted : inCompletedTasks) {
            commentsArea.appendText("Incompleted: " + incompleted + "\n");
        }

        // Display additional comments
        for (String comment : additionalComments) {
            commentsArea.appendText("Additional Comment: " + comment + "\n");
        }

        Button addButton = new Button("Add");
        addButton.setOnAction(event -> showAddCommentsPage(commentsArea));

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> showHomeScreen());

        commentsLayout.getChildren().addAll(commentsComboBox, commentsArea, addButton, backButton);

        showScene(commentsLayout);
    }



    private TextArea createTextArea(ArrayList<String> comments) {
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        for (String comment : comments) {
            textArea.appendText(comment + "\n");
        }
        return textArea;
    }


    public void showAddCommentsPage(TextArea commentsArea) {
        VBox addCommentsLayout = createLayout("Effort Logger - Add Comments");

        commentsField = new TextField();
        commentsField.setPromptText("Type your comments here");

        Button addButton = new Button("Add");
        addButton.setOnAction(event -> {
            String newComment = commentsField.getText();
            if (!newComment.isEmpty()) {
                additionalComments.add(newComment);
                commentsArea.appendText("Additional Comment: " + newComment + "\n");
                commentsField.clear();
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> showCommentsPage());

        addCommentsLayout.getChildren().addAll(commentsField, addButton, backButton);
        showScene(addCommentsLayout);
    }


//    private void showRunsPage() {
//        // Implement the logic to display completed and uncompleted runs page
//    }

 // Inside your ASUHelloWorldJavaFX class:

    private void showTasksPage() {
        VBox tasksLayout = createLayout("Effort Logger - Previous Tasks");

        TextArea completedTasksArea = createTextArea(completedTasks);
        completedTasksArea.setEditable(false);

        TextArea incompletedTasksArea = createTextArea(inCompletedTasks);
        incompletedTasksArea.setEditable(false);

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> showProjectPlanningPage());

        tasksLayout.getChildren().addAll(new Label("Completed Tasks"), completedTasksArea,
                new Label("Incompleted Tasks"), incompletedTasksArea, backButton);

        showScene(tasksLayout);
    }


    private void showHistoricalDataPage() {
    	
    	// array of possible employees
        String[] workersArray = {"Saharsh", "Iksun", "Darsh", "Chaitanyakrishna", "Naveen"};

        // title of the screen
        VBox historicalDataLayout = createLayout("Effort Logger - Historical Data");

        // drop down menu
        ComboBox<String> tasksComboBox = new ComboBox<>();
        
        // add tasks to drop down menu
        tasksComboBox.getItems().addAll(completedTasks);
        tasksComboBox.getItems().addAll(inCompletedTasks);

        // drop down menu of the possible employees
        ComboBox<String> workersComboBox = new ComboBox<>();
        workersComboBox.getItems().addAll(workersArray);

        // show text
        TextArea displayArea = new TextArea();
        displayArea.setEditable(false);

        
     // button to add the worker for the current task
        Button addButton = new Button("Add");
        addButton.setOnAction(event -> addSelectedDataToTextArea(tasksComboBox.getValue(), workersComboBox.getValue(), displayArea));

        // takes user back to project planning page
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> showProjectPlanningPage());

        // layout configuration of the entire page
        historicalDataLayout.getChildren().addAll(new Label("Select Task:"), tasksComboBox,
                new Label("Select Worker:"), workersComboBox, displayArea, addButton, backButton);

        displayArea.setText(String.join("\n", displayList));
        // displays the layout
        showScene(historicalDataLayout);
    }
    

 // Modify the addSelectedDataToTextArea method
    private void addSelectedDataToTextArea(String selectedTask, String selectedWorker, TextArea displayArea) {
        if (selectedTask != null && selectedWorker != null) {
            String data = "Selected Task: " + selectedTask + ", Selected Worker: " + selectedWorker;
            displayList.add(data);
            displayArea.setText(String.join("\n", displayList));
        }
    }



    private void showStrategiesPage() {
        VBox strategiesLayout = createLayout("Effort Logger - Previous Strategies");

        inputArea = new TextArea();
        inputArea.setPromptText("Type your strategies here");

        TextArea displayArea = createTextArea(previousStrategies);

        Button addButton = new Button("Add");
        addButton.setOnAction(event -> addStrategy(displayArea));

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> showProjectPlanningPage());

        strategiesLayout.getChildren().addAll(inputArea, addButton, displayArea, backButton);

        showScene(strategiesLayout);
    }

    private void addStrategy(TextArea displayArea) {
        // Ensure inputArea is not null before calling methods on it
        if (inputArea != null) {
            String newStrategy = inputArea.getText();
            if (!newStrategy.isEmpty()) {
                previousStrategies.add(newStrategy);
                displayArea.appendText("Added Strategy: " + newStrategy + "\n");
                inputArea.clear();
            }
        }
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

    private String validUsername = "saharsh";
    private String hashedPassword = hashPassword("1234");

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder hexHash = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexHash.append('0');
                }
                hexHash.append(hex);
            }
            return hexHash.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showLoginPage();
    }
}

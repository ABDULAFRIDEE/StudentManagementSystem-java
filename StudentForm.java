package application;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Node;

public class StudentForm extends Application {

    TextField nameField, rollField, courseField, semField, emailField, phoneField;

    public static void main(String[] args) {
        launch(args);
    }

   
    public void start(Stage stage) {
        
        Label title = new Label("📘 Student Management System");
        title.setStyle("-fx-font-size: 22px; -fx-text-fill: white; -fx-font-weight: bold;");
        HBox titleBox = new HBox(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(20));
        titleBox.setBackground(new Background(new BackgroundFill(Color.web("#34495e"), CornerRadii.EMPTY, Insets.EMPTY)));

       
        nameField = new TextField(); nameField.setPromptText("Enter full name");
        rollField = new TextField(); rollField.setPromptText("Enter roll number");
        courseField = new TextField(); courseField.setPromptText("Enter course");
        semField = new TextField(); semField.setPromptText("1st, 2nd, etc.");
        emailField = new TextField(); emailField.setPromptText("Enter Gmail address");
        phoneField = new TextField(); phoneField.setPromptText("10-digit number");

        TextField[] fields = { nameField, rollField, courseField, semField, emailField, phoneField };
        for (TextField tf : fields) {
            tf.setPrefHeight(35);
            tf.setStyle("-fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #ccc;");
        }

        GridPane form = new GridPane();
        form.setHgap(15);
        form.setVgap(15);
        form.setPadding(new Insets(25));
        form.setAlignment(Pos.CENTER);

        form.add(new Label("Name:"), 0, 0); form.add(nameField, 1, 0);
        form.add(new Label("Roll Number:"), 0, 1); form.add(rollField, 1, 1);
        form.add(new Label("Course:"), 0, 2); form.add(courseField, 1, 2);
        form.add(new Label("Semester:"), 0, 3); form.add(semField, 1, 3);
        form.add(new Label("Email:"), 0, 4); form.add(emailField, 1, 4);
        form.add(new Label("Phone:"), 0, 5); form.add(phoneField, 1, 5);

        for (Node node : form.getChildren()) {
            if (node instanceof Label)
                ((Label) node).setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        }

    
        Button insertButton = new Button("➕ Insert Student");
        insertButton.setPrefHeight(40);
        insertButton.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8;");
        insertButton.setOnAction(e -> {
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();

            if (!email.endsWith("@gmail.com")) {
                showAlert(Alert.AlertType.WARNING, "Invalid Email", "Email must end with @gmail.com");
                return;
            }

            if (!phone.matches("\\d{10}")) {
                showAlert(Alert.AlertType.WARNING, "Invalid Phone", "Phone number must contain exactly 10 digits.");
                return;
            }

            DatabaseHandler.insertStudent(
                nameField.getText(),
                rollField.getText(),
                courseField.getText(),
                semField.getText(),
                email,
                phone
            );

            clearFields();
            showAlert(Alert.AlertType.INFORMATION, "Success", "🎉 Student inserted successfully!");
        });

        VBox card = new VBox(25, form, insertButton);
        card.setPadding(new Insets(30));
        card.setStyle("-fx-background-color: white; -fx-background-radius: 15; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 5);");
        card.setAlignment(Pos.CENTER);

        VBox root = new VBox(titleBox, card);
        root.setSpacing(30);
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #ecf0f1;");

        Scene scene = new Scene(root, 500, 650);
        stage.setTitle("Student Management System");
        stage.setScene(scene);
        stage.show();
    }

    private void clearFields() {
        nameField.clear();
        rollField.clear();
        courseField.clear();
        semField.clear();
        emailField.clear();
        phoneField.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

package com.example.snapshop.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button registerButton;

    @FXML
    private Button backButton;

    @FXML
    private void handleRegisterAction() {
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showAlert("Lỗi", "Vui lòng điền đầy đủ tất cả các trường!");
            return;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            showAlert("Lỗi", "Email không hợp lệ!");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert("Lỗi", "Mật khẩu và xác nhận mật khẩu không khớp!");
            return;
        }

        showAlert("Thành công", "Đăng ký thành công cho " + username + "!");
        // TODO: Lưu vào database
    }

    @FXML
    private void handleBackAction() {
        try {
            // Debug đường dẫn
            if (getClass().getResource("/fxml/login.fxml") == null) {
                showAlert("Lỗi", "Không tìm thấy file /fxml/login.fxml trong resources!");
                return;
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root, 1400, 800));
            stage.setTitle("Đăng Nhập");
            stage.setResizable(false);
            stage.setMinWidth(1400);
            stage.setMinHeight(800);
            stage.setMaxWidth(1400);
            stage.setMaxHeight(800);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Lỗi", "Không thể mở trang đăng nhập: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
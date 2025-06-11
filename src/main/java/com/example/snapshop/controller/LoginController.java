package com.example.snapshop.controller;

import com.example.snapshop.dao.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signInButton;

    @FXML
    private Hyperlink signupLink;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        // Kiểm tra các trường có rỗng hay không
        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Lỗi", "Vui lòng điền email và mật khẩu!");
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT password FROM admins WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                if (password.equals(storedPassword)) { // Kiểm tra plaintext
                    showAlert("Thành công", "Đăng nhập thành công!");
                    // Chuyển sang admin dashboard
                    loadAdminDashboard();
                } else {
                    showAlert("Lỗi", "Mật khẩu sai!");
                }
            } else {
                showAlert("Lỗi", "Tên đăng nhập không tồn tại!");
            }
        } catch (Exception e) {
            showAlert("Lỗi", "Lỗi kết nối: " + e.getMessage());
        }
    }

    @FXML
    private void handleSignupLink() {
        try {
            // Debug đường dẫn
            if (getClass().getResource("/fxml/register.fxml") == null) {
                showAlert("Lỗi", "Không tìm thấy file register.fxml trong resources!");
                return;
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/register.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) signupLink.getScene().getWindow();
            stage.setScene(new Scene(root, 1400, 800));
            stage.setTitle("Đăng Ký");
            stage.setResizable(false);
            stage.setMinWidth(1400);
            stage.setMinHeight(800);
            stage.setMaxWidth(1400);
            stage.setMaxHeight(800);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Lỗi", "Không thể mở trang đăng ký: " + e.getMessage());
        }
    }

    private void loadAdminDashboard() {
        try {
            // Debug đường dẫn
            if (getClass().getResource("/fxml/admin_dashboard.fxml") == null) {
                showAlert("Lỗi", "Không tìm thấy file admin_dashboard.fxml trong resources!");
                return;
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin_dashboard.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) signInButton.getScene().getWindow();
            stage.setScene(new Scene(root, 1400, 800));
            stage.setTitle("Admin Dashboard");
            stage.setResizable(false);
            stage.setMinWidth(1400);
            stage.setMinHeight(800);
            stage.setMaxWidth(1400);
            stage.setMaxHeight(800);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Lỗi", "Không thể mở trang admin dashboard: " + e.getMessage());
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
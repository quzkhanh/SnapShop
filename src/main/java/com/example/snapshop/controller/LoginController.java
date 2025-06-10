package com.example.snapshop.controller;

import com.example.snapshop.dao.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT password FROM admins WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                if (password.equals(storedPassword)) { // Kiểm tra plaintext
                    errorLabel.setText("Đăng nhập thành công!");
                    // Chuyển sang dashboard (cần tạo sau)
                } else {
                    errorLabel.setText("Mật khẩu sai!");
                }
            } else {
                errorLabel.setText("Tên đăng nhập không tồn tại!");
            }
        } catch (Exception e) {
            errorLabel.setText("Lỗi kết nối: " + e.getMessage());
        }
    }
}
package com.example.snapshop;

import com.example.snapshop.dao.DatabaseConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Đang load FXML: " + getClass().getResource("/fxml/login.fxml"));

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
            if (root == null) {
                throw new RuntimeException("Không thể load file login.fxml. Kiểm tra đường dẫn!");
            }
        } catch (Exception e) {
            System.err.println("Lỗi chi tiết: " + e.getMessage());
            throw e;
        }
        primaryStage.setTitle("SnapShop");
        primaryStage.setScene(new Scene(root, 1200, 750)); // Đặt size mong muốn

        // --- KHÓA RESIZE & CỐ ĐỊNH KÍCH THƯỚC ---
        primaryStage.setResizable(false);
        primaryStage.setMinWidth(1400);
        primaryStage.setMinHeight(800);
        primaryStage.setMaxWidth(1400);
        primaryStage.setMaxHeight(800);

        primaryStage.show();

        initializeDatabase();
    }


    private void initializeDatabase() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("✅ Kết nối H2 thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
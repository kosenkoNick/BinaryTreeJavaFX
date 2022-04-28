package com.example.binarytreejavafx;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
  @Override
  public void start(Stage stage) throws IOException {
    URL url = getClass().getResource("window_conf.fxml");
    FXMLLoader fxmlLoader = new FXMLLoader(url);
    stage = fxmlLoader.load();
    stage.setTitle("Hello!");
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}
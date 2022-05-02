package com.example.binarytreejavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class AppInitializer extends Application {
  @Override
  public void start(Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/window_conf.fxml"));

    stage = loader.load();
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}

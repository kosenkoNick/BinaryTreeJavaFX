module com.example.binarytreejavafx {
  requires javafx.controls;
  requires javafx.fxml;


  opens com.example.binarytreejavafx to javafx.fxml;
  exports com.example.binarytreejavafx;
}
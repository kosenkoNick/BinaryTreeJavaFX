package com.example.binarytreejavafx;

import com.example.binarytreejavafx.tree.BinaryTree;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Controller {
  @FXML Label label;
  @FXML Label root;
  BinaryTree tree;

  @FXML GridPane tier0;
  @FXML GridPane tier1;
  @FXML GridPane tier2;
  @FXML GridPane tier3;

  @FXML TextField inputText;

  @FXML public void addValue() {
    if (tree == null) {
      tree = new BinaryTree(label, root, tier0, tier1, tier2, tier3);
    }
    tree.add(Integer.parseInt(inputText.getText()));
  }

  @FXML public void deleteValue() {
    if(tree != null && !root.getText().isEmpty()) {
      tree.delete(Integer.parseInt(inputText.getText()));
    } else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setContentText("The tree is empty - nothing to delete");
      alert.show();
    }
  }

  @FXML public void preOrder() throws InterruptedException {
    label.setText("Tree traversal [preorder] is: ");
    tree.traversePreOrder(tree.root);
  }
}
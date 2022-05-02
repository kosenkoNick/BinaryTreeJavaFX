package com.example.binarytreejavafx.tree;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class BinaryTree {

  Node root;
  GridPane[] tiers;

  public BinaryTree(Label label, GridPane ... tiers) {
    this.root = new Node(label);
    this.tiers = tiers;
  }

  public void add(int value) throws IOException {
    root = addRecursive(root, value);
  }
  private Node addRecursive(Node current, Integer value) throws IOException {
    if(current.value.getText().isEmpty()){
      current.value.setText(value.toString());
      current.pos = new Coords(0, 0);
    } else {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/label.fxml"));
      Label label = loader.load();
      if (value < current.value()) {
        if(current.left == null) {
          current.left = new Node(label);
          current.left.value.setText(value.toString());
        }
        current.left.pos = new Coords(current.pos.row + 1, current.pos.col * 2);
        current.left = addRecursive(current.left, value);
      } else if (value > current.value()) {
        if(current.right == null) {
          current.right = new Node(label);
          current.right.value.setText(value.toString());
          current.right.pos = new Coords(current.pos.row + 1, current.pos.col * 2 + 1);
        }
        current.right = addRecursive(current.right, value);
      }
      if(current.pos.row < tiers.length) {
        tiers[current.pos.row].add(current.value, current.pos.col, 0);
      } else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("You cannot add more elements to this branch!");
        alert.show();
      }
    }
    return current;
  }



  public void delete(int value) {
    root = deleteRecursive(root, value);
  }
  private Node deleteRecursive(Node current, int value) {
    if (current == null) {
      return null;
    }

    if (value == current.value()) {
      if (current == root){
        root.setText("");
        return null;
      }

      // Case 1: no children
      if (current.left == null && current.right == null) {
        current.value.setVisible(false);
        current = null;
        return null;
      }

      // Case 2: only 1 child
      if (current.right == null) {
        return current.left;
      }

      if (current.left == null) {
        return current.right;
      }

      // Case 3: 2 children
      Integer smallestValue = findSmallestValue(current.right);
      current.value.setText(smallestValue.toString());
      current.right = deleteRecursive(current.right, smallestValue);
      return current;
    }
    if (value < current.value()) {
      current.left = deleteRecursive(current.left, value);
      return current;
    }

    current.right = deleteRecursive(current.right, value);
    return current;
  }

  private int findSmallestValue(Node root) {
    return root.left == null ? root.value() : findSmallestValue(root.left);
  }
}
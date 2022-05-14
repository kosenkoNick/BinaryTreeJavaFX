package com.example.binarytreejavafx.tree;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class BinaryTree {

  String DEFAULT = "-fx-border-width: 1; " +
      "-fx-border-color: #000000; " +
      "-fx-border-radius: 100; " +
      "-fx-background-radius: 100;";

  String HIGHLIGHTED = "-fx-border-width: 3; " +
      "-fx-border-color: #d60404; " +
      "-fx-background-color: #ba5656;" +
      "-fx-border-radius: 100; " +
      "-fx-background-radius: 100;";

  public TreeNode root;
  GridPane[] tiers;
  Label label;

  public BinaryTree(Label label, Label rootLabel, GridPane... tiers) {
    this.root = new TreeNode(rootLabel);
    root.pos = new Coords(0, 0);
    this.tiers = tiers;
    this.label = label;
  }

  public void add(int value) {
    root = addRecursive(root, value);
  }

  public TreeNode addRecursive(TreeNode current, Integer value) {
    // if root is empty
    if (current.value.getText().isBlank()) {
      current.value.setText(value.toString());
      current.value.setVisible(true);
    } else {
      Label label = createLabel();
      if (value < current.value()) {
        if (!current.hasLeft()) {
          TreeNode left = new TreeNode(label);
          left.pos = current.getLeftPos();
          left.value.setText(value.toString());

          current.left = left;
          tiers[left.pos.row].add(left.value, left.pos.col, 0);
        } else {
          current.left = addRecursive(current.left, value);
        }
      } else if (current.value() < value) {
        if (!current.hasRight()) {
          TreeNode right = new TreeNode(label);
          right.pos = current.getRightPos();
          right.value.setText(value.toString());

          current.right = right;

          tiers[right.pos.row].add(right.value, right.pos.col, 0);
        } else {
          current.right = addRecursive(current.right, value);
        }
      }
    }
    return current;
  }

  public void delete(int value) {
    root = deleteRecursive(root, value);
  }

  private TreeNode deleteRecursive(TreeNode current, int value) {
    // if root is empty
    if (current.value.getText().isEmpty()) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setContentText("The tree is empty - nothing to delete");
      alert.show();
      return current;
    }
    // if we found right element
    if (current.value() == value) {
      if (current.value() == root.value()) {
        cutBranch(current);
        current = new TreeNode(createLabel());
        current.pos = new Coords(0, 0);
        tiers[0].add(current.value, 0, 0);
        current.value.setText("");
      } else {
        cutBranch(current);
      }
    } else {
      if (current.hasLeft() && value < current.value()) {
        deleteRecursive(current.left, value);
        current.removeChildren();
      } else if (current.hasRight() && current.value() < value) {
        deleteRecursive(current.right, value);
        current.removeChildren();
      }
    }
    return current;
  }

  public void cutBranch(TreeNode current) {
    if (current.hasLeft()) {
      cutBranch(current.left);
    }
    if (current.hasRight()) {
      cutBranch(current.right);
    }
    GridPane grid = tiers[current.pos.row];
    for (Node node : grid.getChildren()) {
      try {
        if (((Label) node).getText().equals(current.value.getText())) {
          grid.getChildren().remove(node);
          break;
        }
      } catch (ClassCastException exp) {
        System.out.println(node.getClass().getName() + " cannot be cast to Label");
      }
    }
    current.value = null;
  }

  public Label createLabel() {
    Label lab = new Label();
    lab.setAlignment(Pos.CENTER);
    lab.setPrefHeight(70);
    lab.setPrefWidth(70);
    lab.setStyle(DEFAULT);
    lab.setFont(new Font(17));
    return lab;
  }

  public void traversePreOrder(TreeNode node) {
    if (node != null) {

      node.value.setStyle(HIGHLIGHTED);
//      Thread.sleep(1000);
//      node.value.setStyle(DEFAULT);
      label.setText(label.getText() + node.value() + " ");
      traversePreOrder(node.left);
      traversePreOrder(node.right);
    }
  }




}
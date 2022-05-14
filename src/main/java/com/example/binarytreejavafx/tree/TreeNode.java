package com.example.binarytreejavafx.tree;

import javafx.scene.control.Label;

public class TreeNode {
  public Label value;
  TreeNode left;
  TreeNode right;
  Coords pos;

  public TreeNode(Label value) {
    this.value = value;
    this.left = null;
    this.right = null;
  }

  int value() {
    return Integer.parseInt(value.getText());
  }

  public Coords getRightPos() {
    return new Coords(this.pos.row + 1, this.pos.col * 2 + 1);
  }

  public Coords getLeftPos() {
    return new Coords(this.pos.row + 1, this.pos.col * 2);
  }

  boolean hasRight() {
    return this.right != null;
  }

  boolean hasLeft() {
    return this.left != null;
  }

  void removeChildren(){
    this.left = this.right = null;
  }
}
package com.example.binarytreejavafx.tree;

import javafx.scene.control.Label;

public class Node extends Label {
  Label value;
  Node left;
  Node right;
  Coords pos;

  public Node(Label value) {
    this.value = value;
    this.left = null;
    this.right = null;
  }

  int value(){
    return Integer.parseInt(value.getText());
  }
}
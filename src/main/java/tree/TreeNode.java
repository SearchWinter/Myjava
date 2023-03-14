package tree;

/**
 * Created by anjunli on  2020/12/10
 **/
public class TreeNode {
    public TreeNode leftNode;
    public TreeNode rightNode;
    public int data;

    public TreeNode(){

    }

    public TreeNode(int data){
        this.data=data;
    }

    public TreeNode(TreeNode leftNode, TreeNode rightNode, int data) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.data = data;
    }

    public TreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(TreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public TreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(TreeNode rightNode) {
        this.rightNode = rightNode;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}

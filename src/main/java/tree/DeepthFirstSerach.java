package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by anjunli on  2020/12/10
 **/
public class DeepthFirstSerach {
    public static void main(String[] args) {
        TreeNode treeNode01 = new TreeNode(1);
        TreeNode treeNode02 = new TreeNode(2);
        TreeNode treeNode03 = new TreeNode(3);
        TreeNode treeNode04 = new TreeNode(4);
        TreeNode treeNode05 = new TreeNode(5);
        TreeNode treeNode06 = new TreeNode(6);
        TreeNode treeNode07 = new TreeNode(7);

        treeNode01.setLeftNode(treeNode02);
        treeNode01.setRightNode(treeNode03);

        treeNode02.setLeftNode(treeNode04);
        treeNode02.setRightNode(treeNode05);

        treeNode03.setLeftNode(treeNode06);
        treeNode03.setRightNode(treeNode07);

        System.out.println("DFS结果：");
        deepthFirstSearch(treeNode01);

        System.out.println();
        System.out.println("BFS结果：");
        breadFirstSearch(treeNode01);
    }

    /** 深度优先遍历
     * Stack 后进先出，类似弹夹
     * 1、将根节点(rootNode)放入栈
     * 2、while判断栈不为空，从中取出节点Node，如果Node的子节点不为空，就将其子节点加入栈（先加右节点，后加左节点）
     * */
    public static void deepthFirstSearch(TreeNode treeNode){
        if(treeNode==null){
            return;
        }
        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.add(treeNode);
        while(!nodeStack.isEmpty()){
            //取出栈顶元素
            TreeNode node = nodeStack.pop();
            System.out.print(node.data+" ");
            if (node.rightNode!=null){
                nodeStack.push(node.rightNode);
            }
            if(node.leftNode!=null){
                nodeStack.push(node.leftNode);
            }
        }

    }

    /**  广度优先遍历
     *  LinkedList 实现了Queue接口，可作为队列使用
     *  Queue 先进先出
     *  1、将根节点加入队列
     *  2、只要队列不为空，就从中取出并移除第一个节点
     *  3、取出节点时，判断其左右子树是否为空，不为空就加入队列（先加左节点，后加右节点）
     * */
    public static void breadFirstSearch(TreeNode treeNode){
        if(treeNode==null){
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(treeNode);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            System.out.print(node.getData()+" ");
            if(node.leftNode!=null){
                queue.add(node.leftNode);
            }
            if(node.rightNode!=null){
                queue.add(node.rightNode);
            }
        }
    }

}

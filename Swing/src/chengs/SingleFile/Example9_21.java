package chengs.SingleFile;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

/**
 * Created by chengs on 17-3-28.
 */
public class Example9_21 {
    public static void main(String[] args) {
        TreeWin treeWin=new TreeWin();
        treeWin.setBounds(100,100,400,400);
        treeWin.setTitle("学习树形目录");
    }
}
class TreeWin extends JFrame implements TreeSelectionListener{
    JTree tree;
    JTextArea textArea;

    TreeWin(){
        DefaultMutableTreeNode root=new DefaultMutableTreeNode("商品");
        DefaultMutableTreeNode nodeTV=new DefaultMutableTreeNode("电视机类");
        DefaultMutableTreeNode nodePhone=new DefaultMutableTreeNode("手机类");

        DefaultMutableTreeNode tv1=new DefaultMutableTreeNode(new Goods("长虹电视",5699));
        DefaultMutableTreeNode tv2=new DefaultMutableTreeNode(new Goods("海尔电视",56219));
        DefaultMutableTreeNode phone1=new DefaultMutableTreeNode(new Goods("诺基亚手机",699));
        DefaultMutableTreeNode phone2=new DefaultMutableTreeNode(new Goods("三星手机",5692));

        root.add(nodeTV);
        root.add(nodePhone);

        nodeTV.add(tv1);
        nodeTV.add(tv2);

        nodePhone.add(phone1);
        nodePhone.add(phone2);

        tree=new JTree(root);//建立树形目录

        tree.addTreeSelectionListener(this);//添加树形监视器

        textArea=new JTextArea();
        setLayout(new GridLayout(1,2));
        add(new JScrollPane(tree));//建立带滚动条的树形组件
        add(new JScrollPane(textArea));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(80,80,300,300);
        validate();
    }
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node=(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
        if(node.isLeaf()){
            textArea.append(e.toString()+"\n");
        }else {
            textArea.setText(null);
        }
    }
}
class Goods{
    String name;
    double price;
    Goods(String s,double p){
        name=s;
        price=p;
    }
    public String toString(){
        return name;
    }
}
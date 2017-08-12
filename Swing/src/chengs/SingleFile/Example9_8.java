package chengs.SingleFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
/**
 * Created by chengs on 17-3-23.
 */
public class Example9_8 {
    public static void main(String[] args) {
        new WindowOperation();
    }
}

class WindowOperation extends JFrame{
    JTextField inputNumberOne,inputNumberTwo;
    JComboBox choiceSymbol;
    JTextArea textShow;
    JButton button;

    OperatorListener operatorListener;
    ComputeListener computeListener;

    public WindowOperation(){
        setLayout(new FlowLayout());
        inputNumberOne=new JTextField(5);
        inputNumberTwo=new JTextField(5);

        choiceSymbol=new JComboBox();//组合框
        button=new JButton("计算");
        choiceSymbol.addItem("选择运算符号:");
        String[]a={"+","-","*","/"};
        for(int i=0;i<a.length;i++){
            choiceSymbol.addItem(a[i]);
        }
        textShow=new JTextArea(9,30);
        operatorListener=new OperatorListener();
        computeListener=new ComputeListener();

        operatorListener.setChoice(choiceSymbol);
        operatorListener.setWorkTogether(computeListener);

        computeListener.setInputNumberOne(inputNumberOne);
        computeListener.setInputNumberTwo(inputNumberTwo);
        computeListener.setTextShow(textShow);
        computeListener.setSymbol("选择运算符号:");

        choiceSymbol.addItemListener(operatorListener);
        button.addActionListener(computeListener);
        //之后为UI布局
        add(inputNumberOne);
        add(choiceSymbol);
        add(inputNumberTwo);
        add(button);
        add(textShow);

        setTitle("简单计算器");
        setBounds(100,100,400,300);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}


class OperatorListener implements ItemListener{
    JComboBox choice;
    ComputeListener workTogether;

    public void setChoice(JComboBox choice) {
        this.choice = choice;
    }

    public void setWorkTogether(ComputeListener workTogether) {
        this.workTogether = workTogether;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        //System.out.println(e.getSource().toString()); 返回事件源
        String symbol=choice.getSelectedItem().toString();//返回发生ItemEvent事件的事件源
        workTogether.setSymbol(symbol);
    }
}

class ComputeListener implements ActionListener{
    JTextField inputNumberOne,inputNumberTwo;
    JTextArea textShow;
    String symbol;
    public void setInputNumberOne(JTextField inputNumberOne) {
        this.inputNumberOne = inputNumberOne;
    }

    public void setInputNumberTwo(JTextField inputNumberTwo) {
        this.inputNumberTwo = inputNumberTwo;
    }

    public void setTextShow(JTextArea textShow) {
        this.textShow = textShow;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            double number1= Double.parseDouble(inputNumberOne.getText());
            double number2= Double.parseDouble(inputNumberTwo.getText());
            double result=0;
            if(symbol.equals("+")){
                result=number1+number2;
            }
            else if(symbol.equals("-")){
                result=number1-number2;
            }
            else if(symbol.equals("*")){
                result=number1*number2;
            }
            else if(symbol.equals("/")){
                result=number1/number2;
            }else if(symbol.equals("选择运算符号:")){
                textShow.append("必须选择运算符号!\n");
                return;
            }
            textShow.append(number1+" "+symbol+" "+number2+" = "+result+'\n');
        }catch (Exception exp){
            System.out.println("发生异常:"+exp.getMessage());
            textShow.append("必须输入数字\n");
        }
    }

}

/**
 * Created by chengs on 17-4-3.
 */
public class Example12_3 {
    public static void main(String[] args) {
            TicketHouse officer=new TicketHouse();
            Thread zf,lk,lcs;
            zf=new Thread(officer);
            zf.setName("张飞");
            lk=new Thread(officer);
            lk.setName("李逵");
            lcs=new Thread(officer);
            lcs.setName("罗成盛");
            lcs.start();
            zf.start();
            lk.start();
    }
}

class TicketHouse implements Runnable{
    int fiveAmount=2,tenAmount=0,twentyAmount=0;
    @Override
    public void run() {
        if(Thread.currentThread().getName().equals("张飞")){
            saleTicket(20);
        }else if(Thread.currentThread().getName().equals("李逵")){
            saleTicket(5);
        }
    }
    private synchronized void saleTicket(int money){
        if(money==5){
            fiveAmount++;
            System.out.println("给"+Thread.currentThread().getName()+"入场券,他给得钱正好,不差也不需要找零");
        }
        else if(money==20){
            while (fiveAmount<3){//wait看来一般写在循环体内部呢,依据信号量作为循环控制
                try{
                    System.out.println("\n"+Thread.currentThread().getName()+"需要找零不够,请靠边等...");
                    wait();
                    System.out.println("\n"+Thread.currentThread().getName()+"继续买票");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            fiveAmount=fiveAmount-3;
            twentyAmount++;
            System.out.println("给"+Thread.currentThread().getName()+"入场券,找零15元");
        }else if(money==10){
            while(fiveAmount<1){
                try{
                    System.out.println("\n"+Thread.currentThread().getName()+"需要找零不够,请靠边等...");
                    wait();
                    System.out.println("\n"+Thread.currentThread().getName()+"继续买票");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            fiveAmount=fiveAmount-1;
            tenAmount++;
            System.out.println("给"+Thread.currentThread().getName()+"入场券,找零5元");
        }else if(money<5){
            System.out.println("金额不够,无法买票!");
        }else if(money>20){
            System.out.println("本系统不支持此金额!");
        }else{
            System.out.println("顾客你好,你支付太多的金额,你可以收回一部分,再进行支付!");
        }
        //还要加上唤醒语句
        notifyAll();
    }
}
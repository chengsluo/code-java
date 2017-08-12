/**
 * Created by chengs on 17-4-4.
 */
//现做蛋糕店

public class Example12_4 {
    public static void main(String[] args) {
        Thread cooker,custom;
        ThreadJoin cakeShop=new ThreadJoin();
        custom=new Thread(cakeShop);
        cooker=new Thread(cakeShop);
        custom.setName("顾客");
        cooker.setName("厨师");
        cakeShop.setCooker(cooker);
        custom.start();
    }
}
class ThreadJoin implements Runnable{
    Thread Cooker;
    Cake cake;
    public void setCooker(Thread cooker) {
        Cooker = cooker;
    }

    @Override
    public void run() {
        if(Thread.currentThread()!=Cooker){
            System.out.println(Thread.currentThread().getName()+"等待"+Cooker.getName()+"制作蛋糕");
            try{
                Cooker.start();//开辟新线程
                Cooker.join();//当前线程开始终端,并等待Cooker线程结束
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"买了"+cake.name+" 价格:"+cake.price);
        }
        if(Thread.currentThread()==Cooker){
            System.out.println(Cooker.getName()+"开始制作蛋糕,稍等");
            try{
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cake=new Cake(158,"生日蛋糕");
            System.out.println(Cooker.getName()+"制作完毕");
        }
    }

    class Cake{
        double price;
        String name;
        public Cake(double price,String name){
            this.price=price;
            this.name=name;
        }
    }
}
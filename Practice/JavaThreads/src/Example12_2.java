/**
 * Created by chengs on 17-4-3.
 */
public class Example12_2 {
    public static void main(String[] args) {
        ElephantRunable elephantRunable=new ElephantRunable();
        Thread SpeakElephant=new Thread(elephantRunable);

        CarRunnable carRunnable=new CarRunnable();
        Thread SpeakCar=new Thread(carRunnable);

        for (int i = 0; i <100000 ; i++) {
            System.out.println("主函数"+i);
        }
    }
}

class ElephantRunable implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i <100000 ; i++) {
            System.out.println("大象"+i);
        }
    }
}
class CarRunnable implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i <100000 ; i++) {
            System.out.println("汽车"+i);
        }
    }
}
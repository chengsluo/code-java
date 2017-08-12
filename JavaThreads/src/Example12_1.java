import javax.sound.midi.Soundbank;

/**
 * Created by chengs on 17-4-3.
 */
public class Example12_1 {
    public static void main(String[] args) {
        SpeakElephant speakElephant;
        SpeakCar speakCar;

        speakElephant=new SpeakElephant();
        speakCar=new SpeakCar();
        speakCar.start();
        speakElephant.start();
        for(int i=0;i<=1000000;i++){
            System.out.println("主人"+i+";");
        }

    }
}
class SpeakElephant extends Thread{
    public void run() {
        for (int i = 0; i <= 1000000; i++) {
            System.out.println("大象" + i + ";");
        }
    }
}

class SpeakCar extends Thread{
    public void run() {
        for (int i = 0; i <= 1000000; i++) {
            System.out.println("车" + i + ";");
        }
    }
}
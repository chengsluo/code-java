package cn.chengsluo.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by chengs on 17-6-24.
 */
public class MQConsumer implements MessageListener {
    static int c=0;
    public static void main(String[] args){
        AbstractApplicationContext ctx =
                new ClassPathXmlApplicationContext("classpath:application-mq.xml");
        //ctx.close();
    }

    @Override
    public void onMessage(Message message) {

        try {
            c++;
            System.out.println(message.toString());
            System.out.println(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

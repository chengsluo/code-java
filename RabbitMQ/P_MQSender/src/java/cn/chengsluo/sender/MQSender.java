package cn.chengsluo.sender;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by chengs on 17-6-24.
 */
public class MQSender {
    public static void main(String[] args) {
        AbstractApplicationContext ctx =
                new ClassPathXmlApplicationContext("classpath:application-mq.xml");
        RabbitTemplate rabbitmq=ctx.getBean(RabbitTemplate.class);
        int i=0;
        while (i<1000) {
            rabbitmq.convertAndSend("gtoq_exchange", "gtoq_queue", "测试数据"+i);
            i++;
        }
        ctx.close();
    }
}

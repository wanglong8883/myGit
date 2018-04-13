package activemq.helloworld;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Receiver {
    public static void main(String[] args) throws JMSException {
        //第一步:建立ConnectionFactory工厂对象,需要填入用户名、密码、以及要连接的地址,均使用默认即可
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(
                "admin","admin",//添加安全机制
                "tcp://localhost:61616"
        );
        //第二步:通过ConnectionFactory工厂对象创建一个Connection连接,并且调用Connection的start开启连接
        Connection connection=connectionFactory.createConnection();
        connection.start();
        //第三步:通过Connection对象创建Session会话(上下文环境对象),用于接收消息
        Session session=connection.createSession(
                Boolean.FALSE,//是否启用事务
                Session.AUTO_ACKNOWLEDGE//签收模式
        );
        //第四步:通过Session对象创建Destination对象,指的是一个客户端用来指定生产消息目标和消费消息来源的对象
        //在PTP模式中Destination
        Destination destination=session.createQueue("first");
        //第五步:我们需要通过Session对象创建消息的发送和接收对象(生产者和消费者)MessageProducer/MessageConsumer
        MessageConsumer consumer= session.createConsumer(destination);
        while (true){
            TextMessage msg= (TextMessage) consumer.receive();
            System.out.println("消费数据"+msg.getText());
        }

    }
}

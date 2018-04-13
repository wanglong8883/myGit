package activemq.helloworld;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Sender {
    public static void main(String[] args) throws JMSException, InterruptedException {
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
        MessageProducer producer=session.createProducer(null);
        //第六步:我们可以使用MessageProducer的setDeliveryMode方法为其设置持久化特性和非持久化特性
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        //第七步:最后我们使用JMS规范的TextMessage形式创建数据(通过session对象)
        //并用MessageProducer的send方法发送数据
        for (int i = 0; i < 100; i++) {
            TextMessage msg=session.createTextMessage("我是消息内容"+i);
            //第一个参数 目标地址
            //第二个参数 具体的数据信息
            //第三个参数 传送数据模式
            //第四个参数 优先级
            //第五个参数 消息过期时间
            producer.send(destination,msg);
//            System.out.println(msg.getText());
//            TimeUnit.SECONDS.sleep(1);
        }
        //提交数据
//        session.commit();
//        session.rollback();

        if (connection!=null){
            connection.close();
        }

    }
}

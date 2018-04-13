package activemq.pb;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer3 {
    //1 连接工厂
    private ConnectionFactory connectionFactory;
    //2 连接对象
    private Connection connection;
    //3 Session对象
    private Session session;
    //4 消费者
    private MessageConsumer messageConsumer;

    public Consumer3() {
        try {
            this.connectionFactory = new ActiveMQConnectionFactory(
                    "admin",
                    "admin",
                    "tcp://localhost:61616"
            );
            this.connection = this.connectionFactory.createConnection();
            this.connection.start();
            this.session = this.connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void receive() throws JMSException {
        Destination destination=session.createTopic("topic1");
        messageConsumer=session.createConsumer(destination);
        messageConsumer.setMessageListener(message -> {
            if(message instanceof  TextMessage){
                System.out.println("Consumer3收到消息: --------");
                TextMessage m= (TextMessage) message;
                try {
                    System.out.println(m.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public static void main(String[] args) throws JMSException {
        Consumer3 c=new Consumer3();
        c.receive();
    }
}

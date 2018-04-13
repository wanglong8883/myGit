package activemq.p2p;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer {

    public final String SELECTOR_0="age>25";

    public final String SELECTOR_1="color='blue'";

    public final String SELECTOR_2="color='blue' AND sal>2000";

    public final String SELECTOR_3="receiver='A'";

    //1 连接工厂
    private ConnectionFactory connectionFactory;
    //2 连接对象
    private Connection connection;
    //3 Session对象
    private Session session;
    //4 生产者
    private MessageConsumer messageConsumer;
    //5 目标地址
    private Destination destination;

    public Consumer() {
        try {
            this.connectionFactory = new ActiveMQConnectionFactory(
                    "admin",
                    "admin",
                    "tcp://localhost:61616"
            );
            this.connection = this.connectionFactory.createConnection();
            this.connection.start();
            this.session = this.connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            this.destination=this.session.createQueue("first");
            this.messageConsumer = this.session.createConsumer(this.destination);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void receiver(){
        try {
            this.messageConsumer.setMessageListener(message -> {
                if(message instanceof TextMessage){

                }
                if(message instanceof  MapMessage){
                    MapMessage ret= (MapMessage) message;
                    System.out.println(ret.toString());
                    try {
                        System.out.println(ret.getString("name"));
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Consumer c=new Consumer();
        c.receiver();
    }
}

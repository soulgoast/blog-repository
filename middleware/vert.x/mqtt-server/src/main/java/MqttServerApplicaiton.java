import io.netty.handler.codec.mqtt.MqttQoS;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.mqtt.MqttServer;
import io.vertx.mqtt.MqttTopicSubscription;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class MqttServerApplicaiton {

    public static void main(String[] args) {

        Vertx vertx = Vertx.vertx();
        MqttServer mqttServer = MqttServer.create(vertx);

        mqttServer.endpointHandler(endpoint -> {

            // 显示主要连接信息
            System.out.println("MQTT client [" + endpoint.clientIdentifier() + "] request to connect, clean session = " + endpoint.isCleanSession());
            if (endpoint.auth() != null) {
                System.out.println("[username = " + endpoint.auth().getUsername() + ", password = " + endpoint.auth().getPassword() + "]");
            }

            if (endpoint.will() != null) {
                System.out.println("[will topic = " + endpoint.will().getWillTopic() + " msg = " + endpoint.will().getWillMessage() +
                        " QoS = " + endpoint.will().getWillQos() + " isRetain = " + endpoint.will().isWillRetain() + "]");
            }

            System.out.println("[keep alive timeout = " + endpoint.keepAliveTimeSeconds() + "]");
            // 接受远程客户端连接
            endpoint.accept(false);

            /**
             * 断开连接时调用
             */
            endpoint.disconnectHandler(v -> {
                System.out.println("Received disconnect from client");
            });

            /**
             * 客户端订阅时调用
             */
            endpoint.subscribeHandler(subscribe -> {
                List<MqttQoS> grantedQosLevels = new ArrayList<>();
                for (MqttTopicSubscription s: subscribe.topicSubscriptions()) {
                    System.out.println("Subscription for " + s.topicName() + " with QoS " + s.qualityOfService());
                    grantedQosLevels.add(s.qualityOfService());
                }
                // 确认订阅请求
                endpoint.subscribeAcknowledge(subscribe.messageId(), grantedQosLevels);
            });

            /**
             * 客户端取消订阅时调用
             */
            endpoint.unsubscribeHandler(unsubscribe -> {
                for (String t: unsubscribe.topics()) {
                    System.out.println("Unsubscription for " + t);
                }
                // 确认订阅请求
                endpoint.unsubscribeAcknowledge(unsubscribe.messageId());
            });

            /**
             * 收到客户端消息时调用
             */
            endpoint.publishHandler(message -> {
                System.out.println("Just received message [" + message.payload().toString(Charset.defaultCharset()) + "] with QoS [" + message.qosLevel() + "]");
                if (message.qosLevel() == MqttQoS.AT_LEAST_ONCE) {
                    endpoint.publishAcknowledge(message.messageId());
                } else if (message.qosLevel() == MqttQoS.EXACTLY_ONCE) {
                    endpoint.publishRelease(message.messageId());
                }
            }).publishReleaseHandler(messageId -> {
                endpoint.publishComplete(messageId);
            });



        }).listen(ar -> {
            if (ar.succeeded()) {
                System.out.println("MQTT server is listening on port " + ar.result().actualPort());
            } else {
                System.out.println("Error on starting the server");
                ar.cause().printStackTrace();
            }
        });
    }

}

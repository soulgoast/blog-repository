import io.netty.handler.codec.mqtt.MqttQoS;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.mqtt.MqttClient;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class MqttClientApplication {

    public static void main(String[] args) {
        MqttClient client = MqttClient.create(Vertx.vertx());
        client.connect(1883, "localhost", s -> {
            client.disconnect();
        });
        client.publishHandler(s -> {
            System.out.println("There are new message in topic: " + s.topicName());
            System.out.println("Content(as string) of the message: " + s.payload().toString());
            System.out.println("QoS: " + s.qosLevel());
        })
                .subscribe("test", 2);

        client.publish("test",
                Buffer.buffer("hello"),
                MqttQoS.AT_LEAST_ONCE,
                false,
                false);
    }

}

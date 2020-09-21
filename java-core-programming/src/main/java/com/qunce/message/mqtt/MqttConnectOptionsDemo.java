/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.message.mqtt;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.util.Debug;

import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * @ClassName MqttConnectOptionsDemo
 * @Description
 * 用于设置服务端与客户端连接的配置信息
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2019/12/16 9:17
 * @ModifyDate 2019/12/16 9:17
 * @Version 1.0
 */
public class MqttConnectOptionsDemo {
    /**
     * The default keep alive interval in seconds if one is not specified
     * 如果未指定默认的活动间隔，以秒为单位。没隔多长时间发送一个心跳包。TODO
     */
    public static final int KEEP_ALIVE_INTERVAL_DEFAULT = 60;
    /**
     * The default connection timeout in seconds if one is not specified
     * 如果未指定默认连接超时，以秒为单位。连接超时时间为30秒
     */
    public static final int CONNECTION_TIMEOUT_DEFAULT = 30;
    /**
     * The default max inflight if one is not specified
     * 正在发送的请求数
     */
    public static final int MAX_INFLIGHT_DEFAULT = 10;
    /**
     * The default clean session setting if one is not specified
     * 默认的干净会话设置（如果未指定） TODO
     */
    public static final boolean CLEAN_SESSION_DEFAULT = true;
    /**
     * The default MqttVersion is 3.1.1 first, dropping back to 3.1 if that fails
     * 默认的MqttVersion首先是3.1.1，如果失败则回落到3.1
     */
    public static final int MQTT_VERSION_DEFAULT = 0;
    /**
     * Mqtt Version 3.1
     */
    public static final int MQTT_VERSION_3_1 = 3;
    /**
     * Mqtt Version 3.1.1
     */
    public static final int MQTT_VERSION_3_1_1 = 4;

    protected static final int URI_TYPE_TCP = 0;
    protected static final int URI_TYPE_SSL = 1;
    protected static final int URI_TYPE_LOCAL = 2;
    protected static final int URI_TYPE_WS = 3;
    protected static final int URI_TYPE_WSS = 4;

    private int keepAliveInterval = KEEP_ALIVE_INTERVAL_DEFAULT;
    private int maxInflight = MAX_INFLIGHT_DEFAULT;
    private String willDestination = null; // TODO 发送遗嘱消息的地址，默认是
    private MqttMessage willMessage = null; //TODO 当客户端断开连接时，发送给相关的订阅者的遗嘱消息。
    private String userName;
    private char[] password;
    private SocketFactory socketFactory;
    private Properties sslClientProps = null;
    private HostnameVerifier sslHostnameVerifier = null;
    private boolean cleanSession = CLEAN_SESSION_DEFAULT;
    private int connectionTimeout = CONNECTION_TIMEOUT_DEFAULT;
    private String[] serverURIs = null;
    private int MqttVersion = MQTT_VERSION_DEFAULT;
    private boolean automaticReconnect = false;

    /**
     * Constructs a new <code>MqttConnectOptions</code> object using the
     * default values.
     *
     * The defaults are:
     * <ul>
     * <li>The keepalive interval is 60 seconds</li>
     * <li>Clean Session is true</li>
     * <li>The message delivery retry interval is 15 seconds</li>
     * <li>The connection timeout period is 30 seconds</li>
     * <li>No Will message is set</li> 当客户端断开连接时，发送给相关的订阅者的遗嘱消息。
     * <li>A standard SocketFactory is used</li>
     * </ul>
     * More information about these values can be found in the setter methods.
     *
     * 使用默认的配置信息创建一个MqttConnectOptions
     * 默认的是：
     * 1、心跳包发送的间隔时间：60s
     * 2、是否清楚session
     * 3、发送消息重试的间隔时间为15s
     * 4、连接超时的时间为30s
     * 5、
     */
    public MqttConnectOptionsDemo() {
    }

    /**
     * Returns the password to use for the connection.
     * @return the password to use for the connection.
     */
    public char[] getPassword() {
        return password;
    }

    /**
     * Sets the password to use for the connection.
     * @param password A Char Array of the password
     */
    public void setPassword(char[] password) {
        this.password = password;
    }

    /**
     * Returns the user name to use for the connection.
     * @return the user name to use for the connection.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user name to use for the connection.
     * @param userName The Username as a String
     * @throws IllegalArgumentException if the user name is blank or only
     * contains whitespace characters.
     */
    public void setUserName(String userName) {
        if ((userName != null) && (userName.trim().equals(""))) {
            throw new IllegalArgumentException();
        }
        this.userName = userName;
    }

    /**
     * Sets the "Last Will and Testament" (LWT) for the connection.
     * In the event that this client unexpectedly loses its connection to the
     * server, the server will publish a message to itself using the supplied
     * details.
     * 设置连接的“最后的遗嘱”（LWT）。如果此客户端意外失去与服务器的连接，则服务器将使用提供的详细信息向自身发布消息。
     *
     * @param topic the topic to publish to. 发布的主题
     * @param payload the byte payload for the message. 消息的字节有效负载。
     * @param qos the quality of service to publish the message at (0, 1 or 2). 在以下位置发布消息的服务质量0、1、2
     * @param retained whether or not the message should be retained. 是否保留该消息。
     */
    public void setWill(MqttTopic topic, byte[] payload, int qos, boolean retained) {
        String topicS = topic.getName();
        validateWill(topicS, payload);
        this.setWill(topicS, new MqttMessage(payload), qos, retained);
    }

    /**
     * Sets the "Last Will and Testament" (LWT) for the connection.
     * In the event that this client unexpectedly loses its connection to the
     * server, the server will publish a message to itself using the supplied
     * details.
     *
     * @param topic the topic to publish to.
     * @param payload the byte payload for the message.
     * @param qos the quality of service to publish the message at (0, 1 or 2).
     * @param retained whether or not the message should be retained.
     */
    public void setWill(String topic, byte[] payload, int qos, boolean retained) {
        validateWill(topic, payload);
        this.setWill(topic, new MqttMessage(payload), qos, retained);
    }


    /**
     * Validates the will fields.
     */
    private void validateWill(String dest, Object payload) {
        if ((dest == null) || (payload == null)) {
            throw new IllegalArgumentException();
        }

        MqttTopic.validate(dest, false/*wildcards NOT allowed*/);
    }

    /**
     * Sets up the will information, based on the supplied parameters.
     * 根据提供的参数设置遗嘱信息。
     *
     * @param topic the topic to send the LWT message to
     * @param msg the {@link MqttMessage} to send
     * @param qos the QoS Level to send the message at
     * @param retained whether the message should be retained or not
     */
    protected void setWill(String topic, MqttMessage msg, int qos, boolean retained) {
        willDestination = topic;
        willMessage = msg;
        willMessage.setQos(qos);
        willMessage.setRetained(retained);
        // Prevent any more changes to the will message
        // willMessage.setMutable(false);
    }

    /**
     * Returns the "keep alive" interval.
     * @see #setKeepAliveInterval(int)
     * @return the keep alive interval.
     */
    public int getKeepAliveInterval() {
        return keepAliveInterval;
    }

    /**
     * Returns the MQTT version.
     * @see #setMqttVersion(int)
     * @return the MQTT version.
     */
    public int getMqttVersion() {
        return MqttVersion;
    }

    /**
     * Sets the "keep alive" interval.
     * This value, measured in seconds, defines the maximum time interval
     * between messages sent or received. It enables the client to
     * detect if the server is no longer available, without
     * having to wait for the TCP/IP timeout. The client will ensure
     * that at least one message travels across the network within each
     * keep alive period.  In the absence of a data-related message during
     * the time period, the client sends a very small "ping" message, which
     * the server will acknowledge.
     * A value of 0 disables keepalive processing in the client.
     * <p>The default value is 60 seconds</p>
     * 设置“保持活动”间隔。该值以秒为单位，定义了发送或接收消息之间的最大时间间隔。它使客户端可以检测服务器是否不再可用，
     * 而不必等待TCP / IP超时。客户端将确保在每个有效期内，至少有一条消息在网络上传播。在此时间段内没有数据相关消息的情况下，
     * 客户端将发送一个非常小的“ ping”消息，服务器将予以确认。值为0将禁用客户端中的keepalive处理。默认值为60秒
     * @param keepAliveInterval the interval, measured in seconds, must be &gt;= 0.
     * @throws IllegalArgumentException if the keepAliveInterval was invalid
     */
    public void setKeepAliveInterval(int keepAliveInterval)throws IllegalArgumentException {
        if (keepAliveInterval <0 ) {
            throw new IllegalArgumentException();
        }
        this.keepAliveInterval = keepAliveInterval;
    }

    /**
     * Returns the "max inflight".
     * The max inflight limits to how many messages we can send without receiving acknowledgments.
     * 飞行中的最大次数限制了我们在不接收确认的情况下可以发送多少条消息。
     * @see #setMaxInflight(int)
     * @return the max inflight
     */
    public int getMaxInflight() {
        return maxInflight;
    }

    /**
     * Sets the "max inflight".
     * please increase this value in a high traffic environment.
     * <p>The default value is 10</p>
     * @param maxInflight the number of maxInfligt messages
     */
    public void setMaxInflight(int maxInflight) {
        if (maxInflight < 0) {
            throw new IllegalArgumentException();
        }
        this.maxInflight = maxInflight;
    }

    /**
     * Returns the connection timeout value.
     * @see #setConnectionTimeout(int)
     * @return the connection timeout value.
     */
    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    /**
     * Sets the connection timeout value.
     * This value, measured in seconds, defines the maximum time interval
     * the client will wait for the network connection to the MQTT server to be established.
     * The default timeout is 30 seconds.
     * A value of 0 disables timeout processing meaning the client will wait until the
     * network connection is made successfully or fails.
     * @param connectionTimeout the timeout value, measured in seconds. It must be &gt;0;
     */
    public void setConnectionTimeout(int connectionTimeout) {
        if (connectionTimeout <0 ) {
            throw new IllegalArgumentException();
        }
        this.connectionTimeout = connectionTimeout;
    }

    /**
     * Returns the socket factory that will be used when connecting, or
     * <code>null</code> if one has not been set.
     * @return The Socket Factory
     */
    public SocketFactory getSocketFactory() {
        return socketFactory;
    }

    /**
     * Sets the <code>SocketFactory</code> to use.  This allows an application
     * to apply its own policies around the creation of network sockets.  If
     * using an SSL connection, an <code>SSLSocketFactory</code> can be used
     * to supply application-specific security settings.
     * @param socketFactory the factory to use.
     */
    public void setSocketFactory(SocketFactory socketFactory) {
        this.socketFactory = socketFactory;
    }

    /**
     * Returns the topic to be used for last will and testament (LWT).
     * @return the MqttTopic to use, or <code>null</code> if LWT is not set.
     * @see #setWill(MqttTopic, byte[], int, boolean)
     */
    public String getWillDestination() {
        return willDestination;
    }

    /**
     * Returns the message to be sent as last will and testament (LWT).
     * The returned object is "read only".  Calling any "setter" methods on
     * the returned object will result in an
     * <code>IllegalStateException</code> being thrown.
     * @return the message to use, or <code>null</code> if LWT is not set.
     */
    public MqttMessage getWillMessage() {
        return willMessage;
    }

    /**
     * Returns the SSL properties for the connection.
     * @return the properties for the SSL connection
     */
    public Properties getSSLProperties() {
        return sslClientProps;
    }

    /**
     * Sets the SSL properties for the connection.
     * <p>Note that these
     * properties are only valid if an implementation of the Java
     * Secure Socket Extensions (JSSE) is available.  These properties are
     * <em>not</em> used if a SocketFactory has been set using
     * {@link #setSocketFactory(SocketFactory)}.
     * The following properties can be used:</p>
     * <dl>
     * <dt>com.ibm.ssl.protocol</dt>
     * <dd>One of: SSL, SSLv3, TLS, TLSv1, SSL_TLS.</dd>
     * <dt>com.ibm.ssl.contextProvider
     * <dd>Underlying JSSE provider.  For example "IBMJSSE2" or "SunJSSE"</dd>
     *
     * <dt>com.ibm.ssl.keyStore</dt>
     * <dd>The name of the file that contains the KeyStore object that you
     * want the KeyManager to use. For example /mydir/etc/key.p12</dd>
     *
     * <dt>com.ibm.ssl.keyStorePassword</dt>
     * <dd>The password for the KeyStore object that you want the KeyManager to use.
     * The password can either be in plain-text,
     * or may be obfuscated using the static method:
     * <code>com.ibm.micro.security.Password.obfuscate(char[] password)</code>.
     * This obfuscates the password using a simple and insecure XOR and Base64
     * encoding mechanism. Note that this is only a simple scrambler to
     * obfuscate clear-text passwords.</dd>
     *
     * <dt>com.ibm.ssl.keyStoreType</dt>
     * <dd>Type of key store, for example "PKCS12", "JKS", or "JCEKS".</dd>
     *
     * <dt>com.ibm.ssl.keyStoreProvider</dt>
     * <dd>Key store provider, for example "IBMJCE" or "IBMJCEFIPS".</dd>
     *
     * <dt>com.ibm.ssl.trustStore</dt>
     * <dd>The name of the file that contains the KeyStore object that you
     * want the TrustManager to use.</dd>
     *
     * <dt>com.ibm.ssl.trustStorePassword</dt>
     * <dd>The password for the TrustStore object that you want the
     * TrustManager to use.  The password can either be in plain-text,
     * or may be obfuscated using the static method:
     * <code>com.ibm.micro.security.Password.obfuscate(char[] password)</code>.
     * This obfuscates the password using a simple and insecure XOR and Base64
     * encoding mechanism. Note that this is only a simple scrambler to
     * obfuscate clear-text passwords.</dd>
     *
     * <dt>com.ibm.ssl.trustStoreType</dt>
     * <dd>The type of KeyStore object that you want the default TrustManager to use.
     * Same possible values as "keyStoreType".</dd>
     *
     * <dt>com.ibm.ssl.trustStoreProvider</dt>
     * <dd>Trust store provider, for example "IBMJCE" or "IBMJCEFIPS".</dd>
     *
     * <dt>com.ibm.ssl.enabledCipherSuites</dt>
     * <dd>A list of which ciphers are enabled.  Values are dependent on the provider,
     * for example: SSL_RSA_WITH_AES_128_CBC_SHA;SSL_RSA_WITH_3DES_EDE_CBC_SHA.</dd>
     *
     * <dt>com.ibm.ssl.keyManager</dt>
     * <dd>Sets the algorithm that will be used to instantiate a KeyManagerFactory object
     * instead of using the default algorithm available in the platform. Example values:
     * "IbmX509" or "IBMJ9X509".
     * </dd>
     *
     * <dt>com.ibm.ssl.trustManager</dt>
     * <dd>Sets the algorithm that will be used to instantiate a TrustManagerFactory object
     * instead of using the default algorithm available in the platform. Example values:
     * "PKIX" or "IBMJ9X509".
     * </dd>
     * </dl>
     * @param props The SSL {@link Properties}
     */
    public void setSSLProperties(Properties props) {
        this.sslClientProps = props;
    }

    /**
     * Returns the HostnameVerifier for the SSL connection.
     * @return the HostnameVerifier for the SSL connection
     */
    public HostnameVerifier getSSLHostnameVerifier() {
        return sslHostnameVerifier;
    }

    /**
     * Sets the HostnameVerifier for the SSL connection. Note that it will be
     * used after handshake on a connection and you should do actions by
     * yourserlf when hostname is verified error.
     * <p>
     * There is no default HostnameVerifier
     * </p>
     * @param hostnameVerifier the {@link HostnameVerifier}
     */
    public void setSSLHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.sslHostnameVerifier = hostnameVerifier;
    }

    /**
     * Returns whether the client and server should remember state for the client across reconnects.
     * 返回客户端和服务器是否应记住重新连接时客户端的状态。
     * @return the clean session flag
     */
    public boolean isCleanSession() {
        return this.cleanSession;
    }

    /**
     * Sets whether the client and server should remember state across restarts and reconnects.
     * <ul>
     * <li>If set to false both the client and server will maintain state across
     * restarts of the client, the server and the connection. As state is maintained:
     * <ul>
     * <li>Message delivery will be reliable meeting
     * the specified QOS even if the client, server or connection are restarted.
     * <li> The server will treat a subscription as durable.
     * </ul>
     * <li>If set to true the client and server will not maintain state across
     * restarts of the client, the server or the connection. This means
     * <ul>
     * <li>Message delivery to the specified QOS cannot be maintained if the
     * client, server or connection are restarted
     * <li>The server will treat a subscription as non-durable
     * </ul>
     * </ul>
     * @param cleanSession Set to True to enable cleanSession
     */
    public void setCleanSession(boolean cleanSession) {
        this.cleanSession = cleanSession;
    }

    /**
     * Return a list of serverURIs the client may connect to
     * @return the serverURIs or null if not set
     */
    public String[] getServerURIs() {
        return serverURIs;
    }

    /**
     * Set a list of one or more serverURIs the client may connect to.
     * <p>
     * Each <code>serverURI</code> specifies the address of a server that the client may
     * connect to. Two types of
     * connection are supported <code>tcp://</code> for a TCP connection and
     * <code>ssl://</code> for a TCP connection secured by SSL/TLS.
     * For example:
     * <ul>
     * 	<li><code>tcp://localhost:1883</code></li>
     * 	<li><code>ssl://localhost:8883</code></li>
     * </ul>
     * If the port is not specified, it will
     * default to 1883 for <code>tcp://</code>" URIs, and 8883 for <code>ssl://</code> URIs.
     * <p>
     * If serverURIs is set then it overrides the serverURI parameter passed in on the
     * constructor of the MQTT client.
     * <p>
     * When an attempt to connect is initiated the client will start with the first
     * serverURI in the list and work through
     * the list until a connection is established with a server. If a connection cannot be made to
     * any of the servers then the connect attempt fails.
     * <p>
     * Specifying a list of servers that a client may connect to has several uses:
     * <ol>
     * <li>High Availability and reliable message delivery
     * <p>Some MQTT servers support a high availability feature where two or more
     * "equal" MQTT servers share state. An MQTT client can connect to any of the "equal"
     * servers and be assured that messages are reliably delivered and durable subscriptions
     * are maintained no matter which server the client connects to.</p>
     * <p>The cleansession flag must be set to false if durable subscriptions and/or reliable
     * message delivery is required.</p></li>
     * <li>Hunt List
     * <p>A set of servers may be specified that are not "equal" (as in the high availability
     * option). As no state is shared across the servers reliable message delivery and
     * durable subscriptions are not valid. The cleansession flag must be set to true if the
     * hunt list mode is used</p></li>
     * </ol>
     * @param array of serverURIs
     */
    public void setServerURIs(String[] array) {
        for (int i = 0; i < array.length; i++) {
            validateURI(array[i]);
        }
        this.serverURIs = array;
    }

    /**
     * Validate a URI
     * @param srvURI The Server URI
     * @return the URI type
     */
    public static int validateURI(String srvURI) {
        try {
            URI vURI = new URI(srvURI);
            if ("ws".equals(vURI.getScheme())){
                return URI_TYPE_WS;
            }
            else if ("wss".equals(vURI.getScheme())) {
                return URI_TYPE_WSS;
            }

            if ((vURI.getPath() == null) || vURI.getPath().isEmpty()) {
                // No op path must be empty
            }
            else {
                throw new IllegalArgumentException(srvURI);
            }
            if ("tcp".equals(vURI.getScheme())) {
                return URI_TYPE_TCP;
            }
            else if ("ssl".equals(vURI.getScheme())) {
                return URI_TYPE_SSL;
            }
            else if ("local".equals(vURI.getScheme())) {
                return URI_TYPE_LOCAL;
            }
            else {
                throw new IllegalArgumentException(srvURI);
            }
        } catch (URISyntaxException ex) {
            throw new IllegalArgumentException(srvURI);
        }
    }

    /**
     * Sets the MQTT version.
     * The default action is to connect with version 3.1.1,
     * and to fall back to 3.1 if that fails.
     * Version 3.1.1 or 3.1 can be selected specifically, with no fall back,
     * by using the MQTT_VERSION_3_1_1 or MQTT_VERSION_3_1 options respectively.
     *
     * @param MqttVersion the version of the MQTT protocol.
     * @throws IllegalArgumentException If the MqttVersion supplied is invalid
     */
    public void setMqttVersion(int MqttVersion)throws IllegalArgumentException {
        if (MqttVersion != MQTT_VERSION_DEFAULT &&
                MqttVersion != MQTT_VERSION_3_1 &&
                MqttVersion != MQTT_VERSION_3_1_1) {
            throw new IllegalArgumentException();
        }
        this.MqttVersion = MqttVersion;
    }

    /**
     * Returns whether the client will automatically attempt to reconnect to the
     * server if the connection is lost
     * @return the automatic reconnection flag.
     */
    public boolean isAutomaticReconnect() {
        return automaticReconnect;
    }

    /**
     * Sets whether the client will automatically attempt to reconnect to the
     * server if the connection is lost.
     * <ul>
     * <li>If set to false, the client will not attempt to automatically
     *  reconnect to the server in the event that the connection is lost.</li>
     *  <li>If set to true, in the event that the connection is lost, the client
     *  will attempt to reconnect to the server. It will initially wait 1 second before
     *  it attempts to reconnect, for every failed reconnect attempt, the delay will double
     *  until it is at 2 minutes at which point the delay will stay at 2 minutes.</li>
     * </ul>
     * 设置如果连接断开，客户端是否将自动尝试重新连接到服务器。如果设置为false，则在连接丢失的情况下，
     * 客户端将不会尝试自动重新连接到服务器。如果设置为true，则在连接断开的情况下，客户端将尝试重新连接到服务器。
     * 它最初将等待1秒钟，然后再尝试重新连接，对于每次失败的重新连接尝试，延迟将加倍，直到2分钟为止，此时延迟将保持2分钟。
     * @param automaticReconnect If set to True, Automatic Reconnect will be enabled
     */
    public void setAutomaticReconnect(boolean automaticReconnect) {
        this.automaticReconnect = automaticReconnect;
    }


    /**
     * @return The Debug Properties
     */
    public Properties getDebug() {
        final String strNull="null";
        Properties p = new Properties();
        p.put("MqttVersion", new Integer(getMqttVersion()));
        p.put("CleanSession", Boolean.valueOf(isCleanSession()));
        p.put("ConTimeout", new Integer(getConnectionTimeout()));
        p.put("KeepAliveInterval", new Integer(getKeepAliveInterval()));
        p.put("UserName", (getUserName() == null) ? strNull : getUserName());
        p.put("WillDestination", (getWillDestination() == null) ? strNull : getWillDestination());
        if (getSocketFactory()==null) {
            p.put("SocketFactory", strNull);
        } else {
            p.put("SocketFactory", getSocketFactory());
        }
        if (getSSLProperties()==null) {
            p.put("SSLProperties", strNull);
        } else {
            p.put("SSLProperties", getSSLProperties());
        }
        return p;
    }

    public String toString() {
        return Debug.dumpProperties(getDebug(), "Connection options");
    }
}

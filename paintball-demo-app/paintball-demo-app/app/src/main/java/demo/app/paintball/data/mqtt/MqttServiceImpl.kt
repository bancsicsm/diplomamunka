package demo.app.paintball.data.mqtt

import demo.app.paintball.PaintballApplication.Companion.context
import demo.app.paintball.R
import demo.app.paintball.data.mqtt.messages.ChatMessage
import demo.app.paintball.data.mqtt.messages.GameMessage
import demo.app.paintball.data.mqtt.messages.PositionMessage
import demo.app.paintball.util.toast
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MqttServiceImpl @Inject constructor() : MqttService {

    override var gameListener: MqttService.GameListener? = null
    override var positionListener: MqttService.PositionListener? = null
    override var chatListener: MqttService.ChatListener? = null

    var mqttAndroidClient: MqttAndroidClient =
        MqttAndroidClient(context, "tcp://${context.getString(R.string.serverURL)}:1883", MqttClient.generateClientId())

    init {
        setCallback()
        connect()
    }

    private fun setCallback() {
        mqttAndroidClient.setCallback(object : MqttCallbackExtended {
            override fun connectComplete(b: Boolean, s: String) {
                gameListener?.connectComplete()
            }

            override fun messageArrived(topic: String, mqttMessage: MqttMessage) {
                val rawMessage = mqttMessage.toString()
                when (Topic.find(topic)) {
                    Topic.GAME -> {
                        gameListener?.gameMessageArrived(GameMessage.parse(rawMessage))
                    }
                    Topic.POSITIONS_RED_TEAM, Topic.POSITIONS_BLUE_TEAM -> {
                        positionListener?.positionMessageArrived(PositionMessage.parse(rawMessage))
                    }
                    Topic.CHAT_RED_TEAM, Topic.CHAT_BLUE_TEAM -> {
                        chatListener?.chatMessageArrived(ChatMessage.parse(rawMessage))
                    }
                }
            }

            override fun connectionLost(throwable: Throwable) {}
            override fun deliveryComplete(iMqttDeliveryToken: IMqttDeliveryToken) {}
        })
    }

    private fun connect() {
        val mqttConnectOptions = MqttConnectOptions().apply {
            isAutomaticReconnect = true
            isCleanSession = false
        }

        mqttAndroidClient.connect(mqttConnectOptions, null, object : IMqttActionListener {
            override fun onSuccess(asyncActionToken: IMqttToken) {
                toast("Connected to MQTT broker")
            }

            override fun onFailure(asyncActionToken: IMqttToken, exception: Throwable) {
                toast("MQTT: Failed to connect to: ${context.getString(R.string.serverURL)}")
            }
        })
    }

    override fun subscribe(topic: Topic) {
        mqttAndroidClient.subscribe(topic.value, 0, null)
    }

    override fun unsubscribe(topic: Topic) {
        mqttAndroidClient.unsubscribe(topic.value)
    }

    override fun publish(topic: Topic, message: String) {
        mqttAndroidClient.publish(topic.value, message.toByteArray(), 0, false)
    }
}
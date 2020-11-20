package demo.server.paintball.service

import demo.server.paintball.config.AppConfig
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence
import org.springframework.stereotype.Service

@Service
class MqttServiceImpl(val appConfig: AppConfig) : MqttService {

    private lateinit var mqttClient: MqttClient

    init {
        connect()
    }

    private fun connect() {
        mqttClient = MqttClient(appConfig.mqttBrokerUrl, MqttClient.generateClientId(), MqttDefaultFilePersistence("/tmp"))
        mqttClient.connect()
    }

    override fun publish(topic: String, message: String) {
        val mqttMessage = MqttMessage()
        mqttMessage.payload = message.toByteArray()
        mqttClient.publish(topic, mqttMessage)
    }
}
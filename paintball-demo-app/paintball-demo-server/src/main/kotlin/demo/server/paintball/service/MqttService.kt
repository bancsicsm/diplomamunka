package demo.server.paintball.service

interface MqttService {

    fun publish(topic: String, message: String)
}
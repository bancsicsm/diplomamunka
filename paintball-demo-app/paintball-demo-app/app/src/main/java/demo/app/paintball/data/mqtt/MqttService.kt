package demo.app.paintball.data.mqtt

import demo.app.paintball.data.mqtt.messages.ChatMessage
import demo.app.paintball.data.mqtt.messages.GameMessage
import demo.app.paintball.data.mqtt.messages.PositionMessage

interface MqttService {

    var gameListener: GameListener?
    var positionListener: PositionListener?
    var chatListener: ChatListener?

    fun subscribe(topic: Topic)

    fun unsubscribe(topic: Topic)

    fun publish(topic: Topic, message: String)

    interface GameListener {
        fun connectComplete()
        fun gameMessageArrived(message: GameMessage)
    }

    interface PositionListener {
        fun positionMessageArrived(message: PositionMessage)
    }

    interface ChatListener {
        fun chatMessageArrived(message: ChatMessage)
    }
}
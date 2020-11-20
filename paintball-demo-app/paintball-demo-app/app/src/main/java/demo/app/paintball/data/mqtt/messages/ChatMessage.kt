package demo.app.paintball.data.mqtt.messages

import demo.app.paintball.data.mqtt.MqttService
import demo.app.paintball.data.rest.models.Player
import demo.app.paintball.util.getTeamChatTopic

class ChatMessage {

    var raw: String = ""
    var message: String = ""

    // TODO: refactor, insert playername and use it at publish (e.g.: PositionMessage)
    var playerName: String = ""

    companion object {
        const val SEPARATOR = '|'

        fun parse(raw: String): ChatMessage {
            val split = raw.split(SEPARATOR)
            return ChatMessage().apply {
                this.raw = raw
                message = split[0]
                playerName = split[1]
            }
        }

        fun build(message: String, playerName: String = "") =
            ChatMessage().apply {
                raw = "$message$SEPARATOR$playerName"
                this.message = message
                this.playerName = playerName
            }
    }

    fun publish(mqttService: MqttService, player: Player) {
        mqttService.publish(player.getTeamChatTopic(), raw)
    }
}
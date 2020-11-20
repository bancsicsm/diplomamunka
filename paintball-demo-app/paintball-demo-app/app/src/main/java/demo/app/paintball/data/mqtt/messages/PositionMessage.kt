package demo.app.paintball.data.mqtt.messages

import demo.app.paintball.data.mqtt.MqttService
import demo.app.paintball.data.rest.models.Player
import demo.app.paintball.util.getTeamPositionsTopic

class PositionMessage {

    var raw: String = ""
    var player: Player = Player()
    var posX: Int = 0
    var posY: Int = 0

    companion object {
        const val SEPARATOR = '|'

        fun parse(raw: String): PositionMessage {
            val split = raw.split(SEPARATOR)
            return PositionMessage().apply {
                this.raw = raw
                player.name = split[0]
                posX = split[1].toInt()
                posY = split[2].toInt()
            }
        }

        fun build(player: Player, posX: Int, posY: Int) =
            PositionMessage().apply {
                raw = "${player.name}$SEPARATOR$posX$SEPARATOR$posY"
                this.player = player
                this.posX = posX
                this.posY = posY
            }
    }

    fun publish(mqttService: MqttService) {
        mqttService.publish(player.getTeamPositionsTopic(), raw)
    }
}
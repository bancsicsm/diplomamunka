package demo.server.paintball.mock

import demo.server.paintball.config.AppConfig
import demo.server.paintball.data.Player
import demo.server.paintball.service.GameService
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class TestService(private val appConfig: AppConfig) {

    fun initPlayers() {
        if (appConfig.environment == "test") {
            val redPlayer1 = Player().apply { name = "Dani" }
            val bluePlayer1 = Player().apply { name = "Mate" }

            GameService.game?.redTeam?.addAll(
                    mutableListOf(redPlayer1)
            )
            GameService.game?.blueTeam?.addAll(
                    mutableListOf(bluePlayer1)
            )
        }
    }

    fun getPositions(playerName: String, startX: Int, startY: Int, step: Int): List<String> {
        val positions = mutableListOf<String>()
        val randomRange = Random.nextInt(200, 400)
        var startX_ = startX
        var startY_ = startY

        for (pos in startX .. startX + randomRange step step) {
            positions.add("$playerName|$pos|$startY")
            startX_ = pos
        }
        for (pos in startY .. startY + randomRange step step) {
            positions.add("$playerName|$startX_|$pos")
            startY_ = pos
        }
        for (pos in startX_ downTo startX step step) {
            positions.add("$playerName|$pos|$startY_")
        }
        for (pos in startY_ downTo startY step step) {
            positions.add("$playerName|$startX|$pos")
        }
        return positions
    }
}
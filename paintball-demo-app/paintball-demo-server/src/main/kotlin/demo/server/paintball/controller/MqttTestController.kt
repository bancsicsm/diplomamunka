package demo.server.paintball.controller

import demo.server.paintball.config.AppConfig
import demo.server.paintball.mock.TestService
import demo.server.paintball.service.GameService
import demo.server.paintball.service.MqttService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import kotlin.concurrent.schedule
import kotlin.random.Random

@RestController
@RequestMapping(MqttTestController.BASE_URL)
class MqttTestController(val mqttService: MqttService,
                         val testService: TestService,
                         val appConfig: AppConfig) {

    companion object {
        const val BASE_URL = "/api/mqtt"
        const val RED_TEAM_TOPIC = "positions/redTeam"
        const val BLUE_TEAM_TOPIC = "positions/blueTeam"
        const val POSITION_DELAY = 40L
    }

    private lateinit var timer: Timer

    @PostMapping("test/positions/start")
    fun startPositionSending() {
        timer = Timer()
        if (appConfig.environment == "test") {
            val redTeam = GameService.game?.redTeam
            val blueTeam = GameService.game?.blueTeam
            redTeam?.filter { !it.isAdmin }?.forEach {
                val testPositions = testService.getPositions(it.name, 0, 0, 10)
                var i = 0
                timer.schedule(POSITION_DELAY, Random.nextLong(50, 150)) {
                    if (i < testPositions.size - 1) i++ else i = 0
                    mqttService.publish(
                            topic = RED_TEAM_TOPIC,
                            message = testPositions[i]
                    )
                }
            }
            blueTeam?.filter { !it.isAdmin }?.forEach {
                val testPositions = testService.getPositions(it.name, 3000, 4000, 10)
                var i = 0
                timer.schedule(POSITION_DELAY, Random.nextLong(50, 150)) {
                    if (i < testPositions.size - 1) i++ else i = 0
                    mqttService.publish(
                            topic = BLUE_TEAM_TOPIC,
                            message = testPositions[i]
                    )
                }
            }
        }
    }

    @PostMapping("test/positions/stop")
    fun stopPositionSending() {
        timer.cancel()
    }
}
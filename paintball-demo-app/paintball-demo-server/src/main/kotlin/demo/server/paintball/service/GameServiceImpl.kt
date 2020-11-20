package demo.server.paintball.service

import demo.server.paintball.data.Game
import demo.server.paintball.data.Player
import demo.server.paintball.mock.TestService
import org.springframework.stereotype.Service

@Service
class GameServiceImpl(val testService: TestService) : GameService {

    override fun getGame() = GameService.game

    override fun createGame(game: Game) {
        GameService.game = game
        testService.initPlayers()
    }

    override fun deleteGame() {
        GameService.game = null
    }

    override fun addRedPlayer(player: Player) {
        GameService.game?.redTeam?.add(player)
    }

    override fun addBluePlayer(player: Player) {
        GameService.game?.blueTeam?.add(player)
    }
}

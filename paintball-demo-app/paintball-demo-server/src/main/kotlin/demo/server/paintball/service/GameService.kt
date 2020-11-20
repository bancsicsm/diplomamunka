package demo.server.paintball.service

import demo.server.paintball.data.Game
import demo.server.paintball.data.Player

interface GameService {

    companion object {
        var game: Game? = null
    }

    fun getGame(): Game?

    fun createGame(game: Game)

    fun deleteGame()

    fun addRedPlayer(player: Player)

    fun addBluePlayer(player: Player)
}
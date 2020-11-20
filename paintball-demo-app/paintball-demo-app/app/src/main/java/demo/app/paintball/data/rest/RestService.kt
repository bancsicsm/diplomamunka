package demo.app.paintball.data.rest

import demo.app.paintball.data.rest.models.Game
import demo.app.paintball.data.rest.models.Player
import retrofit2.Response

interface RestService {

    var listener: SuccessListener

    var errorListener: ErrorListener

    fun getGame()

    fun createGame(game: Game)

    fun deleteGame()

    fun addRedPlayer(player: Player)

    fun addBluePlayer(player: Player)

    interface SuccessListener {
        fun getGameSuccess(response: Response<Game>)
        fun createGameSuccess()
        fun addRedPlayerSuccess()
        fun addBluePlayerSuccess()
    }

    interface ErrorListener {
        fun handleError(t: Throwable)
    }
}
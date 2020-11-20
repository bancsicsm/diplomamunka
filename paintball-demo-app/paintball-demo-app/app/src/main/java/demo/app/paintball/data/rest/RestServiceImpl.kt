package demo.app.paintball.data.rest

import demo.app.paintball.data.rest.models.Game
import demo.app.paintball.data.rest.models.Player
import demo.app.paintball.util.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestServiceImpl @Inject constructor() : RestService {

    private val gameApi: GameApi = GameApi.create()

    override lateinit var listener: RestService.SuccessListener

    override lateinit var errorListener: RestService.ErrorListener

    override fun getGame() {
        gameApi.getGame().enqueue(object : Callback<Game> {
            override fun onResponse(call: Call<Game>, response: Response<Game>) {
                listener.getGameSuccess(response)
            }

            override fun onFailure(call: Call<Game>, t: Throwable) {
                errorListener.handleError(t)
            }
        })
    }

    override fun createGame(game: Game) {
        gameApi.createGame(game).enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                listener.createGameSuccess()
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                errorListener.handleError(t)
            }
        })
    }

    override fun deleteGame() {
        gameApi.deleteGame().enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                toast("Game deleted")
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                errorListener.handleError(t)
            }
        })
    }

    override fun addRedPlayer(player: Player) {
        gameApi.addRedPlayer(player).enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                listener.addRedPlayerSuccess()
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                errorListener.handleError(t)
            }
        })
    }

    override fun addBluePlayer(player: Player) {
        gameApi.addBluePlayer(player).enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                listener.addBluePlayerSuccess()
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                errorListener.handleError(t)
            }
        })
    }
}
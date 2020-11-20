package demo.app.paintball.activities

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import demo.app.paintball.PaintballApplication
import demo.app.paintball.R
import demo.app.paintball.data.rest.RestService
import demo.app.paintball.data.rest.models.Game
import demo.app.paintball.data.rest.models.Player
import demo.app.paintball.fragments.dialogs.ConnectTagFragment
import demo.app.paintball.fragments.dialogs.CreateGameFragment
import demo.app.paintball.fragments.dialogs.JoinGameFragment
import demo.app.paintball.util.ErrorHandler
import demo.app.paintball.util.checkPermissions
import demo.app.paintball.util.services.PlayerService
import kotlinx.android.synthetic.main.activity_dashboard.*
import retrofit2.Response
import javax.inject.Inject

class DashboardActivity : AppCompatActivity(), RestService.SuccessListener,
    JoinGameFragment.JoinGameListener, CreateGameFragment.CreateGameListener, ConnectTagFragment.ConnectTagListener {

    companion object {
        val permissionsNeeded = arrayListOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }

    @Inject
    lateinit var restService: RestService

    @Inject
    lateinit var playerService: PlayerService

    private lateinit var playerName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        playerService = PaintballApplication.services.player()
        btnCreateGame.setOnClickListener { CreateGameFragment().show(supportFragmentManager, "TAG") }
        btnJoinGame.setOnClickListener { JoinGameFragment().show(supportFragmentManager, "TAG") }
        btnConnectTag.setOnClickListener { ConnectTagFragment().show(supportFragmentManager, "TAG") }
        checkTagsEnabled()
        this.checkPermissions(permissionsNeeded)
    }

    private fun checkTagsEnabled() {
        if (resources.getBoolean(R.bool.tagsEnabled)) {
            btnCreateGame.isEnabled = false
            btnJoinGame.isEnabled = false
        } else {
            btnConnectTag.isEnabled = false
        }
    }

    override fun onResume() {
        super.onResume()
        restService =
            PaintballApplication.services.rest().apply { listener = this@DashboardActivity; errorListener = ErrorHandler }
        restService.getGame()
    }

    override fun onJoinGame(playerName: String) {
        playerService.player = Player(name = playerName, isAdmin = false)
        val intent = Intent(this, JoinGameActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateGame(playerName: String, game: Game) {
        this.playerName = playerName
        restService.createGame(game)
    }

    override fun getGameSuccess(response: Response<Game>) {
        if (response.code() != 404) {
            btnCreateGame.isEnabled = false
        }
    }

    override fun createGameSuccess() {
        playerService.player = Player(name = playerName, isAdmin = true)
        val intent = Intent(this, JoinGameActivity::class.java)
        startActivity(intent)
    }

    override fun addRedPlayerSuccess() {
    }

    override fun addBluePlayerSuccess() {
    }

    override fun onTagConnected() {
        btnCreateGame.isEnabled = true
        btnJoinGame.isEnabled = true
        btnConnectTag.isEnabled = false
    }
}
package demo.app.paintball.activities

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import demo.app.paintball.PaintballApplication
import demo.app.paintball.R
import demo.app.paintball.config.Config
import demo.app.paintball.data.ble.BleService
import demo.app.paintball.data.ble.BleServiceImpl
import demo.app.paintball.data.ble.data.BlePositionData
import demo.app.paintball.data.mqtt.MqttService
import demo.app.paintball.data.mqtt.messages.PositionMessage
import demo.app.paintball.data.rest.RestService
import demo.app.paintball.data.rest.models.Game
import demo.app.paintball.map.MapView
import demo.app.paintball.map.rendering.MapViewImpl
import demo.app.paintball.map.sensors.GestureSensor
import demo.app.paintball.map.sensors.Gyroscope
import demo.app.paintball.util.*
import demo.app.paintball.util.positioning.PositionCalculator
import demo.app.paintball.util.positioning.PositionCalculatorImpl
import demo.app.paintball.util.services.PlayerService
import kotlinx.android.synthetic.main.activity_map.*
import retrofit2.Response
import javax.inject.Inject

class MapActivity : AppCompatActivity(), GestureSensor.GestureListener, Gyroscope.GyroscopeListener, RestService.SuccessListener,
    MqttService.PositionListener, MapViewImpl.MapViewCreatedListener, BleServiceImpl.BleServiceListener,
    PositionCalculator.PositionCalculatorListener {

    @Inject
    lateinit var restService: RestService

    @Inject
    lateinit var mqttService: MqttService

    @Inject
    lateinit var playerService: PlayerService

    @Inject
    lateinit var bleService: BleService

    private var game: Game? = null

    private lateinit var map: MapView

    private lateinit var gyroscope: Gyroscope

    private lateinit var positionCalculator: PositionCalculator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        map = mapView

        map.setOnTouchListener(GestureSensor(gestureListener = this))
        gyroscope = Gyroscope(gyroscopeListener = this)

        playerService = PaintballApplication.services.player()
        restService = PaintballApplication.services.rest().apply { listener = this@MapActivity; errorListener = ErrorHandler }
        mqttService = PaintballApplication.services.mqtt().apply { positionListener = this@MapActivity }
        bleService = PaintballApplication.services.ble().also { it.addListener(this@MapActivity) }

        restService.getGame()
        mqttService.subscribe(playerService.player.getTeamChatTopic())
        mqttService.subscribe(playerService.player.getTeamPositionsTopic())
        bleService.startPositionSending()

        positionCalculator = PositionCalculatorImpl(Config.mapConfig.anchors).apply { listener = this@MapActivity }
    }

    override fun onResume() {
        super.onResume()
        gyroscope.start()
    }

    override fun onPause() {
        super.onPause()
        gyroscope.stop()
    }

    override fun mapViewCreated() {
        if (resources.getBoolean(R.bool.displayAnchors)) {
            Config.mapConfig.anchors
                .filter { it[2] != 0 }
                .forEach { map.addAnchor(it[0], it[1]) }
        }
    }

    override fun onBackPressed() {
    }

    override fun onScaleChanged(scaleFactor: Float) {
        map.zoom(scaleFactor)
    }

    override fun onZoomIn() {
        hideButtons()
    }

    override fun onZoomOut() {
        gameDetailLayout.animate().translationX(0F)
    }

    override fun onOrientationChanged(radian: Float) {
        map.setPlayerOrientation(radian.toDegree())
    }

    override fun getGameSuccess(response: Response<Game>) {
        game = response.body()
        addPlayersToMap()
    }

    private fun addPlayersToMap() {
        game?.redTeam
            ?.filter { it.name != playerService.player.name }
            ?.forEach { map.addRedPlayer(it.name) }
        game?.blueTeam
            ?.filter { it.name != playerService.player.name }
            ?.forEach { map.addBluePlayer(it.name) }
    }

    override fun createGameSuccess() {
    }

    override fun addRedPlayerSuccess() {
    }

    override fun addBluePlayerSuccess() {
    }

    override fun positionMessageArrived(message: PositionMessage) {
        map.setMovablePosition(message.player.name, message.posX, message.posY)
    }

    override fun onBleConnected(connection: BleService) {
        bleService.startPositionSending()
    }

    override fun onBlePositionDataReceived(connection: BleService, data: BlePositionData) {
        positionCalculator.calculate(data)
    }

    override fun onBleDisconnected(connection: BleService) {
        toast("Tag disconnected")
    }

    override fun onPositionCalculated(posX: Int, posY: Int) {
        map.setPlayerPosition(posX, posY)
        PositionMessage.build(playerService.player, posX, posY)
            .publish(mqttService)
    }

    private fun hideButtons() {
        gameDetailLayout.animate().translationX(-300F)
    }

    override fun onDestroy() {
        super.onDestroy()
        bleService.removeListener(this)
        bleService.disconnectDevice()
    }
}
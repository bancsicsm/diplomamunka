package demo.app.paintball.util.services

import demo.app.paintball.data.rest.models.Player
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayerService @Inject constructor() {

    lateinit var player: Player
}
package demo.app.paintball.data.rest.models

class Player(
    var name: String = "",
    var deviceName: String = "",
    var team: String? = null,
    var isAdmin: Boolean = false
)
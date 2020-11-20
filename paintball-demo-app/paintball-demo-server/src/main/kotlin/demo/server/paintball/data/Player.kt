package demo.server.paintball.data

class Player(
        var name: String = "",
        var deviceName: String = "",
        var team: String? = null,
        var isAdmin: Boolean = false
)
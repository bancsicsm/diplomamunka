package demo.app.paintball.data.rest.models

class Game(
    var name: String = "",
    var type: String = "",
    var time: Int = 0,
    var admin: String = "",
    var redTeam: MutableList<Player> = mutableListOf(),
    var blueTeam: MutableList<Player> = mutableListOf(),
    val playerCnt: Int = 0
)
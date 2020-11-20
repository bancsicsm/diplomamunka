package demo.app.paintball.data.mqtt

enum class Topic(val value: String) {
    GAME("game"),
    POSITIONS_RED_TEAM("positions/redTeam"),
    POSITIONS_BLUE_TEAM("positions/blueTeam"),
    CHAT_RED_TEAM("chat/redTeam"),
    CHAT_BLUE_TEAM("chat/blueTeam");

    companion object {
        fun find(value: String): Topic = values().find { it.value == value }!!
    }
}
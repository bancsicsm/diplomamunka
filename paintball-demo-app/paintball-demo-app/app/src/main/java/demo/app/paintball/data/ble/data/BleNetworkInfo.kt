package demo.app.paintball.data.ble.data

class BleNetworkInfo(
    var tagId: Short = 0,
    var groupId: Short = 0,
    var anchorCount: Short = 0,
    var tagCount: Short = 0
) {
    override fun toString() =
        String.format("Network: 0x%04X (%d tag, %d anchor), tagId: 0x%04X", groupId, tagCount, anchorCount, tagId)
}
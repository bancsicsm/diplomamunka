package demo.app.paintball.data.ble.data

import android.os.SystemClock
import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.experimental.and

class BlePositionData(ts: Short = 0, ranges: ShortArray = shortArrayOf()) {

    /*
      Timestamp of the beginning of the superframe containing the ranging information
      The timestamp measured by a 16 bit wide counter running at 32768/33 Hz
     */
    var ts: Short
        private set

    /*
      Raw data calculated by tag, to be processed in algorithm
     */
    var ranges: ShortArray
        private set

    var createdTs: Long
        private set

    init {
        this.ts = ts
        this.ranges = ranges
        this.createdTs = SystemClock.uptimeMillis()
    }

    companion object {
        fun parse(bytes: ByteArray, anchorCount: Short): BlePositionData {
            val bb = ByteBuffer.wrap(bytes)
            bb.order(ByteOrder.LITTLE_ENDIAN)
            return BlePositionData(
                ts = bb.short and 0xFFFF.toShort(),
                ranges = generateRanges(bb, anchorCount)
            )
        }

        private fun generateRanges(bb: ByteBuffer, anchorCount: Short): ShortArray {
            val ranges = ShortArray(anchorCount * (anchorCount - 1) / 2 + 1)
            for (i in ranges.indices) {
                ranges[i] = bb.short
            }
            return ranges
        }
    }
}
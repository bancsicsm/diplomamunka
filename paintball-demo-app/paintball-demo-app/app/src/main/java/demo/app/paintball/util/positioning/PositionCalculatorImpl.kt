package demo.app.paintball.util.positioning

import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import demo.app.paintball.data.ble.data.BlePositionData
import demo.app.paintball.util.toast
import org.apache.commons.math3.linear.MatrixUtils
import org.apache.commons.math3.linear.MatrixUtils.createRealMatrix
import org.apache.commons.math3.linear.SingularMatrixException
import java.util.*
import kotlin.math.pow
import kotlin.math.round
import kotlin.math.sqrt

class PositionCalculatorImpl(private val anchors: List<IntArray>) : PositionCalculator {

    companion object {
        /*
        Stopping condition: maximum number of iterations
            - set to 15-30, based on runtime
         */
        const val MAXITER = 30

        /*
        Stopping condition: error limit of differences between iterations
            - set to 50 or 100
         */
        const val ERROR = 100.0

        /*
        Tag height (now fixed)
         */
        const val ZT = 1100.0

        /*
        Number of last positions to be averaged
         */
        const val WINDOW_SIZE = 12
    }

    override lateinit var listener: PositionCalculator.PositionCalculatorListener

    /*
    Measurement results: distance differences between anchors (will be loaded to zk)
     */
    private lateinit var data: BlePositionData

    /*
    Number of combinations of anchors
     */
    private val anchorCombinationNumber = anchors.size * (anchors.size - 1) / 2

    /*
    Combination of all anchor positions
     */
    private val qa = createRealMatrix(anchorCombinationNumber, 6)

    /*
    Previous position
     */
    private var qPrev = createRealMatrix(2, 1)

    private val lastResultsX: Deque<Int> = LinkedList()
    private val lastResultsY: Deque<Int> = LinkedList()

    private val handler = Handler(Looper.getMainLooper())

    init {
        generateQa()
        initQPrev()
    }

    private fun generateQa() {
        var k = 0
        for (i in 1 until anchors.size) {
            for (j in 0 until i) {
                qa.setEntry(k, 0, anchors[i][0].toDouble())
                qa.setEntry(k, 1, anchors[i][1].toDouble())
                qa.setEntry(k, 2, anchors[i][2].toDouble())
                qa.setEntry(k, 3, anchors[j][0].toDouble())
                qa.setEntry(k, 4, anchors[j][1].toDouble())
                qa.setEntry(k, 5, anchors[j][2].toDouble())
                k++
            }
        }
    }

    private fun initQPrev() {
        var xacc = 0.0
        var yacc = 0.0
        var n = 0
        for (i in anchors.indices) {
            if (anchors[i][2] != 0) {
                xacc += anchors[i][0]
                yacc += anchors[i][1]
                n++
            }
        }
        qPrev.setEntry(0, 0, xacc / n)
        qPrev.setEntry(1, 0, yacc / n)
    }

    override fun calculate(data: BlePositionData) {
        this.data = data
        handler.post(runnable)
    }

    private val runnable = Runnable {
        val start = SystemClock.uptimeMillis()

        try {

            val zk = createRealMatrix(anchorCombinationNumber, 1)

            for (i in 0 until anchorCombinationNumber) {
                zk.setEntry(i, 0, data.ranges[i + 1].toDouble())
            }

            var ncomb = anchorCombinationNumber - 1
            val q = createRealMatrix(2, 1)

            var eta = 100000.0
            var iter = 1

            q.setEntry(0, 0, qPrev.getEntry(0, 0))
            q.setEntry(1, 0, qPrev.getEntry(1, 0))

            while ((zk.getEntry(ncomb, 0) == -32768.0 || zk.getEntry(ncomb, 0) == 0.0) && ncomb > 0) {
                ncomb--
            }

            if (ncomb + 1 < 3) { //skip iteration if there are not enough anchors
                iter = MAXITER
            }

            while (eta > ERROR && iter < MAXITER) { //gauss newton iteration

                val rez = createRealMatrix(ncomb + 1, 1)
                val jac = createRealMatrix(ncomb + 1, 2)

                var j = 0
                for (i in 0..ncomb) {
                    if (zk.getEntry(i, 0) == -32768.0 || zk.getEntry(i, 0) == -1.0) {
                        continue
                    }

                    val hkmValue1 = sqrt(
                        (q.getEntry(0, 0) - qa.getEntry(i, 0)).pow(2) +
                                (q.getEntry(1, 0) - qa.getEntry(i, 1)).pow(2) +
                                (ZT - qa.getEntry(i, 2)).pow(2)
                    )
                    val hkmValue2 = sqrt(
                        (q.getEntry(0, 0) - qa.getEntry(i, 3)).pow(2) +
                                (q.getEntry(1, 0) - qa.getEntry(i, 4)).pow(2) +
                                (ZT - qa.getEntry(i, 5)).pow(2)
                    )
                    rez.setEntry(j, 0, hkmValue1 - hkmValue2 - 2*zk.getEntry(i, 0))

                    val hxValue1 =
                        (q.getEntry(0, 0) - qa.getEntry(i, 0)) / hkmValue1 - (q.getEntry(
                            0,
                            0
                        ) - qa.getEntry(i, 3)) / hkmValue2
                    jac.setEntry(j, 0, hxValue1)

                    val hxValue2 =
                        (q.getEntry(1, 0) - qa.getEntry(i, 1)) / hkmValue1 - (q.getEntry(
                            1,
                            0
                        ) - qa.getEntry(i, 4)) / hkmValue2
                    jac.setEntry(j, 1, hxValue2)

                    j++
                }

                if (j < 3) {
                    break
                }

                val jacT = jac.transpose()
                val a = jacT.multiply(jac)
                val b = MatrixUtils.inverse(a)
                val c = b.multiply(jacT)
                val delta = c.multiply(rez)

                q.setEntry(0, 0, q.getEntry(0, 0) - delta.getEntry(0, 0))
                q.setEntry(1, 0, q.getEntry(1, 0) - delta.getEntry(1, 0))

                eta = sqrt(delta.getEntry(0, 0).pow(2) + delta.getEntry(1, 0).pow(2))

                iter++
             
               if (eta>100000) //skip calculation if iteration is divergent
                {
                    iter= MAXITER;
                }

                            }

            if (iter == MAXITER) {
                q.setEntry(0, 0, qPrev.getEntry(0, 0))
                q.setEntry(1, 0, qPrev.getEntry(1, 0))
            }

            qPrev.setEntry(0, 0, q.getEntry(0, 0))
            qPrev.setEntry(1, 0, q.getEntry(1, 0))

            println(
                "Position calculated: ${q.getEntry(0, 0)}, " +
                        "${q.getEntry(1, 0)}, " +
                        "time: ${SystemClock.uptimeMillis() - start} ms"
            )

            val resultX = round(q.getEntry(0, 0)).toInt()
            val resultY = round(q.getEntry(1, 0)).toInt()
            lastResultsX.addFirst(resultX)
            lastResultsY.addFirst(resultY)
            if (lastResultsX.size == WINDOW_SIZE) {
                lastResultsX.removeLast()
                lastResultsY.removeLast()
            }

            val posX = lastResultsX.average().toInt()
            val posY = lastResultsY.average().toInt()
            listener.onPositionCalculated(posX, posY)

        } catch (e: SingularMatrixException) {
            toast("Singular matrix exception: $e")
        }
    }
}
package demo.app.paintball.util

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.graphics.Canvas
import android.graphics.Color
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import demo.app.paintball.PaintballApplication.Companion.context
import demo.app.paintball.config.Config
import demo.app.paintball.data.mqtt.Topic
import demo.app.paintball.data.rest.models.Player

// ====================================
//  CONVENIENCE
// ====================================

fun toast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, text, duration).show()
}

fun Canvas.clear() {
    this.drawColor(Color.DKGRAY)
}

@SuppressLint("NewApi")
fun View.setBackgroundTint(colorId: Int) {
    this.backgroundTintList = ContextCompat.getColorStateList(context, colorId)
}

// ====================================
//  MATH
// ====================================

fun Float.toDegree() = Math.toDegrees(this.toDouble()).toFloat()

fun Float.to2PIRadiant() = if (this < 0) (2 * Math.PI + this).toFloat() else this

fun Int.mmToPx(): Int {
    val unitMm = Config.mapConfig.anchorOriginPosX.toFloat()
    val unitPx = Config.mapConfig.anchorOriginPxX.toFloat()
    val imagePixel = Config.mapConfig.imageWidthPx.toFloat()
    val imageBitmap =
        ResourcesCompat.getDrawable(context.resources, Config.mapConfig.imageDrawableId, null)!!.intrinsicWidth.toFloat()

    return ((this / unitMm * unitPx) * imageBitmap / imagePixel).toInt()
}

fun Int.xToMapPx(): Int {
    val anchorXMm = Config.mapConfig.anchorOriginPosX
    return (anchorXMm + this).mmToPx()
}

fun Int.yToMapPx(): Int {
    val anchorYMm = Config.mapConfig.anchorOriginPosY
    return (anchorYMm - this).mmToPx()
}

// ====================================
//  TOPICS
// ====================================

// TODO: refactor to config classes: create Team class -> RedTeam, Blueteam subclasses. store these infos in them
fun Player.getTeamPositionsTopic() = when (team) {
    "RED" -> Topic.POSITIONS_RED_TEAM
    "BLUE" -> Topic.POSITIONS_BLUE_TEAM
    else -> Topic.POSITIONS_BLUE_TEAM
}

fun Player.getEnemyPositionsTopic() = when (team) {
    "RED" -> Topic.POSITIONS_BLUE_TEAM
    "BLUE" -> Topic.POSITIONS_RED_TEAM
    else -> Topic.POSITIONS_BLUE_TEAM
}

fun Player.getTeamChatTopic() = when (team) {
    "RED" -> Topic.CHAT_RED_TEAM
    "BLUE" -> Topic.CHAT_BLUE_TEAM
    else -> Topic.CHAT_BLUE_TEAM
}

fun Player.getEnemyChatTopic() = when (team) {
    "RED" -> Topic.CHAT_RED_TEAM
    "BLUE" -> Topic.CHAT_BLUE_TEAM
    else -> Topic.CHAT_BLUE_TEAM
}

// ====================================
//  PERMISSIONS
// ====================================

fun Activity.checkPermissions(permissions: List<String>) {
    permissions.filter { ContextCompat.checkSelfPermission(context, it) != PackageManager.PERMISSION_GRANTED }
        .map { ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 0) }
}
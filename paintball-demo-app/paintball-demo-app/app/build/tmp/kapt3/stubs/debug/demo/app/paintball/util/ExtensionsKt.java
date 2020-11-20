package demo.app.paintball.util;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 2, d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u001a\u0018\u0010\u0006\u001a\u00020\u0001*\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t\u001a\n\u0010\n\u001a\u00020\u0001*\u00020\u000b\u001a\n\u0010\f\u001a\u00020\r*\u00020\u000e\u001a\n\u0010\u000f\u001a\u00020\r*\u00020\u000e\u001a\n\u0010\u0010\u001a\u00020\r*\u00020\u000e\u001a\n\u0010\u0011\u001a\u00020\r*\u00020\u000e\u001a\n\u0010\u0012\u001a\u00020\u0005*\u00020\u0005\u001a\u0014\u0010\u0013\u001a\u00020\u0001*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0005H\u0007\u001a\n\u0010\u0016\u001a\u00020\u0017*\u00020\u0017\u001a\n\u0010\u0018\u001a\u00020\u0017*\u00020\u0017\u001a\n\u0010\u0019\u001a\u00020\u0005*\u00020\u0005\u001a\n\u0010\u001a\u001a\u00020\u0005*\u00020\u0005\u00a8\u0006\u001b"}, d2 = {"toast", "", "text", "", "duration", "", "checkPermissions", "Landroid/app/Activity;", "permissions", "", "clear", "Landroid/graphics/Canvas;", "getEnemyChatTopic", "Ldemo/app/paintball/data/mqtt/Topic;", "Ldemo/app/paintball/data/rest/models/Player;", "getEnemyPositionsTopic", "getTeamChatTopic", "getTeamPositionsTopic", "mmToPx", "setBackgroundTint", "Landroid/view/View;", "colorId", "to2PIRadiant", "", "toDegree", "xToMapPx", "yToMapPx", "app_debug"})
public final class ExtensionsKt {
    
    public static final void toast(@org.jetbrains.annotations.NotNull()
    java.lang.String text, int duration) {
    }
    
    public static final void clear(@org.jetbrains.annotations.NotNull()
    android.graphics.Canvas $this$clear) {
    }
    
    @android.annotation.SuppressLint(value = {"NewApi"})
    public static final void setBackgroundTint(@org.jetbrains.annotations.NotNull()
    android.view.View $this$setBackgroundTint, int colorId) {
    }
    
    public static final float toDegree(float $this$toDegree) {
        return 0.0F;
    }
    
    public static final float to2PIRadiant(float $this$to2PIRadiant) {
        return 0.0F;
    }
    
    public static final int mmToPx(int $this$mmToPx) {
        return 0;
    }
    
    public static final int xToMapPx(int $this$xToMapPx) {
        return 0;
    }
    
    public static final int yToMapPx(int $this$yToMapPx) {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final demo.app.paintball.data.mqtt.Topic getTeamPositionsTopic(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.rest.models.Player $this$getTeamPositionsTopic) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final demo.app.paintball.data.mqtt.Topic getEnemyPositionsTopic(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.rest.models.Player $this$getEnemyPositionsTopic) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final demo.app.paintball.data.mqtt.Topic getTeamChatTopic(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.rest.models.Player $this$getTeamChatTopic) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final demo.app.paintball.data.mqtt.Topic getEnemyChatTopic(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.rest.models.Player $this$getEnemyChatTopic) {
        return null;
    }
    
    public static final void checkPermissions(@org.jetbrains.annotations.NotNull()
    android.app.Activity $this$checkPermissions, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> permissions) {
    }
}
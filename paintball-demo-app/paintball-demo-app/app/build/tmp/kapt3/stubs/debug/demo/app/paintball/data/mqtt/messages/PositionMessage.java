package demo.app.paintball.data.mqtt.messages;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017\u00a8\u0006\u001d"}, d2 = {"Ldemo/app/paintball/data/mqtt/messages/PositionMessage;", "", "()V", "player", "Ldemo/app/paintball/data/rest/models/Player;", "getPlayer", "()Ldemo/app/paintball/data/rest/models/Player;", "setPlayer", "(Ldemo/app/paintball/data/rest/models/Player;)V", "posX", "", "getPosX", "()I", "setPosX", "(I)V", "posY", "getPosY", "setPosY", "raw", "", "getRaw", "()Ljava/lang/String;", "setRaw", "(Ljava/lang/String;)V", "publish", "", "mqttService", "Ldemo/app/paintball/data/mqtt/MqttService;", "Companion", "app_debug"})
public final class PositionMessage {
    @org.jetbrains.annotations.NotNull()
    private java.lang.String raw = "";
    @org.jetbrains.annotations.NotNull()
    private demo.app.paintball.data.rest.models.Player player;
    private int posX = 0;
    private int posY = 0;
    public static final char SEPARATOR = '|';
    public static final demo.app.paintball.data.mqtt.messages.PositionMessage.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRaw() {
        return null;
    }
    
    public final void setRaw(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final demo.app.paintball.data.rest.models.Player getPlayer() {
        return null;
    }
    
    public final void setPlayer(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.rest.models.Player p0) {
    }
    
    public final int getPosX() {
        return 0;
    }
    
    public final void setPosX(int p0) {
    }
    
    public final int getPosY() {
        return 0;
    }
    
    public final void setPosY(int p0) {
    }
    
    public final void publish(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.mqtt.MqttService mqttService) {
    }
    
    public PositionMessage() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nJ\u000e\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Ldemo/app/paintball/data/mqtt/messages/PositionMessage$Companion;", "", "()V", "SEPARATOR", "", "build", "Ldemo/app/paintball/data/mqtt/messages/PositionMessage;", "player", "Ldemo/app/paintball/data/rest/models/Player;", "posX", "", "posY", "parse", "raw", "", "app_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final demo.app.paintball.data.mqtt.messages.PositionMessage parse(@org.jetbrains.annotations.NotNull()
        java.lang.String raw) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final demo.app.paintball.data.mqtt.messages.PositionMessage build(@org.jetbrains.annotations.NotNull()
        demo.app.paintball.data.rest.models.Player player, int posX, int posY) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}
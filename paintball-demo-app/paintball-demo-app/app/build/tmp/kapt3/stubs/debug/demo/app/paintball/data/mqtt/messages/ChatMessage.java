package demo.app.paintball.data.mqtt.messages;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\b\u00a8\u0006\u0016"}, d2 = {"Ldemo/app/paintball/data/mqtt/messages/ChatMessage;", "", "()V", "message", "", "getMessage", "()Ljava/lang/String;", "setMessage", "(Ljava/lang/String;)V", "playerName", "getPlayerName", "setPlayerName", "raw", "getRaw", "setRaw", "publish", "", "mqttService", "Ldemo/app/paintball/data/mqtt/MqttService;", "player", "Ldemo/app/paintball/data/rest/models/Player;", "Companion", "app_debug"})
public final class ChatMessage {
    @org.jetbrains.annotations.NotNull()
    private java.lang.String raw = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String message = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String playerName = "";
    public static final char SEPARATOR = '|';
    public static final demo.app.paintball.data.mqtt.messages.ChatMessage.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRaw() {
        return null;
    }
    
    public final void setRaw(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMessage() {
        return null;
    }
    
    public final void setMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPlayerName() {
        return null;
    }
    
    public final void setPlayerName(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final void publish(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.mqtt.MqttService mqttService, @org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.rest.models.Player player) {
    }
    
    public ChatMessage() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bJ\u000e\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Ldemo/app/paintball/data/mqtt/messages/ChatMessage$Companion;", "", "()V", "SEPARATOR", "", "build", "Ldemo/app/paintball/data/mqtt/messages/ChatMessage;", "message", "", "playerName", "parse", "raw", "app_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final demo.app.paintball.data.mqtt.messages.ChatMessage parse(@org.jetbrains.annotations.NotNull()
        java.lang.String raw) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final demo.app.paintball.data.mqtt.messages.ChatMessage build(@org.jetbrains.annotations.NotNull()
        java.lang.String message, @org.jetbrains.annotations.NotNull()
        java.lang.String playerName) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}
package demo.app.paintball.data.mqtt;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001:\u0003\u001c\u001d\u001eJ\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H&J\u0010\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H&J\u0010\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H&R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u00a6\u000e\u00a2\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u0004\u0018\u00010\tX\u00a6\u000e\u00a2\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u00a6\u000e\u00a2\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013\u00a8\u0006\u001f"}, d2 = {"Ldemo/app/paintball/data/mqtt/MqttService;", "", "chatListener", "Ldemo/app/paintball/data/mqtt/MqttService$ChatListener;", "getChatListener", "()Ldemo/app/paintball/data/mqtt/MqttService$ChatListener;", "setChatListener", "(Ldemo/app/paintball/data/mqtt/MqttService$ChatListener;)V", "gameListener", "Ldemo/app/paintball/data/mqtt/MqttService$GameListener;", "getGameListener", "()Ldemo/app/paintball/data/mqtt/MqttService$GameListener;", "setGameListener", "(Ldemo/app/paintball/data/mqtt/MqttService$GameListener;)V", "positionListener", "Ldemo/app/paintball/data/mqtt/MqttService$PositionListener;", "getPositionListener", "()Ldemo/app/paintball/data/mqtt/MqttService$PositionListener;", "setPositionListener", "(Ldemo/app/paintball/data/mqtt/MqttService$PositionListener;)V", "publish", "", "topic", "Ldemo/app/paintball/data/mqtt/Topic;", "message", "", "subscribe", "unsubscribe", "ChatListener", "GameListener", "PositionListener", "app_debug"})
public abstract interface MqttService {
    
    @org.jetbrains.annotations.Nullable()
    public abstract demo.app.paintball.data.mqtt.MqttService.GameListener getGameListener();
    
    public abstract void setGameListener(@org.jetbrains.annotations.Nullable()
    demo.app.paintball.data.mqtt.MqttService.GameListener p0);
    
    @org.jetbrains.annotations.Nullable()
    public abstract demo.app.paintball.data.mqtt.MqttService.PositionListener getPositionListener();
    
    public abstract void setPositionListener(@org.jetbrains.annotations.Nullable()
    demo.app.paintball.data.mqtt.MqttService.PositionListener p0);
    
    @org.jetbrains.annotations.Nullable()
    public abstract demo.app.paintball.data.mqtt.MqttService.ChatListener getChatListener();
    
    public abstract void setChatListener(@org.jetbrains.annotations.Nullable()
    demo.app.paintball.data.mqtt.MqttService.ChatListener p0);
    
    public abstract void subscribe(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.mqtt.Topic topic);
    
    public abstract void unsubscribe(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.mqtt.Topic topic);
    
    public abstract void publish(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.mqtt.Topic topic, @org.jetbrains.annotations.NotNull()
    java.lang.String message);
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&\u00a8\u0006\u0007"}, d2 = {"Ldemo/app/paintball/data/mqtt/MqttService$GameListener;", "", "connectComplete", "", "gameMessageArrived", "message", "Ldemo/app/paintball/data/mqtt/messages/GameMessage;", "app_debug"})
    public static abstract interface GameListener {
        
        public abstract void connectComplete();
        
        public abstract void gameMessageArrived(@org.jetbrains.annotations.NotNull()
        demo.app.paintball.data.mqtt.messages.GameMessage message);
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Ldemo/app/paintball/data/mqtt/MqttService$PositionListener;", "", "positionMessageArrived", "", "message", "Ldemo/app/paintball/data/mqtt/messages/PositionMessage;", "app_debug"})
    public static abstract interface PositionListener {
        
        public abstract void positionMessageArrived(@org.jetbrains.annotations.NotNull()
        demo.app.paintball.data.mqtt.messages.PositionMessage message);
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Ldemo/app/paintball/data/mqtt/MqttService$ChatListener;", "", "chatMessageArrived", "", "message", "Ldemo/app/paintball/data/mqtt/messages/ChatMessage;", "app_debug"})
    public static abstract interface ChatListener {
        
        public abstract void chatMessageArrived(@org.jetbrains.annotations.NotNull()
        demo.app.paintball.data.mqtt.messages.ChatMessage message);
    }
}
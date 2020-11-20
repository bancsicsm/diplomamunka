package demo.app.paintball.data.mqtt;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\u0018\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020\u001cH\u0002J\u0010\u0010#\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0010\u0010$\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0096\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0096\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0096\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a\u00a8\u0006%"}, d2 = {"Ldemo/app/paintball/data/mqtt/MqttServiceImpl;", "Ldemo/app/paintball/data/mqtt/MqttService;", "()V", "chatListener", "Ldemo/app/paintball/data/mqtt/MqttService$ChatListener;", "getChatListener", "()Ldemo/app/paintball/data/mqtt/MqttService$ChatListener;", "setChatListener", "(Ldemo/app/paintball/data/mqtt/MqttService$ChatListener;)V", "gameListener", "Ldemo/app/paintball/data/mqtt/MqttService$GameListener;", "getGameListener", "()Ldemo/app/paintball/data/mqtt/MqttService$GameListener;", "setGameListener", "(Ldemo/app/paintball/data/mqtt/MqttService$GameListener;)V", "mqttAndroidClient", "Lorg/eclipse/paho/android/service/MqttAndroidClient;", "getMqttAndroidClient", "()Lorg/eclipse/paho/android/service/MqttAndroidClient;", "setMqttAndroidClient", "(Lorg/eclipse/paho/android/service/MqttAndroidClient;)V", "positionListener", "Ldemo/app/paintball/data/mqtt/MqttService$PositionListener;", "getPositionListener", "()Ldemo/app/paintball/data/mqtt/MqttService$PositionListener;", "setPositionListener", "(Ldemo/app/paintball/data/mqtt/MqttService$PositionListener;)V", "connect", "", "publish", "topic", "Ldemo/app/paintball/data/mqtt/Topic;", "message", "", "setCallback", "subscribe", "unsubscribe", "app_debug"})
@javax.inject.Singleton()
public final class MqttServiceImpl implements demo.app.paintball.data.mqtt.MqttService {
    @org.jetbrains.annotations.Nullable()
    private demo.app.paintball.data.mqtt.MqttService.GameListener gameListener;
    @org.jetbrains.annotations.Nullable()
    private demo.app.paintball.data.mqtt.MqttService.PositionListener positionListener;
    @org.jetbrains.annotations.Nullable()
    private demo.app.paintball.data.mqtt.MqttService.ChatListener chatListener;
    @org.jetbrains.annotations.NotNull()
    private org.eclipse.paho.android.service.MqttAndroidClient mqttAndroidClient;
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public demo.app.paintball.data.mqtt.MqttService.GameListener getGameListener() {
        return null;
    }
    
    @java.lang.Override()
    public void setGameListener(@org.jetbrains.annotations.Nullable()
    demo.app.paintball.data.mqtt.MqttService.GameListener p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public demo.app.paintball.data.mqtt.MqttService.PositionListener getPositionListener() {
        return null;
    }
    
    @java.lang.Override()
    public void setPositionListener(@org.jetbrains.annotations.Nullable()
    demo.app.paintball.data.mqtt.MqttService.PositionListener p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public demo.app.paintball.data.mqtt.MqttService.ChatListener getChatListener() {
        return null;
    }
    
    @java.lang.Override()
    public void setChatListener(@org.jetbrains.annotations.Nullable()
    demo.app.paintball.data.mqtt.MqttService.ChatListener p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.eclipse.paho.android.service.MqttAndroidClient getMqttAndroidClient() {
        return null;
    }
    
    public final void setMqttAndroidClient(@org.jetbrains.annotations.NotNull()
    org.eclipse.paho.android.service.MqttAndroidClient p0) {
    }
    
    private final void setCallback() {
    }
    
    private final void connect() {
    }
    
    @java.lang.Override()
    public void subscribe(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.mqtt.Topic topic) {
    }
    
    @java.lang.Override()
    public void unsubscribe(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.mqtt.Topic topic) {
    }
    
    @java.lang.Override()
    public void publish(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.mqtt.Topic topic, @org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    @javax.inject.Inject()
    public MqttServiceImpl() {
        super();
    }
}
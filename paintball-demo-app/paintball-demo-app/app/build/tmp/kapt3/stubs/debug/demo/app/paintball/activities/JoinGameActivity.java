package demo.app.paintball.activities;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001aH\u0016J\b\u0010\u001c\u001a\u00020\u001aH\u0016J\b\u0010\u001d\u001a\u00020\u001aH\u0016J\u0010\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0016\u0010!\u001a\u00020\u001a2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00060#H\u0016J\b\u0010$\u001a\u00020\u001aH\u0002J\b\u0010%\u001a\u00020\u001aH\u0002J\b\u0010&\u001a\u00020\u001aH\u0002J\b\u0010\'\u001a\u00020\u001aH\u0016J\u0012\u0010(\u001a\u00020\u001a2\b\u0010)\u001a\u0004\u0018\u00010*H\u0014J\u0010\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0016J\u0010\u0010/\u001a\u00020,2\u0006\u00100\u001a\u000201H\u0016J\b\u00102\u001a\u00020\u001aH\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001e\u0010\r\u001a\u00020\u000e8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0013\u001a\u00020\u00148\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018\u00a8\u00063"}, d2 = {"Ldemo/app/paintball/activities/JoinGameActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Ldemo/app/paintball/data/rest/RestService$SuccessListener;", "Ldemo/app/paintball/data/mqtt/MqttService$GameListener;", "()V", "game", "Ldemo/app/paintball/data/rest/models/Game;", "mqttService", "Ldemo/app/paintball/data/mqtt/MqttService;", "getMqttService", "()Ldemo/app/paintball/data/mqtt/MqttService;", "setMqttService", "(Ldemo/app/paintball/data/mqtt/MqttService;)V", "playerService", "Ldemo/app/paintball/util/services/PlayerService;", "getPlayerService", "()Ldemo/app/paintball/util/services/PlayerService;", "setPlayerService", "(Ldemo/app/paintball/util/services/PlayerService;)V", "restService", "Ldemo/app/paintball/data/rest/RestService;", "getRestService", "()Ldemo/app/paintball/data/rest/RestService;", "setRestService", "(Ldemo/app/paintball/data/rest/RestService;)V", "addBluePlayerSuccess", "", "addRedPlayerSuccess", "connectComplete", "createGameSuccess", "gameMessageArrived", "message", "Ldemo/app/paintball/data/mqtt/messages/GameMessage;", "getGameSuccess", "response", "Lretrofit2/Response;", "initStartGameButton", "initTeamButtons", "initTexts", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "showDeleteGameAlert", "app_debug"})
public final class JoinGameActivity extends androidx.appcompat.app.AppCompatActivity implements demo.app.paintball.data.rest.RestService.SuccessListener, demo.app.paintball.data.mqtt.MqttService.GameListener {
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public demo.app.paintball.data.rest.RestService restService;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public demo.app.paintball.data.mqtt.MqttService mqttService;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public demo.app.paintball.util.services.PlayerService playerService;
    private demo.app.paintball.data.rest.models.Game game;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final demo.app.paintball.data.rest.RestService getRestService() {
        return null;
    }
    
    public final void setRestService(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.rest.RestService p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final demo.app.paintball.data.mqtt.MqttService getMqttService() {
        return null;
    }
    
    public final void setMqttService(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.mqtt.MqttService p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final demo.app.paintball.util.services.PlayerService getPlayerService() {
        return null;
    }
    
    public final void setPlayerService(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.util.services.PlayerService p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void getGameSuccess(@org.jetbrains.annotations.NotNull()
    retrofit2.Response<demo.app.paintball.data.rest.models.Game> response) {
    }
    
    private final void initTexts() {
    }
    
    private final void initStartGameButton() {
    }
    
    private final void initTeamButtons() {
    }
    
    @java.lang.Override()
    public void createGameSuccess() {
    }
    
    @java.lang.Override()
    public void addRedPlayerSuccess() {
    }
    
    @java.lang.Override()
    public void addBluePlayerSuccess() {
    }
    
    @java.lang.Override()
    public void connectComplete() {
    }
    
    @java.lang.Override()
    public void gameMessageArrived(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.mqtt.messages.GameMessage message) {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    @java.lang.Override()
    public boolean onCreateOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    private final void showDeleteGameAlert() {
    }
    
    public JoinGameActivity() {
        super();
    }
}
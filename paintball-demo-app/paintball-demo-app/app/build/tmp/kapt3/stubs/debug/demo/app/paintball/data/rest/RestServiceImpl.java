package demo.app.paintball.data.rest;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u0012H\u0016J\b\u0010\u001a\u001a\u00020\u0012H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0096.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u0096.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\u00a8\u0006\u001b"}, d2 = {"Ldemo/app/paintball/data/rest/RestServiceImpl;", "Ldemo/app/paintball/data/rest/RestService;", "()V", "errorListener", "Ldemo/app/paintball/data/rest/RestService$ErrorListener;", "getErrorListener", "()Ldemo/app/paintball/data/rest/RestService$ErrorListener;", "setErrorListener", "(Ldemo/app/paintball/data/rest/RestService$ErrorListener;)V", "gameApi", "Ldemo/app/paintball/data/rest/GameApi;", "listener", "Ldemo/app/paintball/data/rest/RestService$SuccessListener;", "getListener", "()Ldemo/app/paintball/data/rest/RestService$SuccessListener;", "setListener", "(Ldemo/app/paintball/data/rest/RestService$SuccessListener;)V", "addBluePlayer", "", "player", "Ldemo/app/paintball/data/rest/models/Player;", "addRedPlayer", "createGame", "game", "Ldemo/app/paintball/data/rest/models/Game;", "deleteGame", "getGame", "app_debug"})
@javax.inject.Singleton()
public final class RestServiceImpl implements demo.app.paintball.data.rest.RestService {
    private final demo.app.paintball.data.rest.GameApi gameApi = null;
    @org.jetbrains.annotations.NotNull()
    public demo.app.paintball.data.rest.RestService.SuccessListener listener;
    @org.jetbrains.annotations.NotNull()
    public demo.app.paintball.data.rest.RestService.ErrorListener errorListener;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public demo.app.paintball.data.rest.RestService.SuccessListener getListener() {
        return null;
    }
    
    @java.lang.Override()
    public void setListener(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.rest.RestService.SuccessListener p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public demo.app.paintball.data.rest.RestService.ErrorListener getErrorListener() {
        return null;
    }
    
    @java.lang.Override()
    public void setErrorListener(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.rest.RestService.ErrorListener p0) {
    }
    
    @java.lang.Override()
    public void getGame() {
    }
    
    @java.lang.Override()
    public void createGame(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.rest.models.Game game) {
    }
    
    @java.lang.Override()
    public void deleteGame() {
    }
    
    @java.lang.Override()
    public void addRedPlayer(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.rest.models.Player player) {
    }
    
    @java.lang.Override()
    public void addBluePlayer(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.rest.models.Player player) {
    }
    
    @javax.inject.Inject()
    public RestServiceImpl() {
        super();
    }
}
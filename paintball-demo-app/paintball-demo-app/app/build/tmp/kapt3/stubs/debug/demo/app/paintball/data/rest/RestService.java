package demo.app.paintball.data.rest;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001:\u0002\u0018\u0019J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H&J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H&J\u0010\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0015H&J\b\u0010\u0016\u001a\u00020\u000fH&J\b\u0010\u0017\u001a\u00020\u000fH&R\u0018\u0010\u0002\u001a\u00020\u0003X\u00a6\u000e\u00a2\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\u00020\tX\u00a6\u000e\u00a2\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r\u00a8\u0006\u001a"}, d2 = {"Ldemo/app/paintball/data/rest/RestService;", "", "errorListener", "Ldemo/app/paintball/data/rest/RestService$ErrorListener;", "getErrorListener", "()Ldemo/app/paintball/data/rest/RestService$ErrorListener;", "setErrorListener", "(Ldemo/app/paintball/data/rest/RestService$ErrorListener;)V", "listener", "Ldemo/app/paintball/data/rest/RestService$SuccessListener;", "getListener", "()Ldemo/app/paintball/data/rest/RestService$SuccessListener;", "setListener", "(Ldemo/app/paintball/data/rest/RestService$SuccessListener;)V", "addBluePlayer", "", "player", "Ldemo/app/paintball/data/rest/models/Player;", "addRedPlayer", "createGame", "game", "Ldemo/app/paintball/data/rest/models/Game;", "deleteGame", "getGame", "ErrorListener", "SuccessListener", "app_debug"})
public abstract interface RestService {
    
    @org.jetbrains.annotations.NotNull()
    public abstract demo.app.paintball.data.rest.RestService.SuccessListener getListener();
    
    public abstract void setListener(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.rest.RestService.SuccessListener p0);
    
    @org.jetbrains.annotations.NotNull()
    public abstract demo.app.paintball.data.rest.RestService.ErrorListener getErrorListener();
    
    public abstract void setErrorListener(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.rest.RestService.ErrorListener p0);
    
    public abstract void getGame();
    
    public abstract void createGame(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.rest.models.Game game);
    
    public abstract void deleteGame();
    
    public abstract void addRedPlayer(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.rest.models.Player player);
    
    public abstract void addBluePlayer(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.rest.models.Player player);
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\u0016\u0010\u0006\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH&\u00a8\u0006\n"}, d2 = {"Ldemo/app/paintball/data/rest/RestService$SuccessListener;", "", "addBluePlayerSuccess", "", "addRedPlayerSuccess", "createGameSuccess", "getGameSuccess", "response", "Lretrofit2/Response;", "Ldemo/app/paintball/data/rest/models/Game;", "app_debug"})
    public static abstract interface SuccessListener {
        
        public abstract void getGameSuccess(@org.jetbrains.annotations.NotNull()
        retrofit2.Response<demo.app.paintball.data.rest.models.Game> response);
        
        public abstract void createGameSuccess();
        
        public abstract void addRedPlayerSuccess();
        
        public abstract void addBluePlayerSuccess();
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Ldemo/app/paintball/data/rest/RestService$ErrorListener;", "", "handleError", "", "t", "", "app_debug"})
    public static abstract interface ErrorListener {
        
        public abstract void handleError(@org.jetbrains.annotations.NotNull()
        java.lang.Throwable t);
    }
}
package demo.app.paintball.data.rest;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000 \f2\u00020\u0001:\u0001\fJ\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H\'J\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H\'J\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0001\u0010\b\u001a\u00020\tH\'J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\'J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\u0003H\'\u00a8\u0006\r"}, d2 = {"Ldemo/app/paintball/data/rest/GameApi;", "", "addBluePlayer", "Lretrofit2/Call;", "player", "Ldemo/app/paintball/data/rest/models/Player;", "addRedPlayer", "createGame", "game", "Ldemo/app/paintball/data/rest/models/Game;", "deleteGame", "getGame", "Companion", "app_debug"})
public abstract interface GameApi {
    public static final demo.app.paintball.data.rest.GameApi.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "/api/game")
    public abstract retrofit2.Call<demo.app.paintball.data.rest.models.Game> getGame();
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "/api/game")
    public abstract retrofit2.Call<java.lang.Object> createGame(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    demo.app.paintball.data.rest.models.Game game);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.DELETE(value = "/api/game")
    public abstract retrofit2.Call<java.lang.Object> deleteGame();
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "/api/game/red")
    public abstract retrofit2.Call<java.lang.Object> addRedPlayer(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    demo.app.paintball.data.rest.models.Player player);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "/api/game/blue")
    public abstract retrofit2.Call<java.lang.Object> addBluePlayer(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    demo.app.paintball.data.rest.models.Player player);
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Ldemo/app/paintball/data/rest/GameApi$Companion;", "", "()V", "BASE_URL", "", "create", "Ldemo/app/paintball/data/rest/GameApi;", "app_debug"})
    public static final class Companion {
        private static final java.lang.String BASE_URL = null;
        
        @org.jetbrains.annotations.NotNull()
        public final demo.app.paintball.data.rest.GameApi create() {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}
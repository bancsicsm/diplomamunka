package demo.app.paintball.map.renderables.movables;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR$\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\n@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\f\"\u0004\b\u0012\u0010\u000e\u00a8\u0006\u001a"}, d2 = {"Ldemo/app/paintball/map/renderables/movables/Movable;", "Ldemo/app/paintball/map/renderables/Renderable;", "name", "", "(Ljava/lang/String;)V", "lastUpdate", "", "getName", "()Ljava/lang/String;", "posX", "", "getPosX", "()I", "setPosX", "(I)V", "value", "posY", "getPosY", "setPosY", "isVisible", "", "render", "", "canvas", "Landroid/graphics/Canvas;", "Companion", "app_debug"})
public abstract class Movable extends demo.app.paintball.map.renderables.Renderable {
    private int posX = 0;
    private int posY = 0;
    private long lastUpdate;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    public static final int size = 3;
    public static final int MAX_TIME_BETWEEN_POSITION_UPDATES = 1500;
    public static final demo.app.paintball.map.renderables.movables.Movable.Companion Companion = null;
    
    public final int getPosX() {
        return 0;
    }
    
    public final void setPosX(int p0) {
    }
    
    public final int getPosY() {
        return 0;
    }
    
    public final void setPosY(int value) {
    }
    
    @java.lang.Override()
    public void render(@org.jetbrains.annotations.NotNull()
    android.graphics.Canvas canvas) {
    }
    
    private final boolean isVisible() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getName() {
        return null;
    }
    
    public Movable(@org.jetbrains.annotations.NotNull()
    java.lang.String name) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Ldemo/app/paintball/map/renderables/movables/Movable$Companion;", "", "()V", "MAX_TIME_BETWEEN_POSITION_UPDATES", "", "size", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
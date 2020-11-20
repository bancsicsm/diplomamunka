package demo.app.paintball.map.rendering;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0005\u0018\u0000 #2\u00020\u0001:\u0001#B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0007J\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0005J\u000e\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016J\b\u0010\u0018\u001a\u00020\u0011H\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u0011H\u0016J\u001e\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0005J\u000e\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u001fJ\u0016\u0010 \u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0005J\u000e\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\u001fR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Ldemo/app/paintball/map/rendering/RenderLoop;", "Ljava/lang/Thread;", "view", "Ldemo/app/paintball/map/rendering/MapViewImpl;", "width", "", "height", "(Ldemo/app/paintball/map/rendering/MapViewImpl;II)V", "renderer", "Ldemo/app/paintball/map/rendering/Renderer;", "running", "", "getRunning", "()Z", "setRunning", "(Z)V", "addAnchor", "", "posX", "posY", "addBluePlayer", "playerName", "", "addRedPlayer", "draw", "getTime", "", "run", "setMovablePosition", "setPlayerOrientation", "degree", "", "setPlayerPosition", "zoom", "scaleFactor", "Companion", "app_debug"})
public final class RenderLoop extends java.lang.Thread {
    private final demo.app.paintball.map.rendering.Renderer renderer = null;
    private boolean running = false;
    private final demo.app.paintball.map.rendering.MapViewImpl view = null;
    private static final long FPS = 30L;
    private static final long TIME_BETWEEN_FRAMES = 33L;
    public static final demo.app.paintball.map.rendering.RenderLoop.Companion Companion = null;
    
    public final boolean getRunning() {
        return false;
    }
    
    public final void setRunning(boolean p0) {
    }
    
    private final long getTime() {
        return 0L;
    }
    
    @java.lang.Override()
    public void run() {
    }
    
    private final void draw() {
    }
    
    public final void setPlayerPosition(int posX, int posY) {
    }
    
    public final void setPlayerOrientation(float degree) {
    }
    
    public final void setMovablePosition(@org.jetbrains.annotations.NotNull()
    java.lang.String playerName, int posX, int posY) {
    }
    
    public final void addRedPlayer(@org.jetbrains.annotations.NotNull()
    java.lang.String playerName) {
    }
    
    public final void addBluePlayer(@org.jetbrains.annotations.NotNull()
    java.lang.String playerName) {
    }
    
    public final void zoom(float scaleFactor) {
    }
    
    public final void addAnchor(int posX, int posY) {
    }
    
    public RenderLoop(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.map.rendering.MapViewImpl view, int width, int height) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Ldemo/app/paintball/map/rendering/RenderLoop$Companion;", "", "()V", "FPS", "", "TIME_BETWEEN_FRAMES", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
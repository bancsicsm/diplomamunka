package demo.app.paintball.map.rendering;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0003J\u000e\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0019J\u001e\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0003J\u000e\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u001eJ\u0016\u0010\u001f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0003J\u0006\u0010 \u001a\u00020\u0010J\u000e\u0010!\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020\u001eR\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Ldemo/app/paintball/map/rendering/Renderer;", "", "width", "", "height", "(II)V", "anchors", "", "Ldemo/app/paintball/map/renderables/Anchor;", "map", "Ldemo/app/paintball/map/renderables/Map;", "movables", "Ldemo/app/paintball/map/renderables/movables/Movable;", "player", "Ldemo/app/paintball/map/renderables/Player;", "addAnchor", "", "posX", "posY", "addBluePlayer", "playerName", "", "addRedPlayer", "draw", "canvas", "Landroid/graphics/Canvas;", "setMovablePosition", "movableName", "setPlayerOrientation", "degree", "", "setPlayerPosition", "step", "zoom", "scaleFactor", "app_debug"})
public final class Renderer {
    private final java.util.List<demo.app.paintball.map.renderables.movables.Movable> movables = null;
    private final java.util.List<demo.app.paintball.map.renderables.Anchor> anchors = null;
    private final demo.app.paintball.map.renderables.Map map = null;
    private final demo.app.paintball.map.renderables.Player player = null;
    private final int width = 0;
    private final int height = 0;
    
    public final void step() {
    }
    
    public final void draw(@org.jetbrains.annotations.NotNull()
    android.graphics.Canvas canvas) {
    }
    
    public final void setPlayerPosition(int posX, int posY) {
    }
    
    public final void setPlayerOrientation(float degree) {
    }
    
    public final void setMovablePosition(@org.jetbrains.annotations.NotNull()
    java.lang.String movableName, int posX, int posY) {
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
    
    public Renderer(int width, int height) {
        super();
    }
}
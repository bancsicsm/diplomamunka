package demo.app.paintball.map.renderables;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u000e\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0018\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006X\u0094\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\u0015"}, d2 = {"Ldemo/app/paintball/map/renderables/Map;", "Ldemo/app/paintball/map/renderables/Renderable;", "()V", "bitmapDrawable", "Landroid/graphics/drawable/BitmapDrawable;", "image", "Landroid/graphics/Bitmap;", "getImage", "()Landroid/graphics/Bitmap;", "render", "", "canvas", "Landroid/graphics/Canvas;", "scale", "scaleFactor", "", "setSize", "x", "", "y", "Companion", "app_debug"})
public final class Map extends demo.app.paintball.map.renderables.Renderable {
    @org.jetbrains.annotations.NotNull()
    private final android.graphics.Bitmap image = null;
    private android.graphics.drawable.BitmapDrawable bitmapDrawable;
    private static final double MIN_ZOOM = 0.0;
    private static final double MAX_ZOOM = 0.0;
    public static final int MAX_SCALE_FACTOR = 2;
    private static double zoom;
    public static final demo.app.paintball.map.renderables.Map.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected android.graphics.Bitmap getImage() {
        return null;
    }
    
    @java.lang.Override()
    public void setSize(int x, int y) {
    }
    
    @java.lang.Override()
    public void render(@org.jetbrains.annotations.NotNull()
    android.graphics.Canvas canvas) {
    }
    
    public final void scale(float scaleFactor) {
    }
    
    public Map() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\u000e\u00a8\u0006\u000f"}, d2 = {"Ldemo/app/paintball/map/renderables/Map$Companion;", "", "()V", "MAX_SCALE_FACTOR", "", "MAX_ZOOM", "", "getMAX_ZOOM", "()D", "MIN_ZOOM", "getMIN_ZOOM", "zoom", "getZoom", "setZoom", "(D)V", "app_debug"})
    public static final class Companion {
        
        public final double getMIN_ZOOM() {
            return 0.0;
        }
        
        public final double getMAX_ZOOM() {
            return 0.0;
        }
        
        public final double getZoom() {
            return 0.0;
        }
        
        public final void setZoom(double p0) {
        }
        
        private Companion() {
            super();
        }
    }
}
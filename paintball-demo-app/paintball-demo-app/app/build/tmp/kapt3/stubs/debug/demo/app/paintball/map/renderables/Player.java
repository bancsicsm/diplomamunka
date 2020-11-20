package demo.app.paintball.map.renderables;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u000e\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\nJ\u0018\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0094\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Ldemo/app/paintball/map/renderables/Player;", "Ldemo/app/paintball/map/renderables/Renderable;", "()V", "image", "Landroid/graphics/Bitmap;", "getImage", "()Landroid/graphics/Bitmap;", "matrix", "Landroid/graphics/Matrix;", "screenCenterX", "", "screenCenterY", "render", "", "canvas", "Landroid/graphics/Canvas;", "setOrientation", "degree", "setSize", "x", "", "y", "Companion", "app_debug"})
public final class Player extends demo.app.paintball.map.renderables.Renderable {
    private float screenCenterX = 0.0F;
    private float screenCenterY = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private final android.graphics.Bitmap image = null;
    private final android.graphics.Matrix matrix = null;
    public static final int size = 3;
    public static final float phoneOrientation = 90.0F;
    private static int posX;
    private static int posY;
    public static final demo.app.paintball.map.renderables.Player.Companion Companion = null;
    
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
    
    public final void setOrientation(float degree) {
    }
    
    public Player() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u000e\u0010\u000e\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Ldemo/app/paintball/map/renderables/Player$Companion;", "", "()V", "phoneOrientation", "", "posX", "", "getPosX", "()I", "setPosX", "(I)V", "posY", "getPosY", "setPosY", "size", "app_debug"})
    public static final class Companion {
        
        public final int getPosX() {
            return 0;
        }
        
        public final void setPosX(int p0) {
        }
        
        public final int getPosY() {
            return 0;
        }
        
        public final void setPosY(int p0) {
        }
        
        private Companion() {
            super();
        }
    }
}
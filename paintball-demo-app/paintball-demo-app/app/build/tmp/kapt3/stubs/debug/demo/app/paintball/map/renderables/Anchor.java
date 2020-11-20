package demo.app.paintball.map.renderables;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u0014\u0010\u0006\u001a\u00020\u0007X\u0094\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f\u00a8\u0006\u0013"}, d2 = {"Ldemo/app/paintball/map/renderables/Anchor;", "Ldemo/app/paintball/map/renderables/Renderable;", "posX", "", "posY", "(II)V", "image", "Landroid/graphics/Bitmap;", "getImage", "()Landroid/graphics/Bitmap;", "<set-?>", "getPosX", "()I", "getPosY", "render", "", "canvas", "Landroid/graphics/Canvas;", "Companion", "app_debug"})
public final class Anchor extends demo.app.paintball.map.renderables.Renderable {
    private int posX;
    private int posY;
    @org.jetbrains.annotations.NotNull()
    private final android.graphics.Bitmap image = null;
    public static final int size = 2;
    public static final demo.app.paintball.map.renderables.Anchor.Companion Companion = null;
    
    public final int getPosX() {
        return 0;
    }
    
    public final int getPosY() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected android.graphics.Bitmap getImage() {
        return null;
    }
    
    @java.lang.Override()
    public void render(@org.jetbrains.annotations.NotNull()
    android.graphics.Canvas canvas) {
    }
    
    public Anchor(int posX, int posY) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Ldemo/app/paintball/map/renderables/Anchor$Companion;", "", "()V", "size", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
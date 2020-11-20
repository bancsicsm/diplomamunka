package demo.app.paintball.map.renderables;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H&J\u0018\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\bH\u0016J\b\u0010\u0017\u001a\u00020\u0011H\u0016R\u0012\u0010\u0003\u001a\u00020\u0004X\u00a4\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\bX\u0084\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\bX\u0084\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\f\u00a8\u0006\u0018"}, d2 = {"Ldemo/app/paintball/map/renderables/Renderable;", "", "()V", "image", "Landroid/graphics/Bitmap;", "getImage", "()Landroid/graphics/Bitmap;", "screenHeight", "", "getScreenHeight", "()I", "setScreenHeight", "(I)V", "screenWidth", "getScreenWidth", "setScreenWidth", "render", "", "canvas", "Landroid/graphics/Canvas;", "setSize", "x", "y", "step", "app_debug"})
public abstract class Renderable {
    private int screenWidth = 0;
    private int screenHeight = 0;
    
    protected final int getScreenWidth() {
        return 0;
    }
    
    protected final void setScreenWidth(int p0) {
    }
    
    protected final int getScreenHeight() {
        return 0;
    }
    
    protected final void setScreenHeight(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    protected abstract android.graphics.Bitmap getImage();
    
    public void setSize(int x, int y) {
    }
    
    public void step() {
    }
    
    public abstract void render(@org.jetbrains.annotations.NotNull()
    android.graphics.Canvas canvas);
    
    public Renderable() {
        super();
    }
}
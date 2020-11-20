package demo.app.paintball.map;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J \u0010\u000b\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH&J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0011H&J\u0018\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0011H&\u00a8\u0006\u0015"}, d2 = {"Ldemo/app/paintball/map/MapView;", "", "addAnchor", "", "posX", "", "posY", "addBluePlayer", "playerName", "", "addRedPlayer", "setMovablePosition", "setOnTouchListener", "listener", "Landroid/view/View$OnTouchListener;", "setPlayerOrientation", "degree", "", "setPlayerPosition", "zoom", "scaleFactor", "app_debug"})
public abstract interface MapView {
    
    public abstract void setPlayerPosition(int posX, int posY);
    
    public abstract void setPlayerOrientation(float degree);
    
    public abstract void setMovablePosition(@org.jetbrains.annotations.NotNull()
    java.lang.String playerName, int posX, int posY);
    
    public abstract void addRedPlayer(@org.jetbrains.annotations.NotNull()
    java.lang.String playerName);
    
    public abstract void addBluePlayer(@org.jetbrains.annotations.NotNull()
    java.lang.String playerName);
    
    public abstract void zoom(float scaleFactor);
    
    public abstract void addAnchor(int posX, int posY);
    
    public abstract void setOnTouchListener(@org.jetbrains.annotations.NotNull()
    android.view.View.OnTouchListener listener);
}
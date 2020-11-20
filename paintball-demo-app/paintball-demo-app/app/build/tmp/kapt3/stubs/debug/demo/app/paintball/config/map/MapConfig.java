package demo.app.paintball.config.map;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0010\u0015\n\u0002\b\t\n\u0002\u0010\u0006\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\u0004X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u0012\u0010\t\u001a\u00020\u0004X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u0006R\u0018\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0012\u0010\u0010\u001a\u00020\u0004X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u0006R\u0012\u0010\u0012\u001a\u00020\u0004X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0006R\u0012\u0010\u0014\u001a\u00020\u0004X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0006R\u0012\u0010\u0016\u001a\u00020\u0017X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0012\u0010\u001a\u001a\u00020\u0017X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001b\u0010\u0019\u00a8\u0006\u001c"}, d2 = {"Ldemo/app/paintball/config/map/MapConfig;", "", "()V", "anchorOriginPosX", "", "getAnchorOriginPosX", "()I", "anchorOriginPosY", "getAnchorOriginPosY", "anchorOriginPxX", "getAnchorOriginPxX", "anchors", "", "", "getAnchors", "()Ljava/util/List;", "imageDrawableId", "getImageDrawableId", "imageWidthPx", "getImageWidthPx", "mapOrientation", "getMapOrientation", "maxZoom", "", "getMaxZoom", "()D", "minZoom", "getMinZoom", "app_debug"})
public abstract class MapConfig {
    
    public abstract int getImageDrawableId();
    
    public abstract int getImageWidthPx();
    
    public abstract int getMapOrientation();
    
    public abstract double getMinZoom();
    
    public abstract double getMaxZoom();
    
    public abstract int getAnchorOriginPosX();
    
    public abstract int getAnchorOriginPosY();
    
    public abstract int getAnchorOriginPxX();
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<int[]> getAnchors();
    
    public MapConfig() {
        super();
    }
}
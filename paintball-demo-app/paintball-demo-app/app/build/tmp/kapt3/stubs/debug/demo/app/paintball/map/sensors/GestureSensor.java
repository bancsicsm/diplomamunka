package demo.app.paintball.map.sensors;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 %2\u00020\u00012\u00020\u00022\u00020\u0003:\u0002%&B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0012\u0010\u0012\u001a\u00020\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J,\u0010\u0015\u001a\u00020\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\fH\u0016J\u0012\u0010\u0019\u001a\u00020\u001a2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u0010\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u000eH\u0016J\u0010\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u000eH\u0016J\u0010\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u000eH\u0016J\u0012\u0010\u001f\u001a\u00020\u001a2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u0012\u0010 \u001a\u00020\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u0018\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0014H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\'"}, d2 = {"Ldemo/app/paintball/map/sensors/GestureSensor;", "Landroid/view/View$OnTouchListener;", "Landroid/view/ScaleGestureDetector$OnScaleGestureListener;", "Landroid/view/GestureDetector$SimpleOnGestureListener;", "gestureListener", "Ldemo/app/paintball/map/sensors/GestureSensor$GestureListener;", "(Ldemo/app/paintball/map/sensors/GestureSensor$GestureListener;)V", "gestureDetector", "Landroid/view/GestureDetector;", "getGestureListener", "()Ldemo/app/paintball/map/sensors/GestureSensor$GestureListener;", "lastScale", "", "scaleDetector", "Landroid/view/ScaleGestureDetector;", "scaleFactor", "up", "", "onDown", "p0", "Landroid/view/MotionEvent;", "onFling", "p1", "p2", "p3", "onLongPress", "", "onScale", "detector", "onScaleBegin", "onScaleEnd", "onShowPress", "onSingleTapUp", "onTouch", "view", "Landroid/view/View;", "event", "Companion", "GestureListener", "app_debug"})
public final class GestureSensor extends android.view.GestureDetector.SimpleOnGestureListener implements android.view.View.OnTouchListener, android.view.ScaleGestureDetector.OnScaleGestureListener {
    private final android.view.ScaleGestureDetector scaleDetector = null;
    private final android.view.GestureDetector gestureDetector = null;
    private float scaleFactor = 1.0F;
    private float lastScale = 1.0F;
    private boolean up = false;
    @org.jetbrains.annotations.NotNull()
    private final demo.app.paintball.map.sensors.GestureSensor.GestureListener gestureListener = null;
    public static final float zoomLimit = 0.2F;
    public static final float scrollLimit = 40.0F;
    public static final demo.app.paintball.map.sensors.GestureSensor.Companion Companion = null;
    
    @java.lang.Override()
    public boolean onTouch(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.NotNull()
    android.view.MotionEvent event) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onScale(@org.jetbrains.annotations.NotNull()
    android.view.ScaleGestureDetector detector) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onScaleBegin(@org.jetbrains.annotations.NotNull()
    android.view.ScaleGestureDetector detector) {
        return false;
    }
    
    @java.lang.Override()
    public void onScaleEnd(@org.jetbrains.annotations.NotNull()
    android.view.ScaleGestureDetector detector) {
    }
    
    @java.lang.Override()
    public boolean onDown(@org.jetbrains.annotations.Nullable()
    android.view.MotionEvent p0) {
        return false;
    }
    
    @java.lang.Override()
    public void onShowPress(@org.jetbrains.annotations.Nullable()
    android.view.MotionEvent p0) {
    }
    
    @java.lang.Override()
    public boolean onSingleTapUp(@org.jetbrains.annotations.Nullable()
    android.view.MotionEvent p0) {
        return false;
    }
    
    @java.lang.Override()
    public void onLongPress(@org.jetbrains.annotations.Nullable()
    android.view.MotionEvent p0) {
    }
    
    @java.lang.Override()
    public boolean onFling(@org.jetbrains.annotations.Nullable()
    android.view.MotionEvent p0, @org.jetbrains.annotations.Nullable()
    android.view.MotionEvent p1, float p2, float p3) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final demo.app.paintball.map.sensors.GestureSensor.GestureListener getGestureListener() {
        return null;
    }
    
    public GestureSensor(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.map.sensors.GestureSensor.GestureListener gestureListener) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0003H&\u00a8\u0006\b"}, d2 = {"Ldemo/app/paintball/map/sensors/GestureSensor$GestureListener;", "", "onScaleChanged", "", "scaleFactor", "", "onZoomIn", "onZoomOut", "app_debug"})
    public static abstract interface GestureListener {
        
        public abstract void onScaleChanged(float scaleFactor);
        
        public abstract void onZoomIn();
        
        public abstract void onZoomOut();
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Ldemo/app/paintball/map/sensors/GestureSensor$Companion;", "", "()V", "scrollLimit", "", "zoomLimit", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
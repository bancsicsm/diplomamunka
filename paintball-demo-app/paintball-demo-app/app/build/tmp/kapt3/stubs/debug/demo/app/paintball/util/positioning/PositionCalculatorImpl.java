package demo.app.paintball.util.positioning;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\b\u001a\u00020\tH\u0016J\b\u0010\u001d\u001a\u00020\u001cH\u0002J\b\u0010\u001e\u001a\u00020\u001cH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u0010X\u0096.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0015\u001a\n \u0017*\u0004\u0018\u00010\u00160\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0018\u001a\n \u0017*\u0004\u0018\u00010\u00160\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Ldemo/app/paintball/util/positioning/PositionCalculatorImpl;", "Ldemo/app/paintball/util/positioning/PositionCalculator;", "anchors", "", "", "(Ljava/util/List;)V", "anchorCombinationNumber", "", "data", "Ldemo/app/paintball/data/ble/data/BlePositionData;", "handler", "Landroid/os/Handler;", "lastResultsX", "Ljava/util/Deque;", "lastResultsY", "listener", "Ldemo/app/paintball/util/positioning/PositionCalculator$PositionCalculatorListener;", "getListener", "()Ldemo/app/paintball/util/positioning/PositionCalculator$PositionCalculatorListener;", "setListener", "(Ldemo/app/paintball/util/positioning/PositionCalculator$PositionCalculatorListener;)V", "qPrev", "Lorg/apache/commons/math3/linear/RealMatrix;", "kotlin.jvm.PlatformType", "qa", "runnable", "Ljava/lang/Runnable;", "calculate", "", "generateQa", "initQPrev", "Companion", "app_debug"})
public final class PositionCalculatorImpl implements demo.app.paintball.util.positioning.PositionCalculator {
    @org.jetbrains.annotations.NotNull()
    public demo.app.paintball.util.positioning.PositionCalculator.PositionCalculatorListener listener;
    private demo.app.paintball.data.ble.data.BlePositionData data;
    private final int anchorCombinationNumber = 0;
    private final org.apache.commons.math3.linear.RealMatrix qa = null;
    private org.apache.commons.math3.linear.RealMatrix qPrev;
    private final java.util.Deque<java.lang.Integer> lastResultsX = null;
    private final java.util.Deque<java.lang.Integer> lastResultsY = null;
    private final android.os.Handler handler = null;
    private final java.lang.Runnable runnable = null;
    private final java.util.List<int[]> anchors = null;
    public static final int MAXITER = 30;
    public static final double ERROR = 100.0;
    public static final double ZT = 1100.0;
    public static final int WINDOW_SIZE = 12;
    public static final demo.app.paintball.util.positioning.PositionCalculatorImpl.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public demo.app.paintball.util.positioning.PositionCalculator.PositionCalculatorListener getListener() {
        return null;
    }
    
    @java.lang.Override()
    public void setListener(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.util.positioning.PositionCalculator.PositionCalculatorListener p0) {
    }
    
    private final void generateQa() {
    }
    
    private final void initQPrev() {
    }
    
    @java.lang.Override()
    public void calculate(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.ble.data.BlePositionData data) {
    }
    
    public PositionCalculatorImpl(@org.jetbrains.annotations.NotNull()
    java.util.List<int[]> anchors) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Ldemo/app/paintball/util/positioning/PositionCalculatorImpl$Companion;", "", "()V", "ERROR", "", "MAXITER", "", "WINDOW_SIZE", "ZT", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
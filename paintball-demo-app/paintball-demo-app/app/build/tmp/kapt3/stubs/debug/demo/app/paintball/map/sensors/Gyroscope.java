package demo.app.paintball.map.sensors;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 !2\u00020\u0001:\u0002!\"B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0006\u0010\u001f\u001a\u00020\u0018J\u0006\u0010 \u001a\u00020\u0018R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Ldemo/app/paintball/map/sensors/Gyroscope;", "Landroid/hardware/SensorEventListener;", "gyroscopeListener", "Ldemo/app/paintball/map/sensors/Gyroscope$GyroscopeListener;", "(Ldemo/app/paintball/map/sensors/Gyroscope$GyroscopeListener;)V", "accelerometer", "Landroid/hardware/Sensor;", "kotlin.jvm.PlatformType", "getGyroscopeListener", "()Ldemo/app/paintball/map/sensors/Gyroscope$GyroscopeListener;", "lastAccelerometerSet", "", "lastAccelerometerValue", "", "lastMagnetometerSet", "lastMagnetometerValue", "lastOrient", "", "magnetometer", "orientation", "rotation", "sensorManager", "Landroid/hardware/SensorManager;", "onAccuracyChanged", "", "sensor", "accuracy", "", "onSensorChanged", "event", "Landroid/hardware/SensorEvent;", "start", "stop", "Companion", "GyroscopeListener", "app_debug"})
public final class Gyroscope implements android.hardware.SensorEventListener {
    private final android.hardware.SensorManager sensorManager = null;
    private final android.hardware.Sensor accelerometer = null;
    private final android.hardware.Sensor magnetometer = null;
    private final float[] lastAccelerometerValue = null;
    private final float[] lastMagnetometerValue = null;
    private boolean lastAccelerometerSet = false;
    private boolean lastMagnetometerSet = false;
    private final float[] rotation = null;
    private final float[] orientation = null;
    private float lastOrient = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private final demo.app.paintball.map.sensors.Gyroscope.GyroscopeListener gyroscopeListener = null;
    public static final float orientChangedThreshold = 0.16F;
    public static final demo.app.paintball.map.sensors.Gyroscope.Companion Companion = null;
    
    public final void start() {
    }
    
    public final void stop() {
    }
    
    @java.lang.Override()
    public void onSensorChanged(@org.jetbrains.annotations.NotNull()
    android.hardware.SensorEvent event) {
    }
    
    @java.lang.Override()
    public void onAccuracyChanged(@org.jetbrains.annotations.NotNull()
    android.hardware.Sensor sensor, int accuracy) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final demo.app.paintball.map.sensors.Gyroscope.GyroscopeListener getGyroscopeListener() {
        return null;
    }
    
    public Gyroscope(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.map.sensors.Gyroscope.GyroscopeListener gyroscopeListener) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Ldemo/app/paintball/map/sensors/Gyroscope$GyroscopeListener;", "", "onOrientationChanged", "", "radian", "", "app_debug"})
    public static abstract interface GyroscopeListener {
        
        public abstract void onOrientationChanged(float radian);
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Ldemo/app/paintball/map/sensors/Gyroscope$Companion;", "", "()V", "orientChangedThreshold", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
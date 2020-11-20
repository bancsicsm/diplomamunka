package demo.app.paintball.data.ble;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000b\b\u0007\u0018\u0000 ,2\u00020\u00012\u00020\u00022\u00020\u0003:\u0003*+,B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\fH\u0016J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0017H\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0014J\u0010\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u0019H\u0016J\u0010\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u0019H\u0016J\u0018\u0010 \u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u00192\u0006\u0010!\u001a\u00020\"H\u0016J\u0010\u0010#\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u0019H\u0016J\u0018\u0010$\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u00192\u0006\u0010!\u001a\u00020\"H\u0016J\u0010\u0010%\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u0019H\u0016J\u0010\u0010&\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\fH\u0016J\b\u0010\'\u001a\u00020\u0017H\u0016J\b\u0010(\u001a\u00020\u0017H\u0016J\b\u0010)\u001a\u00020\u0017H\u0002R\u001e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@RX\u0096\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006-"}, d2 = {"Ldemo/app/paintball/data/ble/BleServiceImpl;", "Ldemo/app/paintball/data/ble/BleService;", "Lno/nordicsemi/android/ble/BleManager;", "Lno/nordicsemi/android/ble/observer/ConnectionObserver;", "()V", "<set-?>", "Ldemo/app/paintball/data/ble/data/BleNetworkInfo;", "bleNetworkInfo", "getBleNetworkInfo", "()Ldemo/app/paintball/data/ble/data/BleNetworkInfo;", "bleServiceListeners", "Ljava/util/LinkedList;", "Ldemo/app/paintball/data/ble/BleServiceImpl$BleServiceListener;", "infoCharacteristic", "Landroid/bluetooth/BluetoothGattCharacteristic;", "modeCharacteristic", "rangingCharacteristic", "rangingService", "Landroid/bluetooth/BluetoothGattService;", "addListener", "", "listener", "connectDevice", "", "tag", "Landroid/bluetooth/BluetoothDevice;", "disconnectDevice", "getGattCallback", "Lno/nordicsemi/android/ble/BleManager$BleManagerGattCallback;", "onDeviceConnected", "device", "onDeviceConnecting", "onDeviceDisconnected", "reason", "", "onDeviceDisconnecting", "onDeviceFailedToConnect", "onDeviceReady", "removeListener", "startPositionSending", "stopPositionSending", "updateBleNetworkInfo", "BleGattCallback", "BleServiceListener", "Companion", "app_debug"})
@javax.inject.Singleton()
public final class BleServiceImpl extends no.nordicsemi.android.ble.BleManager implements demo.app.paintball.data.ble.BleService, no.nordicsemi.android.ble.observer.ConnectionObserver {
    private android.bluetooth.BluetoothGattService rangingService;
    private android.bluetooth.BluetoothGattCharacteristic infoCharacteristic;
    private android.bluetooth.BluetoothGattCharacteristic rangingCharacteristic;
    private android.bluetooth.BluetoothGattCharacteristic modeCharacteristic;
    private java.util.LinkedList<demo.app.paintball.data.ble.BleServiceImpl.BleServiceListener> bleServiceListeners;
    @org.jetbrains.annotations.NotNull()
    private demo.app.paintball.data.ble.data.BleNetworkInfo bleNetworkInfo;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.UUID GATT_RANGING_SERVICE_UUID = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.UUID GATT_RANGING_SERVICE_INFO_UUID = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.UUID GATT_RANGING_SERVICE_RANGING_UUID = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.UUID GATT_RANGING_SERVICE_MODE_UUID = null;
    public static final byte TAG_RANGING = (byte)1;
    public static final byte TAG_POWERDOWN = (byte)0;
    public static final demo.app.paintball.data.ble.BleServiceImpl.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public demo.app.paintball.data.ble.data.BleNetworkInfo getBleNetworkInfo() {
        return null;
    }
    
    @java.lang.Override()
    public boolean addListener(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.ble.BleServiceImpl.BleServiceListener listener) {
        return false;
    }
    
    @java.lang.Override()
    public boolean removeListener(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.ble.BleServiceImpl.BleServiceListener listener) {
        return false;
    }
    
    @java.lang.Override()
    public void connectDevice(@org.jetbrains.annotations.NotNull()
    android.bluetooth.BluetoothDevice tag) {
    }
    
    @java.lang.Override()
    public void disconnectDevice() {
    }
    
    @java.lang.Override()
    public void startPositionSending() {
    }
    
    @java.lang.Override()
    public void stopPositionSending() {
    }
    
    private final void updateBleNetworkInfo() {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected no.nordicsemi.android.ble.BleManager.BleManagerGattCallback getGattCallback() {
        return null;
    }
    
    @java.lang.Override()
    public void onDeviceFailedToConnect(@org.jetbrains.annotations.NotNull()
    android.bluetooth.BluetoothDevice device, int reason) {
    }
    
    @java.lang.Override()
    public void onDeviceReady(@org.jetbrains.annotations.NotNull()
    android.bluetooth.BluetoothDevice device) {
    }
    
    @java.lang.Override()
    public void onDeviceDisconnected(@org.jetbrains.annotations.NotNull()
    android.bluetooth.BluetoothDevice device, int reason) {
    }
    
    @java.lang.Override()
    public void onDeviceConnecting(@org.jetbrains.annotations.NotNull()
    android.bluetooth.BluetoothDevice device) {
    }
    
    @java.lang.Override()
    public void onDeviceConnected(@org.jetbrains.annotations.NotNull()
    android.bluetooth.BluetoothDevice device) {
    }
    
    @java.lang.Override()
    public void onDeviceDisconnecting(@org.jetbrains.annotations.NotNull()
    android.bluetooth.BluetoothDevice device) {
    }
    
    @javax.inject.Inject()
    public BleServiceImpl() {
        super(null);
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0014J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0014J\b\u0010\t\u001a\u00020\u0004H\u0014\u00a8\u0006\n"}, d2 = {"Ldemo/app/paintball/data/ble/BleServiceImpl$BleGattCallback;", "Lno/nordicsemi/android/ble/BleManager$BleManagerGattCallback;", "(Ldemo/app/paintball/data/ble/BleServiceImpl;)V", "initialize", "", "isRequiredServiceSupported", "", "gatt", "Landroid/bluetooth/BluetoothGatt;", "onDeviceDisconnected", "app_debug"})
    final class BleGattCallback extends no.nordicsemi.android.ble.BleManager.BleManagerGattCallback {
        
        @java.lang.Override()
        protected boolean isRequiredServiceSupported(@org.jetbrains.annotations.NotNull()
        android.bluetooth.BluetoothGatt gatt) {
            return false;
        }
        
        @java.lang.Override()
        protected void initialize() {
        }
        
        @java.lang.Override()
        protected void onDeviceDisconnected() {
        }
        
        public BleGattCallback() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH&\u00a8\u0006\n"}, d2 = {"Ldemo/app/paintball/data/ble/BleServiceImpl$BleServiceListener;", "", "onBleConnected", "", "connection", "Ldemo/app/paintball/data/ble/BleService;", "onBleDisconnected", "onBlePositionDataReceived", "data", "Ldemo/app/paintball/data/ble/data/BlePositionData;", "app_debug"})
    public static abstract interface BleServiceListener {
        
        public abstract void onBleConnected(@org.jetbrains.annotations.NotNull()
        demo.app.paintball.data.ble.BleService connection);
        
        public abstract void onBlePositionDataReceived(@org.jetbrains.annotations.NotNull()
        demo.app.paintball.data.ble.BleService connection, @org.jetbrains.annotations.NotNull()
        demo.app.paintball.data.ble.data.BlePositionData data);
        
        public abstract void onBleDisconnected(@org.jetbrains.annotations.NotNull()
        demo.app.paintball.data.ble.BleService connection);
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0005\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u000e\u0010\r\u001a\u00020\u000eX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Ldemo/app/paintball/data/ble/BleServiceImpl$Companion;", "", "()V", "GATT_RANGING_SERVICE_INFO_UUID", "Ljava/util/UUID;", "getGATT_RANGING_SERVICE_INFO_UUID", "()Ljava/util/UUID;", "GATT_RANGING_SERVICE_MODE_UUID", "getGATT_RANGING_SERVICE_MODE_UUID", "GATT_RANGING_SERVICE_RANGING_UUID", "getGATT_RANGING_SERVICE_RANGING_UUID", "GATT_RANGING_SERVICE_UUID", "getGATT_RANGING_SERVICE_UUID", "TAG_POWERDOWN", "", "TAG_RANGING", "app_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.UUID getGATT_RANGING_SERVICE_UUID() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.UUID getGATT_RANGING_SERVICE_INFO_UUID() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.UUID getGATT_RANGING_SERVICE_RANGING_UUID() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.UUID getGATT_RANGING_SERVICE_MODE_UUID() {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}
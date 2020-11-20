package demo.app.paintball.data.ble;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&J\b\u0010\u000e\u001a\u00020\u000bH&J\u0010\u0010\u000f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\b\u0010\u0010\u001a\u00020\u000bH&J\b\u0010\u0011\u001a\u00020\u000bH&R\u0012\u0010\u0002\u001a\u00020\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\u00a8\u0006\u0012"}, d2 = {"Ldemo/app/paintball/data/ble/BleService;", "", "bleNetworkInfo", "Ldemo/app/paintball/data/ble/data/BleNetworkInfo;", "getBleNetworkInfo", "()Ldemo/app/paintball/data/ble/data/BleNetworkInfo;", "addListener", "", "listener", "Ldemo/app/paintball/data/ble/BleServiceImpl$BleServiceListener;", "connectDevice", "", "tag", "Landroid/bluetooth/BluetoothDevice;", "disconnectDevice", "removeListener", "startPositionSending", "stopPositionSending", "app_debug"})
public abstract interface BleService {
    
    @org.jetbrains.annotations.NotNull()
    public abstract demo.app.paintball.data.ble.data.BleNetworkInfo getBleNetworkInfo();
    
    public abstract boolean addListener(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.ble.BleServiceImpl.BleServiceListener listener);
    
    public abstract boolean removeListener(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.ble.BleServiceImpl.BleServiceListener listener);
    
    public abstract void connectDevice(@org.jetbrains.annotations.NotNull()
    android.bluetooth.BluetoothDevice tag);
    
    public abstract void disconnectDevice();
    
    public abstract void startPositionSending();
    
    public abstract void stopPositionSending();
}
package demo.app.paintball.fragments.dialogs;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 /2\u00020\u00012\u00020\u0002:\u0002/0B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u0007H\u0016J\u0010\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u0007H\u0016J\u0018\u0010\u001f\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010 \u001a\u00020!H\u0016J&\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\'2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\b\u0010*\u001a\u00020\u0019H\u0016J\u001a\u0010+\u001a\u00020\u00192\u0006\u0010,\u001a\u00020#2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\b\u0010-\u001a\u00020\u0019H\u0002J\b\u0010.\u001a\u00020\u0019H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00061"}, d2 = {"Ldemo/app/paintball/fragments/dialogs/ConnectTagFragment;", "Landroidx/fragment/app/DialogFragment;", "Ldemo/app/paintball/data/ble/BleServiceImpl$BleServiceListener;", "()V", "bleScanner", "Landroid/bluetooth/le/BluetoothLeScanner;", "bleService", "Ldemo/app/paintball/data/ble/BleService;", "getBleService", "()Ldemo/app/paintball/data/ble/BleService;", "setBleService", "(Ldemo/app/paintball/data/ble/BleService;)V", "foundTags", "Ljava/util/HashSet;", "Landroid/bluetooth/BluetoothDevice;", "listAdapter", "Landroid/widget/ArrayAdapter;", "", "listener", "Ldemo/app/paintball/fragments/dialogs/ConnectTagFragment$ConnectTagListener;", "scanning", "Landroid/bluetooth/le/ScanCallback;", "timer", "Ljava/util/Timer;", "onAttach", "", "context", "Landroid/content/Context;", "onBleConnected", "connection", "onBleDisconnected", "onBlePositionDataReceived", "data", "Ldemo/app/paintball/data/ble/data/BlePositionData;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDetach", "onViewCreated", "view", "scanTags", "stopScan", "Companion", "ConnectTagListener", "app_debug"})
public final class ConnectTagFragment extends androidx.fragment.app.DialogFragment implements demo.app.paintball.data.ble.BleServiceImpl.BleServiceListener {
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public demo.app.paintball.data.ble.BleService bleService;
    private demo.app.paintball.fragments.dialogs.ConnectTagFragment.ConnectTagListener listener;
    private android.widget.ArrayAdapter<java.lang.String> listAdapter;
    private android.bluetooth.le.BluetoothLeScanner bleScanner;
    private final java.util.Timer timer = null;
    private java.util.HashSet<android.bluetooth.BluetoothDevice> foundTags;
    private final android.bluetooth.le.ScanCallback scanning = null;
    public static final long BLE_SCAN_PERIOD = 500L;
    public static final demo.app.paintball.fragments.dialogs.ConnectTagFragment.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final demo.app.paintball.data.ble.BleService getBleService() {
        return null;
    }
    
    public final void setBleService(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.ble.BleService p0) {
    }
    
    @java.lang.Override()
    public void onAttach(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void scanTags() {
    }
    
    private final void stopScan() {
    }
    
    @java.lang.Override()
    public void onBleConnected(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.ble.BleService connection) {
    }
    
    @java.lang.Override()
    public void onBlePositionDataReceived(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.ble.BleService connection, @org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.ble.data.BlePositionData data) {
    }
    
    @java.lang.Override()
    public void onBleDisconnected(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.ble.BleService connection) {
    }
    
    @java.lang.Override()
    public void onDetach() {
    }
    
    public ConnectTagFragment() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&\u00a8\u0006\u0004"}, d2 = {"Ldemo/app/paintball/fragments/dialogs/ConnectTagFragment$ConnectTagListener;", "", "onTagConnected", "", "app_debug"})
    public static abstract interface ConnectTagListener {
        
        public abstract void onTagConnected();
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Ldemo/app/paintball/fragments/dialogs/ConnectTagFragment$Companion;", "", "()V", "BLE_SCAN_PERIOD", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
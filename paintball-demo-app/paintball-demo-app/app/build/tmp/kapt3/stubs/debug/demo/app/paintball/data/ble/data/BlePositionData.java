package demo.app.paintball.data.ble.data;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\n\n\u0000\n\u0002\u0010\u0017\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\t\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0011"}, d2 = {"Ldemo/app/paintball/data/ble/data/BlePositionData;", "", "ts", "", "ranges", "", "(S[S)V", "<set-?>", "", "createdTs", "getCreatedTs", "()J", "getRanges", "()[S", "getTs", "()S", "Companion", "app_debug"})
public final class BlePositionData {
    private short ts;
    @org.jetbrains.annotations.NotNull()
    private short[] ranges;
    private long createdTs;
    public static final demo.app.paintball.data.ble.data.BlePositionData.Companion Companion = null;
    
    public final short getTs() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final short[] getRanges() {
        return null;
    }
    
    public final long getCreatedTs() {
        return 0L;
    }
    
    public BlePositionData(short ts, @org.jetbrains.annotations.NotNull()
    short[] ranges) {
        super();
    }
    
    public BlePositionData() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\n\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\b\u00a8\u0006\r"}, d2 = {"Ldemo/app/paintball/data/ble/data/BlePositionData$Companion;", "", "()V", "generateRanges", "", "bb", "Ljava/nio/ByteBuffer;", "anchorCount", "", "parse", "Ldemo/app/paintball/data/ble/data/BlePositionData;", "bytes", "", "app_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final demo.app.paintball.data.ble.data.BlePositionData parse(@org.jetbrains.annotations.NotNull()
        byte[] bytes, short anchorCount) {
            return null;
        }
        
        private final short[] generateRanges(java.nio.ByteBuffer bb, short anchorCount) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}
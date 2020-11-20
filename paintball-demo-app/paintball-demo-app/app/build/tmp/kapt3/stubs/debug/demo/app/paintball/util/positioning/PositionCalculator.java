package demo.app.paintball.util.positioning;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\fJ\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&R\u0018\u0010\u0002\u001a\u00020\u0003X\u00a6\u000e\u00a2\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007\u00a8\u0006\r"}, d2 = {"Ldemo/app/paintball/util/positioning/PositionCalculator;", "", "listener", "Ldemo/app/paintball/util/positioning/PositionCalculator$PositionCalculatorListener;", "getListener", "()Ldemo/app/paintball/util/positioning/PositionCalculator$PositionCalculatorListener;", "setListener", "(Ldemo/app/paintball/util/positioning/PositionCalculator$PositionCalculatorListener;)V", "calculate", "", "data", "Ldemo/app/paintball/data/ble/data/BlePositionData;", "PositionCalculatorListener", "app_debug"})
public abstract interface PositionCalculator {
    
    @org.jetbrains.annotations.NotNull()
    public abstract demo.app.paintball.util.positioning.PositionCalculator.PositionCalculatorListener getListener();
    
    public abstract void setListener(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.util.positioning.PositionCalculator.PositionCalculatorListener p0);
    
    public abstract void calculate(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.ble.data.BlePositionData data);
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&\u00a8\u0006\u0007"}, d2 = {"Ldemo/app/paintball/util/positioning/PositionCalculator$PositionCalculatorListener;", "", "onPositionCalculated", "", "posX", "", "posY", "app_debug"})
    public static abstract interface PositionCalculatorListener {
        
        public abstract void onPositionCalculated(int posX, int posY);
    }
}
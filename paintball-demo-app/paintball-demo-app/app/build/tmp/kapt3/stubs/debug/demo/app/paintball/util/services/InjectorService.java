package demo.app.paintball.util.services;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&\u00a8\u0006\n"}, d2 = {"Ldemo/app/paintball/util/services/InjectorService;", "", "ble", "Ldemo/app/paintball/data/ble/BleServiceImpl;", "mqtt", "Ldemo/app/paintball/data/mqtt/MqttServiceImpl;", "player", "Ldemo/app/paintball/util/services/PlayerService;", "rest", "Ldemo/app/paintball/data/rest/RestServiceImpl;", "app_debug"})
@dagger.Component()
@javax.inject.Singleton()
public abstract interface InjectorService {
    
    @org.jetbrains.annotations.NotNull()
    public abstract demo.app.paintball.data.rest.RestServiceImpl rest();
    
    @org.jetbrains.annotations.NotNull()
    public abstract demo.app.paintball.data.mqtt.MqttServiceImpl mqtt();
    
    @org.jetbrains.annotations.NotNull()
    public abstract demo.app.paintball.data.ble.BleServiceImpl ble();
    
    @org.jetbrains.annotations.NotNull()
    public abstract demo.app.paintball.util.services.PlayerService player();
}
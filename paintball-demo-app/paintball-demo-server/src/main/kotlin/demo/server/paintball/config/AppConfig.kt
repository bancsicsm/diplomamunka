package demo.server.paintball.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component

@Component
@PropertySource(value = ["classpath:default.properties"])
class AppConfig {

    @Value("\${environment}")
    val environment = ""

    @Value("\${mqtt.broker.url}")
    val mqttBrokerUrl = ""
}
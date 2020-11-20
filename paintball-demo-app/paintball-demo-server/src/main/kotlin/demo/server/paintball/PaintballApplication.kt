package demo.server.paintball

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PaintballApplication

fun main(args: Array<String>) {
	runApplication<PaintballApplication>(*args)
}

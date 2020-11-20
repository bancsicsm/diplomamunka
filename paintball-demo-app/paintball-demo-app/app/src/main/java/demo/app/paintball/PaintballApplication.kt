package demo.app.paintball

import android.app.Application
import android.content.Context
import demo.app.paintball.util.services.DaggerInjectorService
import demo.app.paintball.util.services.InjectorService

class PaintballApplication : Application() {

    companion object {
        lateinit var context: Context
        lateinit var services: InjectorService
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        services = DaggerInjectorService.builder().build()
    }
}
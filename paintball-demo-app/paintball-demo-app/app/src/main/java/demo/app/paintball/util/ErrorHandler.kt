package demo.app.paintball.util

import demo.app.paintball.data.rest.RestService

object ErrorHandler : RestService.ErrorListener {

    override fun handleError(t: Throwable) {
        toast("Error occurred: $t")
    }
}
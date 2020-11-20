package demo.app.paintball.fragments.dialogs

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import demo.app.paintball.R
import kotlinx.android.synthetic.main.fragment_join_game.*

class JoinGameFragment : DialogFragment() {

    private lateinit var listener: JoinGameListener

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = activity as JoinGameListener
        } catch (e: ClassCastException) {
            throw RuntimeException(e)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_join_game, container, false)
        dialog?.setTitle(R.string.itemPlayerName)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnDone.setOnClickListener{
            val name = etPlayerName.text.toString()
            val errorMsg = getString(R.string.fill_out)

            if (name != "") {
                listener.onJoinGame(etPlayerName.text.toString())
                dismiss()
            } else {
                etPlayerName.error = errorMsg
            }
        }
    }

    interface JoinGameListener {
        fun onJoinGame(playerName: String)
    }
}
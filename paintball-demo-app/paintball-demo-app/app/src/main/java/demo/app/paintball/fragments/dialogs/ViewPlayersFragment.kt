package demo.app.paintball.fragments.dialogs

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.DialogFragment
import demo.app.paintball.PaintballApplication
import demo.app.paintball.R
import demo.app.paintball.data.rest.models.Player

class ViewPlayersFragment : DialogFragment() {

    private var players: Array<String>? = null

    companion object {
        @JvmStatic
        fun newInstance(players: MutableList<Player>?) = ViewPlayersFragment().apply {
            arguments = Bundle().apply {
                putStringArray("PLAYERS", players?.map { it.name }?.toTypedArray())
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getStringArray("PLAYERS")?.let {
            players = it
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_view_players, container, false)
        players?.let {
            val listView = view.findViewById<ListView>(R.id.lsPlayers)
            val adapter = ArrayAdapter<String>(PaintballApplication.context, android.R.layout.simple_expandable_list_item_1, it)
            listView.adapter = adapter
        }
        return view
    }
}
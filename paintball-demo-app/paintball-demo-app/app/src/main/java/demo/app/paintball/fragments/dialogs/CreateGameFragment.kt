package demo.app.paintball.fragments.dialogs

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.DialogFragment
import demo.app.paintball.PaintballApplication
import demo.app.paintball.R
import demo.app.paintball.data.rest.models.Game
import kotlinx.android.synthetic.main.fragment_create_game.*
import kotlinx.android.synthetic.main.fragment_join_game.etPlayerName

class CreateGameFragment : DialogFragment() {

    private lateinit var listener: CreateGameListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setStyle(STYLE_NORMAL, R.style.TitleDialog)

        try {
            listener = activity as CreateGameListener
        } catch (e: ClassCastException) {
            throw RuntimeException(e)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_create_game, container, false)
        dialog?.setTitle(R.string.create_game)
        setUpSpinner(view)
        return view
    }

    override fun onStart() {
        super.onStart()
        initTestData()
    }

    private fun setUpSpinner(view: View) {
        val spinner: Spinner = view.findViewById(R.id.spGameType)
        ArrayAdapter.createFromResource(
            PaintballApplication.context,
            R.array.game_type_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnCreate.setOnClickListener {
            val playerName = etPlayerName.text.toString()
            val gameName = etGameName.text.toString()
            val gameType = spGameType.selectedItem.toString()
            val gameTime = etGameTime.text.toString()
            val errorMsg = getString(R.string.fill_out)

            when ("") {
                playerName -> etPlayerName.error = errorMsg
                gameName -> etGameName.error = errorMsg
                gameTime -> etGameTime.error = errorMsg
                else -> {
                    val game = Game(
                        name = gameName,
                        type = gameType,
                        time = gameTime.toInt(),
                        admin = playerName
                    )
                    listener.onCreateGame(playerName, game)
                    dismiss()
                }
            }
        }
    }

    private fun initTestData() {
        if (resources.getBoolean(R.bool.testGameTexts)) {
            etPlayerName.setText(getString(R.string.test_player))
            etGameName.setText(getString(R.string.test_game))
            etGameTime.setText(getString(R.string.test_time))
        }
    }

    interface CreateGameListener {
        fun onCreateGame(playerName: String, game: Game)
    }
}
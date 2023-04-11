package ph.stacktreck.snakeandladders.ternida.johnkenneth

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LeaderboardsActivity : AppCompatActivity(){

    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        // Initialize SharedPreferences instance
        prefs = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        // Get player 1 and player 2 wins from SharedPreferences
        val player1Wins = prefs.getInt(PREF_PLAYER_1_WINS, 0)
        val player2Wins = prefs.getInt(PREF_PLAYER_2_WINS, 0)

        // Set the text of the TextViews to display the player wins
        val player1WinsTextView = findViewById<TextView>(R.id.player1_wins_text_view)
        val player2WinsTextView = findViewById<TextView>(R.id.player2_wins_text_view)
        player1WinsTextView.text = "Player 1 Wins: $player1Wins"
        player2WinsTextView.text = "Player 2 Wins: $player2Wins"
    }

    companion object {
        const val PREF_PLAYER_1_WINS = "pref_player_1_wins"
        const val PREF_PLAYER_2_WINS = "pref_player_2_wins"
    }
}

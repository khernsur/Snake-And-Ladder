package ph.stacktreck.snakeandladders.ternida.johnkenneth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class WinnerPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_winner_page)
        val winner = intent.getStringExtra("winner")
        val winnerTextView = findViewById<TextView>(R.id.winnerTextView)
        winnerTextView.text = "$winner wins!"

        val leaderboardButton = findViewById<Button>(R.id.leaderboardButton)
        leaderboardButton.setOnClickListener {
            val intent = Intent(this, LeaderboardsActivity::class.java)
            startActivity(intent)
        }
    }


}
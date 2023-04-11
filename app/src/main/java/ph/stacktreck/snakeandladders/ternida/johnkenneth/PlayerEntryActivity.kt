package ph.stacktreck.snakeandladders.ternida.johnkenneth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PlayerEntryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_entry2)

        val backButton = findViewById<Button>(R.id.back_button)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val doneButton = findViewById<Button>(R.id.done_button)
        doneButton.setOnClickListener {
            val intent = Intent(this, InGamePageActivity::class.java)
            startActivity(intent)
        }
    }
}
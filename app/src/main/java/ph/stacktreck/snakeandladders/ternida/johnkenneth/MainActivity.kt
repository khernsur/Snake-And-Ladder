package ph.stacktreck.snakeandladders.ternida.johnkenneth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val startButton = findViewById<Button>(R.id.start_button)
        startButton.setOnClickListener {
            val intent = Intent(this, PlayerEntryActivity::class.java)
            startActivity(intent)
        }

    }
}





//        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
//        // Save players' names
//        val editor = sharedPreferences.edit()
//        editor.putString(PLAYER_1_KEY, player1Name)
//        editor.putString(PLAYER_2_KEY, player2Name)
//        editor.apply()
//
//        // Retrieve players' names
//        val player1Name = sharedPreferences.getString(PLAYER_1_KEY, null)
//        val player2Name = sharedPreferences.getString(PLAYER_2_KEY, null)
//
//        // Save winners' list
//        val winnersSet = mutableSetOf<String>()
//        winnersSet.addAll(winnersList)
//        val editor = sharedPreferences.edit()
//        editor.putStringSet(WINNERS_KEY, winnersSet)
//        editor.apply()
//
//        // Retrieve winners' list
//        val winnersSet = sharedPreferences.getStringSet(WINNERS_KEY, emptySet())
//        val winnersList = winnersSet.toList()
package ph.stacktreck.snakeandladders.ternida.johnkenneth

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import ph.stacktreck.snakeandladders.ternida.johnkenneth.LeaderboardsActivity.Companion.PREF_PLAYER_1_WINS
import ph.stacktreck.snakeandladders.ternida.johnkenneth.LeaderboardsActivity.Companion.PREF_PLAYER_2_WINS

class InGamePageActivity : AppCompatActivity() {

    private lateinit var boardImageView: ImageView
    private lateinit var player1FrameLayout: FrameLayout
    private lateinit var player2FrameLayout: FrameLayout
    private lateinit var player1View: View
    private lateinit var player2View: View
    private lateinit var rollButton: Button
    private lateinit var positionTextView: TextView
    private lateinit var winnerTextView: TextView

    private val boardSize = 10
    private var player1Position = 1
    private var player2Position = 1
    private var currentPlayer = 1

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_in_game_page)

        val backButton1 = findViewById<Button>(R.id.back_button1)
        backButton1.setOnClickListener {
            val intent = Intent(this, RatingActivity::class.java)
            startActivity(intent)
        }
        // finds the UI components by their ID and initialize them
        boardImageView = findViewById(R.id.boardImageView)
        player1FrameLayout = findViewById(R.id.player1FrameLayout)
        player2FrameLayout = findViewById(R.id.player2FrameLayout)
        player1View = findViewById(R.id.player1View)
        player2View = findViewById(R.id.player2View)
        rollButton = findViewById(R.id.rollButton)
        positionTextView = findViewById(R.id.positionTextView)


        rollButton.setOnClickListener {
            rollDice()
        }

        updatePositions()
    }

    private fun rollDice() {
        // generates random numbers 1-6
        val diceRoll = (1..6).random()

        //updates the position of the current player
        if (currentPlayer == 1) {
            player1Position += diceRoll
        } else {
            player2Position += diceRoll
        }
        //checks if the player reached the end of the board
        if (player1Position > boardSize * boardSize) {
            player1Position = boardSize * boardSize
            showWinner(1)

        } else if (player2Position >= boardSize * boardSize) {
            player2Position = boardSize * boardSize
            showWinner(2)
        } else {
            //switches the players
            currentPlayer = if (currentPlayer == 1) 2 else 1
        }
        updatePositions()

    }

    private fun showWinner(playerNumber: Int) {
        val prefKey = if (playerNumber == 1) PREF_PLAYER_1_WINS else PREF_PLAYER_2_WINS
        val winnerName = if (playerNumber == 1) "Player 1" else "Player 2"

        val winnerPref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        var winnerCount = winnerPref.getInt(prefKey, 0)
        winnerCount++

        with (winnerPref.edit()) {
            putInt(prefKey, winnerCount)
            apply()
        }

        if (playerNumber == 1) {
            if (player1Position >= 100) {
                val intent = Intent(this, WinnerPageActivity::class.java)
                intent.putExtra("winner", "Player 1")
                startActivity(intent)
                return
            }
        } else {
            if (player2Position >= 100) {
                val intent = Intent(this, WinnerPageActivity::class.java)
                intent.putExtra("winner", "Player 2")
                startActivity(intent)
                return
            }
        }

        winnerTextView.text = "Player $playerNumber wins!"
        rollButton.isEnabled = false
        Toast.makeText(this, "Congratulations, Player $playerNumber!", Toast.LENGTH_SHORT).show()
    }

    private fun updatePositions() {
        if (currentPlayer == 1) {
            updatePosition(player1FrameLayout, player1View, player1Position)
        } else {
            updatePosition(player2FrameLayout, player2View, player2Position)
        }
        //update the text on the positionTextView
        positionTextView.text =
            "Player $currentPlayer position: ${if (currentPlayer == 1) player1Position else player2Position}"
    }


    private fun updatePosition(frameLayout: FrameLayout, view: View, position: Int) {
        //calculate the row and column of the cell that corresponds to the given position on the board
        val row = (boardSize - 1) - (position - 1) / boardSize
        val col = if (row % 2 == 0) {
            (position - 1) % boardSize
        } else {
            boardSize - 1 - (position - 1) % boardSize
        }
        val cellSize = boardImageView.width / boardSize
        val x = col * cellSize
        val y = row * cellSize
        frameLayout.setPadding(x, y, 0, 0)
    }
}
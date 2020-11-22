package ljie.game2048

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import ljie.game2048.view.GameView

class MainActivity : AppCompatActivity() {

    private lateinit var gameView: GameView
    private lateinit var scoreTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameView = findViewById(R.id.game_view)
        scoreTextView = findViewById(R.id.tv_score)


    }
}
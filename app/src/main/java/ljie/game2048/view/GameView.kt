package ljie.game2048.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.GridLayout
import android.widget.RelativeLayout
import kotlin.math.abs

private const val MIN_DISTANCE = 5
private const val TAG = "GameView"
class GameView @JvmOverloads constructor (context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    GridLayout(context, attrs, defStyleAttr) {


    private var num = 0
    private var score = 0
    private var column = 4

    private var margin = 10
    private var padding = 0

    private var once = false

    private var cells = Array(column) {arrayOfNulls<CellView>(column)}

    init {
        margin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, margin.toFloat(), resources.displayMetrics).toInt()
        padding = paddingStart.coerceAtLeast(paddingTop).coerceAtLeast(paddingBottom).coerceAtLeast(paddingEnd)
        initView()
    }


    private fun initView() {
        columnCount = column
        setBackgroundColor(0xffbbada0.toInt())
        setOnTouchListener(object : OnTouchListener {
            private var startX = 0F
            private var startY = 0F
            private var offsetX = 0F
            private var offsetY = 0F
            @SuppressLint("ClickableViewAccessibility")
            override fun onTouch(view: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        startX = event.x
                        startY = event.y
                    }
                    MotionEvent.ACTION_UP -> {
                        offsetX = event.x - startX
                        offsetY = event.y - startY

                        // 判断滑动方向
                        if (abs(offsetX) > abs(offsetY)) {
                            if (offsetX > MIN_DISTANCE) {
                                swipeRight()
                            } else if (offsetX < -MIN_DISTANCE) {
                                swipeLeft()
                            }
                        } else {
                            if (offsetY > MIN_DISTANCE) {
                                swipeDown()
                            } else if (offsetY < -MIN_DISTANCE) {
                                swipeUp()
                            }
                        }
                    }
                }
                return true
            }
        })
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        addCell((w - 10 )/ 4)

        startGame()
    }

    private fun startGame() {
        for (i in 0 until column) {
            for (j in 0 until column) {
                cells[j][i]?.setNum(0)
            }
        }
    }

    private fun addCell(width: Int) {
        for (i in 0 until column) {
            for (j in 0 until column) {
                val cell = CellView(context)
                cell.setNum(0)
                addView(cell, width, height)
                cells[j][i] = cell
            }
        }
    }


    private fun isOver() : Boolean {
        if (!isFull()) {
            return false
        }

        for (i in 0 until column) {
            for (j in 0 until column) {

            }
        }
        return true
    }

    private fun isFull() : Boolean {
        return false
    }

    private fun swipeLeft() {

    }

    private fun swipeRight() {

    }

    private fun swipeUp() {

    }

    private fun swipeDown() {

    }

}
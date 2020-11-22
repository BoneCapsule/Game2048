package ljie.game2048.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView

class CellView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr) {

    private var num: Int = 0
    private var background: View = View(context)
    private var label: TextView = TextView(context)
    private val colorMap = mapOf(0 to "#CCC0B3", 2 to "#EEE4DA", 4 to "#EDE0C8",
        8 to "#F2B179", 16 to "#F49563", 32 to "#F5794D", 64 to "#F55D37", 128 to "#EEE863",
        256 to "#EDB04D", 512 to "#ECB04D", 1024 to "#EB9437", 2048 to "#EA7821", 4096 to "#EA7821")

    init {
        background.setBackgroundColor(0x33ffffff)
        val lp = LayoutParams(-1, -1)
        lp.setMargins(10, 10, 0, 0)
        addView(background, lp)

        label.textSize = 28F
        label.gravity = Gravity.CENTER
        addView(label, lp)
        setNum(0)
    }

    fun setNum(num: Int) {
        this.num = num
        if (num <= 0) {
            label.text = ""
            label.setBackgroundColor(0x33ffffff)
        } else {
            label.text = num.toString()
            val color = if (colorMap.containsKey(num)) {
                colorMap[num]
            } else {
                colorMap[0]
            }
            label.setBackgroundColor(Color.parseColor(color))
        }
    }

    fun getNum() = num

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }
        val o = other as CellView
        return this.num == o.num
    }

    override fun hashCode(): Int {
        var result = num
        result = 31 * result + label.hashCode()
        result = 31 * result + colorMap.hashCode()
        return result
    }
}
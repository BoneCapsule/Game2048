package ljie.game2048.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView

class CellView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr) {

    private var num: Int = 0
    private var background: View = View(context)
    private var label: TextView = TextView(context)
    private val colorMap = mapOf(0 to 0x00000000, 2 to 0xffeee4da, 4 to 0xffede0c8,
        8 to 0xfff2b179, 16 to 0xfff59563, 32 to 0xfff67c5f, 64 to 0xfff65e3b, 128 to 0xffedcf72,
        256 to 0xffedcc61, 512 to 0xffedc850, 1024 to 0xffedc53f, 2048 to 0xffedc22e)

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
        label.text = if (num <= 0) "" else num.toString()

        val color = if (colorMap.containsKey(num)) {
            colorMap[num]
        } else {
            0xff3c3a32
        }
        label.setBackgroundColor(color as Int)
    }

    fun getNum() = num

    fun getLabel() = label

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

    fun clone(): CellView {
        val cell = CellView(context)
        cell.setNum(num)
        return cell
    }
}
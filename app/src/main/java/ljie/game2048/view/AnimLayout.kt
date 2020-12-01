package ljie.game2048.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import android.widget.FrameLayout


class AnimLayout @JvmOverloads constructor (context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr) {

    private val cells by lazy { arrayListOf<CellView>() }

    fun createMoveAnim(from: CellView, to: CellView, fromX: Int, toX: Int, fromY: Int, toY: Int) {
        val cell = getCell(from.getNum())
        val lp = LayoutParams(GameView.WIDTH, GameView.WIDTH)
        lp.leftMargin = fromX * GameView.WIDTH
        lp.topMargin = fromX * GameView.WIDTH
        cell.layoutParams = lp

        if (to.getNum() <= 0) {
            to.getLabel().visibility = View.INVISIBLE
        }

        val ta = TranslateAnimation(0F, (GameView.WIDTH * (toX - fromX)).toFloat(),
            0F, (GameView.WIDTH * (toY - fromY)).toFloat())
        ta.duration = 100
        ta.setAnimationListener(object: Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                to.getLabel().visibility = View.VISIBLE
                recycleCell(cell)
            }

            override fun onAnimationStart(p0: Animation?) {

            }

        })


    }

    fun createScaleTo1(cell: CellView) {
        val sa = ScaleAnimation(0.1F, 1F, 0.1F, 1F,
            Animation.RELATIVE_TO_SELF, 0.5F,
            Animation.RELATIVE_TO_SELF, 0.5F
        )
        sa.duration = 100
        cell.animation = null
        cell.getLabel().startAnimation(sa)
    }

    private fun getCell(num: Int): CellView {
        val cell: CellView
        if (cells.size > 0) {
            cell = cells.removeAt(0)
        } else {
            cell = CellView(context)
            addView(cell)
        }
        cell.visibility = View.VISIBLE
        cell.setNum(num)
        return cell
    }

    private fun recycleCell(cell: CellView) {
        cell.visibility = View.INVISIBLE
        cell.animation = null
        cells.add(cell)
    }
}
package cn.shimmer.appcore.utils

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.core.widget.TextViewCompat
import cn.shimmer.appcore.core.BaseApp
import cn.shimmer.appcore.extension.dp2px
import java.lang.ref.WeakReference


/**
 * Created by horsttop on 29/11/2017.
 */
object ToastUtil {
    private const val COLOR_DEFAULT = -0x1000001

    //transparent_half
    private const val COLOR_BG_DEFAULT = -1895825408

    private val HANDLER = Handler(Looper.getMainLooper())

    private var sToast: Toast? = null
    private var sViewWeakReference: WeakReference<View>? = null
    private var sLayoutId = -1
    private var gravity = Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM
    private var xOffset = 0
    private var yOffset = BaseApp.getInstance().dp2px(64f)
    private var bgColor = COLOR_BG_DEFAULT
    private var bgResource = -1
    private var msgColor = COLOR_DEFAULT

    private fun ToastUtil() {
        throw UnsupportedOperationException("u can't instantiate me...")
    }

    /**
     * 设置吐司位置
     *
     * @param gravity 位置
     * @param xOffset x偏移
     * @param yOffset y偏移
     */
    fun setGravity(gravity: Int, xOffset: Int, yOffset: Int) {
        this.gravity = gravity
        this.xOffset = xOffset
        this.yOffset = yOffset
    }

    /**
     * 设置背景颜色
     *
     * @param backgroundColor 背景色
     */
    fun setBgColor(@ColorInt backgroundColor: Int) {
        this.bgColor = backgroundColor
    }

    /**
     * 设置背景资源
     *
     * @param bgResource 背景资源
     */
    fun setBgResource(@DrawableRes bgResource: Int) {
        this.bgResource = bgResource
    }

    /**
     * 设置消息颜色
     *
     * @param msgColor 颜色
     */
    fun setMsgColor(@ColorInt msgColor: Int) {
        this.msgColor = msgColor
    }

    /**
     * 安全地显示短时吐司
     *
     * @param text 文本
     */
    fun showShort(text: CharSequence) {
        showed(text, Toast.LENGTH_SHORT)
    }

    /**
     * 安全地显示短时吐司
     *
     * @param resId 资源Id
     */
    fun showShort(@StringRes resId: Int) {
        show(resId, Toast.LENGTH_SHORT)
    }

    /**
     * 安全地显示短时吐司
     *
     * @param resId 资源Id
     * @param args  参数
     */
    fun showShort(@StringRes resId: Int, vararg args: Any) {
        show(resId, Toast.LENGTH_SHORT, *args)
    }

    /**
     * 安全地显示短时吐司
     *
     * @param format 格式
     * @param args   参数
     */
    fun showShort(format: String, vararg args: Any) {
        show(format, Toast.LENGTH_SHORT, *args)
    }

    /**
     * 安全地显示长时吐司
     *
     * @param text 文本
     */
    fun showLong(text: CharSequence) {
        showed(text, Toast.LENGTH_LONG)
    }

    /**
     * 安全地显示长时吐司
     *
     * @param resId 资源Id
     */
    fun showLong(@StringRes resId: Int) {
        show(resId, Toast.LENGTH_LONG)
    }

    /**
     * 安全地显示长时吐司
     *
     * @param resId 资源Id
     * @param args  参数
     */
    fun showLong(@StringRes resId: Int, vararg args: Any) {
        show(resId, Toast.LENGTH_LONG, *args)
    }

    /**
     * 安全地显示长时吐司
     *
     * @param format 格式
     * @param args   参数
     */
    fun showLong(format: String, vararg args: Any) {
        show(format, Toast.LENGTH_LONG, *args)
    }

    /**
     * 安全地显示短时自定义吐司
     */
    fun showCustomShort(@LayoutRes layoutId: Int): View {
        val view = getView(layoutId)
        showed(view, Toast.LENGTH_SHORT)
        return view
    }

    /**
     * 安全地显示长时自定义吐司
     */
    fun showCustomLong(@LayoutRes layoutId: Int): View {
        val view = getView(layoutId)
        showed(view, Toast.LENGTH_LONG)
        return view
    }

    /**
     * 取消吐司显示
     */
    fun cancel() {
        if (sToast != null) {
            sToast!!.cancel()
            sToast = null
        }
    }

    private fun show(@StringRes resId: Int, duration: Int) {
        show(BaseApp.getInstance().resources.getText(resId).toString(), duration)
    }

    private fun show(@StringRes resId: Int, duration: Int, vararg args: Any) {
        show(String.format(BaseApp.getInstance().resources.getString(resId), args), duration)
    }

    private fun show(format: String, duration: Int, vararg args: Any) {
        showed(String.format(format, *args), duration)
    }

    private fun showed(text: CharSequence, duration: Int) {
        HANDLER.post {
            cancel()
            sToast = Toast.makeText(BaseApp.getInstance(), text, duration)
            // solve the font of toast
            val tvMessage = sToast!!.view.findViewById<View>(android.R.id.message) as TextView
            TextViewCompat.setTextAppearance(tvMessage, android.R.style.TextAppearance)
            tvMessage.setTextColor(msgColor)
            setBgAndGravity()
            sToast!!.show()
        }
    }

    private fun showed(view: View, duration: Int) {
        HANDLER.post {
            cancel()
            sToast = Toast(BaseApp.getInstance())
            sToast!!.view = view
            sToast!!.duration = duration
            setBgAndGravity()
            sToast!!.show()
        }
    }

    private fun setBgAndGravity() {
        val toastView = sToast!!.view
        if (bgResource != -1) {
            toastView.setBackgroundResource(bgResource)
        } else if (bgColor != COLOR_DEFAULT) {
            val background = toastView.background
            background.colorFilter = PorterDuffColorFilter(bgColor, PorterDuff.Mode.SRC_IN)
        }
        sToast!!.setGravity(gravity, xOffset, yOffset)
    }

    private fun getView(@LayoutRes layoutId: Int): View {
        if (sLayoutId == layoutId) {
            if (sViewWeakReference != null) {
                val toastView = sViewWeakReference!!.get()
                if (toastView != null) {
                    return toastView
                }
            }
        }
        val inflate =
            BaseApp.getInstance().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val toastView = inflate.inflate(layoutId, null)
        sViewWeakReference = WeakReference(toastView)
        sLayoutId = layoutId
        return toastView
    }
}
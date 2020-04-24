package cn.shimmer.appcore.utils

object OSUtils {

    @JvmStatic
    public val ROM_MIUI = "MIUI"
    @JvmStatic
    val ROM_EMUI = "EMUI"
    @JvmStatic
    val ROM_FLYME = "FLYME"
    @JvmStatic
    val ROM_OPPO = "OPPO"
    @JvmStatic
    val ROM_SMARTISAN = "SMARTISAN"
    @JvmStatic
    val ROM_VIVO = "VIVO"
    @JvmStatic
    val ROM_QIKU = "QIKU"

    @JvmStatic
    val KEY_VERSION_MIUI = "ro.miui.ui.version.name"
    @JvmStatic
    val KEY_VERSION_EMUI = "ro.build.version.emui"
    @JvmStatic
    val KEY_VERSION_OPPO = "ro.build.version.opporom"
    @JvmStatic
    val KEY_VERSION_SMARTISAN = "ro.smartisan.version"
    @JvmStatic
    val KEY_VERSION_VIVO = "ro.vivo.os.version"
}
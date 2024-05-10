package notifications

import java.awt.Image
import java.awt.SystemTray
import java.awt.Toolkit
import java.awt.TrayIcon

class DesktopTrayNotifier(
    tray: SystemTray = SystemTray.getSystemTray(),
    title: String = "App Notify",
    trayImageResourcePath: String? = null
) {
    private val image = createImageFromResource(trayImageResourcePath)
        ?: makeDefaultImage()
    val trayIcon: TrayIcon = TrayIcon(image, title)

    init {
        tray.add(trayIcon)
    }

    fun sendNotification(title: String, message: String) {
    }

    companion object {
        private fun createImageFromResource(
            imageResourcePath: String?
        ): Image? {
            return imageResourcePath?.let {
                Toolkit.getDefaultToolkit().createImage(it)
            }
        }

        private fun makeDefaultImage(): Image {
            return Toolkit.getDefaultToolkit()
                .createImage(
                    TrayIcon::class.java.getResource("icon.png")
                )
        }
    }
}

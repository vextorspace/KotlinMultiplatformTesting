package notifications

import java.awt.Image
import java.awt.SystemTray
import java.awt.Toolkit
import java.awt.TrayIcon

class DesktopTrayNotifier(private val tray: SystemTray, val trayIcon: TrayIcon) {


    init {
        tray.add(trayIcon)
    }

    constructor(
        tray: SystemTray = SystemTray.getSystemTray(),
        title: String = "App Notify",
        trayImageResourcePath: String? = null
    ) : this(
        tray,
        TrayIcon(
            createImageFromResource(trayImageResourcePath)
                ?: makeDefaultImage(),
            title
        )
    )

    constructor(title: String, trayImageResourcePath: String? = null) : this(
        SystemTray.getSystemTray(),
        title,
        trayImageResourcePath
    )


    fun sendNotification(title: String, message: String) {
        trayIcon.displayMessage(
            title,
            message,
            TrayIcon.MessageType.INFO
        )
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

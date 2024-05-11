package notifications

import java.awt.Image
import java.awt.SystemTray
import java.awt.Toolkit
import java.awt.TrayIcon

class DesktopTrayNotifier(tray: SystemTray?, val trayIcon: TrayIcon?) {


    init {
        tray?.add(trayIcon)
    }

    constructor(
        tray: SystemTray? = if(SystemTray.isSupported()) SystemTray.getSystemTray() else null,
        title: String = "App Notify",
        trayImageResourcePath: String? = null
    ) : this(
        tray,
        tray?.let {
            TrayIcon(
                createImageFromResource(trayImageResourcePath)
                    ?: makeDefaultImage(),
                title
            )
        }
    )

    constructor(title: String, trayImageResourcePath: String? = null) : this(
        if(SystemTray.isSupported()) SystemTray.getSystemTray() else null,
        title,
        trayImageResourcePath
    )

    fun sendNotification(title: String, message: String) {
        trayIcon?.displayMessage(
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
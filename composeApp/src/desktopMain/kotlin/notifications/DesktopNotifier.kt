package notifications

import platform.PlatformIdentifier

class DesktopNotifier (
    private val platformIdentifier: PlatformIdentifier,
    private val linuxGtkNotify: LinuxGtkNotify,
    private val desktopTrayNotifier: DesktopTrayNotifier
) {
    constructor() : this(
        platformIdentifier = PlatformIdentifier(),
        linuxGtkNotify = LinuxGtkNotify(),
        desktopTrayNotifier = DesktopTrayNotifier()
    )

    fun send(title: String, message: String) {
        if (platformIdentifier.isLinux()) {
            linuxGtkNotify.send(title, message)
        } else {
            desktopTrayNotifier.sendNotification(title, message)
        }
    }
}

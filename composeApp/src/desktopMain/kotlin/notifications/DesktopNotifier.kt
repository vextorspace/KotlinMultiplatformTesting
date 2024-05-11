package notifications

import platform.PlatformIdentifier

class DesktopNotifier (
    private val platformIdentifier: PlatformIdentifier,
    private val linuxGtkNotify: LinuxGtkNotify,
    private val desktopTrayNotifier: DesktopTrayNotifier
) : PlatformSpecificNotification {
    constructor() : this(
        platformIdentifier = PlatformIdentifier(),
        linuxGtkNotify = LinuxGtkNotify(),
        desktopTrayNotifier = DesktopTrayNotifier()
    )

    override fun send(title: String, message: String) {
        if (platformIdentifier.isLinux()) {
            linuxGtkNotify.send(title, message)
        } else {
            desktopTrayNotifier.sendNotification(title, message)
        }
    }
}

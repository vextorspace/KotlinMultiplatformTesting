package notifications

import java.io.File

class WindowsPowershellNotify(val title: String, val message: String) {
    fun makeCommand(): List<String> {
        return listOf("powershell.exe",
            "[void] [System.Reflection.Assembly]::LoadWithPartialName('System.Windows.Forms')\n" +
                    "\$objNotifyIcon = New-Object System.Windows.Forms.NotifyIcon\n" +
                    "\$objNotifyIcon.Icon = [System.Drawing.SystemIcons]::Information\n" +
                    "\$objNotifyIcon.BalloonTipIcon = \"$title\"\n" +
                    "\$objNotifyIcon.BalloonTipText = \"$message\"\n" +
                    "\$objNotifyIcon.BalloonTipTitle = \"$title\"\n" +
                    "\$objNotifyIcon.Visible = \$True\n" +
                    "\$objNotifyIcon.ShowBalloonTip(10000)"
        )
    }

    fun send() {
        try {
            ProcessBuilder(makeCommand())
                .directory(File("."))
                .start()
                .waitFor()
        } catch (e: Exception) {
            println(e)
        }
    }
}

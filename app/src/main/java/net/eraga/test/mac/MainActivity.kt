package net.eraga.test.mac

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.net.NetworkInterface

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text.text = getMacAddress()
    }

    private fun getMacAddress(): String {
        try {
            val networkInterfaces = NetworkInterface.getNetworkInterfaces()
            for (nif in networkInterfaces) {
                if (!nif.name.equals("wlan0", ignoreCase = true)) continue
                val macBytes = nif.hardwareAddress ?: return ""
                val builder = StringBuilder()
                for (b in macBytes) {
                    builder.append(String.format("%02X:", b))
                }

                if (builder.isNotEmpty()) {
                    builder.deleteCharAt(builder.length - 1)
                }
                return builder.toString()
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Unable get mac address", ex)
        }

        return "02:00:00:00:00:00"
    }
}

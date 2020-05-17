package com.example.bluetoothnoti

//import android.R

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothHeadset
import android.bluetooth.BluetoothProfile
import android.content.Context


class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		setSupportActionBar(toolbar)

		fab.setOnClickListener { view ->
			Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
					.setAction("Action", null).show()
		}


		var bluetoothHeadset: BluetoothHeadset? = null

// Get the default adapter
		val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()

		private val profileListener = object : BluetoothProfile.ServiceListener {

			override fun onServiceConnected(profile: Int, proxy: BluetoothProfile) {
				if (profile == BluetoothProfile.HEADSET) {
					bluetoothHeadset = proxy as BluetoothHeadset
				}
			}

			override fun onServiceDisconnected(profile: Int) {
				if (profile == BluetoothProfile.HEADSET) {
					bluetoothHeadset = null
				}
			}
		}

// Establish connection to the proxy.
		bluetoothAdapter?.getProfileProxy(context, profileListener, BluetoothProfile.HEADSET)

// ... call functions on bluetoothHeadset

// Close proxy connection after use.
		bluetoothAdapter?.closeProfileProxy(BluetoothProfile.HEADSET, bluetoothHeadset)
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		// Inflate the menu; this adds items to the action bar if it is present.
		menuInflater.inflate(R.menu.menu_main, menu)
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		return when (item.itemId) {
			R.id.action_settings -> true
			else -> super.onOptionsItemSelected(item)
		}
	}
}

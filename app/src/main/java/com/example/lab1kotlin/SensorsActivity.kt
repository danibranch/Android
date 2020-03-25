package com.example.lab1kotlin

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import org.w3c.dom.Text


class SensorsActivity : AppCompatActivity(), SensorEventListener {
    private var sensorManager: SensorManager? = null
    private var accelerometer: Sensor? = null
//    private var gyroscope: Sensor? = null
    private var proximitySensor: Sensor? = null
//    private var tempSensor: Sensor? = null
//    private var pressureSensor: Sensor? = null
    private var locationManager: LocationManager? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensors)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager?
        accelerometer = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
//        gyroscope = sensorManager?.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        proximitySensor = sensorManager?.getDefaultSensor(Sensor.TYPE_PROXIMITY)
//        tempSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
//        pressureSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_PRESSURE)


        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            var location = locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            findViewById<TextView>(R.id.locationLongitude).text = "Location longitude: ${location?.longitude}"
            findViewById<TextView>(R.id.locationLatitude).text = "Location latitude: ${location?.latitude}"
        }
        else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager?.registerListener(this, accelerometer as Sensor, SensorManager.SENSOR_DELAY_NORMAL)
//        sensorManager?.registerListener(this, gyroscope as Sensor, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager?.registerListener(this, proximitySensor as Sensor, SensorManager.SENSOR_DELAY_NORMAL)
//        sensorManager?.registerListener(this, tempSensor as Sensor, SensorManager.SENSOR_DELAY_NORMAL)
//        sensorManager?.registerListener(this, pressureSensor as Sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }


    override fun onPause() {
        super.onPause()
        sensorManager?.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
//        Log.d("accuracy changed", accuracy.toString())
    }

    override fun onSensorChanged(event: SensorEvent?) {
        when (event?.sensor?.type) {
            Sensor.TYPE_ACCELEROMETER -> {
                val accelerometerValues = event?.values
                findViewById<TextView>(R.id.accelerometerValue1).text =
                    "Accelerometer X: ${accelerometerValues?.get(0).toString()}"
                findViewById<TextView>(R.id.accelerometerValue2).text =
                    "Accelerometer Y: ${accelerometerValues?.get(1).toString()}"
                findViewById<TextView>(R.id.accelerometerValue3).text =
                    "Accelerometer Z: ${accelerometerValues?.get(2).toString()}"
            }
            Sensor.TYPE_PROXIMITY -> {
                findViewById<TextView>(R.id.proximity).text = "Proximity: ${event?.values?.get(0)}"
            }
//            Sensor.TYPE_AMBIENT_TEMPERATURE -> {
//                findViewById<TextView>(R.id.temperature).text = "Temperature: ${event?.values?.get(0)}"
//            }
//            Sensor.TYPE_PRESSURE -> {
//                findViewById<TextView>(R.id.pressure).text = "Pressure: ${event?.values?.get(0)}"
//            }
        }


//        Log.d("sensor changed", event?.values?.size.toString());
    }
}

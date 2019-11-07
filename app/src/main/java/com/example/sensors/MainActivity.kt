package com.example.sensors

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Vibrator
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SensorEventListener {

    private var sensorManager: SensorManager? = null
    private var sensorProximy: Sensor? = null
    private var sensorLight: Sensor? = null
    private var sensorAccelerometer: Sensor? = null
    private var sensorGyroscope: Sensor? = null

    var textLight: TextView? = null
    var textAprox: TextView? = null
    var textAceler: TextView? = null

    var textGyroscope: TextView? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textLight = findViewById(R.id.textLight)
        textAprox = findViewById(R.id.TextProximity)
        textAceler = findViewById(R.id.textAccelerometer)
        textGyroscope = findViewById(R.id.textGyroscope)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager?

        sensorLight = sensorManager?.getDefaultSensor(Sensor.TYPE_LIGHT)
        sensorProximy = sensorManager?.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        sensorAccelerometer = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorGyroscope = sensorManager?.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    }

    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }

    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(this, sensorLight, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager!!.registerListener(this, sensorProximy, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager!!.registerListener(this, sensorAccelerometer,SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager!!.registerListener(this, sensorGyroscope, SensorManager.SENSOR_DELAY_NORMAL)

    }
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        sensorManager!!.registerListener(this, sensorLight, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager!!.registerListener(this, sensorProximy, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager!!.registerListener(this,sensorAccelerometer,SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager!!.registerListener(this, sensorGyroscope, SensorManager.SENSOR_DELAY_NORMAL)

    }

    override fun onSensorChanged(event: SensorEvent) {
        val vl = (event.values[0])

        if (event.sensor.type == Sensor.TYPE_LIGHT) {
            if(vl>100){
                textLight!!.text = "Light: ALTA"
                idLight.setBackgroundResource(R.color.colorAccent)
            }else{
                textLight!!.text = "Light: BAIXA"
                idLight.setBackgroundResource(R.color.colorPrimaryDark)
            }
           // Log.i("sensor", "Light:  ${vl}")
        }

        if (event.sensor.type == Sensor.TYPE_PROXIMITY) {
            if(vl>0){
                textAprox!!.text = "PROXI: LIVRE"
                idProximity.setBackgroundResource(R.color.colorAccent)

            }else{
                textAprox!!.text = "PROXI: FECHADO"
                idProximity.setBackgroundResource(R.color.colorPrimaryDark)
            }

            Log.i("sensor", "Aproximidade:  ${vl}")

        }

        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            textAceler!!.text = "ACCELER: X:${vl};"
            idAccelerometer.setBackgroundResource(R.color.colorAccent)

         //   Log.i("sensor", "Acelerometro:  ${vl}")

        }

        if (event.sensor.type == Sensor.TYPE_GYROSCOPE) {

            textGyroscope!!.text = "Gyroscope:  ${vl}"
            Log.i("sensor", "Gyroscope:  ${vl}")

        }


    }

}

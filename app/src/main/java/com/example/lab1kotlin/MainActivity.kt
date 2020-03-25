package com.example.lab1kotlin

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.provider.DocumentsContract
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lista = findViewById<ListView>(R.id.lista1);
        val textView = findViewById<TextView>(R.id.textView)
        val arrayList = ArrayList<String>();
        arrayList.add("item1");
        arrayList.add("item2");
        arrayList.add("item3");

        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        lista.adapter =  arrayAdapter;

        lista.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, arrayList[position], Toast.LENGTH_LONG).show();
            textView.text = arrayList[position]
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.activity -> {
                val message = findViewById<TextView>(R.id.textView).text
                val intent = Intent(this, SecondaryActivity::class.java).apply {
                    putExtra(EXTRA_MESSAGE, message)
                }
                startActivity(intent)
            }
            R.id.dialog -> {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle(findViewById<TextView>(R.id.textView).text)

                builder.setPositiveButton("Ok") { dialog, which ->
                    Toast.makeText(applicationContext, "Ok", Toast.LENGTH_SHORT).show()
                };
                builder.setNegativeButton("Cancel") { dialog, which ->
                    Toast.makeText(applicationContext, "Cancelled", Toast.LENGTH_SHORT).show()
                }

                builder.create().show()
            }
            R.id.preferences -> {
                val intent = Intent(this, PreferenceActivity::class.java).apply {}
                startActivity(intent)
            }
            R.id.save -> {
                openFileOutput("file.txt", Context.MODE_PRIVATE).use {
                    it.write("item1, item2, item3".toByteArray())
                }
            }
            R.id.sensorData -> {
                val intent = Intent(this, SensorsActivity::class.java).apply {}
                startActivity(intent)
            }
        }
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putCharSequence("text", findViewById<TextView>(R.id.textView).text)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        findViewById<TextView>(R.id.textView).text = savedInstanceState.getCharSequence("text")
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        Log.d("onCreate", "on create log")
    }

    override fun onResume() {
        super.onResume()
        Log.d("onResume", "on resume log")
    }

    override fun onPause() {
        super.onPause()
        Log.d("onPause", "on pause log")
    }

    override fun onStop() {
        super.onStop()
        Log.d("onStop", "on stop log")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("onDestroy", "on destroy log")
    }
}

package com.example.lifeinminutes

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnDatePicker= findViewById<Button>(R.id.btn_date)


        btnDatePicker.setOnClickListener{view->
            clickableDatePicker(view)
        }
    }

    fun clickableDatePicker(view:View){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val myCalendar=Calendar.getInstance();
            val year=myCalendar.get(Calendar.YEAR)
            val month=myCalendar.get(Calendar.MONTH)
            val day=myCalendar.get(Calendar.DAY_OF_MONTH)
            var txtSetDate=findViewById<TextView>(R.id.txt_date_select);
            var txtSetMinute=findViewById<TextView>(R.id.txt_minutes);
            var txtSetDays=findViewById<TextView>(R.id.txt_days);

            DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener{ view, setday, setmonth, setYear ->
                    val selectedDate="${setYear}/${setmonth+1}/${setday}"
                    txtSetDate.text = selectedDate
                    print(selectedDate)
                    val sdf=SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
                    val theDate=sdf.parse(selectedDate)
                    val seleectedDateMinuts=theDate!!.time/60000
                    val selectToDays=theDate!!.time/86400000
                    val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
                    val currentDateToMinutes=currentDate!!.time/60000
                    val currentDateToDays=currentDate!!.time/86400000
                    val diference=currentDateToMinutes-seleectedDateMinuts
                    val diference2=currentDateToDays-selectToDays

                    txtSetMinute.text=diference.toString()+" minutes"
                    txtSetDays.text=diference2.toString()+" days"
                },year,month,day).show()
        }

    }



}
package vn.edu.lop1k.modulemanagejobservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import vn.edu.lop1k.model.Job;

public class AddActivity extends AppCompatActivity {

    EditText edtTieuDe, edtDeadline, edtId;
    EditText edtNoiDung;

    TextView txtTuyChinh;
    Button btnSetDeadline, btnLuuCV;
    Calendar calendar2=Calendar.getInstance();
    //SimpleDateFormat spdf1=new SimpleDateFormat("HH:mm");
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

    AlarmManager alarmManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnSetDeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyDatGioBatDau();
            }
        });
        btnLuuCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyLuu();
            }
        });

    }

    private void xuLyTuyChinh() {
        //Intent intent= new Intent(AddActivity.this,TuyChinhTimeActivity.class);
        //startActivity(intent);
    }


    private void xuLyLuu() {

        Intent intent= getIntent();

        //Bundle bundle=intent.getBundleExtra("mybud");
        Job job=new Job();
        job.id=1;
        job.Name=edtTieuDe.getText().toString();
        //Date cld=xuLyDatGioBatDau().getTime();
        //edtGioBatDau.setText(dateFormat.format(cld));
        //edtDeadline.setText(dateFormat.format());
        try {

            job.TimeBatDau = dateFormat.parse(edtDeadline.getText().toString());
        } catch (ParseException ex) {
            Log.v("Exception", ex.getLocalizedMessage());
        }

        job.Note=edtNoiDung.getText().toString();
        intent.putExtra("newJob",job);
       // bundle.putSerializable("x",job);
        //intent.putExtra("mybud",bundle);
        setResult(115,intent);
        finish();

        //startActivity(intent);


    }

    private void xuLyDatGioBatDau() {
        TimePickerDialog.OnTimeSetListener callBack=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar2.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar2.set(Calendar.MINUTE,minute);
               // edtGioBatDau.setText(spdf1.format(calendar2.getTime()));
            }
        };
        TimePickerDialog timePickerDialog=new TimePickerDialog(AddActivity.this,callBack,calendar2.get(Calendar.HOUR_OF_DAY),calendar2.get(Calendar.MINUTE),true);
        timePickerDialog.show();
        edtDeadline.setText(dateFormat.format(calendar2.getTime()));
    }

    private void addControls() {
        edtTieuDe=findViewById(R.id.edtTieuDe);
        edtNoiDung=findViewById(R.id.edtNoiDung);
        txtTuyChinh=findViewById(R.id.txtTuyChinh);
        edtDeadline=findViewById(R.id.edtDeadline);
        btnSetDeadline=findViewById(R.id.btnDeadline);
        btnLuuCV=findViewById(R.id.btnLuuCV);



    }
}

package com.course.foodreport;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;

import android.widget.Button;
import android.widget.CalendarView;

import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import android.view.View;

import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
//implements OnMapReadyCallback
public class Writedata extends AppCompatActivity  {

    Button btn_end;
    RadioButton rdo_Cal, rdo_time;
    CalendarView calendar;
    TimePicker timePicker;
    TextView tv_text;
    TextView time_txt;
    DatePickerDialog datePickerDialog;
    Button buttonphoto;
    ImageView photoview;
    Uri uri;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writedata);

        //SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mMap);
        //mapFragment.getMapAsync(this);

        rdo_Cal = findViewById(R.id.rdo_Cal);
        rdo_time = findViewById(R.id.rdo_time);
        tv_text = findViewById(R.id.tv_text);
        final DatePickerDialog[] datePickerDialog = new DatePickerDialog[1];
        time_txt = (TextView)findViewById(R.id.time_txt);
        int hour = 0, min = 0;


        Button buttonphoto = (Button) findViewById(R.id.buttonphoto);
        buttonphoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityResult.launch(intent);
            }
        });

        Button cancle = (Button) findViewById(R.id.cancle);
        cancle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Writedata.this, MainActivity. class);
                startActivity(intent); } });



        rdo_Cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int pYear = calendar.get(Calendar.YEAR);
                int pMonth = calendar.get(Calendar.MONTH);
                int pDay = calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog[0] = new DatePickerDialog(Writedata.this, new DatePickerDialog.OnDateSetListener(){
                    public void onDateSet(DatePicker datePicker, int year, int month, int day){
                        month = month+1;
                        String date = year + "/"+month+"/"+day;
                        tv_text.setText(date);
                    }
                }, pYear, pMonth, pDay);
                datePickerDialog[0].show();
            }
        });
        rdo_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(Writedata.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int phour, int pmin) {
                    time_txt.setText(phour+"시"+pmin+"분");
                    }
                }, hour, min, false);
                timePickerDialog.show();
            }
        });
    }

   /* @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        LatLng dongguk = new LatLng(37.55827, 126.998425);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(dongguk);
        markerOptions.title("동국대학교");
        markerOptions.snippet("지금 있는 곳");
        mMap.addMarker(markerOptions);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(dongguk));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
    }
*/
    ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode()==RESULT_OK&&result.getData() != null){
                uri = result.getData().getData();
                try{
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    photoview.setImageBitmap(bitmap);
                }
                catch(FileNotFoundException e){
                    e.printStackTrace();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }

        }
    });

}
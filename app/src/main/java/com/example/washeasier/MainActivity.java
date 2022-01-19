package com.example.washeasier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnMapa;
    RadioGroup radioGroupRaio;
    RadioButton radioButton1km;
    RadioButton radioButton5km;
    RadioButton radioButton10km;
    RadioButton radioButton15km;
    RadioButton radioButton20km;
    RadioButton radioButtonIlimitado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMapa=findViewById(R.id.btnMapa);
        radioGroupRaio=findViewById(R.id.rgRaio);
        radioButton1km=findViewById(R.id.rb1km);
        radioButton5km=findViewById(R.id.rb5km);
        radioButton10km=findViewById(R.id.rb10km);
        radioButton15km=findViewById(R.id.rb15km);
        radioButton20km=findViewById(R.id.rb20km);
        radioButtonIlimitado=findViewById(R.id.rbIlimitado);

        btnMapa.setOnClickListener(v ->{
            Intent intent= new Intent(this, MapsActivity.class);
            startActivity(intent);
        });

        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }else{
            // Write you code here if permission already given.

            /*btnMapa.setOnClickListener(v ->{
                Intent intent= new Intent(this, MapsActivity.class);
                startActivity(intent);
            });*/
        }
    }

}
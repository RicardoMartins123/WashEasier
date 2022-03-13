package com.example.washeasier;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class MainActivity extends AppCompatActivity {

    private Button btnMapa;
    private RadioGroup rg_raio;
    private RadioButton rb_1km, rb_5km, rb_10km, rb_15km, rb_20km;
    String raioSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rg_raio = findViewById(R.id.rgRaio);
        rb_1km = findViewById(R.id.rb1km);
        rb_5km = findViewById(R.id.rb5km);
        rb_10km = findViewById(R.id.rb10km);
        rb_15km = findViewById(R.id.rb15km);
        rb_20km = findViewById(R.id.rb20km);

        btnMapa=findViewById(R.id.btnMapa);

        rg_raio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(rb_1km.isChecked()){
                    raioSelecionado = "1000";
                } else if(rb_5km.isChecked()){
                    raioSelecionado = "5000";
                } else if(rb_10km.isChecked()){
                    raioSelecionado = "10000";
                } else if(rb_15km.isChecked()){
                    raioSelecionado = "15000";
                } else if(rb_20km.isChecked()){
                    raioSelecionado = "20000";
                } else {
                    raioSelecionado = null;
                }
            }
        });

        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withContext(MainActivity.this)
                        .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                if(raioSelecionado == null){
                                    Toast.makeText(MainActivity.this, "Tem de selecionar um valor", Toast.LENGTH_LONG).show();
                                } else{
                                    Intent intent = new Intent(MainActivity.this,MapsActivity.class);
                                    intent.putExtra("raio_Selecionado", raioSelecionado);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                                if(permissionDeniedResponse.isPermanentlyDenied()){
                                    AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                                    builder.setTitle("Permissão recusada")
                                            .setMessage("Permissão para acessar a localização foi recusada. " +
                                                    "É necessário ir ás definições para ativar a permissão")
                                            .setNegativeButton("Cancelar",null)
                                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    Intent intent=new Intent();
                                                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                                    intent.setData(Uri.fromParts("package", getPackageName(),null));
                                                }
                                            }).show();
                                } else{
                                    Toast.makeText(MainActivity.this, "Permissão recusada", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        })
                        .check();
            }
        });
    }

}
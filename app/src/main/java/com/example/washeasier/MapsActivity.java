package com.example.washeasier;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.washeasier.databinding.ActivityMapsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.maps.android.SphericalUtil;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private PlacesClient placesClient;
    private List<AutocompletePrediction> predictionList;

    private Location mLastNowLocation;
    private LocationCallback locationCallback;

    LatLng myLocation;
    String myLat;
    String myLong;
    private float DEFAULT_ZOOM = 15f;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        fusedLocationProviderClient=LocationServices.getFusedLocationProviderClient(MapsActivity.this);
        Places.initialize(MapsActivity.this,"AIzaSyCEFc4PyQ2b7zj3FmX171b3XHvJSXZY2Yw");
        placesClient=Places.createClient(this);
        AutocompleteSessionToken token= AutocompleteSessionToken.newInstance();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        //verificar se a localização está ativada
        LocationRequest locationRequest= LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder=new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);

        SettingsClient settingsClient= LocationServices.getSettingsClient(MapsActivity.this);
        Task<LocationSettingsResponse> task= settingsClient.checkLocationSettings(builder.build());


        //trazer o raio da main activity
        Intent intent = getIntent();
        String raio = intent.getStringExtra("raio_Selecionado");
        int raioSelecionado = Integer.parseInt(raio);

        //ajustar o zoom conforme o raio
        switch (raioSelecionado){
            case 1000:
                DEFAULT_ZOOM = 16f;
                break;
            case 5000:
                DEFAULT_ZOOM = 15f;
                break;
            case 10000:
                DEFAULT_ZOOM = 14f;
                break;
            case 15000:
                DEFAULT_ZOOM = 13f;
                break;
            case 20000:
                DEFAULT_ZOOM = 12f;
                break;
        }

        task.addOnSuccessListener(MapsActivity.this, new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                getDeviceLocation();
            }
        });
        task.addOnFailureListener(MapsActivity.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if(e instanceof ResolvableApiException){
                    ResolvableApiException resolvable= (ResolvableApiException) e;
                    try {
                        resolvable.startResolutionForResult(MapsActivity.this,51);
                    } catch (IntentSender.SendIntentException sendIntentException) {
                        sendIntentException.printStackTrace();
                    }
                }
            }
        });

        DataBaseHelper dataBaseHelper = new DataBaseHelper(MapsActivity.this);
        List<EstacaoModel> listaEstacao = dataBaseHelper.getEstacao();
        List<Est_ServModel> listaEst_Serv = dataBaseHelper.getEst_Serv();
        List<ServicosModel> listaServicos = dataBaseHelper.getServicos();

        myLocation = new LatLng(37.74921230875358, -25.664910613967244);

        for(int i = 0; i < listaEstacao.size(); i++){
            LatLng estacaoLatlong = new LatLng(listaEstacao.get(i).getEstLat(), listaEstacao.get(i).getEstLong());

            String precoSimples = null;
            String precoEspecial = null;
            String precoExtra = null;

            if(SphericalUtil.computeDistanceBetween(myLocation, estacaoLatlong) < raioSelecionado){

                for(int x = 0; x < listaEst_Serv.size(); x++){
                    for(int y = 0; y < listaServicos.size(); y++) {
                        if (listaEst_Serv.get(x).getEstacaoID() == listaEstacao.get(i).getEstacaoID() && listaEst_Serv.get(x).getServicosID() == 1) {
                            precoSimples = listaEst_Serv.get(x).getPreco();
                        } else if (listaEst_Serv.get(x).getEstacaoID() == listaEstacao.get(i).getEstacaoID() && listaEst_Serv.get(x).getServicosID() == 2) {
                            precoEspecial = listaEst_Serv.get(x).getPreco();
                        } else if (listaEst_Serv.get(x).getEstacaoID() == listaEstacao.get(i).getEstacaoID() && listaEst_Serv.get(x).getServicosID() == 3) {
                            precoExtra = listaEst_Serv.get(x).getPreco();
                        }
                    }
                }
                googleMap.addMarker(new MarkerOptions()
                        .position(estacaoLatlong)
                        .title(listaEstacao.get(i).getNomeEmpresa())
                        .snippet(listaEstacao.get(i).getHorarioServico() +
                                "\n\nServicos: " +
                                "\nSimples - " + precoSimples +
                                "\nEspecial - " + precoEspecial +
                                "\nExtra - " + precoExtra ));
            }
        }

        //marker personalizado para poder mostrar imformação em mais que uma linha
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Nullable
            @Override
            public View getInfoContents(@NonNull Marker marker) {
                LinearLayout info =new LinearLayout(MapsActivity.this);
                info.setOrientation(LinearLayout.VERTICAL);

                TextView title = new TextView(MapsActivity.this);
                title.setTextColor(Color.BLACK);
                title.setGravity(Gravity.CENTER);
                title.setTypeface(null, Typeface.BOLD);
                title.setText(marker.getTitle());

                TextView snippet = new TextView(MapsActivity.this);
                snippet.setTextColor(Color.GRAY);
                snippet.setText(marker.getSnippet());

                info.addView(title);
                info.addView(snippet);

                return info;
            }

            @Nullable
            @Override
            public View getInfoWindow(@NonNull Marker marker) {
                return null;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==51){
            if(resultCode== RESULT_OK){
                getDeviceLocation();
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void getDeviceLocation(){
        fusedLocationProviderClient.getLastLocation()
                .addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if(task.isSuccessful()){
                            mLastNowLocation= task.getResult();
                            if(mLastNowLocation!=null){
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLastNowLocation.getLatitude(),
                                        mLastNowLocation.getLongitude()),DEFAULT_ZOOM));
                            } else {
                                LocationRequest locationRequest=LocationRequest.create();
                                locationRequest.setInterval(10000);
                                locationRequest.setFastestInterval(5000);
                                locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                                locationCallback=new LocationCallback() {
                                    @Override
                                    public void onLocationResult(@NonNull LocationResult locationResult) {
                                        super.onLocationResult(locationResult);
                                        if(locationResult == null){
                                            return;
                                        }
                                        mLastNowLocation=locationResult.getLastLocation();
                                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLastNowLocation.getLatitude(),
                                                mLastNowLocation.getLongitude()),DEFAULT_ZOOM));
                                        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
                                    }
                                };
                                fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
                            }
                        } else{
                            Toast.makeText(MapsActivity.this, "Não foi possivel obter a localização", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
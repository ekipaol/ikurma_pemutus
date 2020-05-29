package com.application.bris.brisi_pemutus.map_agunan;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.SupportMapFragment;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    private Button konfirmasi;
    LatLng loc;
    String latitude,longitude;
    Marker marker;
    RelativeLayout mapView;
    int statusLokasi=0;
    int statusMyLocation=0;
    RelativeLayout loading;
    ApiClientAdapter apiClientAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        konfirmasi=findViewById(R.id.bt_konfirmasi_map);
        loading=findViewById(R.id.progressbar_loading);
        mapView=findViewById(R.id.rl_maps);

        loading.setVisibility(View.VISIBLE);
        apiClientAdapter=new ApiClientAdapter(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mapView.setVisibility(View.VISIBLE);

        // Add a marker in Sydney and move the camera
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
konfirmasi.setVisibility(View.INVISIBLE);

        mMap.setMyLocationEnabled(true);
        loading.setVisibility(View.INVISIBLE);
//        LatLng sydney = new LatLng(-34, 151);
//
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                 loc = new LatLng(location.getLatitude(), location.getLongitude());
                 konfirmasi.setVisibility(View.VISIBLE);
              // Marker current= mMap.addMarker(new MarkerOptions().position(loc));
                if(mMap != null &&statusMyLocation<1){
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 16.0f));
                    statusMyLocation=1;
                }

                //Toast.makeText(MapsActivity.this, "Latitude : "+loc.latitude, Toast.LENGTH_SHORT).show();
            }
        });

        konfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(statusLokasi>=1){
                    setResult(Activity.RESULT_OK,
                            new Intent().putExtra("latitude", latitude).putExtra("longitude", longitude));
                }
                else{
                    setResult(Activity.RESULT_OK,
                            new Intent().putExtra("latitude", Double.toString(loc.latitude)).putExtra("longitude", Double.toString(loc.longitude)));
                }

                finish();
            }
        });


        //onclick keterangan
        final TextView keterangan_map=findViewById(R.id.tv_keterangan_map);
        keterangan_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keterangan_map.setVisibility(View.GONE);
            }
        });
        //end of onclick keterangan

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if(marker!=null){
                    marker.remove();
                }
                  marker=mMap.addMarker(new MarkerOptions().position(latLng).title("Lokasi Agunan"));
                marker.setDraggable(true);
                setLatitudeLongitude(Double.toString(latLng.latitude),Double.toString(latLng.longitude));

                statusLokasi=1;
                Toast.makeText(MapsActivity.this, "Klik tahan pada marker untuk menggeser marker", Toast.LENGTH_SHORT).show();
            }
        });

        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                mMap.animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
                setLatitudeLongitude(Double.toString(marker.getPosition().latitude),Double.toString(marker.getPosition().longitude));
            }
        });
    }

    private void setLatitudeLongitude(String latitude,String longitude){
        this.latitude=latitude;
        this.longitude=longitude;
    }


}

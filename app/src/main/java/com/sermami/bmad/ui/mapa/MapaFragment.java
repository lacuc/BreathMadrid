package com.sermami.bmad.ui.mapa;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class MapaFragment extends SupportMapFragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Location miLoc;
    private FusedLocationProviderClient flClient;
    static final String NIVEL_BAJO = "Nivel de Contaminación: BAJO";
    static final String NIVEL_MEDIO = "Nivel de Contaminación: MEDIO";
    static final String NIVEL_ALTO = "Nivel de Contaminación: ALTO";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View vista = super.onCreateView(inflater, container, savedInstanceState);

        flClient = LocationServices.getFusedLocationProviderClient(getContext());

        flClient.getLastLocation().addOnSuccessListener((Activity) getContext(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    miLoc =location;
                }
            }
        });

        getMapAsync(this);

        return vista;
    }

    public void crearEstacion(String titulo, double lat, double lon, String nivel){
        LatLng ubi = new LatLng(lat, lon);
        if(nivel.equals(NIVEL_ALTO)) {
            mMap.addMarker(new MarkerOptions()
                    .position(ubi)
                    .title(titulo)
                    .snippet(nivel)
                    .icon(BitmapDescriptorFactory.defaultMarker(
                            BitmapDescriptorFactory.HUE_RED)));
        } else if(nivel.equals(NIVEL_MEDIO)) {
            mMap.addMarker(new MarkerOptions()
                    .position(ubi)
                    .title(titulo)
                    .snippet(nivel)
                    .icon(BitmapDescriptorFactory.defaultMarker(
                            BitmapDescriptorFactory.HUE_YELLOW)));
        } else {
            mMap.addMarker(new MarkerOptions()
                    .position(ubi)
                    .title(titulo)
                    .snippet(nivel)
                    .icon(BitmapDescriptorFactory.defaultMarker(
                            BitmapDescriptorFactory.HUE_GREEN)));
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //mMap.setMyLocationEnabled(true);

        crearEstacion("CUATRO CAMINOS",40.446935, -3.704126 , NIVEL_ALTO);
        crearEstacion("ESTACIÓN PLAZA DEL CARMEN",40.418588, -3.703372 , NIVEL_MEDIO);
        crearEstacion("ARTURO SORIA",40.446935, -3.704126 , NIVEL_ALTO);
        crearEstacion("PLAZA ELÍPTICA",40.384671, -3.718348 , NIVEL_MEDIO);
        crearEstacion("RETIRO", 40.421613, -3.685868 , NIVEL_ALTO);
        crearEstacion("VILLAVERDE", 40.353705, -3.705519, NIVEL_BAJO);
        crearEstacion("EL PARDO", 40.519941, -3.775808 , NIVEL_BAJO);
        crearEstacion("PLAZA DE CASTILLA", 40.466918, -3.689013 , NIVEL_ALTO);
        crearEstacion("SANCHINARRO", 40.486312, -3.663322 , NIVEL_BAJO);
        crearEstacion("CASTELLANA", 40.435448, -3.688511 , NIVEL_ALTO);
        crearEstacion("MÉNDEZ ÁLVARO", 40.396355, -3.678455 , NIVEL_MEDIO);
        crearEstacion("CASA DE CAMPO", 40.40438, -3.760724 , NIVEL_BAJO);
        crearEstacion("PUENTE DE VALLECAS", 40.382863, -3.667758 , NIVEL_BAJO);
        crearEstacion("ESTACIÓN DE VALLECAS", 40.382822, -3.624861 , NIVEL_MEDIO);
        crearEstacion("MAHADAONDA", 40.474949, -3.868411 , NIVEL_BAJO);
        crearEstacion("MONCLOA", 40.436302, -3.719027 , NIVEL_BAJO);


        LatLng camara = new LatLng(40.416725, -3.703484);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(camara, 11));

        //mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        mMap.getUiSettings().setZoomControlsEnabled(true);
        //mMap.setMyLocationEnabled(true);
    }

}
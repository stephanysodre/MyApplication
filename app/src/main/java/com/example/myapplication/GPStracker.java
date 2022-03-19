package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;

public class GPStracker implements LocationListener {
    Context context;
    //metodo publico para passar a informaçao do context
    public GPStracker(Context c){
        context = c;
    }

    //procuara geoposicionamento
    public Location getLocation(){
        //ultilizar o metodo para que vc possa ter permiçao para fazer a localizaçao solicitada
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) !=
        PackageManager.PERMISSION_GRANTED){
            //retorno de forma nula se n aparecer permiçao,
            Toast.makeText(context, "Nao foi permitido", Toast.LENGTH_SHORT).show();
            return  null;
        }
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnebled  = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        //gps acionado requer localizar sempre a onde vc esta
        if (isGPSEnebled){
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10, this);
            Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return l;
        }else {
            //janela de popup que pede para abilitar o gps se vc n permitiu
            Toast.makeText(context, "Por favor, habilitar GPS!", Toast.LENGTH_LONG).show();
        }
        //retorna ao nulo
        return null;
    }

    //n funcionou
//    @Override
//    public void onProviderDisable(@NonNull String Provider){ }

    //metodo de super classe de localizaçao
    @Override
    public void  onLocationChanged(@NonNull Location location) { }
    //metodo de super classe de localizaçao
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) { }
}

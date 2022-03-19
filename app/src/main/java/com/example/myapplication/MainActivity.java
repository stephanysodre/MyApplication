package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
// classes criadas
    private ImageView imageViewFoto;
    private Button btnGeo;

    //sobre escrevendo
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //pedir permisao e funcionamento do botao gps
        btnGeo = (Button) findViewById(R.id.botao_gps);
        ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION},123);

        //acionar o boato ao clicar
        btnGeo.setOnClickListener(new View.OnClickListener() {
            // metodo que executa
            @Override
            public void onClick(View view) {
              GPStracker g = new GPStracker(getApplication());
              Location l = g.getLocation();

              //dento da localizaçao exite latitude e longitdude (chamou o gps ao acionar o botao)
                if(l !=null){
                    double lat = l.getLatitude();
                    double lon = l.getLongitude();
                    Toast.makeText(getApplicationContext(),"LATITUDE" + "\n LONGITUDE" + lon, Toast.LENGTH_LONG).show();

                }
            }
        });
        //manifestar o pedido para checar permiçao de camera
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, 0);
        }
        //indentificar a imagem q tem na tela view e endentificaçao do botao foto
        imageViewFoto = (ImageView) findViewById(R.id.image_foto);
        findViewById(R.id.botao_foto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tirarFoto();
            }
        });
    }
    //intençao de captura da midia
    private void tirarFoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,1);
    }
    //ultimo procedimento...
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        //chamar o metodo para o data(imagem q ta tirando foto)
        if (requestCode == 1 && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imagem = (Bitmap) extras.get("data");
            imageViewFoto.setImageBitmap(imagem);
        }
        super.onActivityResult(requestCode,resultCode,data);
    }
}

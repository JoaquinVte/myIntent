package com.jovialsa.myintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class FotoActivity extends AppCompatActivity {

    private String mRespuesta = "No se pudo tomar foto";
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private Button mVolverButton;
    private ImageView mFotoImageView;
    private ImageButton mFotoImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);

        inicializar();
        setOnClickListeners();
    }

    private void inicializar() {

        mVolverButton = (Button) findViewById(R.id.volverButton);
        mFotoImageView = (ImageView) findViewById(R.id.fotoImageView);
        mFotoImageButton = (ImageButton) findViewById(R.id.fotoImageButton);

    }

    private void setOnClickListeners() {

        mVolverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                i.putExtra("RESPUESTA_FOTO", mRespuesta);
                setResult(RESULT_OK, i);
                finish();
            }
        });

        mFotoImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
    }//Fin de onCreate

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap thumbnail = data.getParcelableExtra("data");
            mFotoImageView.setImageBitmap(thumbnail);
            mRespuesta = "Foto tomada";
        }
    }

}


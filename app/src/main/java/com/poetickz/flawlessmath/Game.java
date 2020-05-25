package com.poetickz.flawlessmath;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.UUID;

public class Game extends AppCompatActivity {

    private Button erase, save;
    private PaintView paintView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        paintView = (PaintView) findViewById(R.id.drawing);
        erase = (Button) findViewById(R.id.erase_btn);
        save = (Button) findViewById(R.id.save_btn);
    }


    public void onClick(View v)  {

        if(v.getId()==R.id.erase_btn){
            paintView.setErase(true);
        }

        if(v.getId()==R.id.save_btn){

            paintView.setDrawingCacheEnabled(true);
            String imgSaved = MediaStore.Images.Media.insertImage(
                    getContentResolver(), paintView.getDrawingCache(),
                    UUID.randomUUID().toString()+".png", "drawing");
            if(imgSaved!=null){
                Toast savedToast = Toast.makeText(getApplicationContext(),
                        "Drawing saved to Gallery!", Toast.LENGTH_SHORT);
                savedToast.show();
            }
            else{
                Toast unsavedToast = Toast.makeText(getApplicationContext(),
                        "Oops! Image could not be saved.", Toast.LENGTH_SHORT);
                unsavedToast.show();
            }
            paintView.destroyDrawingCache();
        }
    }
}

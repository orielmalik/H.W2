package com.example.hw2orielmalik322985441;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.button.MaterialButton;

public class MenuActivity extends AppCompatActivity {
    MaterialButton mb,fb ,sb,rb;
    final private   int f=2,s=9;
    MainActivity m;
    private Intent scoreIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
       // m=new MainActivity();

        mb= new MaterialButton(this);
        mb=findViewById(R.id.game_BTN_yes);
        fb= new MaterialButton(this);
        fb=findViewById(R.id.game_BTN_no);
        sb= new MaterialButton(this);

        sb=findViewById(R.id.game_BTN);
        MakeAction();;
        MakeActionFast();;
        MakeSensorMode();
       rb= new MaterialButton(this);
        rb=findViewById(R.id.game_REC);
MaketoRecordTable();

    }

    private void MakeAction()
    {
        mb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openButtonScreen(s,false);

            }
        });
    }
    private void MakeSensorMode()
    {
        sb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openButtonScreen(f,true);
               // m.isSensorMode(true);
            }
        });
    }
    private void MakeActionFast()
    {
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openButtonScreen(f,false);

            }
        });
    }

    private  void  MaketoRecordTable()
    {
        rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toRecordTable();
            }
        });
    }


private  void  toRecordTable()
{
    Bundle Abundle=new Bundle();
     Abundle = getIntent().getBundleExtra("Scorebundle");
    Intent Aintent = new Intent(this, Activity_Panel.class);
    Aintent.putExtra("Scorebundle",Abundle);
    startActivity(Aintent);

}



    private void openButtonScreen(int k,boolean sen) {
      //m.setFactorDeLAY(k);
         Bundle bundle = new Bundle();
        Intent intent = new Intent(this, MainActivity.class);

       bundle.putInt("factorDELAY",k);
        bundle.putBoolean("sen", sen);
        Log.d( "openButtonScreen: ",""+ bundle.getBoolean("sen"));
        intent.putExtra("bundle",bundle);
        Log.d( "interButtonScreen: ",""+ intent.getExtras().getBoolean("sen"));

        startActivity(intent);

//m.isSensorMode(Intent,"sensor");
        startActivity(intent);
        // finish();
    }
 /*   private void refreshUI() {
        if (gameManager.isLose()) {
            openScoreScreen("Game Over", gameManager.getScore());
        }
    }*/







}
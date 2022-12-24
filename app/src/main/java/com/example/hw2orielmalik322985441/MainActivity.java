package com.example.hw2orielmalik322985441;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private ExtendedFloatingActionButton efl, efr;
    private ImageView h1, h2, h3, thor1, t2, t3;//t2-left
    private ImageView[][] rocks;
    private ImageView[] Thor, Thunders;
    //private Intent intent;
    //private Bundle bundle;
    private int stepsX = 0, stepsY = 0;
    private int[][] arr;// to attach th ImagView Matrix
    private int[] place;//place of matrix
    private int click = 1, index = 1, clicked = 0;
    final int DELAY = 1000;
    private ImageView[] hearts;
    private StepDetector stepDetector;
    private boolean sen, threeHearts = false,thundervis=false;
    public static int SensorSaver = 0;
    private stepCallback callBack_steps;
    private long startTime = 0;
    private  Manager manager;
    private int sensorPlace = 0, Main_LBL_score = 0, factorDELAY = 1, colB = -1;
    private static final String SP_KEY_NAME = "SP_KEY_NAME";
    private static final String SP_KEY_SCORE = "SP_KEY_SCORE";
    private static final String SP_KEY_PLAYLIST = "SP_KEY_PLAYLIST";
    // private Backgroundsound mBackgroundSound;
    private TextView ScoreLBL;
    BackgroundSound mBackgroundSound;

    String playlistAsJsonStringFromSP=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.rocks = new ImageView[4][5];
        this.arr = new int[3][3];
        clicked = rocks.length / 2;
        ScoreLBL = new TextView(this);
        ScoreLBL = findViewById(R.id.main_Score);
        Thunders = new ImageView[5];
manager=new Manager();
        startMatrix();
        startTimer();


        Thor = new ImageView[5];
        for (int j = 0; j < Thor.length; j++) {
            Thor[j] = new ImageView(this);
        }

        findViews();
        MakeActiotoLeft();
        MakeActiotoRight();
        sensorPlace = Thor.length / 2;


        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");

        factorDELAY = bundle.getInt("factorDELAY");
        sen = bundle.getBoolean("sen");

        isSensorMode(sen);
        initStepDetector();
        // setFactorDeLAY(getIntent().getExtras().getInt("factorDELAY"));

    }

    final Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, factorDELAY * DELAY); //do it again in a second.
            //updateRocks(rocks,3,3,target);
            // updateTimerUI();
            obstacles(rocks);
            MinusHearet(colB);



        }
    };

    private void findViews() {
        efl = findViewById(R.id.main_BTN_left);
        efr = findViewById(R.id.main_BTN_right);

        Thor[1] = findViewById(R.id.ic_player2);
        Thor[0] = findViewById(R.id.ic_player1);
        Thor[2] = findViewById(R.id.ic_player3);
        Thor[3] = findViewById(R.id.ic_player4);
        Thor[4] = findViewById(R.id.ic_player5);

        Thunders[0] = findViewById(R.id.thunder1);
        Thunders[1] = findViewById(R.id.thunder2);
        Thunders[2] = findViewById(R.id.thunder3);
        Thunders[3] = findViewById(R.id.thunder4);
        Thunders[4] = findViewById(R.id.thunder5);

    }

    private void MakeActiotoLeft() {
        efr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                clicked--;

                checkDuo(clicked);

            }
        });
    }


    private void clicked(boolean answer) {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

    }

    private void toast(String w) {
        Toast
                .makeText(this, w, Toast.LENGTH_LONG)
                .show();

    }

    private void MakeActiotoRight() {
        efl.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                clicked++;

                checkDuo(clicked);

            }
        });
    }

    private void visi(ImageView a, ImageView v) {//to prevent duplicate code
        a.setVisibility(View.INVISIBLE);
        v.setVisibility(View.INVISIBLE);

    }

    private void checkDuo(int place) {
        int p = place;
        if (place < 0) {
            place = 0;
        } else if (place > 4) {
            place = 4;

        }
        for (int i = 0; i < Thor.length; i++) {
            Thor[i].setVisibility(View.INVISIBLE);
        }
        Thor[(place)].setVisibility(View.VISIBLE);
    }

    public void startMatrix() {
        for (int i = 0; i < rocks.length; i++) {
            for (int j = 0; j < rocks[0].length; j++) {
                rocks[i][j] = new ImageView(this);

            }

        }
        rocks = new ImageView[][]{
                {findViewById(R.id.a0), findViewById(R.id.a1), findViewById(R.id.a2), findViewById(R.id.a3), findViewById(R.id.a4)},
                {findViewById(R.id.a5), findViewById(R.id.a6), findViewById(R.id.a7), findViewById(R.id.a8), findViewById(R.id.a9)},
                {findViewById(R.id.a15), findViewById(R.id.a16), findViewById(R.id.a17), findViewById(R.id.a18), findViewById(R.id.a19)},
                {findViewById(R.id.a20), findViewById(R.id.a21), findViewById(R.id.a22), findViewById(R.id.a23), findViewById(R.id.a24)}

        };
        hearts = new ImageView[3];
        hearts[0] = findViewById(R.id.ic_heart1);
        hearts[1] = findViewById(R.id.ic_heart2);
        hearts[2] = findViewById(R.id.ic_heart3);

        ImageView v = new ImageView(this);
        v.findViewById(R.id.a15);

        v.setImageResource(R.drawable.coinbit);
        v.setVisibility(View.VISIBLE);

        //   rocks[(int)(Math.random() * (rocks.length-1))][(int)(Math.random() * rocks[0].length-1)]=(ImageView) v;


    }


    public void stopTimer() {
        handler.removeCallbacks(runnable);
    }

    public void startTimer() {
        startTime = System.currentTimeMillis();
        handler.postDelayed(runnable, DELAY);

    }

    public void obstacles(ImageView[][] rocks) {
        int val = rocks.length - 1;
        int sel = rocks.length - 1, k = (int) (Math.random() * rocks[0].length), bk = (int) (Math.random() * rocks[0].length);
        place = new int[3];

        rocks[(int) (Math.random() * (rocks.length - 1))][(int) (Math.random() * rocks[0].length - 1)].setVisibility(View.VISIBLE);
        rocks[(int) (Math.random() * (rocks.length - 2))][(int) (Math.random() * rocks[0].length - 1)].setVisibility(View.VISIBLE);
        rocks[(int) (Math.random() * (rocks.length - 2))][(int) (Math.random() * rocks[0].length - 1)].setVisibility(View.VISIBLE);


        for (int i = rocks.length - 1; i > 0; i--) {
            for (int j = 1; j < rocks[0].length; j++) {
                rocks[i][j].setVisibility(rocks[i - 1][j - 1].getVisibility());
                ;
                rocks[i - 1][j - 1].setVisibility(View.INVISIBLE);
                if (rocks[i][j - 1].getVisibility() == View.VISIBLE && rocks[i][j].getVisibility() == View.VISIBLE) {
                    rocks[i][j - 1].setVisibility(View.INVISIBLE);

                }
                int col = (int) (Math.random() * rocks[0].length - 1);
                int row = (int) (Math.random() * (rocks.length - 1));
                rocks[row][col].setImageResource(R.drawable.coinbit);
                if (row == rocks.length - 1) {
                    colB = col;
                }

            }

        }

    }


    private int h = 0;

    private void MinusHearet(int t) {

        // for (int r = 0; r < rocks.length; r++) {
        // for (int g = 0; g < rocks[0].length; g++) {
        for (int i = 0; i < Thor.length; i++) {


            if (rocks[rocks.length - 1][i].getVisibility() == View.VISIBLE && Thor[i].getVisibility() == View.VISIBLE) {
                Thunders[i].setVisibility(View.VISIBLE);
                thundervis=true;
                hearts[h].setVisibility(View.INVISIBLE);
                //  toast("h--");
                //Thunders[i].setVisibility(View.INVISIBLE);


                if (h == hearts.length - 1) {

                    threeHearts = true;
                } else {
                    h++;
                }


                //Thunders[h].setVisibility(View.INVISIBLE);


                //  threeHearts = true;
                if (t == i) {

                    Main_LBL_score *= 2;
                }

            } else if (rocks[rocks.length - 1][i].getVisibility() != View.VISIBLE && Thor[i].getVisibility() == View.VISIBLE) {
                Main_LBL_score++;
            }

        }

        // }
        // }

        this.ScoreLBL.setText(String.valueOf(Main_LBL_score));
        thundervis=false;

    }

    int in = 0;

    @Override
    protected void onResume() {
        super.onResume();
        // mBackgroundSound = new Backgroundsound(this);
        // mBackgroundSound.execute();
        if (sen) {
            stepDetector.start();

        }

        mBackgroundSound = new BackgroundSound();
        if(thundervis) {
            mBackgroundSound.execute();
        }
        manager.getScore().add( Main_LBL_score);
       /* String playlistJson = new Gson().toJson(manager);
        Log.d("JSON", playlistJson);
        MySPV3.getInstance().putString(SP_KEY_PLAYLIST, playlistJson);
        playlistAsJsonStringFromSP = MySPV3.getInstance().getString(SP_KEY_PLAYLIST, "");*/
    }

    //String playlistAsJsonStringFromSP = MySPV3.getInstance().getString(SP_KEY_PLAYLIST+String.valueOf(in++), "");
    @Override
    protected void onPause() {
        super.onPause();
        // mBackgroundSound.cancel(true);
        stepDetector.stop();
       // stopTimer();
       // ScoreIntent(Main_LBL_score, threeHearts, playlistAsJsonStringFromSP);
        mBackgroundSound.cancel(true);
        if (threeHearts) {
            //stopTimer();
           // MySPV3.getInstance().putInt(SP_KEY_PLAYLIST, Main_LBL_score);
           // playlistAsJsonStringFromSP = MySPV3.getInstance().getString(SP_KEY_PLAYLIST, "");
            ScoreIntent(Main_LBL_score, threeHearts,playlistAsJsonStringFromSP);
        }
    }


    public void ScoreIntent(int Score, boolean b, String playlistAsJsonStringFromSP) {
        Intent Scoreintent = new Intent(this, Activity_Panel.class);
        Bundle Scorebundle = new Bundle();

        if (Scorebundle != null && Scoreintent != null && b) {


           /* Manager playlistFromSP = new Gson().fromJson(playlistAsJsonStringFromSP, Manager.class);
            Log.d("playlistFromSP", playlistFromSP.toString());

            playlistFromSP.getScore().add(Score);
            Log.d( "run: ",""+  playlistFromSP.getScore().add(Score));
*/
            Scorebundle.putInt("Score",Score);
            Scoreintent.putExtra("Scorebundle", Scorebundle);


            startActivity(Scoreintent);
            stopTimer();
        }
    }


    public void isSensorMode(boolean b) {
        if (b) {
            efl.setVisibility(View.GONE);
            efr.setVisibility(View.GONE);
            //Log.d( "isSensorMode: ",""+stepDetector.getStepCountX());
//this.callBack_steps.stepX();;
        }


    }

    // Playlist playlist2 = new Gson().fromJson(/*json2, Playlist.class*/);

    //  String json = new Gson().toJson(playlist);
    private void checkSensorDuo(int place) {
        if (place >0&&clicked<Thor.length-1&&clicked>=0) {
            clicked++;
        } else if(clicked>0&&clicked<Thor.length-1&&place<0)  {
            clicked--;
        }

        for (int i = 0; i < Thor.length; i++) {
            Thor[i].setVisibility(View.INVISIBLE);
        }


        Thor[clicked].setVisibility(View.VISIBLE);


    }

    private void initStepDetector() {
        stepDetector = new StepDetector(this, new stepCallback() {

            @Override
            public void stepX() {
                // checkDuo( (stepDetector.getStepCountX()));

                Log.d("isSensorMode: ", "" + stepDetector.getStepCountX());


                    checkSensorDuo(stepDetector.getStepCountX());


            }

            @Override
            public void stepY() {
                //  checkDuo (stepDetector.stepCounter()%Thor.length);
                checkDuo(stepDetector.getStepCountX());
            }
        });
    }
    public class BackgroundSound extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            MediaPlayer player = MediaPlayer.create(MainActivity.this, R.raw.msc_yemen_music);
            player.setLooping(true); // Set looping
            player.setVolume(1.0f, 1.0f);
            player.start();

            return null;
        }

    }
}







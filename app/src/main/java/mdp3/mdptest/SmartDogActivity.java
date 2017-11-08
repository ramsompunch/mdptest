package mdp3.mdptest;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by JBTS-02-05 on 2017-10-20.
 */

public class SmartDogActivity extends AppCompatActivity {

    private int count = 0;
    private int num = 0;
    private int wnum = 0;
    private String quan;
    private GestureDetectorCompat gestureDetectorCompat;
    private GestureDetectorCompat gestureDetectorCompat2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smartdog_main);

        Button web = (Button)findViewById(R.id.webcon);
        Button dog_rigmenubtn = (Button) findViewById(R.id.RIG_menubtndog);
        ImageButton manual = (ImageButton) findViewById(R.id.manual);
        Button dog_lfmenubtn = (Button) findViewById(R.id.LF_menubtndog);
        ImageButton dog_feed = (ImageButton) findViewById(R.id.dogfeed);

        gestureDetectorCompat = new GestureDetectorCompat(this, new SmartDogActivity.MyGestureListener());
        gestureDetectorCompat2 = new GestureDetectorCompat(this, new SmartDogActivity.My2ndGestureListener());


        dog_feed.setOnClickListener(
                new Button.OnClickListener() {
                    RadioButton a3 = (RadioButton) findViewById(R.id.a3);
                    RadioButton a2 = (RadioButton) findViewById(R.id.a2);
                    RadioButton a1 = (RadioButton) findViewById(R.id.a1);
                    RadioButton a0 = (RadioButton) findViewById(R.id.a0);
                    RadioButton b3 = (RadioButton) findViewById(R.id.b3);
                    RadioButton b2 = (RadioButton) findViewById(R.id.b2);
                    RadioButton b1 = (RadioButton) findViewById(R.id.b1);
                    RadioButton b0 = (RadioButton) findViewById(R.id.b0);
                    RadioButton c3 = (RadioButton) findViewById(R.id.c3);
                    RadioButton c2 = (RadioButton) findViewById(R.id.c2);
                    RadioButton c1 = (RadioButton) findViewById(R.id.c1);
                    RadioButton c0 = (RadioButton) findViewById(R.id.c0);
                    RadioButton w1 = (RadioButton) findViewById(R.id.w1);
                    RadioButton w0 = (RadioButton) findViewById(R.id.w0);
                    RadioGroup rd1 = (RadioGroup) findViewById(R.id.dogfeedrd1);
                    RadioGroup rd2 = (RadioGroup) findViewById(R.id.dogfeedrd2);
                    RadioGroup rd3 = (RadioGroup) findViewById(R.id.dogfeedrd3);
                    RadioGroup rd4 = (RadioGroup) findViewById(R.id.dogfeedrd4);

                    public void onClick(View v) {
                        if (a3.isChecked()) num = num + 3;
                        if (b3.isChecked()) num = num + 3;
                        if (c3.isChecked()) num = num + 3;
                        if (a2.isChecked()) num = num + 2;
                        if (b2.isChecked()) num = num + 2;
                        if (c2.isChecked()) num = num + 2;
                        if (a1.isChecked()) num = num + 1;
                        if (b1.isChecked()) num = num + 1;
                        if (c1.isChecked()) num = num + 1;
                        if (w1.isChecked()) wnum = wnum + 2;
                        if (w0.isChecked()) wnum = wnum + 1;
                        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(SmartDogActivity.this);
                        alert_confirm.setMessage("배급 하시겠습니까?").setCancelable(false).setPositiveButton("취소",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }

                                }).setNegativeButton("확인",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (num > 3) {
                                            setToast("밥 양이 너무 많습니다.메뉴얼을 참고해 주세요");
                                            num = 0;
                                            wnum = 0;
                                        } else if (wnum == 0) {
                                            setToast("물을 선택하세요");
                                            num = 0;
                                            wnum = 0;
                                        } else {
                                            //sendThread
                                            setToast("밥 주는중입니다.");
                                            if (wnum == 2) {
                                                if (num == 3) quan = "많고";
                                                if (num == 2) quan = "보통이고";
                                                if (num == 1) quan = "적고";
                                                setToast("사료양은 " + "[" + quan + "]" + " 물을 줍니다.");
                                                num = 0;
                                            }
                                            if (wnum == 1) {
                                                if (num == 3) quan = "많고";
                                                if (num == 2) quan = "보통이고";
                                                if (num == 1) quan = "적고";
                                                setToast("사료양은 " + "[" + quan + "]" + " 물을 주지 않습니다.");
                                                num = 0;
                                            }
                                            wnum = 0;
                                            rd1.clearCheck();
                                            rd2.clearCheck();
                                            rd3.clearCheck();
                                            rd4.clearCheck();


                                        }
                                    }
                                });
                        AlertDialog alert = alert_confirm.create();
                        alert.show();
                    }
                });
        manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SmartDogActivity.this, ManualActivity.class);
                startActivity(intent);
            }
        });


        dog_rigmenubtn.setOnClickListener(
                new ImageButton.OnClickListener() {
                    ScalableLayout Menudog = (ScalableLayout) findViewById(R.id.menu);
                    ScalableLayout DataBase = (ScalableLayout) findViewById(R.id.DataBasedog);
                    ScalableLayout dogctrl1 = (ScalableLayout) findViewById(R.id.dogctrl1);
                    ScalableLayout dogctrl2 = (ScalableLayout) findViewById(R.id.dogctrl2);
                    ScalableLayout dogctrl3 = (ScalableLayout) findViewById(R.id.dogctrl3);
                    Button LF_menudog = (Button) findViewById(R.id.LF_menubtndog);
                    Button RIG_menudog = (Button) findViewById(R.id.RIG_menubtndog);
                    TextView statusdog = (TextView) findViewById(R.id.statusdog);


                    public void onClick(View v) {
                        count++;
                        if (count == 5) count = 4;

                        if (count == 0) {
                            Menudog.setVisibility(View.VISIBLE);
                            DataBase.setVisibility(View.GONE);
                            dogctrl1.setVisibility(View.GONE);
                            dogctrl2.setVisibility(View.GONE);
                            dogctrl3.setVisibility(View.GONE);
                            RIG_menudog.setVisibility(View.VISIBLE);
                            LF_menudog.setVisibility(View.GONE);
                            statusdog.setText("Menu");
                        } else if (count == 1) {
                            Menudog.setVisibility(View.GONE);
                            DataBase.setVisibility(View.GONE);
                            dogctrl1.setVisibility(View.VISIBLE);
                            dogctrl2.setVisibility(View.GONE);
                            dogctrl3.setVisibility(View.GONE);
                            LF_menudog.setVisibility(View.VISIBLE);
                            RIG_menudog.setVisibility(View.VISIBLE);
                            statusdog.setText("DogCtrl1");
                        } else if (count == 2) {
                            Menudog.setVisibility(View.GONE);
                            DataBase.setVisibility(View.GONE);
                            dogctrl1.setVisibility(View.GONE);
                            dogctrl2.setVisibility(View.VISIBLE);
                            dogctrl3.setVisibility(View.GONE);
                            LF_menudog.setVisibility(View.VISIBLE);
                            RIG_menudog.setVisibility(View.VISIBLE);
                            statusdog.setText("DogCtrl2");
                        } else if (count == 3) {
                            Menudog.setVisibility(View.GONE);
                            DataBase.setVisibility(View.GONE);
                            dogctrl1.setVisibility(View.GONE);
                            dogctrl2.setVisibility(View.GONE);
                            dogctrl3.setVisibility(View.VISIBLE);
                            LF_menudog.setVisibility(View.VISIBLE);
                            RIG_menudog.setVisibility(View.VISIBLE);
                            statusdog.setText("DogCtrl3");
                        } else if (count == 4) {
                            Menudog.setVisibility(View.GONE);
                            DataBase.setVisibility(View.VISIBLE);
                            dogctrl1.setVisibility(View.GONE);
                            dogctrl2.setVisibility(View.GONE);
                            dogctrl3.setVisibility(View.GONE);
                            LF_menudog.setVisibility(View.VISIBLE);
                            statusdog.setText("DataBase");
                            RIG_menudog.setVisibility(View.GONE);
                        }
                    }
                });
        dog_lfmenubtn.setOnClickListener(
                new ImageButton.OnClickListener() {

                    ScalableLayout Menudog = (ScalableLayout) findViewById(R.id.menu);
                    ScalableLayout DataBase = (ScalableLayout) findViewById(R.id.DataBasedog);
                    ScalableLayout dogctrl1 = (ScalableLayout) findViewById(R.id.dogctrl1);
                    ScalableLayout dogctrl2 = (ScalableLayout) findViewById(R.id.dogctrl2);
                    ScalableLayout dogctrl3 = (ScalableLayout) findViewById(R.id.dogctrl3);
                    Button LF_menudog = (Button) findViewById(R.id.LF_menubtndog);
                    Button RIG_menudog = (Button) findViewById(R.id.RIG_menubtndog);
                    TextView statusdog = (TextView) findViewById(R.id.statusdog);

                    public void onClick(View v) {
                        count--;
                        if (count == -1) count = 0;

                        if (count == 0) {
                            Menudog.setVisibility(View.VISIBLE);
                            DataBase.setVisibility(View.GONE);
                            dogctrl1.setVisibility(View.GONE);
                            dogctrl2.setVisibility(View.GONE);
                            dogctrl3.setVisibility(View.GONE);
                            RIG_menudog.setVisibility(View.VISIBLE);
                            LF_menudog.setVisibility(View.GONE);
                            statusdog.setText("Menu");
                        } else if (count == 1) {
                            Menudog.setVisibility(View.GONE);
                            DataBase.setVisibility(View.GONE);
                            dogctrl1.setVisibility(View.VISIBLE);
                            dogctrl2.setVisibility(View.GONE);
                            dogctrl3.setVisibility(View.GONE);
                            LF_menudog.setVisibility(View.VISIBLE);
                            RIG_menudog.setVisibility(View.VISIBLE);
                            statusdog.setText("DogCtrl1");
                        } else if (count == 2) {
                            Menudog.setVisibility(View.GONE);
                            DataBase.setVisibility(View.GONE);
                            dogctrl1.setVisibility(View.GONE);
                            dogctrl2.setVisibility(View.VISIBLE);
                            dogctrl3.setVisibility(View.GONE);
                            LF_menudog.setVisibility(View.VISIBLE);
                            RIG_menudog.setVisibility(View.VISIBLE);
                            statusdog.setText("DogCtrl2");
                        } else if (count == 3) {
                            Menudog.setVisibility(View.GONE);
                            DataBase.setVisibility(View.GONE);
                            dogctrl1.setVisibility(View.GONE);
                            dogctrl2.setVisibility(View.GONE);
                            dogctrl3.setVisibility(View.VISIBLE);
                            LF_menudog.setVisibility(View.VISIBLE);
                            RIG_menudog.setVisibility(View.VISIBLE);
                            statusdog.setText("DogCtrl3");
                        } else if (count == 4) {
                            Menudog.setVisibility(View.GONE);
                            DataBase.setVisibility(View.VISIBLE);
                            dogctrl1.setVisibility(View.GONE);
                            dogctrl2.setVisibility(View.GONE);
                            dogctrl3.setVisibility(View.GONE);
                            LF_menudog.setVisibility(View.VISIBLE);
                            RIG_menudog.setVisibility(View.GONE);
                            statusdog.setText("DataBase");
                        }
                    }
                });
        web.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent Intentweb = new Intent(getApplicationContext(),DogwebActivity.class);
                //액티비티 시작!
                startActivity(Intentweb);
            }
        });


    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetectorCompat.onTouchEvent(event);
        this.gestureDetectorCompat2.onTouchEvent(event);

        return super.onTouchEvent(event);
    }
    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        //handle 'swipe left' action only


        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            ScalableLayout Menudog = (ScalableLayout) findViewById(R.id.menu);
            ScalableLayout DataBase = (ScalableLayout) findViewById(R.id.DataBasedog);
            ScalableLayout dogctrl1 = (ScalableLayout) findViewById(R.id.dogctrl1);
            ScalableLayout dogctrl2 = (ScalableLayout) findViewById(R.id.dogctrl2);
            ScalableLayout dogctrl3 = (ScalableLayout) findViewById(R.id.dogctrl3);
            Button LF_menudog = (Button) findViewById(R.id.LF_menubtndog);
            Button RIG_menudog = (Button) findViewById(R.id.RIG_menubtndog);
            TextView statusdog = (TextView) findViewById(R.id.statusdog);
         /*
         Toast.makeText(getBaseContext(),
          event1.toString() + "\n\n" +event2.toString(),
          Toast.LENGTH_SHORT).show();
         */

            if (event2.getX() < event1.getX()) {
                count++;
                if (count == 5) count = 4;

                if (count == 0) {
                    Menudog.setVisibility(View.VISIBLE);
                    DataBase.setVisibility(View.GONE);
                    dogctrl1.setVisibility(View.GONE);
                    dogctrl2.setVisibility(View.GONE);
                    dogctrl3.setVisibility(View.GONE);
                    RIG_menudog.setVisibility(View.VISIBLE);
                    LF_menudog.setVisibility(View.GONE);
                    statusdog.setText("Menu");
                } else if (count == 1) {
                    Menudog.setVisibility(View.GONE);
                    DataBase.setVisibility(View.GONE);
                    dogctrl1.setVisibility(View.VISIBLE);
                    dogctrl2.setVisibility(View.GONE);
                    dogctrl3.setVisibility(View.GONE);
                    LF_menudog.setVisibility(View.VISIBLE);
                    RIG_menudog.setVisibility(View.VISIBLE);
                    statusdog.setText("DogCtrl1");
                } else if (count == 2) {
                    Menudog.setVisibility(View.GONE);
                    DataBase.setVisibility(View.GONE);
                    dogctrl1.setVisibility(View.GONE);
                    dogctrl2.setVisibility(View.VISIBLE);
                    dogctrl3.setVisibility(View.GONE);
                    LF_menudog.setVisibility(View.VISIBLE);
                    RIG_menudog.setVisibility(View.VISIBLE);
                    statusdog.setText("DogCtrl2");
                } else if (count == 3) {
                    Menudog.setVisibility(View.GONE);
                    DataBase.setVisibility(View.GONE);
                    dogctrl1.setVisibility(View.GONE);
                    dogctrl2.setVisibility(View.GONE);
                    dogctrl3.setVisibility(View.VISIBLE);
                    LF_menudog.setVisibility(View.VISIBLE);
                    RIG_menudog.setVisibility(View.VISIBLE);
                    statusdog.setText("DogCtrl3");
                } else if (count == 4) {
                    Menudog.setVisibility(View.GONE);
                    DataBase.setVisibility(View.VISIBLE);
                    dogctrl1.setVisibility(View.GONE);
                    dogctrl2.setVisibility(View.GONE);
                    dogctrl3.setVisibility(View.GONE);
                    LF_menudog.setVisibility(View.VISIBLE);
                    statusdog.setText("DataBase");
                    RIG_menudog.setVisibility(View.GONE);
                }

            }

            return true;
        }
    }


    class My2ndGestureListener extends GestureDetector.SimpleOnGestureListener {
        //handle 'swipe right' action only
        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            ScalableLayout Menudog = (ScalableLayout) findViewById(R.id.menu);
            ScalableLayout DataBase = (ScalableLayout) findViewById(R.id.DataBasedog);
            ScalableLayout dogctrl1 = (ScalableLayout) findViewById(R.id.dogctrl1);
            ScalableLayout dogctrl2 = (ScalableLayout) findViewById(R.id.dogctrl2);
            ScalableLayout dogctrl3 = (ScalableLayout) findViewById(R.id.dogctrl3);
            Button LF_menudog = (Button) findViewById(R.id.LF_menubtndog);
            Button RIG_menudog = (Button) findViewById(R.id.RIG_menubtndog);
            TextView statusdog = (TextView) findViewById(R.id.statusdog);
         /*
         Toast.makeText(getBaseContext(),
          event1.toString() + "\n\n" +event2.toString(),
          Toast.LENGTH_SHORT).show();
         */

            if(event2.getX() > event1.getX()){
                count--;
                if (count == -1) count = 0;

                if (count == 0) {
                    Menudog.setVisibility(View.VISIBLE);
                    DataBase.setVisibility(View.GONE);
                    dogctrl1.setVisibility(View.GONE);
                    dogctrl2.setVisibility(View.GONE);
                    dogctrl3.setVisibility(View.GONE);
                    RIG_menudog.setVisibility(View.VISIBLE);
                    LF_menudog.setVisibility(View.GONE);
                    statusdog.setText("Menu");
                } else if (count == 1) {
                    Menudog.setVisibility(View.GONE);
                    DataBase.setVisibility(View.GONE);
                    dogctrl1.setVisibility(View.VISIBLE);
                    dogctrl2.setVisibility(View.GONE);
                    dogctrl3.setVisibility(View.GONE);
                    LF_menudog.setVisibility(View.VISIBLE);
                    RIG_menudog.setVisibility(View.VISIBLE);
                    statusdog.setText("DogCtrl1");
                } else if (count == 2) {
                    Menudog.setVisibility(View.GONE);
                    DataBase.setVisibility(View.GONE);
                    dogctrl1.setVisibility(View.GONE);
                    dogctrl2.setVisibility(View.VISIBLE);
                    dogctrl3.setVisibility(View.GONE);
                    LF_menudog.setVisibility(View.VISIBLE);
                    RIG_menudog.setVisibility(View.VISIBLE);
                    statusdog.setText("DogCtrl2");
                } else if (count == 3) {
                    Menudog.setVisibility(View.GONE);
                    DataBase.setVisibility(View.GONE);
                    dogctrl1.setVisibility(View.GONE);
                    dogctrl2.setVisibility(View.GONE);
                    dogctrl3.setVisibility(View.VISIBLE);
                    LF_menudog.setVisibility(View.VISIBLE);
                    RIG_menudog.setVisibility(View.VISIBLE);
                    statusdog.setText("DogCtrl3");
                } else if (count == 4) {
                    Menudog.setVisibility(View.GONE);
                    DataBase.setVisibility(View.VISIBLE);
                    dogctrl1.setVisibility(View.GONE);
                    dogctrl2.setVisibility(View.GONE);
                    dogctrl3.setVisibility(View.GONE);
                    LF_menudog.setVisibility(View.VISIBLE);
                    RIG_menudog.setVisibility(View.GONE);
                    statusdog.setText("DataBase");
                }
            }

            return true;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    void setToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

package mdp3.mdptest;

import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import static android.R.attr.id;
import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.content.Context.WIFI_SERVICE;
import static android.net.ConnectivityManager.TYPE_WIFI;

public class MainActivity extends AppCompatActivity {

    private EditText et1, et2, nm;
    private TextView tv1;
    private Socket socket;
    private DataOutputStream writeSocket;
    private DataInputStream readSocket;
    private Handler mHandler = new Handler();
    private Button btn1,btn2,btn3,btn4;
    private ConnectivityManager cManager;
    private NetworkInfo wifi;
    private ServerSocket serverSocket;
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;
    private int count=0;


    EditText text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //activity_main에서 불러옴
        et1 = (EditText) findViewById(R.id.ip);
        et2 = (EditText) findViewById(R.id.port);
        tv1=(TextView)findViewById(R.id.textView3);
        btn1=(Button)findViewById(R.id.home);
        btn2=(Button)findViewById(R.id.ip_check);
        btn3=(Button)findViewById(R.id.homemanager);
        btn4=(Button)findViewById(R.id.dogmanager);
        //BUTTON 선언
        cManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        btn1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                wifi = cManager.getNetworkInfo(TYPE_WIFI);
                if (et1.getText().toString().equals("")) {
                    setToast("IP를 입력하세요");
                } else if (et2.getText().toString().equals("")) {
                    setToast("PORT를 입력하세요");
                } else {
                    if (wifi.isConnected()) {
                        (new Connecthome()).start();
                    } else {
                        setToast("WI-FI연결을 확인해주세요");
                    }
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                wifi = cManager.getNetworkInfo(TYPE_WIFI);
                if (wifi.isConnected()) {
                    WifiManager wManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                    WifiInfo info = wManager.getConnectionInfo();
                    tv1.setText("IP Address : " + Formatter.formatIpAddress(info.getIpAddress()));
                } else {
                    tv1.setText("Disconnected");
                }

            }
        });
        btn3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {


                    Intent Intenthome = new Intent(getApplicationContext(),SmartHouseActivity.class);
                    //액티비티 시작!
                    startActivity(Intenthome);
                finish();

            }
        });
        btn4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent Intentdog = new Intent(getApplicationContext(),SmartDogActivity.class);
                //액티비티 시작!
                startActivity(Intentdog);
                finish();
            }
        });

    }
    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;


        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(MainActivity.this);
        alert_confirm.setMessage("프로그램을 종료 하시겠습니까?").setCancelable(false).setPositiveButton("취소",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }

                }).setNegativeButton("확인",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                });

        AlertDialog alert = alert_confirm.create();

        alert.show();

    }


    class Connecthome extends Thread {
        public void run() {
            try {
            } catch (Exception e) {
                final String recvInput = "정확히 입력하세요!";
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        setToast(recvInput);
                    }
                });
            }
            try {
                mHandler.post(new Runnable() {
                    String ip = et1.getText().toString();
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                            setToast("연결된 IP:" + ip + "Port:" + "555");
                            Intent Intenthome = new Intent(MainActivity.this, SmartHouseActivity.class);
                            Intenthome.putExtra("ip", ip);
                            //액티비티 시작!
                            startActivity(Intenthome);

                    }
                });
            } catch (Exception e) {
                final String recvInput = "연결에 실패하였습니다.";
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        setToast(recvInput);
                    }

                });
            }
        }
    }



    void setToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

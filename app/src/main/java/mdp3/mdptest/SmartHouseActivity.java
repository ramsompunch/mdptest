package mdp3.mdptest;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SmartHouseActivity extends AppCompatActivity {

    //카운트
    private int count=0;
    //카운트3
    private int count3=0;
    //카운트1
    private int count1=0;
    private int count5=0;
    //핸들러
    private Handler mHandler = new Handler();
    //소켓
    private Socket socket;
    //딜레이
    private TimerTask mTask;
    //딜레이
    private Timer mTimer;
    //쓰는소켓
    private DataOutputStream writeSocket;
    //읽는 소켓
    private DataInputStream readSocket;
    //image 변경 flag
    boolean flag = false;
    //image 변경 flag
    boolean flag1 = false;
    //image 변경 flag
    boolean flag2 = false;
    //image 변경 flag
    boolean flag3 = false;
    boolean flag4 = false;
    //슬라이드를 위한거
    private GestureDetectorCompat gestureDetectorCompat;
    //슬라이드를 위한거2
    private GestureDetectorCompat gestureDetectorCompat2;
    //프로그래스바를 위한것
    int pStatus = 0;
    //카운트4
    int count4=0;
    //핸들러
    private Handler handler = new Handler();
    //텍스트
    TextView tv;
    //랜덤변수
    Random mRand1,mRand2,mRand3,mRand4,mRand5,mRand6;
    //랜덤 받아올 변수
    int number1,number2,number3,number4,number5,number6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smarthouse_main);
        (new Connect()).start();

        //슬라이드를 위한  생성
        gestureDetectorCompat = new GestureDetectorCompat(this, new MyGestureListener());
        //슬라이드를 위한  생성
        gestureDetectorCompat2 = new GestureDetectorCompat(this, new My2ndGestureListener());
        //오른쪽 메뉴 버튼
        Button RIG_menubtn = (Button) findViewById(R.id.RIG_menubtn);
        //왼쪽 메뉴 버튼
        Button LF_menubtn = (Button) findViewById(R.id.LF_menubtn);
        //가스 버튼
        Button gasvelve = (Button) findViewById(R.id.gas_btn);
        //선풍기 버튼
        Button fan = (Button) findViewById(R.id.fan_btn);
        //선풍기 긴급정지 버튼
        Button fanstop = (Button) findViewById(R.id.fan_stop);
        //커튼 왼쪽버튼
        Button Ctn_LF = (Button) findViewById(R.id.LF);
        //커튼 오른쪽 버튼
        Button Ctn_RIG = (Button) findViewById(R.id.RIG);
        //커튼 왼쪽으로 축이동 버튼
        Button Ctn_LFSpin = (Button) findViewById(R.id.LF_spin);
        //커튼 오른쪽으로 축이동 버튼
        Button Ctn_RIGSpin = (Button) findViewById(R.id.RIG_spin);
        //데이터베이스 버튼
        Button DB = (Button) findViewById(R.id.DB);
        //문 버튼
        final Button Doorlock = (Button) findViewById(R.id.Door_lock);
        //OTP버튼
        Button otpbtn = (Button) findViewById(R.id.otpbtn);
        //tv라는 텍스트
        tv=(TextView)findViewById(R.id.tv) ;

        //랜덤생성
        mRand1 = new Random();
        //오른쪽 메뉴 버튼 클릭리스너
        RIG_menubtn.setOnClickListener(
                new ImageButton.OnClickListener() {
                    //메뉴 레이아웃
                    LinearLayout Menu = (LinearLayout) findViewById(R.id.Menu);
                    //데이터베이스 레이아웃
                    LinearLayout DataBase = (LinearLayout) findViewById(R.id.DataBase);
                    //홈컨트롤1 레이아웃
                    LinearLayout homectrl1 = (LinearLayout) findViewById(R.id.homectrl);
                    //홈컨트롤2 레이아웃
                    LinearLayout homectrl2 = (LinearLayout) findViewById(R.id.homectrl1);
                    //홈컨트롤3 레이아웃
                    LinearLayout homectrl3 = (LinearLayout) findViewById(R.id.homectrl2);
                    //OTP 레이아웃
                    LinearLayout otpctrl = (LinearLayout) findViewById(R.id.otp);
                    //왼쪽 메뉴 버튼
                    Button LF_menu = (Button) findViewById(R.id.LF_menubtn);
                    //오른쪽 메뉴 버튼
                    Button RIG_menu = (Button) findViewById(R.id.RIG_menubtn);
                    //현재 상태 나타내는 텍스트
                    TextView status = (TextView) findViewById(R.id.status);
                    //버튼이 눌렀을 경우에
                    public void onClick(View v) {
                        //카운트 증가
                        count++;
                        //만약 카운트 6이면 5로 셋
                        if (count == 6) count = 5;
                        //카운트가 0일때
                        if (count == 0) {
                            //메뉴 레이아웃 보이기
                            Menu.setVisibility(View.VISIBLE);
                            //데이터베이스 레이아웃 숨김
                            DataBase.setVisibility(View.GONE);
                            //홈컨트롤1 레이아웃 숨김
                            homectrl1.setVisibility(View.GONE);
                            //홈컨트롤2 레이아웃 숨김
                            homectrl2.setVisibility(View.GONE);
                            //홈컨트롤3 레이아웃 숨김
                            homectrl3.setVisibility(View.GONE);
                            //OPT 레이아웃 숨김
                            otpctrl.setVisibility(View.GONE);
                            //오른쪽 메뉴버튼 보이기
                            RIG_menu.setVisibility(View.VISIBLE);
                            //왼쪽 메뉴버튼 숨기기
                            LF_menu.setVisibility(View.GONE);
                            //상태 텍스트를 MENU로 바꿈
                            status.setText("Menu");
                        }
                        //만약 카운트가 1이면
                        else if (count == 1) {
                            //메뉴 레이아웃 숨기기
                            Menu.setVisibility(View.GONE);
                            //데이터베이스 레이아웃 숨기기
                            DataBase.setVisibility(View.GONE);
                            //홈컨트롤1 레이아웃 보이기
                            homectrl1.setVisibility(View.VISIBLE);
                            //홈컨트롤2 레이아웃 숨기기
                            homectrl2.setVisibility(View.GONE);
                            //홈컨트롤3 레이아웃 숨기기
                            homectrl3.setVisibility(View.GONE);
                            //OTP컨트롤 레이아웃 숨기기
                            otpctrl.setVisibility(View.GONE);
                            //왼쪽 메뉴버튼 보이기
                            LF_menu.setVisibility(View.VISIBLE);
                            //오른쪽 메뉴버튼 보이기
                            RIG_menu.setVisibility(View.VISIBLE);
                            //상태 텍스트를 HOMECTRL1으로 바꿈
                            status.setText("HomeCtrl1");
                        }
                        //만약 카운트가 2이면
                        else if (count == 2) {
                            //메뉴 레이아웃 숨기기
                            Menu.setVisibility(View.GONE);
                            //데이터베이스 레이아웃 숨기기
                            DataBase.setVisibility(View.GONE);
                            //홈컨트롤1 레이아웃 숨기기
                            homectrl1.setVisibility(View.GONE);
                            //홈컨트롤2 레이아웃 보이기
                            homectrl2.setVisibility(View.VISIBLE);
                            //홈컨트롤3 레이아웃 숨기기
                            homectrl3.setVisibility(View.GONE);
                            //OTP 레이아웃 숨기기
                            otpctrl.setVisibility(View.GONE);
                            //왼족메뉴버튼 보이기
                            LF_menu.setVisibility(View.VISIBLE);
                            //오른쪽메뉴버튼 보이기
                            RIG_menu.setVisibility(View.VISIBLE);
                            //상태 텍스트를 HOMECTRL2으로 바꿈
                            status.setText("HomeCtrl2");
                        }
                        //만약 카운트가 3이면
                        else if (count == 3) {
                            //메뉴 레이아웃 숨기기
                            Menu.setVisibility(View.GONE);
                            //데이터베이스 레이아웃 숨기기
                            DataBase.setVisibility(View.GONE);
                            //홈컨트롤1 레이아웃 숨기기
                            homectrl1.setVisibility(View.GONE);
                            //홈컨트롤2 레이아웃 숨기기
                            homectrl2.setVisibility(View.GONE);
                            //홈컨트롤3 레이아웃 보이기
                            homectrl3.setVisibility(View.VISIBLE);
                            //otp 레이아웃 숨기기
                            otpctrl.setVisibility(View.GONE);
                            //왼쪽 메뉴 버튼 보이기
                            LF_menu.setVisibility(View.VISIBLE);
                            //오른쪽 메뉴 버튼 보이기
                            RIG_menu.setVisibility(View.VISIBLE);
                            //상태 텍스트를 HOMECTRL3로 바꿈
                            status.setText("HomeCtrl3");
                        }
                        //만약 카운트가 4이면
                        else if (count == 4) {
                            //메뉴 레이아웃 숨기기
                            Menu.setVisibility(View.GONE);
                            //데이터베이스 레이아웃 보이기
                            DataBase.setVisibility(View.VISIBLE);
                            //홈컨트롤1 레이아웃 숨기기
                            homectrl1.setVisibility(View.GONE);
                            //홈컨트롤2 레이아웃 숨기기
                            homectrl2.setVisibility(View.GONE);
                            //홈컨트롤3 레이아웃 숨기기
                            homectrl3.setVisibility(View.GONE);
                            //otp 레이아웃 숨기기
                            otpctrl.setVisibility(View.GONE);
                            //왼쪽 메뉴 버튼 보이기
                            LF_menu.setVisibility(View.VISIBLE);
                            //상태 텍스트를 데이터베이스로 바꿈
                            status.setText("DataBase");
                            //오른쪽 메뉴 버튼 보이기
                            RIG_menu.setVisibility(View.VISIBLE);
                        }
                        //만약 카운트가 5이면
                        else if (count == 5) {
                            //메뉴 레이아웃 숨기기
                            Menu.setVisibility(View.GONE);
                            //데이터베이스 레이아웃 숨기기
                            DataBase.setVisibility(View.GONE);
                            //홈컨트롤1 레이아웃 숨기기
                            homectrl1.setVisibility(View.GONE);
                            //홈컨트롤2 레이아웃 숨기기
                            homectrl2.setVisibility(View.GONE);
                            //홈컨트롤3 레이아웃 숨기기
                            homectrl3.setVisibility(View.GONE);
                            //OTP 레이아웃 보이기
                            otpctrl.setVisibility(View.VISIBLE);
                            //왼쪽 메뉴 버튼 보이기
                            LF_menu.setVisibility(View.VISIBLE);
                            //상태 텍스트 OTP로 바꿈
                            status.setText("OTP");
                            //오른쪽 메뉴 버튼 보이기
                            RIG_menu.setVisibility(View.GONE);
                        }
                    }
                });
        //왼쪽 메뉴 버튼 눌렀을때
        LF_menubtn.setOnClickListener(
                new ImageButton.OnClickListener() {
                    //메뉴 레이아웃
                    LinearLayout Menu = (LinearLayout) findViewById(R.id.Menu);
                    //데이터베이스 레이아웃
                    LinearLayout DataBase = (LinearLayout) findViewById(R.id.DataBase);
                    //홈컨트롤1 레이아웃
                    LinearLayout homectrl1 = (LinearLayout) findViewById(R.id.homectrl);
                    //홈컨트롤2 레이아웃
                    LinearLayout homectrl2 = (LinearLayout) findViewById(R.id.homectrl1);
                    //홈컨트롤3 레이아웃
                    LinearLayout homectrl3 = (LinearLayout) findViewById(R.id.homectrl2);
                    //OTP 레이아웃
                    LinearLayout otpctrl = (LinearLayout) findViewById(R.id.otp);
                    //왼쪽 메뉴 버튼
                    Button LF_menu = (Button) findViewById(R.id.LF_menubtn);
                    //오른쪽 메뉴 버튼
                    Button RIG_menu = (Button) findViewById(R.id.RIG_menubtn);
                    //현재 상태 나타내는 텍스트
                    TextView status = (TextView) findViewById(R.id.status);
                    //버튼이 눌렀을 경우에
                    public void onClick(View v) {
                        //카운트 감소
                        count--;
                        //카운트가 -1일때 카운트는 0
                        if (count == -1) count = 0;
                        //만약 카운트가 6일때 카운트는 5
                        if (count == 6) count = 5;
                        //만약 카운트가 0이면
                        if (count == 0) {
                            //메뉴 레이아웃 보이기
                            Menu.setVisibility(View.VISIBLE);
                            //데이터베이스 레이아웃 숨김
                            DataBase.setVisibility(View.GONE);
                            //홈컨트롤1 레이아웃 숨김
                            homectrl1.setVisibility(View.GONE);
                            //홈컨트롤2 레이아웃 숨김
                            homectrl2.setVisibility(View.GONE);
                            //홈컨트롤3 레이아웃 숨김
                            homectrl3.setVisibility(View.GONE);
                            //OPT 레이아웃 숨김
                            otpctrl.setVisibility(View.GONE);
                            //오른쪽 메뉴버튼 보이기
                            RIG_menu.setVisibility(View.VISIBLE);
                            //왼쪽 메뉴버튼 숨기기
                            LF_menu.setVisibility(View.GONE);
                            //상태 텍스트를 MENU로 바꿈
                            status.setText("Menu");
                        }
                        //만약 카운트가 1이면
                        else if (count == 1) {
                            //메뉴 레이아웃 숨기기
                            Menu.setVisibility(View.GONE);
                            //데이터베이스 레이아웃 숨기기
                            DataBase.setVisibility(View.GONE);
                            //홈컨트롤1 레이아웃 보이기
                            homectrl1.setVisibility(View.VISIBLE);
                            //홈컨트롤2 레이아웃 숨기기
                            homectrl2.setVisibility(View.GONE);
                            //홈컨트롤3 레이아웃 숨기기
                            homectrl3.setVisibility(View.GONE);
                            //OTP컨트롤 레이아웃 숨기기
                            otpctrl.setVisibility(View.GONE);
                            //왼쪽 메뉴버튼 보이기
                            LF_menu.setVisibility(View.VISIBLE);
                            //오른쪽 메뉴버튼 보이기
                            RIG_menu.setVisibility(View.VISIBLE);
                            //상태 텍스트를 HOMECTRL1으로 바꿈
                            status.setText("HomeCtrl1");
                        }
                        //만약 카운트가 2이면
                        else if (count == 2) {
                            //메뉴 레이아웃 숨기기
                            Menu.setVisibility(View.GONE);
                            //데이터베이스 레이아웃 숨기기
                            DataBase.setVisibility(View.GONE);
                            //홈컨트롤1 레이아웃 숨기기
                            homectrl1.setVisibility(View.GONE);
                            //홈컨트롤2 레이아웃 보이기
                            homectrl2.setVisibility(View.VISIBLE);
                            //홈컨트롤3 레이아웃 숨기기
                            homectrl3.setVisibility(View.GONE);
                            //OTP 레이아웃 숨기기
                            otpctrl.setVisibility(View.GONE);
                            //왼족메뉴버튼 보이기
                            LF_menu.setVisibility(View.VISIBLE);
                            //오른쪽메뉴버튼 보이기
                            RIG_menu.setVisibility(View.VISIBLE);
                            //상태 텍스트를 HOMECTRL2으로 바꿈
                            status.setText("HomeCtrl2");
                        }
                        //만약 카운트가 3이면
                        else if (count == 3) {
                            //메뉴 레이아웃 숨기기
                            Menu.setVisibility(View.GONE);
                            //데이터베이스 레이아웃 숨기기
                            DataBase.setVisibility(View.GONE);
                            //홈컨트롤1 레이아웃 숨기기
                            homectrl1.setVisibility(View.GONE);
                            //홈컨트롤2 레이아웃 숨기기
                            homectrl2.setVisibility(View.GONE);
                            //홈컨트롤3 레이아웃 보이기
                            homectrl3.setVisibility(View.VISIBLE);
                            //otp 레이아웃 숨기기
                            otpctrl.setVisibility(View.GONE);
                            //왼쪽 메뉴 버튼 보이기
                            LF_menu.setVisibility(View.VISIBLE);
                            //오른쪽 메뉴 버튼 보이기
                            RIG_menu.setVisibility(View.VISIBLE);
                            //상태 텍스트를 HOMECTRL3로 바꿈
                            status.setText("HomeCtrl3");
                        }
                        //만약 카운트가 4이면
                        else if (count == 4) {
                            //메뉴 레이아웃 숨기기
                            Menu.setVisibility(View.GONE);
                            //데이터베이스 레이아웃 보이기
                            DataBase.setVisibility(View.VISIBLE);
                            //홈컨트롤1 레이아웃 숨기기
                            homectrl1.setVisibility(View.GONE);
                            //홈컨트롤2 레이아웃 숨기기
                            homectrl2.setVisibility(View.GONE);
                            //홈컨트롤3 레이아웃 숨기기
                            homectrl3.setVisibility(View.GONE);
                            //otp 레이아웃 숨기기
                            otpctrl.setVisibility(View.GONE);
                            //왼쪽 메뉴 버튼 보이기
                            LF_menu.setVisibility(View.VISIBLE);
                            //상태 텍스트를 데이터베이스로 바꿈
                            status.setText("DataBase");
                            //오른쪽 메뉴 버튼 보이기
                            RIG_menu.setVisibility(View.VISIBLE);
                        }
                        //만약 카운트가 5이면
                        else if (count == 5) {
                            //메뉴 레이아웃 숨기기
                            Menu.setVisibility(View.GONE);
                            //데이터베이스 레이아웃 숨기기
                            DataBase.setVisibility(View.GONE);
                            //홈컨트롤1 레이아웃 숨기기
                            homectrl1.setVisibility(View.GONE);
                            //홈컨트롤2 레이아웃 숨기기
                            homectrl2.setVisibility(View.GONE);
                            //홈컨트롤3 레이아웃 숨기기
                            homectrl3.setVisibility(View.GONE);
                            //OTP 레이아웃 보이기
                            otpctrl.setVisibility(View.VISIBLE);
                            //왼쪽 메뉴 버튼 보이기
                            LF_menu.setVisibility(View.VISIBLE);
                            //상태 텍스트 OTP로 바꿈
                            status.setText("OTP");
                            //오른쪽 메뉴 버튼 보이기
                            RIG_menu.setVisibility(View.GONE);
                        }
                    }
                });
        //가스 버튼리스너
        gasvelve.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        // (new sendMessagee()).start();
                        //가스버튼 선언
                        Button iv = (Button) findViewById(R.id.gas_btn);
                        //(new sendMessagee()).start();
                        //flag가 false일때
                        if (flag == false) {
                            //가스벨브 버튼 열음으로 바꿈
                            iv.setBackgroundResource(R.drawable.valve_open);
                            //flag는 true
                            flag = true;
                        } else {
                            //가스벨브 버튼 닫음으로 바꿈
                            iv.setBackgroundResource(R.drawable.valve_close);
                            //flag는 false
                            flag = false;
                        }   //use flag to change image
                    }
                });
        //팬 버튼 리스너
        fan.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //팬 이미지뷰 생성
                        ImageView fan = (ImageView) findViewById(R.id.fan);
                        //팬컨트롤 이미지뷰 생성
                        ImageView fanctrl = (ImageView) findViewById(R.id.fan_ctrl);
                        //팬레벨 텍스트 생성
                        TextView fanlevel = (TextView) findViewById(R.id.fanlevel);
                        //팬 회전 앵글 선언
                        int angle;
                        //랜덤변수 r선언
                        Random r;
                        //랜덤 생성
                        r = new Random();
                        // (new sendMessagef()).start();
                        //카운트1 증가
                        count1++;
                        //팬그림을 동작안하는 팬으로 설정
                        fan.setBackgroundResource(R.drawable.fan_nop);
                        //애니매이션 클리어
                        fanctrl.clearAnimation();
                        //카운트1이 만약 1이라면
                        if (count1 == 1) {
                            //팬 이미지를 동작하는 팬으로 설정
                            fan.setBackgroundResource(R.drawable.fan_op);
                            //앵글값 구하기
                            angle = r.nextInt() + 360;
                            //이제 돌아갈 각도 설정
                            RotateAnimation rotate = new RotateAnimation(0, angle,
                                    RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5F);
                            rotate.setFillAfter(true);
                            rotate.setDuration(500000);
                            //무한으로 돌기
                            rotate.setRepeatCount(Animation.INFINITE);
                            //재시작
                            rotate.setRepeatMode(Animation.RESTART);
                            rotate.setInterpolator(new AccelerateDecelerateInterpolator());
                            //애니메이션 설정
                            fanctrl.startAnimation(rotate);

                        } else if (count1 == 2) {
                            //팬 이미지를 동작하는 팬으로 설정
                            fan.setBackgroundResource(R.drawable.fan_op);
                            //앵글값 구하기
                            angle = r.nextInt() + 360;
                            //이제 돌아갈 각도 설정
                            RotateAnimation rotate = new RotateAnimation(0, angle,
                                    RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5F);
                            rotate.setFillAfter(true);
                            rotate.setDuration(500000);
                            //무한으로 돌기
                            rotate.setRepeatCount(Animation.INFINITE);
                            //재시작
                            rotate.setRepeatMode(Animation.RESTART);
                            rotate.setInterpolator(new AccelerateDecelerateInterpolator());
                            //애니메이션 설정
                            fanctrl.startAnimation(rotate);
                        } else if (count1 == 3) {
                            //팬 이미지를 동작하는 팬으로 설정
                            fan.setBackgroundResource(R.drawable.fan_op);
                            //앵글값 구하기
                            angle = r.nextInt() + 360;
                            //이제 돌아갈 각도 설정
                            RotateAnimation rotate = new RotateAnimation(0, angle,
                                    RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5F);
                            rotate.setFillAfter(true);
                            rotate.setDuration(500000);
                            //무한으로 돌기
                            rotate.setRepeatCount(Animation.INFINITE);
                            //재시작
                            rotate.setRepeatMode(Animation.RESTART);
                            rotate.setInterpolator(new AccelerateDecelerateInterpolator());
                            //애니메이션 설정
                            fanctrl.startAnimation(rotate);
                        } else if (count1 == 4) {
                            //팬 이미지를 동작하는 팬으로 설정
                            fan.setBackgroundResource(R.drawable.fan_op);
                            //앵글값 구하기
                            angle = r.nextInt() + 360;
                            //이제 돌아갈 각도 설정
                            RotateAnimation rotate = new RotateAnimation(0, angle,
                                    RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5F);
                            rotate.setFillAfter(true);
                            rotate.setDuration(500000);
                            //무한으로 돌기
                            rotate.setRepeatCount(Animation.INFINITE);
                            //재시작
                            rotate.setRepeatMode(Animation.RESTART);
                            rotate.setInterpolator(new AccelerateDecelerateInterpolator());
                            //애니메이션 설정
                            fanctrl.startAnimation(rotate);
                        } else if (count1 == 5) {
                            count1 = 0;
                        }
                        fanlevel.setText("" + count1);
                    }
                });
        fanstop.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        ImageView fan = (ImageView) findViewById(R.id.fan);
                        ImageView fanctrl = (ImageView) findViewById(R.id.fan_ctrl);
                        TextView fanlevel = (TextView) findViewById(R.id.fanlevel);
                        fan.setBackgroundResource(R.drawable.fan_nop);
                        fanctrl.clearAnimation();
                        count1 = 0;
                        fanlevel.setText("" + count1);

                    }
                });
        Ctn_LF.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //  (new sendMessageg()).start();
                    }
                });
        Ctn_RIG.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //  (new sendMessageh()).start();
                    }
                });
        Ctn_LFSpin.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //    (new sendMessagei()).start();
                    }
                });
        Ctn_RIGSpin.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //  (new sendMessagej()).start();
                    }
                });
        Doorlock.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //(new sendMessagek()).start();
                        Doorlock.setBackgroundResource(R.drawable.unlock);

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Doorlock.setBackgroundResource(R.drawable.lock);

                            }
                        }, 3000);

                    }
                });
        DB.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent Intentdb = new Intent(SmartHouseActivity.this, DataBaseActivity.class);
                        //데이터 베이스 액티비티 시작!
                        startActivity(Intentdb);
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
                LinearLayout Menu = (LinearLayout) findViewById(R.id.Menu);
            LinearLayout DataBase = (LinearLayout) findViewById(R.id.DataBase);
            LinearLayout homectrl1 = (LinearLayout) findViewById(R.id.homectrl);
            LinearLayout homectrl2 = (LinearLayout) findViewById(R.id.homectrl1);
            LinearLayout homectrl3 = (LinearLayout) findViewById(R.id.homectrl2);
            LinearLayout otpctrl = (LinearLayout) findViewById(R.id.otp);
            Button LF_menu = (Button) findViewById(R.id.LF_menubtn);
            Button RIG_menu = (Button) findViewById(R.id.RIG_menubtn);
            TextView status = (TextView)findViewById(R.id.status);
         /*
         Toast.makeText(getBaseContext(),
          event1.toString() + "\n\n" +event2.toString(),
          Toast.LENGTH_SHORT).show();
         */

            if (event2.getX() < event1.getX()) {
                count++;
                if (count == 6) count = 5;

                if (count == 0) {
                    Menu.setVisibility(View.VISIBLE);
                    DataBase.setVisibility(View.GONE);
                    homectrl1.setVisibility(View.GONE);
                    homectrl2.setVisibility(View.GONE);
                    homectrl3.setVisibility(View.GONE);
                    otpctrl.setVisibility(View.GONE);
                    RIG_menu.setVisibility(View.VISIBLE);
                    LF_menu.setVisibility(View.GONE);
                    status.setText("Menu");
                } else if (count == 1) {
                    Menu.setVisibility(View.GONE);
                    DataBase.setVisibility(View.GONE);
                    homectrl1.setVisibility(View.VISIBLE);
                    homectrl2.setVisibility(View.GONE);
                    homectrl3.setVisibility(View.GONE);
                    otpctrl.setVisibility(View.GONE);
                    LF_menu.setVisibility(View.VISIBLE);
                    RIG_menu.setVisibility(View.VISIBLE);
                    status.setText("HomeCtrl1");
                } else if (count == 2) {
                    Menu.setVisibility(View.GONE);
                    DataBase.setVisibility(View.GONE);
                    homectrl1.setVisibility(View.GONE);
                    homectrl2.setVisibility(View.VISIBLE);
                    homectrl3.setVisibility(View.GONE);
                    otpctrl.setVisibility(View.GONE);
                    LF_menu.setVisibility(View.VISIBLE);
                    RIG_menu.setVisibility(View.VISIBLE);
                    status.setText("HomeCtrl2");
                } else if (count == 3) {
                    Menu.setVisibility(View.GONE);
                    DataBase.setVisibility(View.GONE);
                    homectrl1.setVisibility(View.GONE);
                    homectrl2.setVisibility(View.GONE);
                    homectrl3.setVisibility(View.VISIBLE);
                    otpctrl.setVisibility(View.GONE);
                    LF_menu.setVisibility(View.VISIBLE);
                    RIG_menu.setVisibility(View.VISIBLE);
                    status.setText("HomeCtrl3");
                } else if (count == 4) {
                    Menu.setVisibility(View.GONE);
                    DataBase.setVisibility(View.VISIBLE);
                    homectrl1.setVisibility(View.GONE);
                    homectrl2.setVisibility(View.GONE);
                    homectrl3.setVisibility(View.GONE);
                    otpctrl.setVisibility(View.GONE);
                    LF_menu.setVisibility(View.VISIBLE);
                    status.setText("DataBase");
                    RIG_menu.setVisibility(View.VISIBLE);
                } else if (count == 5) {
                    Menu.setVisibility(View.GONE);
                    DataBase.setVisibility(View.GONE);
                    homectrl1.setVisibility(View.GONE);
                    homectrl2.setVisibility(View.GONE);
                    homectrl3.setVisibility(View.GONE);
                    otpctrl.setVisibility(View.VISIBLE);
                    LF_menu.setVisibility(View.VISIBLE);
                    status.setText("OTP");
                    RIG_menu.setVisibility(View.GONE);
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
            LinearLayout Menu = (LinearLayout)findViewById(R.id.Menu);
            LinearLayout DataBase = (LinearLayout)findViewById(R.id.DataBase);
            LinearLayout homectrl1 = (LinearLayout)findViewById(R.id.homectrl);
            LinearLayout homectrl2 = (LinearLayout)findViewById(R.id.homectrl1);
            LinearLayout homectrl3 = (LinearLayout)findViewById(R.id.homectrl2);
            LinearLayout otpctrl = (LinearLayout) findViewById(R.id.otp);
            Button LF_menu = (Button)findViewById(R.id.LF_menubtn);
            Button RIG_menu = (Button)findViewById(R.id.RIG_menubtn);
            TextView status = (TextView)findViewById(R.id.status);
         /*
         Toast.makeText(getBaseContext(),
          event1.toString() + "\n\n" +event2.toString(),
          Toast.LENGTH_SHORT).show();
         */

            if(event2.getX() > event1.getX()){
                count--;
                if (count == -1) count = 0;

                if (count == 6) count = 5;

                if (count == 0) {
                    Menu.setVisibility(View.VISIBLE);
                    DataBase.setVisibility(View.GONE);
                    homectrl1.setVisibility(View.GONE);
                    homectrl2.setVisibility(View.GONE);
                    homectrl3.setVisibility(View.GONE);
                    otpctrl.setVisibility(View.GONE);
                    RIG_menu.setVisibility(View.VISIBLE);
                    LF_menu.setVisibility(View.GONE);
                    status.setText("Menu");
                } else if (count == 1) {
                    Menu.setVisibility(View.GONE);
                    DataBase.setVisibility(View.GONE);
                    homectrl1.setVisibility(View.VISIBLE);
                    homectrl2.setVisibility(View.GONE);
                    homectrl3.setVisibility(View.GONE);
                    otpctrl.setVisibility(View.GONE);
                    LF_menu.setVisibility(View.VISIBLE);
                    RIG_menu.setVisibility(View.VISIBLE);
                    status.setText("HomeCtrl1");
                } else if (count == 2) {
                    Menu.setVisibility(View.GONE);
                    DataBase.setVisibility(View.GONE);
                    homectrl1.setVisibility(View.GONE);
                    homectrl2.setVisibility(View.VISIBLE);
                    homectrl3.setVisibility(View.GONE);
                    otpctrl.setVisibility(View.GONE);
                    LF_menu.setVisibility(View.VISIBLE);
                    RIG_menu.setVisibility(View.VISIBLE);
                    status.setText("HomeCtrl2");
                } else if (count == 3) {
                    Menu.setVisibility(View.GONE);
                    DataBase.setVisibility(View.GONE);
                    homectrl1.setVisibility(View.GONE);
                    homectrl2.setVisibility(View.GONE);
                    homectrl3.setVisibility(View.VISIBLE);
                    otpctrl.setVisibility(View.GONE);
                    LF_menu.setVisibility(View.VISIBLE);
                    RIG_menu.setVisibility(View.VISIBLE);
                    status.setText("HomeCtrl3");
                } else if (count == 4) {
                    Menu.setVisibility(View.GONE);
                    DataBase.setVisibility(View.VISIBLE);
                    homectrl1.setVisibility(View.GONE);
                    homectrl2.setVisibility(View.GONE);
                    homectrl3.setVisibility(View.GONE);
                    otpctrl.setVisibility(View.GONE);
                    LF_menu.setVisibility(View.VISIBLE);
                    status.setText("DataBase");
                    RIG_menu.setVisibility(View.VISIBLE);
                } else if (count == 5) {
                    Menu.setVisibility(View.GONE);
                    DataBase.setVisibility(View.GONE);
                    homectrl1.setVisibility(View.GONE);
                    homectrl2.setVisibility(View.GONE);
                    homectrl3.setVisibility(View.GONE);
                    otpctrl.setVisibility(View.VISIBLE);
                    LF_menu.setVisibility(View.VISIBLE);
                    status.setText("OTP");
                    RIG_menu.setVisibility(View.GONE);
                }
            }

            return true;
        }
    }






    //led controll
    @SuppressWarnings("deprecation")
    public void OnClick(View v) throws Exception {

        switch (v.getId())
        {

            case R.id.led_red:
                ImageButton iv1 = (ImageButton) findViewById(R.id.led_red);
                //(new sendMessagea()).start();
                if (flag == false) {
                    iv1.setBackgroundResource(R.drawable.red_ledof);
                    flag = true;
                } else

                {
                    iv1.setBackgroundResource(R.drawable.red_led);
                    flag = false;
                }   //use flag to change image
                break;
            case R.id.led_blue:
                ImageButton iv2 = (ImageButton) findViewById(R.id.led_blue);
                //(new sendMessageb()).start();
                if (flag1 == false) {
                    iv2.setBackgroundResource(R.drawable.blue_ledof);
                    flag1 = true;
                } else

                {
                    iv2.setBackgroundResource(R.drawable.blue_led);
                    flag1 = false;
                }   //use flag to change image
                break;
            case R.id.led_green:
                ImageButton iv3 = (ImageButton) findViewById(R.id.led_green);
                //(new sendMessagec()).start();
                if (flag2 == false) {
                    iv3.setBackgroundResource(R.drawable.green_ledof);
                    flag2= true;
                } else
                {
                    iv3.setBackgroundResource(R.drawable.green_led);
                    flag2=false;
                }   //use flag to change image  flag = false;
                break;
            case R.id.led_dimmer:
                ImageButton iv4 = (ImageButton) findViewById(R.id.led_dimmer);
                TextView DimmerLevel = (TextView)findViewById(R.id.dimmerlevel);
                //(new sendMessaged()).start();
                count3++;

                if(count3==5){
                    count3=0;
                    iv4.setBackgroundResource(R.drawable.dimmer0);
                }
                else if(count3==4){
                    iv4.setBackgroundResource(R.drawable.dimmer4);
                }
                else if(count3==3){
                    iv4.setBackgroundResource(R.drawable.dimmer3);
                }
                else if(count3==2){
                    iv4.setBackgroundResource(R.drawable.dimmer2);
                }
                else if(count3==1){
                    iv4.setBackgroundResource(R.drawable.dimmer1);
                }
                DimmerLevel.setText("" + count3);
                break;
            case R.id.led_door:
                ImageButton LD = (ImageButton) findViewById(R.id.led_door);

                if (flag3 == false) {
                    LD.setBackgroundResource(R.drawable.drled_cl);
                    flag3= true;
                } else

                {
                    LD.setBackgroundResource(R.drawable.drled_op);
                    flag3= false;
                }   //use flag to change image
                break;
            case R.id.otpbtn:
                TextView tv1=(TextView)findViewById(R.id.sta);
                TextView mTextResult1=(TextView)findViewById(R.id.one);
                TextView mTextResult2=(TextView)findViewById(R.id.two);
                TextView mTextResult3=(TextView)findViewById(R.id.three);
                TextView mTextResult4=(TextView)findViewById(R.id.four);
                TextView mTextResult5=(TextView)findViewById(R.id.five);
                TextView mTextResult6=(TextView)findViewById(R.id.six);
                if(flag==false) {
                    flag=true;
                    Resources res = getResources();
                    Drawable drawable = res.getDrawable(R.drawable.circular);
                    final ProgressBar mProgress = (ProgressBar) findViewById(R.id.circularProgressbar);
                    mProgress.setProgress(0);   // Main Progress
                    mProgress.setSecondaryProgress(100); // Secondary Progress
                    mProgress.setMax(100); // Maximum Progress
                    mProgress.setProgressDrawable(drawable);
                    ObjectAnimator animation = ObjectAnimator.ofInt(mProgress, "progress", 0, 100);
                    animation.setDuration(57000);
                    animation.setInterpolator(new DecelerateInterpolator());
                    animation.start();
                    mRand1=new Random();
                    mRand2=new Random();
                    mRand3=new Random();
                    mRand4=new Random();
                    mRand5=new Random();
                    mRand6=new Random();

                    number1 = mRand1.nextInt(9);
                    number2 = mRand2.nextInt(9);
                    number3 = mRand3.nextInt(9);
                    number4 = mRand4.nextInt(9);
                    number5 = mRand5.nextInt(9);
                    number6 = mRand6.nextInt(9);
                    mTextResult1.setText(Integer.toString(number1));
                    mTextResult2.setText(Integer.toString(number2));
                    mTextResult3.setText(Integer.toString(number3));
                    mTextResult4.setText(Integer.toString(number4));
                    mTextResult5.setText(Integer.toString(number5));
                    mTextResult6.setText(Integer.toString(number6));
                    (new progres()).start();
                    tv1.setText("");

                }
          


                break;




        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();

    }

    class progres extends Thread {
        TextView tv1=(TextView)findViewById(R.id.sta);
        final ProgressBar mProgress = (ProgressBar) findViewById(R.id.circularProgressbar);
        public void run() {
            // TODO Auto-generated method stub
            pStatus=6000;

                while (pStatus > 1) {
                    pStatus -= 1;
                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            mProgress.setProgress(pStatus);
                            tv.setText(pStatus / 100 + "초");
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        // Just to display the progress slowly
                        Thread.sleep(9); //thread will take approx 1.5 seconds to finish
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }flag=false;
        }
    }
    class sendMessage1 extends Thread {
        public void run() {

            try {
                writeSocket = new DataOutputStream(socket.getOutputStream());
                readSocket = new DataInputStream(socket.getInputStream());
                byte b = (byte)number1;
                writeSocket.write(b);
            } catch (Exception e) {

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                    }
                });
            }
        }
    }
    class sendMessage2 extends Thread {

        public void run() {

            try {
                writeSocket = new DataOutputStream(socket.getOutputStream());
                readSocket = new DataInputStream(socket.getInputStream());
                byte b = (byte)number2;
                writeSocket.write(b);
            } catch (Exception e) {

                Log.d("SetServer", e.getMessage());
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                    }

                });

            }

        }
    }
    class sendMessage3 extends Thread {

        public void run() {

            try {
                writeSocket = new DataOutputStream(socket.getOutputStream());
                readSocket = new DataInputStream(socket.getInputStream());
                byte b = (byte)number3;
                writeSocket.write(b);
            } catch (Exception e) {

                Log.d("SetServer", e.getMessage());
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                    }

                });

            }

        }
    }
    class sendMessage4 extends Thread {

        public void run() {

            try {
                writeSocket = new DataOutputStream(socket.getOutputStream());
                readSocket = new DataInputStream(socket.getInputStream());
                byte b = (byte)number4;
                writeSocket.write(b);
            } catch (Exception e) {

                Log.d("SetServer", e.getMessage());
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                    }

                });

            }

        }
    }
    class sendMessage5 extends Thread {

        public void run() {

            try {
                writeSocket = new DataOutputStream(socket.getOutputStream());
                readSocket = new DataInputStream(socket.getInputStream());
                byte b = (byte)number5;
                writeSocket.write(b);
            } catch (Exception e) {

                Log.d("SetServer", e.getMessage());
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                    }

                });

            }

        }
    }
    class sendMessage6 extends Thread {

        public void run() {

            try {
                writeSocket = new DataOutputStream(socket.getOutputStream());
                readSocket = new DataInputStream(socket.getInputStream());
                byte b = (byte)number6;
                writeSocket.write(b);
            } catch (Exception e) {

                Log.d("SetServer", e.getMessage());
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                    }

                });

            }

        }
    }
    class sendMessagea extends Thread {

        public void run() {

            try {
                writeSocket = new DataOutputStream(socket.getOutputStream());
                readSocket = new DataInputStream(socket.getInputStream());
                byte[] b = new byte[4];
                b="a".getBytes();
                writeSocket.write(b);
            } catch (Exception e) {

                Log.d("SetServer", e.getMessage());
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                    }

                });

            }

        }
    }
    class sendMessageb extends Thread {

        public void run() {

            try {
                writeSocket = new DataOutputStream(socket.getOutputStream());
                readSocket = new DataInputStream(socket.getInputStream());
                byte[] b = new byte[4];
                b = "b".getBytes();
                writeSocket.write(b);
            } catch (Exception e) {
                Log.d("SetServer", e.getMessage());
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                    }

                });

            }

        }
    }
    class sendMessagec extends Thread {

        public void run() {

            try {
                writeSocket = new DataOutputStream(socket.getOutputStream());
                readSocket = new DataInputStream(socket.getInputStream());
                byte[] b = new byte[4];
                b = "c".getBytes();
                writeSocket.write(b);
            } catch (Exception e) {
                Log.d("SetServer", e.getMessage());
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                    }

                });

            }

        }
    }
    class sendMessaged extends Thread {

        public void run() {

            try {
                writeSocket = new DataOutputStream(socket.getOutputStream());
                readSocket = new DataInputStream(socket.getInputStream());
                byte[] b = new byte[4];
                b = "d".getBytes();
                writeSocket.write(b);
            } catch (Exception e) {
                Log.d("SetServer", e.getMessage());
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                    }

                });

            }

        }
    }
    class sendMessagee extends Thread {

        public void run() {

            try {
                writeSocket = new DataOutputStream(socket.getOutputStream());
                readSocket = new DataInputStream(socket.getInputStream());
                byte[] b = new byte[4];
                b = "e".getBytes();
                writeSocket.write(b);

            } catch (Exception e) {
                Log.d("SetServer", e.getMessage());
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                    }

                });

            }

        }
    }
    class sendMessagef extends Thread {

        public void run() {

            try {
                writeSocket = new DataOutputStream(socket.getOutputStream());
                readSocket = new DataInputStream(socket.getInputStream());
                byte[] b = new byte[4];
                b = "f".getBytes();
                writeSocket.write(b);

            } catch (Exception e) {
                Log.d("SetServer", e.getMessage());
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                    }

                });

            }

        }
    }
    class sendMessageg extends Thread {

        public void run() {

            try {
                writeSocket = new DataOutputStream(socket.getOutputStream());
                readSocket = new DataInputStream(socket.getInputStream());
                byte[] b = new byte[4];
                b = "g".getBytes();
                writeSocket.write(b);

            } catch (Exception e) {
                Log.d("SetServer", e.getMessage());
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                    }

                });

            }

        }
    }
    class sendMessageh extends Thread {

        public void run() {

            try {
                writeSocket = new DataOutputStream(socket.getOutputStream());
                readSocket = new DataInputStream(socket.getInputStream());
                byte[] b = new byte[4];
                b = "h".getBytes();
                writeSocket.write(b);

            } catch (Exception e) {
                Log.d("SetServer", e.getMessage());
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                    }

                });

            }

        }
    }
    class sendMessagei extends Thread {

        public void run() {

            try {
                writeSocket = new DataOutputStream(socket.getOutputStream());
                readSocket = new DataInputStream(socket.getInputStream());
                byte[] b = new byte[4];
                b = "i".getBytes();
                writeSocket.write(b);

            } catch (Exception e) {
                Log.d("SetServer", e.getMessage());
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                    }

                });

            }

        }
    }
    class sendMessagej extends Thread {

        public void run() {

            try {
                writeSocket = new DataOutputStream(socket.getOutputStream());
                readSocket = new DataInputStream(socket.getInputStream());
                byte[] b = new byte[4];
                b = "j".getBytes();
                writeSocket.write(b);

            } catch (Exception e) {
                Log.d("SetServer", e.getMessage());
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                    }

                });

            }

        }
    }
    class sendMessagek extends Thread {

        public void run() {

            try {
                writeSocket = new DataOutputStream(socket.getOutputStream());
                readSocket = new DataInputStream(socket.getInputStream());
                byte[] b = new byte[4];
                b = "k".getBytes();
                writeSocket.write(b);

            } catch (Exception e) {
                Log.d("SetServer", e.getMessage());
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                    }

                });

            }

        }
    }
    class sendMessagez extends Thread {

        public void run() {

            try {
                writeSocket = new DataOutputStream(socket.getOutputStream());
                readSocket = new DataInputStream(socket.getInputStream());
                byte[] b = new byte[4];
                b = "z".getBytes();
                writeSocket.write(b);

            } catch (Exception e) {
                Log.d("SetServer", e.getMessage());
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                    }

                });

            }

        }
    }
    class sendMessagex extends Thread {

        public void run() {

            try {
                writeSocket = new DataOutputStream(socket.getOutputStream());
                readSocket = new DataInputStream(socket.getInputStream());
                byte[] b = new byte[4];
                b = "x".getBytes();
                writeSocket.write(b);

            } catch (Exception e) {
                Log.d("SetServer", e.getMessage());
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                    }

                });

            }

        }
    }

    class sendMessagew extends Thread {

        public void run() {

            try {
                writeSocket = new DataOutputStream(socket.getOutputStream());
                readSocket = new DataInputStream(socket.getInputStream());
                byte[] b = new byte[4];
                b = "w".getBytes();
                writeSocket.write(b);

            } catch (Exception e) {
                Log.d("SetServer", e.getMessage());
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                    }

                });

            }

        }
    }

    class Connect extends Thread {
        public void run() {
            Log.d("Connect", "Run Connect");
            Intent RecIP = getIntent();
            String ip = RecIP.getStringExtra("ip");
            int port = 555;


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
                socket = new Socket(ip, port);
                writeSocket = new DataOutputStream(socket.getOutputStream());
                readSocket = new DataInputStream(socket.getInputStream());

                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                        setToast("연결에 성공하였습니다.");
                        mTask = new TimerTask() {
                            @Override
                            public void run() {
                                (new sendMessagew()).start();
                            }
                        };
                        mTimer=new Timer();
                        mTimer.schedule(mTask,0,5000);
                    }
                });
                (new recvSocket()).start();
            } catch (Exception e) {

                final String recvInput = "연결에 실패하였습니다.";
                Intent Intentback = new Intent(getApplicationContext(),MainActivity.class);
                //액티비티 시작!
                startActivity(Intentback);
                finish();
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

    class recvSocket extends Thread {

        public void run() {
            try {
                readSocket = new DataInputStream(socket.getInputStream());

                while (true) {
                    byte[] b = new byte[100];
                    int ac = readSocket.read(b, 0, b.length);
                    String input = new String(b, 0, b.length);
                    final String recvInput = input.trim();

                    if (ac == -1)
                        break;

                    mHandler.post(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            setToast(recvInput);
                        }

                    });
                }
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        setToast("연결이 종료되었습니다.");
                    }

                });
            } catch (Exception e) {
                final String recvInput = "연결에 문제가 발생하여 종료되었습니다..";
                Log.d("SetServer", e.getMessage());
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

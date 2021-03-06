package com.example.gameframework;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	AppDirector mAppDirector;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
       // setContentView(new MyView(this));  //한장의 그림과 같음
       
        //custom view를 등록
        setContentView(new GameView(this)); //영화와 같음
        
        Log.d("AppDirector Check","Appdirector 메모리 주소 값 : "+AppDirector.getInstance());
        Log.d("AppDirector Check","Appdirector 메모리 주소 값 : "+AppDirector.getInstance());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
        
        
    }
    
}


//custom view 생성
//View : 한장 짜리 그림
class MyView extends View{

	public MyView(Context context) {
		super(context);
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		//바탕색을 파란색으로 칠하기
		canvas.drawColor(Color.BLUE);
		//회색 원 그리기
		Paint paint = new Paint();  // 붓
		paint.setColor(Color.LTGRAY);
		canvas.drawCircle(200, 200, 100, paint);
		
		super.onDraw(canvas);
	}
	
}


//싱글턴 패턴과 멀티쓰레드 문제
class A extends Thread{

	@Override
	public void run() {
		
	}
	
}
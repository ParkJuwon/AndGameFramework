package com.example.gameframework;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
       // setContentView(new MyView(this)); //custom view�� ���
        setContentView(new GameView(this));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
        
        
    }
    
}


//custom view ����
//View : ���� ¥�� �׸�
class MyView extends View{

	public MyView(Context context) {
		super(context);
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		//�������� �Ķ������� ĥ�ϱ�
		canvas.drawColor(Color.BLUE);
		//ȸ�� �� �׸���
		Paint paint = new Paint();  // ��
		paint.setColor(Color.LTGRAY);
		canvas.drawCircle(200, 200, 100, paint);
		
		super.onDraw(canvas);
	}
	
}
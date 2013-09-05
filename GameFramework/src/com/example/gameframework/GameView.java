package com.example.gameframework;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

//SurfaceView 상속 : 필름
//Callback 구현 : 필름의 상태가 바뀔 때 호출되는 인터페이스 , 3가지의 필름 상태를 정의
/**
 * @author user
 *
 */
public class GameView extends SurfaceView implements Callback{
	RenderingThread mRenderingThread;
	Paint mPaint;
//	public GameView(Context context, AttributeSet attrs, int defStyle) {
//		super(context, attrs, defStyle);
//	}

//	public GameView(Context context, AttributeSet attrs) {
//		super(context, attrs);
//	}
	
	public GameView(Context context) {
		super(context);
		
		//1. 필름과 연필을 만들어서 에니메이션 작가를 고용(생성) 해서 넘겨줌
		SurfaceHolder mHolder = getHolder();
		mHolder.addCallback(this); //현제 콜백을 add
		mRenderingThread = new RenderingThread(this, mHolder);
	
		mPaint = new Paint();
		mPaint.setColor(Color.LTGRAY);
	}

	// 필름을 카메라에 끼운 상태
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		//2. 에니메이션 작가에게 일을 시켜야 됨.
		mRenderingThread.start();
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		
	}

	//5. 애니메이션 작가를 고용 해제(쓰레드 정지)
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		mRenderingThread.setRunning(false);
		
	}
	
	
	
	/**
	 * 게임 프레임 워크가 완성이 되면 update() 와 present() 메소드에 대해
	 * 개발을 하면 된다. 
	 */
	//3. 필름 상태를 update
	public void update(){
		
	}
	
	//4. 필름에 그림 그리기
	public void present(Canvas canvas){
		//바탕색을 파란색으로 칠하기
		canvas.drawColor(Color.BLUE);
		//회색 원 그리기
		//canvas.drawCircle(200, 200, 100, mPaint);
		
		//Todo 숙제 : deltaTime 과  FPS 찍기 100,100 deltaTime, 100,200 에 FPS 찍기
		//힌트 : drawText와 위에 선언된 mPaint
		
		canvas.drawText("DeletaTime : "+mRenderingThread.deltaTime, 100, 100, mPaint);
		canvas.drawText("FPS : "+ 1000f/mRenderingThread.deltaTime, 100, 200, mPaint);
	}
}

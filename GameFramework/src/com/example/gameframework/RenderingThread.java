package com.example.gameframework;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class RenderingThread extends Thread {

	private volatile boolean mIsRunning = true; // thread 상 변수 동기화를 위해 volatile을 선언
	private GameView mGameView;
	private SurfaceHolder mSurfaceHolder;
	
	public static long deltaTime;
	
	public RenderingThread(GameView gameView, SurfaceHolder mHolder) {
		mGameView = gameView;
		mSurfaceHolder = mHolder;
	}



	@Override
	public void run() {
		
		// 한번의 루프가 에니메이션 작가가 한장의 그림을 그린다. => deltaTime
		//1. 상태 업데이트 & 그리기
		Canvas canvas = null; //도화지 생성
		long currTime = 0;
		while(mIsRunning){
			currTime = System.currentTimeMillis();
			
			//도화지를 정지시키고 그림 그리기
			canvas = mSurfaceHolder.lockCanvas();
			//update
			mGameView.update();
			
			//present
			mGameView.present(canvas);
			//도화지를 떼내서 필름에 갖다 붙이기
			mSurfaceHolder.unlockCanvasAndPost(canvas);
			
			
			deltaTime = System.currentTimeMillis() - currTime;
			
			
			
			//로그를 보기
			//Log.d("real_p", "deltaTime : "+deltaTime);
		}
	}
	
	public void setRunning(boolean isRunning){
		mIsRunning = isRunning;
	
	}

}

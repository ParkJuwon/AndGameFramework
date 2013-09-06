package com.example.gameframework;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class RenderingThread extends Thread {

	private volatile boolean mIsRunning = true; // thread 상 변수 동기화를 위해 volatile을 선언
	private GameView mGameView;
	private SurfaceHolder mSurfaceHolder;
	
	private long deltaTime =1;
	
	public RenderingThread(GameView gameView, SurfaceHolder mHolder) {
		//필름과 연필를 받음
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
			//종료 시, canvas 가 null이 뜰 수 있기 때문에 예외처리를 해준다
			try{
				//도화지를 정지시키고 그림 그리기
				canvas = mSurfaceHolder.lockCanvas();
				
				//update
				mGameView.update();
				
				//present
				mGameView.present(canvas);
				
				//도화지를 떼내서 필름에 갖다 붙이기
				mSurfaceHolder.unlockCanvasAndPost(canvas);
			}catch(Exception e){

			}finally{
			
			}
			
			deltaTime = System.currentTimeMillis() - currTime;
			AppDirector.getInstance().setmDeltaTime(deltaTime);
			//로그를 보기
			//Log.d("real_p", "deltaTime : "+deltaTime);
		}
	}
	
	public void setRunning(boolean isRunning){
		mIsRunning = isRunning;
	
	}

}

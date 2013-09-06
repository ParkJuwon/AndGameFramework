package com.example.gameframework;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;

public class RenderingThread extends Thread {

	private volatile boolean mIsRunning = true; // thread 상 변수 동기화를 위해 volatile을 선언
	private GameView mGameView;
	private SurfaceHolder mSurfaceHolder;
	
	private long deltaTime =1;
	
	//작업 공간 정의
	private Canvas virtualCanvas; //가상 작업공간
	private Rect dstRect; // 실제 디바이스
	private Bitmap mBitmap;
	
	public RenderingThread(GameView gameView, SurfaceHolder mHolder) {
		//필름과 연필를 받음
		mGameView = gameView;
		mSurfaceHolder = mHolder;
		
		//작업 공간 할당
		mBitmap = Bitmap.createBitmap(1080, 1920, Config.ARGB_8888);
		//숙제 : 하드코딩 제거, AppDirector로 정의하고 가져오도록
		virtualCanvas = new Canvas();
		virtualCanvas.setBitmap(mBitmap);
		dstRect = new Rect();
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
				//mGameView.present(canvas);
				
				//작업공간을 가상의 작업 공간으로 변경
				mGameView.present(virtualCanvas); 
				
			}catch(Exception e){

			}finally{
				//도화지를 떼내서 필름에 갖다 붙이기
				mSurfaceHolder.unlockCanvasAndPost(canvas);
			
				//dstRect에 실제 디바이스 크기를 할당
				canvas.getClipBounds(dstRect); 
				//작업공간을 실제 디바이스 크기로 늘리기
				canvas.drawBitmap(mBitmap, null, dstRect, null);
				
				//숙제 : dstRect크기 Log.d로 찍어보기
				Log.d("dstRect Sizte","dstRect Sizte = "+dstRect.width()+" * "+dstRect.height());
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

package com.example.gameframework;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class RenderingThread extends Thread {

	private volatile boolean mIsRunning = true; // thread �� ���� ����ȭ�� ���� volatile�� ����
	private GameView mGameView;
	private SurfaceHolder mSurfaceHolder;
	
	public static long deltaTime;
	
	public RenderingThread(GameView gameView, SurfaceHolder mHolder) {
		mGameView = gameView;
		mSurfaceHolder = mHolder;
	}



	@Override
	public void run() {
		
		// �ѹ��� ������ ���ϸ��̼� �۰��� ������ �׸��� �׸���. => deltaTime
		//1. ���� ������Ʈ & �׸���
		Canvas canvas = null; //��ȭ�� ����
		long currTime = 0;
		while(mIsRunning){
			currTime = System.currentTimeMillis();
			
			//��ȭ���� ������Ű�� �׸� �׸���
			canvas = mSurfaceHolder.lockCanvas();
			//update
			mGameView.update();
			
			//present
			mGameView.present(canvas);
			//��ȭ���� ������ �ʸ��� ���� ���̱�
			mSurfaceHolder.unlockCanvasAndPost(canvas);
			
			
			deltaTime = System.currentTimeMillis() - currTime;
			
			
			
			//�α׸� ����
			//Log.d("real_p", "deltaTime : "+deltaTime);
		}
	}
	
	public void setRunning(boolean isRunning){
		mIsRunning = isRunning;
	
	}

}

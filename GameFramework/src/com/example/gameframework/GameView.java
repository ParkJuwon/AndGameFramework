package com.example.gameframework;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

//SurfaceView ��� : �ʸ�
//Callback ���� : �ʸ��� ���°� �ٲ� �� ȣ��Ǵ� �������̽� , 3������ �ʸ� ���¸� ����
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
		
		//1. �ʸ��� ������ ���� ���ϸ��̼� �۰��� ���(����) �ؼ� �Ѱ���
		SurfaceHolder mHolder = getHolder();
		mHolder.addCallback(this); //���� �ݹ��� add
		mRenderingThread = new RenderingThread(this, mHolder);
	
		mPaint = new Paint();
		mPaint.setColor(Color.LTGRAY);
	}

	// �ʸ��� ī�޶� ���� ����
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		//2. ���ϸ��̼� �۰����� ���� ���Ѿ� ��.
		mRenderingThread.start();
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		
	}

	//5. �ִϸ��̼� �۰��� ��� ����(������ ����)
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		mRenderingThread.setRunning(false);
		
	}
	
	
	
	/**
	 * ���� ������ ��ũ�� �ϼ��� �Ǹ� update() �� present() �޼ҵ忡 ����
	 * ������ �ϸ� �ȴ�. 
	 */
	//3. �ʸ� ���¸� update
	public void update(){
		
	}
	
	//4. �ʸ��� �׸� �׸���
	public void present(Canvas canvas){
		//�������� �Ķ������� ĥ�ϱ�
		canvas.drawColor(Color.BLUE);
		//ȸ�� �� �׸���
		//canvas.drawCircle(200, 200, 100, mPaint);
		
		//Todo ���� : deltaTime ��  FPS ��� 100,100 deltaTime, 100,200 �� FPS ���
		//��Ʈ : drawText�� ���� ����� mPaint
		
		canvas.drawText("DeletaTime : "+mRenderingThread.deltaTime, 100, 100, mPaint);
	}
}

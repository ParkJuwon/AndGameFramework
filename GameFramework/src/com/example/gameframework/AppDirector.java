package com.example.gameframework;

public class AppDirector {

	// 싱글턴 패턴 시작---------------------------------------------------------
	// App을 전체에 반드시 하나만 존재.
	// ==> 외부에서 new로 인스턴스를 생성 못하게 한다. ==> 생성자를 private
	private AppDirector(){
		
	}
	private static AppDirector sAppDirector;
	
	//public static synchronized AppDirector getInstance(){  //함수 전체에 sync를 걸면 느려질 수도 있으므로..
   public static  AppDirector getInstance(){
		if(sAppDirector==null){
			synchronized(AppDirector.class){ 
				//동기 화 전 한번 들어올 수 있으므로 null check를 한번 더 해 주어야 한다.
				if(sAppDirector == null){
					sAppDirector = new AppDirector();
				}
			}
		}
		return sAppDirector;
	}
	// 싱글턴 패턴 종료---------------------------------------------------------
   
   private long mDeltaTime;

	public long getmDeltaTime() {
		return mDeltaTime;
	}

	public void setmDeltaTime(long mDeltaTime) {
		this.mDeltaTime = mDeltaTime;
	}
   
}

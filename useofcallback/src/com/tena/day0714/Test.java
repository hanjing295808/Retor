package com.tena.day0714;

public class Test {

	/**
	 * @param args
	 */
	private ICallback callback;
	public void setCallback(ICallback callback){
		this.callback=callback;
	}
	public void doSth(){
		callback.postExec();
	}
	public static void main(String[] args) {
		Test test=new Test();
		test.setCallback(new ICallback() {
			
			@Override
			public void postExec() {
				System.out.println("º¯Êý»Øµ÷");
			}
		});
		test.doSth();
	}

}























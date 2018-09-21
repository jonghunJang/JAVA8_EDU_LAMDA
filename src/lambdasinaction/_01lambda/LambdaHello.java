package lambdasinaction._01lambda;

public class LambdaHello {
	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("dddddddddddd");
			}
		}).start();
		
		new Thread(() ->  {System.out.println("tttttttttttttttttttttt");}).start();
	}
}

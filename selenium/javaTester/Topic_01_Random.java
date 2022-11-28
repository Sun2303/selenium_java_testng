package javaTester;

import java.util.Random;

public class Topic_01_Random {

	public static void main(String[] args) {
		//utilities thư viện = tiện ích
		Random rand = new Random();
		System.out.println(rand.nextInt(9999));
		System.out.println(rand.nextInt(999));
		System.out.println(rand.nextInt(99));
	}

}

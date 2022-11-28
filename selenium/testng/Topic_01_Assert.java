package testng;

import org.testng.Assert;

public class Topic_01_Assert {
	public static void main(String[] arg) {	
		String 	fullName = "Automation testing";
		
		//Mong đợi điều kiện trả về là đúng
		Assert.assertTrue(fullName.contains("Automation"));
		
		//Mong đợi điều kiện trả về là sai
		Assert.assertFalse(fullName.contains("Manual"));
		
		//Mong đợi 2 điều kiện bằng nhau
		Assert.assertEquals(fullName, "Automation testing");
	}
}

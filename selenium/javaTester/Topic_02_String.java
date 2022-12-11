package javaTester;

public class Topic_02_String {
	public static void main(String[] args) {
		String url = "http://the-internet.herokuapp.com/basic_auth";
		String username = "admin";
		String password = "admin";
		
		System.out.println(url);
		
		String[] arrayUrl = url.split("//"); // => Sẽ tách ra:
		// 0 - http: và 1 - the-internet.herokuapp.com/basic_auth
		
		url = arrayUrl[0] + "//" + username + ":" + password + "@" + arrayUrl[1];
		System.out.println(url);
	}
}

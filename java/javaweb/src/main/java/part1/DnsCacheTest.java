package part1;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class DnsCacheTest {

	public static void main(String[] args) {
		try { 
			//解析DNS
			InetAddress address = InetAddress.getByName("www.baidu.com");
			
			System.out.println(address.getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

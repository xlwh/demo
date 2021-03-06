package part1;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

@SuppressWarnings("deprecation")
public class TestHttpClient {

	public static void main(String[] args) {
		HttpPost post;
		@SuppressWarnings("resource")
		HttpClient client  = new DefaultHttpClient();
		post = new HttpPost("http://www.cnblogs.com/loveyakamoz/archive/2011/07/21/2112804.html");
		post.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=GBK");
		post.addHeader("Host", "www.baidu.com");
		try {
			HttpResponse resp = client.execute(post);
			System.out.println("exec result:"+resp.getStatusLine());
			System.out.println("return content length:"+resp.getEntity().getContentLength());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

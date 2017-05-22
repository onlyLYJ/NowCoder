package epg;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class HttpRequest {

	public static void sendGet(String url, String param, String cookie) {
		
		InputStream in = null;
		FileOutputStream fos = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("connection", "keep-alive");
			connection.setRequestProperty("Upgrade-Insecure-Requests", "1");
			connection.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 10.0; WOW64; Trident/7.0; .NET4.0C; .NET4.0E; .NET CLR 2.0.50727; .NET CLR 3.0.30729; .NET CLR 3.5.30729)");
			connection.setRequestProperty("Host", "172.26.203.62");
			connection.setRequestProperty("Referer",
					"http://172.26.203.62/smg_xtbd/bianpai/playListAction.do?method=egpList");
			connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
			connection.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
			String cookie1 = "theme=THEME1; userName=liyijun; modules=m_work|m_message; " + cookie;
			System.out.println("cookie 1 ===" + cookie1);
			connection.setRequestProperty("Cookie", cookie1);

			// 发送POST请求必须设置如下两行
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = connection.getInputStream();
			
			fos = new FileOutputStream("D:\\b.zip");

			byte[] buffer = new byte[1024];
			int len = -1;
			while((len=in.read(buffer)) != -1) {
				fos.write(buffer,0,len); 
			}
			fos.flush();
			
			in.close();
			fos.close();

		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}

		finally {
			try {
				if (in != null) {
					in.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}

	}

	public static String getCookie(String path, String params) throws Exception {
		HttpURLConnection httpConn = null;
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			URL url = new URL(path);
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setRequestMethod("POST");
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);

			// 发送post请求参数
			out = new PrintWriter(httpConn.getOutputStream());
			out.println(params);
			out.flush();
			
			// 读取响应
			if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
//				StringBuffer content = new StringBuffer();
//				String tempStr = "";
//				in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
//				while ((tempStr = in.readLine()) != null) {
//					content.append(tempStr);
//				}
//				
				Map<String, List<String>> map = httpConn.getHeaderFields();
				
				List<String> cookies = map.get("Set-Cookie");
				String cookie = cookies.get(1);
				cookie = cookie.substring(0, cookie.indexOf(";"));
				
//				String http = "http://172.26.203.62/smg_xtbd/bianpai/playListAction.do";
//				String p1 = "playDateAfter=2017-05-22&playDateBefor=2017-05-24&fileType=XLS&channelId=L&method=export";
//				HttpRequest.sendGet(http, p1, cookie);
//				
				return cookie;

			} else {
				throw new RuntimeException("请求出现了问题!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			in.close();
			out.close();
			httpConn.disconnect();
		}
		return null;
	}


	public static void main(String[] args) {
		// 发送 GET 请求
		String login = "http://172.26.203.62/smg_xtbd/login.do";
		String password = "userName=liyijun&password=sitv0601&rememberMe=on&method=login&button1=+%B5%C7%C2%BC+";

		String epg = "http://172.26.203.62/smg_xtbd/bianpai/playListAction.do";
		String param = "playDateAfter=2017-05-22&playDateBefor=2017-05-24&fileType=XLS&channelId=L&method=export";
		
		
		try {
			String cookie = HttpRequest.getCookie(login, password);
			HttpRequest.sendGet(epg, param, cookie);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
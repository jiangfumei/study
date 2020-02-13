package com.java.study.util;



import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * request --HTTP/HTTPS 
 * method --GET/POST
 * @date 2019 8 16
 */
public class HttpUtils {

	static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
	private static final int SO_TIMEOUT = 60000;
	private static final int CONNECT_TIMEOUT = 60000;
	private static final String SSL = "SSL";
	public static final String SSL_V3 = "SSLv3";

	/**
	 *  get 请求
	 * @param params 参数
	 * @param url 地址
	 * @return String
	 */
	public static String invokeGet(Map<String, Object> params, String url, int connectTimeout) {
		return invokeGet(params,url,SO_TIMEOUT,connectTimeout);
	}
	/* get 请求
	* @param params 参数
	* @param url 地址
	* @return String
	*/
	public static String invokeGet(Map<String, Object> params, String url) {
		return invokeGet(params,url,SO_TIMEOUT,CONNECT_TIMEOUT);
	}
	/**
	 *  get 请求
	 * @param params 参数
	 * @param url 地址
	 * @param socketTimeout socketTimeout
	 * @param connectTimeout connectTimeout
	 * @return String
	 */
	public static String invokeGet(Map<String, Object> params, String url, int socketTimeout,int connectTimeout) {
		// 组织请求参数
		StringBuilder urlParams = new StringBuilder();
		String result = "";
		CloseableHttpClient closeableHttpClient = null;
		try {
			if (null != params) {
				for (Map.Entry<String, Object> entry : params.entrySet()) {
				       if(entry.getValue()!=null){
					urlParams.append(entry.getKey()).append("=")
						.append(URLEncoder.encode(entry.getValue().toString(), "utf-8")).append("&");
				       }else{
				           logger.info("[invokeGet]key="+entry.getKey()+",value=null");
				       }
				}

				if (!params.isEmpty()) {
					urlParams.deleteCharAt(urlParams.length() - 1);
				}
				url += "?" + urlParams;
			}
			logger.info("# GET 请求URL�?:" + url);

			HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
			if(url.toLowerCase().startsWith("https"))
				httpClientBuilder.setSSLSocketFactory(createGenerousSSLSocketFactory(SSL));
			closeableHttpClient = httpClientBuilder.build();
			
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).
					setConnectTimeout(connectTimeout).build();//设置请求和传输超时时�?
			HttpGet get = new HttpGet(url);
			get.setConfig(requestConfig);
			HttpResponse resp = closeableHttpClient.execute(get);
			result = convertResponseBytes2String(resp);
		} catch (Exception e) {
			logger.error("#httpGet error", e);
		} finally{
			try {
				if(closeableHttpClient!=null)
					closeableHttpClient.close();
			} catch (IOException e) {
				logger.info("# close client error" + result);
			}
		}
		logger.info("# GET JSON end ");
		return result;
	}
	/**
	 *  Post 请求
	 * @param jsonStrData 参数
	 * @param url 地址
	 * @return String
	 */
	public static String invokePost(String jsonStrData, String url) {
		return invokePost(jsonStrData, url, SO_TIMEOUT, CONNECT_TIMEOUT);
	}
	public static String invokePost(String jsonStrData, String url, String sslType) {
		return invokePost(jsonStrData, url, SO_TIMEOUT, CONNECT_TIMEOUT, sslType);
	}
	public static String invokePost(String jsonStrData, String url, int socketTimeout,int connectTimeout) {
		return invokePost(jsonStrData, url, SO_TIMEOUT, CONNECT_TIMEOUT, SSL);
	}
	public static String invokePostExceptionHandler(String jsonStrData, String url) throws ClientProtocolException, IOException {
		return invokePostExceptionHandler(jsonStrData, url, SO_TIMEOUT, CONNECT_TIMEOUT, SSL);
	}
	/**
	 * Post 请求
	 * @param jsonStrData 参数
	 * @param url 地址
	 * @param socketTimeout socketTimeout
	 * @param connectTimeout connectTimeout
	 * @return String
	 */
	public static String invokePost(String jsonStrData, String url, int socketTimeout,int connectTimeout,String sslType) {
		logger.info("# POST JSON 请求URL为：" + url);
		logger.info("# POST JSON 数据为：" + jsonStrData);
		String result = "";
		CloseableHttpClient closeableHttpClient = null;
		try {
			HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
			if(url.toLowerCase().startsWith("https")){
				httpClientBuilder.setSSLSocketFactory(createGenerousSSLSocketFactory(sslType));
			}
			HttpEntity entity = new StringEntity(jsonStrData, "UTF-8");
			closeableHttpClient = httpClientBuilder.build();
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).
						setConnectTimeout(connectTimeout).build();//设置请求和传输超时时�?
			HttpPost post = new HttpPost(url);
			post.setConfig(requestConfig);
			post.setEntity(entity);
			
			post.setHeader("Content-type", "application/json");
			HttpResponse resp = closeableHttpClient.execute(post);
			result = convertResponseBytes2String(resp);
		} catch (Exception e) {
			logger.error("#httpPOST error", e);
		} finally{
			try {
				if(closeableHttpClient!=null)
					closeableHttpClient.close();
			} catch (IOException e) {
				logger.info("# close client error" + result);
			}
		}
		logger.info("# POST JSON end");
		return result;
	}
	public static String invokeUrlencodedPost(String jsonStrData, String url) {
		logger.info("# POST x-www-form-urlencoded 请求URL为：" + url);
		logger.info("# POST x-www-form-urlencoded 数据为：" + jsonStrData);
		String result = "";
		CloseableHttpClient closeableHttpClient = null;
		try {
			HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
			if(url.toLowerCase().startsWith("https")){
				httpClientBuilder.setSSLSocketFactory(createGenerousSSLSocketFactory(SSL));
			}
			HttpEntity entity = new StringEntity(jsonStrData, "UTF-8");
			closeableHttpClient = httpClientBuilder.build();
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(SO_TIMEOUT).
						setConnectTimeout(CONNECT_TIMEOUT).build();//设置请求和传输超时时�?
			HttpPost post = new HttpPost(url);
			post.setConfig(requestConfig);
			post.setEntity(entity);
			
			post.setHeader("Content-type", "application/x-www-form-urlencoded");
			HttpResponse resp = closeableHttpClient.execute(post);
			result = convertResponseBytes2String(resp);
		} catch (Exception e) {
			logger.error("#httpPOST error", e);
		} finally{
			try {
				if(closeableHttpClient!=null)
					closeableHttpClient.close();
			} catch (IOException e) {
				logger.info("# close client error" + result);
			}
		}
		logger.info("# POST x-www-form-urlencoded end");
		return result;
	}
	/**
	 *  Post 请求
	 * @param jsonStrData 参数
	 * @param url 地址
	 * @param socketTimeout socketTimeout
	 * @param connectTimeout connectTimeout
	 * @return String
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String invokePostExceptionHandler(String jsonStrData, String url, int socketTimeout,int connectTimeout,String sslType) throws ClientProtocolException, IOException {
		logger.info("# POST JSON 请求URL为：" + url);
		logger.info("# POST JSON 数据为：" + jsonStrData);
		String result = "";
		CloseableHttpClient closeableHttpClient = null;
		try {
			HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
			if(url.toLowerCase().startsWith("https")){
				httpClientBuilder.setSSLSocketFactory(createGenerousSSLSocketFactory(sslType));
			}
			HttpEntity entity = new StringEntity(jsonStrData, "UTF-8");
			closeableHttpClient = httpClientBuilder.build();
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).
						setConnectTimeout(connectTimeout).build();//设置请求和传输超时时�?
			HttpPost post = new HttpPost(url);
			post.setConfig(requestConfig);
			post.setEntity(entity);
			
			post.setHeader("Content-type", "application/json");
			HttpResponse resp = closeableHttpClient.execute(post);
			result = convertResponseBytes2String(resp);
		} finally{
			try {
				if(closeableHttpClient!=null)
					closeableHttpClient.close();
			} catch (IOException e) {
				logger.info("# close client error" + result);
			}
		}
		logger.info("# POST JSON end");
		return result;
	}

	/**
	 * 发送 Post 请求
	 * @param url 地址
	 * @param socketTimeout socketTimeout
	 * @param connectTimeout connectTimeout
	 * @return String
	 */
	public static String invokePost(String url, Map<String, String> params){
		return invokePost(url, params, null, SO_TIMEOUT, CONNECT_TIMEOUT);
	}
	
	/**
	 * 发送 Post 请求
	 * @param url 地址
	 * @param socketTimeout socketTimeout
	 * @param connectTimeout connectTimeout
	 * @return String
	 */
	public static String invokePost(String url, Map<String, String> params, File file){
		return invokePost(url, params, file, SO_TIMEOUT, CONNECT_TIMEOUT);
	}
	
	/**
	 * 发送 Post 请求
	 * @param url 地址
	 * @param socketTimeout socketTimeout
	 * @param connectTimeout connectTimeout
	 * @return String
	 */
	public static String invokePost(String url, Map<String, String> params, File file, int socketTimeout,int connectTimeout){
		logger.info("# POST JSON 请求URL为：" + url); 
		String result = "";
		CloseableHttpClient closeableHttpClient = null;
		try {
			HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
			if(url.toLowerCase().startsWith("https"))
				httpClientBuilder.setSSLSocketFactory(createGenerousSSLSocketFactory("SSL"));
			closeableHttpClient = httpClientBuilder.build();
			ContentType contentType = ContentType.create("text/plain",Charset.forName("UTF-8"));
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			if(file!=null){
				builder.addBinaryBody("file", file);
			}
			if (null != params) {
				for (Map.Entry<String, String> entry : params.entrySet()) {
				       if(entry.getValue()!=null){
				    	   builder.addTextBody(entry.getKey(), entry.getValue(), contentType);
				       }else{
				           logger.info("[invokePost]key="+entry.getKey()+",value=null");
				       }
				}
			}
			
			
	        HttpEntity entity = builder.build();
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).
						setConnectTimeout(connectTimeout).build();
			HttpPost post = new HttpPost(url);
			post.setConfig(requestConfig);
			post.setEntity(entity);
			
			HttpResponse resp = closeableHttpClient.execute(post);
			result = convertResponseBytes2String(resp);
		} catch (Exception e) {
			logger.error("#httpPOST error", e);
		} finally{
			try {
				if(closeableHttpClient!=null)
					closeableHttpClient.close();
			} catch (IOException e) {
				logger.info("# close client error" + result);
			}
		}
		logger.info("# POST JSON end");
		return result;
	}

	/**
	 * HTTPS 证书
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	public static void trustAllHttpsCertificates() throws NoSuchAlgorithmException, KeyManagementException {
		TrustManager[] trustAllCerts = new TrustManager[1];
		TrustManager tm = new miTM();
		trustAllCerts[0] = tm;
		SSLContext sc = SSLContext.getInstance("SSL"); // SSL,TLS
		sc.init(null, trustAllCerts, null);
		javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	}

	static class miTM implements TrustManager, X509TrustManager {
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public boolean isServerTrusted(X509Certificate[] certs) {
			return true;
		}

		public boolean isClientTrusted(X509Certificate[] certs) {
			return true;
		}

		public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
			return;
		}

		public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
			return;
		}
	}
	
	/**
	 * 转换响应数据
	 * @param resp
	 * @return
	 */
	private static String convertResponseBytes2String(HttpResponse resp) {
		String result = "";
		InputStream instream  =null;
		try {
			instream  = resp.getEntity().getContent();
			byte[] respBytes = IOUtils.toByteArray(instream );
			result = new String(respBytes, Charset.forName("UTF-8"));
		} catch (Exception e) {
			logger.error("# error", e);
		} finally{
			try {
				if(instream != null)
					instream .close();
			} catch (IOException e) {
				logger.error("# close Io error", e);
			}
		}
		return result;
	}
	
	/***
     * @param sslType 
	 * @return SSLConnectionSocketFactory that bypass certificate check and bypass HostnameVerifier
     */
    @SuppressWarnings("deprecation")
	private static SSLConnectionSocketFactory createGenerousSSLSocketFactory(String sslType) {
        SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance(sslType);
            sslContext.init(null, new TrustManager[]{createGenerousTrustManager()}, new SecureRandom());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("create SSLConnectionSocketFactory error",e);
            return null;
        }
        return new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
    }

    private static X509TrustManager createGenerousTrustManager() {
        return new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] cert, String s) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] cert, String s) throws CertificateException {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
    }

	/**
	 * 模拟请求
	 *
	 * @param url        资源地址
	 * @param map    参数列表
	 * @param encoding    编码
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static String send(String url, Map<String,String> headers,Map<String,String> map,String encoding) throws ParseException, IOException {
		String body = "";

		//创建httpclient对象
		CloseableHttpClient client = HttpClients.createDefault();
		//创建post方式请求对象
		HttpPost httpPost = new HttpPost(url);

		for (Map.Entry<String, String> e : headers.entrySet()) {
			httpPost.setHeader(e.getKey(), e.getValue());
		}
		//装填参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if(map!=null){
			for (Map.Entry<String, String> entry : map.entrySet()) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		//设置参数到请求对象中
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));

		System.out.println("请求地址："+url);
		System.out.println("请求参数："+nvps.toString());

		//设置header信息
		//指定报文头【Content-type】、【User-Agent】
//		httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
//		httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

		//执行请求操作，并拿到结果（同步阻塞）
		CloseableHttpResponse response = client.execute(httpPost);
		//获取结果实体
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			//按指定编码转换结果实体为String类型
			body = EntityUtils.toString(entity, encoding);
		}
		EntityUtils.consume(entity);
		//释放链接
		response.close();
		return body;
	}
}

package com.ccx.models.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 
 * 封装了采用HttpClient发送HTTP请求的方法
 * 
 * @see 本工具所采用的是HttpComponents-Client-4.3.2
 * @see ========================================================================
 *      == =========================
 * @see sendPostSSLRequest 发送https post请求，  兼容 http post 请求    
 * @see sendGetSSLRequest  发送https get请求，  兼容 http get 请求  
 * @create 2016年10月12日18:52:06
 * @author lilin
 */

public class HttpClientUtil {
	
	/**
	 * 连接超时
	 */
	public static final int connTimeout=10000;
    /**
     * 响应超时
     */
    public static final int readTimeout=600000;
    /**
     * 默认字符编码
     */
    public static final String charset="UTF-8";
    private static HttpClient client = null;
    
    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(128);
        cm.setDefaultMaxPerRoute(128);
        client = HttpClients.custom().setConnectionManager(cm).build();
    }
    
    public static String postParameters(String url, String parameterStr){
        return sendPostSSLRequest(url,parameterStr,charset,"application/json");
    }
    
    public static String postParameters(String url, String parameterStr,String charset){
        return sendPostSSLRequest(url,parameterStr,charset,"application/json");
    }
    
    public static String postParameters(String url, Map<String, String> params) throws ConnectTimeoutException,  
     SocketTimeoutException, Exception {
         return postForm(url, params, null, connTimeout, readTimeout);
     }
    
    public static String postParameters(String url, Map<String, String> params, Integer connTimeout,Integer readTimeout) throws ConnectTimeoutException,  
    SocketTimeoutException, Exception {
         return postForm(url, params, null, connTimeout, readTimeout);
     }
      
    public static String get(String url) {  
        return sendGetSSLRequest(url, charset);  
    }
    
    public static String get(String url, String charset) {  
        return sendGetSSLRequest(url, charset);  
    } 

    /** 
     * 发送一个 Post 请求, 使用指定的字符集编码. 
     *  
     * @param url 
     * @param body RequestBody 
     * @param mimeType 例如 application/xml "application/x-www-form-urlencoded" a=1&b=2&c=3
     * @param charset 编码 
     * @param connTimeout 建立链接超时时间,毫秒. 
     * @param readTimeout 响应超时时间,毫秒. 
     * @return ResponseBody, 使用指定的字符集编码. 
     * @throws ConnectTimeoutException 建立链接超时异常 
     * @throws SocketTimeoutException  响应超时 
     * @throws Exception 
     */  
    public static String sendPostSSLRequest(String url, String body ,String charset,String mimeType){
        HttpClient client = null;
        HttpPost post = new HttpPost(url);
        String result = "通信失败";
        try {
            if (StringUtils.isNotBlank(body)) {
                HttpEntity entity = new StringEntity(body, ContentType.create(mimeType, charset));
                post.setEntity(entity);
            }
            // 设置参数
            Builder customReqConf = RequestConfig.custom();
            customReqConf.setConnectTimeout(connTimeout);
            customReqConf.setSocketTimeout(readTimeout);
            post.setConfig(customReqConf.build());

            HttpResponse res;
            if (url.startsWith("https")) {
                // 执行 Https 请求.
				client = createSSLInsecureClient();
				
				res = client.execute(post);
				
            } else {
                // 执行 Http 请求.
                client = HttpClientUtil.client;
                res = client.execute(post);
            }
            result = IOUtils.toString(res.getEntity().getContent(), charset);
        } catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
            post.releaseConnection();
            if (url.startsWith("https") && client != null&& client instanceof CloseableHttpClient) {
                try {
					((CloseableHttpClient) client).close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        }
        return result;
    }  
    
    
    /** 
     * 提交form表单 
     *  
     * @param url 
     * @param params 
     * @param connTimeout 
     * @param readTimeout 
     * @return 
     * @throws ConnectTimeoutException 
     * @throws SocketTimeoutException 
     * @throws Exception 
     */  
    public static String postForm(String url, Map<String, String> params, Map<String, String> headers, Integer connTimeout,Integer readTimeout) throws ConnectTimeoutException,  
            SocketTimeoutException, Exception {  
  
        HttpClient client = null;  
        HttpPost post = new HttpPost(url);  
        try {  
            if (params != null && !params.isEmpty()) {  
                List<NameValuePair> formParams = new ArrayList<org.apache.http.NameValuePair>();  
                Set<Entry<String, String>> entrySet = params.entrySet();  
                for (Entry<String, String> entry : entrySet) {  
                    formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));  
                }  
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);  
                post.setEntity(entity);  
            }
            
            if (headers != null && !headers.isEmpty()) {  
                for (Entry<String, String> entry : headers.entrySet()) {  
                    post.addHeader(entry.getKey(), entry.getValue());  
                }  
            }  
            // 设置参数  
            Builder customReqConf = RequestConfig.custom();  
            if (connTimeout != null) {  
                customReqConf.setConnectTimeout(connTimeout);  
            }  
            if (readTimeout != null) {  
                customReqConf.setSocketTimeout(readTimeout);  
            }  
            post.setConfig(customReqConf.build());  
            HttpResponse res = null;  
            if (url.startsWith("https")) {  
                // 执行 Https 请求.  
                client = createSSLInsecureClient();  
                res = client.execute(post);  
            } else {  
                // 执行 Http 请求.  
                client = HttpClientUtil.client;  
                res = client.execute(post);  
            }  
            return IOUtils.toString(res.getEntity().getContent(), "UTF-8");  
        } finally {  
            post.releaseConnection();  
            if (url.startsWith("https") && client != null  
                    && client instanceof CloseableHttpClient) {  
                ((CloseableHttpClient) client).close();  
            }  
        }  
    } 
    
    
    
    
    /** 
     * 发送一个 GET 请求 
     *  
     * @param url 
     * @param charset 
     * @param connTimeout  建立链接超时时间,毫秒. 
     * @param readTimeout  响应超时时间,毫秒. 
     * @return 
     * @throws ConnectTimeoutException   建立链接超时 
     * @throws SocketTimeoutException   响应超时 
     * @throws Exception 
     */  
    public static String sendGetSSLRequest(String url, String charset){ 
        
        HttpClient client = null;  
        HttpGet get = new HttpGet(url);  
        String result = "通信失败";  
        try {  
            // 设置参数  
            Builder customReqConf = RequestConfig.custom();  
            customReqConf.setConnectTimeout(connTimeout);  
            customReqConf.setSocketTimeout(readTimeout);  
            get.setConfig(customReqConf.build());  
  
            HttpResponse res = null;  
  
            if (url.startsWith("https")) {  
                // 执行 Https 请求.  
				client = createSSLInsecureClient();
				res = client.execute(get);
				
            } else {  
                // 执行 Http 请求.  
                client = HttpClientUtil.client;  
                res = client.execute(get);  
            }  
  
            result = IOUtils.toString(res.getEntity().getContent(), charset);  
        } catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}     finally {  
            get.releaseConnection();  
            if (url.startsWith("https") && client != null && client instanceof CloseableHttpClient) {  
                try {
					((CloseableHttpClient) client).close();
				} catch (IOException e) {
					e.printStackTrace();
				}  
            }  
        }  
        return result;  
    }  
    
    
    /** 
     * 从 response 里获取 charset 
     *  
     * @param ressponse 
     * @return 
     */  
    @SuppressWarnings("unused")  
    private static String getCharsetFromResponse(HttpResponse ressponse) {  
        // Content-Type:text/html; charset=GBK  
        if (ressponse.getEntity() != null  && ressponse.getEntity().getContentType() != null && ressponse.getEntity().getContentType().getValue() != null) {  
            String contentType = ressponse.getEntity().getContentType().getValue();  
            if (contentType.contains("charset=")) {  
                return contentType.substring(contentType.indexOf("charset=") + 8);  
            }  
        }  
        return null;  
    }  
    
    
    
    /**
     * 创建 SSL连接
     * @return
     * @throws GeneralSecurityException
     */
    private static CloseableHttpClient createSSLInsecureClient() throws GeneralSecurityException {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                        public boolean isTrusted(X509Certificate[] chain,String authType) throws CertificateException {
                            return true;
                        }
                    }).build();
            
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {

                        public boolean verify(String arg0, SSLSession arg1) {
                            return true;
                        }

                        public void verify(String host, SSLSocket ssl)
                                throws IOException {
                        }

                        public void verify(String host, X509Certificate cert)
                                throws SSLException {
                        }

                        public void verify(String host, String[] cns,
                                String[] subjectAlts) throws SSLException {
                        }

                    });
            
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
            
        } catch (GeneralSecurityException e) {
            throw e;
        }
    }
    /**
	 * 
	 * 
	 * 发送HTTP_GET请求
	 * 
	 * 
	 * @see 1)该方法会自动关闭连接,释放资源
	 * 
	 * 
	 * @see 2)方法内设置了连接和读取超时时间,单位为毫秒,超时或发生其它异常时方法会自动返回"通信失败"字符串
	 * 
	 * 
	 * @see 3)请求参数含中文时,经测试可直接传入中文,HttpClient会自动编码发给Server,应用时应根据实际效果决定传入前是否转码
	 * 
	 * 
	 * @see 4)该方法会自动获取到响应消息头中[Content-Type:text/html;
	 *      charset=GBK]的charset值作为响应报文的解码字符集
	 * 
	 * 
	 * @see 若响应消息头中无Content-Type属性,则会使用HttpClient内部默认的ISO-8859-1作为响应报文的解码字符集
	 * 
	 * 
	 * @param requestURL
	 *            请求地址(含参数)
	 * 
	 * 
	 * @return 远程主机响应正文
	 */

	public static String sendGetRequest(String reqURL, String encodeCharset, Integer timeout) {
		
		if(timeout == null){
			timeout = 50000;
		}

		String respContent = "通信失败"; // 响应内容

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpGet httpGet = new HttpGet(reqURL); // 创建org.apache.http.client.methods.HttpGet

		// HttpClient httpClient = new DefaultHttpClient(); // 创建默认的httpClient实例

		// 设置代理服务器
		// httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
		// new HttpHost("10.0.0.4", 8080));

		// httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
		// 50000); // 连接超时50s（4.0 <= 版本 < 4.3 超时设置方式）
		//
		// httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
		// 50000); // 数据传输超时50s（4.0 <= 版本 < 4.3 超时设置方式）

		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(30000).build();// 设置请求和传输超时时间（版本>=4.3设置超时方式）

		httpGet.setConfig(requestConfig);

		try {
			
//			HttpResponse response = httpClient.execute(httpGet); // 执行GET请求
			CloseableHttpResponse response = httpClient.execute(httpGet);

			try{
				HttpEntity entity = response.getEntity(); // 获取响应实体

				if (null != entity) {

					try {
						respContent = EntityUtils.toString(entity, encodeCharset);
					} catch (ParseException | IOException e) {
						e.printStackTrace();
					}

					EntityUtils.consume(entity);

				}

				System.out.println("-------------------------------------------------------------------------------------------");
				
				System.out.println("请求地址：" + reqURL);

				StringBuilder respHeaderDatas = new StringBuilder();

				for (Header header : response.getAllHeaders()) {

					respHeaderDatas.append(header.toString()).append("\r\n");

				}
				
				System.out.println("HTTP应答完整报文");
				System.out.println(response.getStatusLine().toString()); // HTTP应答状态行信息
				System.out.println(respHeaderDatas.toString().trim());// HTTP应答报文头信息
				System.out.println(respContent);// HTTP应答报文体信息

				System.out.println("-------------------------------------------------------------------------------------------");

			} catch (Exception e) {
				
			}finally{
				response.close();
			}
			
		} catch (ConnectTimeoutException cte) {
			cte.printStackTrace();
			// Should catch ConnectTimeoutException, and don`t catch
			// org.apache.http.conn.HttpHostConnectException
			// //LogUtil.getLogger().error("请求通信[" + reqURL + "]时连接超时,堆栈轨迹如下", cte);
		} catch (SocketTimeoutException ste) {
			ste.printStackTrace();
			// LogUtil.getLogger().error("请求通信[" + reqURL + "]时读取超时,堆栈轨迹如下", ste);
		} catch (ClientProtocolException cpe) {
			cpe.printStackTrace();
			// 该异常通常是协议错误导致:比如构造HttpGet对象时传入协议不对(将'http'写成'htp')or响应内容不符合HTTP协议要求等
			// LogUtil.getLogger().error("请求通信[" + reqURL + "]时协议异常,堆栈轨迹如下", cpe);
		} catch (ParseException pe) {
			pe.printStackTrace();
			// LogUtil.getLogger().error("请求通信[" + reqURL + "]时解析异常,堆栈轨迹如下", pe);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			// 该异常通常是网络原因引起的,如HTTP服务器未启动等
			// LogUtil.getLogger().error("请求通信[" + reqURL + "]时网络异常,堆栈轨迹如下", ioe);
		} catch (Exception e) {
			e.printStackTrace();
			// LogUtil.getLogger().error("请求通信[" + reqURL + "]时偶遇异常,堆栈轨迹如下", e);
		} finally {
			if(httpClient != null){
				// 关闭连接,释放资源
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return respContent;

	}

    /**
     * 二进制流文件传输
      * @param url
     * @param bytes
     * @param contentType
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String post(String url, byte[] bytes, String contentType,String fileName) throws IOException {
	    String result="";
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new ByteArrayEntity(bytes));
        if (contentType != null)
            httpPost.setHeader("Content-type", contentType);
            httpPost.setHeader("Content-disposition", "attachment;filename= "+ fileName);

        CloseableHttpResponse httpResponse = HttpClients.createDefault().execute(httpPost);
        try {
            result = IOUtils.toString(httpResponse.getEntity().getContent(), charset);
        } finally {
            httpResponse.close();
        }
        return  result;
    }
    public static byte[] file2byte(String path){
        byte[] data = null;
        FileImageInputStream input = null;
        try {
            input = new FileImageInputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        }
        catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        }
        catch (IOException ex1) {
            ex1.printStackTrace();
        }
        return data;
    }
    //byte数组到图片
    public void byte2image(byte[] data,String path){
        if(data.length<3||path.equals("")) return;
        try{
            FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            System.out.println("Make Picture success,Please find image in " + path);
        } catch(Exception ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();
        }
    }
}

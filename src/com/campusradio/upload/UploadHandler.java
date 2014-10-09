package com.campusradio.upload;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class UploadHandler extends AsyncTask<String, String, String> {

	String result = "Not loaded yet";

	@Override
	protected String doInBackground(String... arg0) {
		/*
		 * HttpClient httpClient = new DefaultHttpClient();
		 * HttpConnectionParams.setConnectionTimeout(httpClient.getParams(),
		 * CampusRadio.properties.uploadTimeOut);
		 * HttpConnectionParams.setSoTimeout(httpClient.getParams(),
		 * CampusRadio.properties.uploadTimeOut); URI url; BufferedReader
		 * br=null; try { url = new
		 * URI("http://172.16.16.16/24online/webpages/client.jsp"); HttpPost
		 * httpPost = new HttpPost(url); List<NameValuePair> nameValuePairs =
		 * new ArrayList<NameValuePair>(); nameValuePairs.add(new
		 * BasicNameValuePair("username", "140905023")); nameValuePairs.add(new
		 * BasicNameValuePair("password", "5208")); httpPost.setEntity(new
		 * UrlEncodedFormEntity(nameValuePairs)); HttpResponse response =
		 * httpClient.execute(httpPost);
		 * 
		 * br = new BufferedReader(new
		 * InputStreamReader(response.getEntity().getContent()), 8096); } catch
		 * (URISyntaxException e) { e.printStackTrace(); } catch
		 * (UnsupportedEncodingException e) { e.printStackTrace(); } catch
		 * (ClientProtocolException e) { e.printStackTrace(); } catch
		 * (IOException e) { e.printStackTrace(); } if(br!=null){ try { return
		 * result=br.readLine(); } catch (IOException e) { e.printStackTrace();
		 * } } return null;
		 */
		BufferedReader bufferReader = null;
		try {
			HttpClient client = new DefaultHttpClient();
			URI website_uri = new URI("http://www.mybringback.com");
			HttpGet request = new HttpGet();
			request.setURI(website_uri);
			HttpResponse response = client.execute(request);
			bufferReader = new BufferedReader(new InputStreamReader(response
					.getEntity().getContent()));
			StringBuffer stringBuffer = new StringBuffer();
			String l = "";
			String nl = System.getProperty("line.separator");
			while ((l = bufferReader.readLine()) != null) {
				stringBuffer.append(l + nl);
			}
			bufferReader.close();
			result = stringBuffer.toString();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferReader != null) {
				try {
					bufferReader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

}

package com.reubenjohn.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;

import android.os.AsyncTask;

import com.campusradio.android.CampusRadio;
import com.campusradio.android.debug.NullPointerAsserter;
import com.campusradio.upload.OnHttpPostCompleteListener;

public class HttpPostHandler extends AsyncTask<String, String, String> {

	NullPointerAsserter asserter = new NullPointerAsserter(
			HttpPostHandler.class.toString());

	public String result = "loading...";
	OnHttpPostCompleteListener postListener = new OnHttpPostCompleteListener() {

		@Override
		public void onHttpPostComplete(String response) {
			// TODO Auto-generated method stub

		}
	};

	@Override
	protected String doInBackground(String... arg) {
		BufferedReader bufferReader = null;
		try {
			if (arg[1] != null)
				result = arg[1];
			HttpClient httpClient = new DefaultHttpClient();
			HttpConnectionParams.setConnectionTimeout(httpClient.getParams(),
					CampusRadio.properties.uploadTimeOut);
			URI website_uri = new URI(arg[0]);

			HttpPost httpPost = new HttpPost(website_uri);
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			for (int i = 2; i < arg.length; i += 2) {
				nameValuePairs.add(new BasicNameValuePair(arg[i], arg[i + 1]));
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse response = httpClient.execute(httpPost);
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
		postListener.onHttpPostComplete(result);
		return result;
	}

	public void setOnPostCompleteListener(
			OnHttpPostCompleteListener onHttpPostCompleteListener) {
		postListener = onHttpPostCompleteListener;
	}

}

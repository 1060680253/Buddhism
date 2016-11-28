package com.yuanming.buddhism.http;

import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.yuanming.buddhism.R;
import com.yuanming.buddhism.app.App;
import com.yuanming.buddhism.entity.BaseItem;
import com.yuanming.buddhism.http.convert.FastJsonTools;
import com.yuanming.buddhism.interf.HttpRequestListener;
import com.yuanming.buddhism.util.StringUtils;
import com.yuanming.buddhism.util.TDevice;

import java.io.UnsupportedEncodingException;

/**
 * @author seven@microdoapp.com
 *
 */
public class RequestManager {
	private static RequestManager instance;
	private RequestQueue mRequestQueue;

	private RequestManager() {
		 mRequestQueue = getRequestQueue();
	}
	public static synchronized RequestManager getInstance() {
		if (instance == null) {
			instance = new RequestManager();
		}
		return instance;
	}
	private RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(App.getInstance().getContext());
		}
		return mRequestQueue;
	}

    public void get(RequestParams params,String url,HttpRequestListener listener,Class clazz) {
	    if(TDevice.isConnected()){
	    	ByteArrayRequest request = new ByteArrayRequest(Method.GET, url, params, responseListenerByGet(listener,clazz), responseError(listener));
	    	request.setRetryPolicy(new DefaultRetryPolicy(5 * 1000, 1,  DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		    mRequestQueue.add(request);
		}else{
			listener.onError(-1,StringUtils.getStringFromRes(R.string.no_internet));
		}
    }
	
    public void post(RequestParams params,String webserverURL, HttpRequestListener listener,Class clazz) {
		if(TDevice.isConnected()){
			ByteArrayRequest request = new ByteArrayRequest(Method.POST, webserverURL, params, responseListener(listener,clazz), responseError(listener));
			request.setRetryPolicy(new DefaultRetryPolicy(5 * 1000, 1,  DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
			mRequestQueue.add(request);
		}else{
			listener.onError(-1,StringUtils.getStringFromRes(R.string.no_internet));
		}
	}
	
	  private Response.ErrorListener responseError(final HttpRequestListener listener) {
	    	return new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError e) {
					listener.onError(e.networkResponse.statusCode,VolleyErrorHelper.getMessage(e));
				}
			};
	    }
	    
	
	 private Response.Listener<byte[]> responseListener(final HttpRequestListener listener,final Class clazz) {
	    	return new Response.Listener<byte[]>() {
				@Override
				public void onResponse(byte[] arg0) {
					String result=getResultByStream(arg0);
					if(StringUtils.isEmpty(result)){
						listener.onError(-2,StringUtils.getStringFromRes(R.string.generic_no_data_error));
					}else{
						if(result.indexOf("{")<0||result.indexOf("}")<0){
							listener.onError(-3,StringUtils.getStringFromRes(R.string.generic_form_error));
						}else{
							if(result.indexOf("{")>0){
								result=result.substring(result.indexOf("{"), result.lastIndexOf("}")+1);
							}
							try {
								BaseItem psContentMode=FastJsonTools.getBean(result, BaseItem.class);
								if(psContentMode==null){
									listener.onError(-3,StringUtils.getStringFromRes(R.string.generic_form_error));
								}else{
									if(psContentMode.isSuccess()){
										listener.onSuccess(psContentMode);
									}else{
										listener.onError(psContentMode.getState(),psContentMode.getMsg());
									}
								}
							} catch (Exception e) {
								listener.onError(-3,StringUtils.getStringFromRes(R.string.generic_form_error));
							}
						}
					}
				}
			};
	    }
	 
	 
	 private Response.Listener<byte[]> responseListenerByGet(final HttpRequestListener listener,final Class clazz) {
	    	return new Response.Listener<byte[]>() {
				@Override
				public void onResponse(byte[] arg0) {
					String result=getResultByStream(arg0);
					if(StringUtils.isEmpty(result)){
						listener.onError(-2,StringUtils.getStringFromRes(R.string.generic_no_data_error));
					}else if(result.indexOf("{")<0||result.indexOf("}")<0){
						listener.onError(-3,StringUtils.getStringFromRes(R.string.generic_form_error));
					}else{
						listener.onSuccess(FastJsonTools.getBean(result, clazz));
					}
				}
			};
	    }
	 
	 private String getResultByStream(byte[] arg0){
	    	String result = null;
			try {
				result = StringUtils.decode(new String(arg0, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				Log.i("responseListener", e.toString());
			}
			return result;
	    }

}

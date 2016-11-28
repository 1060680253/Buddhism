package com.yuanming.buddhism.http;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.yuanming.buddhism.R;
import com.yuanming.buddhism.app.App;

import java.util.Map;

public class VolleyErrorHelper {
	   /**
	   * Returns appropriate message which is to be displayed to the user 
	   * against the specified error object.
	   * 
	   * @param error
	   * @return
	   */
	  public static String getMessage(Object error) {
	    if (error instanceof TimeoutError) {
	      return App.getInstance().getContext().getResources().getString(R.string.generic_server_down);
	    }
	    else if (isServerProblem(error)) {
	      return handleServerError(error, App.getInstance().getContext());
	    }
	    else if (isNetworkProblem(error)) {
	      return App.getInstance().getContext().getResources().getString(R.string.no_internet);
	    }
	    return App.getInstance().getContext().getResources().getString(R.string.generic_error);
	  }
	  /**
	  * Determines whether the error is related to network
	  * @param error
	  * @return
	  */
	  private static boolean isNetworkProblem(Object error) {
	    return (error instanceof NetworkError) || (error instanceof NoConnectionError);
	  }
	  /**
	  * Determines whether the error is related to server
	  * @param error
	  * @return
	  */
	  private static boolean isServerProblem(Object error) {
	    return (error instanceof ServerError) || (error instanceof AuthFailureError);
	  }
	  /**
	  * Handles the server error, tries to determine whether to show a stock message or to 
	  * show a message retrieved from the server.
	  * 
	  * @param err
	  * @param context
	  * @return
	  */
	  private static String handleServerError(Object err, Context context) {
	    VolleyError error = (VolleyError) err;
	    NetworkResponse response = error.networkResponse;
	    if (response != null) {
	      switch (response.statusCode) {
	      case 404:
	      case 422:
	      case 401:
	        try {
	          // server might return error like this { "error": "Some error occured" }
	          // Use "Gson" to parse the result
	          Map<String, Object> result = JSON.parseObject(new String(response.data),
	        		  new TypeReference<Map<String, Object>>() {}) ;
	          if (result != null && result.containsKey("error")) {
	            return (String)result.get("error");
	          }
	        } catch (Exception e) {
	          e.printStackTrace();
	        }
	        // invalid request
	        return error.getMessage();
	      default:
	        return context.getResources().getString(R.string.generic_server_down);
	      }
	    }
	    return context.getResources().getString(R.string.generic_error);
	  }
	}

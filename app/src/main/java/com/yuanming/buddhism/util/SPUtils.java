package com.yuanming.buddhism.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.yuanming.buddhism.app.App;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SPUtils {
	/**
	 * 保存在手机里面的文件名
	 */
	public static final String FILE_NAME = "phyt_config";

	public static final String USER_MESSAGE_FILE_NAME = "user_message_file_name";

	public static final String USER_MESSAGE = "user_message";
	
	/**
	 * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
	 *
	 * @param key
	 * @param object
	 */
	public static void put(String key, Object object) {
		put(key, object, FILE_NAME);
	}
	
	public static void put(String key, Object object, String file_name) {
		SharedPreferences sp = App.getInstance().getContext().getSharedPreferences(file_name,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();

		if (object instanceof String) {
			editor.putString(key, (String) object);
		} else if (object instanceof Integer) {
			editor.putInt(key, (Integer) object);
		} else if (object instanceof Boolean) {
			editor.putBoolean(key, (Boolean) object);
		} else if (object instanceof Float) {
			editor.putFloat(key, (Float) object);
		} else if (object instanceof Long) {
			editor.putLong(key, (Long) object);
		} else {
			editor.putString(key, object.toString());
		}
		SharedPreferencesCompat.apply(editor);
	}

	public static void put(String key, List<String> strs){
		if(strs != null) {
			StringBuilder stringBuffer = new StringBuilder();
			for (String str : strs) {
				stringBuffer.append(str);
				stringBuffer.append(",");
			}
			put(key, stringBuffer.toString());
		}
	}

	public static List<String> get(String key){
		String strs = (String) get(key,"");
		if(!StringUtils.isEmpty(strs)){
			String[] strings = strs.split(",");
			List<String> stringList = new ArrayList<>();
			for(String str:strings){
				stringList.add(str);
			}

			return  stringList;
		}
		return null;
	}

	/**
	 * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
	 *
	 * @param key
	 * @param defaultObject
	 * @return
	 */
	public static Object get(String key, Object defaultObject) {
		return get(key, defaultObject, FILE_NAME);
	}

	public static Object get(String key, Object defaultObject, String file_name) {
		SharedPreferences sp = App.getInstance().getContext().getSharedPreferences(file_name,
				Context.MODE_PRIVATE);

		if (defaultObject instanceof String) {
			return sp.getString(key, (String) defaultObject);
		} else if (defaultObject instanceof Integer) {
			return sp.getInt(key, (Integer) defaultObject);
		} else if (defaultObject instanceof Boolean) {
			return sp.getBoolean(key, (Boolean) defaultObject);
		} else if (defaultObject instanceof Float) {
			return sp.getFloat(key, (Float) defaultObject);
		} else if (defaultObject instanceof Long) {
			return sp.getLong(key, (Long) defaultObject);
		}

		return null;
	}
	
	/**
	 * 移除某个key值已经对应的值
	 *
	 * @param key
	 */
	public static void remove(String key) {
		remove(key, FILE_NAME);
	}
	
	public static void remove(String key, String file_name) {
		SharedPreferences sp = App.getInstance().getContext().getSharedPreferences(file_name,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.remove(key);
		SharedPreferencesCompat.apply(editor);
	}


	/**
	 * 清除所有数据
	 *
	 */
	public static void clear() {
		clear(FILE_NAME);
	}
	
	public static void clear(String file_name) {
		SharedPreferences sp = App.getInstance().getContext().getSharedPreferences(file_name,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.clear();
		SharedPreferencesCompat.apply(editor);
	}

	/**
	 * 查询某个key是否已经存在
	 *
	 * @param key
	 * @return
	 */
	public static boolean contains(String key) {
		return contains(key, FILE_NAME);
	}
	
	public static boolean contains(String key, String file_name) {
		SharedPreferences sp = App.getInstance().getContext().getSharedPreferences(file_name,
				Context.MODE_PRIVATE);
		return sp.contains(key);
	}

	/**
	 * 返回所有的键值对
	 *
	 * @return
	 */
	public static Map<String, ?> getAll() {
		return getAll(FILE_NAME);
	}

	public static Map<String, ?> getAll(String file_name) {
		SharedPreferences sp = App.getInstance().getContext().getSharedPreferences(file_name,
				Context.MODE_PRIVATE);
		return sp.getAll();
	}
	
	/**
	 * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
	 * 
	 */
	private static class SharedPreferencesCompat {
		private static final Method sApplyMethod = findApplyMethod();

		/**
		 * 反射查找apply的方法
		 * 
		 * @return
		 */
		@SuppressWarnings({ "unchecked", "rawtypes" })
		private static Method findApplyMethod() {
			try {
				Class clz = SharedPreferences.Editor.class;
				return clz.getMethod("apply");
			} catch (NoSuchMethodException e) {
			}

			return null;
		}

		/**
		 * 如果找到则使用apply执行，否则使用commit
		 * 
		 * @param editor
		 */
		public static void apply(SharedPreferences.Editor editor) {
			try {
				if (sApplyMethod != null) {
					sApplyMethod.invoke(editor);
					return;
				}
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			} catch (InvocationTargetException e) {
			}
			editor.commit();
		}
	}

}

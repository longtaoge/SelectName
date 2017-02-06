package org.xiangbalao.common.util;

import android.text.TextUtils;
import android.util.Log;

public class LogUtils {

	static String DEFAULT_MESSAGE = "execute";
	String LINE_SEPARATOR = System.getProperty("line.separator");
	static String NULL_TIPS = "对象为null"; // Log with null object
	int JSON_INDENT = 4;

	public static final int V = 1;
	public static final int D = 2;
	public static final int I = 3;
	public static final int W = 4;
	public static final int E = 5;
	public static final int A = 6;

	private static boolean IS_SHOW_LOG = true;

	private static boolean IS_DEBUG = true;

	protected static void printLine(String tag, boolean isTop) {
		if (isTop) {
			Log.d(tag,
					"╔═══════════════════════════════════════════════════════════════════════════════════════");
		} else {
			Log.d(tag,
					"╚═══════════════════════════════════════════════════════════════════════════════════════");
		}
	}

	protected static boolean isEmpty(String line) {
		return TextUtils.isEmpty(line) || line.equals("\n")
				|| line.equals("\t") || TextUtils.isEmpty(line.trim());
	}

	public static void printDefault(int type, String tag, String msg) {
	
		
		switch (type) {
		case V:
			Log.v(tag, msg);
			break;
		case D:
			Log.d(tag, msg);
			break;
		case I:
			Log.i(tag, msg);
			break;
		case W:
			Log.w(tag, msg);
			break;
		case E:
			Log.e(tag, msg);
			break;
		case A:
			Log.wtf(tag, msg);
			break;
		}
	}

	public static void init(boolean isShowLog, boolean isDebug) {
		IS_SHOW_LOG = isShowLog;
		IS_DEBUG = isDebug;
	}

	public static void v() {
		printLog(V, null, DEFAULT_MESSAGE);
	}

	public static void v(Object msg) {
		printLog(V, null, msg);
	}

	public static void v(String tag, String msg) {
		printLog(V, tag, msg);
	}

	public static void d() {

		printLog(D, null, DEFAULT_MESSAGE);
	}

	public static void d(Object msg) {
		printLog(D, null, msg);
	}

	public static void d(String tag, Object msg) {
		printLog(D, tag, msg);
	}

	public static void i() {
		printLog(I, null, DEFAULT_MESSAGE);
	}

	public static void i(Object msg) {
		printLog(I, null, msg);
	}

	public static void i(String tag, Object msg) {
		printLog(I, tag, msg);
	}

	public static void w() {
		printLog(W, null, DEFAULT_MESSAGE);
	}

	public static void w(Object msg) {
		printLog(W, null, msg);
	}

	public static void w(String tag, Object msg) {
		printLog(W, tag, msg);
	}

	public static void e() {
		printLog(E, null, DEFAULT_MESSAGE);
	}

	public static void e(Object msg) {
		printLog(E, null, msg);
	}

	public static void e(String tag, Object msg) {
		printLog(E, tag, msg);
	}

	private static void printLog(int type, String tagStr, Object objectMsg) {

		String[] contents = wrapperContent(tagStr, objectMsg);
		String tag = contents[0];
		String msg = contents[1];
		String headString = contents[2];

		switch (type) {

		case V:

		case I:
		case W:
		case E:
		case A:
			if (!IS_SHOW_LOG) {
				return;
			}
			printDefault(type, tag, headString + msg);
			break;

		case D:
			if (!IS_DEBUG) {
				return;
			}
			printDefault(type, tag, headString + msg);

			break;

		}
	}

	private static String[] wrapperContent(String tagStr, Object objectMsg) {

		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		int index = 5;
		String className = stackTrace[index].getFileName();
		String methodName = stackTrace[index].getMethodName();
		int lineNumber = stackTrace[index].getLineNumber();
		@SuppressWarnings("unused")
		String methodNameShort = methodName.substring(0, 1).toUpperCase()
				+ methodName.substring(1);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("[ (").append(className).append(":")
				.append(lineNumber).append(")").append(" ] ");

		String tag = (tagStr == null ? className : tagStr);
		String msg = (objectMsg == null) ? NULL_TIPS : objectMsg.toString();
		String headString = stringBuilder.toString();

		return new String[] { tag, msg, headString };
	}

}
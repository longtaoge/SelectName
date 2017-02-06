package org.xiangbalao.common.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import org.xiangbalao.common.util.LogUtils;

import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
	private static Class<?>[] clazzes;
	private static String dataBaseName;
	private static int dataBaseVersion;//
	@SuppressWarnings("unused")
	private static String password;// 数据库加密时使用
	private static DatabaseHelper instance;

	public static synchronized DatabaseHelper getInstance(Context context) {
		if (instance == null) {
			instance = new DatabaseHelper(context);
		}
		return instance;
	}

	public DatabaseHelper(Context context) {
		// super(context, databaseName, null, databaseVersion, password);
		// 数据库加密时使用
		super(context, dataBaseName, null, dataBaseVersion);
	}

	public static void initialize(String dbName, int dbVersion, Class<?>[] clazz) {
		dataBaseName = dbName.length() == 0 ? "longtaoge.db" : dbName;
		dataBaseVersion = dbVersion;
		clazzes = clazz;
		password = "";
	}

	public static void initialize(String dbName, int dbVersion,
								  Class<?>[] clazz, String mPassword) {
		dataBaseName = dbName.length() == 0 ? "longtaoge.db" : dbName;
		dataBaseVersion = dbVersion;
		clazzes = clazz;
		password = mPassword;
	}

	public void close() {
		super.close();
		for (Class<?> clazz : clazzes) {
			try {
				getDao(clazz).clearObjectCache();
			} catch (SQLException e) {
				LogUtils.d("无法清除Dao的缓存" + e);
			}
		}
	}

	public void onCreate(SQLiteDatabase database,
			ConnectionSource connectionSource) {

		try {
			if (clazzes != null) {
				for (Class<?> clazz : clazzes) {
					TableUtils.createTable(connectionSource, clazz);
				}
			}
		} catch (SQLException e) {
			LogUtils.e("DatabaseHelper无法创建数据库 :" + e);
		}

	}

	public void onUpgrade(android.database.sqlite.SQLiteDatabase database,
			ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			if (clazzes != null) {
				for (Class<?> clazz : clazzes) {
					TableUtils.dropTable(connectionSource, clazz, true);
				}
				onCreate(database, connectionSource);
			}
		} catch (SQLException e) {
			LogUtils.d("DatabaseHelper升级数据库失败：" + e);
		}
	}
}
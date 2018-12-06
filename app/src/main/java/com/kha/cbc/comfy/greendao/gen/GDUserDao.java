package com.kha.cbc.comfy.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.kha.cbc.comfy.entity.GDUser;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "GDUSER".
*/
public class GDUserDao extends AbstractDao<GDUser, String> {

    public static final String TABLENAME = "GDUSER";

    /**
     * Properties of entity GDUser.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Username = new Property(0, String.class, "username", true, "USERNAME");
        public final static Property SessionToken = new Property(1, String.class, "sessionToken", false, "SESSION_TOKEN");
        public final static Property ObjectId = new Property(2, String.class, "objectId", false, "OBJECT_ID");
    }


    public GDUserDao(DaoConfig config) {
        super(config);
    }
    
    public GDUserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"GDUSER\" (" + //
                "\"USERNAME\" TEXT PRIMARY KEY NOT NULL ," + // 0: username
                "\"SESSION_TOKEN\" TEXT," + // 1: sessionToken
                "\"OBJECT_ID\" TEXT);"); // 2: objectId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"GDUSER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, GDUser entity) {
        stmt.clearBindings();
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(1, username);
        }
 
        String sessionToken = entity.getSessionToken();
        if (sessionToken != null) {
            stmt.bindString(2, sessionToken);
        }
 
        String objectId = entity.getObjectId();
        if (objectId != null) {
            stmt.bindString(3, objectId);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, GDUser entity) {
        stmt.clearBindings();
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(1, username);
        }
 
        String sessionToken = entity.getSessionToken();
        if (sessionToken != null) {
            stmt.bindString(2, sessionToken);
        }
 
        String objectId = entity.getObjectId();
        if (objectId != null) {
            stmt.bindString(3, objectId);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public GDUser readEntity(Cursor cursor, int offset) {
        GDUser entity = new GDUser( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // username
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // sessionToken
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // objectId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, GDUser entity, int offset) {
        entity.setUsername(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setSessionToken(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setObjectId(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final String updateKeyAfterInsert(GDUser entity, long rowId) {
        return entity.getUsername();
    }
    
    @Override
    public String getKey(GDUser entity) {
        if(entity != null) {
            return entity.getUsername();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(GDUser entity) {
        return entity.getUsername() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}

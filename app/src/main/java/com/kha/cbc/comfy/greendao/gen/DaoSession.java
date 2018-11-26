package com.kha.cbc.comfy.greendao.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.kha.cbc.comfy.entity.GDPersonalCard;
import com.kha.cbc.comfy.entity.GDPersonalTask;
import com.kha.cbc.comfy.entity.GDUser;

import com.kha.cbc.comfy.greendao.gen.GDPersonalCardDao;
import com.kha.cbc.comfy.greendao.gen.GDPersonalTaskDao;
import com.kha.cbc.comfy.greendao.gen.GDUserDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig gDPersonalCardDaoConfig;
    private final DaoConfig gDPersonalTaskDaoConfig;
    private final DaoConfig gDUserDaoConfig;

    private final GDPersonalCardDao gDPersonalCardDao;
    private final GDPersonalTaskDao gDPersonalTaskDao;
    private final GDUserDao gDUserDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        gDPersonalCardDaoConfig = daoConfigMap.get(GDPersonalCardDao.class).clone();
        gDPersonalCardDaoConfig.initIdentityScope(type);

        gDPersonalTaskDaoConfig = daoConfigMap.get(GDPersonalTaskDao.class).clone();
        gDPersonalTaskDaoConfig.initIdentityScope(type);

        gDUserDaoConfig = daoConfigMap.get(GDUserDao.class).clone();
        gDUserDaoConfig.initIdentityScope(type);

        gDPersonalCardDao = new GDPersonalCardDao(gDPersonalCardDaoConfig, this);
        gDPersonalTaskDao = new GDPersonalTaskDao(gDPersonalTaskDaoConfig, this);
        gDUserDao = new GDUserDao(gDUserDaoConfig, this);

        registerDao(GDPersonalCard.class, gDPersonalCardDao);
        registerDao(GDPersonalTask.class, gDPersonalTaskDao);
        registerDao(GDUser.class, gDUserDao);
    }
    
    public void clear() {
        gDPersonalCardDaoConfig.clearIdentityScope();
        gDPersonalTaskDaoConfig.clearIdentityScope();
        gDUserDaoConfig.clearIdentityScope();
    }

    public GDPersonalCardDao getGDPersonalCardDao() {
        return gDPersonalCardDao;
    }

    public GDPersonalTaskDao getGDPersonalTaskDao() {
        return gDPersonalTaskDao;
    }

    public GDUserDao getGDUserDao() {
        return gDUserDao;
    }

}

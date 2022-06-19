package com.example.m3.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.m3.R;

public class DBOpenHelper extends SQLiteOpenHelper {
    public DBOpenHelper(@Nullable Context context) {
        super(context,"tally.db" , null, 1);
    }
    
    //    创建数据库的方法，只有项目第一次运行时，会被调用
    @Override
    public void onCreate(SQLiteDatabase db) {
//        创建表示类型的表
        String sql = "create table typetb(id integer primary key autoincrement,typename varchar(10),imageId integer,sImageId integer,kind integer)";
        db.execSQL(sql);
        insertType(db);
        //创建记账表
        sql = "create table accounttb(id integer primary key autoincrement,typename varchar(10),sImageId integer,beizhu varchar(80),money float," +
            "time varchar(60),year integer,month integer,day integer,kind integer)";
        db.execSQL(sql);
    }
    
    private void insertType(SQLiteDatabase db) {
//      向typetb表当中插入元素
        String sql = "insert into typetb (typename,imageId,sImageId,kind) values (?,?,?,?)";
        db.execSQL(sql,new Object[]{"餐饮", R.mipmap.oc_cy,R.mipmap.oc_cy_s,0});
        db.execSQL(sql,new Object[]{"零食", R.mipmap.oc_ls,R.mipmap.oc_ls_s,0});
        db.execSQL(sql,new Object[]{"日用", R.mipmap.oc_ry,R.mipmap.oc_ry_s,0});
        db.execSQL(sql,new Object[]{"交通", R.mipmap.oc_gj,R.mipmap.oc_gj_s,0});
        db.execSQL(sql,new Object[]{"购物", R.mipmap.oc_gw,R.mipmap.oc_gw_s,0});
        db.execSQL(sql,new Object[]{"服饰", R.mipmap.oc_fs,R.mipmap.oc_fs_s,0});
        db.execSQL(sql,new Object[]{"美容", R.mipmap.oc_mr,R.mipmap.oc_mr_s,0});
        db.execSQL(sql,new Object[]{"娱乐", R.mipmap.oc_yl,R.mipmap.oc_yl_s,0});
        db.execSQL(sql,new Object[]{"通讯", R.mipmap.oc_tx,R.mipmap.oc_tx_s,0});
        db.execSQL(sql,new Object[]{"社交", R.mipmap.oc_sj,R.mipmap.oc_sj_s,0});
        db.execSQL(sql,new Object[]{"旅行", R.mipmap.oc_ly,R.mipmap.oc_ly_s,0});
        db.execSQL(sql,new Object[]{"数码", R.mipmap.oc_sm,R.mipmap.oc_sm_s,0});
        db.execSQL(sql,new Object[]{"医疗", R.mipmap.oc_yy,R.mipmap.oc_yy_s,0});
        db.execSQL(sql,new Object[]{"学习", R.mipmap.oc_xx,R.mipmap.oc_xx_s,0});
        db.execSQL(sql,new Object[]{"礼物", R.mipmap.oc_lw,R.mipmap.oc_lw_s,0});
        db.execSQL(sql,new Object[]{"快递", R.mipmap.oc_kd,R.mipmap.oc_kd_s,0});
        
        db.execSQL(sql,new Object[]{"工资", R.mipmap.ic_gz,R.mipmap.ic_gz_s,1});
        db.execSQL(sql,new Object[]{"兼职", R.mipmap.ic_jz,R.mipmap.ic_jz_s,1});
        db.execSQL(sql,new Object[]{"理财", R.mipmap.ic_lc,R.mipmap.ic_lc_s,1});
        db.execSQL(sql,new Object[]{"礼金", R.mipmap.ic_lj,R.mipmap.ic_lj_s,1});
        db.execSQL(sql,new Object[]{"其他", R.mipmap.ic_qt,R.mipmap.ic_qt_s,1});
    }
    
    // 数据库版本在更新时发生改变，会调用此方法
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    
    }
}

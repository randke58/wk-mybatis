package com.randke.util;

import org.apache.ibatis.jdbc.SQL;

import java.io.FileDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SqlProvider<T  extends Serializable> {


    public String  insert(T obj){

        SQL sql = new SQL();
        sql.INSERT_INTO(obj.getClass().getSimpleName());
        Field[] fieldList =  obj.getClass().getDeclaredFields();
        for(Field field :fieldList){
            field.setAccessible(true);
            String column =  field.getName();
            sql.VALUES(column,String.format("#{"+column+"}"));
        }
        return sql.toString();
    }

//
//
//    public String updateById(T obj) {
//
//        String idname = id(obj);
//
//        BEGIN();
//
//        UPDATE(tableName(obj));
//
//        caculationColumnList(obj);
//
//        SET(returnUpdateSetFull(obj));
//
//        WHEREID(idname);
//
//        return SQL();
//
//    }
//
//    public String updateNotNullById(T obj) {
//
//        String idname = id(obj);
//
//
//
//        BEGIN();
//
//
//
//        UPDATE(tableName(obj));
//
//        caculationColumnList(obj);
//
//        SET(returnUpdateSet(obj));
//
//        WHEREID(idname);
//
//        return SQL();
//
//    }
//
//
//
//    public String deleteById(T obj) {
//
//        String idname = id(obj);
//
//        BEGIN();
//
//        DELETE_FROM(tableName(obj));
//
//        WHEREID(idname);
//
//        return SQL();
//
//    }
//
//
//
//    public String deleteByObject(T obj){
//
//        caculationColumnList(obj);
//
//        BEGIN();
//
//        DELETE_FROM(tableName(obj));
//
//        WHERE(whereColumnNotNull(obj));
//
//        return SQL();
//
//    }
//
//    public String deleteByParamNotEmpty(Map<String,Object> param){
//
//        removeEmpty(param);
//
//        BEGIN();
//
//        Serializable obj = (Serializable) param.get(SPACE_TABLE_NAME);
//
//        String limit ="";
//
//        if(param.containsKey(Constant.MYBATIS_SPECIAL_STRING.LIMIT.name())){
//
//            limit=addlimit(param);
//
//        }
//
//        DELETE_FROM(tableName(obj));
//
//        param.remove(SPACE_TABLE_NAME);
//
//        if(param!=null && param.values()!=null && param.values().size()>0)
//
//            WHERE(whereColumnNotEmpty(param));
//
//        return SQL()+limit;
//
//    }
//
//    public String deleteByParam(Map<String,Object> param){
//
//        BEGIN();
//
//        Serializable obj = (Serializable) param.get(SPACE_TABLE_NAME);
//
//        String limit ="";
//
//        if(param.containsKey(Constant.MYBATIS_SPECIAL_STRING.LIMIT.name())){
//
//            limit=addlimit(param);
//
//        }
//
//        DELETE_FROM(tableName(obj));
//
//        param.remove(SPACE_TABLE_NAME);
//
//        if(param!=null && param.values()!=null && param.values().size()>0)
//
//            WHERE(whereColumn(param));
//
//        return SQL()+limit;
//
//    }
//
//
//
//    public String queryById(T obj){
//
//        String idname = id(obj);
//
//        caculationColumnList(obj);
//
//        BEGIN();
//
//        SELECT(queryColumn(obj));
//
//        FROM(tableName(obj)+" "+obj.getClass().getSimpleName());
//
//        WHEREID(idname);
//
//        return SQL();
//
//    }
//
//    public String queryByObject(T obj){
//
//        caculationColumnList(obj);
//
//        BEGIN();
//
//        SELECT(queryColumn(obj));
//
//        FROM(tableName(obj)+" "+obj.getClass().getSimpleName());
//
//        if(!"".equals(whereColumnNotNull(obj))){
//
//            WHERE(whereColumnNotNull(obj));
//
//        }
//
//        return SQL();
//
//    }
//
//
//
//    public String queryByParamNotEmpty(Map<String,Object> param){
//
//        try{
//
//            removeEmpty(param);
//
//            Serializable obj =(Serializable) param.get(SPACE_TABLE_NAME);
//
//            param.remove(SPACE_TABLE_NAME);
//
//            Object orderBy = param.get(Constant.MYBATIS_SPECIAL_STRING.ORDER_BY.name());
//
//            param.remove(Constant.MYBATIS_SPECIAL_STRING.ORDER_BY.name());
//
//            caculationColumnList(obj);
//
//            BEGIN();
//
//            SELECT(queryColumn(obj));
//
//            FROM(tableName(obj)+" "+obj.getClass().getSimpleName());
//
//            if(!param.isEmpty())
//
//                WHERE(whereColumnNotEmpty(param));
//
//            if(null!=orderBy)
//
//                ORDER_BY(orderBy.toString());
//
//            String sql = SQL();
//
//            log.debug(sql);
//
//            return sql;
//
//        }catch(Exception e){
//
//            log.error("出错了 ！"+param);
//
//            e.printStackTrace();
//
//            return null;
//
//        }
//
//    }
//
//
//
//    public String queryByParam(Map<String,Object> param){
//
//        try{
//
//            Serializable obj =(Serializable) param.get(SPACE_TABLE_NAME);
//
//            Object orderBy = param.get(Constant.MYBATIS_SPECIAL_STRING.ORDER_BY.name());
//
//            param.remove(SPACE_TABLE_NAME);
//
//            param.remove(Constant.MYBATIS_SPECIAL_STRING.ORDER_BY.name());
//
//            caculationColumnList(obj);
//
//            BEGIN();
//
//            SELECT(queryColumn(obj));
//
//            FROM(tableName(obj)+" "+obj.getClass().getSimpleName());
//
//            if(!param.isEmpty())
//
//                WHERE(whereColumn(param));
//
//            if(null!=orderBy)
//
//                ORDER_BY(orderBy.toString());
//
//            String sql = SQL();
//
//            log.debug(sql);
//
//            return sql;
//
//        }catch(Exception e){
//
//            log.error("出错了！"+param);
//
//            e.printStackTrace();
//
//            return null;
//
//        }
//
//    }
//
//
//
//    public String queryByObjectCount(T obj) {
//
//        caculationColumnList(obj);
//
//        BEGIN();
//
//        SELECT(" count(*) total ");
//
//        FROM(tableName(obj) + " " + obj.getClass().getSimpleName());
//
//        WHERE(whereColumnNotNull(obj));
//
//        return SQL();
//
//    }
//
//
//
//    public String queryByParamNotEmptyCount(Map<String, Object> param) {
//
//        try{
//
//            removeEmpty(param);
//
//            Serializable obj = (Serializable)param.remove(SPACE_TABLE_NAME);
//
//            caculationColumnList(obj);
//
//            BEGIN();
//
//            SELECT(" count(*) total ");
//
//            FROM(tableName(obj) + " " + obj.getClass().getSimpleName());
//
//            Object orderBy = param.remove(Constant.MYBATIS_SPECIAL_STRING.ORDER_BY.name());
//
//            if(!param.isEmpty())
//
//                WHERE(whereColumn(param));
//
//            param.put(Constant.MYBATIS_SPECIAL_STRING.ORDER_BY.name(), orderBy);
//
//            String sql = SQL();
//
//            log.debug(sql);
//
//            return sql;
//
//        }catch(Exception e){
//
//            log.error("出错了！"+param);
//
//            e.printStackTrace();
//
//            return null;
//
//        }
//
//    }
//
//
//
//    public String queryByParamCount(Map<String, Object> param) {
//
//        try{
//
//            Serializable obj = (Serializable) param.get(SPACE_TABLE_NAME);
//
//            param.remove(SPACE_TABLE_NAME);
//
//            caculationColumnList(obj);
//
//            BEGIN();
//
//            SELECT(" count(*) total ");
//
//            FROM(tableName(obj) + " " + obj.getClass().getSimpleName());
//
//            Object orderBy = param.remove(Constant.MYBATIS_SPECIAL_STRING.ORDER_BY.name());
//
//            if(!param.isEmpty())
//
//                WHERE(whereColumn(param));
//
//            param.put(Constant.MYBATIS_SPECIAL_STRING.ORDER_BY.name(), orderBy);
//
//            String sql = SQL();
//
//            log.debug(sql);
//
//            return sql;
//
//        }catch(Exception e){
//
//            log.error("出错了！"+param);
//
//            e.printStackTrace();
//
//            return null;
//
//        }
//
//    }
//
//
//
//    public String queryPageByParamNotEmpty(Map<String,Object> param){
//
//        removeEmpty(param);
//
//        Serializable obj =(Serializable)param.remove(SPACE_TABLE_NAME);
//
//        String limit = addlimit(param);
//
//        Object orderBy = param.remove(Constant.MYBATIS_SPECIAL_STRING.ORDER_BY.name());
//
//        caculationColumnList(obj);
//
//        BEGIN();
//
//        SELECT(queryColumn(obj));
//
//        FROM(tableName(obj)+" "+obj.getClass().getSimpleName());
//
//        if(!param.isEmpty())
//
//            WHERE(whereColumnNotEmpty(param));
//
//        if(orderBy!=null){
//
//            ORDER_BY(orderBy.toString());
//
//        }
//
//        return  SQL()+limit;
//
//    }
//
//
//
//    public String queryPageByParam(Map<String,Object> param){
//
//        Serializable obj =(Serializable)param.remove(SPACE_TABLE_NAME);
//
//        String limit = addlimit(param);
//
//        Object orderBy = param.remove(Constant.MYBATIS_SPECIAL_STRING.ORDER_BY.name());
//
//        caculationColumnList(obj);
//
//        BEGIN();
//
//        SELECT(queryColumn(obj));
//
//        FROM(tableName(obj)+" "+obj.getClass().getSimpleName());
//
//        if(!param.isEmpty())
//
//            WHERE(whereColumn(param));
//
//        if(orderBy!=null){
//
//            ORDER_BY(orderBy.toString());
//
//        }
//
//        return SQL()+limit;
//
//    }
//
//
//
//    @SuppressWarnings("unchecked")
//
//    public String queryPageByParams(Map<String,Object> param){
//
//        Serializable obj =(Serializable)param.remove(SPACE_TABLE_NAME);
//
//        Object orderBy = param.remove(Constant.MYBATIS_SPECIAL_STRING.ORDER_BY.name());
//
//        caculationColumnList(obj);
//
//        BEGIN();
//
//        SELECT(queryColumn(obj));
//
//        FROM(tableName(obj)+" "+obj.getClass().getSimpleName());
//
//        if(!((Map<String,Object>)param.get("params")).isEmpty())
//
//            WHERE(whereColumn((Map<String,Object>)param.get("params")));
//
//        if(orderBy!=null){
//
//            ORDER_BY(orderBy.toString());
//
//        }
//
//        return SQL();
//
//    }
//
//
//
//    public void WHEREID(final String idname){
//
//        WHERE(idname.replaceAll("([A-Z])", "_$1").toLowerCase() + "=#{" + idname + "}");
//
//    }
//
//
//
//    public String addlimit(Map<String,Object> param){
//
//        String subsql =" "+ Constant.MYBATIS_SPECIAL_STRING.LIMIT.name()+" ";
//
//        Object obj =param.remove(Constant.MYBATIS_SPECIAL_STRING.LIMIT.name());
//
//        if(null==obj){
//
//            subsql = subsql+"0,10";
//
//        }else{
//
//            subsql = subsql+obj;
//
//        }
//
//
//
//        return subsql;
//
//    }
//
//
//
//    private void removeEmpty(Map<String,Object> params){
//
//        Iterator<String> iterator = params.keySet().iterator();
//
//        while(iterator.hasNext()){
//
//            String key = iterator.next();
//
//            if(params.get(key)==null){
//
//                params.remove(key);
//
//                iterator = params.keySet().iterator();
//
//            }
//
//        }
//
//    }
}

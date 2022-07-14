package com.randke.mapper;

import com.randke.util.SqlProvider;
import org.apache.ibatis.annotations.InsertProvider;

public interface BaseMapper<T> {
    @InsertProvider(type = SqlProvider.class,method = "insert")
    int insert(T t );
}

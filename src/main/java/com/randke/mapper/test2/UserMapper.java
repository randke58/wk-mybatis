package com.randke.mapper.test2;

import com.randke.mapper.BaseMapper;
import com.randke.mapper.test1.Blog;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper {
    Blog select(int id);
}

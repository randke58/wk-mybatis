package com.randke.mapper.test1;

import com.randke.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogMapper extends BaseMapper {
    Blog select(int id);
}

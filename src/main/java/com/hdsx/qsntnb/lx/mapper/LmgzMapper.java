package com.hdsx.jhsjgx.lx.mapper;

import com.hdsx.jhsjgx.dao.Mapper;
import com.hdsx.jhsjgx.lx.vo.Lmgz;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Kimbo on 2017/5/8.
 */
@Mapper
public interface LmgzMapper {
    /**
     * 通过行政区划或者路线编码查询计划数据
     * @param xzqh 行政区划名称
     * @param ghlxbh 路线编码
     * @return
     */
    List<Lmgz> queryList(@Param("xzqh") String xzqh, @Param("ghlxbh") String ghlxbh);
}

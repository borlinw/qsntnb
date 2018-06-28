package com.hdsx.jhsjgx.api.mapper;

import com.hdsx.jhsjgx.dao.AppMapper;
import com.hdsx.jhsjgx.api.vo.*;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Kimbo on 2017/5/8.
 */
@AppMapper
public interface QlzpMapper {
    /**
     * 通过行政区划或者路线编码查询计划数据
     * @param xzqh 行政区划名称
     * @param ghlxbh 路线编码
     * @return
     */
    List<Qlzp> queryJckQlzp(HashMap<String,String> hm);
    List<Qlzp> queryJckQlzpNr(HashMap<String,String> hm);
    
}

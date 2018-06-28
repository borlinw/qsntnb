package com.hdsx.jhsjgx.api.mapper;

import com.hdsx.jhsjgx.dao.Mapper;
import com.hdsx.jhsjgx.api.vo.*;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Kimbo on 2017/5/8.
 */
//@AppMapper
@Mapper
public interface GsdgzMapper {
    /**
     * 通过行政区划或者路线编码查询计划数据
     * @param xzqh 行政区划名称
     * @param ghlxbh 路线编码
     * @return
     */
    List<Gsdgz> queryGsdgzList(HashMap<String,String> hm);
    List<Zhhfcj> queryZhhfcjList(HashMap<String,String> hm);
    List<Wqgz> queryWqgzList(HashMap<String,String> hm);
    List<Wqgz> queryWqgzByQlbm(HashMap<String,String> hm);
    
    List<Glafgc> queryGlafgcList(HashMap<String,String> hm);
    List<Zhfz> queryZhfzList(HashMap<String,String> hm);
    List<Yhdzx> queryYhdzxList(HashMap<String,String> hm);
    List<Lkpd> getRoadDetails(HashMap<String,String> hm);
    List<Lkpdfx> getStatisticsData(HashMap<String,String> hm);
    
    List<HashMap<String,String>>getgxlxbyzh(HashMap<String,String> hm);
    HashMap<String,String> queryDetailSql(HashMap<String,String> hm);
    HashMap<String,String> queryDetailLbSql(HashMap<String,String> hm);
    
    HashMap<String, String> queryRoadSql(HashMap<String,String> hm);
    List<HashMap<String,String>> queryDetail(String sql);
    List<HashMap<String,String>> queryRoad(String sql);
    List<HashMap<String,String>> queryDetailLb(String sql);
    
    List<HashMap<String,String>>getProjectMenuQq(HashMap<String,String> hm);
    List<HashMap<String,String>>getProjectMenuJh(HashMap<String,String> hm);
    List<HashMap<String,String>>getProjectMenuJd(HashMap<String,String> hm);
    List<Qlzp> queryJckQlzp(HashMap<String,String> hm);
    List<Qlzp> queryJckQlzpNr(HashMap<String,String> hm);
    List<HashMap<String,String>> queryDwByUser(HashMap<String,String> hm);

}

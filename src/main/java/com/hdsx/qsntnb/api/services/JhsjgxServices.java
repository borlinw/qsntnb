package com.hdsx.jhsjgx.api.services;

import com.hdsx.jhsjgx.api.vo.*;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Kimbo on 2017/5/8.
 */
public interface JhsjgxServices {
    public List<Gsdgz> queryGsdgz(HashMap<String,String> hm);
    public List<Zhhfcj> queryZhhfcj(HashMap<String,String> hm);
    public List<Wqgz> queryWqgz(HashMap<String,String> hm);
    public List<Wqgz> queryWqgzByQlbm(HashMap<String,String> hm);
    
    public List<Glafgc> queryGlafgc(HashMap<String,String> hm);
    public List<Zhfz> queryZhfz(HashMap<String,String> hm);
    public List<Yhdzx> queryYhdzx(HashMap<String,String> hm);
    public List<Lkpd> getRoadDetails(HashMap<String,String> hm);
    public List<Lkpdfx> getStatisticsData(HashMap<String,String> hm);
    
    public List<HashMap<String,String>> getStageProjectDetail(HashMap<String,String> hm);
    public List<List<HashMap<String, String>>> getStageProjectDetailLb(HashMap<String,String> hm);
    public List<List<HashMap<String, String>>> getStageProjectRoad(HashMap<String,String> hm);
    public List<HashMap<String,String>> getProjectMenu(HashMap<String,String> hm);
    public List<Qlzp> queryJckQlzp(HashMap<String,String> hm);
    public List<Qlzp> queryJckQlzpNr(HashMap<String,String> hm);
    public List<HashMap<String,String>> queryDwByUser(HashMap<String,String> hm);
}

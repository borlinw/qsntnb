package com.hdsx.jhsjgx.api.services.impl;

import com.hdsx.jhsjgx.api.mapper.GsdgzMapper;
import com.hdsx.jhsjgx.api.mapper.QlzpMapper;
import com.hdsx.jhsjgx.api.services.JhsjgxServices;
import com.hdsx.jhsjgx.api.vo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Kimbo on 2017/5/8.
 */
@Service
public class JhsjgxServicesImpl implements JhsjgxServices {
    @Autowired
    GsdgzMapper gsdgzMapper;
    @Autowired
    QlzpMapper qlzpMapper;
    public List<Gsdgz> queryGsdgz(HashMap<String,String> hm) {
    	List<Gsdgz> result=gsdgzMapper.queryGsdgzList( hm);
		
		/*if(!"".equals(hm.get("lxbm"))){
			if("".equals(hm.get("qdzh")))
				hm.put("qdzh", "0");
			if("".equals(hm.get("zdzh")))
				hm.put("zdzh","9999");
			List<HashMap<String,String>> l=gsdgzMapper.getgxlxbyzh(hm);
			if(l.size()>0)
				for (HashMap<String,String> lx2 : l) {
					hm.put("lxbm",lx2.get("lxbm"));
					hm.put("qdzh",lx2.get("qdzh"));
					hm.put("zdzh",lx2.get("zdzh"));
					List<Gsdgz> l2 = gsdgzMapper.queryGsdgzList( hm);
					result.addAll(l2);
				}
		}*/
        return result;
    }
    public List<Zhhfcj> queryZhhfcj(HashMap<String,String> hm) {
    	List<Zhhfcj> result=gsdgzMapper.queryZhhfcjList( hm);
        return result;
    }
    public List<Wqgz> queryWqgz(HashMap<String,String> hm) {
    	List<Wqgz> result=gsdgzMapper.queryWqgzList( hm);
        return result;
    }
    public List<Wqgz> queryWqgzByQlbm(HashMap<String,String> hm) {
    	List<Wqgz> result=gsdgzMapper.queryWqgzByQlbm( hm);
        return result;
    }
    public List<Glafgc> queryGlafgc(HashMap<String,String> hm) {
    	List<Glafgc> result=gsdgzMapper.queryGlafgcList( hm);
        return result;
    }
    public List<Zhfz> queryZhfz(HashMap<String,String> hm) {
    	List<Zhfz> result=gsdgzMapper.queryZhfzList( hm);
        return result;
    }
    public List<Yhdzx> queryYhdzx(HashMap<String,String> hm) {
    	List<Yhdzx> result=gsdgzMapper.queryYhdzxList( hm);
        return result;
    }
    public List<Lkpd> getRoadDetails(HashMap<String,String> hm) {
    	List<Lkpd> result=gsdgzMapper.getRoadDetails( hm);
        return result;
    }
    public List<Lkpdfx> getStatisticsData(HashMap<String,String> hm) {
    	List<Lkpdfx> result=gsdgzMapper.getStatisticsData( hm);
        return result;
    }
    
	@Override
	public List<HashMap<String, String>> getStageProjectDetail(
			HashMap<String, String> hm) {
		HashMap<String,String> shm= gsdgzMapper.queryDetailSql(hm);
		String sql="select  name, value from (";
		if("危桥改造".equals(hm.get("xmlx")) &&  
		   !  "基础项目库".equals(hm.get("jdbs")) &&  !"方案审查库".equals(hm.get("jdbs")) 
		  )
		{
		sql=sql+shm.get("SQL1")+" = ( select max(t.id) from plan_wqgz t,sck_wqgz s where t.sckid=s.sckid and s.xmkid='"+  hm.get("xmbm")+"')"+") unpivot (value for name in ("+shm.get("SQL2")+" ))";
		}else if(
				"灾害防治".equals(hm.get("xmlx")) &&  
				   ! "基础项目库".equals(hm.get("jdbs")) &&  !"方案审查库".equals(hm.get("jdbs"))
			){
			sql=sql+shm.get("SQL1")+" = ( select max(t.id) from plan_zhfz t,sck_zhfz s where t.sckid=s.sckid and s.xmkid='"+  hm.get("xmbm")+"')"+") unpivot (value for name in ("+shm.get("SQL2")+" ))";

		}else if(
				"公路安防工程".equals(hm.get("xmlx")) &&  
				   !"基础项目库".equals(hm.get("jdbs")) &&  !"方案审查库".equals(hm.get("jdbs" ))
			){
			sql=sql+shm.get("SQL1")+" = ( select max(t.id) from plan_af t,sck_aqsmfh s,sck_aqsmfh_ld sl where t.sckid=s.id and s.xmbm=sl.xmbm and sl.xmkid='"+  hm.get("xmbm")+"')"+") unpivot (value for name in ("+shm.get("SQL2")+" ))";

		}else if(
				"公路安防工程".equals(hm.get("xmlx")) &&  
				   "方案审查库".equals(hm.get("jdbs")  )
			){
			sql=sql+shm.get("SQL1")+" = ( select max(s.id) from sck_aqsmfh s,sck_aqsmfh_ld sl where  s.xmbm=sl.xmbm and sl.xmkid='"+  hm.get("xmbm")+"')"+") unpivot (value for name in ("+shm.get("SQL2")+" ))";

		}
		
		else{
			sql=sql+shm.get("SQL1")+" = '"+hm.get("xmbm")+"'"+") unpivot (value for name in ("+shm.get("SQL2")+" ))";
		}
		List<HashMap<String, String>> result=gsdgzMapper.queryDetail( sql);
		return result;
	}
	@Override
	public List<List<HashMap<String, String>>> getStageProjectDetailLb(
			HashMap<String, String> hm) {
		HashMap<String,String> shm= gsdgzMapper.queryDetailLbSql( hm);
		String sql="select  name, value from (";
		sql=sql+shm.get("SQL1")+" = '"+hm.get("xmbm")+"'"+") unpivot (value for name in ("+shm.get("SQL2")+" ))";
		List<HashMap<String, String>> result=gsdgzMapper.queryDetailLb( sql);
		
		List<List<HashMap<String, String>>> l=new ArrayList<List<HashMap<String,String>>>();
		List<HashMap<String,String>> l1=null;
		if(result.size()>0)
		{
			String name=result.get(0).get("NAME");
		for(int i=0;i<result.size();i++){
			if(name.equals(result.get(i).get("NAME")))
			{
				if(i!=0) l.add(l1);
				l1=new ArrayList<HashMap<String,String>>();
				l1.add(result.get(i));
			}else{
				l1.add(result.get(i));
			}
		}
		if(result.size()>0)l.add(l1);
		}
		return l;
	}
	
	@Override
	public List<List<HashMap<String, String>>> getStageProjectRoad(
			HashMap<String, String> hm) {
		HashMap<String,String> shm= gsdgzMapper.queryRoadSql( hm);
		String sql="select  name, value from (";
		sql=sql+shm.get("SQL1")+" = '"+hm.get("xmbm")+"'"+") unpivot (value for name in ("+shm.get("SQL2")+" ))";
		List<HashMap<String, String>> result=gsdgzMapper.queryRoad( sql);
		List<List<HashMap<String, String>>> l=new ArrayList<List<HashMap<String,String>>>();
		List<HashMap<String,String>> l1=null;
		if(result.size()>0)
		{
		String name=result.get(0).get("NAME");
		for(int i=0;i<result.size();i++){
			if(name.equals(result.get(i).get("NAME")))
			{
				if(i!=0) l.add(l1);
				l1=new ArrayList<HashMap<String,String>>();
				l1.add(result.get(i));
			}else{
				l1.add(result.get(i));
			}
		}
		if(result.size()>0)l.add(l1);
		}
		return l;
	}
	
	@Override
	public List<HashMap<String, String>> getProjectMenu(
			HashMap<String, String> hm) {
		List<HashMap<String, String>> result=null;
		if("前期管理".equals(hm.get("xmlx")))
			 result=gsdgzMapper.getProjectMenuQq(hm);
		if("计划管理".equals(hm.get("xmlx")))
			 result=gsdgzMapper.getProjectMenuJh(hm);
		if("进度报表".equals(hm.get("xmlx")))
			 result=gsdgzMapper.getProjectMenuJd(hm);
		return result;
	}
	@Override
	public List<Qlzp> queryJckQlzp(HashMap<String, String> hm) {
		// TODO Auto-generated method stub
		List<Qlzp> list=qlzpMapper.queryJckQlzp(hm);
		List<Qlzp> list2=gsdgzMapper.queryJckQlzp(hm);
		if(list2.size()>0)
		list.addAll(list2);
		return list;
	}
	@Override
	public List<Qlzp> queryJckQlzpNr(HashMap<String, String> hm) {
		// TODO Auto-generated method stub
		List<Qlzp> list=qlzpMapper.queryJckQlzpNr(hm);
		List<Qlzp> list2=gsdgzMapper.queryJckQlzpNr(hm);
		if(list2.size()>0)
		list.addAll(list2);
		return list;
	}
	@Override
	public List<HashMap<String, String>> queryDwByUser(
			HashMap<String, String> hm) {
		// TODO Auto-generated method stub
		return gsdgzMapper.queryDwByUser(hm);
	}
}

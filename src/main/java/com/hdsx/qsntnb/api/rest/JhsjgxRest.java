package com.hdsx.jhsjgx.api.rest;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;

import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.guice.MyBatisModule;
import org.mybatis.guice.datasource.builtin.PooledDataSourceProvider;

import java.util.Properties;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.hdsx.forest.common.ServerMessage;
import com.hdsx.jhsjgx.api.mapper.DataReader;
import com.hdsx.jhsjgx.api.services.JhsjgxServices;
import com.hdsx.jhsjgx.api.services.impl.Export2sql;
import com.hdsx.jhsjgx.api.util.FileUtil;
import com.hdsx.jhsjgx.api.vo.Glafgc;
import com.hdsx.jhsjgx.api.vo.Gsdgz;
import com.hdsx.jhsjgx.api.vo.Lkpd;
import com.hdsx.jhsjgx.api.vo.Lkpdfx;
import com.hdsx.jhsjgx.api.vo.Qlzp;
import com.hdsx.jhsjgx.api.vo.Wqgz;
import com.hdsx.jhsjgx.api.vo.Yhdzx;
import com.hdsx.jhsjgx.api.vo.Zhfz;
import com.hdsx.jhsjgx.api.vo.Zhhfcj;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sun.misc.BASE64Encoder;
/**
 * Created by Kimbo on 2017/5/8.
 */
@RestController
@RequestMapping("api")
@Api(description = "计划数据查询服务")
public class JhsjgxRest {
    private static final Logger logger = LoggerFactory.getLogger(JhsjgxRest.class);
    @Autowired
    JhsjgxServices JhsjgxServices;
    @RequestMapping(value = "getProjectList", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取项目模块的列表数据")
    public ServerMessage getProjectList(@ApiParam(value = "路线编码", required = false) @RequestParam(value = "lxbm", required = false) String lxbm,
                                        @ApiParam(value = "行政区划代码", required = true) @RequestParam(value = "xzqh", required = true) String xzqh,
                                        @ApiParam(value = "项目类型", required = true) @RequestParam(value = "xmlx", required = true) String xmlx,
                                        @ApiParam(value = "一页多少条数据", required = true) @RequestParam(value = "pagesize", required = true) String pagesize,
                                        @ApiParam(value = "第几页数据", required = true) @RequestParam(value = "page", required = true) String page,
                                        @ApiParam(value = "起点桩号", required = false) @RequestParam(value = "qdzh", required = false) String qdzh,
                                        @ApiParam(value = "止点桩号", required = false) @RequestParam(value = "zdzh", required = false) String zdzh){
        System.out.println("======"+xmlx);
    	HashMap<String,String> hm=new HashMap<String,String>();
        hm.put("lxbm", lxbm);
        hm.put("xzqh", xzqh);
        hm.put("pagesize", pagesize);
        hm.put("page", page);
        hm.put("qdzh", qdzh);
        hm.put("zdzh", zdzh);
    	ServerMessage message = new ServerMessage();
        try{
        	int size=0;
            if("国省道改造".equals(xmlx))
            	{
	            	List<Gsdgz> l=JhsjgxServices.queryGsdgz(hm);
	                size=l.size();
	                if(size>0)
	                	{message.setData(l);
	                	message.setMsg("获取数据成功");
	                	}
	                	
	                else
	                	{message.setData("");
	                	message.setMsg("暂无数据");}
            	}
            if("灾毁恢复重建".equals(xmlx))
            {
            	List<Zhhfcj> l=JhsjgxServices.queryZhhfcj(hm);
                size=l.size();
                if(size>0)
                	{message.setData(l);
                	message.setMsg("获取数据成功");
                	}
                else
                	{message.setData("");
                	message.setMsg("暂无数据");}
        	}
            	
            if("危桥改造".equals(xmlx))
            {
            	List<Wqgz> l=JhsjgxServices.queryWqgz(hm);
                size=l.size();
                if(size>0)
                	{message.setData(l);
                	message.setMsg("获取数据成功");
                	}
                else
                	{message.setData("");
                	message.setMsg("暂无数据");}
        	}
            	
            if("公路安防工程".equals(xmlx))
            {
            	List<Glafgc> l=JhsjgxServices.queryGlafgc(hm);
                size=l.size();
                if(size>0)
                	{message.setData(l);
                	message.setMsg("获取数据成功");
                	}
                else
                	{message.setData("");
                	message.setMsg("暂无数据");}
        	}
            if("灾害防治".equals(xmlx))
            {
            	List<Zhfz> l=JhsjgxServices.queryZhfz(hm);
                size=l.size();
                if(size>0)
                	{message.setData(l);
                	message.setMsg("获取数据成功");
                	}
                else
                	{message.setData("");
                	message.setMsg("暂无数据");}
        	}
            if("养护大中修".equals(xmlx))
            {
            	List<Yhdzx> l=JhsjgxServices.queryYhdzx(hm);
                size=l.size();
                if(size>0)
                	{message.setData(l);
                	message.setMsg("获取数据成功");
                	}
                else
                	{message.setData("");
                	message.setMsg("暂无数据");}
        	}
            
            message.setCode("1");
        }catch (Exception ex){
            message.setSuccess(false);
            message.setData(ex.getMessage());
            message.setCode("0");
            logger.error("查询计划数据列表报错",ex);
        }
        return message;
    }
    
    @RequestMapping(value = "queryWqgzByQlbm", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取项目模块的列表数据")
    public ServerMessage queryWqgzByQlbm(@ApiParam(value = "桥梁编码", required = false) @RequestParam(value = "qlbm", required = false) String qlbm){
    	HashMap<String,String> hm=new HashMap<String,String>();
        hm.put("qlbm", qlbm);

    	ServerMessage message = new ServerMessage();
        try{
        	int size=0;
            
            	List<Wqgz> l=JhsjgxServices.queryWqgzByQlbm(hm);
                size=l.size();
                if(size>0)
                	{message.setData(l);
                	message.setMsg("获取数据成功");
                	}
                else
                	{message.setData("");
                	message.setMsg("暂无数据");
                	}
        	
            message.setCode("1");
        }catch (Exception ex){
            message.setSuccess(false);
            message.setData(ex.getMessage());
            message.setCode("0");
            logger.error("查询计划数据列表报错",ex);
        }
        return message;
    }
    @RequestMapping(value = "getStageProjectDetail", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "根据项目编码和阶段名称获取各个的阶段的详情")
    public ServerMessage getStageProjectDetail(@ApiParam(value = "项目编码", required = true) @RequestParam(value = "xmbm", required = true) String xmbm,
                                        @ApiParam(value = "项目类型", required = true) @RequestParam(value = "xmlx", required = true) String xmlx,
                                        @ApiParam(value = "阶段标识", required = true) @RequestParam(value = "jdbs", required = true) String jdbs){
        System.out.println("======"+xmlx);
    	HashMap<String,String> hm=new HashMap<String,String>();
        hm.put("xmbm", xmbm);
        hm.put("xmlx", xmlx);
        hm.put("jdbs", jdbs);
    	ServerMessage message = new ServerMessage();
        try{
        	if(!("计划下达".equals(jdbs) || "月报".equals(jdbs) ||"资金拨付".equals(jdbs) ))
        	{
        		List<HashMap<String,String>> l=JhsjgxServices.getStageProjectDetail(hm);
                if(l != null)
                	{message.setData(l);
                	message.setMsg("获取数据成功");
                	}
                else
                	{message.setData("");
                	message.setMsg("暂无数据");}
        	}
        	else
        	{
        		List<List<HashMap<String, String>>> l=JhsjgxServices.getStageProjectDetailLb(hm);
                if(l.size()>0 )
                	{message.setData(l);
                	message.setMsg("获取数据成功");
                	}
                else
                	{message.setData("");
                	message.setMsg("暂无数据");}
        	}
        	message.setCode("1");
        }catch (Exception ex){
            message.setSuccess(false);
            message.setData(ex.getMessage());
            message.setCode("0");
            logger.error("查询计划数据列表报错",ex);
        }
        return message;
    }
    @RequestMapping(value = "getStageProjectRoad", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "根据项目编码和阶段名称获取各个的阶段的路线")
    public ServerMessage getStageProjectRoad(@ApiParam(value = "项目编码", required = true) @RequestParam(value = "xmbm", required = true) String xmbm,
                                        @ApiParam(value = "项目类型", required = true) @RequestParam(value = "xmlx", required = true) String xmlx,
                                        @ApiParam(value = "阶段标识", required = true) @RequestParam(value = "jdbs", required = true) String jdbs){
        System.out.println("======"+xmlx);
    	HashMap<String,String> hm=new HashMap<String,String>();
        hm.put("xmbm", xmbm);
        hm.put("xmlx", xmlx);
        if("项目立项".equals(jdbs) || "工程可行性报告".equals(jdbs)){
        	hm.put("jdbs", jdbs);
        }else if("灾毁恢复重建".equals(xmlx) || "养护大中修".equals(xmlx)){
        	hm.put("jdbs", "施工图设计");
        }else{
        	hm.put("jdbs", "工程初步设计");
        }
        
    	ServerMessage message = new ServerMessage();
        try{
        	{
        		List<List<HashMap<String, String>>> l=JhsjgxServices.getStageProjectRoad(hm);
                if(l.size()>0)
                	{message.setData(l);
                	message.setMsg("获取数据成功");}
                else
                	{message.setData("");
                	message.setMsg("暂无数据");}
        	}
            message.setCode("1");
            
        }catch (Exception ex){
            message.setSuccess(false);
            message.setData(ex.getMessage());
            message.setCode("0");
            logger.error("查询计划数据列表报错",ex);
        }
        return message;
    }
    
    @RequestMapping(value = "getProjectMenu", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "点击查询当前项目的可用菜单")
    public ServerMessage getProjectMenu(@ApiParam(value = "项目编码", required = true) @RequestParam(value = "xmbm", required = true) String xmbm,
                                        @ApiParam(value = "项目类型", required = true) @RequestParam(value = "xmlx", required = true) String xmlx
                                        ){
        System.out.println("======"+xmlx);
    	HashMap<String,String> hm=new HashMap<String,String>();
        hm.put("xmbm", xmbm);
        hm.put("xmlx", xmlx);
    	ServerMessage message = new ServerMessage();
        try{
        	{
            	List<HashMap<String, String>> l=JhsjgxServices.getProjectMenu(hm);
                if(l.size()>0)
                	{message.setData(l);
                	message.setMsg("获取数据成功");}
                else
                	{message.setData("");
                	message.setMsg("暂无数据");}
        	}
            message.setCode("1");
        }catch (Exception ex){
            message.setSuccess(false);
            message.setData(ex.getMessage());
            message.setCode("0");
            logger.error("查询计划数据列表报错",ex);
        }
        return message;
    }
    
    
    @RequestMapping(value = "getRoadDetails", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "通过路线编码、桩号、方向获取路况信息")
    public ServerMessage getRoadDetails(@ApiParam(value = "路线编码", required = true) @RequestParam(value = "lxbm", required = true) String lxbm,
                                        @ApiParam(value = "桩号", required = true) @RequestParam(value = "zh", required = true) String zh){
    	HashMap<String,String> hm=new HashMap<String,String>();
        hm.put("lxbm", lxbm);
        hm.put("zh", zh);
    	ServerMessage message = new ServerMessage();
        try{
        	int size=0;
            
	            	List<Lkpd> l=JhsjgxServices.getRoadDetails(hm);
	                size=l.size();
	                if(size>0)
	                	{message.setData(l);
	                	message.setMsg("获取数据成功");
	                	}
	                	
	                else
	                	{message.setData("");
	                	message.setMsg("暂无数据");}
	                message.setCode("1"); 
            	}
            catch (Exception ex){
            message.setSuccess(false);
            message.setData(ex.getMessage());
            message.setCode("0");
            logger.error("查询计划数据列表报错",ex);
        }
        return message;
    }
    
    @RequestMapping(value = "getStatisticsData", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "通过年份、路线编码获取路况信息")
    public ServerMessage getStatisticsData(@ApiParam(value = "管理路段", required = true) @RequestParam(value = "glld", required = true) String glld,
                                        @ApiParam(value = "路况评定版本", required = true) @RequestParam(value = "lkpdbb", required = true) String lkpdbb){
    	HashMap<String,String> hm=new HashMap<String,String>();
        hm.put("lxbh", glld);
        hm.put("tbnf", lkpdbb);
    	ServerMessage message = new ServerMessage();
        try{
        	int size=0;
            
	            	List<Lkpdfx> l=JhsjgxServices.getStatisticsData(hm);
	                size=l.size();
	                if(size>0)
	                	{message.setData(l);
	                	message.setMsg("获取数据成功");
	                	}
	                	
	                else
	                	{message.setData("");
	                	message.setMsg("暂无数据");}
	                message.setCode("1"); 
            	}
            catch (Exception ex){
            message.setSuccess(false);
            message.setData(ex.getMessage());
            message.setCode("0");
            logger.error("查询计划数据列表报错",ex);
        }
        return message;
    }
    
    @RequestMapping(value = "queryJckQlzp", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取基础库桥梁照片")
    public ServerMessage queryJckQlzp(@ApiParam(value = "PARENTID", required = true) @RequestParam(value = "parentid", required = true) String parentid,
    		@ApiParam(value = "文件类型", required = false) @RequestParam(value = "filetype", required = false) String filetype){
    	HashMap<String,String> hm=new HashMap<String,String>();
        hm.put("parentid", parentid);
        hm.put("filetype", filetype);
    	ServerMessage message = new ServerMessage();
        try{
        	int size=0;
            
            	List<Qlzp> l=JhsjgxServices.queryJckQlzp(hm);
                size=l.size();
                if(size>0)
                	{message.setData(l);
                	message.setMsg("获取数据成功");
                	}
                else
                	{message.setData("");
                	message.setMsg("暂无数据");
                	}
        	
            message.setCode("1");
        }catch (Exception ex){
            message.setSuccess(false);
            message.setData(ex.getMessage());
            message.setCode("0");
            logger.error("查询基础库桥梁照片报错",ex);
        }
        return message;
    }
    @RequestMapping(value = "queryJckQlzpNr", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取基础库桥梁照片")
    public ServerMessage queryJckQlzpNr(@ApiParam(value = "ID", required = true) @RequestParam(value = "id", required = true) String id){
    	HashMap<String,String> hm=new HashMap<String,String>();
        hm.put("id", id);
    	ServerMessage message = new ServerMessage();
        try{
        	int size=0;
            
            	List<Qlzp> l=JhsjgxServices.queryJckQlzpNr(hm);
                size=l.size();
                if(size>0)
                	{message.setData(l);
                	message.setMsg("获取数据成功");
                	}
                else
                	{message.setData("");
                	message.setMsg("暂无数据");
                	}
        	
            message.setCode("1");
        }catch (Exception ex){
            message.setSuccess(false);
            message.setData(ex.getMessage());
            message.setCode("0");
            logger.error("查询基础库桥梁照片报错",ex);
        }
        return message;
    }
    @RequestMapping(value = "downloadData", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "下载数据更新的文件")
    public ServerMessage downloadData(@ApiParam(value = "username", required = true) @RequestParam(value = "username", required = true) String username,
    		@ApiParam(value = "password", required = true) @RequestParam(value = "password", required = true) String password){
    	HashMap<String,String> hm=new HashMap<String,String>();
    	MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base = new BASE64Encoder();
			String temp = base.encode(md5.digest(password.getBytes()));
			hm.put("password", temp);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		System.out.println("username::::::::::::"+username);
        hm.put("username", username);
        List<HashMap<String,String>> lhm=JhsjgxServices.queryDwByUser(hm);
        
    	ServerMessage message = new ServerMessage();
    	if(lhm.size()==0){
    		message.setSuccess(false);
            message.setCode("0");
    	}else{
	       System.out.println("unit::::::::::::"+lhm.get(0).get("UNIT"));
	        		final Properties myBatisProperties = new Properties();
	        		myBatisProperties.setProperty("mybatis.environment.id", "mysql");
	        		myBatisProperties.setProperty("JDBC.url", "jdbc:oracle:thin:@36.2.6.12:1521:orcl");
	        		myBatisProperties.setProperty("JDBC.driver", "oracle.jdbc.driver.OracleDriver");
	        		myBatisProperties.setProperty("JDBC.username", "jhsjgx");
	        		myBatisProperties.setProperty("JDBC.password", "jhsjgx");
	        		myBatisProperties.setProperty("JDBC.autoCommit", "true");
	        		FileUtil fu=new FileUtil();
	        		String ur="F:\\JavaTools\\apache-tomcat-7.0.26\\webapps\\jhsjgx\\";
	        		//String ur="E:\\apache-tomcat-7.0.68089\\webapps\\jhsjgx\\";
	        		String url1 = ur+"jxjhxcProNull.sqlite";// 源文件路径
	        		String url2 = ur+username+"jxjhxcPro.sqlite";// 目标路径（复制到E盘，重命名为b.txt）
	        		try {
	        			fu.fileCope(url1,url2);
	        			Injector injector = Guice.createInjector(new MyBatisModule() {
	        				@Override
	        				protected void initialize() {
	        					Names.bindProperties(this.binder(), myBatisProperties);
	        					bindDataSourceProviderType(PooledDataSourceProvider.class);
	        					bindTransactionFactoryType(JdbcTransactionFactory.class);
	        					addMapperClass(DataReader.class);
	        					this.bind(Export2sql.class);
	        				}
	        			});
	        			Export2sql service = injector.getInstance(Export2sql.class);
	        			String unit=lhm.get(0).get("UNIT");
	        			if(unit.endsWith("00"))
	        			{
	        				unit=unit.substring(0, unit.length()-2);
	        				
	        			}
	        			if(unit.endsWith("00"))
	        			{
	        				unit=unit.substring(0, unit.length()-2);
	        			}
	        			
	        			if("36".equals(unit)){
	        				unit="%";
	        			}else{
	        				unit=unit+"%";
	        			}
	        	        boolean succeed = service.exportDataFromOracle( url2,unit);
	        			
	        	        //InputStream inputStream = new FileInputStream(url2);
	        	        
	        	        message.setData("http://36.2.11.57:8089/jhsjgx/"+username+"jxjhxcPro.sqlite");
	        	        message.setMsg("获取数据成功");
	                    message.setCode("1");
	        }catch (Exception ex){
	            message.setSuccess(false);
	            message.setData(ex.getMessage());
	            message.setCode("0");
	            logger.error("查询基础库桥梁照片报错",ex);
	        }
    	
    	}
        return message;
    }
    
    private byte [] inputStreamToByte(InputStream is) throws IOException { 
	    ByteArrayOutputStream bAOutputStream = new ByteArrayOutputStream(); 
	    byte [] arr = new byte[1024*10];
	    int ch; 
	    while((ch = is.read(arr) ) != -1){ 
	        bAOutputStream.write(arr,0,ch); 
	    } 
	    byte data [] =bAOutputStream.toByteArray(); 
	    bAOutputStream.close(); 
	    return data; 
	}
}

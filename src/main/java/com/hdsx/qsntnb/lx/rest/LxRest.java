package com.hdsx.jhsjgx.lx.rest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import com.hdsx.forest.common.ServerMessage;
import com.hdsx.jhsjgx.api.mapper.DataReader;
import com.hdsx.jhsjgx.api.services.impl.Export2sql;
import com.hdsx.jhsjgx.api.util.FileUtil;
import com.hdsx.jhsjgx.lx.services.LxServices;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.guice.MyBatisModule;
import org.mybatis.guice.datasource.builtin.PooledDataSourceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sun.misc.BASE64Encoder;

/**
 * Created by Kimbo on 2017/5/8.
 */
@RestController
@RequestMapping("lx")
@Api(description = "计划数据查询服务")
public class LxRest {
    private static final Logger logger = LoggerFactory.getLogger(LxRest.class);
    @Autowired
    LxServices lxServices;
    @RequestMapping(value = "query", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "通过行政区划或者路线编码查询计划数据")
    public ServerMessage BlockHeatFeature(@ApiParam(value = "行政区划名称", required = false) @RequestParam(value = "xzqh", required = false) String xzqh,
                                          @ApiParam(value = "路线编码", required = false) @RequestParam(value = "ghlxbh", required = false) String ghlxbh){
        ServerMessage message = new ServerMessage();
        try{
            message.setData(lxServices.query(xzqh,ghlxbh));
        }catch (Exception ex){
            message.setSuccess(false);
            message.setData(ex.getMessage());
            logger.error("查询计划数据列表报错",ex);
        }
        return message;
    }
    
    
    @RequestMapping(value = "query2", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "通过行政区划或者路线编码查询计划数据222")
    public ServerMessage BlockHeatFeature2(@ApiParam(value = "行政区划名称", required = false) @RequestParam(value = "xzqh", required = false) String xzqh,
                                          @ApiParam(value = "路线编码", required = false) @RequestParam(value = "ghlxbh", required = false) String ghlxbh){
        ServerMessage message = new ServerMessage();
        try{
            message.setData(lxServices.query(xzqh,ghlxbh));
        }catch (Exception ex){
            message.setSuccess(false);
            message.setData(ex.getMessage());
            logger.error("查询计划数据列表报错",ex);
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
		
        hm.put("username", username);
        hm.put("password", password);
        List<HashMap<String,String>> lhm=null;//JhsjgxServices.queryDwByUser(hm);
        
    	ServerMessage message = new ServerMessage();
    	if(lhm.size()==0){
    		message.setSuccess(false);
            message.setCode("0");
    	}else{
	       
	        		final Properties myBatisProperties = new Properties();
	        		myBatisProperties.setProperty("mybatis.environment.id", "mysql");
	        		myBatisProperties.setProperty("JDBC.url", "jdbc:oracle:thin:@211.101.37.230:1521:fantlam");
	        		myBatisProperties.setProperty("JDBC.driver", "oracle.jdbc.driver.OracleDriver");
	        		myBatisProperties.setProperty("JDBC.username", "jhsjgx");
	        		myBatisProperties.setProperty("JDBC.password", "jhsjgx");
	        		myBatisProperties.setProperty("JDBC.autoCommit", "true");
	        		FileUtil fu=new FileUtil();
	        		String url1 = "D:\\apache-tomcat-7.0.26\\webapps\\jhsjgx\\jxjhxcProNull.sqlite";// 源文件路径
	        		String url2 = "D:\\apache-tomcat-7.0.26\\webapps\\jhsjgx\\jxjhxcPro.sqlite";// 目标路径（复制到E盘，重命名为b.txt）
	        		try {
	        			
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
	        			
	        }catch (Exception ex){
	            message.setSuccess(false);
	            message.setData(ex.getMessage());
	            message.setCode("0");
	            logger.error("查询基础库桥梁照片报错",ex);
	        }
    	
    	}
        return message;
    }
}

package com.hdsx.jhsjgx.lx.services.impl;

import com.hdsx.jhsjgx.lx.mapper.LmgzMapper;
import com.hdsx.jhsjgx.lx.services.LxServices;
import com.hdsx.jhsjgx.lx.vo.Lmgz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Kimbo on 2017/5/8.
 */
@Service
public class LxServicesImpl implements LxServices {
    @Autowired
    LmgzMapper lmgzMapper;
    public List<Lmgz> query(String xzqh,String ghlxbh) {
        return lmgzMapper.queryList(xzqh,ghlxbh);
    }
}

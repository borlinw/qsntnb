package com.hdsx.jhsjgx.fastjson;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.fastjson.support.springfox.SwaggerJsonSerializer;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.Point;
import springfox.documentation.spring.web.json.Json;

/**
 * Created by Kimbo on 2017/5/8.
 */
public class FastJsonHttpMessageConverterEx extends FastJsonHttpMessageConverter {
    public FastJsonHttpMessageConverterEx() {
        super();
        SerializeConfig config=this.getFastJsonConfig().getSerializeConfig();
        config.put(Json.class, SwaggerJsonSerializer.instance);
    }
}
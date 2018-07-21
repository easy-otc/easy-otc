package com.easytech.otc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.easytech.otc.common.mybatis.plugin.OtcMapper;

@SpringBootApplication
@MapperScan(basePackages = "com.easytech.otc.mapper", markerInterface = OtcMapper.class)
public class Application {

    @Value("${server.port}")
    private String port;
    @Value("${server.mvc.viewPrefix}")
    private String mvcViewPrefix;
    @Value("${server.mvc.viewSuffix}")
    private String mvcViewSuffix;

    public static void main(String[] args) {

        // Cyclic reference detection
        JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.DisableCircularReferenceDetect.getMask();
        // Include fields with a value of null
        JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.WriteMapNullValue.getMask();
        // The first level fields are sorted alphabetically
        JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.SortField.getMask();
        // Nested fields are sorted alphabetically
        JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.MapSortField.getMask();

        ApplicationContext context = SpringApplication.run(Application.class, args);
        Application application = context.getBean(Application.class);

        System.setProperty("server.port", application.port);
        //        System.setProperty("spring.mvc.view.prefix", application.mvcViewPrefix);
        //        System.setProperty("spring.mvc.view.suffix", application.mvcViewSuffix);
    }
}
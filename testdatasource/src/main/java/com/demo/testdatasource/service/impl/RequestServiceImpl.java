package com.demo.testdatasource.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.testdatasource.mapper.RequestMapper;
import com.demo.testdatasource.model.Request;
import com.demo.testdatasource.service.RequestService;
import com.demo.testdatasource.utils.ExcelUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * <p>
 * 项目申报信息 服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2022-03-11
 */
@Service
public class RequestServiceImpl extends ServiceImpl<RequestMapper, Request> implements RequestService {

    @Override
    public void exportHedgingRegistrationTemplate(HttpServletResponse response) {
        System.out.println("连通测试");
        String fileName;
        String[] titles;
        String[][] values;
        ArrayList<String> tableValueList = new ArrayList<>();
        ArrayList<String> titlesList = new ArrayList<>();
        titlesList.add(0, "序号");
        tableValueList.add(0, "rowId");
        tableValueList.add("name");
        tableValueList.add("age");
        tableValueList.add("class");
        titlesList.add("姓名");
        titlesList.add("年龄");
        titlesList.add("班级");
        //表头字段
        titles = titlesList.toArray(new String[0]);
        values = new String[1][tableValueList.size()];
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[i].length; j++) {
                String info;
                    //如果标签为政银担业务报送
                    info = tableInfoMapHedgingRegistration.get(tableValueList.get(j));
                    if (info != null) {
                        values[i][j] = info;
                    }
            }
        }
        fileName = "学生表导出";
        ExcelUtils.demo(titles, values, fileName + "_"  + "_模板", response);
    }

    /**
     * 下载模版 信息案例map（政银担业务报送）
     */
    private static final HashMap<String, String> tableInfoMapHedgingRegistration = new LinkedHashMap<String, String>() {{
        put("rowId", "1");
        put("name", "youwei");
        put("age", "19");
        put("class", "一班");
    }};
}

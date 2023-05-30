package com.demo.testdatasource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.testdatasource.model.Merchant;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/4/22 21:08
 */
@Mapper
public interface MerchantMapper extends BaseMapper<Merchant> {
    Integer getMerchantCount(Map<String, Object> map);
}

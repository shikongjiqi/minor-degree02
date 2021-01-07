package edu.huc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.huc.bean.Major;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MajorMapper extends BaseMapper<Major> {
}
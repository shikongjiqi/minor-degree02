package edu.huc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.huc.bean.EntryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EntryFormMapper extends BaseMapper<EntryForm> {
    @Select({"update tb_entry_form set checked = 0 where entry_form_id = #{entryFormId}"})
    void updatCheck(int entryFormId);

    void updateEntryFormReserved1(String newFileName ,String userName);
}
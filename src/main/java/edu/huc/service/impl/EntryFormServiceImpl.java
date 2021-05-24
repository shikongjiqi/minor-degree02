package edu.huc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.huc.bean.EntryForm;
import edu.huc.bean.Major;
import edu.huc.bean.Minor;
import edu.huc.bean.User;
import edu.huc.common.response.RespCode;
import edu.huc.common.response.RespData;
import edu.huc.common.vo.ApplyUserVo;
import edu.huc.common.vo.EntryFormVo;
import edu.huc.common.vo.PageVo;
import edu.huc.dao.EntryFormMapper;
import edu.huc.dao.MajorMapper;
import edu.huc.dao.MinorMapper;
import edu.huc.dao.UserMapper;
import edu.huc.service.IEntryFormService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EntryFormServiceImpl implements IEntryFormService {
    @Resource
    private EntryFormMapper entryFormMapper;
    @Resource
    private MinorMapper minorMapper;
    @Resource
    private MajorMapper majorMapper;
    @Resource
    private UserMapper userMapper;

    //申请报名辅修学位
    @Override
    public RespData apply(EntryForm entryForm, String name, String name1, String username) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",username);
        User user = userMapper.selectOne(queryWrapper);
        //校验是否为本人报名
        if (user == null){
            return new RespData(RespCode.DUPLICATE_KEY);
        }
        if (!name.equals(name1)){
            return new RespData(RespCode.DUPLICATE_KEY);
        }
        queryWrapper.clear();
        queryWrapper.eq("userName",entryForm.getUserName());
        queryWrapper.eq("majorName",entryForm.getMajorName());
        queryWrapper.eq("minorId",entryForm.getMinorId());
        EntryForm apply = entryFormMapper.selectOne(queryWrapper);
        if (apply == null){
            entryFormMapper.insert(entryForm);//进行报名，向报名表中添加数据
            return new RespData(RespCode.SUCCESS);
        }else {
            return new RespData(RespCode.REPETITION);
        }
    }

    //检验报名辅修学位前是否登录
    @Override
    public RespData check(Integer id) {
        if (id == null || id == 0)
            return new RespData(RespCode.ERROR_SESSION);
        return new RespData(RespCode.SUCCESS);
    }

    //查询没有审核的报名数据
    @Override
    public RespData queryToAudit(int page) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("checked",1);
        Page entryFormList = entryFormMapper.selectPage(new Page<>(page, 10), queryWrapper);
        List<EntryFormVo> list = convertUserList(entryFormList.getRecords());
        entryFormList.setRecords(list);
        return new RespData(RespCode.SUCCESS,entryFormList);
    }

    //对报名用户进行审核
    @Override
    public RespData checkApply(int entryFormId) {
        entryFormMapper.updatCheck(entryFormId);//对数据审核
        //审核后修改辅修课程的报名人数
        EntryForm entryForm = entryFormMapper.selectById(entryFormId);
        Minor minor = minorMapper.selectById(entryForm.getMinorId());
        minor.setCount(minor.getCount()+1);
        minorMapper.update(minor,null);
        return new RespData(RespCode.SUCCESS);
    }

    //对于报名人员的查询
    @Override
    public RespData queryApplyUser(int page) {
        Page<EntryForm> entryFormPage = entryFormMapper.selectPage(new Page<>(page, 10), null);
        List<ApplyUserVo> userList = convertResultApplyUserList(entryFormPage.getRecords());
        PageVo resultPage = new PageVo();
        resultPage.setData(userList);
        resultPage.setPage(page);
        resultPage.setPageSize(entryFormPage.getSize());
        resultPage.setCount(entryFormPage.getTotal());
        return new RespData(RespCode.SUCCESS,resultPage);
    }

    //通过全部审核
    @Override
    public RespData allApply() {
//        entryFormMapper.update(null);
        return new RespData(RespCode.SUCCESS);
    }

    @Override
    public void deleteEntry(int entryFormId) {
        entryFormMapper.deleteById(entryFormId);
    }

    //将待审核数据转换为我们所需要的，便于页面话处理的对象
    private List<EntryFormVo> convertUserList(List<EntryForm> list){
        List<EntryFormVo> entryFormList = new ArrayList<>();
        for (EntryForm entryForm : list) {
            EntryFormVo resultEntryForm = new EntryFormVo();
            resultEntryForm.setEntryFormId(entryForm.getEntryFormId());
            resultEntryForm.setMajorName(entryForm.getMajorName());
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("username",entryForm.getUserName());
            User user = userMapper.selectOne(queryWrapper);
            resultEntryForm.setUserName(user.getName());
            Minor minor = minorMapper.selectById(entryForm.getMinorId());
            resultEntryForm.setMinorName(minor.getName());
            entryFormList.add(resultEntryForm);
        }
        return entryFormList;
    }

    private List<ApplyUserVo> convertResultApplyUserList(List<EntryForm> list){
        List<ApplyUserVo> userList = new ArrayList<>();
        QueryWrapper queryWrapper = new QueryWrapper();
        for (EntryForm entryForm : list) {
            ApplyUserVo resultApplyUser = new ApplyUserVo();
            queryWrapper.eq("username",entryForm.getUserName());
            User user = userMapper.selectOne(queryWrapper);
            resultApplyUser.setName(user.getName());
            resultApplyUser.setMajorName(entryForm.getMajorName());
            resultApplyUser.setInterestCourse(entryForm.getInterestCourse());
            resultApplyUser.setChecked(entryForm.getChecked());
            resultApplyUser.setAverageScore(entryForm.getAverageScore());
            queryWrapper.clear();
            queryWrapper.eq("major_name",entryForm.getMajorName());
            Major major = majorMapper.selectOne(queryWrapper);
            queryWrapper.clear();
            resultApplyUser.setMajorCourse(major.getMajorCourse());
            resultApplyUser.setCardId(entryForm.getCardId());
            Minor minor = minorMapper.selectById(entryForm.getMinorId());
            resultApplyUser.setMinorName(minor.getName());
            resultApplyUser.setId(entryForm.getEntryFormId());
            userList.add(resultApplyUser);
        }
        return userList;
    }
}

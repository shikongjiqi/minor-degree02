package edu.huc.controller;

import edu.huc.common.response.RespData;
import edu.huc.service.IChartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class ChartController {
    @Resource
    private IChartService chartService;

    /**
     * 生成饼状图
     * @param minorId
     * @return
     */
    @RequestMapping(value = "admin/pie",produces = "application/json;charset=utf-8")
    @ResponseBody
    public RespData pie(int minorId){
        RespData respData = chartService.statics(minorId);
        return respData;
    }

    /**
     * 生成柱状图
     * @param minorId
     * @return
     */
    @RequestMapping(value = "admin/histogram",produces = "application/json;charset=utf-8")
    @ResponseBody
    public RespData histogram(int minorId, String academyName){
        RespData respData = chartService.histogram(minorId,academyName);
        return respData;
    }
}

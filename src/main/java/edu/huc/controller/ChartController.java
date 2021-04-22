package edu.huc.controller;

import edu.huc.common.response.RespData;
import edu.huc.service.IChartService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequiresAuthentication
@RequestMapping("/admin")
public class ChartController {
    @Resource
    private IChartService chartService;

    /**
     * 生成饼状图
     *
     * @param minorId
     * @return
     */
    @GetMapping(value = "/pie", produces = "application/json;charset=utf-8")
    public RespData pie(int minorId) {
        RespData respData = chartService.statics(minorId);
        return respData;
    }

    /**
     * 生成柱状图
     *
     * @param minorId
     * @return
     */
    @PostMapping(value = "/histogram", produces = "application/json;charset=utf-8")
    public RespData histogram(@RequestParam Integer minorId, @RequestParam String academyName) {
        RespData respData = chartService.histogram(minorId, academyName);
        return respData;
    }
}

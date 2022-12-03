package cn.tycoding.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import cn.tycoding.admin.service.CommodityFeignClient;

import javax.annotation.Resource;

/**
 * @author tycoding
 * @date 2019-05-18
 */
@RestController
public class AdminController {

    @Resource
    private CommodityFeignClient commodityFeignClient;

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        return "hello " + name + ", this is template-admin";

    }


}

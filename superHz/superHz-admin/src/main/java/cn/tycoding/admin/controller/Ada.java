package cn.renxz.admin.controller;

import org.apache.commons.lang.ArrayUtils;

import java.util.Map;

public class Ada {
    public static void main(String[] args) {
        String[][] translation = { { "中国", "China" }, { "很高兴见到你", "Nice to meet you!" },   { "操", "Fuck" }, { "我爱你", "I Love You" }, { "再见", "See You!" } };
        Map countryCapitals = ArrayUtils.toMap(translation);
        System.out.println("很高兴见到你： " + countryCapitals.get("很高兴见到你"));
        System.out.println("再见: " + countryCapitals.get("再见"));
    }
}

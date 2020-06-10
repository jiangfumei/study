package com.java.study.designPattern.factory;

/**
 * 简单工厂模式
 */
public class SimpleRoujiaMoFactory {

    public RoujiaMo creatRoujiaMo(String type) {
        RoujiaMo roujiaMo = null;
        switch (type) {
            case "Suan":
                roujiaMo = new SuanRoujiaMo();
                break;
            case "La":
                roujiaMo = new LaRoujiaMo();
                break;
            case "Tian":
                roujiaMo = new TianRoujiaMo();
                break;
            default:// 默认为酸肉夹馍
                roujiaMo = new SuanRoujiaMo();
                break;
        }
        return roujiaMo;
    }
}

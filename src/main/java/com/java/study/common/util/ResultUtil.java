package com.java.study.util;

import com.xy.goone.common.vo.Result;

/**
 * @param <T>
 * @author fumei.jiang
 * @date 2019-07-26 12:58
 */
public class ResultUtil<T> {
    private Result<T> result;

    public ResultUtil() {
        result = new Result<>();
        result.setSuccess(true);
        result.setMessage("success");
        result.setCode(200);
    }

    public Result<T> setData(Object t) {
        this.result.setResult(t);
        this.result.setCode(200);
        return this.result;
    }

    public Result<T> setSuccessMsg(String msg) {
        this.result.setSuccess(true);
        this.result.setMessage(msg);
        this.result.setCode(200);
        this.result.setResult(null);
        return this.result;

    }

    public Result<T> setData(Object t, String msg) {
        this.result.setResult(t);
        this.result.setCode(200);
        this.result.setMessage(msg);
        return this.result;
    }

    public Result<T> setNoData(Object t){
        this.result.setResult(t);
        this.result.setCode(201);
        this.result.setMessage("No datas");
        return this.result;
    }

    public Result<T> setErrorMsg(String msg) {
        this.result.setSuccess(false);
        this.result.setMessage(msg);
        this.result.setCode(500);
        this.result.setResult(null);
        return this.result;
    }

    public Result<T> setErrorMsg(Integer code, String msg) {
        this.result.setSuccess(false);
        this.result.setMessage(msg);
        this.result.setCode(code);
        return this.result;
    }

    public static <T> Result<T> data(T t) {
        return new ResultUtil<T>().setData(t);
    }

    public static <T> Result<T> data(T t, String msg) {
        return new ResultUtil<T>().setData(t, msg);
    }

    public static <T> Result<T> success(String msg) {
        return new ResultUtil<T>().setSuccessMsg(msg);
    }

    public static <T> Result<T> error(String msg) {
        return new ResultUtil<T>().setErrorMsg(msg);
    }

    public static <T> Result<T> error(Integer code, String msg) {
        return new ResultUtil<T>().setErrorMsg(code, msg);
    }
}
package com.abo.news.Http;

import android.os.Handler;

import com.google.gson.internal.$Gson$Types;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by abo on 16/8/8.
 */
public class OkHttpUtils {

    private OkHttpClient mOkHttpClient;
    private Handler mDelivery;

    private OkHttpUtils(){
        mOkHttpClient = new OkHttpClient();
    }


    /**
     * Get请求
     * @param url
     * @param callback
     */
    private void getRequest(String url, final ResultCallback callback){
        final Request request = new Request.Builder().url(url).build();
        deliverResult(callback,request);
    }

    /**
     * Post请求,请求与回调都进行类封装。
     * @param url
     * @param callbakc
     * @param params
     */
    private void postRequest(String url, ResultCallback callbakc, List<Param> params){
        Request request = buildPostRequset(url,params);
        deliverResult(callbakc,request);
    }



    private void deliverResult(final ResultCallback callback, final Request request) {
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                sendFailCallback(callback,e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String str = request.body().toString();
                if (callback.mType == String.class){
                    sendSuccessCallback(callback, str);
                }else{
                    Object object = JsonUtils.deserialize(str, callback.mType);
                    sendSuccessCallback(callback,object);
                }
            }
        });

    }

    private void sendSuccessCallback(final ResultCallback callback, final Object obj) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null){
                    callback.onSuccess(obj);
                }
            }
        });
    }

    private void sendFailCallback(final ResultCallback callback, final IOException e) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null){
                    callback.onFailure(e);
                }
            }
        });
    }

    /**
     * Http Post 简单请求
     * @param url
     * @param params
     * @return
     */
    private Request buildPostRequset(String url, List<Param> params) {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        for(Param param : params){
            builder.add(param.key,param.values);
        }
        RequestBody requsetBody = builder.build();
        return new Request.Builder().url(url).post(requsetBody).build();
    }


    /**
     * @param <T>
     */
    public static abstract class ResultCallback<T>{
        Type mType;

        public ResultCallback(){
            mType = getSuperclassTypeParameter(getClass());
        }

        private Type getSuperclassTypeParameter(Class<?> subclass) {
            Type superclass = subclass.getGenericSuperclass();
            if (superclass instanceof Class) {
                throw new RuntimeException("Missing type parameter.");
            }
            ParameterizedType parameterized = (ParameterizedType) superclass;
            return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
        }

        /**
         * 请求回调成功
         * @param response
         */
        public abstract void onSuccess(T response);

        /**
         * 请求回调失败
         * @param e
         */
        public abstract void onFailure(Exception e);

    }

    /**
     * post请求参数
     */
    private class Param {
        String key;
        String values;

        public Param(){

        }

        public Param(String key,String values){
            this.key = key;
            this.values = values;

        }
    }

}

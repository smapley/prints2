package com.smapley.prints2.http.params;


import com.smapley.prints2.util.MyData;

import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;

/**
 * Created by smapley on 16/1/25.
 */
public class GetMingxiqParams extends RequestParams {

    public GetMingxiqParams(String zhang, String qishu) {
        super(MyData.getURL_getMingxiq());
        addBodyParameter("zhang", zhang);
        addBodyParameter("qihao", qishu);
        try {
            LogUtil.d(toJSONString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

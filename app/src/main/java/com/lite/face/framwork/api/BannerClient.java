package com.lite.face.framwork.api;

import com.easybenefit.commons.rest.ServiceCallback;
import com.easybenefit.commons.rest.annotations.Get;
import com.easybenefit.commons.rest.annotations.Interceptor;
import com.easybenefit.commons.rest.annotations.Interceptors;
import com.lite.face.framwork.api.Interceptor.HeaderInterceptor;
import com.lite.face.framwork.common.AppURL;

/**
 * Created by whoislcj on 2015/11/4 - 21:25.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description:
 */

@Interceptors({
        @Interceptor(HeaderInterceptor.class)
})
public interface BannerClient {

    //查询Banner列表接口
    @Get(AppURL.REST_API_URL + "/banner/list")
    void getBannerList(ServiceCallback callback);
}

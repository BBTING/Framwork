package com.lite.face.framwork.api;

import com.easybenefit.commons.rest.ServiceCallback;
import com.easybenefit.commons.rest.annotations.Interceptor;
import com.easybenefit.commons.rest.annotations.Interceptors;
import com.easybenefit.commons.rest.annotations.Param;
import com.easybenefit.commons.rest.annotations.Post;
import com.lite.face.framwork.api.Interceptor.HeaderInterceptor;
import com.lite.face.framwork.common.AppURL;

/**
 * Created by whoislcj on 2015/11/3 - 14:06.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description:
 */
@Interceptors({
        @Interceptor(HeaderInterceptor.class)
})
public interface CaptchaClient {

    //获取短信验证码
    @Post(AppURL.NORMAL_API_URL)
    void getCaptcha(@Param(name = "mobile") String mobile,
                    @Param(name = "scence") String scence,
                    @Param(name = "actionName") String action,
                    ServiceCallback callback);


}

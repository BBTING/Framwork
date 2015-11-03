package com.lite.face.framwork.inteface;

import com.easybenefit.commons.rest.ServiceCallback;
import com.easybenefit.commons.rest.annotations.Get;

import java.util.List;

/**
 * Created by whoislcj on 2015/11/3 - 14:06.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description:
 */
public interface UserService {

    @Get("www.baidu.com")
    List<String> get(String string, ServiceCallback callback, String value);
}

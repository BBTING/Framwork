package com.lite.face.framwork.inteface;

import com.lite.face.framwork.bean.ExtraBean;

/**
 * Created by whoislcj on 2015/10/28 - 9:26.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description:
 */
public interface RequestPlugin {

    ExtraBean request(ExtraBean extraBean);

    ExtraBean request(ExtraBean extraBean, RequestCallback requestCallback);

    boolean cancel(ExtraBean extraBean);
}

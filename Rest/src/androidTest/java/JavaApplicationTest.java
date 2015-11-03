import com.easybenefit.commons.rest.RestClient;
import com.easybenefit.commons.rest.impl.DynamicRestClientProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by whoislcj on 2015/11/3 - 13:12.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description:
 */
public class JavaApplicationTest {

    public static void main(String... args) {

        InvocationHandler invocationHandler = new DynamicRestClientProxy();
        RestClient restClient = (RestClient) Proxy.newProxyInstance(invocationHandler.getClass().getClassLoader(), invocationHandler.getClass().getInterfaces(), invocationHandler);
        restClient.doGet("url", "do something...");
    }
}

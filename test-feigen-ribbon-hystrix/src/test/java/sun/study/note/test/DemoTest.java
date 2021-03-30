package sun.study.note.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.study.note.TestApplication;

import java.net.URI;
import java.util.List;


/**
 * DemoServiceTest:
 *
 * @author: sunzhen
 * Date: 2021-03-30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class DemoTest {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Test
    public void test1() {
        List<ServiceInstance> monitorServiceInstances = discoveryClient.getInstances("monitor-server");
        String url = monitorServiceInstances.get(0).getUri().toString();
        System.err.println(url);
    }

    @Test
    public void test2() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("monitor-server");
        URI uri = serviceInstance.getUri();
        System.err.println(uri.toString());
    }
}

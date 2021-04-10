package scaudachuang.catlife.publisher.jdk_proxy;


import org.junit.jupiter.api.Test;
import scaudachuang.catlife.publisher.jdk_proxy.proxy.HelloWorld;
import scaudachuang.catlife.publisher.jdk_proxy.proxy.HelloWorldImpl;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestProxy {
    @Test
    public void testJdkProxy() {
        JdkProxyExample jdk = new JdkProxyExample();
        HelloWorld proxy = (HelloWorld) jdk.bind(new HelloWorldImpl());
        proxy.sayHello("john");
    }

    @Test
    public void classFileTest() {
        byte[] proxyBytes = ProxyGenerator.generateProxyClass("ProxyObject", new Class[]{HelloWorld.class});
        File file = new File("D:\\ProxyObject.class");
        try {
            FileOutputStream out = new FileOutputStream(file);
            out.write(proxyBytes);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

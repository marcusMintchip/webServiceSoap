package generator

import java.lang.reflect.Field
import java.lang.reflect.Method
import java.lang.reflect.Type

class UrlClassLoader {
    public static void main(String[] args) {
        URL url = new URL("file:/home/wangchenxu/testWsimport/out/hello.jar")
        URLClassLoader loader = new URLClassLoader(url)
        Class clazz = loader.loadClass("com.marcus.hello.HelloServiceImplService")
        Method method = clazz.getDeclaredMethod("getHelloServiceImplPort", null)
        Type type = method.getAnnotatedReturnType().getType()
        println type.getTypeName()
        clazz.newInstance().invokeMethod("sayHello","marcus")
        /*Object result = clazz.newInstance().invokeMethod("getHelloServiceImplPort",null)
        println result.getClass()
        println(result)*/
    }
}

package generator

class TestResolveJar {
    static void main(args) {
        URLClassEntity entity = new URLClassEntity("/home/wangchenxu/temp.jar")
        entity.resolveJar()
        Object obj = entity.callMethod("com.webservice.HelloServiceImplService", "getHelloServiceImplPort", null)
        println obj
    }
}

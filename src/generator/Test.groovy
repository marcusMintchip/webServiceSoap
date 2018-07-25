package generator

class Test {
    static String wsdlURL = "http://localhost:8080/webService/HelloService?wsdl"
    static String wsimportCommand = "wsimport -keep -s /home/wangchenxu/testWsimport/src -d /home/wangchenxu/testWsimport/out -p com.cn.weather -verbose http://localhost:8080/webService/HelloService?wsdl"
    static String wsdl2java = "wsimport -d {classesFolder} -p {package} {wsdlURL}"
    static String makeJarCommand = "jar cvf hello.jar com/marcus/hello"

    public static void main(String[] args) {
        File file = ProjectGenerator
                .generateJavaProject("/home/wangchenxu","testWsimport")

        CommandLineProcessor.executeCommand("wsimport -keep -s /home/wangchenxu/testWsimport/src -d /home/wangchenxu/testWsimport/out -p com.marcus.hello "+wsdlURL)
        CommandLineProcessor.executeCommand(makeJarCommand,new File(file,"out"))
    }
}

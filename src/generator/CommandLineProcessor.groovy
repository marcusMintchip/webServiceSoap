package generator

class CommandLineProcessor {
    static Process process
    static Runtime runtime = Runtime.getRuntime()
    static void executeCommand(String command){
        executeCommand(command,null)
    }
    static void executeCommand(String command,File workingDir){
        process = runtime.exec(command,null,workingDir)
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))
        while (reader.readLine()!=null){
            println reader.readLine()
        }
    }
}

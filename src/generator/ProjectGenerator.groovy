package generator

class ProjectGenerator {
    public static File generateJavaProject(String location,String projectName) {
        File file = new File(location,projectName);
        if(file.exists()){
            deleteFile(file)
            return createProjectStructure(file)?file:null
        }else {
            return createProjectStructure(file)?file:null
        }
    }

    private static boolean createProjectStructure(File file){
        String path = file.getPath()
        boolean isBuilt
        isBuilt = file.mkdir()
        File src = new File(path,"src")
        isBuilt = src.mkdir()
        File out = new File(path,"out")
        isBuilt = out.mkdir()
        return isBuilt
    }

    private static void deleteFile(File file){
        if(file.isFile()){
            file.delete()
        }else if(file.isDirectory()){
            File[] files = file.listFiles()
            for (File subFile:files){
                deleteFile(subFile)
            }
            file.delete()
        }
    }
}

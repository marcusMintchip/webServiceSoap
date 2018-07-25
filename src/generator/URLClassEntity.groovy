package generator

import java.lang.annotation.Annotation
import java.lang.reflect.Method
import java.util.jar.JarEntry
import java.util.jar.JarFile

class URLClassEntity {

    private String urlPath
    Set<Class<?>> classes = new LinkedHashSet<Class<?>>()//所有class对象
    Map<Class<?>,Annotation[]> classAnnotationMap = new HashMap<Class<?>,Annotation[]>()//每个class对象上的注解
    Map<Class<?>,Map<Method,Annotation[]>> classmethodAnnotaionMap = new HashMap<Class<?>,Map<Method,Annotation[]>>()//每个class对象中每个方法上的注解

    public URLClassEntity(String urlPath){
        this.urlPath = urlPath
        resolveJar()
    }

    Set<Class<?>> getClasses() {
        return classes
    }

    void setClasses(Set<Class<?>> classes) {
        this.classes = classes
    }

    Map<Class<?>, Annotation[]> getClassAnnotationMap() {
        return classAnnotationMap
    }

    void setClassAnnotationMap(Map<Class<?>, Annotation[]> classAnnotationMap) {
        this.classAnnotationMap = classAnnotationMap
    }

    Map<Class<?>, Map<Method, Annotation[]>> getClassmethodAnnotaionMap() {
        return classmethodAnnotaionMap
    }

    void setClassmethodAnnotaionMap(Map<Class<?>, Map<Method, Annotation[]>> classmethodAnnotaionMap) {
        this.classmethodAnnotaionMap = classmethodAnnotaionMap
    }

    void resolveJar(){
        JarFile jarFile = new JarFile(new File(urlPath))
        URL[] urls = [new URL("file:"+urlPath)] as URL[]
        ClassLoader classLoader = new URLClassLoader(urls)
        Enumeration<JarEntry> entrys = jarFile.entries()
        while (entrys.hasMoreElements()) {
            JarEntry entry = entrys.nextElement()
            String name = entry.getName()
            if(null!=name&&name.endsWith(".class")){
                Class<?> clazz = classLoader.loadClass(name.replace('/','.').substring(0,name.length()-6))
                println clazz
                println '****************************************************'
                classes.add(clazz)
                Annotation[] classAnnotations = clazz.getDeclaredAnnotations()
                classAnnotationMap.put(clazz,classAnnotations)
                Method[] methods = clazz.getDeclaredMethods()
                Map<Method,Annotation[]> methodAnnotationMap = new HashMap<Method,Annotation[]>()
                methods.each {
                    println it.getName()
                    Annotation[] methodAnnotations = it.getDeclaredAnnotations()
                    methodAnnotationMap.put(it,methodAnnotations)
                }
                    println '__________________________________________'
                classmethodAnnotaionMap.put(clazz,methodAnnotationMap)
            }
        }
    }

    Object callMethod(String className,String methodName,Object[] arguments){
        Object result
        for(Class clazz:classes){
            if(clazz.getName().equals(className)){
                result = clazz.newInstance().invokeMethod(methodName,arguments)
            }
        }
        return result
    }

}

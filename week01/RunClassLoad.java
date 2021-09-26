import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RunClassLoad extends ClassLoader{

    public static void main(String[] args) {
        try {
            //类加载
            Class<?> helloClass = new RunClassLoad().findClass("Hello");
            Method helloMethod = helloClass.getMethod("hello");
            helloMethod.invoke(helloClass.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    //自定义fundclass加载类
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        //文件路径
        Path path = Paths.get("/Users/admin/Desktop/demo/Hello.xlass");
        //读取文件中字节码文件
        byte[] byteContents = new byte[0];
        try {
            byteContents = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0 ; i < byteContents.length ; i++){
            byteContents[i] = (byte)(255 - byteContents[i]);
        }
        return defineClass(name, byteContents,0,byteContents.length);
    }
}


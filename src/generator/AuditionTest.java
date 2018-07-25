package generator;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class AuditionTest {

    public String convertInputStreamToString()throws Exception{
        InputStream inputStream = new FileInputStream(new File("/home/wangchenxu/text.txt"));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer))!=-1){
            bos.write(buffer,0,length);
        }
        return bos.toString();
    }

    public long convertByteToLong(byte[] bytes){
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        return buffer.getLong();
    }

    public static void main(String[] args) throws Exception{
        AuditionTest test = new AuditionTest();
        System.out.println(test.convertInputStreamToString());
    }
}

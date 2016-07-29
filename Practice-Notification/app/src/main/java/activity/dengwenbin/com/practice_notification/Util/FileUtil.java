package activity.dengwenbin.com.practice_notification.Util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by 40284 on 2016/7/29.
 */
public class FileUtil {

    public static void writeFile(String fileString,String filePath) throws IOException {
        InputStream is = new ByteArrayInputStream(fileString.getBytes());
        File file = new File(filePath);

        FileOutputStream fos = new FileOutputStream(file);
        byte[] bytes = new byte[2048];
        int len = 0;
        while ((len = is.read(bytes)) != -1) {
            fos.write(bytes, 0, len);
        }
        fos.flush();
        fos.close();
        is.close();
    }

    public static void isFileDir(String fileDir,String fileName) throws IOException {
        File fileDirs = new File(fileDir);
        if (!fileDirs.exists()) {
            fileDirs.mkdirs();
        }
        File filesName = new File(fileDir,fileName);
        if (!filesName.exists()) {
            filesName.createNewFile();
        }
    }


    public static String readFile(String fileDir, String fileName) {
        File file = new File(fileDir+"/"+fileName);
        String string = "";
        try {
            if(file.exists()){
                InputStream inputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                bufferedReader.close();
                inputStreamReader.close();
                string = stringBuilder.toString();
            }else{
                return string;
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return string;
    }
}

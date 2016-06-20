

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


public class File2xml {


        public static void main(String[] args) {
            test();
        }

        private static void test() {
            int startRowNumber = 0;
            int rowNumberSize = 1;
            String dirPath = "C:\\Users\\*****\\Desktop\\";
            String sourceFileName = "***************";

            for(int i=1;i<=30;i++){
                startRowNumber = i*rowNumberSize;
                splitFile(startRowNumber,rowNumberSize,dirPath,sourceFileName);
            }
        }


        public static void splitFile(int startRowNumber, int rowNumberSize,
                                     String dirPath, String sourceFileName) {

            File inputFile = new File(dirPath+sourceFileName);

            if(inputFile==null || !inputFile.exists()){
                throw new RuntimeException("File not exist, file path: "+inputFile.getAbsolutePath());
            }

            System.out.println("Start Split File  "+sourceFileName);

            String suffix = ".xml";
            String realFileName = sourceFileName;
            int index = sourceFileName.lastIndexOf(".");


            int serilizeNumber = 1;
            String outputPath = dirPath+realFileName+"_"+serilizeNumber + suffix;
            File outputFile = new File(outputPath);

            while(outputFile.exists()){

                serilizeNumber++;

                outputPath = dirPath+realFileName+"_"+serilizeNumber + suffix;
                outputFile = new File(outputPath);
            }

            System.out.println("The File After Split "+outputPath);

            int currentIndex = 0;
            int writeNumber = 0;

            InputStream in = null;
            InputStreamReader reader = null;
            BufferedReader br = null;

            OutputStream out = null;
            OutputStreamWriter writer = null;
            BufferedWriter bw = null;

            try {
                in = new FileInputStream(inputFile);
                reader = new InputStreamReader(in,"utf-8");
                br = new BufferedReader(reader);

                //写出
                out = new FileOutputStream(outputFile);
                writer = new OutputStreamWriter(out, "utf-8");
                bw = new BufferedWriter(writer);

                String line = null;
                String newLine = null;


                while((line=br.readLine())!=null){

                    currentIndex++;

                    if(writeNumber>=rowNumberSize){
                        break;
                    }

                    if(currentIndex>=startRowNumber){
                        newLine = line;

                        bw.write(newLine);
                        bw.newLine();

                        writeNumber++;
                    }
                }

                bw.flush();

                System.out.println("File Split is Complete...");

            }catch (Exception e) {
                e.printStackTrace();
            }finally{
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

}

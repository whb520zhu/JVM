import java.io.*;
import java.lang.reflect.Array;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IllegalFormatCodePointException;
import java.util.SplittableRandom;
import java.util.regex.Pattern;

public class Main {
     CharFile charFile=new CharFile();
    String[][] excel = new String[9][5];
    static int row=0;
    static int col=0;
    private char binaryNumber[]=new char[4];
    public void judegeArrayLength1(String[] AL) throws Exception {
        int length=0;
        length=AL.length;
        if (length==1){
//            System.out.println("ok_1");
        } else {
            throw new Exception("指令集有误，请修改");
        }
    }

    public void judegeArrayLength2(String[] AL) throws Exception {
        int length=0;
        length=AL.length;
        if (length==2){
//            System.out.println("ok_2");
        } else {
            throw new Exception("指令集有误，请修改");
        }
    }

    //  逐行读取文件
    public void readTxt(String filePath) throws Exception {
        File output=new File("/Users/whb/Downloads/testGrammer/output.txt");
        FileWriter fileWriter= new FileWriter(output);
        File file =new File(filePath);
        BufferedReader bufferedReader=new BufferedReader(
                new FileReader(file));
        String strLine=null;
        int lineCount=1;
        int pushRecord=0;
        int record;
        int arraysLengrh = 0;//用来判断数组的长度
        String judge []= null;
        int charat;

        while(null != (strLine = bufferedReader.readLine())){
            System.out.println("第[" + lineCount + "]行数据:" + strLine);
            judge=strLine.split(" ");
            fileWriter.write(charFile.search(judge));

            if (judge.length>1&&isNumeric(judge[1])){
                fileWriter.write("\n");
                fileWriter.write(toBinary(judge[1]));
                System.out.println("二进制数为：");
                System.out.println((toBinary(judge[1])));
            }
            switch (judge[0]){
                case "PUSH":
                    judegeArrayLength2(judge);//判断是否有参数
                    break;
                case "IFNE":
                    judegeArrayLength2(judge);
                    break;
                case "DUP":
                    judegeArrayLength1(judge);
                    break;
                case "SWAP":
                    judegeArrayLength1(judge);
                    break;
                case "POP":
                    judegeArrayLength1(judge);
                    break;
                case "ADD":
                    judegeArrayLength1(judge);
                    break;
                case "SUB":
                    judegeArrayLength1(judge);
                    break;
                case "MUL":
                    judegeArrayLength1(judge);
                    break;
                case "DIV":
                    judegeArrayLength1(judge);
                    break;
            }


            lineCount++;

            fileWriter.write("\n");
        }
        fileWriter.close();
    }

    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }


    public char[] toBinary(String a){
        int index=0;
        //二进制String
        String result=Integer.toBinaryString(Integer.valueOf(a));
//        System.out.println("resukt"+result);
        //二进制个数
        int resultLength=result.length();
      //  int start=binaryNumber.length-resultLength;

        switch (resultLength){
            case 0:
                System.out.println("error");
                break;
            case 1:
                for (int i=0;i<3;i++){
                    binaryNumber[i]='0';
                }
                binaryNumber[3]=result.charAt(0);
                break;
            case 2:
                for (int i=0;i<2;i++){
                    binaryNumber[i]='0';
                }
                binaryNumber[2]=result.charAt(0);
                binaryNumber[3]=result.charAt(1);
                break;
            case 3:
                for (int i=0;i<1;i++){
                    binaryNumber[i]='0';
                }
                binaryNumber[1]=result.charAt(0);
                binaryNumber[2]=result.charAt(1);
                binaryNumber[3]=result.charAt(2);
                break;
            case 4:
                for (int i=0;i<4;i++){
                    binaryNumber[i]=result.charAt(i);
                }
                break;
        }
//        System.out.println(binaryNumber);
        return binaryNumber;
    }

    public static void main(String[] args) throws Exception {
        Main main=new Main();
        String filePath="/Users/whb/Downloads/testGrammer/test.txt";
        String ouputFilePath="/Users/whb/Downloads/testGrammer/output.txt";
        main.readTxt(filePath);
        Load load=new Load();
        load.readOutput(ouputFilePath);
        load.engine();
    }
}

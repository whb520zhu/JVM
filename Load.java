import com.whb.tool.ArrayTool;
import org.junit.Test;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Load {
    CharFile charFile=new CharFile();
    List<String> methodArea=new ArrayList<>();
    int i=0;
    int index=0;//角标
    StackCreator stackCreator=new StackCreator();
    ArrayTool arrayTool=new ArrayTool();
    public List<String> readOutput(String outputFilePath) throws IOException {
        String[] methodFinal=new String[100];
//        String[] methodArea=null;
        int a=0;
        int methodIndex = 0;//用作方法去的角标
        File  file=new File(outputFilePath);
        BufferedReader bufferedReader=new BufferedReader(
                new FileReader(file));
        String strLine=null;
        int lineCount=1;
        while(null != (strLine = bufferedReader.readLine())) {
            System.out.println(charFile.search(strLine));
            methodFinal[methodIndex]=charFile.search(strLine);
            methodIndex++;
            lineCount++;
        }
        System.out.println("methodFinal="+ Arrays.toString(methodFinal));
        methodArea=arrayTool.removeNull(methodFinal);
        System.out.println("取出NULL"+ methodArea);
        return methodArea;
    }


    public void engine() throws IOException {
        List<String> methodArea=new ArrayList<>();
        String ouputFilePath="/Users/whb/Downloads/testGrammer/output.txt";
        methodArea=readOutput(ouputFilePath);
        System.out.println("下面为引擎");
        for (index=0;index<methodArea.size();index++){
            switch (methodArea.get(index)){
                case "PUSH":
                    System.out.println("index的值为："+index);
//                    System.out.println(Integer.parseInt((methodArea[index+1]),2));
                    stackCreator.push(Integer.parseInt((methodArea.get(index+1)),2));
                    break;
                case "ADD":
                    System.out.println("ADD操作：");
                    System.out.println("index的值为："+index);
                    stackCreator.add(stackCreator.stack);
                    break;
                case "DUP":
                    System.out.println("DUP操作：");
                    System.out.println("index的值为："+index);
                    stackCreator.dup(stackCreator.stack);
                    break;
                case "SUB":
                    System.out.println("index的值为："+index);
                    stackCreator.sub(stackCreator.stack);
                    break;
                case "IFNE":
                    System.out.println("INFE操作");
                    index=stackCreator.ifne(stackCreator.stack,index,Integer.valueOf(methodArea.get(index+1)));
                    methodArea.get(index);
                    break;
                case "HALT":
                    System.out.println("halt");
                    System.out.println("退出程序");
                    break;
                case "NULL":break;

                default:
                    break;
            }
        }
    }

    public String judgeZero(String a){
        int flag=0;
            if (a.charAt(0)=='0'){
                if (a.charAt(1)=='0'){
                    if (a.charAt(2)=='0'){
                        if (a.charAt(3)=='0'){
                            return "0";
                        }
                        return a.substring(3,4);
                    }
                    return a.substring(2,4);
                }
                return a.substring(1,4);

            }
        return "result";
    }
}




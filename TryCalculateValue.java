package TryCalculate;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
 
import edu.mit.jwi.*;
import edu.mit.jwi.item.*;


 
 
public class TryCalculateValue {
 
    public static void main(String[] args){

    	
    	
    	//=====================以前：如果加更多数据的话===========================
//    	GetWords getwords = new GetWords();
//    	GetWords2 getwords2 = new GetWords2();
//    	List<String> WordsList1 = getwords.GetWordList();
//    	List<String> WordsList2 = getwords2.GetWordList2();
//    	//处理末尾有一个null问题
//    	WordsList1.remove(WordsList1.size()-1);
//    	WordsList1.addAll(WordsList2);
//    	//再去一次重复
//    	for(int i = 0;i < WordsList1.size()-1;i++){       
//		    for(int j = WordsList1.size()-1;j > i;j--){   
//		       if(WordsList1.get(j).equals(WordsList1.get(i))){       
//		          WordsList1.remove(j);
//		       }        
//		    }        
//		 }
//         System.out.println("去掉重复词后："+WordsList1.size());
//         
//    	List<String> WordsList = WordsList1;
    	//================================================================
    	
    	
    	//取数据
    	//GetWords getwords = new GetWords();
    	//List<String> WordsList = getwords.GetWordList(1);
    	
    	

    	

    	
    	//开始循环
    	for(int i=1;i<=10;i++){
    		
    		List<String> WordsList = new ArrayList<String>();
    		if(i>=1 && i<=5){
    			GetWords getwords = new GetWords();
    	    	WordsList = getwords.GetWordList(i);
    		}
    		else if(i>=6 && i<=10){
    			GetWords2 getwords2 = new GetWords2();
    	    	WordsList = getwords2.GetWordList2(i);
    		}
    		else{   }
    		
    		
    		for(int j=0;j<14;j++){

    		//定义14个数据
    		List<String> StringList = new ArrayList<String>();
    		StringList.add("age");
    		StringList.add("race");
    		StringList.add("party");
    		StringList.add("income");
    		StringList.add("debt");
    		StringList.add("property");
    		StringList.add("position");
    		StringList.add("address");
    		StringList.add("occupation");
    		StringList.add("salary");
    		StringList.add("education");
    		StringList.add("marriage");
    		StringList.add("hobby");
    		StringList.add("health");
    		
    	
    	
    	double PrivacyValue = 0.0;
    	
    	long StartTime = System.currentTimeMillis();
    	TryAlgorithm Algorithm = new TryAlgorithm();
        PrivacyValue = Algorithm.TryRelationAlgorithm(WordsList,StringList.get(j));
    	long EndTime = System.currentTimeMillis();
    	
    	//TryAlgorithm_Shen Algorithm0 = new TryAlgorithm_Shen(); 
        //PrivacyValue = Algorithm0.TryRelationAlgorithm_Shen(WordsList);
    	
    	
    	System.out.print("最终返回的泄露值："+PrivacyValue+System.getProperty("line.separator")); 
    	
    	Method method = new Method(); 
    	//写txt文件
		method.WriteFile("D://CalculateTest//CalculateValue.txt", "总泄露值："+PrivacyValue+System.getProperty("line.separator"));
		long TotalTime = EndTime - StartTime;
		method.WriteFile("D://CalculateTest//CalculateValue.txt", "所用时间："+TotalTime+System.getProperty("line.separator"));
		method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator"));
		method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator"));
		
		
    	    }
    	}
    	
    	
    	
    	
    	
    	
    	
    	
    }

 
    
    
}
package TryCalculate;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
 
import edu.mit.jwi.*;
import edu.mit.jwi.item.*;


 
 
public class TryAlgorithm {
	 
	
	
	
	public double TryRelationAlgorithm(List<String> WordsList,String privacyword) {
		
		//总的泄露值
		double PrivacyValue = 0.0;
		//记录是否重复的List
		List<Node> PrivacyNode = new ArrayList<Node>();
		//记录是否发散过的List
		List<Node> HasCalcuNode = new ArrayList<Node>();
		
		
		String word = null;
		//接纳这个词外延词的list
		List<ISynset> RelatedWords = new ArrayList<ISynset>();
		//准备
		Method method = new Method();
		String wnhome = System.getenv("WNHOME"); //获取WordNet根目录环境变量WNHOME
        String path = "D:\\WordNet" + File.separator+ "dict";  
        File wnDir=new File(path);
        URL url = null;
		try {
			url = new URL("file", null, path);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		IDictionary dict=new Dictionary(url);
       try {
			dict.open();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//打开词典
        POS pos = POS.NOUN;
        
        //Node集合
        List<Node> NodeList = new ArrayList<Node>();
        //不知道为什么改了str以后突然需要从.size()变成.size()-1（List后面有一个null）
        for(int i=0;i<WordsList.size()-1;i++){
        	String NodeName = WordsList.get(i);
        	Node node = new Node();
        	node.setNodeName(NodeName);
        	node.setNodeValue(0.0);
        	IIndexWord idxWord1=dict.getIndexWord(WordsList.get(i), pos);
            IWordID wordID1 = idxWord1.getWordIDs().get(0); 
        	IWord word1 = dict.getWord(wordID1); //获取词
            ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
            node.setNodeSynset(synset1);
            node.setNodeFrom("None");
            node.setNodeFromWord("None");
            node.setNodeWordID(wordID1);
            node.setNodeHasCalculated(false);
            node.setNodeIsDisclo(false);
            NodeList.add(node);
        }
        System.out.print("NodeList："+NodeList+"\n");
        
        
        

        
        //开始循环
        for(int i=0;i<NodeList.size();i++){
        	
        	
        //==============为了实现循环后加的！==============
        	Node onenode = new Node();
        	onenode.setNodeName(privacyword);
        	onenode.setNodeValue(0.0);
        	IIndexWord oneidxWord1=dict.getIndexWord(privacyword, pos);
            IWordID onewordID1 = oneidxWord1.getWordIDs().get(0); 
        	IWord oneword1 = dict.getWord(onewordID1); //获取词
            ISynset onesynset1 = oneword1.getSynset(); //获取该词所在的Synset
            onenode.setNodeSynset(onesynset1);
            onenode.setNodeFrom("None");
            onenode.setNodeFromWord("None");
            onenode.setNodeWordID(onewordID1);
            onenode.setNodeHasCalculated(false);
            onenode.setNodeIsDisclo(false);
            NodeList.set(i, onenode);
        //==========================================	
        	
            
        word = privacyword;
        //写txt文件
		method.WriteFile("D://CalculateTest//CalculateValue.txt", "缺失词为："+word+System.getProperty("line.separator"));
       
		//为缺失词赋值1
        NodeList.get(i).setNodeValue(1.0);
        //总的需要计算的NodeList
        List<Node> AllNode_father= new ArrayList<Node>();
        List<Node> AllNode_child= new ArrayList<Node>();
        List<Node> AllNode_instancefather= new ArrayList<Node>();
        List<Node> AllNode_instancechild= new ArrayList<Node>();
        List<Node> AllNode_part1father= new ArrayList<Node>();
        List<Node> AllNode_part1child= new ArrayList<Node>();
        List<Node> AllNode_part2father= new ArrayList<Node>();
        List<Node> AllNode_part2child= new ArrayList<Node>();
        List<Node> AllNode_part3father= new ArrayList<Node>();
        List<Node> AllNode_part3child= new ArrayList<Node>();
        List<Node> AllNode_attributechild= new ArrayList<Node>();
        
        List<Node> AllNode= new ArrayList<Node>();
        //先添加好他的来源防止报错
        NodeList.get(i).setNodeFrom("RootNode");
        NodeList.get(i).setNodeHasCalculated(false);
        AllNode.add(NodeList.get(i));
        
        
//        //计算第一个词的父类总数以防套圈
//        int FatherNumber0 = 0;
//        List<ISynset> allfather = new ArrayList<ISynset>();
//        String str = AllNode.get(0).NodeName;
//		try {
//			allfather = method.getHypernyms(dict, str, pos);
//			FatherNumber0 = allfather.size();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        
        
        
        
        
        //定义一个循环的计数
    	int roundnumber = 0;
    	
    	
        //第二套循环 AllNode的循环
        while(!(AllNode.isEmpty())){
        	
	
  	
        for(int p=0;p<AllNode.size();p++){
        	
        	//取词
        	String thisword = AllNode.get(p).NodeName;
        	IWordID wordID = AllNode.get(p).getNodeWordID();
        	//判断是否已经发散过
        	boolean hasculcu = false;
       	    for(int q=0;q<HasCalcuNode.size();q++){
       		    if(thisword.equals(HasCalcuNode.get(q).NodeName)){
       		    	hasculcu = true;
       		    }
       		    else{    }
       	    }
       	    if(hasculcu){
       	    	continue;
       	    }
       	    else{    }
        	
          
		//开始寻找外延词
		//父类
		ISynset Father = method.getOneHypernyms(dict, wordID);
		//子类
		List<ISynset> Child = method.getOneHyponyms(dict, wordID);
		//Instance
		ISynset InstanceFather = method.getOneHyperInstance(dict, wordID);
		List<ISynset> InstanceChild = method.getOneHypoInstance(dict, wordID);
		//Part1
		ISynset Part1Father = method.getOneHyperPart_1(dict, wordID);
		List<ISynset> Part1Child = method.getOneHypoPart_1(dict, wordID);
		//Part2
		ISynset Part2Father = method.getOneHyperPart_2(dict, wordID);
		List<ISynset> Part2Child = method.getOneHypoPart_2(dict, wordID);
		//Part3
		ISynset Part3Father = method.getOneHyperPart_3(dict, wordID);
		List<ISynset> Part3Child = method.getOneHypoPart_3(dict, wordID);
		//Attribute
		List<ISynset> AttributeChild = method.getOneHypoAttribute(dict, wordID);
		//已经发散过了
        HasCalcuNode.add(AllNode.get(p));
		

		
		//判断空
		if(Father==null&&Child==null&&InstanceFather==null&&InstanceChild==null&&
				Part1Father==null&&Part1Child==null&&Part2Father==null&&Part2Child==null&&
						Part3Father==null&&Part3Child==null&&AttributeChild==null){   
			
			System.out.print(thisword+"没有可以找的关系！");
			break;
			
			
		}
		else{  
			
			
			
			//====================父类套装=======================
			//先判断来源
			if(!(AllNode.get(p).NodeFrom.equals("Child"))){
	
			if(!(Father==null)){		
			//计算数值并将这些节点添加进循环
			String father = null;	
			IWord iword = Father.getWord(1);
			father = iword.getLemma(); 
			//找IWordID
			IWordID wordid = Father.getWord(1).getID();
			//System.out.print(thisword+"的父类单词："+father+"\n");
			
			//计算值
			int HypoNumber = 0;
			HypoNumber = method.getHyponymsNumber(dict, wordid);
			if(HypoNumber == 0){
				HypoNumber = 1;
			}
			else{    }
			//为父类建立Node
			Node FatherNode = new Node();
			FatherNode.setNodeName(father);
			FatherNode.setNodeWordID(wordid);
			IIndexWord idxWord1=dict.getIndexWord(father, pos);
            IWordID wordID1 = idxWord1.getWordIDs().get(0); 
        	IWord word1 = dict.getWord(wordID1); //获取词
            ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
            FatherNode.setNodeSynset(synset1);
            //往上走1/n double nodevalue_father = AllNode.get(p).getNodeValue() * HypoNumber;
            double nodevalue_father = AllNode.get(p).getNodeValue() / HypoNumber;
            //父类赋值
            FatherNode.setNodeValue(nodevalue_father);
            //System.out.print(FatherNode.NodeName+"的值："+nodevalue_father+"\n");
            
            //加入List
            FatherNode.setNodeFrom("Father");
            FatherNode.setNodeFromWord(father);
            AllNode_father.add(FatherNode);
            //看看父类找没找到
            for(int m=0;m<NodeList.size();m++){
            	if(father.equals(NodeList.get(m).NodeName) && !(father.equals(NodeList.get(i).NodeName))){
            		NodeList.get(m).setNodeValue(FatherNode.NodeValue);
            		double PrivacyValue_father = FatherNode.NodeValue;
            		AllNode_father.remove(FatherNode);
            		PrivacyNode.add(FatherNode);
            		//System.out.print("找到了某词的父类是已知单词！ "+father+"的值："+PrivacyValue_father+"\n");
            		//写txt文件
            		//method.WriteFile("D://CalculateTest//CalculateValue.txt", father+"的泄露值："+PrivacyValue_father+System.getProperty("line.separator"));
            		method.WriteFile("D://CalculateTest//CalculateValue.txt", father+"   ");
            		//判断泄露值是否大于1
                    if(PrivacyValue_father>=1.0){
                    	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
                    	return PrivacyValue_father;
                    }
                    else{  
                    	
                    	//看有没有重复
                    	//xindexiao现在代表有重复
                		boolean xindexiao = false;
                		for(int n=0;n<PrivacyNode.size();n++){
                		   if(FatherNode.NodeName.equals(PrivacyNode.get(n).NodeName)){
//                			  if(PrivacyValue_father>PrivacyNode.get(n).NodeValue){
//                				  PrivacyValue = PrivacyValue - PrivacyNode.get(n).NodeValue;
//                				  PrivacyValue = PrivacyValue + PrivacyValue_father;
//                				  PrivacyNode.remove(PrivacyNode.get(n));
//                			  }
//                			  else{  
//                				  xindexiao = true;
//                			  }
                			   
                			   //改算法
                			   xindexiao = true;
                			   double oldvalue = PrivacyNode.get(n).NodeValue;
                			   double newvalue = PrivacyNode.get(n).NodeValue + PrivacyValue_father;
                			   if(newvalue>1.0){
                				   newvalue = 1.0;
                			   }
                			   else{   }
                			   PrivacyNode.get(n).setNodeValue(newvalue);
                			   PrivacyValue = PrivacyValue - oldvalue;
                			   PrivacyValue = PrivacyValue + newvalue;
                			   FatherNode.setNodeValue(newvalue);
                			   PrivacyNode.remove(PrivacyNode.get(n));
                			   
                			   
                			   
                		   }
                		   else{    }
                		}
                		if(xindexiao){  	}
                		else{ 
                			PrivacyValue = PrivacyValue + PrivacyValue_father;
                			PrivacyNode.add(FatherNode);
                		}
                		
                    }
            		break;
            	}
            	else{      }
            }
            
            //判断泄露值是否大于1
            if(PrivacyValue>=1.0){
            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
            	return PrivacyValue;
            }
            else{    }

            
			}
			else{   
				//System.out.print(AllNode.get(0).NodeName+"父类走不下去了！"); 
				//判断泄露值是否大于1
	            if(PrivacyValue>=1.0){
	            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
	            	return PrivacyValue;
	            }
	            else{      }
			}
			
			
			}
			else{    }
			//=========================父类套装结束============================
			
			
			
			
			//====================子类套装=======================
			//首先判断来源
			if(!(AllNode.get(p).NodeFrom.equals("Father"))){
				
				//判断是否重复计算这个点
				double yuanvalue = AllNode.get(p).NodeValue;
			
			if(!(null == Child || Child.size() ==0)){
						
			//计算数值并将这些节点添加进循环
			List<String> child = new ArrayList<String>();
			List<IWordID> wordid = new ArrayList<IWordID>();
			String simplechild = null;
			//转换成String的List
			for(int t=0;t<Child.size();t++){
				IWord iword = Child.get(t).getWord(1);
				IWordID wordid0 = iword.getID();
				simplechild = iword.getLemma();
				child.add(simplechild);
				wordid.add(wordid0);
			}
			
			//几个子类分别计算
			for(int n=0;n<child.size();n++){
		    
		    simplechild = child.get(n);
		    IWordID wordid0 = wordid.get(n);
			//System.out.print(thisword+"的一个子类单词："+simplechild+"\n");
			
			//计算值
			int HypoNumber = 0;
			HypoNumber = method.getHyponymsNumber(dict, AllNode.get(p).NodeWordID);

			//为子类建立Node
			Node ChildNode = new Node();
			ChildNode.setNodeName(simplechild);
			ChildNode.setNodeWordID(wordid0);
			IIndexWord idxWord1=dict.getIndexWord(simplechild, pos);
            IWordID wordID1 = idxWord1.getWordIDs().get(0); 
        	IWord word1 = dict.getWord(wordID1); //获取词
            ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
            ChildNode.setNodeSynset(synset1);
            //往下走100% double nodevalue_child = AllNode.get(p).getNodeValue() / HypoNumber;
            double nodevalue_child = AllNode.get(p).getNodeValue();
            //子类赋值
            ChildNode.setNodeValue(nodevalue_child);
            //System.out.print(ChildNode.NodeName+"的值："+nodevalue_child+"\n");
            
            //加入List
            ChildNode.setNodeFrom("Child");
            ChildNode.setNodeFromWord(simplechild);
            AllNode_child.add(ChildNode);
            //看看父类找没找到
            for(int m=0;m<NodeList.size();m++){
            	if(simplechild.equals(NodeList.get(m).NodeName) && !(simplechild.equals(NodeList.get(i).NodeName))){
            		NodeList.get(m).setNodeValue(ChildNode.NodeValue);
            		double PrivacyValue_child = ChildNode.NodeValue;
            		AllNode_child.remove(ChildNode);
                    PrivacyNode.add(ChildNode);
            		//System.out.print("找到了某词的子类是已知单词！ "+simplechild+"的值："+PrivacyValue_child+"\n");
            		//写txt文件
            		//method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"的泄露值："+PrivacyValue_child+System.getProperty("line.separator"));
                    method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"   ");
                    //判断泄露值是否大于1
                    if(PrivacyValue_child>=1.0){
                    	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
                    	return PrivacyValue_child;
                    }
                    else{  

                    	//看有没有重复
                		boolean xindexiao = false;
                		for(int t=0;t<PrivacyNode.size();t++){
                		   if(ChildNode.NodeName.equals(PrivacyNode.get(t).NodeName)){
//                			  if(PrivacyValue_child>PrivacyNode.get(t).NodeValue){
//                				  PrivacyValue = PrivacyValue - PrivacyNode.get(t).NodeValue;
//                				  PrivacyValue = PrivacyValue + PrivacyValue_child;
//                				  PrivacyNode.remove(PrivacyNode.get(t));
//                			  }
//                			  else{  
//                				  xindexiao = true;
//                			  }
                			   
                			 //改算法
                			   xindexiao = true;
                			   double oldvalue = PrivacyNode.get(t).NodeValue;
                			   double newvalue = PrivacyNode.get(t).NodeValue + PrivacyValue_child;
                			   if(newvalue>1.0){
                				   newvalue = 1.0;
                			   }
                			   else{   }
                			   PrivacyNode.get(t).setNodeValue(newvalue);
                			   PrivacyValue = PrivacyValue - oldvalue;
                			   PrivacyValue = PrivacyValue + newvalue;
                			   ChildNode.setNodeValue(newvalue);
                			   PrivacyNode.remove(PrivacyNode.get(t));
                			   
                			   
                			   
                		   }
                		   else{    }
                		}
                		if(xindexiao){  	}
                		else{ 
                			PrivacyValue = PrivacyValue + PrivacyValue_child;
                			PrivacyNode.add(ChildNode);
                		}
                    	
                    }
            		break;
            	}
            	else{      	}
            }
            
            //判断泄露值是否大于1
            if(PrivacyValue>=1.0){
            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
            	return PrivacyValue;
            }
            else{    }

            
            
            //循环几个子的结束
			}
			//判断Child为空的if
			}
			else{   
				//System.out.print(AllNode.get(0).NodeName+"子类走不下去了！"); 
				//判断泄露值是否大于1
	            if(PrivacyValue>=1.0){
	            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
	            	return PrivacyValue;
	            }
	            else{      }
			}
			
			
			}
			
			//如果是从父类来的，要跳过那个点
			else{  
				
				//判断是否重复计算这个点
				double yuanvalue = AllNode.get(p).NodeValue;
				
				if(!(null == Child || Child.size() ==0)){
					
					//要跳过的那个点
					String tiao = AllNode.get(p).NodeFromWord;
					
					//计算数值并将这些节点添加进循环
					List<String> child = new ArrayList<String>();
					List<IWordID> wordid = new ArrayList<IWordID>();
					String simplechild = null;
					//转换成String的List
					for(int t=0;t<Child.size();t++){
						IWord iword = Child.get(t).getWord(1);
						IWordID wordid0 = iword.getID();
						simplechild = iword.getLemma();
						if(!(simplechild.equals(tiao))){
						child.add(simplechild);
						wordid.add(wordid0);
						}
						else{    }
					}
					
					//几个子类分别计算
					for(int n=0;n<child.size();n++){
				    
				    simplechild = child.get(n);
				    IWordID wordid0 = wordid.get(n);
					//System.out.print(thisword+"的一个子类单词："+simplechild+"\n");
					
					//计算值
					int HypoNumber = 0;
					HypoNumber = method.getHyponymsNumber(dict, AllNode.get(p).NodeWordID);

					//为子类建立Node
					Node ChildNode = new Node();
					ChildNode.setNodeName(simplechild);
					ChildNode.setNodeWordID(wordid0);
					IIndexWord idxWord1=dict.getIndexWord(simplechild, pos);
		            IWordID wordID1 = idxWord1.getWordIDs().get(0); 
		        	IWord word1 = dict.getWord(wordID1); //获取词
		            ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
		            ChildNode.setNodeSynset(synset1);
		            //往下走100% double nodevalue_child = AllNode.get(p).getNodeValue() / HypoNumber;
		            double nodevalue_child = AllNode.get(p).getNodeValue();
		            //子类赋值
		            ChildNode.setNodeValue(nodevalue_child);
		            //System.out.print(ChildNode.NodeName+"的值："+nodevalue_child+"\n");
		            
		            //加入List
		            ChildNode.setNodeFrom("Child");
		            ChildNode.setNodeFromWord(simplechild);
		            AllNode_child.add(ChildNode);
		            //看看父类找没找到
		            for(int m=0;m<NodeList.size();m++){
		            	if(simplechild.equals(NodeList.get(m).NodeName) && !(simplechild.equals(NodeList.get(i).NodeName))){
		            		NodeList.get(m).setNodeValue(ChildNode.NodeValue);
		            		double PrivacyValue_child = ChildNode.NodeValue;
		            		AllNode_child.remove(ChildNode);
		            		PrivacyNode.add(ChildNode);
		            		//System.out.print("找到了某词的子类是已知单词！ "+simplechild+"的值："+PrivacyValue_child+"\n");
		            		//写txt文件
		            		//method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"的泄露值："+PrivacyValue_child+System.getProperty("line.separator"));
		            		method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"   ");
		            		//判断泄露值是否大于1
		                    if(PrivacyValue_child>=1.0){
		                    	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
		                    	return PrivacyValue_child;
		                    }
		                    else{  

		                    	//看有没有重复
		                		boolean xindexiao = false;
		                		for(int t=0;t<PrivacyNode.size();t++){
		                		   if(ChildNode.NodeName.equals(PrivacyNode.get(t).NodeName)){
//		                			  if(PrivacyValue_child>PrivacyNode.get(t).NodeValue){
//		                				  PrivacyValue = PrivacyValue - PrivacyNode.get(t).NodeValue;
//		                				  PrivacyValue = PrivacyValue + PrivacyValue_child;
//		                				  PrivacyNode.remove(PrivacyNode.get(t));
//		                			  }
//		                			  else{  
//		                				  xindexiao = true;
//		                			  }
		                			   
		                			 //改算法
		                			   xindexiao = true;
		                			   double oldvalue = PrivacyNode.get(t).NodeValue;
		                			   double newvalue = PrivacyNode.get(t).NodeValue + PrivacyValue_child;
		                			   if(newvalue>1.0){
		                				   newvalue = 1.0;
		                			   }
		                			   else{   }
		                			   PrivacyNode.get(t).setNodeValue(newvalue);
		                			   PrivacyValue = PrivacyValue - oldvalue;
		                			   PrivacyValue = PrivacyValue + newvalue;
		                			   ChildNode.setNodeValue(newvalue);
		                			   PrivacyNode.remove(PrivacyNode.get(t));
		                			   
		                			   
		                			   
		                		   }
		                		   else{    }
		                		}
		                		if(xindexiao){  	}
		                		else{ 
		                			PrivacyValue = PrivacyValue + PrivacyValue_child;
		                			PrivacyNode.add(ChildNode);
		                		}
		                    	
		                    }
		            		break;
		            	}
		            	else{      	}
		            }
		            
		            //判断泄露值是否大于1
		            if(PrivacyValue>=1.0){
		            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
		            	return PrivacyValue;
		            }
		            else{    }

		            
		            
		            //循环几个子的结束
					}
					//判断Child为空的if
					}
					else{   
						//System.out.print(AllNode.get(0).NodeName+"子类走不下去了！"); 
						//判断泄露值是否大于1
			            if(PrivacyValue>=1.0){
			            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
			            	return PrivacyValue;
			            }
			            else{      }
					}
				
			}
			//=========================子类套装结束============================
			
			
			
			
			
			//====================Instance父类套装=======================
			//先判断来源
			if(!(AllNode.get(p).NodeFrom.equals("InstanceChild"))){
			
				//判断是否重复计算这个点
				double yuanvalue = AllNode.get(p).NodeValue;
			
			if(!(InstanceFather==null)){
						
			//计算数值并将这些节点添加进循环
			String instancefather = null;	
			IWord iword = InstanceFather.getWord(1);
			instancefather = iword.getLemma();
			//找IWordID
			IWordID wordid = InstanceFather.getWord(1).getID();
			//System.out.print(thisword+"的Instance父类单词："+instancefather+"\n");
			
			//计算值
			int HypoNumber = 0;
			HypoNumber = method.getInstanceHyponymsNumber(dict, wordid);
			if(HypoNumber == 0){
				HypoNumber = 1;
			}
			else{    }
			//为父类建立Node
			Node InstanceFatherNode = new Node();
			InstanceFatherNode.setNodeName(instancefather);
			InstanceFatherNode.setNodeWordID(wordid);
			IIndexWord idxWord1=dict.getIndexWord(instancefather, pos);
            IWordID wordID1 = idxWord1.getWordIDs().get(0); 
        	IWord word1 = dict.getWord(wordID1); //获取词
            ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
            InstanceFatherNode.setNodeSynset(synset1);
            //往上走1/n double nodevalue_instancefather = AllNode.get(p).getNodeValue() * HypoNumber;
            double nodevalue_instancefather = AllNode.get(p).getNodeValue() / HypoNumber;
            //父类赋值
            InstanceFatherNode.setNodeValue(nodevalue_instancefather);
            //System.out.print(InstanceFatherNode.NodeName+"的值："+nodevalue_instancefather+"\n");
            
            //加入List
            InstanceFatherNode.setNodeFrom("InstanceFather");
            InstanceFatherNode.setNodeFromWord(instancefather);
            AllNode_instancefather.add(InstanceFatherNode);
            //看看父类找没找到
            for(int m=0;m<NodeList.size();m++){
            	if(instancefather.equals(NodeList.get(m).NodeName) && !(instancefather.equals(NodeList.get(i).NodeName))){
            		NodeList.get(m).setNodeValue(InstanceFatherNode.NodeValue);
            		double PrivacyValue_instancefather = InstanceFatherNode.NodeValue;
            		AllNode_instancefather.remove(InstanceFatherNode);
            		PrivacyNode.add(InstanceFatherNode);
            		//System.out.print("找到了某词的Instance父类是已知单词！ "+instancefather+"的值："+PrivacyValue_instancefather+"\n");
            		//写txt文件
            		//method.WriteFile("D://CalculateTest//CalculateValue.txt", instancefather+"的泄露值："+PrivacyValue_instancefather+System.getProperty("line.separator"));
            		method.WriteFile("D://CalculateTest//CalculateValue.txt", instancefather+"   ");
            		//判断泄露值是否大于1
                    if(PrivacyValue_instancefather>=1.0){
                    	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
                    	return PrivacyValue_instancefather;
                    }
                    else{  
                    	
                    	//看有没有重复
                		boolean xindexiao = false;
                		for(int t=0;t<PrivacyNode.size();t++){
//                		   if(InstanceFatherNode.NodeName.equals(PrivacyNode.get(t).NodeName)){
//                			  if(PrivacyValue_instancefather>PrivacyNode.get(t).NodeValue){
//                				  PrivacyValue = PrivacyValue - PrivacyNode.get(t).NodeValue;
//                				  PrivacyValue = PrivacyValue + PrivacyValue_instancefather;
//                				  PrivacyNode.remove(PrivacyNode.get(t));
//                			  }
//                			  else{  
//                				  xindexiao = true;
//                			  }
//                		   }
//                		   else{    }
                			
                			//改算法
             			   xindexiao = true;
             			   double oldvalue = PrivacyNode.get(t).NodeValue;
             			   double newvalue = PrivacyNode.get(t).NodeValue + PrivacyValue_instancefather;
             			   if(newvalue>1.0){
             				   newvalue = 1.0;
             			   }
             			   else{   }
             			   PrivacyNode.get(t).setNodeValue(newvalue);
             			   PrivacyValue = PrivacyValue - oldvalue;
             			   PrivacyValue = PrivacyValue + newvalue;
             			   InstanceFatherNode.setNodeValue(newvalue);
             			   PrivacyNode.remove(PrivacyNode.get(t));
                			
                			
                			
                		}
                		if(xindexiao){  	}
                		else{ 
                			PrivacyValue = PrivacyValue + PrivacyValue_instancefather;
                			PrivacyNode.add(InstanceFatherNode);
                		}
                		
                    }
            		break;
            	}
            	else{      	}
            }
            InstanceFatherNode.setNodeFrom("InstanceFather");
            InstanceFatherNode.setNodeFromWord(instancefather);
            AllNode_instancefather.add(InstanceFatherNode);
            //判断泄露值是否大于1
            if(PrivacyValue>=1.0){
            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
            	return PrivacyValue;
            }
            else{    }

            
			}
			else{   
				//System.out.print(AllNode.get(0).NodeName+"的Instance父类走不下去了！"); 
				//判断泄露值是否大于1
	            if(PrivacyValue>=1.0){
	            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
	            	return PrivacyValue;
	            }
	            else{      }
			}
			
			
			}
			else{    }
			//=========================Instance父类套装结束============================
			
			
			
			
			//====================Instance子类套装=======================
			//先判断来源
			if(!(AllNode.get(p).NodeFrom.equals("InstanceFather"))){
				
				//判断是否重复计算这个点
				double yuanvalue = AllNode.get(p).NodeValue;
			
			if(!(null == InstanceChild || InstanceChild.size() == 0)){
						
			//计算数值并将这些节点添加进循环
			List<String> instancechild = new ArrayList<String>();
			List<IWordID> wordid = new ArrayList<IWordID>();
			String simplechild = null;
			//转换成String的List
			for(int t=0;t<InstanceChild.size();t++){
				IWord iword = InstanceChild.get(t).getWord(1);
				IWordID wordid0 = iword.getID();
				simplechild = iword.getLemma();
				instancechild.add(simplechild);
				wordid.add(wordid0);
			}
			
			//几个子类分别计算
			for(int n=0;n<instancechild.size();n++){
		    
		    simplechild = instancechild.get(n);
		    IWordID wordid0 = wordid.get(n);
			//System.out.print(thisword+"的一个子类单词："+simplechild+"\n");
			
			//计算值
			int HypoNumber = 0;
			HypoNumber = method.getInstanceHyponymsNumber(dict, AllNode.get(p).NodeWordID);

			//为子类建立Node
			Node InstanceChildNode = new Node();
			InstanceChildNode.setNodeName(simplechild);
			InstanceChildNode.setNodeWordID(wordid0);
			IIndexWord idxWord1=dict.getIndexWord(simplechild, pos);
            IWordID wordID1 = idxWord1.getWordIDs().get(0); 
        	IWord word1 = dict.getWord(wordID1); //获取词
            ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
            InstanceChildNode.setNodeSynset(synset1);
            //往下走100% double nodevalue_instancechild = AllNode.get(p).getNodeValue() / HypoNumber;
            double nodevalue_instancechild = AllNode.get(p).getNodeValue();
            //子类赋值
            InstanceChildNode.setNodeValue(nodevalue_instancechild);
            //System.out.print(InstanceChildNode.NodeName+"的值："+nodevalue_instancechild+"\n");
            
            //加入List
            InstanceChildNode.setNodeFrom("InstanceChild");
            InstanceChildNode.setNodeFromWord(simplechild);
            AllNode_instancechild.add(InstanceChildNode);
            //看看父类找没找到
            for(int m=0;m<NodeList.size();m++){
            	if(simplechild.equals(NodeList.get(m).NodeName) && !(simplechild.equals(NodeList.get(i).NodeName))){
            		NodeList.get(m).setNodeValue(InstanceChildNode.NodeValue);
            		double PrivacyValue_instancechild = InstanceChildNode.NodeValue;
            		AllNode_instancechild.remove(InstanceChildNode);
            		PrivacyNode.add(InstanceChildNode);
            		//System.out.print("找到了某词的instance子类是已知单词！ "+simplechild+"的值："+PrivacyValue_instancechild+"\n");
            		//写txt文件
            		//method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"的泄露值："+PrivacyValue_instancechild+System.getProperty("line.separator"));
            		method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"   ");
            		//判断泄露值是否大于1
                    if(PrivacyValue_instancechild>=1.0){
                    	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
                    	return PrivacyValue_instancechild;
                    }
                    else{  
                    	
                    	//看有没有重复
                		boolean xindexiao = false;
                		for(int t=0;t<PrivacyNode.size();t++){
                		   if(InstanceChildNode.NodeName.equals(PrivacyNode.get(t).NodeName)){
//                			  if(PrivacyValue_instancechild>PrivacyNode.get(t).NodeValue){
//                				  PrivacyValue = PrivacyValue - PrivacyNode.get(t).NodeValue;
//                				  PrivacyValue = PrivacyValue + PrivacyValue_instancechild;
//                				  PrivacyNode.remove(PrivacyNode.get(t));
//                			  }
//                			  else{  
//                				  xindexiao = true;
//                			  }
                			   
                			 //改算法
                 			   xindexiao = true;
                 			   double oldvalue = PrivacyNode.get(t).NodeValue;
                 			   double newvalue = PrivacyNode.get(t).NodeValue + PrivacyValue_instancechild;
                 			   if(newvalue>1.0){
                 				   newvalue = 1.0;
                 			   }
                 			   else{   }
                 			   PrivacyNode.get(t).setNodeValue(newvalue);
                 			   PrivacyValue = PrivacyValue - oldvalue;
                 			   PrivacyValue = PrivacyValue + newvalue;
                 			   InstanceChildNode.setNodeValue(newvalue);
                 			   PrivacyNode.remove(PrivacyNode.get(t));
                			   
                			   
                		   }
                		   else{    }
                		}
                		if(xindexiao){  	}
                		else{ 
                			PrivacyValue = PrivacyValue + PrivacyValue_instancechild;
                			PrivacyNode.add(InstanceChildNode);
                		}
                		
                    }
            		break;
            	}
            	else{      	}
            }
            
            //判断泄露值是否大于1
            if(PrivacyValue>=1.0){
            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
            	return PrivacyValue;
            }
            else{    }

            
            
            //循环几个子的结束
			}
			//判断Child为空的if
			}
			else{   
				//System.out.print(AllNode.get(0).NodeName+"的Instance子类走不下去了！"); 
				//判断泄露值是否大于1
	            if(PrivacyValue>=1.0){
	            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
	            	return PrivacyValue;
	            }
	            else{      }
			}
			
			
			}
			
			//如果是找父类找来的，要跳过那个点
			else{
				
				//判断是否重复计算这个点
				double yuanvalue = AllNode.get(p).NodeValue;
				
				if(!(null == InstanceChild || InstanceChild.size() == 0)){
					
					//要跳过的那个点
					String tiao = AllNode.get(p).NodeFromWord;
					
					//计算数值并将这些节点添加进循环
					List<String> instancechild = new ArrayList<String>();
					List<IWordID> wordid = new ArrayList<IWordID>();
					String simplechild = null;
					//转换成String的List
					for(int t=0;t<InstanceChild.size();t++){
						IWord iword = InstanceChild.get(t).getWord(1);
						IWordID wordid0 = iword.getID();
						simplechild = iword.getLemma();
						if(!(simplechild.equals(tiao))){
						instancechild.add(simplechild);
						wordid.add(wordid0);
						}
						else{    }
					}
					
					//几个子类分别计算
					for(int n=0;n<instancechild.size();n++){
				    
				    simplechild = instancechild.get(n);
				    IWordID wordid0 = wordid.get(n);
					//System.out.print(thisword+"的一个子类单词："+simplechild+"\n");
					
					//计算值
					int HypoNumber = 0;
					HypoNumber = method.getInstanceHyponymsNumber(dict, AllNode.get(p).NodeWordID);

					//为子类建立Node
					Node InstanceChildNode = new Node();
					InstanceChildNode.setNodeName(simplechild);
					InstanceChildNode.setNodeWordID(wordid0);
					IIndexWord idxWord1=dict.getIndexWord(simplechild, pos);
		            IWordID wordID1 = idxWord1.getWordIDs().get(0); 
		        	IWord word1 = dict.getWord(wordID1); //获取词
		            ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
		            InstanceChildNode.setNodeSynset(synset1);
		            //往下走100% double nodevalue_instancechild = AllNode.get(p).getNodeValue() / HypoNumber;
		            double nodevalue_instancechild = AllNode.get(p).getNodeValue();
		            //子类赋值
		            InstanceChildNode.setNodeValue(nodevalue_instancechild);
		            //System.out.print(InstanceChildNode.NodeName+"的值："+nodevalue_instancechild+"\n");
		            
		            //加入List
		            InstanceChildNode.setNodeFrom("InstanceChild");
		            InstanceChildNode.setNodeFromWord(simplechild);
		            AllNode_instancechild.add(InstanceChildNode);
		            //看看父类找没找到
		            for(int m=0;m<NodeList.size();m++){
		            	if(simplechild.equals(NodeList.get(m).NodeName) && !(simplechild.equals(NodeList.get(i).NodeName))){
		            		NodeList.get(m).setNodeValue(InstanceChildNode.NodeValue);
		            		double PrivacyValue_instancechild = InstanceChildNode.NodeValue;
		            		AllNode_instancechild.remove(InstanceChildNode);
		            		PrivacyNode.add(InstanceChildNode);
		            		//System.out.print("找到了某词的instance子类是已知单词！ "+simplechild+"的值："+PrivacyValue_instancechild+"\n");
		            		//写txt文件
		            		//method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"的泄露值："+PrivacyValue_instancechild+System.getProperty("line.separator"));
		            		method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"   ");
		            		//判断泄露值是否大于1
		                    if(PrivacyValue_instancechild>=1.0){
		                    	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
		                    	return PrivacyValue_instancechild;
		                    }
		                    else{  
		                    	
		                    	//看有没有重复
		                		boolean xindexiao = false;
		                		for(int t=0;t<PrivacyNode.size();t++){
		                		   if(InstanceChildNode.NodeName.equals(PrivacyNode.get(t).NodeName)){
//		                			  if(PrivacyValue_instancechild>PrivacyNode.get(t).NodeValue){
//		                				  PrivacyValue = PrivacyValue - PrivacyNode.get(t).NodeValue;
//		                				  PrivacyValue = PrivacyValue + PrivacyValue_instancechild;
//		                				  PrivacyNode.remove(PrivacyNode.get(t));
//		                			  }
//		                			  else{  
//		                				  xindexiao = true;
//		                			  }
		                			   
		                			   //改算法
		                 			   xindexiao = true;
		                 			   double oldvalue = PrivacyNode.get(t).NodeValue;
		                 			   double newvalue = PrivacyNode.get(t).NodeValue + PrivacyValue_instancechild;
		                 			   if(newvalue>1.0){
		                 				   newvalue = 1.0;
		                 			   }
		                 			   else{   }
		                 			   PrivacyNode.get(t).setNodeValue(newvalue);
		                 			   PrivacyValue = PrivacyValue - oldvalue;
		                 			   PrivacyValue = PrivacyValue + newvalue;
		                 			   InstanceChildNode.setNodeValue(newvalue);
		                 			   PrivacyNode.remove(PrivacyNode.get(t));
		                			   
		                			   
		                		   }
		                		   else{    }
		                		}
		                		if(xindexiao){  	}
		                		else{ 
		                			PrivacyValue = PrivacyValue + PrivacyValue_instancechild;
		                			PrivacyNode.add(InstanceChildNode);
		                		}
		                		
		                    }
		            		break;
		            	}
		            	else{      	}
		            }
		            
		            //判断泄露值是否大于1
		            if(PrivacyValue>=1.0){
		            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
		            	return PrivacyValue;
		            }
		            else{    }

		            
		            
		            //循环几个子的结束
					}
					//判断Child为空的if
					}
					else{   
						//System.out.print(AllNode.get(0).NodeName+"的Instance子类走不下去了！"); 
						//判断泄露值是否大于1
			            if(PrivacyValue>=1.0){
			            	return PrivacyValue;
			            }
			            else{      }
					}
				
			}
			//=========================Instance子类套装结束============================
			
			
			
			
			
			
			//====================Part1父类套装=======================
			//先判断来源
			if(!(AllNode.get(p).NodeFrom.equals("Part1Child"))){
				
				//判断是否重复计算这个点
				double yuanvalue = AllNode.get(p).NodeValue;
			
			 if(!(Part1Father==null)){
						
			//计算数值并将这些节点添加进循环
			String part1father = null;	
			IWord iword = Part1Father.getWord(1);
			part1father = iword.getLemma();
			//找IWordID
			IWordID wordid = Part1Father.getWord(1).getID();
			//System.out.print(thisword+"的part1父类单词："+part1father+"\n");
			
			//计算值
			int HypoNumber = 0;
			HypoNumber = method.getPart1HyponymsNumber(dict, wordid);
			if(HypoNumber == 0){
				HypoNumber = 1;
			}
			else{    }
			//为父类建立Node
			Node Part1FatherNode = new Node();
			Part1FatherNode.setNodeName(part1father);
			Part1FatherNode.setNodeWordID(wordid);
			IIndexWord idxWord1=dict.getIndexWord(part1father, pos);
            IWordID wordID1 = idxWord1.getWordIDs().get(0); 
        	IWord word1 = dict.getWord(wordID1); //获取词
            ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
            Part1FatherNode.setNodeSynset(synset1);
            //向上走100% double nodevalue_part1father = AllNode.get(p).getNodeValue() * HypoNumber;
            double nodevalue_part1father = AllNode.get(p).getNodeValue();
            //父类赋值
            Part1FatherNode.setNodeValue(nodevalue_part1father);
            //System.out.print(Part1FatherNode.NodeName+"的值："+nodevalue_part1father+"\n");
            //加入List
            Part1FatherNode.setNodeFrom("Part1Father");
            Part1FatherNode.setNodeFromWord(part1father);
            AllNode_part1father.add(Part1FatherNode);
            //看看父类找没找到
            for(int m=0;m<NodeList.size();m++){
            	if(part1father.equals(NodeList.get(m).NodeName) && !(part1father.equals(NodeList.get(i).NodeName))){
            		NodeList.get(m).setNodeValue(Part1FatherNode.NodeValue);
            		double PrivacyValue_part1father = Part1FatherNode.NodeValue;
            		AllNode_part1father.remove(Part1FatherNode);
            		PrivacyNode.add(Part1FatherNode);
            		//System.out.print("找到了某词的part1父类是已知单词！ "+part1father+"的值："+PrivacyValue_part1father+"\n");
            		//写txt文件
            		//method.WriteFile("D://CalculateTest//CalculateValue.txt", part1father+"的泄露值："+PrivacyValue_part1father+System.getProperty("line.separator"));
            		method.WriteFile("D://CalculateTest//CalculateValue.txt", part1father+"   ");
            		//判断泄露值是否大于1
                    if(PrivacyValue_part1father>=1.0){
                    	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
                    	return PrivacyValue_part1father;
                    }
                    else{  
                    	
                    	//看有没有重复
                		boolean xindexiao = false;
                		for(int t=0;t<PrivacyNode.size();t++){
                		   if(Part1FatherNode.NodeName.equals(PrivacyNode.get(t).NodeName)){
//                			  if(PrivacyValue_part1father>PrivacyNode.get(t).NodeValue){
//                				  PrivacyValue = PrivacyValue - PrivacyNode.get(t).NodeValue;
//                				  PrivacyValue = PrivacyValue + PrivacyValue_part1father;
//                				  PrivacyNode.remove(PrivacyNode.get(t));
//                			  }
//                			  else{  
//                				  xindexiao = true;
//                			  }
                			   
                			   //改算法
                 			   xindexiao = true;
                 			   double oldvalue = PrivacyNode.get(t).NodeValue;
                 			   double newvalue = PrivacyNode.get(t).NodeValue + PrivacyValue_part1father;
                 			   if(newvalue>1.0){
                 				   newvalue = 1.0;
                 			   }
                 			   else{   }
                 			   PrivacyNode.get(t).setNodeValue(newvalue);
                 			   PrivacyValue = PrivacyValue - oldvalue;
                 			   PrivacyValue = PrivacyValue + newvalue;
                 			   Part1FatherNode.setNodeValue(newvalue);
                 			   PrivacyNode.remove(PrivacyNode.get(t));
                			   
                			   
                		   }
                		   else{    }
                		}
                		if(xindexiao){  	}
                		else{ 
                			PrivacyValue = PrivacyValue + PrivacyValue_part1father;
                			PrivacyNode.add(Part1FatherNode);
                		}
                		
                    }
            		break;
            	}
            	else{      	}
            }
            
            //判断泄露值是否大于1
            if(PrivacyValue>=1.0){
            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
            	return PrivacyValue;
            }
            else{    }

            
			}
			else{   
				//System.out.print(AllNode.get(0).NodeName+"的part1父类走不下去了！"); 
				//判断泄露值是否大于1
	            if(PrivacyValue>=1.0){
	            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
	            	return PrivacyValue;
	            }
	            else{      }
			}
			 
			}
			else{    }
			//=========================Part1父类套装结束============================
			
			
			
			
			
			//====================Part1子类套装=======================
			//先判断来源
			if(!(AllNode.get(p).NodeFrom.equals("Part1Father"))){
				
				//判断是否重复计算这个点
				double yuanvalue = AllNode.get(p).NodeValue;
			
			if(!(null == Part1Child || Part1Child.size() == 0)){
						
			//计算数值并将这些节点添加进循环
			List<String> part1child = new ArrayList<String>();
			List<IWordID> wordid = new ArrayList<IWordID>();
			String simplechild = null;
			//转换成String的List
			for(int t=0;t<Part1Child.size();t++){
				IWord iword = Part1Child.get(t).getWord(1);
				IWordID wordid0 = iword.getID();
				simplechild = iword.getLemma();
				part1child.add(simplechild);
				wordid.add(wordid0);
			}
			
			//几个子类分别计算
			for(int n=0;n<part1child.size();n++){
		    
		    simplechild = part1child.get(n);
		    IWordID wordid0 = wordid.get(n);
			//System.out.print(thisword+"的一个子类单词："+simplechild+"\n");
			
			//计算值
			int HypoNumber = 0;
			HypoNumber = method.getPart1HyponymsNumber(dict, AllNode.get(p).NodeWordID);

			//为子类建立Node
			Node Part1ChildNode = new Node();
			Part1ChildNode.setNodeName(simplechild);
			Part1ChildNode.setNodeWordID(wordid0);
			IIndexWord idxWord1=dict.getIndexWord(simplechild, pos);
            IWordID wordID1 = idxWord1.getWordIDs().get(0); 
        	IWord word1 = dict.getWord(wordID1); //获取词
            ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
            Part1ChildNode.setNodeSynset(synset1);
            //往下走1/n double nodevalue_part1child = AllNode.get(p).getNodeValue() / HypoNumber;
            double nodevalue_part1child = AllNode.get(p).getNodeValue() / HypoNumber;
            //子类赋值
            Part1ChildNode.setNodeValue(nodevalue_part1child);
            //System.out.print(Part1ChildNode.NodeName+"的值："+nodevalue_part1child+"\n");
            
            //加入List
            Part1ChildNode.setNodeFrom("Part1Child");
            Part1ChildNode.setNodeFromWord(simplechild);
            AllNode_part1child.add(Part1ChildNode);
            //看看子类找没找到
            for(int m=0;m<NodeList.size();m++){
            	if(simplechild.equals(NodeList.get(m).NodeName) && !(simplechild.equals(NodeList.get(i).NodeName))){
            		NodeList.get(m).setNodeValue(Part1ChildNode.NodeValue);
            		double PrivacyValue_part1child = Part1ChildNode.NodeValue;
            	    AllNode_part1child.remove(Part1ChildNode);
            		PrivacyNode.add(Part1ChildNode);
            		//System.out.print("找到了某词的part1子类是已知单词！ "+simplechild+"的值："+PrivacyValue_part1child+"\n");
            		//写txt文件
            		//method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"的泄露值："+PrivacyValue_part1child+System.getProperty("line.separator"));
            		method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"   ");
            		//判断泄露值是否大于1
                    if(PrivacyValue_part1child>=1.0){
                    	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
                    	return PrivacyValue_part1child;
                    }
                    else{  
                    	
                    	//看有没有重复
                		boolean xindexiao = false;
                		for(int t=0;t<PrivacyNode.size();t++){
                		   if(Part1ChildNode.NodeName.equals(PrivacyNode.get(t).NodeName)){
//                			  if(PrivacyValue_part1child>PrivacyNode.get(t).NodeValue){
//                				  PrivacyValue = PrivacyValue - PrivacyNode.get(t).NodeValue;
//                				  PrivacyValue = PrivacyValue + PrivacyValue_part1child;
//                				  PrivacyNode.remove(PrivacyNode.get(t));
//                			  }
//                			  else{  
//                				  xindexiao = true;
//                			  }
                			   
                			   //改算法
                 			   xindexiao = true;
                 			   double oldvalue = PrivacyNode.get(t).NodeValue;
                 			   double newvalue = PrivacyNode.get(t).NodeValue + PrivacyValue_part1child;
                 			   if(newvalue>1.0){
                 				   newvalue = 1.0;
                 			   }
                 			   else{   }
                 			   PrivacyNode.get(t).setNodeValue(newvalue);
                 			   PrivacyValue = PrivacyValue - oldvalue;
                 			   PrivacyValue = PrivacyValue + newvalue;
                 			   Part1ChildNode.setNodeValue(newvalue);
                 			   PrivacyNode.remove(PrivacyNode.get(t));
                			   
                			   
                		   }
                		   else{    }
                		}
                		if(xindexiao){  	}
                		else{ 
                			PrivacyValue = PrivacyValue + PrivacyValue_part1child;
                			PrivacyNode.add(Part1ChildNode);
                		}
                		
                    }
            		break;
            	}
            	else{      	}
            }
            
            //判断泄露值是否大于1
            if(PrivacyValue>=1.0){
            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
            	return PrivacyValue;
            }
            else{    }

            
            
            //循环几个子的结束
			}
			//判断Child为空的if
			}
			else{   
				//System.out.print(AllNode.get(0).NodeName+"的part1子类走不下去了！"); 
				//判断泄露值是否大于1
	            if(PrivacyValue>=1.0){
	            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
	            	return PrivacyValue;
	            }
	            else{      }
			}
			
			}
			
			//如果是找父类找来的，要跳过那个点
			else{
				
				//判断是否重复计算这个点
				double yuanvalue = AllNode.get(p).NodeValue;
				
				if(!(null == Part1Child || Part1Child.size() == 0)){
					
					//要跳过的那个点
					String tiao = AllNode.get(p).NodeFromWord;
					
					//计算数值并将这些节点添加进循环
					List<String> part1child = new ArrayList<String>();
					List<IWordID> wordid = new ArrayList<IWordID>();
					String simplechild = null;
					//转换成String的List
					for(int t=0;t<Part1Child.size();t++){
						IWord iword = Part1Child.get(t).getWord(1);
						IWordID wordid0 = iword.getID();
						simplechild = iword.getLemma();
						if(!(simplechild.equals(tiao))){
						part1child.add(simplechild);
						wordid.add(wordid0);
						}
						else{   }
					}
					
					//几个子类分别计算
					for(int n=0;n<part1child.size();n++){
				    
				    simplechild = part1child.get(n);
				    IWordID wordid0 = wordid.get(n);
					//System.out.print(thisword+"的一个子类单词："+simplechild+"\n");
					
					//计算值
					int HypoNumber = 0;
					HypoNumber = method.getPart1HyponymsNumber(dict, AllNode.get(p).NodeWordID);

					//为子类建立Node
					Node Part1ChildNode = new Node();
					Part1ChildNode.setNodeName(simplechild);
					Part1ChildNode.setNodeWordID(wordid0);
					IIndexWord idxWord1=dict.getIndexWord(simplechild, pos);
		            IWordID wordID1 = idxWord1.getWordIDs().get(0); 
		        	IWord word1 = dict.getWord(wordID1); //获取词
		            ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
		            Part1ChildNode.setNodeSynset(synset1);
		            //往下走1/n double nodevalue_part1child = AllNode.get(p).getNodeValue() / HypoNumber;
		            double nodevalue_part1child = AllNode.get(p).getNodeValue() / HypoNumber;
		            //子类赋值
		            Part1ChildNode.setNodeValue(nodevalue_part1child);
		            //System.out.print(Part1ChildNode.NodeName+"的值："+nodevalue_part1child+"\n");
		            
		            //加入List
		            Part1ChildNode.setNodeFrom("Part1Child");
		            Part1ChildNode.setNodeFromWord(simplechild);
		            AllNode_part1child.add(Part1ChildNode);
		            //看看子类找没找到
		            for(int m=0;m<NodeList.size();m++){
		            	if(simplechild.equals(NodeList.get(m).NodeName) && !(simplechild.equals(NodeList.get(i).NodeName))){
		            		NodeList.get(m).setNodeValue(Part1ChildNode.NodeValue);
		            		double PrivacyValue_part1child = Part1ChildNode.NodeValue;
		            		AllNode_part1child.remove(Part1ChildNode);
		            		PrivacyNode.add(Part1ChildNode);
		            		//System.out.print("找到了某词的part1子类是已知单词！ "+simplechild+"的值："+PrivacyValue_part1child+"\n");
		            		//写txt文件
		            		//method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"的泄露值："+PrivacyValue_part1child+System.getProperty("line.separator"));
		            		method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"   ");
		            		//判断泄露值是否大于1
		                    if(PrivacyValue_part1child>=1.0){
		                    	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
		                    	return PrivacyValue_part1child;
		                    }
		                    else{  
		                    	
		                    	//看有没有重复
		                		boolean xindexiao = false;
		                		for(int t=0;t<PrivacyNode.size();t++){
		                		   if(Part1ChildNode.NodeName.equals(PrivacyNode.get(t).NodeName)){
//		                			  if(PrivacyValue_part1child>PrivacyNode.get(t).NodeValue){
//		                				  PrivacyValue = PrivacyValue - PrivacyNode.get(t).NodeValue;
//		                				  PrivacyValue = PrivacyValue + PrivacyValue_part1child;
//		                				  PrivacyNode.remove(PrivacyNode.get(t));
//		                			  }
//		                			  else{  
//		                				  xindexiao = true;
//		                			  }
		                			   
		                			 //改算法
		                 			   xindexiao = true;
		                 			   double oldvalue = PrivacyNode.get(t).NodeValue;
		                 			   double newvalue = PrivacyNode.get(t).NodeValue + PrivacyValue_part1child;
		                 			   if(newvalue>1.0){
		                 				   newvalue = 1.0;
		                 			   }
		                 			   else{   }
		                 			   PrivacyNode.get(t).setNodeValue(newvalue);
		                 			   PrivacyValue = PrivacyValue - oldvalue;
		                 			   PrivacyValue = PrivacyValue + newvalue;
		                 			   Part1ChildNode.setNodeValue(newvalue);
		                 			   PrivacyNode.remove(PrivacyNode.get(t));
		                			   
		                			   
		                		   }
		                		   else{    }
		                		}
		                		if(xindexiao){  	}
		                		else{ 
		                			PrivacyValue = PrivacyValue + PrivacyValue_part1child;
		                			PrivacyNode.add(Part1ChildNode);
		                		}
		                		
		                    }
		            		break;
		            	}
		            	else{      	}
		            }
		            
		            //判断泄露值是否大于1
		            if(PrivacyValue>=1.0){
		            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
		            	return PrivacyValue;
		            }
		            else{    }

		            
		            
		            //循环几个子的结束
					}
					//判断Child为空的if
					}
					else{   
						//System.out.print(AllNode.get(0).NodeName+"的part1子类走不下去了！"); 
						//判断泄露值是否大于1
			            if(PrivacyValue>=1.0){
			            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
			            	return PrivacyValue;
			            }
			            else{      }
					}
				
			}
			//=========================part1子类套装结束============================
			
			
			
			
			
			//====================Part2父类套装=======================
			
			//先判断来源
			if(!(AllNode.get(p).NodeFrom.equals("Part2Child"))){
				
				//判断是否重复计算这个点
				double yuanvalue = AllNode.get(p).NodeValue;
			
			if(!(Part2Father==null)){
						
			//计算数值并将这些节点添加进循环
			String part2father = null;	
			IWord iword = Part2Father.getWord(1);
			part2father = iword.getLemma();
			//找IWordID
			IWordID wordid = Part2Father.getWord(1).getID();
			//System.out.print(thisword+"的part2父类单词："+part2father+"\n");
			
			//计算值
			int HypoNumber = 0;
			HypoNumber = method.getPart2HyponymsNumber(dict, wordid);
			if(HypoNumber == 0){
				HypoNumber = 1;
			}
			else{    }
			//为父类建立Node
			Node Part2FatherNode = new Node();
			Part2FatherNode.setNodeName(part2father);
			Part2FatherNode.setNodeWordID(wordid);
			IIndexWord idxWord1=dict.getIndexWord(part2father, pos);
            IWordID wordID1 = idxWord1.getWordIDs().get(0); 
        	IWord word1 = dict.getWord(wordID1); //获取词
            ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
            Part2FatherNode.setNodeSynset(synset1);
            //向上走100% double nodevalue_part2father = AllNode.get(p).getNodeValue() * HypoNumber;
            double nodevalue_part2father = AllNode.get(p).getNodeValue();
            //父类赋值
            Part2FatherNode.setNodeValue(nodevalue_part2father);
            //System.out.print(Part2FatherNode.NodeName+"的值："+nodevalue_part2father+"\n");
            
            //加入List
            Part2FatherNode.setNodeFrom("Part2Father");
            Part2FatherNode.setNodeFromWord(part2father);
            AllNode_part2father.add(Part2FatherNode);
            //看看父类找没找到
            for(int m=0;m<NodeList.size();m++){
            	if(part2father.equals(NodeList.get(m).NodeName) && !(part2father.equals(NodeList.get(i).NodeName))){
            		NodeList.get(m).setNodeValue(Part2FatherNode.NodeValue);
            		double PrivacyValue_part2father = Part2FatherNode.NodeValue;
            		AllNode_part2father.remove(Part2FatherNode);
            		PrivacyNode.add(Part2FatherNode);
            		//System.out.print("找到了某词的part2父类是已知单词！ "+part2father+"的值："+PrivacyValue_part2father+"\n");
            		//写txt文件
            		//method.WriteFile("D://CalculateTest//CalculateValue.txt", part2father+"的泄露值："+PrivacyValue_part2father+System.getProperty("line.separator"));
            		method.WriteFile("D://CalculateTest//CalculateValue.txt", part2father+"   ");
            		//判断泄露值是否大于1
                    if(PrivacyValue_part2father>=1.0){
                    	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
                    	return PrivacyValue_part2father;
                    }
                    else{  
                    	
                    	//看有没有重复
                		boolean xindexiao = false;
                		for(int t=0;t<PrivacyNode.size();t++){
                		   if(Part2FatherNode.NodeName.equals(PrivacyNode.get(t).NodeName)){
//                			  if(PrivacyValue_part2father>PrivacyNode.get(t).NodeValue){
//                				  PrivacyValue = PrivacyValue - PrivacyNode.get(t).NodeValue;
//                				  PrivacyValue = PrivacyValue + PrivacyValue_part2father;
//                				  PrivacyNode.remove(PrivacyNode.get(t));
//                			  }
//                			  else{  
//                				  xindexiao = true;
//                			  }
                			   
                			 //改算法
                 			   xindexiao = true;
                 			   double oldvalue = PrivacyNode.get(t).NodeValue;
                 			   double newvalue = PrivacyNode.get(t).NodeValue + PrivacyValue_part2father;
                 			   if(newvalue>1.0){
                 				   newvalue = 1.0;
                 			   }
                 			   else{   }
                 			   PrivacyNode.get(t).setNodeValue(newvalue);
                 			   PrivacyValue = PrivacyValue - oldvalue;
                 			   PrivacyValue = PrivacyValue + newvalue;
                 			   Part2FatherNode.setNodeValue(newvalue);
                 			   PrivacyNode.remove(PrivacyNode.get(t));
                			   
                			   
                		   }
                		   else{    }
                		}
                		if(xindexiao){  	}
                		else{ 
                			PrivacyValue = PrivacyValue + PrivacyValue_part2father;
                			PrivacyNode.add(Part2FatherNode);
                		}
                		
                    }
            		break;
            	}
            	else{      	}
            }
            
            //判断泄露值是否大于1
            if(PrivacyValue>=1.0){
            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
            	return PrivacyValue;
            }
            else{    }

            
			}
			else{   
				//System.out.print(AllNode.get(0).NodeName+"的part2父类走不下去了！"); 
				//判断泄露值是否大于1
	            if(PrivacyValue>=1.0){
	            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
	            	return PrivacyValue;
	            }
	            else{      }
			}
			
			}
			else{    }
			//=========================Part2父类套装结束============================
			
			
			
			
			
			//====================Part2子类套装=======================
			//先判断来源
			if(!(AllNode.get(p).NodeFrom.equals("Part2Father"))){
				
				//判断是否重复计算这个点
				double yuanvalue = AllNode.get(p).NodeValue;
			
			if(!(null == Part2Child || Part2Child.size() == 0)){
						
			//计算数值并将这些节点添加进循环
			List<String> part2child = new ArrayList<String>();
			List<IWordID> wordid = new ArrayList<IWordID>();
			String simplechild = null;
			//转换成String的List
			for(int t=0;t<Part2Child.size();t++){
				IWord iword = Part2Child.get(t).getWord(1);
				IWordID wordid0 = iword.getID();
				simplechild = iword.getLemma();
				part2child.add(simplechild);
				wordid.add(wordid0);
			}
			
			//几个子类分别计算
			for(int n=0;n<part2child.size();n++){
		    
		    simplechild = part2child.get(n);
		    IWordID wordid0 = wordid.get(n);
			//System.out.print(thisword+"的一个子类单词："+simplechild+"\n");
			
			//计算值
			int HypoNumber = 0;
			HypoNumber = method.getPart2HyponymsNumber(dict, AllNode.get(p).NodeWordID);

			//为子类建立Node
			Node Part2ChildNode = new Node();
			Part2ChildNode.setNodeName(simplechild);
			Part2ChildNode.setNodeWordID(wordid0);
			IIndexWord idxWord1=dict.getIndexWord(simplechild, pos);
            IWordID wordID1 = idxWord1.getWordIDs().get(0); 
        	IWord word1 = dict.getWord(wordID1); //获取词
            ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
            Part2ChildNode.setNodeSynset(synset1);
            //往下走1/n double nodevalue_part2child = AllNode.get(p).getNodeValue() / HypoNumber;
            double nodevalue_part2child = AllNode.get(p).getNodeValue() / HypoNumber;
            //子类赋值
            Part2ChildNode.setNodeValue(nodevalue_part2child);
            //System.out.print(Part2ChildNode.NodeName+"的值："+nodevalue_part2child+"\n");
            
            //加入List
            Part2ChildNode.setNodeFrom("Part2Child");
            Part2ChildNode.setNodeFromWord(simplechild);
            AllNode_part2child.add(Part2ChildNode);
            //看看子类找没找到
            for(int m=0;m<NodeList.size();m++){
            	if(simplechild.equals(NodeList.get(m).NodeName) && !(simplechild.equals(NodeList.get(i).NodeName))){
            		NodeList.get(m).setNodeValue(Part2ChildNode.NodeValue);
            		double PrivacyValue_part2child = Part2ChildNode.NodeValue;
            	    AllNode_part2child.remove(Part2ChildNode);
            		PrivacyNode.add(Part2ChildNode);
            		//System.out.print("找到了某词的part2子类是已知单词！ "+simplechild+"的值："+PrivacyValue_part2child+"\n");
            		//写txt文件
            		//method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"的泄露值："+PrivacyValue_part2child+System.getProperty("line.separator"));
            		method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"   ");
            		//判断泄露值是否大于1
                    if(PrivacyValue_part2child>=1.0){
                    	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
                    	return PrivacyValue_part2child;
                    }
                    else{  
                    	
                    	//看有没有重复
                		boolean xindexiao = false;
                		for(int t=0;t<PrivacyNode.size();t++){
                		   if(Part2ChildNode.NodeName.equals(PrivacyNode.get(t).NodeName)){
//                			  if(PrivacyValue_part2child>PrivacyNode.get(t).NodeValue){
//                				  PrivacyValue = PrivacyValue - PrivacyNode.get(t).NodeValue;
//                				  PrivacyValue = PrivacyValue + PrivacyValue_part2child;
//                				  PrivacyNode.remove(PrivacyNode.get(t));
//                			  }
//                			  else{  
//                				  xindexiao = true;
//                			  }
                			   
                			 //改算法
                 			   xindexiao = true;
                 			   double oldvalue = PrivacyNode.get(t).NodeValue;
                 			   double newvalue = PrivacyNode.get(t).NodeValue + PrivacyValue_part2child;
                 			   if(newvalue>1.0){
                 				   newvalue = 1.0;
                 			   }
                 			   else{   }
                 			   PrivacyNode.get(t).setNodeValue(newvalue);
                 			   PrivacyValue = PrivacyValue - oldvalue;
                 			   PrivacyValue = PrivacyValue + newvalue;
                 			   Part2ChildNode.setNodeValue(newvalue);
                 			   PrivacyNode.remove(PrivacyNode.get(t));
                			   
                			   
                			   
                		   }
                		   else{    }
                		}
                		if(xindexiao){  	}
                		else{ 
                			PrivacyValue = PrivacyValue + PrivacyValue_part2child;
                			PrivacyNode.add(Part2ChildNode);
                		}
                		
                    }
            		break;
            	}
            	else{      	}
            }

            //判断泄露值是否大于1
            if(PrivacyValue>=1.0){
            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
            	return PrivacyValue;
            }
            else{    }

            
            
            //循环几个子的结束
			}
			//判断Child为空的if
			}
			else{   
				//System.out.print(AllNode.get(0).NodeName+"的part2子类走不下去了！"); 
				//判断泄露值是否大于1
	            if(PrivacyValue>=1.0){
	            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
	            	return PrivacyValue;
	            }
	            else{      }
			}
			
			}
			
			//如果是找父类找来的，要跳过那个点
			else{
				
				//判断是否重复计算这个点
				double yuanvalue = AllNode.get(p).NodeValue;
				
				if(!(null == Part2Child || Part2Child.size() == 0)){
					
					//要跳过的那个点
					String tiao = AllNode.get(p).NodeFromWord;
					
					//计算数值并将这些节点添加进循环
					List<String> part2child = new ArrayList<String>();
					List<IWordID> wordid = new ArrayList<IWordID>();
					String simplechild = null;
					//转换成String的List
					for(int t=0;t<Part2Child.size();t++){
						IWord iword = Part2Child.get(t).getWord(1);
						IWordID wordid0 = iword.getID();
						simplechild = iword.getLemma();
						if(!(simplechild.equals(tiao))){
						part2child.add(simplechild);
						wordid.add(wordid0);
						}
						else{    }
					}
					
					//几个子类分别计算
					for(int n=0;n<part2child.size();n++){
				    
				    simplechild = part2child.get(n);
				    IWordID wordid0 = wordid.get(n);
					//System.out.print(thisword+"的一个子类单词："+simplechild+"\n");
					
					//计算值
					int HypoNumber = 0;
					HypoNumber = method.getPart2HyponymsNumber(dict, AllNode.get(p).NodeWordID);

					//为子类建立Node
					Node Part2ChildNode = new Node();
					Part2ChildNode.setNodeName(simplechild);
					Part2ChildNode.setNodeWordID(wordid0);
					IIndexWord idxWord1=dict.getIndexWord(simplechild, pos);
		            IWordID wordID1 = idxWord1.getWordIDs().get(0); 
		        	IWord word1 = dict.getWord(wordID1); //获取词
		            ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
		            Part2ChildNode.setNodeSynset(synset1);
		            //往下走1/n double nodevalue_part2child = AllNode.get(p).getNodeValue() / HypoNumber;
		            double nodevalue_part2child = AllNode.get(p).getNodeValue() / HypoNumber;
		            //子类赋值
		            Part2ChildNode.setNodeValue(nodevalue_part2child);
		            //System.out.print(Part2ChildNode.NodeName+"的值："+nodevalue_part2child+"\n");
		            
		            //加入List
		            Part2ChildNode.setNodeFrom("Part2Child");
		            Part2ChildNode.setNodeFromWord(simplechild);
		            AllNode_part2child.add(Part2ChildNode);
		            //看看子类找没找到
		            for(int m=0;m<NodeList.size();m++){
		            	if(simplechild.equals(NodeList.get(m).NodeName) && !(simplechild.equals(NodeList.get(i).NodeName))){
		            		NodeList.get(m).setNodeValue(Part2ChildNode.NodeValue);
		            		double PrivacyValue_part2child = Part2ChildNode.NodeValue;
		            		AllNode_part2child.remove(Part2ChildNode);
		            		PrivacyNode.add(Part2ChildNode);
		            		//System.out.print("找到了某词的part2子类是已知单词！ "+simplechild+"的值："+PrivacyValue_part2child+"\n");
		            		//写txt文件
		            		//method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"的泄露值："+PrivacyValue_part2child+System.getProperty("line.separator"));
		            		method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"   ");
		            		//判断泄露值是否大于1
		                    if(PrivacyValue_part2child>=1.0){
		                    	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
		                    	return PrivacyValue_part2child;
		                    }
		                    else{  
		                    	
		                    	//看有没有重复
		                		boolean xindexiao = false;
		                		for(int t=0;t<PrivacyNode.size();t++){
		                		   if(Part2ChildNode.NodeName.equals(PrivacyNode.get(t).NodeName)){
//		                			  if(PrivacyValue_part2child>PrivacyNode.get(t).NodeValue){
//		                				  PrivacyValue = PrivacyValue - PrivacyNode.get(t).NodeValue;
//		                				  PrivacyValue = PrivacyValue + PrivacyValue_part2child;
//		                				  PrivacyNode.remove(PrivacyNode.get(t));
//		                			  }
//		                			  else{  
//		                				  xindexiao = true;
//		                			  }
		                			   
		                			 //改算法
		                 			   xindexiao = true;
		                 			   double oldvalue = PrivacyNode.get(t).NodeValue;
		                 			   double newvalue = PrivacyNode.get(t).NodeValue + PrivacyValue_part2child;
		                 			   if(newvalue>1.0){
		                 				   newvalue = 1.0;
		                 			   }
		                 			   else{   }
		                 			   PrivacyNode.get(t).setNodeValue(newvalue);
		                 			   PrivacyValue = PrivacyValue - oldvalue;
		                 			   PrivacyValue = PrivacyValue + newvalue;
		                 			   Part2ChildNode.setNodeValue(newvalue);
		                 			   PrivacyNode.remove(PrivacyNode.get(t));
		                			   
		                			   
		                		   }
		                		   else{    }
		                		}
		                		if(xindexiao){  	}
		                		else{ 
		                			PrivacyValue = PrivacyValue + PrivacyValue_part2child;
		                			PrivacyNode.add(Part2ChildNode);
		                		}
		                		
		                    }
		            		break;
		            	}
		            	else{      	}
		            }
		            
		            //判断泄露值是否大于1
		            if(PrivacyValue>=1.0){
		            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
		            	return PrivacyValue;
		            }
		            else{    }

		            
		            
		            //循环几个子的结束
					}
					//判断Child为空的if
					}
					else{   
						//System.out.print(AllNode.get(0).NodeName+"的part2子类走不下去了！"); 
						//判断泄露值是否大于1
			            if(PrivacyValue>=1.0){
			            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
			            	return PrivacyValue;
			            }
			            else{      }
					}
				
			}
			//=========================part2子类套装结束============================
			
			
			
			
			
			//====================Part3父类套装=======================
			//先判断来源
			if(!(AllNode.get(p).NodeFrom.equals("Part3Child"))){
				
				//判断是否重复计算这个点
				double yuanvalue = AllNode.get(p).NodeValue;
			
			if(!(Part3Father==null)){
						
			//计算数值并将这些节点添加进循环
			String part3father = null;	
			IWord iword = Part3Father.getWord(1);
			part3father = iword.getLemma();
			//找IWordID
			IWordID wordid = Part3Father.getWord(1).getID();
			//System.out.print(thisword+"的part3父类单词："+part3father+"\n");
			
			//计算值
			int HypoNumber = 0;
			HypoNumber = method.getPart3HyponymsNumber(dict, wordid);
			if(HypoNumber == 0){
				HypoNumber = 1;
			}
			else{    }
			//为父类建立Node
			Node Part3FatherNode = new Node();
			Part3FatherNode.setNodeName(part3father);
			Part3FatherNode.setNodeWordID(wordid);
			IIndexWord idxWord1=dict.getIndexWord(part3father, pos);
            IWordID wordID1 = idxWord1.getWordIDs().get(0); 
        	IWord word1 = dict.getWord(wordID1); //获取词
            ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
            Part3FatherNode.setNodeSynset(synset1);
            //向上走100% double nodevalue_part3father = AllNode.get(p).getNodeValue() * HypoNumber;
            double nodevalue_part3father = AllNode.get(p).getNodeValue();
            //父类赋值
            Part3FatherNode.setNodeValue(nodevalue_part3father);
            //System.out.print(Part3FatherNode.NodeName+"的值："+nodevalue_part3father+"\n");
            
            //加入List
            Part3FatherNode.setNodeFrom("Part3Father");
            Part3FatherNode.setNodeFromWord(part3father);
            AllNode_part3father.add(Part3FatherNode);
            //看看父类找没找到
            for(int m=0;m<NodeList.size();m++){
            	if(part3father.equals(NodeList.get(m).NodeName) && !(part3father.equals(NodeList.get(i).NodeName))){
            		NodeList.get(m).setNodeValue(Part3FatherNode.NodeValue);
            		double PrivacyValue_part3father = Part3FatherNode.NodeValue;
            		AllNode_part3father.remove(Part3FatherNode);
            		PrivacyNode.add(Part3FatherNode);
            		//System.out.print("找到了某词的part3父类是已知单词！ "+part3father+"的值："+PrivacyValue_part3father+"\n");
            		//写txt文件
            		//method.WriteFile("D://CalculateTest//CalculateValue.txt", part3father+"的泄露值："+PrivacyValue_part3father+System.getProperty("line.separator"));
            		method.WriteFile("D://CalculateTest//CalculateValue.txt", part3father+"   ");
            		//判断泄露值是否大于1
                    if(PrivacyValue_part3father>=1.0){
                    	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
                    	return PrivacyValue_part3father;
                    }
                    else{  
                    	
                    	//看有没有重复
                		boolean xindexiao = false;
                		for(int t=0;t<PrivacyNode.size();t++){
                		   if(Part3FatherNode.NodeName.equals(PrivacyNode.get(t).NodeName)){
//                			  if(PrivacyValue_part3father>PrivacyNode.get(t).NodeValue){
//                				  PrivacyValue = PrivacyValue - PrivacyNode.get(t).NodeValue;
//                				  PrivacyValue = PrivacyValue + PrivacyValue_part3father;
//                				  PrivacyNode.remove(PrivacyNode.get(t));
//                			  }
//                			  else{  
//                				  xindexiao = true;
//                			  }
                			   
                			 //改算法
                 			   xindexiao = true;
                 			   double oldvalue = PrivacyNode.get(t).NodeValue;
                 			   double newvalue = PrivacyNode.get(t).NodeValue + PrivacyValue_part3father;
                 			   if(newvalue>1.0){
                 				   newvalue = 1.0;
                 			   }
                 			   else{   }
                 			   PrivacyNode.get(t).setNodeValue(newvalue);
                 			   PrivacyValue = PrivacyValue - oldvalue;
                 			   PrivacyValue = PrivacyValue + newvalue;
                 			   Part3FatherNode.setNodeValue(newvalue);
                 			   PrivacyNode.remove(PrivacyNode.get(t));
                			   
                			   
                			   
                		   }
                		   else{    }
                		}
                		if(xindexiao){  	}
                		else{ 
                			PrivacyValue = PrivacyValue + PrivacyValue_part3father;
                			PrivacyNode.add(Part3FatherNode);
                		}
                		
                    }
            		break;
            	}
            	else{      	}
            }
            
            //判断泄露值是否大于1
            if(PrivacyValue>=1.0){
            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
            	return PrivacyValue;
            }
            else{    }

            
			}
			else{   
				//System.out.print(AllNode.get(0).NodeName+"的part3父类走不下去了！"); 
				//判断泄露值是否大于1
	            if(PrivacyValue>=1.0){
	            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
	            	return PrivacyValue;
	            }
	            else{      }
			}
			
			}
			else{    }
			//=========================Part3父类套装结束============================
			
			
			
			
			
			//====================Part3子类套装=======================
			//先判断来源
			if(!(AllNode.get(p).NodeFrom.equals("Part3Father"))){
				
				//判断是否重复计算这个点
				double yuanvalue = AllNode.get(p).NodeValue;
			
			if(!(null == Part3Child || Part3Child.size() == 0)){
						
			//计算数值并将这些节点添加进循环
			List<String> part3child = new ArrayList<String>();
			List<IWordID> wordid = new ArrayList<IWordID>();
			String simplechild = null;
			//转换成String的List
			for(int t=0;t<Part3Child.size();t++){
				IWord iword = Part3Child.get(t).getWord(1);
				IWordID wordid0 = iword.getID();
				simplechild = iword.getLemma();
				part3child.add(simplechild);
				wordid.add(wordid0);
			}
			
			//几个子类分别计算
			for(int n=0;n<part3child.size();n++){
		    
		    simplechild = part3child.get(n);
		    IWordID wordid0 = wordid.get(n);
			//System.out.print(thisword+"的一个子类单词："+simplechild+"\n");
			
			//计算值
			int HypoNumber = 0;
			HypoNumber = method.getPart3HyponymsNumber(dict, AllNode.get(p).NodeWordID);

			//为子类建立Node
			Node Part3ChildNode = new Node();
			Part3ChildNode.setNodeName(simplechild);
			Part3ChildNode.setNodeWordID(wordid0);
			IIndexWord idxWord1=dict.getIndexWord(simplechild, pos);
            IWordID wordID1 = idxWord1.getWordIDs().get(0); 
        	IWord word1 = dict.getWord(wordID1); //获取词
            ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
            Part3ChildNode.setNodeSynset(synset1);
            //往下走1/n double nodevalue_part3child = AllNode.get(p).getNodeValue() / HypoNumber;
            double nodevalue_part3child = AllNode.get(p).getNodeValue() / HypoNumber;
            //子类赋值
            Part3ChildNode.setNodeValue(nodevalue_part3child);
            //System.out.print(Part3ChildNode.NodeName+"的值："+nodevalue_part3child+"\n");
            
            //加入List
            Part3ChildNode.setNodeFrom("Part3Child");
            Part3ChildNode.setNodeFromWord(simplechild);
            AllNode_part3child.add(Part3ChildNode);
            //看看子类找没找到
            for(int m=0;m<NodeList.size();m++){
            	if(simplechild.equals(NodeList.get(m).NodeName) && !(simplechild.equals(NodeList.get(i).NodeName))){
            		NodeList.get(m).setNodeValue(Part3ChildNode.NodeValue);
            		double PrivacyValue_part3child = Part3ChildNode.NodeValue;
            		AllNode_part3child.remove(Part3ChildNode);
            		PrivacyNode.add(Part3ChildNode);
            		//System.out.print("找到了某词的part3子类是已知单词！ "+simplechild+"的值："+PrivacyValue_part3child+"\n");
            		//写txt文件
            		//method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"的泄露值："+PrivacyValue_part3child+System.getProperty("line.separator"));
            		method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"   ");
            		//判断泄露值是否大于1
                    if(PrivacyValue_part3child>=1.0){
                    	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
                    	return PrivacyValue_part3child;
                    }
                    else{  
                    	
                    	//看有没有重复
                		boolean xindexiao = false;
                		for(int t=0;t<PrivacyNode.size();t++){
                		   if(Part3ChildNode.NodeName.equals(PrivacyNode.get(t).NodeName)){
//                			  if(PrivacyValue_part3child>PrivacyNode.get(t).NodeValue){
//                				  PrivacyValue = PrivacyValue - PrivacyNode.get(t).NodeValue;
//                				  PrivacyValue = PrivacyValue + PrivacyValue_part3child;
//                				  PrivacyNode.remove(PrivacyNode.get(t));
//                			  }
//                			  else{  
//                				  xindexiao = true;
//                			  }
                			   
                			 //改算法
                 			   xindexiao = true;
                 			   double oldvalue = PrivacyNode.get(t).NodeValue;
                 			   double newvalue = PrivacyNode.get(t).NodeValue + PrivacyValue_part3child;
                 			   if(newvalue>1.0){
                 				   newvalue = 1.0;
                 			   }
                 			   else{   }
                 			   PrivacyNode.get(t).setNodeValue(newvalue);
                 			   PrivacyValue = PrivacyValue - oldvalue;
                 			   PrivacyValue = PrivacyValue + newvalue;
                 			   Part3ChildNode.setNodeValue(newvalue);
                 			   PrivacyNode.remove(PrivacyNode.get(t));
                			   
                			   
                			   
                		   }
                		   else{    }
                		}
                		if(xindexiao){  	}
                		else{ 
                			PrivacyValue = PrivacyValue + PrivacyValue_part3child;
                			PrivacyNode.add(Part3ChildNode);
                		}
                		
                    }
            		break;
            	}
            	else{      	}
            }
            
            //判断泄露值是否大于1
            if(PrivacyValue>=1.0){
            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
            	return PrivacyValue;
            }
            else{    }

            
            
            //循环几个子的结束
			}
			//判断Child为空的if
			}
			else{   
				//System.out.print(AllNode.get(0).NodeName+"的part3子类走不下去了！"); 
				//判断泄露值是否大于1
	            if(PrivacyValue>=1.0){
	            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
	            	return PrivacyValue;
	            }
	            else{      }
			}
			

			}
			
			//如果是找父类找来的，要跳过那个点
			else{
				
				//判断是否重复计算这个点
				double yuanvalue = AllNode.get(p).NodeValue;
				
				if(!(null == Part3Child || Part3Child.size() == 0)){
					
					//要跳过的那个点
					String tiao = AllNode.get(p).NodeFromWord;
					
					//计算数值并将这些节点添加进循环
					List<String> part3child = new ArrayList<String>();
					List<IWordID> wordid = new ArrayList<IWordID>();
					String simplechild = null;
					//转换成String的List
					for(int t=0;t<Part3Child.size();t++){
						IWord iword = Part3Child.get(t).getWord(1);
						IWordID wordid0 = iword.getID();
						simplechild = iword.getLemma();
						if(!(simplechild.equals(tiao))){
						part3child.add(simplechild);
						wordid.add(wordid0);
						}
						else{    }
					}
					
					//几个子类分别计算
					for(int n=0;n<part3child.size();n++){
				    
				    simplechild = part3child.get(n);
				    IWordID wordid0 = wordid.get(n);
					//System.out.print(thisword+"的一个子类单词："+simplechild+"\n");
					
					//计算值
					int HypoNumber = 0;
					HypoNumber = method.getPart3HyponymsNumber(dict, AllNode.get(p).NodeWordID);

					//为子类建立Node
					Node Part3ChildNode = new Node();
					Part3ChildNode.setNodeName(simplechild);
					Part3ChildNode.setNodeWordID(wordid0);
					IIndexWord idxWord1=dict.getIndexWord(simplechild, pos);
		            IWordID wordID1 = idxWord1.getWordIDs().get(0); 
		        	IWord word1 = dict.getWord(wordID1); //获取词
		            ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
		            Part3ChildNode.setNodeSynset(synset1);
		            //往下走1/n double nodevalue_part3child = AllNode.get(p).getNodeValue() / HypoNumber;
		            double nodevalue_part3child = AllNode.get(p).getNodeValue() / HypoNumber;
		            //子类赋值
		            Part3ChildNode.setNodeValue(nodevalue_part3child);
		            //System.out.print(Part3ChildNode.NodeName+"的值："+nodevalue_part3child+"\n");
		            
		            //加入List
		            Part3ChildNode.setNodeFrom("Part3Child");
		            Part3ChildNode.setNodeFromWord(simplechild);
		            AllNode_part3child.add(Part3ChildNode);
		            //看看子类找没找到
		            for(int m=0;m<NodeList.size();m++){
		            	if(simplechild.equals(NodeList.get(m).NodeName) && !(simplechild.equals(NodeList.get(i).NodeName))){
		            		NodeList.get(m).setNodeValue(Part3ChildNode.NodeValue);
		            		double PrivacyValue_part3child = Part3ChildNode.NodeValue;
		            		AllNode_part3child.remove(Part3ChildNode);
		            		PrivacyNode.add(Part3ChildNode);
		            		//System.out.print("找到了某词的part3子类是已知单词！ "+simplechild+"的值："+PrivacyValue_part3child+"\n");
		            		//写txt文件
		            		//method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"的泄露值："+PrivacyValue_part3child+System.getProperty("line.separator"));
		            		method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"   ");
		            		//判断泄露值是否大于1
		                    if(PrivacyValue_part3child>=1.0){
		                    	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
		                    	return PrivacyValue_part3child;
		                    }
		                    else{  
		                    	
		                    	//看有没有重复
		                		boolean xindexiao = false;
		                		for(int t=0;t<PrivacyNode.size();t++){
		                		   if(Part3ChildNode.NodeName.equals(PrivacyNode.get(t).NodeName)){
//		                			  if(PrivacyValue_part3child>PrivacyNode.get(t).NodeValue){
//		                				  PrivacyValue = PrivacyValue - PrivacyNode.get(t).NodeValue;
//		                				  PrivacyValue = PrivacyValue + PrivacyValue_part3child;
//		                				  PrivacyNode.remove(PrivacyNode.get(t));
//		                			  }
//		                			  else{  
//		                				  xindexiao = true;
//		                			  }
		                			   
		                			 //改算法
		                 			   xindexiao = true;
		                 			   double oldvalue = PrivacyNode.get(t).NodeValue;
		                 			   double newvalue = PrivacyNode.get(t).NodeValue + PrivacyValue_part3child;
		                 			   if(newvalue>1.0){
		                 				   newvalue = 1.0;
		                 			   }
		                 			   else{   }
		                 			   PrivacyNode.get(t).setNodeValue(newvalue);
		                 			   PrivacyValue = PrivacyValue - oldvalue;
		                 			   PrivacyValue = PrivacyValue + newvalue;
		                 			   Part3ChildNode.setNodeValue(newvalue);
		                 			   PrivacyNode.remove(PrivacyNode.get(t));
		                			   
		                			   
		                			   
		                		   }
		                		   else{    }
		                		}
		                		if(xindexiao){  	}
		                		else{ 
		                			PrivacyValue = PrivacyValue + PrivacyValue_part3child;
		                			PrivacyNode.add(Part3ChildNode);
		                		}
		                		
		                    }
		            		break;
		            	}
		            	else{      	}
		            }
		            
		            //判断泄露值是否大于1
		            if(PrivacyValue>=1.0){
		            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
		            	return PrivacyValue;
		            }
		            else{    }

		            
		            
		            //循环几个子的结束
					}
					//判断Child为空的if
					}
					else{   
						//System.out.print(AllNode.get(0).NodeName+"的part3子类走不下去了！"); 
						//判断泄露值是否大于1
			            if(PrivacyValue>=1.0){
			            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
			            	return PrivacyValue;
			            }
			            else{      }
					}
				
			}
			//=========================part3子类套装结束============================
			
			
			
			
			//====================Attribute子类套装=======================
			//先判断来源
			if(!(AllNode.get(p).NodeFrom.equals("AttributeFather"))){
				
				//判断是否重复计算这个点
				double yuanvalue = AllNode.get(p).NodeValue;
			
			if(!(null == AttributeChild || AttributeChild.size() == 0)){
						
			//计算数值并将这些节点添加进循环
			List<String> attributechild = new ArrayList<String>();
			List<IWordID> wordid = new ArrayList<IWordID>();
			String simplechild = null;
			//转换成String的List
			for(int t=0;t<AttributeChild.size();t++){
				IWord iword = AttributeChild.get(t).getWord(1);
				IWordID wordid0 = iword.getID();
				simplechild = iword.getLemma();
				attributechild.add(simplechild);
				wordid.add(wordid0);
			}
			
			//几个子类分别计算
			for(int n=0;n<attributechild.size();n++){
		    
		    simplechild = attributechild.get(n);
		    IWordID wordid0 = wordid.get(n);
			//System.out.print(thisword+"的一个子类单词："+simplechild+"\n");
			
			//计算值
			int HypoNumber = 0;
			HypoNumber = method.getAttributeHyponymsNumber(dict, AllNode.get(p).NodeWordID);

			//为子类建立Node
			Node AttributeChildNode = new Node();
			AttributeChildNode.setNodeName(simplechild);
			AttributeChildNode.setNodeWordID(wordid0);
			
			//以防副词再被查Attribute报错
			if(dict.getIndexWord(simplechild, pos) == null){
				//System.out.print(simplechild+"是副词，不再找Attribute了");
				break;
			}
			else{    }

			IIndexWord idxWord1=dict.getIndexWord(simplechild, pos);
			IWordID wordID1 = idxWord1.getWordIDs().get(0); 
        	IWord word1 = dict.getWord(wordID1); //获取词
            ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
            AttributeChildNode.setNodeSynset(synset1);
            //往下走1/n double nodevalue_attributechild = AllNode.get(p).getNodeValue() / HypoNumber;
            double nodevalue_attributechild = AllNode.get(p).getNodeValue() / HypoNumber;
            //子类赋值
            AttributeChildNode.setNodeValue(nodevalue_attributechild);
            //System.out.print(AttributeChildNode.NodeName+"的值："+nodevalue_attributechild+"\n");
            
            //加入List
            AttributeChildNode.setNodeFrom("AttributeChild");
            AttributeChildNode.setNodeFromWord(simplechild);
            AllNode_attributechild.add(AttributeChildNode);
            //看看子类找没找到
            for(int m=0;m<NodeList.size();m++){
            	if(simplechild.equals(NodeList.get(m).NodeName) && !(simplechild.equals(NodeList.get(i).NodeName))){
            		NodeList.get(m).setNodeValue(AttributeChildNode.NodeValue);
            		double PrivacyValue_attributechild = AttributeChildNode.NodeValue;
            		AllNode_attributechild.remove(AttributeChildNode);
            		PrivacyNode.add(AttributeChildNode);
            		//System.out.print("找到了某词的attribute子类是已知单词！ "+simplechild+"的值："+PrivacyValue_attributechild+"\n");
            		//写txt文件
            		//method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"的泄露值："+PrivacyValue_attributechild+System.getProperty("line.separator"));
            		method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"   ");
            		//判断泄露值是否大于1
                    if(PrivacyValue_attributechild>=1.0){
                    	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
                    	return PrivacyValue_attributechild;
                    }
                    else{  
                    	
                    	//看有没有重复
                		boolean xindexiao = false;
                		for(int t=0;t<PrivacyNode.size();t++){
                		   if(AttributeChildNode.NodeName.equals(PrivacyNode.get(t).NodeName)){
//                			  if(PrivacyValue_attributechild>PrivacyNode.get(t).NodeValue){
//                				  PrivacyValue = PrivacyValue - PrivacyNode.get(t).NodeValue;
//                				  PrivacyValue = PrivacyValue + PrivacyValue_attributechild;
//                				  PrivacyNode.remove(PrivacyNode.get(t));
//                			  }
//                			  else{  
//                				  xindexiao = true;
//                			  }
                			   
                			 //改算法
                 			   xindexiao = true;
                 			   double oldvalue = PrivacyNode.get(t).NodeValue;
                 			   double newvalue = PrivacyNode.get(t).NodeValue + PrivacyValue_attributechild;
                 			   if(newvalue>1.0){
                 				   newvalue = 1.0;
                 			   }
                 			   else{   }
                 			   PrivacyNode.get(t).setNodeValue(newvalue);
                 			   PrivacyValue = PrivacyValue - oldvalue;
                 			   PrivacyValue = PrivacyValue + newvalue;
                 			   AttributeChildNode.setNodeValue(newvalue);
                 			   PrivacyNode.remove(PrivacyNode.get(t));
                			   
                			   
                			   
                		   }
                		   else{    }
                		}
                		if(xindexiao){  	}
                		else{ 
                			PrivacyValue = PrivacyValue + PrivacyValue_attributechild;
                			PrivacyNode.add(AttributeChildNode);
                		}
                		
                    }
            		break;
            	}
            	else{      	}
            }

            //判断泄露值是否大于1
            if(PrivacyValue>=1.0){
            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
            	return PrivacyValue;
            }
            else{    }

            
            
            //循环几个子的结束
			}
			//判断Child为空的if
			}
			else{   
				//System.out.print(AllNode.get(0).NodeName+"的attribute子类走不下去了！"); 
				//判断泄露值是否大于1
	            if(PrivacyValue>=1.0){
	            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
	            	return PrivacyValue;
	            }
	            else{      }
			}
			
			
			}
			
			//如果是找父类找来的，要跳过那个点
			else{
				
				//判断是否重复计算这个点
				double yuanvalue = AllNode.get(p).NodeValue;
				
				if(!(null == AttributeChild || AttributeChild.size() == 0)){
					
					//要跳过的那个点
					String tiao = AllNode.get(p).NodeFromWord;
					
					//计算数值并将这些节点添加进循环
					List<String> attributechild = new ArrayList<String>();
					List<IWordID> wordid = new ArrayList<IWordID>();
					String simplechild = null;
					//转换成String的List
					for(int t=0;t<AttributeChild.size();t++){
						IWord iword = AttributeChild.get(t).getWord(1);
						IWordID wordid0 = iword.getID();
						simplechild = iword.getLemma();
						if(!(simplechild.equals(tiao))){
						attributechild.add(simplechild);
						wordid.add(wordid0);
						}
						else{    }
					}
					
					//几个子类分别计算
					for(int n=0;n<attributechild.size();n++){
				    
				    simplechild = attributechild.get(n);
				    IWordID wordid0 = wordid.get(n);
					//System.out.print(thisword+"的一个子类单词："+simplechild+"\n");
					
					//计算值
					int HypoNumber = 0;
					HypoNumber = method.getAttributeHyponymsNumber(dict, AllNode.get(p).NodeWordID);

					//为子类建立Node
					Node AttributeChildNode = new Node();
					AttributeChildNode.setNodeName(simplechild);
					AttributeChildNode.setNodeWordID(wordid0);
					
					//以防副词再被查Attribute报错
					if(dict.getIndexWord(simplechild, pos) == null){
						//System.out.print(simplechild+"是副词，不再找Attribute了");
						break;
					}
					else{    }

					IIndexWord idxWord1=dict.getIndexWord(simplechild, pos);
					IWordID wordID1 = idxWord1.getWordIDs().get(0); 
		        	IWord word1 = dict.getWord(wordID1); //获取词
		            ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
		            AttributeChildNode.setNodeSynset(synset1);
		            //往下走1/n double nodevalue_attributechild = AllNode.get(p).getNodeValue() / HypoNumber;
		            double nodevalue_attributechild = AllNode.get(p).getNodeValue() / HypoNumber;
		            //子类赋值
		            AttributeChildNode.setNodeValue(nodevalue_attributechild);
		            //System.out.print(AttributeChildNode.NodeName+"的值："+nodevalue_attributechild+"\n");
		            
		            //加入List
		            AttributeChildNode.setNodeFrom("AttributeChild");
		            AttributeChildNode.setNodeFromWord(simplechild);
		            AllNode_attributechild.add(AttributeChildNode);
		            //看看子类找没找到
		            for(int m=0;m<NodeList.size();m++){
		            	if(simplechild.equals(NodeList.get(m).NodeName) && !(simplechild.equals(NodeList.get(i).NodeName))){
		            		NodeList.get(m).setNodeValue(AttributeChildNode.NodeValue);
		            		double PrivacyValue_attributechild = AttributeChildNode.NodeValue;
		            		AllNode_attributechild.remove(AttributeChildNode);
		            		PrivacyNode.add(AttributeChildNode);
		            		//System.out.print("找到了某词的attribute子类是已知单词！ "+simplechild+"的值："+PrivacyValue_attributechild+"\n");
		            		//写txt文件
		            		//method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"的泄露值："+PrivacyValue_attributechild+System.getProperty("line.separator"));
		            		method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"   ");
		            		//判断泄露值是否大于1
		                    if(PrivacyValue_attributechild>=1.0){
		                    	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
		                    	return PrivacyValue_attributechild;
		                    }
		                    else{  
		                    	
		                    	//看有没有重复
		                		boolean xindexiao = false;
		                		for(int t=0;t<PrivacyNode.size();t++){
		                		   if(AttributeChildNode.NodeName.equals(PrivacyNode.get(t).NodeName)){
//		                			  if(PrivacyValue_attributechild>PrivacyNode.get(t).NodeValue){
//		                				  PrivacyValue = PrivacyValue - PrivacyNode.get(t).NodeValue;
//		                				  PrivacyValue = PrivacyValue + PrivacyValue_attributechild;
//		                				  PrivacyNode.remove(PrivacyNode.get(t));
//		                			  }
//		                			  else{  
//		                				  xindexiao = true;
//		                			  }
		                			   
		                			   //改算法
		                 			   xindexiao = true;
		                 			   double oldvalue = PrivacyNode.get(t).NodeValue;
		                 			   double newvalue = PrivacyNode.get(t).NodeValue + PrivacyValue_attributechild;
		                 			   if(newvalue>1.0){
		                 				   newvalue = 1.0;
		                 			   }
		                 			   else{   }
		                 			   PrivacyNode.get(t).setNodeValue(newvalue);
		                 			   PrivacyValue = PrivacyValue - oldvalue;
		                 			   PrivacyValue = PrivacyValue + newvalue;
		                 			   AttributeChildNode.setNodeValue(newvalue);
		                 			   PrivacyNode.remove(PrivacyNode.get(t));
		                			   
		                			   
		                			   
		                		   }
		                		   else{    }
		                		}
		                		if(xindexiao){  	}
		                		else{ 
		                			PrivacyValue = PrivacyValue + PrivacyValue_attributechild;
		                			PrivacyNode.add(AttributeChildNode);
		                		}
		                		
		                    }
		            		break;
		            	}
		            	else{      	}
		            }
		            
		            //判断泄露值是否大于1
		            if(PrivacyValue>=1.0){
		            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
		            	return PrivacyValue;
		            }
		            else{    }

		            
		            
		            //循环几个子的结束
					}
					//判断Child为空的if
					}
					else{   
						//System.out.print(AllNode.get(0).NodeName+"的attribute子类走不下去了！"); 
						//判断泄露值是否大于1
			            if(PrivacyValue>=1.0){
			            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"这个词泄露了！"+System.getProperty("line.separator"));
			            	return PrivacyValue;
			            }
			            else{      }
					}
				
			}
			//=========================attribute子类套装结束============================
			
			
			
			
			
			
			
			
			
		}
		
		
		

        }
        //整理
        AllNode.clear();
        AllNode.addAll(AllNode_father);
      	AllNode.addAll(AllNode_child);
      	AllNode.addAll(AllNode_instancefather);
      	AllNode.addAll(AllNode_instancechild);
      	AllNode.addAll(AllNode_part1father);
      	AllNode.addAll(AllNode_part1child);
      	AllNode.addAll(AllNode_part2father);
      	AllNode.addAll(AllNode_part2child);
      	AllNode.addAll(AllNode_part3father);
      	AllNode.addAll(AllNode_part3child);
      	AllNode.addAll(AllNode_attributechild);
      	AllNode_father.clear();
      	AllNode_child.clear();
      	AllNode_instancefather.clear();
      	AllNode_instancechild.clear();
      	AllNode_part1father.clear();
      	AllNode_part1child.clear();
      	AllNode_part2father.clear();
      	AllNode_part2child.clear();
      	AllNode_part3father.clear();
      	AllNode_part3child.clear();
      	AllNode_attributechild.clear();
      	//循环取词次数
      	roundnumber++;
         //第二套循环 AllNode的循环
        }
        
        
        
        
        //break;
        System.out.print(NodeList.get(i).getNodeName()+"返回的泄露值："+PrivacyValue+System.getProperty("line.separator")); 
    	System.out.print(System.getProperty("line.separator")); 
        method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"泄露值："+PrivacyValue+System.getProperty("line.separator"));
        //置回
        PrivacyValue = 0.0;
        PrivacyNode.clear();
        HasCalcuNode.clear();
        return PrivacyValue;
        //总循环
        }
		
		
		
		
		
		
		
		return PrivacyValue;
		
	}
	
	
	
	
}
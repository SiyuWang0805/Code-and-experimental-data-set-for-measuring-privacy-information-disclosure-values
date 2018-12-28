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
		
		//�ܵ�й¶ֵ
		double PrivacyValue = 0.0;
		//��¼�Ƿ��ظ���List
		List<Node> PrivacyNode = new ArrayList<Node>();
		//��¼�Ƿ�ɢ����List
		List<Node> HasCalcuNode = new ArrayList<Node>();
		
		
		String word = null;
		//������������Ӵʵ�list
		List<ISynset> RelatedWords = new ArrayList<ISynset>();
		//׼��
		Method method = new Method();
		String wnhome = System.getenv("WNHOME"); //��ȡWordNet��Ŀ¼��������WNHOME
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
		}//�򿪴ʵ�
        POS pos = POS.NOUN;
        
        //Node����
        List<Node> NodeList = new ArrayList<Node>();
        //��֪��Ϊʲô����str�Ժ�ͻȻ��Ҫ��.size()���.size()-1��List������һ��null��
        for(int i=0;i<WordsList.size()-1;i++){
        	String NodeName = WordsList.get(i);
        	Node node = new Node();
        	node.setNodeName(NodeName);
        	node.setNodeValue(0.0);
        	IIndexWord idxWord1=dict.getIndexWord(WordsList.get(i), pos);
            IWordID wordID1 = idxWord1.getWordIDs().get(0); 
        	IWord word1 = dict.getWord(wordID1); //��ȡ��
            ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
            node.setNodeSynset(synset1);
            node.setNodeFrom("None");
            node.setNodeFromWord("None");
            node.setNodeWordID(wordID1);
            node.setNodeHasCalculated(false);
            node.setNodeIsDisclo(false);
            NodeList.add(node);
        }
        System.out.print("NodeList��"+NodeList+"\n");
        
        
        

        
        //��ʼѭ��
        for(int i=0;i<NodeList.size();i++){
        	
        	
        //==============Ϊ��ʵ��ѭ����ӵģ�==============
        	Node onenode = new Node();
        	onenode.setNodeName(privacyword);
        	onenode.setNodeValue(0.0);
        	IIndexWord oneidxWord1=dict.getIndexWord(privacyword, pos);
            IWordID onewordID1 = oneidxWord1.getWordIDs().get(0); 
        	IWord oneword1 = dict.getWord(onewordID1); //��ȡ��
            ISynset onesynset1 = oneword1.getSynset(); //��ȡ�ô����ڵ�Synset
            onenode.setNodeSynset(onesynset1);
            onenode.setNodeFrom("None");
            onenode.setNodeFromWord("None");
            onenode.setNodeWordID(onewordID1);
            onenode.setNodeHasCalculated(false);
            onenode.setNodeIsDisclo(false);
            NodeList.set(i, onenode);
        //==========================================	
        	
            
        word = privacyword;
        //дtxt�ļ�
		method.WriteFile("D://CalculateTest//CalculateValue.txt", "ȱʧ��Ϊ��"+word+System.getProperty("line.separator"));
       
		//Ϊȱʧ�ʸ�ֵ1
        NodeList.get(i).setNodeValue(1.0);
        //�ܵ���Ҫ�����NodeList
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
        //����Ӻ�������Դ��ֹ����
        NodeList.get(i).setNodeFrom("RootNode");
        NodeList.get(i).setNodeHasCalculated(false);
        AllNode.add(NodeList.get(i));
        
        
//        //�����һ���ʵĸ��������Է���Ȧ
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
        
        
        
        
        
        //����һ��ѭ���ļ���
    	int roundnumber = 0;
    	
    	
        //�ڶ���ѭ�� AllNode��ѭ��
        while(!(AllNode.isEmpty())){
        	
	
  	
        for(int p=0;p<AllNode.size();p++){
        	
        	//ȡ��
        	String thisword = AllNode.get(p).NodeName;
        	IWordID wordID = AllNode.get(p).getNodeWordID();
        	//�ж��Ƿ��Ѿ���ɢ��
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
        	
          
		//��ʼѰ�����Ӵ�
		//����
		ISynset Father = method.getOneHypernyms(dict, wordID);
		//����
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
		//�Ѿ���ɢ����
        HasCalcuNode.add(AllNode.get(p));
		

		
		//�жϿ�
		if(Father==null&&Child==null&&InstanceFather==null&&InstanceChild==null&&
				Part1Father==null&&Part1Child==null&&Part2Father==null&&Part2Child==null&&
						Part3Father==null&&Part3Child==null&&AttributeChild==null){   
			
			System.out.print(thisword+"û�п����ҵĹ�ϵ��");
			break;
			
			
		}
		else{  
			
			
			
			//====================������װ=======================
			//���ж���Դ
			if(!(AllNode.get(p).NodeFrom.equals("Child"))){
	
			if(!(Father==null)){		
			//������ֵ������Щ�ڵ���ӽ�ѭ��
			String father = null;	
			IWord iword = Father.getWord(1);
			father = iword.getLemma(); 
			//��IWordID
			IWordID wordid = Father.getWord(1).getID();
			//System.out.print(thisword+"�ĸ��൥�ʣ�"+father+"\n");
			
			//����ֵ
			int HypoNumber = 0;
			HypoNumber = method.getHyponymsNumber(dict, wordid);
			if(HypoNumber == 0){
				HypoNumber = 1;
			}
			else{    }
			//Ϊ���ཨ��Node
			Node FatherNode = new Node();
			FatherNode.setNodeName(father);
			FatherNode.setNodeWordID(wordid);
			IIndexWord idxWord1=dict.getIndexWord(father, pos);
            IWordID wordID1 = idxWord1.getWordIDs().get(0); 
        	IWord word1 = dict.getWord(wordID1); //��ȡ��
            ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
            FatherNode.setNodeSynset(synset1);
            //������1/n double nodevalue_father = AllNode.get(p).getNodeValue() * HypoNumber;
            double nodevalue_father = AllNode.get(p).getNodeValue() / HypoNumber;
            //���ำֵ
            FatherNode.setNodeValue(nodevalue_father);
            //System.out.print(FatherNode.NodeName+"��ֵ��"+nodevalue_father+"\n");
            
            //����List
            FatherNode.setNodeFrom("Father");
            FatherNode.setNodeFromWord(father);
            AllNode_father.add(FatherNode);
            //����������û�ҵ�
            for(int m=0;m<NodeList.size();m++){
            	if(father.equals(NodeList.get(m).NodeName) && !(father.equals(NodeList.get(i).NodeName))){
            		NodeList.get(m).setNodeValue(FatherNode.NodeValue);
            		double PrivacyValue_father = FatherNode.NodeValue;
            		AllNode_father.remove(FatherNode);
            		PrivacyNode.add(FatherNode);
            		//System.out.print("�ҵ���ĳ�ʵĸ�������֪���ʣ� "+father+"��ֵ��"+PrivacyValue_father+"\n");
            		//дtxt�ļ�
            		//method.WriteFile("D://CalculateTest//CalculateValue.txt", father+"��й¶ֵ��"+PrivacyValue_father+System.getProperty("line.separator"));
            		method.WriteFile("D://CalculateTest//CalculateValue.txt", father+"   ");
            		//�ж�й¶ֵ�Ƿ����1
                    if(PrivacyValue_father>=1.0){
                    	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
                    	return PrivacyValue_father;
                    }
                    else{  
                    	
                    	//����û���ظ�
                    	//xindexiao���ڴ������ظ�
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
                			   
                			   //���㷨
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
            
            //�ж�й¶ֵ�Ƿ����1
            if(PrivacyValue>=1.0){
            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
            	return PrivacyValue;
            }
            else{    }

            
			}
			else{   
				//System.out.print(AllNode.get(0).NodeName+"�����߲���ȥ�ˣ�"); 
				//�ж�й¶ֵ�Ƿ����1
	            if(PrivacyValue>=1.0){
	            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
	            	return PrivacyValue;
	            }
	            else{      }
			}
			
			
			}
			else{    }
			//=========================������װ����============================
			
			
			
			
			//====================������װ=======================
			//�����ж���Դ
			if(!(AllNode.get(p).NodeFrom.equals("Father"))){
				
				//�ж��Ƿ��ظ����������
				double yuanvalue = AllNode.get(p).NodeValue;
			
			if(!(null == Child || Child.size() ==0)){
						
			//������ֵ������Щ�ڵ���ӽ�ѭ��
			List<String> child = new ArrayList<String>();
			List<IWordID> wordid = new ArrayList<IWordID>();
			String simplechild = null;
			//ת����String��List
			for(int t=0;t<Child.size();t++){
				IWord iword = Child.get(t).getWord(1);
				IWordID wordid0 = iword.getID();
				simplechild = iword.getLemma();
				child.add(simplechild);
				wordid.add(wordid0);
			}
			
			//��������ֱ����
			for(int n=0;n<child.size();n++){
		    
		    simplechild = child.get(n);
		    IWordID wordid0 = wordid.get(n);
			//System.out.print(thisword+"��һ�����൥�ʣ�"+simplechild+"\n");
			
			//����ֵ
			int HypoNumber = 0;
			HypoNumber = method.getHyponymsNumber(dict, AllNode.get(p).NodeWordID);

			//Ϊ���ཨ��Node
			Node ChildNode = new Node();
			ChildNode.setNodeName(simplechild);
			ChildNode.setNodeWordID(wordid0);
			IIndexWord idxWord1=dict.getIndexWord(simplechild, pos);
            IWordID wordID1 = idxWord1.getWordIDs().get(0); 
        	IWord word1 = dict.getWord(wordID1); //��ȡ��
            ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
            ChildNode.setNodeSynset(synset1);
            //������100% double nodevalue_child = AllNode.get(p).getNodeValue() / HypoNumber;
            double nodevalue_child = AllNode.get(p).getNodeValue();
            //���ำֵ
            ChildNode.setNodeValue(nodevalue_child);
            //System.out.print(ChildNode.NodeName+"��ֵ��"+nodevalue_child+"\n");
            
            //����List
            ChildNode.setNodeFrom("Child");
            ChildNode.setNodeFromWord(simplechild);
            AllNode_child.add(ChildNode);
            //����������û�ҵ�
            for(int m=0;m<NodeList.size();m++){
            	if(simplechild.equals(NodeList.get(m).NodeName) && !(simplechild.equals(NodeList.get(i).NodeName))){
            		NodeList.get(m).setNodeValue(ChildNode.NodeValue);
            		double PrivacyValue_child = ChildNode.NodeValue;
            		AllNode_child.remove(ChildNode);
                    PrivacyNode.add(ChildNode);
            		//System.out.print("�ҵ���ĳ�ʵ���������֪���ʣ� "+simplechild+"��ֵ��"+PrivacyValue_child+"\n");
            		//дtxt�ļ�
            		//method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"��й¶ֵ��"+PrivacyValue_child+System.getProperty("line.separator"));
                    method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"   ");
                    //�ж�й¶ֵ�Ƿ����1
                    if(PrivacyValue_child>=1.0){
                    	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
                    	return PrivacyValue_child;
                    }
                    else{  

                    	//����û���ظ�
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
                			   
                			 //���㷨
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
            
            //�ж�й¶ֵ�Ƿ����1
            if(PrivacyValue>=1.0){
            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
            	return PrivacyValue;
            }
            else{    }

            
            
            //ѭ�������ӵĽ���
			}
			//�ж�ChildΪ�յ�if
			}
			else{   
				//System.out.print(AllNode.get(0).NodeName+"�����߲���ȥ�ˣ�"); 
				//�ж�й¶ֵ�Ƿ����1
	            if(PrivacyValue>=1.0){
	            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
	            	return PrivacyValue;
	            }
	            else{      }
			}
			
			
			}
			
			//����ǴӸ������ģ�Ҫ�����Ǹ���
			else{  
				
				//�ж��Ƿ��ظ����������
				double yuanvalue = AllNode.get(p).NodeValue;
				
				if(!(null == Child || Child.size() ==0)){
					
					//Ҫ�������Ǹ���
					String tiao = AllNode.get(p).NodeFromWord;
					
					//������ֵ������Щ�ڵ���ӽ�ѭ��
					List<String> child = new ArrayList<String>();
					List<IWordID> wordid = new ArrayList<IWordID>();
					String simplechild = null;
					//ת����String��List
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
					
					//��������ֱ����
					for(int n=0;n<child.size();n++){
				    
				    simplechild = child.get(n);
				    IWordID wordid0 = wordid.get(n);
					//System.out.print(thisword+"��һ�����൥�ʣ�"+simplechild+"\n");
					
					//����ֵ
					int HypoNumber = 0;
					HypoNumber = method.getHyponymsNumber(dict, AllNode.get(p).NodeWordID);

					//Ϊ���ཨ��Node
					Node ChildNode = new Node();
					ChildNode.setNodeName(simplechild);
					ChildNode.setNodeWordID(wordid0);
					IIndexWord idxWord1=dict.getIndexWord(simplechild, pos);
		            IWordID wordID1 = idxWord1.getWordIDs().get(0); 
		        	IWord word1 = dict.getWord(wordID1); //��ȡ��
		            ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
		            ChildNode.setNodeSynset(synset1);
		            //������100% double nodevalue_child = AllNode.get(p).getNodeValue() / HypoNumber;
		            double nodevalue_child = AllNode.get(p).getNodeValue();
		            //���ำֵ
		            ChildNode.setNodeValue(nodevalue_child);
		            //System.out.print(ChildNode.NodeName+"��ֵ��"+nodevalue_child+"\n");
		            
		            //����List
		            ChildNode.setNodeFrom("Child");
		            ChildNode.setNodeFromWord(simplechild);
		            AllNode_child.add(ChildNode);
		            //����������û�ҵ�
		            for(int m=0;m<NodeList.size();m++){
		            	if(simplechild.equals(NodeList.get(m).NodeName) && !(simplechild.equals(NodeList.get(i).NodeName))){
		            		NodeList.get(m).setNodeValue(ChildNode.NodeValue);
		            		double PrivacyValue_child = ChildNode.NodeValue;
		            		AllNode_child.remove(ChildNode);
		            		PrivacyNode.add(ChildNode);
		            		//System.out.print("�ҵ���ĳ�ʵ���������֪���ʣ� "+simplechild+"��ֵ��"+PrivacyValue_child+"\n");
		            		//дtxt�ļ�
		            		//method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"��й¶ֵ��"+PrivacyValue_child+System.getProperty("line.separator"));
		            		method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"   ");
		            		//�ж�й¶ֵ�Ƿ����1
		                    if(PrivacyValue_child>=1.0){
		                    	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
		                    	return PrivacyValue_child;
		                    }
		                    else{  

		                    	//����û���ظ�
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
		                			   
		                			 //���㷨
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
		            
		            //�ж�й¶ֵ�Ƿ����1
		            if(PrivacyValue>=1.0){
		            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
		            	return PrivacyValue;
		            }
		            else{    }

		            
		            
		            //ѭ�������ӵĽ���
					}
					//�ж�ChildΪ�յ�if
					}
					else{   
						//System.out.print(AllNode.get(0).NodeName+"�����߲���ȥ�ˣ�"); 
						//�ж�й¶ֵ�Ƿ����1
			            if(PrivacyValue>=1.0){
			            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
			            	return PrivacyValue;
			            }
			            else{      }
					}
				
			}
			//=========================������װ����============================
			
			
			
			
			
			//====================Instance������װ=======================
			//���ж���Դ
			if(!(AllNode.get(p).NodeFrom.equals("InstanceChild"))){
			
				//�ж��Ƿ��ظ����������
				double yuanvalue = AllNode.get(p).NodeValue;
			
			if(!(InstanceFather==null)){
						
			//������ֵ������Щ�ڵ���ӽ�ѭ��
			String instancefather = null;	
			IWord iword = InstanceFather.getWord(1);
			instancefather = iword.getLemma();
			//��IWordID
			IWordID wordid = InstanceFather.getWord(1).getID();
			//System.out.print(thisword+"��Instance���൥�ʣ�"+instancefather+"\n");
			
			//����ֵ
			int HypoNumber = 0;
			HypoNumber = method.getInstanceHyponymsNumber(dict, wordid);
			if(HypoNumber == 0){
				HypoNumber = 1;
			}
			else{    }
			//Ϊ���ཨ��Node
			Node InstanceFatherNode = new Node();
			InstanceFatherNode.setNodeName(instancefather);
			InstanceFatherNode.setNodeWordID(wordid);
			IIndexWord idxWord1=dict.getIndexWord(instancefather, pos);
            IWordID wordID1 = idxWord1.getWordIDs().get(0); 
        	IWord word1 = dict.getWord(wordID1); //��ȡ��
            ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
            InstanceFatherNode.setNodeSynset(synset1);
            //������1/n double nodevalue_instancefather = AllNode.get(p).getNodeValue() * HypoNumber;
            double nodevalue_instancefather = AllNode.get(p).getNodeValue() / HypoNumber;
            //���ำֵ
            InstanceFatherNode.setNodeValue(nodevalue_instancefather);
            //System.out.print(InstanceFatherNode.NodeName+"��ֵ��"+nodevalue_instancefather+"\n");
            
            //����List
            InstanceFatherNode.setNodeFrom("InstanceFather");
            InstanceFatherNode.setNodeFromWord(instancefather);
            AllNode_instancefather.add(InstanceFatherNode);
            //����������û�ҵ�
            for(int m=0;m<NodeList.size();m++){
            	if(instancefather.equals(NodeList.get(m).NodeName) && !(instancefather.equals(NodeList.get(i).NodeName))){
            		NodeList.get(m).setNodeValue(InstanceFatherNode.NodeValue);
            		double PrivacyValue_instancefather = InstanceFatherNode.NodeValue;
            		AllNode_instancefather.remove(InstanceFatherNode);
            		PrivacyNode.add(InstanceFatherNode);
            		//System.out.print("�ҵ���ĳ�ʵ�Instance��������֪���ʣ� "+instancefather+"��ֵ��"+PrivacyValue_instancefather+"\n");
            		//дtxt�ļ�
            		//method.WriteFile("D://CalculateTest//CalculateValue.txt", instancefather+"��й¶ֵ��"+PrivacyValue_instancefather+System.getProperty("line.separator"));
            		method.WriteFile("D://CalculateTest//CalculateValue.txt", instancefather+"   ");
            		//�ж�й¶ֵ�Ƿ����1
                    if(PrivacyValue_instancefather>=1.0){
                    	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
                    	return PrivacyValue_instancefather;
                    }
                    else{  
                    	
                    	//����û���ظ�
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
                			
                			//���㷨
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
            //�ж�й¶ֵ�Ƿ����1
            if(PrivacyValue>=1.0){
            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
            	return PrivacyValue;
            }
            else{    }

            
			}
			else{   
				//System.out.print(AllNode.get(0).NodeName+"��Instance�����߲���ȥ�ˣ�"); 
				//�ж�й¶ֵ�Ƿ����1
	            if(PrivacyValue>=1.0){
	            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
	            	return PrivacyValue;
	            }
	            else{      }
			}
			
			
			}
			else{    }
			//=========================Instance������װ����============================
			
			
			
			
			//====================Instance������װ=======================
			//���ж���Դ
			if(!(AllNode.get(p).NodeFrom.equals("InstanceFather"))){
				
				//�ж��Ƿ��ظ����������
				double yuanvalue = AllNode.get(p).NodeValue;
			
			if(!(null == InstanceChild || InstanceChild.size() == 0)){
						
			//������ֵ������Щ�ڵ���ӽ�ѭ��
			List<String> instancechild = new ArrayList<String>();
			List<IWordID> wordid = new ArrayList<IWordID>();
			String simplechild = null;
			//ת����String��List
			for(int t=0;t<InstanceChild.size();t++){
				IWord iword = InstanceChild.get(t).getWord(1);
				IWordID wordid0 = iword.getID();
				simplechild = iword.getLemma();
				instancechild.add(simplechild);
				wordid.add(wordid0);
			}
			
			//��������ֱ����
			for(int n=0;n<instancechild.size();n++){
		    
		    simplechild = instancechild.get(n);
		    IWordID wordid0 = wordid.get(n);
			//System.out.print(thisword+"��һ�����൥�ʣ�"+simplechild+"\n");
			
			//����ֵ
			int HypoNumber = 0;
			HypoNumber = method.getInstanceHyponymsNumber(dict, AllNode.get(p).NodeWordID);

			//Ϊ���ཨ��Node
			Node InstanceChildNode = new Node();
			InstanceChildNode.setNodeName(simplechild);
			InstanceChildNode.setNodeWordID(wordid0);
			IIndexWord idxWord1=dict.getIndexWord(simplechild, pos);
            IWordID wordID1 = idxWord1.getWordIDs().get(0); 
        	IWord word1 = dict.getWord(wordID1); //��ȡ��
            ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
            InstanceChildNode.setNodeSynset(synset1);
            //������100% double nodevalue_instancechild = AllNode.get(p).getNodeValue() / HypoNumber;
            double nodevalue_instancechild = AllNode.get(p).getNodeValue();
            //���ำֵ
            InstanceChildNode.setNodeValue(nodevalue_instancechild);
            //System.out.print(InstanceChildNode.NodeName+"��ֵ��"+nodevalue_instancechild+"\n");
            
            //����List
            InstanceChildNode.setNodeFrom("InstanceChild");
            InstanceChildNode.setNodeFromWord(simplechild);
            AllNode_instancechild.add(InstanceChildNode);
            //����������û�ҵ�
            for(int m=0;m<NodeList.size();m++){
            	if(simplechild.equals(NodeList.get(m).NodeName) && !(simplechild.equals(NodeList.get(i).NodeName))){
            		NodeList.get(m).setNodeValue(InstanceChildNode.NodeValue);
            		double PrivacyValue_instancechild = InstanceChildNode.NodeValue;
            		AllNode_instancechild.remove(InstanceChildNode);
            		PrivacyNode.add(InstanceChildNode);
            		//System.out.print("�ҵ���ĳ�ʵ�instance��������֪���ʣ� "+simplechild+"��ֵ��"+PrivacyValue_instancechild+"\n");
            		//дtxt�ļ�
            		//method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"��й¶ֵ��"+PrivacyValue_instancechild+System.getProperty("line.separator"));
            		method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"   ");
            		//�ж�й¶ֵ�Ƿ����1
                    if(PrivacyValue_instancechild>=1.0){
                    	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
                    	return PrivacyValue_instancechild;
                    }
                    else{  
                    	
                    	//����û���ظ�
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
                			   
                			 //���㷨
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
            
            //�ж�й¶ֵ�Ƿ����1
            if(PrivacyValue>=1.0){
            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
            	return PrivacyValue;
            }
            else{    }

            
            
            //ѭ�������ӵĽ���
			}
			//�ж�ChildΪ�յ�if
			}
			else{   
				//System.out.print(AllNode.get(0).NodeName+"��Instance�����߲���ȥ�ˣ�"); 
				//�ж�й¶ֵ�Ƿ����1
	            if(PrivacyValue>=1.0){
	            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
	            	return PrivacyValue;
	            }
	            else{      }
			}
			
			
			}
			
			//������Ҹ��������ģ�Ҫ�����Ǹ���
			else{
				
				//�ж��Ƿ��ظ����������
				double yuanvalue = AllNode.get(p).NodeValue;
				
				if(!(null == InstanceChild || InstanceChild.size() == 0)){
					
					//Ҫ�������Ǹ���
					String tiao = AllNode.get(p).NodeFromWord;
					
					//������ֵ������Щ�ڵ���ӽ�ѭ��
					List<String> instancechild = new ArrayList<String>();
					List<IWordID> wordid = new ArrayList<IWordID>();
					String simplechild = null;
					//ת����String��List
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
					
					//��������ֱ����
					for(int n=0;n<instancechild.size();n++){
				    
				    simplechild = instancechild.get(n);
				    IWordID wordid0 = wordid.get(n);
					//System.out.print(thisword+"��һ�����൥�ʣ�"+simplechild+"\n");
					
					//����ֵ
					int HypoNumber = 0;
					HypoNumber = method.getInstanceHyponymsNumber(dict, AllNode.get(p).NodeWordID);

					//Ϊ���ཨ��Node
					Node InstanceChildNode = new Node();
					InstanceChildNode.setNodeName(simplechild);
					InstanceChildNode.setNodeWordID(wordid0);
					IIndexWord idxWord1=dict.getIndexWord(simplechild, pos);
		            IWordID wordID1 = idxWord1.getWordIDs().get(0); 
		        	IWord word1 = dict.getWord(wordID1); //��ȡ��
		            ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
		            InstanceChildNode.setNodeSynset(synset1);
		            //������100% double nodevalue_instancechild = AllNode.get(p).getNodeValue() / HypoNumber;
		            double nodevalue_instancechild = AllNode.get(p).getNodeValue();
		            //���ำֵ
		            InstanceChildNode.setNodeValue(nodevalue_instancechild);
		            //System.out.print(InstanceChildNode.NodeName+"��ֵ��"+nodevalue_instancechild+"\n");
		            
		            //����List
		            InstanceChildNode.setNodeFrom("InstanceChild");
		            InstanceChildNode.setNodeFromWord(simplechild);
		            AllNode_instancechild.add(InstanceChildNode);
		            //����������û�ҵ�
		            for(int m=0;m<NodeList.size();m++){
		            	if(simplechild.equals(NodeList.get(m).NodeName) && !(simplechild.equals(NodeList.get(i).NodeName))){
		            		NodeList.get(m).setNodeValue(InstanceChildNode.NodeValue);
		            		double PrivacyValue_instancechild = InstanceChildNode.NodeValue;
		            		AllNode_instancechild.remove(InstanceChildNode);
		            		PrivacyNode.add(InstanceChildNode);
		            		//System.out.print("�ҵ���ĳ�ʵ�instance��������֪���ʣ� "+simplechild+"��ֵ��"+PrivacyValue_instancechild+"\n");
		            		//дtxt�ļ�
		            		//method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"��й¶ֵ��"+PrivacyValue_instancechild+System.getProperty("line.separator"));
		            		method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"   ");
		            		//�ж�й¶ֵ�Ƿ����1
		                    if(PrivacyValue_instancechild>=1.0){
		                    	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
		                    	return PrivacyValue_instancechild;
		                    }
		                    else{  
		                    	
		                    	//����û���ظ�
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
		                			   
		                			   //���㷨
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
		            
		            //�ж�й¶ֵ�Ƿ����1
		            if(PrivacyValue>=1.0){
		            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
		            	return PrivacyValue;
		            }
		            else{    }

		            
		            
		            //ѭ�������ӵĽ���
					}
					//�ж�ChildΪ�յ�if
					}
					else{   
						//System.out.print(AllNode.get(0).NodeName+"��Instance�����߲���ȥ�ˣ�"); 
						//�ж�й¶ֵ�Ƿ����1
			            if(PrivacyValue>=1.0){
			            	return PrivacyValue;
			            }
			            else{      }
					}
				
			}
			//=========================Instance������װ����============================
			
			
			
			
			
			
			//====================Part1������װ=======================
			//���ж���Դ
			if(!(AllNode.get(p).NodeFrom.equals("Part1Child"))){
				
				//�ж��Ƿ��ظ����������
				double yuanvalue = AllNode.get(p).NodeValue;
			
			 if(!(Part1Father==null)){
						
			//������ֵ������Щ�ڵ���ӽ�ѭ��
			String part1father = null;	
			IWord iword = Part1Father.getWord(1);
			part1father = iword.getLemma();
			//��IWordID
			IWordID wordid = Part1Father.getWord(1).getID();
			//System.out.print(thisword+"��part1���൥�ʣ�"+part1father+"\n");
			
			//����ֵ
			int HypoNumber = 0;
			HypoNumber = method.getPart1HyponymsNumber(dict, wordid);
			if(HypoNumber == 0){
				HypoNumber = 1;
			}
			else{    }
			//Ϊ���ཨ��Node
			Node Part1FatherNode = new Node();
			Part1FatherNode.setNodeName(part1father);
			Part1FatherNode.setNodeWordID(wordid);
			IIndexWord idxWord1=dict.getIndexWord(part1father, pos);
            IWordID wordID1 = idxWord1.getWordIDs().get(0); 
        	IWord word1 = dict.getWord(wordID1); //��ȡ��
            ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
            Part1FatherNode.setNodeSynset(synset1);
            //������100% double nodevalue_part1father = AllNode.get(p).getNodeValue() * HypoNumber;
            double nodevalue_part1father = AllNode.get(p).getNodeValue();
            //���ำֵ
            Part1FatherNode.setNodeValue(nodevalue_part1father);
            //System.out.print(Part1FatherNode.NodeName+"��ֵ��"+nodevalue_part1father+"\n");
            //����List
            Part1FatherNode.setNodeFrom("Part1Father");
            Part1FatherNode.setNodeFromWord(part1father);
            AllNode_part1father.add(Part1FatherNode);
            //����������û�ҵ�
            for(int m=0;m<NodeList.size();m++){
            	if(part1father.equals(NodeList.get(m).NodeName) && !(part1father.equals(NodeList.get(i).NodeName))){
            		NodeList.get(m).setNodeValue(Part1FatherNode.NodeValue);
            		double PrivacyValue_part1father = Part1FatherNode.NodeValue;
            		AllNode_part1father.remove(Part1FatherNode);
            		PrivacyNode.add(Part1FatherNode);
            		//System.out.print("�ҵ���ĳ�ʵ�part1��������֪���ʣ� "+part1father+"��ֵ��"+PrivacyValue_part1father+"\n");
            		//дtxt�ļ�
            		//method.WriteFile("D://CalculateTest//CalculateValue.txt", part1father+"��й¶ֵ��"+PrivacyValue_part1father+System.getProperty("line.separator"));
            		method.WriteFile("D://CalculateTest//CalculateValue.txt", part1father+"   ");
            		//�ж�й¶ֵ�Ƿ����1
                    if(PrivacyValue_part1father>=1.0){
                    	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
                    	return PrivacyValue_part1father;
                    }
                    else{  
                    	
                    	//����û���ظ�
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
                			   
                			   //���㷨
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
            
            //�ж�й¶ֵ�Ƿ����1
            if(PrivacyValue>=1.0){
            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
            	return PrivacyValue;
            }
            else{    }

            
			}
			else{   
				//System.out.print(AllNode.get(0).NodeName+"��part1�����߲���ȥ�ˣ�"); 
				//�ж�й¶ֵ�Ƿ����1
	            if(PrivacyValue>=1.0){
	            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
	            	return PrivacyValue;
	            }
	            else{      }
			}
			 
			}
			else{    }
			//=========================Part1������װ����============================
			
			
			
			
			
			//====================Part1������װ=======================
			//���ж���Դ
			if(!(AllNode.get(p).NodeFrom.equals("Part1Father"))){
				
				//�ж��Ƿ��ظ����������
				double yuanvalue = AllNode.get(p).NodeValue;
			
			if(!(null == Part1Child || Part1Child.size() == 0)){
						
			//������ֵ������Щ�ڵ���ӽ�ѭ��
			List<String> part1child = new ArrayList<String>();
			List<IWordID> wordid = new ArrayList<IWordID>();
			String simplechild = null;
			//ת����String��List
			for(int t=0;t<Part1Child.size();t++){
				IWord iword = Part1Child.get(t).getWord(1);
				IWordID wordid0 = iword.getID();
				simplechild = iword.getLemma();
				part1child.add(simplechild);
				wordid.add(wordid0);
			}
			
			//��������ֱ����
			for(int n=0;n<part1child.size();n++){
		    
		    simplechild = part1child.get(n);
		    IWordID wordid0 = wordid.get(n);
			//System.out.print(thisword+"��һ�����൥�ʣ�"+simplechild+"\n");
			
			//����ֵ
			int HypoNumber = 0;
			HypoNumber = method.getPart1HyponymsNumber(dict, AllNode.get(p).NodeWordID);

			//Ϊ���ཨ��Node
			Node Part1ChildNode = new Node();
			Part1ChildNode.setNodeName(simplechild);
			Part1ChildNode.setNodeWordID(wordid0);
			IIndexWord idxWord1=dict.getIndexWord(simplechild, pos);
            IWordID wordID1 = idxWord1.getWordIDs().get(0); 
        	IWord word1 = dict.getWord(wordID1); //��ȡ��
            ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
            Part1ChildNode.setNodeSynset(synset1);
            //������1/n double nodevalue_part1child = AllNode.get(p).getNodeValue() / HypoNumber;
            double nodevalue_part1child = AllNode.get(p).getNodeValue() / HypoNumber;
            //���ำֵ
            Part1ChildNode.setNodeValue(nodevalue_part1child);
            //System.out.print(Part1ChildNode.NodeName+"��ֵ��"+nodevalue_part1child+"\n");
            
            //����List
            Part1ChildNode.setNodeFrom("Part1Child");
            Part1ChildNode.setNodeFromWord(simplechild);
            AllNode_part1child.add(Part1ChildNode);
            //����������û�ҵ�
            for(int m=0;m<NodeList.size();m++){
            	if(simplechild.equals(NodeList.get(m).NodeName) && !(simplechild.equals(NodeList.get(i).NodeName))){
            		NodeList.get(m).setNodeValue(Part1ChildNode.NodeValue);
            		double PrivacyValue_part1child = Part1ChildNode.NodeValue;
            	    AllNode_part1child.remove(Part1ChildNode);
            		PrivacyNode.add(Part1ChildNode);
            		//System.out.print("�ҵ���ĳ�ʵ�part1��������֪���ʣ� "+simplechild+"��ֵ��"+PrivacyValue_part1child+"\n");
            		//дtxt�ļ�
            		//method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"��й¶ֵ��"+PrivacyValue_part1child+System.getProperty("line.separator"));
            		method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"   ");
            		//�ж�й¶ֵ�Ƿ����1
                    if(PrivacyValue_part1child>=1.0){
                    	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
                    	return PrivacyValue_part1child;
                    }
                    else{  
                    	
                    	//����û���ظ�
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
                			   
                			   //���㷨
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
            
            //�ж�й¶ֵ�Ƿ����1
            if(PrivacyValue>=1.0){
            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
            	return PrivacyValue;
            }
            else{    }

            
            
            //ѭ�������ӵĽ���
			}
			//�ж�ChildΪ�յ�if
			}
			else{   
				//System.out.print(AllNode.get(0).NodeName+"��part1�����߲���ȥ�ˣ�"); 
				//�ж�й¶ֵ�Ƿ����1
	            if(PrivacyValue>=1.0){
	            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
	            	return PrivacyValue;
	            }
	            else{      }
			}
			
			}
			
			//������Ҹ��������ģ�Ҫ�����Ǹ���
			else{
				
				//�ж��Ƿ��ظ����������
				double yuanvalue = AllNode.get(p).NodeValue;
				
				if(!(null == Part1Child || Part1Child.size() == 0)){
					
					//Ҫ�������Ǹ���
					String tiao = AllNode.get(p).NodeFromWord;
					
					//������ֵ������Щ�ڵ���ӽ�ѭ��
					List<String> part1child = new ArrayList<String>();
					List<IWordID> wordid = new ArrayList<IWordID>();
					String simplechild = null;
					//ת����String��List
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
					
					//��������ֱ����
					for(int n=0;n<part1child.size();n++){
				    
				    simplechild = part1child.get(n);
				    IWordID wordid0 = wordid.get(n);
					//System.out.print(thisword+"��һ�����൥�ʣ�"+simplechild+"\n");
					
					//����ֵ
					int HypoNumber = 0;
					HypoNumber = method.getPart1HyponymsNumber(dict, AllNode.get(p).NodeWordID);

					//Ϊ���ཨ��Node
					Node Part1ChildNode = new Node();
					Part1ChildNode.setNodeName(simplechild);
					Part1ChildNode.setNodeWordID(wordid0);
					IIndexWord idxWord1=dict.getIndexWord(simplechild, pos);
		            IWordID wordID1 = idxWord1.getWordIDs().get(0); 
		        	IWord word1 = dict.getWord(wordID1); //��ȡ��
		            ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
		            Part1ChildNode.setNodeSynset(synset1);
		            //������1/n double nodevalue_part1child = AllNode.get(p).getNodeValue() / HypoNumber;
		            double nodevalue_part1child = AllNode.get(p).getNodeValue() / HypoNumber;
		            //���ำֵ
		            Part1ChildNode.setNodeValue(nodevalue_part1child);
		            //System.out.print(Part1ChildNode.NodeName+"��ֵ��"+nodevalue_part1child+"\n");
		            
		            //����List
		            Part1ChildNode.setNodeFrom("Part1Child");
		            Part1ChildNode.setNodeFromWord(simplechild);
		            AllNode_part1child.add(Part1ChildNode);
		            //����������û�ҵ�
		            for(int m=0;m<NodeList.size();m++){
		            	if(simplechild.equals(NodeList.get(m).NodeName) && !(simplechild.equals(NodeList.get(i).NodeName))){
		            		NodeList.get(m).setNodeValue(Part1ChildNode.NodeValue);
		            		double PrivacyValue_part1child = Part1ChildNode.NodeValue;
		            		AllNode_part1child.remove(Part1ChildNode);
		            		PrivacyNode.add(Part1ChildNode);
		            		//System.out.print("�ҵ���ĳ�ʵ�part1��������֪���ʣ� "+simplechild+"��ֵ��"+PrivacyValue_part1child+"\n");
		            		//дtxt�ļ�
		            		//method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"��й¶ֵ��"+PrivacyValue_part1child+System.getProperty("line.separator"));
		            		method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"   ");
		            		//�ж�й¶ֵ�Ƿ����1
		                    if(PrivacyValue_part1child>=1.0){
		                    	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
		                    	return PrivacyValue_part1child;
		                    }
		                    else{  
		                    	
		                    	//����û���ظ�
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
		                			   
		                			 //���㷨
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
		            
		            //�ж�й¶ֵ�Ƿ����1
		            if(PrivacyValue>=1.0){
		            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
		            	return PrivacyValue;
		            }
		            else{    }

		            
		            
		            //ѭ�������ӵĽ���
					}
					//�ж�ChildΪ�յ�if
					}
					else{   
						//System.out.print(AllNode.get(0).NodeName+"��part1�����߲���ȥ�ˣ�"); 
						//�ж�й¶ֵ�Ƿ����1
			            if(PrivacyValue>=1.0){
			            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
			            	return PrivacyValue;
			            }
			            else{      }
					}
				
			}
			//=========================part1������װ����============================
			
			
			
			
			
			//====================Part2������װ=======================
			
			//���ж���Դ
			if(!(AllNode.get(p).NodeFrom.equals("Part2Child"))){
				
				//�ж��Ƿ��ظ����������
				double yuanvalue = AllNode.get(p).NodeValue;
			
			if(!(Part2Father==null)){
						
			//������ֵ������Щ�ڵ���ӽ�ѭ��
			String part2father = null;	
			IWord iword = Part2Father.getWord(1);
			part2father = iword.getLemma();
			//��IWordID
			IWordID wordid = Part2Father.getWord(1).getID();
			//System.out.print(thisword+"��part2���൥�ʣ�"+part2father+"\n");
			
			//����ֵ
			int HypoNumber = 0;
			HypoNumber = method.getPart2HyponymsNumber(dict, wordid);
			if(HypoNumber == 0){
				HypoNumber = 1;
			}
			else{    }
			//Ϊ���ཨ��Node
			Node Part2FatherNode = new Node();
			Part2FatherNode.setNodeName(part2father);
			Part2FatherNode.setNodeWordID(wordid);
			IIndexWord idxWord1=dict.getIndexWord(part2father, pos);
            IWordID wordID1 = idxWord1.getWordIDs().get(0); 
        	IWord word1 = dict.getWord(wordID1); //��ȡ��
            ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
            Part2FatherNode.setNodeSynset(synset1);
            //������100% double nodevalue_part2father = AllNode.get(p).getNodeValue() * HypoNumber;
            double nodevalue_part2father = AllNode.get(p).getNodeValue();
            //���ำֵ
            Part2FatherNode.setNodeValue(nodevalue_part2father);
            //System.out.print(Part2FatherNode.NodeName+"��ֵ��"+nodevalue_part2father+"\n");
            
            //����List
            Part2FatherNode.setNodeFrom("Part2Father");
            Part2FatherNode.setNodeFromWord(part2father);
            AllNode_part2father.add(Part2FatherNode);
            //����������û�ҵ�
            for(int m=0;m<NodeList.size();m++){
            	if(part2father.equals(NodeList.get(m).NodeName) && !(part2father.equals(NodeList.get(i).NodeName))){
            		NodeList.get(m).setNodeValue(Part2FatherNode.NodeValue);
            		double PrivacyValue_part2father = Part2FatherNode.NodeValue;
            		AllNode_part2father.remove(Part2FatherNode);
            		PrivacyNode.add(Part2FatherNode);
            		//System.out.print("�ҵ���ĳ�ʵ�part2��������֪���ʣ� "+part2father+"��ֵ��"+PrivacyValue_part2father+"\n");
            		//дtxt�ļ�
            		//method.WriteFile("D://CalculateTest//CalculateValue.txt", part2father+"��й¶ֵ��"+PrivacyValue_part2father+System.getProperty("line.separator"));
            		method.WriteFile("D://CalculateTest//CalculateValue.txt", part2father+"   ");
            		//�ж�й¶ֵ�Ƿ����1
                    if(PrivacyValue_part2father>=1.0){
                    	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
                    	return PrivacyValue_part2father;
                    }
                    else{  
                    	
                    	//����û���ظ�
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
                			   
                			 //���㷨
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
            
            //�ж�й¶ֵ�Ƿ����1
            if(PrivacyValue>=1.0){
            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
            	return PrivacyValue;
            }
            else{    }

            
			}
			else{   
				//System.out.print(AllNode.get(0).NodeName+"��part2�����߲���ȥ�ˣ�"); 
				//�ж�й¶ֵ�Ƿ����1
	            if(PrivacyValue>=1.0){
	            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
	            	return PrivacyValue;
	            }
	            else{      }
			}
			
			}
			else{    }
			//=========================Part2������װ����============================
			
			
			
			
			
			//====================Part2������װ=======================
			//���ж���Դ
			if(!(AllNode.get(p).NodeFrom.equals("Part2Father"))){
				
				//�ж��Ƿ��ظ����������
				double yuanvalue = AllNode.get(p).NodeValue;
			
			if(!(null == Part2Child || Part2Child.size() == 0)){
						
			//������ֵ������Щ�ڵ���ӽ�ѭ��
			List<String> part2child = new ArrayList<String>();
			List<IWordID> wordid = new ArrayList<IWordID>();
			String simplechild = null;
			//ת����String��List
			for(int t=0;t<Part2Child.size();t++){
				IWord iword = Part2Child.get(t).getWord(1);
				IWordID wordid0 = iword.getID();
				simplechild = iword.getLemma();
				part2child.add(simplechild);
				wordid.add(wordid0);
			}
			
			//��������ֱ����
			for(int n=0;n<part2child.size();n++){
		    
		    simplechild = part2child.get(n);
		    IWordID wordid0 = wordid.get(n);
			//System.out.print(thisword+"��һ�����൥�ʣ�"+simplechild+"\n");
			
			//����ֵ
			int HypoNumber = 0;
			HypoNumber = method.getPart2HyponymsNumber(dict, AllNode.get(p).NodeWordID);

			//Ϊ���ཨ��Node
			Node Part2ChildNode = new Node();
			Part2ChildNode.setNodeName(simplechild);
			Part2ChildNode.setNodeWordID(wordid0);
			IIndexWord idxWord1=dict.getIndexWord(simplechild, pos);
            IWordID wordID1 = idxWord1.getWordIDs().get(0); 
        	IWord word1 = dict.getWord(wordID1); //��ȡ��
            ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
            Part2ChildNode.setNodeSynset(synset1);
            //������1/n double nodevalue_part2child = AllNode.get(p).getNodeValue() / HypoNumber;
            double nodevalue_part2child = AllNode.get(p).getNodeValue() / HypoNumber;
            //���ำֵ
            Part2ChildNode.setNodeValue(nodevalue_part2child);
            //System.out.print(Part2ChildNode.NodeName+"��ֵ��"+nodevalue_part2child+"\n");
            
            //����List
            Part2ChildNode.setNodeFrom("Part2Child");
            Part2ChildNode.setNodeFromWord(simplechild);
            AllNode_part2child.add(Part2ChildNode);
            //����������û�ҵ�
            for(int m=0;m<NodeList.size();m++){
            	if(simplechild.equals(NodeList.get(m).NodeName) && !(simplechild.equals(NodeList.get(i).NodeName))){
            		NodeList.get(m).setNodeValue(Part2ChildNode.NodeValue);
            		double PrivacyValue_part2child = Part2ChildNode.NodeValue;
            	    AllNode_part2child.remove(Part2ChildNode);
            		PrivacyNode.add(Part2ChildNode);
            		//System.out.print("�ҵ���ĳ�ʵ�part2��������֪���ʣ� "+simplechild+"��ֵ��"+PrivacyValue_part2child+"\n");
            		//дtxt�ļ�
            		//method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"��й¶ֵ��"+PrivacyValue_part2child+System.getProperty("line.separator"));
            		method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"   ");
            		//�ж�й¶ֵ�Ƿ����1
                    if(PrivacyValue_part2child>=1.0){
                    	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
                    	return PrivacyValue_part2child;
                    }
                    else{  
                    	
                    	//����û���ظ�
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
                			   
                			 //���㷨
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

            //�ж�й¶ֵ�Ƿ����1
            if(PrivacyValue>=1.0){
            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
            	return PrivacyValue;
            }
            else{    }

            
            
            //ѭ�������ӵĽ���
			}
			//�ж�ChildΪ�յ�if
			}
			else{   
				//System.out.print(AllNode.get(0).NodeName+"��part2�����߲���ȥ�ˣ�"); 
				//�ж�й¶ֵ�Ƿ����1
	            if(PrivacyValue>=1.0){
	            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
	            	return PrivacyValue;
	            }
	            else{      }
			}
			
			}
			
			//������Ҹ��������ģ�Ҫ�����Ǹ���
			else{
				
				//�ж��Ƿ��ظ����������
				double yuanvalue = AllNode.get(p).NodeValue;
				
				if(!(null == Part2Child || Part2Child.size() == 0)){
					
					//Ҫ�������Ǹ���
					String tiao = AllNode.get(p).NodeFromWord;
					
					//������ֵ������Щ�ڵ���ӽ�ѭ��
					List<String> part2child = new ArrayList<String>();
					List<IWordID> wordid = new ArrayList<IWordID>();
					String simplechild = null;
					//ת����String��List
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
					
					//��������ֱ����
					for(int n=0;n<part2child.size();n++){
				    
				    simplechild = part2child.get(n);
				    IWordID wordid0 = wordid.get(n);
					//System.out.print(thisword+"��һ�����൥�ʣ�"+simplechild+"\n");
					
					//����ֵ
					int HypoNumber = 0;
					HypoNumber = method.getPart2HyponymsNumber(dict, AllNode.get(p).NodeWordID);

					//Ϊ���ཨ��Node
					Node Part2ChildNode = new Node();
					Part2ChildNode.setNodeName(simplechild);
					Part2ChildNode.setNodeWordID(wordid0);
					IIndexWord idxWord1=dict.getIndexWord(simplechild, pos);
		            IWordID wordID1 = idxWord1.getWordIDs().get(0); 
		        	IWord word1 = dict.getWord(wordID1); //��ȡ��
		            ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
		            Part2ChildNode.setNodeSynset(synset1);
		            //������1/n double nodevalue_part2child = AllNode.get(p).getNodeValue() / HypoNumber;
		            double nodevalue_part2child = AllNode.get(p).getNodeValue() / HypoNumber;
		            //���ำֵ
		            Part2ChildNode.setNodeValue(nodevalue_part2child);
		            //System.out.print(Part2ChildNode.NodeName+"��ֵ��"+nodevalue_part2child+"\n");
		            
		            //����List
		            Part2ChildNode.setNodeFrom("Part2Child");
		            Part2ChildNode.setNodeFromWord(simplechild);
		            AllNode_part2child.add(Part2ChildNode);
		            //����������û�ҵ�
		            for(int m=0;m<NodeList.size();m++){
		            	if(simplechild.equals(NodeList.get(m).NodeName) && !(simplechild.equals(NodeList.get(i).NodeName))){
		            		NodeList.get(m).setNodeValue(Part2ChildNode.NodeValue);
		            		double PrivacyValue_part2child = Part2ChildNode.NodeValue;
		            		AllNode_part2child.remove(Part2ChildNode);
		            		PrivacyNode.add(Part2ChildNode);
		            		//System.out.print("�ҵ���ĳ�ʵ�part2��������֪���ʣ� "+simplechild+"��ֵ��"+PrivacyValue_part2child+"\n");
		            		//дtxt�ļ�
		            		//method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"��й¶ֵ��"+PrivacyValue_part2child+System.getProperty("line.separator"));
		            		method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"   ");
		            		//�ж�й¶ֵ�Ƿ����1
		                    if(PrivacyValue_part2child>=1.0){
		                    	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
		                    	return PrivacyValue_part2child;
		                    }
		                    else{  
		                    	
		                    	//����û���ظ�
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
		                			   
		                			 //���㷨
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
		            
		            //�ж�й¶ֵ�Ƿ����1
		            if(PrivacyValue>=1.0){
		            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
		            	return PrivacyValue;
		            }
		            else{    }

		            
		            
		            //ѭ�������ӵĽ���
					}
					//�ж�ChildΪ�յ�if
					}
					else{   
						//System.out.print(AllNode.get(0).NodeName+"��part2�����߲���ȥ�ˣ�"); 
						//�ж�й¶ֵ�Ƿ����1
			            if(PrivacyValue>=1.0){
			            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
			            	return PrivacyValue;
			            }
			            else{      }
					}
				
			}
			//=========================part2������װ����============================
			
			
			
			
			
			//====================Part3������װ=======================
			//���ж���Դ
			if(!(AllNode.get(p).NodeFrom.equals("Part3Child"))){
				
				//�ж��Ƿ��ظ����������
				double yuanvalue = AllNode.get(p).NodeValue;
			
			if(!(Part3Father==null)){
						
			//������ֵ������Щ�ڵ���ӽ�ѭ��
			String part3father = null;	
			IWord iword = Part3Father.getWord(1);
			part3father = iword.getLemma();
			//��IWordID
			IWordID wordid = Part3Father.getWord(1).getID();
			//System.out.print(thisword+"��part3���൥�ʣ�"+part3father+"\n");
			
			//����ֵ
			int HypoNumber = 0;
			HypoNumber = method.getPart3HyponymsNumber(dict, wordid);
			if(HypoNumber == 0){
				HypoNumber = 1;
			}
			else{    }
			//Ϊ���ཨ��Node
			Node Part3FatherNode = new Node();
			Part3FatherNode.setNodeName(part3father);
			Part3FatherNode.setNodeWordID(wordid);
			IIndexWord idxWord1=dict.getIndexWord(part3father, pos);
            IWordID wordID1 = idxWord1.getWordIDs().get(0); 
        	IWord word1 = dict.getWord(wordID1); //��ȡ��
            ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
            Part3FatherNode.setNodeSynset(synset1);
            //������100% double nodevalue_part3father = AllNode.get(p).getNodeValue() * HypoNumber;
            double nodevalue_part3father = AllNode.get(p).getNodeValue();
            //���ำֵ
            Part3FatherNode.setNodeValue(nodevalue_part3father);
            //System.out.print(Part3FatherNode.NodeName+"��ֵ��"+nodevalue_part3father+"\n");
            
            //����List
            Part3FatherNode.setNodeFrom("Part3Father");
            Part3FatherNode.setNodeFromWord(part3father);
            AllNode_part3father.add(Part3FatherNode);
            //����������û�ҵ�
            for(int m=0;m<NodeList.size();m++){
            	if(part3father.equals(NodeList.get(m).NodeName) && !(part3father.equals(NodeList.get(i).NodeName))){
            		NodeList.get(m).setNodeValue(Part3FatherNode.NodeValue);
            		double PrivacyValue_part3father = Part3FatherNode.NodeValue;
            		AllNode_part3father.remove(Part3FatherNode);
            		PrivacyNode.add(Part3FatherNode);
            		//System.out.print("�ҵ���ĳ�ʵ�part3��������֪���ʣ� "+part3father+"��ֵ��"+PrivacyValue_part3father+"\n");
            		//дtxt�ļ�
            		//method.WriteFile("D://CalculateTest//CalculateValue.txt", part3father+"��й¶ֵ��"+PrivacyValue_part3father+System.getProperty("line.separator"));
            		method.WriteFile("D://CalculateTest//CalculateValue.txt", part3father+"   ");
            		//�ж�й¶ֵ�Ƿ����1
                    if(PrivacyValue_part3father>=1.0){
                    	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
                    	return PrivacyValue_part3father;
                    }
                    else{  
                    	
                    	//����û���ظ�
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
                			   
                			 //���㷨
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
            
            //�ж�й¶ֵ�Ƿ����1
            if(PrivacyValue>=1.0){
            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
            	return PrivacyValue;
            }
            else{    }

            
			}
			else{   
				//System.out.print(AllNode.get(0).NodeName+"��part3�����߲���ȥ�ˣ�"); 
				//�ж�й¶ֵ�Ƿ����1
	            if(PrivacyValue>=1.0){
	            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
	            	return PrivacyValue;
	            }
	            else{      }
			}
			
			}
			else{    }
			//=========================Part3������װ����============================
			
			
			
			
			
			//====================Part3������װ=======================
			//���ж���Դ
			if(!(AllNode.get(p).NodeFrom.equals("Part3Father"))){
				
				//�ж��Ƿ��ظ����������
				double yuanvalue = AllNode.get(p).NodeValue;
			
			if(!(null == Part3Child || Part3Child.size() == 0)){
						
			//������ֵ������Щ�ڵ���ӽ�ѭ��
			List<String> part3child = new ArrayList<String>();
			List<IWordID> wordid = new ArrayList<IWordID>();
			String simplechild = null;
			//ת����String��List
			for(int t=0;t<Part3Child.size();t++){
				IWord iword = Part3Child.get(t).getWord(1);
				IWordID wordid0 = iword.getID();
				simplechild = iword.getLemma();
				part3child.add(simplechild);
				wordid.add(wordid0);
			}
			
			//��������ֱ����
			for(int n=0;n<part3child.size();n++){
		    
		    simplechild = part3child.get(n);
		    IWordID wordid0 = wordid.get(n);
			//System.out.print(thisword+"��һ�����൥�ʣ�"+simplechild+"\n");
			
			//����ֵ
			int HypoNumber = 0;
			HypoNumber = method.getPart3HyponymsNumber(dict, AllNode.get(p).NodeWordID);

			//Ϊ���ཨ��Node
			Node Part3ChildNode = new Node();
			Part3ChildNode.setNodeName(simplechild);
			Part3ChildNode.setNodeWordID(wordid0);
			IIndexWord idxWord1=dict.getIndexWord(simplechild, pos);
            IWordID wordID1 = idxWord1.getWordIDs().get(0); 
        	IWord word1 = dict.getWord(wordID1); //��ȡ��
            ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
            Part3ChildNode.setNodeSynset(synset1);
            //������1/n double nodevalue_part3child = AllNode.get(p).getNodeValue() / HypoNumber;
            double nodevalue_part3child = AllNode.get(p).getNodeValue() / HypoNumber;
            //���ำֵ
            Part3ChildNode.setNodeValue(nodevalue_part3child);
            //System.out.print(Part3ChildNode.NodeName+"��ֵ��"+nodevalue_part3child+"\n");
            
            //����List
            Part3ChildNode.setNodeFrom("Part3Child");
            Part3ChildNode.setNodeFromWord(simplechild);
            AllNode_part3child.add(Part3ChildNode);
            //����������û�ҵ�
            for(int m=0;m<NodeList.size();m++){
            	if(simplechild.equals(NodeList.get(m).NodeName) && !(simplechild.equals(NodeList.get(i).NodeName))){
            		NodeList.get(m).setNodeValue(Part3ChildNode.NodeValue);
            		double PrivacyValue_part3child = Part3ChildNode.NodeValue;
            		AllNode_part3child.remove(Part3ChildNode);
            		PrivacyNode.add(Part3ChildNode);
            		//System.out.print("�ҵ���ĳ�ʵ�part3��������֪���ʣ� "+simplechild+"��ֵ��"+PrivacyValue_part3child+"\n");
            		//дtxt�ļ�
            		//method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"��й¶ֵ��"+PrivacyValue_part3child+System.getProperty("line.separator"));
            		method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"   ");
            		//�ж�й¶ֵ�Ƿ����1
                    if(PrivacyValue_part3child>=1.0){
                    	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
                    	return PrivacyValue_part3child;
                    }
                    else{  
                    	
                    	//����û���ظ�
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
                			   
                			 //���㷨
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
            
            //�ж�й¶ֵ�Ƿ����1
            if(PrivacyValue>=1.0){
            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
            	return PrivacyValue;
            }
            else{    }

            
            
            //ѭ�������ӵĽ���
			}
			//�ж�ChildΪ�յ�if
			}
			else{   
				//System.out.print(AllNode.get(0).NodeName+"��part3�����߲���ȥ�ˣ�"); 
				//�ж�й¶ֵ�Ƿ����1
	            if(PrivacyValue>=1.0){
	            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
	            	return PrivacyValue;
	            }
	            else{      }
			}
			

			}
			
			//������Ҹ��������ģ�Ҫ�����Ǹ���
			else{
				
				//�ж��Ƿ��ظ����������
				double yuanvalue = AllNode.get(p).NodeValue;
				
				if(!(null == Part3Child || Part3Child.size() == 0)){
					
					//Ҫ�������Ǹ���
					String tiao = AllNode.get(p).NodeFromWord;
					
					//������ֵ������Щ�ڵ���ӽ�ѭ��
					List<String> part3child = new ArrayList<String>();
					List<IWordID> wordid = new ArrayList<IWordID>();
					String simplechild = null;
					//ת����String��List
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
					
					//��������ֱ����
					for(int n=0;n<part3child.size();n++){
				    
				    simplechild = part3child.get(n);
				    IWordID wordid0 = wordid.get(n);
					//System.out.print(thisword+"��һ�����൥�ʣ�"+simplechild+"\n");
					
					//����ֵ
					int HypoNumber = 0;
					HypoNumber = method.getPart3HyponymsNumber(dict, AllNode.get(p).NodeWordID);

					//Ϊ���ཨ��Node
					Node Part3ChildNode = new Node();
					Part3ChildNode.setNodeName(simplechild);
					Part3ChildNode.setNodeWordID(wordid0);
					IIndexWord idxWord1=dict.getIndexWord(simplechild, pos);
		            IWordID wordID1 = idxWord1.getWordIDs().get(0); 
		        	IWord word1 = dict.getWord(wordID1); //��ȡ��
		            ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
		            Part3ChildNode.setNodeSynset(synset1);
		            //������1/n double nodevalue_part3child = AllNode.get(p).getNodeValue() / HypoNumber;
		            double nodevalue_part3child = AllNode.get(p).getNodeValue() / HypoNumber;
		            //���ำֵ
		            Part3ChildNode.setNodeValue(nodevalue_part3child);
		            //System.out.print(Part3ChildNode.NodeName+"��ֵ��"+nodevalue_part3child+"\n");
		            
		            //����List
		            Part3ChildNode.setNodeFrom("Part3Child");
		            Part3ChildNode.setNodeFromWord(simplechild);
		            AllNode_part3child.add(Part3ChildNode);
		            //����������û�ҵ�
		            for(int m=0;m<NodeList.size();m++){
		            	if(simplechild.equals(NodeList.get(m).NodeName) && !(simplechild.equals(NodeList.get(i).NodeName))){
		            		NodeList.get(m).setNodeValue(Part3ChildNode.NodeValue);
		            		double PrivacyValue_part3child = Part3ChildNode.NodeValue;
		            		AllNode_part3child.remove(Part3ChildNode);
		            		PrivacyNode.add(Part3ChildNode);
		            		//System.out.print("�ҵ���ĳ�ʵ�part3��������֪���ʣ� "+simplechild+"��ֵ��"+PrivacyValue_part3child+"\n");
		            		//дtxt�ļ�
		            		//method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"��й¶ֵ��"+PrivacyValue_part3child+System.getProperty("line.separator"));
		            		method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"   ");
		            		//�ж�й¶ֵ�Ƿ����1
		                    if(PrivacyValue_part3child>=1.0){
		                    	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
		                    	return PrivacyValue_part3child;
		                    }
		                    else{  
		                    	
		                    	//����û���ظ�
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
		                			   
		                			 //���㷨
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
		            
		            //�ж�й¶ֵ�Ƿ����1
		            if(PrivacyValue>=1.0){
		            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
		            	return PrivacyValue;
		            }
		            else{    }

		            
		            
		            //ѭ�������ӵĽ���
					}
					//�ж�ChildΪ�յ�if
					}
					else{   
						//System.out.print(AllNode.get(0).NodeName+"��part3�����߲���ȥ�ˣ�"); 
						//�ж�й¶ֵ�Ƿ����1
			            if(PrivacyValue>=1.0){
			            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
			            	return PrivacyValue;
			            }
			            else{      }
					}
				
			}
			//=========================part3������װ����============================
			
			
			
			
			//====================Attribute������װ=======================
			//���ж���Դ
			if(!(AllNode.get(p).NodeFrom.equals("AttributeFather"))){
				
				//�ж��Ƿ��ظ����������
				double yuanvalue = AllNode.get(p).NodeValue;
			
			if(!(null == AttributeChild || AttributeChild.size() == 0)){
						
			//������ֵ������Щ�ڵ���ӽ�ѭ��
			List<String> attributechild = new ArrayList<String>();
			List<IWordID> wordid = new ArrayList<IWordID>();
			String simplechild = null;
			//ת����String��List
			for(int t=0;t<AttributeChild.size();t++){
				IWord iword = AttributeChild.get(t).getWord(1);
				IWordID wordid0 = iword.getID();
				simplechild = iword.getLemma();
				attributechild.add(simplechild);
				wordid.add(wordid0);
			}
			
			//��������ֱ����
			for(int n=0;n<attributechild.size();n++){
		    
		    simplechild = attributechild.get(n);
		    IWordID wordid0 = wordid.get(n);
			//System.out.print(thisword+"��һ�����൥�ʣ�"+simplechild+"\n");
			
			//����ֵ
			int HypoNumber = 0;
			HypoNumber = method.getAttributeHyponymsNumber(dict, AllNode.get(p).NodeWordID);

			//Ϊ���ཨ��Node
			Node AttributeChildNode = new Node();
			AttributeChildNode.setNodeName(simplechild);
			AttributeChildNode.setNodeWordID(wordid0);
			
			//�Է������ٱ���Attribute����
			if(dict.getIndexWord(simplechild, pos) == null){
				//System.out.print(simplechild+"�Ǹ��ʣ�������Attribute��");
				break;
			}
			else{    }

			IIndexWord idxWord1=dict.getIndexWord(simplechild, pos);
			IWordID wordID1 = idxWord1.getWordIDs().get(0); 
        	IWord word1 = dict.getWord(wordID1); //��ȡ��
            ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
            AttributeChildNode.setNodeSynset(synset1);
            //������1/n double nodevalue_attributechild = AllNode.get(p).getNodeValue() / HypoNumber;
            double nodevalue_attributechild = AllNode.get(p).getNodeValue() / HypoNumber;
            //���ำֵ
            AttributeChildNode.setNodeValue(nodevalue_attributechild);
            //System.out.print(AttributeChildNode.NodeName+"��ֵ��"+nodevalue_attributechild+"\n");
            
            //����List
            AttributeChildNode.setNodeFrom("AttributeChild");
            AttributeChildNode.setNodeFromWord(simplechild);
            AllNode_attributechild.add(AttributeChildNode);
            //����������û�ҵ�
            for(int m=0;m<NodeList.size();m++){
            	if(simplechild.equals(NodeList.get(m).NodeName) && !(simplechild.equals(NodeList.get(i).NodeName))){
            		NodeList.get(m).setNodeValue(AttributeChildNode.NodeValue);
            		double PrivacyValue_attributechild = AttributeChildNode.NodeValue;
            		AllNode_attributechild.remove(AttributeChildNode);
            		PrivacyNode.add(AttributeChildNode);
            		//System.out.print("�ҵ���ĳ�ʵ�attribute��������֪���ʣ� "+simplechild+"��ֵ��"+PrivacyValue_attributechild+"\n");
            		//дtxt�ļ�
            		//method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"��й¶ֵ��"+PrivacyValue_attributechild+System.getProperty("line.separator"));
            		method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"   ");
            		//�ж�й¶ֵ�Ƿ����1
                    if(PrivacyValue_attributechild>=1.0){
                    	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
                    	return PrivacyValue_attributechild;
                    }
                    else{  
                    	
                    	//����û���ظ�
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
                			   
                			 //���㷨
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

            //�ж�й¶ֵ�Ƿ����1
            if(PrivacyValue>=1.0){
            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
            	return PrivacyValue;
            }
            else{    }

            
            
            //ѭ�������ӵĽ���
			}
			//�ж�ChildΪ�յ�if
			}
			else{   
				//System.out.print(AllNode.get(0).NodeName+"��attribute�����߲���ȥ�ˣ�"); 
				//�ж�й¶ֵ�Ƿ����1
	            if(PrivacyValue>=1.0){
	            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
	            	return PrivacyValue;
	            }
	            else{      }
			}
			
			
			}
			
			//������Ҹ��������ģ�Ҫ�����Ǹ���
			else{
				
				//�ж��Ƿ��ظ����������
				double yuanvalue = AllNode.get(p).NodeValue;
				
				if(!(null == AttributeChild || AttributeChild.size() == 0)){
					
					//Ҫ�������Ǹ���
					String tiao = AllNode.get(p).NodeFromWord;
					
					//������ֵ������Щ�ڵ���ӽ�ѭ��
					List<String> attributechild = new ArrayList<String>();
					List<IWordID> wordid = new ArrayList<IWordID>();
					String simplechild = null;
					//ת����String��List
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
					
					//��������ֱ����
					for(int n=0;n<attributechild.size();n++){
				    
				    simplechild = attributechild.get(n);
				    IWordID wordid0 = wordid.get(n);
					//System.out.print(thisword+"��һ�����൥�ʣ�"+simplechild+"\n");
					
					//����ֵ
					int HypoNumber = 0;
					HypoNumber = method.getAttributeHyponymsNumber(dict, AllNode.get(p).NodeWordID);

					//Ϊ���ཨ��Node
					Node AttributeChildNode = new Node();
					AttributeChildNode.setNodeName(simplechild);
					AttributeChildNode.setNodeWordID(wordid0);
					
					//�Է������ٱ���Attribute����
					if(dict.getIndexWord(simplechild, pos) == null){
						//System.out.print(simplechild+"�Ǹ��ʣ�������Attribute��");
						break;
					}
					else{    }

					IIndexWord idxWord1=dict.getIndexWord(simplechild, pos);
					IWordID wordID1 = idxWord1.getWordIDs().get(0); 
		        	IWord word1 = dict.getWord(wordID1); //��ȡ��
		            ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
		            AttributeChildNode.setNodeSynset(synset1);
		            //������1/n double nodevalue_attributechild = AllNode.get(p).getNodeValue() / HypoNumber;
		            double nodevalue_attributechild = AllNode.get(p).getNodeValue() / HypoNumber;
		            //���ำֵ
		            AttributeChildNode.setNodeValue(nodevalue_attributechild);
		            //System.out.print(AttributeChildNode.NodeName+"��ֵ��"+nodevalue_attributechild+"\n");
		            
		            //����List
		            AttributeChildNode.setNodeFrom("AttributeChild");
		            AttributeChildNode.setNodeFromWord(simplechild);
		            AllNode_attributechild.add(AttributeChildNode);
		            //����������û�ҵ�
		            for(int m=0;m<NodeList.size();m++){
		            	if(simplechild.equals(NodeList.get(m).NodeName) && !(simplechild.equals(NodeList.get(i).NodeName))){
		            		NodeList.get(m).setNodeValue(AttributeChildNode.NodeValue);
		            		double PrivacyValue_attributechild = AttributeChildNode.NodeValue;
		            		AllNode_attributechild.remove(AttributeChildNode);
		            		PrivacyNode.add(AttributeChildNode);
		            		//System.out.print("�ҵ���ĳ�ʵ�attribute��������֪���ʣ� "+simplechild+"��ֵ��"+PrivacyValue_attributechild+"\n");
		            		//дtxt�ļ�
		            		//method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"��й¶ֵ��"+PrivacyValue_attributechild+System.getProperty("line.separator"));
		            		method.WriteFile("D://CalculateTest//CalculateValue.txt", simplechild+"   ");
		            		//�ж�й¶ֵ�Ƿ����1
		                    if(PrivacyValue_attributechild>=1.0){
		                    	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
		                    	return PrivacyValue_attributechild;
		                    }
		                    else{  
		                    	
		                    	//����û���ظ�
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
		                			   
		                			   //���㷨
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
		            
		            //�ж�й¶ֵ�Ƿ����1
		            if(PrivacyValue>=1.0){
		            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
		            	return PrivacyValue;
		            }
		            else{    }

		            
		            
		            //ѭ�������ӵĽ���
					}
					//�ж�ChildΪ�յ�if
					}
					else{   
						//System.out.print(AllNode.get(0).NodeName+"��attribute�����߲���ȥ�ˣ�"); 
						//�ж�й¶ֵ�Ƿ����1
			            if(PrivacyValue>=1.0){
			            	method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"�����й¶�ˣ�"+System.getProperty("line.separator"));
			            	return PrivacyValue;
			            }
			            else{      }
					}
				
			}
			//=========================attribute������װ����============================
			
			
			
			
			
			
			
			
			
		}
		
		
		

        }
        //����
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
      	//ѭ��ȡ�ʴ���
      	roundnumber++;
         //�ڶ���ѭ�� AllNode��ѭ��
        }
        
        
        
        
        //break;
        System.out.print(NodeList.get(i).getNodeName()+"���ص�й¶ֵ��"+PrivacyValue+System.getProperty("line.separator")); 
    	System.out.print(System.getProperty("line.separator")); 
        method.WriteFile("D://CalculateTest//CalculateValue.txt", System.getProperty("line.separator")+"й¶ֵ��"+PrivacyValue+System.getProperty("line.separator"));
        //�û�
        PrivacyValue = 0.0;
        PrivacyNode.clear();
        HasCalcuNode.clear();
        return PrivacyValue;
        //��ѭ��
        }
		
		
		
		
		
		
		
		return PrivacyValue;
		
	}
	
	
	
	
}
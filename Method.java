package TryCalculate;



import java.io.*;
import java.net.URL;
import java.util.*;
import edu.mit.jwi.*;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.ISynsetID;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.mit.jwi.item.Pointer;
import jxl.Workbook;
import jxl.write.*;
import wordnet.CutRepetition;



public class Method {
	
	
	
	
	public void FindID(IDictionary dict,String str,POS pos) throws IOException{
		   IIndexWord idxWord=dict.getIndexWord(str, pos);
	       IWordID wordID=idxWord.getWordIDs().get(0);//��ȡdog��һ������ID
	       //System.out.println ("����ʵ�Id = " + wordID );
	       
	    }
	
	
	public boolean IfInWordnet(IDictionary dict,String str,POS pos) throws IOException{
		   IIndexWord idxWord=dict.getIndexWord(str, pos);
		   //System.out.println ("�����:" + idxWord);
		   if(!(idxWord==null)){ 
			   return true;
		   }
		   else{
			   return false;
		   } 
	    }
	
	
	
	
	////=========================Ϊ���㷨======================================
	
	
	//��ֱϵ��λ��
	public ISynset getOneHypernyms(IDictionary dict,IWordID wordID1){
		
		//IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
        //IWordID wordID1 = idxWord1.getWordIDs().get(num); 
        IWord word1 = dict.getWord(wordID1); //��ȡ��
        ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
		
		if(!(synset1.getRelatedSynsets(Pointer.HYPERNYM).isEmpty())){
			List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.HYPERNYM);
	        ISynsetID fulei1 = hypernyms1_tamp.get(0);
	        List<IWord> wordlist1 = dict.getSynset(fulei1).getWords();
	        IWord word11 = wordlist1.get(0);
	        ISynset fulei1_synset = word11.getSynset();
	        return fulei1_synset;
		}
		else{
			return null;
		}
	}
	//��ֱϵ��λ��
		public List<ISynset> getOneHyponyms(IDictionary dict,IWordID wordID1){
			
			//IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
	        //IWordID wordID1 = idxWord1.getWordIDs().get(num); 
	        IWord word1 = dict.getWord(wordID1); //��ȡ��
	        ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
			if(!(synset1.getRelatedSynsets(Pointer.HYPONYM).isEmpty())){
				List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.HYPONYM);
				List<ISynset> fuleiSynset = new ArrayList<ISynset>();
				for(int i=0;i<hypernyms1_tamp.size();i++){
				ISynsetID fulei1 = hypernyms1_tamp.get(i);
		        List<IWord> wordlist1 = dict.getSynset(fulei1).getWords();
		        IWord word11 = wordlist1.get(0);
		        ISynset fulei1_synset = word11.getSynset();
		        fuleiSynset.add(fulei1_synset);
				}
		        return fuleiSynset;
			}
			else{
				return null;
			}
		}
		//��ֱϵ��λInstanceOf��
				public ISynset getOneHyperInstance(IDictionary dict,IWordID wordID1){
					
					//IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
			        //IWordID wordID1 = idxWord1.getWordIDs().get(num); 
			        IWord word1 = dict.getWord(wordID1); //��ȡ��
			        ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
					
					if(!(synset1.getRelatedSynsets(Pointer.HYPERNYM_INSTANCE).isEmpty())){
						List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.HYPERNYM_INSTANCE);
				        ISynsetID fulei1 = hypernyms1_tamp.get(0);
				        List<IWord> wordlist1 = dict.getSynset(fulei1).getWords();
				        IWord word11 = wordlist1.get(0);
				        ISynset fulei1_synset = word11.getSynset();
				        return fulei1_synset;
					}
					else{
						return null;
					}
				}
				//��ֱϵ��λInstanceOf��
				public List<ISynset> getOneHypoInstance(IDictionary dict,IWordID wordID1){
					
					//IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
			        //IWordID wordID1 = idxWord1.getWordIDs().get(num); 
			        IWord word1 = dict.getWord(wordID1); //��ȡ��
			        ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
					if(!(synset1.getRelatedSynsets(Pointer.HYPONYM_INSTANCE).isEmpty())){
						List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.HYPONYM_INSTANCE);
						List<ISynset> fuleiSynset = new ArrayList<ISynset>();
						for(int i=0;i<hypernyms1_tamp.size();i++){
						ISynsetID fulei1 = hypernyms1_tamp.get(i);
				        List<IWord> wordlist1 = dict.getSynset(fulei1).getWords();
				        IWord word11 = wordlist1.get(0);
				        ISynset fulei1_synset = word11.getSynset();
				        fuleiSynset.add(fulei1_synset);
						}
				        return fuleiSynset;
					}
					else{
						return null;
					}
				}
				//��ֱϵ��λPartOf1��
				public ISynset getOneHyperPart_1(IDictionary dict,IWordID wordID1){
					
					//IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
			        //IWordID wordID1 = idxWord1.getWordIDs().get(num); 
			        IWord word1 = dict.getWord(wordID1); //��ȡ��
			        ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
					
					if(!(synset1.getRelatedSynsets(Pointer.HOLONYM_PART).isEmpty())){
						List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.HOLONYM_PART);
				        ISynsetID fulei1 = hypernyms1_tamp.get(0);
				        List<IWord> wordlist1 = dict.getSynset(fulei1).getWords();
				        IWord word11 = wordlist1.get(0);
				        ISynset fulei1_synset = word11.getSynset();
				        return fulei1_synset;
					}
					else{
						return null;
					}
				}
				//��ֱϵ��λPartOf1��
				public List<ISynset> getOneHypoPart_1(IDictionary dict,IWordID wordID1){
					
					//IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
			        //IWordID wordID1 = idxWord1.getWordIDs().get(num); 
			        IWord word1 = dict.getWord(wordID1); //��ȡ��
			        ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
					if(!(synset1.getRelatedSynsets(Pointer.MERONYM_PART).isEmpty())){
						List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.MERONYM_PART);
						List<ISynset> fuleiSynset = new ArrayList<ISynset>();
						for(int i=0;i<hypernyms1_tamp.size();i++){
						ISynsetID fulei1 = hypernyms1_tamp.get(i);
				        List<IWord> wordlist1 = dict.getSynset(fulei1).getWords();
				        IWord word11 = wordlist1.get(0);
				        ISynset fulei1_synset = word11.getSynset();
				        fuleiSynset.add(fulei1_synset);
						}
				        return fuleiSynset;
					}
					else{
						return null;
					}
				}
				//��ֱϵ��λPartOf2��
				public ISynset getOneHyperPart_2(IDictionary dict,IWordID wordID1){
					
					//IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
			        //IWordID wordID1 = idxWord1.getWordIDs().get(num); 
			        IWord word1 = dict.getWord(wordID1); //��ȡ��
			        ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
					
					if(!(synset1.getRelatedSynsets(Pointer.HOLONYM_MEMBER).isEmpty())){
						List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.HOLONYM_MEMBER);
				        ISynsetID fulei1 = hypernyms1_tamp.get(0);
				        List<IWord> wordlist1 = dict.getSynset(fulei1).getWords();
				        IWord word11 = wordlist1.get(0);
				        ISynset fulei1_synset = word11.getSynset();
				        return fulei1_synset;
					}
					else{
						return null;
					}
				}
				//��ֱϵ��λPartOf2��
				public List<ISynset> getOneHypoPart_2(IDictionary dict,IWordID wordID1){
					
					//IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
			        //IWordID wordID1 = idxWord1.getWordIDs().get(num); 
			        IWord word1 = dict.getWord(wordID1); //��ȡ��
			        ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
					if(!(synset1.getRelatedSynsets(Pointer.MERONYM_MEMBER).isEmpty())){
						List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.MERONYM_MEMBER);
						List<ISynset> fuleiSynset = new ArrayList<ISynset>();
						for(int i=0;i<hypernyms1_tamp.size();i++){
						ISynsetID fulei1 = hypernyms1_tamp.get(i);
				        List<IWord> wordlist1 = dict.getSynset(fulei1).getWords();
				        IWord word11 = wordlist1.get(0);
				        ISynset fulei1_synset = word11.getSynset();
				        fuleiSynset.add(fulei1_synset);
						}
				        return fuleiSynset;
					}
					else{
						return null;
					}
				}
				//��ֱϵ��λPartOf3��
				public ISynset getOneHyperPart_3(IDictionary dict,IWordID wordID1){
					
					//IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
			        //IWordID wordID1 = idxWord1.getWordIDs().get(num); 
			        IWord word1 = dict.getWord(wordID1); //��ȡ��
			        ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
					
					if(!(synset1.getRelatedSynsets(Pointer.HOLONYM_SUBSTANCE).isEmpty())){
						List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.HOLONYM_SUBSTANCE);
				        ISynsetID fulei1 = hypernyms1_tamp.get(0);
				        List<IWord> wordlist1 = dict.getSynset(fulei1).getWords();
				        IWord word11 = wordlist1.get(0);
				        ISynset fulei1_synset = word11.getSynset();
				        return fulei1_synset;
					}
					else{
						return null;
					}
				}
				//��ֱϵ��λPartOf3��
				public List<ISynset> getOneHypoPart_3(IDictionary dict,IWordID wordID1){
					
					//IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
			        //IWordID wordID1 = idxWord1.getWordIDs().get(num); 
			        IWord word1 = dict.getWord(wordID1); //��ȡ��
			        ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
					if(!(synset1.getRelatedSynsets(Pointer.MERONYM_SUBSTANCE).isEmpty())){
						List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.MERONYM_SUBSTANCE);
						List<ISynset> fuleiSynset = new ArrayList<ISynset>();
						for(int i=0;i<hypernyms1_tamp.size();i++){
						ISynsetID fulei1 = hypernyms1_tamp.get(i);
				        List<IWord> wordlist1 = dict.getSynset(fulei1).getWords();
				        IWord word11 = wordlist1.get(0);
				        ISynset fulei1_synset = word11.getSynset();
				        fuleiSynset.add(fulei1_synset);
						}
				        return fuleiSynset;
					}
					else{
						return null;
					}
				}
				//��ֱϵ��λAttributeOf��
				public List<ISynset> getOneHypoAttribute(IDictionary dict,IWordID wordID1){
					
					//IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
			        //IWordID wordID1 = idxWord1.getWordIDs().get(num); 
			        IWord word1 = dict.getWord(wordID1); //��ȡ��
			        ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
					if(!(synset1.getRelatedSynsets(Pointer.ATTRIBUTE).isEmpty())){
						List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.ATTRIBUTE);
						List<ISynset> fuleiSynset = new ArrayList<ISynset>();
						for(int i=0;i<hypernyms1_tamp.size();i++){
						ISynsetID fulei1 = hypernyms1_tamp.get(i);
				        List<IWord> wordlist1 = dict.getSynset(fulei1).getWords();
				        IWord word11 = wordlist1.get(0);
				        ISynset fulei1_synset = word11.getSynset();
				        fuleiSynset.add(fulei1_synset);
						}
				        return fuleiSynset;
					}
					else{
						return null;
					}
				}
				
				
				
				
				//����λ�ʸ���
				 public int getHyponymsNumber(IDictionary dict,IWordID wordID1){
					 POS pos = POS.NOUN;
					 //IIndexWord idxWord1=dict.getIndexWord(str, pos);
				     //IWordID wordID1 = idxWord1.getWordIDs().get(num); 
				     IWord word1 = dict.getWord(wordID1); //��ȡ��
				     ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
				     if(!(synset1.getRelatedSynsets(Pointer.HYPONYM).isEmpty())){
						List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.HYPONYM);
						return hypernyms1_tamp.size();
						}
						else{
							return 0;
						}
				     }
				 
				 
				 
				//��Instance��λ�ʸ���
				 public int getInstanceHyponymsNumber(IDictionary dict,IWordID wordID1){
					 POS pos = POS.NOUN;
					 //IIndexWord idxWord1=dict.getIndexWord(str, pos);
				     //IWordID wordID1 = idxWord1.getWordIDs().get(num); 
				     IWord word1 = dict.getWord(wordID1); //��ȡ��
				     ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
				     if(!(synset1.getRelatedSynsets(Pointer.HYPONYM_INSTANCE).isEmpty())){
						List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.HYPONYM_INSTANCE);
						return hypernyms1_tamp.size();
						}
						else{
							return 0;
						}
				     }
				 
				 
				//��Part1��λ�ʸ���
				 public int getPart1HyponymsNumber(IDictionary dict,IWordID wordID1){
					 POS pos = POS.NOUN;
					 //IIndexWord idxWord1=dict.getIndexWord(str, pos);
				     //IWordID wordID1 = idxWord1.getWordIDs().get(num); 
				     IWord word1 = dict.getWord(wordID1); //��ȡ��
				     ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
				     if(!(synset1.getRelatedSynsets(Pointer.MERONYM_PART).isEmpty())){
						List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.MERONYM_PART);
						return hypernyms1_tamp.size();
						}
						else{
							return 0;
						}
				     }
				//��Part2��λ�ʸ���
				 public int getPart2HyponymsNumber(IDictionary dict,IWordID wordID1){
					 POS pos = POS.NOUN;
					 //IIndexWord idxWord1=dict.getIndexWord(str, pos);
				     //IWordID wordID1 = idxWord1.getWordIDs().get(num); 
				     IWord word1 = dict.getWord(wordID1); //��ȡ��
				     ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
				     if(!(synset1.getRelatedSynsets(Pointer.MERONYM_MEMBER).isEmpty())){
						List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.MERONYM_MEMBER);
						return hypernyms1_tamp.size();
						}
						else{
							return 0;
						}
				     }
				//��Part3��λ�ʸ���
				 public int getPart3HyponymsNumber(IDictionary dict,IWordID wordID1){
					 POS pos = POS.NOUN;
					 //IIndexWord idxWord1=dict.getIndexWord(str, pos);
				     //IWordID wordID1 = idxWord1.getWordIDs().get(num); 
				     IWord word1 = dict.getWord(wordID1); //��ȡ��
				     ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
				     if(!(synset1.getRelatedSynsets(Pointer.MERONYM_SUBSTANCE).isEmpty())){
						List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.MERONYM_SUBSTANCE);
						return hypernyms1_tamp.size();
						}
						else{
							return 0;
						}
				     }
				 
				 
				 
				//��Attribute��λ�ʸ���
				 public int getAttributeHyponymsNumber(IDictionary dict,IWordID wordID1){
					 POS pos = POS.NOUN;
					 //IIndexWord idxWord1=dict.getIndexWord(str, pos);
				     //IWordID wordID1 = idxWord1.getWordIDs().get(num); 
				     IWord word1 = dict.getWord(wordID1); //��ȡ��
				     ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
				     if(!(synset1.getRelatedSynsets(Pointer.ATTRIBUTE).isEmpty())){
						List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.ATTRIBUTE);
						return hypernyms1_tamp.size();
						}
						else{
							return 0;
						}
				     }
				
				
				
				
				
	//=================================�ҹ�ϵ_�����Ҫ�ķ�������=======================================
				 
			
				 
    //=================================�ҹ�ϵ_�����Ҫ�ķ���=======================================
				 
				 
					
				 
				 
				 
				 
				 
				 
				 
				 
	//=================================�ҹ�ϵ_�����Ҫ�ķ�������=======================================
			 
				 
	

	//�ҵ�������λ��
		//����Pointer�������Լ�https://wenku.baidu.com/view/18c6c532b90d6c85ec3ac6de.html
		public List<ISynset> getHypernyms(IDictionary dict,String str1,POS pos1) throws IOException{
			
			IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
	        IWordID wordID1 = idxWord1.getWordIDs().get(0); 
	        IWord word1 = dict.getWord(wordID1); //��ȡ��
	        ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
	        
	        // ��ȡ��λ�ʼ���
	        List<ISynset> hypernyms1 = new ArrayList<ISynset>();
	        hypernyms1.add(0,synset1);
	        while(!(synset1.getRelatedSynsets(Pointer.HYPERNYM).isEmpty())){
	        List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.HYPERNYM);
	        ISynsetID fulei1 = hypernyms1_tamp.get(0);
	        List<IWord> wordlist1 = dict.getSynset(fulei1).getWords();
	        IWord word11 = wordlist1.get(0);
	        ISynset fulei1_synset = word11.getSynset(); //��ȡ�ô����ڵ�Synset
	        hypernyms1.add(fulei1_synset);
	        synset1 = fulei1_synset;
	        }
	        //System.out.println (str1+"����λ�ʣ�"+hypernyms1);
	        
	        
	        return hypernyms1;
			
		}
	    
	    
	 
	    
	    //�ҵ�������λ��
	    public void gethyponyms(IDictionary dict,String str){
	        //��ȡָ����synset
	        IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//��ȡdog��IndexWord
	        IWordID wordID = idxWord.getWordIDs().get(0); //ȡ����һ������Ĵʵ�ID��
	        IWord word = dict.getWord(wordID); //��ȡ��
	        ISynset synset = word.getSynset(); //��ȡ�ô����ڵ�Synset
	  
	        // ��ȡthyponyms
	        List<ISynsetID> hyponyms =synset.getRelatedSynsets(Pointer.HYPONYM);//ͨ��ָ����������ȡ��صĴʼ�������Pointer����ΪHYPERNYM
	        // print out each hyponyms id and synonyms
	        List <IWord > words ;
	        System.out.println (str+"����λ�ʣ�");
	        for( ISynsetID sid : hyponyms ){
	            words = dict.getSynset(sid).getWords(); //��synset�л�ȡһ��Word��list
	            System.out.print(sid + "{");
	            for( Iterator<IWord > i = words.iterator(); i.hasNext();){
	               System.out.print(i.next().getLemma ());
	               if(i. hasNext ()){
	                   System.out.print(", ");
	               }
	            }
	            System .out . println ("}");
	        }
	          System.out.println("\n");
	     }
	    
	    
	    
	    
	    
	  //�ҵ�������λ��
	  		//����Pointer�������Լ�https://wenku.baidu.com/view/18c6c532b90d6c85ec3ac6de.html
	  		public List<ISynset> getHypernymsInstance(IDictionary dict,String str1,POS pos1) throws IOException{
	  			
	  			IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
	  	        IWordID wordID1 = idxWord1.getWordIDs().get(1); 
	  	        IWord word1 = dict.getWord(wordID1); //��ȡ��
	  	        ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
	  	        
	  	        // ��ȡ��λ�ʼ���
	  	        List<ISynset> hypernyms1 = new ArrayList<ISynset>();
	  	        hypernyms1.add(0,synset1);
	  	        while(!(synset1.getRelatedSynsets(Pointer.HYPERNYM_INSTANCE).isEmpty())){
	  	        List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.HYPERNYM_INSTANCE);
	  	        ISynsetID fulei1 = hypernyms1_tamp.get(0);
	  	        List<IWord> wordlist1 = dict.getSynset(fulei1).getWords();
	  	        IWord word11 = wordlist1.get(0);
	  	        ISynset fulei1_synset = word11.getSynset(); //��ȡ�ô����ڵ�Synset
	  	        hypernyms1.add(fulei1_synset);
	  	        synset1 = fulei1_synset;
	  	        }
	  	        System.out.println (str1+"����λ��instance��"+hypernyms1);
	  	        
	  	        
	  	        return hypernyms1;
	  			
	  		}
		    
		    
		 
		    
		    //�ҵ�������λ��Instance
		    public void gethyponymsInstance(IDictionary dict,String str){
		        //��ȡָ����synset
		        IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//��ȡdog��IndexWord
		        IWordID wordID = idxWord.getWordIDs().get(0); //ȡ����һ������Ĵʵ�ID��
		        IWord word = dict.getWord(wordID); //��ȡ��
		        ISynset synset = word.getSynset(); //��ȡ�ô����ڵ�Synset
		  
		        // ��ȡthyponyms
		        List<ISynsetID> hyponyms =synset.getRelatedSynsets(Pointer.HYPONYM_INSTANCE);//ͨ��ָ����������ȡ��صĴʼ�������Pointer����ΪHYPERNYM
		        // print out each hyponyms id and synonyms
		        List <IWord > words ;
		        System.out.println (str+"����λ��Instance��");
		        for( ISynsetID sid : hyponyms ){
		            words = dict.getSynset(sid).getWords(); //��synset�л�ȡһ��Word��list
		            System.out.print(sid + "{");
		            for( Iterator<IWord > i = words.iterator(); i.hasNext();){
		               System.out.print(i.next().getLemma ());
		               if(i. hasNext ()){
		                   System.out.print(", ");
		               }
		            }
		            System .out . println ("}");
		        }
		          System.out.println("\n");
		     }
	     

	
	/////////////////////////////////////////////////////////////////////////////
	    
	    
	    
	    
	    //SEE ALSO
	    public void getSeeAlso(IDictionary dict,String str){
	        //��ȡָ����synset
	        IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//��ȡdog��IndexWord
	        IWordID wordID = idxWord.getWordIDs().get(0); //ȡ����һ������Ĵʵ�ID��
	        IWord word = dict.getWord(wordID); //��ȡ��
	        ISynset synset = word.getSynset(); //��ȡ�ô����ڵ�Synset
	        
	  
	        // ��ȡhypernyms
	        List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.ALSO_SEE);//ͨ��ָ����������ȡ��صĴʼ�������Pointer����ΪHYPERNYM
	        // print out each hypernyms id and synonyms
	        List <IWord> words ;
	        System.out.println (str+"��see also��");
	        for( ISynsetID sid : hypernyms ){
	            words = dict.getSynset(sid).getWords(); //��synset�л�ȡһ��Word��list
	            System.out.print(sid + "{");
	            for( Iterator<IWord > i = words.iterator(); i.hasNext();){
	               System.out.print(i.next().getLemma ());
	               if(i. hasNext ()){
	                   System.out.print(", ");
	               }
	            }
	            System .out . println ("}");
	        }
	          System.out.println("\n");
	     }
	    
	    
	    
/////////////////////////////////////////////////////////////////////////////
	    
	    
	    
	    
//ANTONYM
public void getAntonym(IDictionary dict,String str){
//��ȡָ����synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//��ȡdog��IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //ȡ����һ������Ĵʵ�ID��
IWord word = dict.getWord(wordID); //��ȡ��
ISynset synset = word.getSynset(); //��ȡ�ô����ڵ�Synset


// ��ȡhypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.ANTONYM);//ͨ��ָ����������ȡ��صĴʼ�������Pointer����ΪHYPERNYM
// print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"��Antonym����ʣ�");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //��synset�л�ȡһ��Word��list
System.out.print(sid + "{");
for( Iterator<IWord > i = words.iterator(); i.hasNext();){
System.out.print(i.next().getLemma ());
if(i. hasNext ()){
System.out.print(", ");
}
}
System .out . println ("}");
}
System.out.println("\n");
}





/////////////////////////////////////////////////////////////////////////////




//ATTRIBUTE
public void getAttribute(IDictionary dict,String str){
//��ȡָ����synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//��ȡdog��IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //ȡ����һ������Ĵʵ�ID��
IWord word = dict.getWord(wordID); //��ȡ��
ISynset synset = word.getSynset(); //��ȡ�ô����ڵ�Synset


//��ȡhypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.ATTRIBUTE);//ͨ��ָ����������ȡ��صĴʼ�������Pointer����ΪHYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"��Attribute���Դʣ�");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //��synset�л�ȡһ��Word��list
System.out.print(sid + "{");
for( Iterator<IWord > i = words.iterator(); i.hasNext();){
System.out.print(i.next().getLemma ());
if(i. hasNext ()){
System.out.print(", ");
}
}
System .out . println ("}");
}
System.out.println("\n");
}




/////////////////////////////////////////////////////////////////////////////




//CAUSE
public void getCause(IDictionary dict,String str){
//��ȡָ����synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//��ȡdog��IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //ȡ����һ������Ĵʵ�ID��
IWord word = dict.getWord(wordID); //��ȡ��
ISynset synset = word.getSynset(); //��ȡ�ô����ڵ�Synset


//��ȡhypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.CAUSE);//ͨ��ָ����������ȡ��صĴʼ�������Pointer����ΪHYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"��Cause��");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //��synset�л�ȡһ��Word��list
System.out.print(sid + "{");
for( Iterator<IWord > i = words.iterator(); i.hasNext();){
System.out.print(i.next().getLemma ());
if(i. hasNext ()){
System.out.print(", ");
}
}
System .out . println ("}");
}
System.out.println("\n");
}





/////////////////////////////////////////////////////////////////////////////




//DERIVATIONALLY_RELATED
public void getDerivationally_related(IDictionary dict,String str){
//��ȡָ����synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//��ȡdog��IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //ȡ����һ������Ĵʵ�ID��
IWord word = dict.getWord(wordID); //��ȡ��
ISynset synset = word.getSynset(); //��ȡ�ô����ڵ�Synset


//��ȡhypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.DERIVATIONALLY_RELATED);//ͨ��ָ����������ȡ��صĴʼ�������Pointer����ΪHYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"��Derivationally_related��");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //��synset�л�ȡһ��Word��list
System.out.print(sid + "{");
for( Iterator<IWord > i = words.iterator(); i.hasNext();){
System.out.print(i.next().getLemma ());
if(i. hasNext ()){
System.out.print(", ");
}
}
System .out . println ("}");
}
System.out.println("\n");
}





/////////////////////////////////////////////////////////////////////////////




//DERIVED_FROM_ADJ
public void getDerivedFromADJ(IDictionary dict,String str){
//��ȡָ����synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//��ȡdog��IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //ȡ����һ������Ĵʵ�ID��
IWord word = dict.getWord(wordID); //��ȡ��
ISynset synset = word.getSynset(); //��ȡ�ô����ڵ�Synset


//��ȡhypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.DERIVED_FROM_ADJ);//ͨ��ָ����������ȡ��صĴʼ�������Pointer����ΪHYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"��DerivedFromADJ��");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //��synset�л�ȡһ��Word��list
System.out.print(sid + "{");
for( Iterator<IWord > i = words.iterator(); i.hasNext();){
System.out.print(i.next().getLemma ());
if(i. hasNext ()){
System.out.print(", ");
}
}
System .out . println ("}");
}
System.out.println("\n");
}





/////////////////////////////////////////////////////////////////////////////




//DOMAIN
public void getDomain(IDictionary dict,String str){
//��ȡָ����synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//��ȡdog��IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //ȡ����һ������Ĵʵ�ID��
IWord word = dict.getWord(wordID); //��ȡ��
ISynset synset = word.getSynset(); //��ȡ�ô����ڵ�Synset


//��ȡhypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.DOMAIN);//ͨ��ָ����������ȡ��صĴʼ�������Pointer����ΪHYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"��Domain��");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //��synset�л�ȡһ��Word��list
System.out.print(sid + "{");
for( Iterator<IWord > i = words.iterator(); i.hasNext();){
System.out.print(i.next().getLemma ());
if(i. hasNext ()){
System.out.print(", ");
}
}
System .out . println ("}");
}
System.out.println("\n");
}





/////////////////////////////////////////////////////////////////////////////




//ENTAILMENT
public void getEntailment(IDictionary dict,String str){
//��ȡָ����synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//��ȡdog��IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //ȡ����һ������Ĵʵ�ID��
IWord word = dict.getWord(wordID); //��ȡ��
ISynset synset = word.getSynset(); //��ȡ�ô����ڵ�Synset


//��ȡhypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.ENTAILMENT);//ͨ��ָ����������ȡ��صĴʼ�������Pointer����ΪHYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"��Entailment�̺��ʣ�");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //��synset�л�ȡһ��Word��list
System.out.print(sid + "{");
for( Iterator<IWord > i = words.iterator(); i.hasNext();){
System.out.print(i.next().getLemma ());
if(i. hasNext ()){
System.out.print(", ");
}
}
System .out . println ("}");
}
System.out.println("\n");
}






/////////////////////////////////////////////////////////////////////////////




//HOLONYM_MEMBER
public void getHolonymMember(IDictionary dict,String str){
//��ȡָ����synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//��ȡdog��IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //ȡ����һ������Ĵʵ�ID��
IWord word = dict.getWord(wordID); //��ȡ��
ISynset synset = word.getSynset(); //��ȡ�ô����ڵ�Synset


//��ȡhypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.HOLONYM_MEMBER);//ͨ��ָ����������ȡ��صĴʼ�������Pointer����ΪHYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"��HolonymMember���岿�֣�");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //��synset�л�ȡһ��Word��list
System.out.print(sid + "{");
for( Iterator<IWord > i = words.iterator(); i.hasNext();){
System.out.print(i.next().getLemma ());
if(i. hasNext ()){
System.out.print(", ");
}
}
System .out . println ("}");
}
System.out.println("\n");
}





/////////////////////////////////////////////////////////////////////////////




//HOLONYM_PART
public void getHolonymPart(IDictionary dict,String str){
//��ȡָ����synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//��ȡdog��IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //ȡ����һ������Ĵʵ�ID��
IWord word = dict.getWord(wordID); //��ȡ��
ISynset synset = word.getSynset(); //��ȡ�ô����ڵ�Synset


//��ȡhypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.HOLONYM_PART);//ͨ��ָ����������ȡ��صĴʼ�������Pointer����ΪHYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"��HolonymPart���岿�֣�");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //��synset�л�ȡһ��Word��list
System.out.print(sid + "{");
for( Iterator<IWord > i = words.iterator(); i.hasNext();){
System.out.print(i.next().getLemma ());
if(i. hasNext ()){
System.out.print(", ");
}
}
System .out . println ("}");
}
System.out.println("\n");
}





/////////////////////////////////////////////////////////////////////////////




//HOLONYM_SUBSTANCE
public void getHolonymSubstance(IDictionary dict,String str){
//��ȡָ����synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//��ȡdog��IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //ȡ����һ������Ĵʵ�ID��
IWord word = dict.getWord(wordID); //��ȡ��
ISynset synset = word.getSynset(); //��ȡ�ô����ڵ�Synset


//��ȡhypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.HOLONYM_SUBSTANCE);//ͨ��ָ����������ȡ��صĴʼ�������Pointer����ΪHYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"��HolonymSubstance���岿�֣�");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //��synset�л�ȡһ��Word��list
System.out.print(sid + "{");
for( Iterator<IWord > i = words.iterator(); i.hasNext();){
System.out.print(i.next().getLemma ());
if(i. hasNext ()){
System.out.print(", ");
}
}
System .out . println ("}");
}
System.out.println("\n");
}






/////////////////////////////////////////////////////////////////////////////




//MEMBER
public void getMember(IDictionary dict,String str){
//��ȡָ����synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//��ȡdog��IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //ȡ����һ������Ĵʵ�ID��
IWord word = dict.getWord(wordID); //��ȡ��
ISynset synset = word.getSynset(); //��ȡ�ô����ڵ�Synset


//��ȡhypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.MEMBER);//ͨ��ָ����������ȡ��صĴʼ�������Pointer����ΪHYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"��member��");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //��synset�л�ȡһ��Word��list
System.out.print(sid + "{");
for( Iterator<IWord > i = words.iterator(); i.hasNext();){
System.out.print(i.next().getLemma ());
if(i. hasNext ()){
System.out.print(", ");
}
}
System .out . println ("}");
}
System.out.println("\n");
}






/////////////////////////////////////////////////////////////////////////////




//MERONYM_MEMBER
public void getMeronymMember(IDictionary dict,String str){
//��ȡָ����synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//��ȡdog��IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //ȡ����һ������Ĵʵ�ID��
IWord word = dict.getWord(wordID); //��ȡ��
ISynset synset = word.getSynset(); //��ȡ�ô����ڵ�Synset


//��ȡhypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.MERONYM_MEMBER);//ͨ��ָ����������ȡ��صĴʼ�������Pointer����ΪHYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"��MeronymMember��");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //��synset�л�ȡһ��Word��list
System.out.print(sid + "{");
for( Iterator<IWord > i = words.iterator(); i.hasNext();){
System.out.print(i.next().getLemma ());
if(i. hasNext ()){
System.out.print(", ");
}
}
System .out . println ("}");
}
System.out.println("\n");
}






/////////////////////////////////////////////////////////////////////////////




//MERONYM_PART
public void getMeronymPart(IDictionary dict,String str){
//��ȡָ����synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//��ȡdog��IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //ȡ����һ������Ĵʵ�ID��
IWord word = dict.getWord(wordID); //��ȡ��
ISynset synset = word.getSynset(); //��ȡ�ô����ڵ�Synset


//��ȡhypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.MERONYM_PART);//ͨ��ָ����������ȡ��صĴʼ�������Pointer����ΪHYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"��MeronymPart��");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //��synset�л�ȡһ��Word��list
System.out.print(sid + "{");
for( Iterator<IWord > i = words.iterator(); i.hasNext();){
System.out.print(i.next().getLemma ());
if(i. hasNext ()){
System.out.print(", ");
}
}
System .out . println ("}");
}
System.out.println("\n");
}






/////////////////////////////////////////////////////////////////////////////




//MERONYM_SUBSTANCE
public void getMeronymSubstance(IDictionary dict,String str){
//��ȡָ����synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//��ȡdog��IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //ȡ����һ������Ĵʵ�ID��
IWord word = dict.getWord(wordID); //��ȡ��
ISynset synset = word.getSynset(); //��ȡ�ô����ڵ�Synset


//��ȡhypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.MERONYM_SUBSTANCE);//ͨ��ָ����������ȡ��صĴʼ�������Pointer����ΪHYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"��MeronymSubstance��");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //��synset�л�ȡһ��Word��list
System.out.print(sid + "{");
for( Iterator<IWord > i = words.iterator(); i.hasNext();){
System.out.print(i.next().getLemma ());
if(i. hasNext ()){
System.out.print(", ");
}
}
System .out . println ("}");
}
System.out.println("\n");
}







/////////////////////////////////////////////////////////////////////////////




//PARTICIPLE
public void getParticiple(IDictionary dict,String str){
//��ȡָ����synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//��ȡdog��IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //ȡ����һ������Ĵʵ�ID��
IWord word = dict.getWord(wordID); //��ȡ��
ISynset synset = word.getSynset(); //��ȡ�ô����ڵ�Synset


//��ȡhypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.PARTICIPLE);//ͨ��ָ����������ȡ��صĴʼ�������Pointer����ΪHYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"��Participle��");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //��synset�л�ȡһ��Word��list
System.out.print(sid + "{");
for( Iterator<IWord > i = words.iterator(); i.hasNext();){
System.out.print(i.next().getLemma ());
if(i. hasNext ()){
System.out.print(", ");
}
}
System .out . println ("}");
}
System.out.println("\n");
}






/////////////////////////////////////////////////////////////////////////////




//PERTAINYM
public void getPertainym(IDictionary dict,String str){
//��ȡָ����synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//��ȡdog��IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //ȡ����һ������Ĵʵ�ID��
IWord word = dict.getWord(wordID); //��ȡ��
ISynset synset = word.getSynset(); //��ȡ�ô����ڵ�Synset


//��ȡhypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.PERTAINYM);//ͨ��ָ����������ȡ��صĴʼ�������Pointer����ΪHYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"��Pertainym��");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //��synset�л�ȡһ��Word��list
System.out.print(sid + "{");
for( Iterator<IWord > i = words.iterator(); i.hasNext();){
System.out.print(i.next().getLemma ());
if(i. hasNext ()){
System.out.print(", ");
}
}
System .out . println ("}");
}
System.out.println("\n");
}






/////////////////////////////////////////////////////////////////////////////




//REGION
public void getRegion(IDictionary dict,String str){
//��ȡָ����synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//��ȡdog��IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //ȡ����һ������Ĵʵ�ID��
IWord word = dict.getWord(wordID); //��ȡ��
ISynset synset = word.getSynset(); //��ȡ�ô����ڵ�Synset


//��ȡhypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.REGION);//ͨ��ָ����������ȡ��صĴʼ�������Pointer����ΪHYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"��Region����");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //��synset�л�ȡһ��Word��list
System.out.print(sid + "{");
for( Iterator<IWord > i = words.iterator(); i.hasNext();){
System.out.print(i.next().getLemma ());
if(i. hasNext ()){
System.out.print(", ");
}
}
System .out . println ("}");
}
System.out.println("\n");
}






/////////////////////////////////////////////////////////////////////////////




//REGION_MEMBER
public void getRegionMember(IDictionary dict,String str){
//��ȡָ����synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//��ȡdog��IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //ȡ����һ������Ĵʵ�ID��
IWord word = dict.getWord(wordID); //��ȡ��
ISynset synset = word.getSynset(); //��ȡ�ô����ڵ�Synset


//��ȡhypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.REGION_MEMBER);//ͨ��ָ����������ȡ��صĴʼ�������Pointer����ΪHYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"��RegionMember��");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //��synset�л�ȡһ��Word��list
System.out.print(sid + "{");
for( Iterator<IWord > i = words.iterator(); i.hasNext();){
System.out.print(i.next().getLemma ());
if(i. hasNext ()){
System.out.print(", ");
}
}
System .out . println ("}");
}
System.out.println("\n");
}






/////////////////////////////////////////////////////////////////////////////




//SIMILAR_TO
public void getSimilarTo(IDictionary dict,String str){
//��ȡָ����synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//��ȡdog��IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //ȡ����һ������Ĵʵ�ID��
IWord word = dict.getWord(wordID); //��ȡ��
ISynset synset = word.getSynset(); //��ȡ�ô����ڵ�Synset


//��ȡhypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.SIMILAR_TO);//ͨ��ָ����������ȡ��صĴʼ�������Pointer����ΪHYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"��SimilarTo��");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //��synset�л�ȡһ��Word��list
System.out.print(sid + "{");
for( Iterator<IWord > i = words.iterator(); i.hasNext();){
System.out.print(i.next().getLemma ());
if(i. hasNext ()){
System.out.print(", ");
}
}
System .out . println ("}");
}
System.out.println("\n");
}






/////////////////////////////////////////////////////////////////////////////




//TOPIC
public void getTopic(IDictionary dict,String str){
//��ȡָ����synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//��ȡdog��IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //ȡ����һ������Ĵʵ�ID��
IWord word = dict.getWord(wordID); //��ȡ��
ISynset synset = word.getSynset(); //��ȡ�ô����ڵ�Synset


//��ȡhypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.TOPIC);//ͨ��ָ����������ȡ��صĴʼ�������Pointer����ΪHYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"��Topic��");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //��synset�л�ȡһ��Word��list
System.out.print(sid + "{");
for( Iterator<IWord > i = words.iterator(); i.hasNext();){
System.out.print(i.next().getLemma ());
if(i. hasNext ()){
System.out.print(", ");
}
}
System .out . println ("}");
}
System.out.println("\n");
}






/////////////////////////////////////////////////////////////////////////////




//TOPIC_MEMBER
public void getTopicMmeber(IDictionary dict,String str){
//��ȡָ����synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//��ȡdog��IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //ȡ����һ������Ĵʵ�ID��
IWord word = dict.getWord(wordID); //��ȡ��
ISynset synset = word.getSynset(); //��ȡ�ô����ڵ�Synset


//��ȡhypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.TOPIC_MEMBER);//ͨ��ָ����������ȡ��صĴʼ�������Pointer����ΪHYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"��TopicMmeber��");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //��synset�л�ȡһ��Word��list
System.out.print(sid + "{");
for( Iterator<IWord > i = words.iterator(); i.hasNext();){
System.out.print(i.next().getLemma ());
if(i. hasNext ()){
System.out.print(", ");
}
}
System .out . println ("}");
}
System.out.println("\n");
}





/////////////////////////////////////////////////////////////////////////////




//USAGE
public void getUsage(IDictionary dict,String str){
//��ȡָ����synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//��ȡdog��IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //ȡ����һ������Ĵʵ�ID��
IWord word = dict.getWord(wordID); //��ȡ��
ISynset synset = word.getSynset(); //��ȡ�ô����ڵ�Synset


//��ȡhypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.USAGE);//ͨ��ָ����������ȡ��صĴʼ�������Pointer����ΪHYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"��Usage��");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //��synset�л�ȡһ��Word��list
System.out.print(sid + "{");
for( Iterator<IWord > i = words.iterator(); i.hasNext();){
System.out.print(i.next().getLemma ());
if(i. hasNext ()){
System.out.print(", ");
}
}
System .out . println ("}");
}
System.out.println("\n");
}





/////////////////////////////////////////////////////////////////////////////




//USAGE_MEMBER
public void getUsageMmebe(IDictionary dict,String str){
//��ȡָ����synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//��ȡdog��IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //ȡ����һ������Ĵʵ�ID��
IWord word = dict.getWord(wordID); //��ȡ��
ISynset synset = word.getSynset(); //��ȡ�ô����ڵ�Synset


//��ȡhypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.USAGE_MEMBER);//ͨ��ָ����������ȡ��صĴʼ�������Pointer����ΪHYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"��UsageMmebe��");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //��synset�л�ȡһ��Word��list
System.out.print(sid + "{");
for( Iterator<IWord > i = words.iterator(); i.hasNext();){
System.out.print(i.next().getLemma ());
if(i. hasNext ()){
System.out.print(", ");
}
}
System .out . println ("}");
}
System.out.println("\n");
}






/////////////////////////////////////////////////////////////////////////////




//VERB_GROUP
public void getVerbGroup(IDictionary dict,String str){
//��ȡָ����synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//��ȡdog��IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //ȡ����һ������Ĵʵ�ID��
IWord word = dict.getWord(wordID); //��ȡ��
ISynset synset = word.getSynset(); //��ȡ�ô����ڵ�Synset


//��ȡhypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.VERB_GROUP);//ͨ��ָ����������ȡ��صĴʼ�������Pointer����ΪHYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"��VerbGroup��");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //��synset�л�ȡһ��Word��list
System.out.print(sid + "{");
for( Iterator<IWord > i = words.iterator(); i.hasNext();){
System.out.print(i.next().getLemma ());
if(i. hasNext ()){
System.out.print(", ");
}
}
System .out . println ("}");
}
System.out.println("\n");
}




//==============================================================================//







//�ҵ�������λ��
	//����Pointer�������Լ�https://wenku.baidu.com/view/18c6c532b90d6c85ec3ac6de.html
	public List<ISynset> FindAllFather(IDictionary dict,String str1,POS pos1,int num1) throws IOException{
		
		IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
      IWordID wordID1 = idxWord1.getWordIDs().get(num1); 
      IWord word1 = dict.getWord(wordID1); //��ȡ��
      ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
      
      // ��ȡ��λ�ʼ���
      List<ISynset> hypernyms1 = new ArrayList<ISynset>();
      hypernyms1.add(0,synset1);
      while(!(synset1.getRelatedSynsets(Pointer.HYPERNYM).isEmpty())){
      List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.HYPERNYM);
      ISynsetID fulei1 = hypernyms1_tamp.get(0);
      List<IWord> wordlist1 = dict.getSynset(fulei1).getWords();
      IWord word11 = wordlist1.get(0);
      ISynset fulei1_synset = word11.getSynset(); //��ȡ�ô����ڵ�Synset
      hypernyms1.add(fulei1_synset);
      synset1 = fulei1_synset;
      }
      //System.out.println (str1+"����λ�ʼ��ϣ�"+hypernyms1);
      
      
      return hypernyms1;
		
	}
	
	
	
	
	
	
	public List<ISynset> FindAllPath_Two(List<ISynset> hypernyms1,List<ISynset> hypernyms2) throws IOException{

		   //�����������λ��
		    List<ISynset> hypernyms_temp = new ArrayList<ISynset>(hypernyms1);
		    hypernyms_temp.retainAll(hypernyms2);
		    
		    List<ISynset> hypernyms1_new = new ArrayList<ISynset>();
		    //��ֹ�Ҳ�������
		    if(hypernyms_temp.size()==0){
		    	hypernyms1_new.add(hypernyms1.get(0)); 
		    	return hypernyms1_new;
		    }
	        ISynset SameFather = hypernyms_temp.get(0);
	        //System.out.println ("���������λ�ʣ�"+SameFather);
	        
	        
	        //��������е�֦
	        int i=0;
	        for(i=0;i<hypernyms1.size();i++){
	        	if(SameFather!=hypernyms1.get(i)){
	        		hypernyms1_new.add(hypernyms1.get(i));
	        	}
	        	else{  
	        		hypernyms1_new.add(hypernyms1.get(i));
	        		break;  
	        	}
	        }
	        //System.out.println ("�޼���һ֦��"+hypernyms1_new);
	        
	        List<ISynset> hypernyms2_new = new ArrayList<ISynset>();
	        i=0;
	        for(i=0;i<hypernyms2.size();i++){
	        	if(SameFather!=hypernyms2.get(i)){
	        		hypernyms2_new.add(hypernyms2.get(i));
	        	}
	        	else{  
	        		hypernyms2_new.add(hypernyms2.get(i));
	        		break;  
	        	}
	        }
	        //System.out.println ("�޼��ڶ�֦��"+hypernyms2_new);
	        
	       
	      //ȥ���ظ���·��
		   CutRepetition cut = new CutRepetition();
		   cut.CutRepetition_Two(hypernyms1_new, hypernyms2_new);
		   //System.out.println ("�޼���һ֦��"+hypernyms1_new);
		   //System.out.println ("�޼��ڶ�֦��"+hypernyms2_new);
		   
		   
		   //��������·��
		   if(hypernyms2_new.size()==1){    }
		   else{
			   for(int m=1;m<=hypernyms2_new.size()-1;m++){
				   hypernyms1_new.add(hypernyms2_new.get(hypernyms2_new.size()-m-1));
			   }
		   }
		   //System.out.println ("Is-a���Ϻ�"+hypernyms1_new);

	       return hypernyms1_new;
   
	    }
	
	
	
	
	
	
	public void WriteFile(String file, String conent) {   
        BufferedWriter out = null;   
        try {   
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));   
            out.write(conent);   
        } catch (Exception e) {   
            e.printStackTrace();   
        } finally {   
            try {   
            if(out != null){
            out.close();   
                }
            } catch (IOException e) {   
                e.printStackTrace();   
            }   
        }   
    } 
	
	
	
	
	
	
	
	
	
	//===================InstanceOf============================================
	
	
	
	
public List<ISynset> FindAllFather_InstanceOf(IDictionary dict,String str1,POS pos1,int num1) throws IOException{
		
		IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
      IWordID wordID1 = idxWord1.getWordIDs().get(num1); 
      IWord word1 = dict.getWord(wordID1); //��ȡ��
      ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
      
      // ��ȡ��λ�ʼ���
      List<ISynset> hypernyms1 = new ArrayList<ISynset>();
      hypernyms1.add(0,synset1);
      while(!(synset1.getRelatedSynsets(Pointer.HYPERNYM_INSTANCE).isEmpty())){
      List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.HYPERNYM_INSTANCE);
      ISynsetID fulei1 = hypernyms1_tamp.get(0);
      List<IWord> wordlist1 = dict.getSynset(fulei1).getWords();
      IWord word11 = wordlist1.get(0);
      ISynset fulei1_synset = word11.getSynset(); //��ȡ�ô����ڵ�Synset
      hypernyms1.add(fulei1_synset);
      synset1 = fulei1_synset;
      }
      //System.out.println (str1+"��instance��λ���ϣ�"+hypernyms1);
      
      
      return hypernyms1;
		
	}




public List<ISynset> FindAllPath_Two_InstanceOf(List<ISynset> hypernyms1,List<ISynset> hypernyms2) throws IOException{

	   //�����������λ��
	    List<ISynset> hypernyms_temp = new ArrayList<ISynset>(hypernyms1);
	    hypernyms_temp.retainAll(hypernyms2);
	    
	    List<ISynset> hypernyms1_new = new ArrayList<ISynset>();
	    //��ֹ�Ҳ�������
	    if(hypernyms_temp.size()==0){
	    	hypernyms1_new.add(hypernyms1.get(0));
	    	return hypernyms1_new;
	    }
     ISynset SameFather = hypernyms_temp.get(0);
     //System.out.println ("���������λ�ʣ�"+SameFather);
     
     
     //��������е�֦
     int i=0;
     for(i=0;i<hypernyms1.size();i++){
     	if(SameFather!=hypernyms1.get(i)){
     		hypernyms1_new.add(hypernyms1.get(i));
     	}
     	else{  
     		hypernyms1_new.add(hypernyms1.get(i));
     		break;  
     	}
     }
     //System.out.println ("�޼���һ֦��"+hypernyms1_new);
     
     List<ISynset> hypernyms2_new = new ArrayList<ISynset>();
     i=0;
     for(i=0;i<hypernyms2.size();i++){
     	if(SameFather!=hypernyms2.get(i)){
     		hypernyms2_new.add(hypernyms2.get(i));
     	}
     	else{  
     		hypernyms2_new.add(hypernyms2.get(i));
     		break;  
     	}
     }
     //System.out.println ("�޼��ڶ�֦��"+hypernyms2_new);
     
    
   //ȥ���ظ���·��
	   CutRepetition cut = new CutRepetition();
	   cut.CutRepetition_Two(hypernyms1_new, hypernyms2_new);
	   //System.out.println ("�޼���һ֦��"+hypernyms1_new);
	   //System.out.println ("�޼��ڶ�֦��"+hypernyms2_new);
	   
	   
	   //��������·��
	   if(hypernyms2_new.size()==1){    }
	   else{
		   for(int m=1;m<=hypernyms2_new.size()-1;m++){
			   hypernyms1_new.add(hypernyms2_new.get(hypernyms2_new.size()-m-1));
		   }
	   }
	   //System.out.println ("InstanceOf���Ϻ�"+hypernyms1_new);

    return hypernyms1_new;

 }






//====================PartOf====================================================





public List<ISynset> FindAllFather_PartOf1(IDictionary dict,String str1,POS pos1,int num1) throws IOException{
	
	IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
  IWordID wordID1 = idxWord1.getWordIDs().get(num1); 
  IWord word1 = dict.getWord(wordID1); //��ȡ��
  ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
  
  // ��ȡ��λ�ʼ���
  List<ISynset> hypernyms1 = new ArrayList<ISynset>();
  hypernyms1.add(0,synset1);
  while(!(synset1.getRelatedSynsets(Pointer.HOLONYM_PART).isEmpty())){
  List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.HOLONYM_PART);
  ISynsetID fulei1 = hypernyms1_tamp.get(0);
  List<IWord> wordlist1 = dict.getSynset(fulei1).getWords();
  IWord word11 = wordlist1.get(0);
  ISynset fulei1_synset = word11.getSynset(); //��ȡ�ô����ڵ�Synset
  hypernyms1.add(fulei1_synset);
  synset1 = fulei1_synset;
  }
  //System.out.println (str1+"��part_part��λ���ϣ�"+hypernyms1);
  
  
  return hypernyms1;
	
}
public List<ISynset> FindAllFather_PartOf2(IDictionary dict,String str1,POS pos1,int num1) throws IOException{
	
	IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
  IWordID wordID1 = idxWord1.getWordIDs().get(num1); 
  IWord word1 = dict.getWord(wordID1); //��ȡ��
  ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
  
  // ��ȡ��λ�ʼ���
  List<ISynset> hypernyms1 = new ArrayList<ISynset>();
  hypernyms1.add(0,synset1);
  while(!(synset1.getRelatedSynsets(Pointer.HOLONYM_MEMBER).isEmpty())){
  List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.HOLONYM_MEMBER);
  ISynsetID fulei1 = hypernyms1_tamp.get(0);
  List<IWord> wordlist1 = dict.getSynset(fulei1).getWords();
  IWord word11 = wordlist1.get(0);
  ISynset fulei1_synset = word11.getSynset(); //��ȡ�ô����ڵ�Synset
  hypernyms1.add(fulei1_synset);
  synset1 = fulei1_synset;
  }
  //System.out.println (str1+"��part_member��λ���ϣ�"+hypernyms1);
  
  
  return hypernyms1;
	
}
public List<ISynset> FindAllFather_PartOf3(IDictionary dict,String str1,POS pos1,int num1) throws IOException{
	
	IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
  IWordID wordID1 = idxWord1.getWordIDs().get(num1); 
  IWord word1 = dict.getWord(wordID1); //��ȡ��
  ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
  
  // ��ȡ��λ�ʼ���
  List<ISynset> hypernyms1 = new ArrayList<ISynset>();
  hypernyms1.add(0,synset1);
  while(!(synset1.getRelatedSynsets(Pointer.HOLONYM_SUBSTANCE).isEmpty())){
  List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.HOLONYM_SUBSTANCE);
  ISynsetID fulei1 = hypernyms1_tamp.get(0);
  List<IWord> wordlist1 = dict.getSynset(fulei1).getWords();
  IWord word11 = wordlist1.get(0);
  ISynset fulei1_synset = word11.getSynset(); //��ȡ�ô����ڵ�Synset
  hypernyms1.add(fulei1_synset);
  synset1 = fulei1_synset;
  }
  //System.out.println (str1+"��part_substance��λ���ϣ�"+hypernyms1);
  
  
  return hypernyms1;
	
}





public List<ISynset> FindAllPath_Two_PartOf(List<ISynset> hypernyms1,List<ISynset> hypernyms2) throws IOException{

	   //�����������λ��
	    List<ISynset> hypernyms_temp = new ArrayList<ISynset>(hypernyms1);
	    hypernyms_temp.retainAll(hypernyms2);
	    
	    List<ISynset> hypernyms1_new = new ArrayList<ISynset>();
	    //��ֹ�Ҳ�������
	    if(hypernyms_temp.size()==0){
	    	hypernyms1_new.add(hypernyms1.get(0));
	    	return hypernyms1_new;
	    }
  ISynset SameFather = hypernyms_temp.get(0);
  //System.out.println ("���������λ�ʣ�"+SameFather);
  
  
  //��������е�֦
  int i=0;
  for(i=0;i<hypernyms1.size();i++){
  	if(SameFather!=hypernyms1.get(i)){
  		hypernyms1_new.add(hypernyms1.get(i));
  	}
  	else{  
  		hypernyms1_new.add(hypernyms1.get(i));
  		break;  
  	}
  }
  //System.out.println ("�޼���һ֦��"+hypernyms1_new);
  
  List<ISynset> hypernyms2_new = new ArrayList<ISynset>();
  i=0;
  for(i=0;i<hypernyms2.size();i++){
  	if(SameFather!=hypernyms2.get(i)){
  		hypernyms2_new.add(hypernyms2.get(i));
  	}
  	else{  
  		hypernyms2_new.add(hypernyms2.get(i));
  		break;  
  	}
  }
  //System.out.println ("�޼��ڶ�֦��"+hypernyms2_new);
  
 
//ȥ���ظ���·��
	   CutRepetition cut = new CutRepetition();
	   cut.CutRepetition_Two(hypernyms1_new, hypernyms2_new);
	   //System.out.println ("�޼���һ֦��"+hypernyms1_new);
	   //System.out.println ("�޼��ڶ�֦��"+hypernyms2_new);
	   
	   
	   //��������·��
	   if(hypernyms2_new.size()==1){    }
	   else{
		   for(int m=1;m<=hypernyms2_new.size()-1;m++){
			   hypernyms1_new.add(hypernyms2_new.get(hypernyms2_new.size()-m-1));
		   }
	   }
	   //System.out.println ("PartOf���Ϻ�"+hypernyms1_new);

 return hypernyms1_new;

}







//===================AttributeOf=================================================

	


public List<ISynset> FindAllFather_AttributeOf(IDictionary dict,String str1,POS pos1,int num1) throws IOException{
	
	IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
    IWordID wordID1 = idxWord1.getWordIDs().get(num1); 
    IWord word1 = dict.getWord(wordID1); //��ȡ��
    ISynset synset1 = word1.getSynset(); //��ȡ�ô����ڵ�Synset
  
    // ��ȡ��λ�ʼ���
    List<ISynset> hypernyms1 = new ArrayList<ISynset>();
    hypernyms1.add(0,synset1);
    if(!(synset1.getRelatedSynsets(Pointer.ATTRIBUTE).isEmpty())){
    List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.ATTRIBUTE);
    ISynsetID fulei1 = hypernyms1_tamp.get(0);
    List<IWord> wordlist1 = dict.getSynset(fulei1).getWords();
    IWord word11 = wordlist1.get(0);
    ISynset fulei1_synset = word11.getSynset(); //��ȡ�ô����ڵ�Synset
    hypernyms1.add(fulei1_synset);
    }
    else{    }
    //System.out.println (str1+"��Attribute���ϣ�"+hypernyms1);
  
  
  return hypernyms1;
	
}











	    
	
	
	
}
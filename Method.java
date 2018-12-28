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
	       IWordID wordID=idxWord.getWordIDs().get(0);//获取dog第一个词义ID
	       //System.out.println ("所查词的Id = " + wordID );
	       
	    }
	
	
	public boolean IfInWordnet(IDictionary dict,String str,POS pos) throws IOException{
		   IIndexWord idxWord=dict.getIndexWord(str, pos);
		   //System.out.println ("所查词:" + idxWord);
		   if(!(idxWord==null)){ 
			   return true;
		   }
		   else{
			   return false;
		   } 
	    }
	
	
	
	
	////=========================为了算法======================================
	
	
	//找直系上位词
	public ISynset getOneHypernyms(IDictionary dict,IWordID wordID1){
		
		//IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
        //IWordID wordID1 = idxWord1.getWordIDs().get(num); 
        IWord word1 = dict.getWord(wordID1); //获取词
        ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
		
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
	//找直系下位词
		public List<ISynset> getOneHyponyms(IDictionary dict,IWordID wordID1){
			
			//IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
	        //IWordID wordID1 = idxWord1.getWordIDs().get(num); 
	        IWord word1 = dict.getWord(wordID1); //获取词
	        ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
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
		//找直系上位InstanceOf词
				public ISynset getOneHyperInstance(IDictionary dict,IWordID wordID1){
					
					//IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
			        //IWordID wordID1 = idxWord1.getWordIDs().get(num); 
			        IWord word1 = dict.getWord(wordID1); //获取词
			        ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
					
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
				//找直系下位InstanceOf词
				public List<ISynset> getOneHypoInstance(IDictionary dict,IWordID wordID1){
					
					//IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
			        //IWordID wordID1 = idxWord1.getWordIDs().get(num); 
			        IWord word1 = dict.getWord(wordID1); //获取词
			        ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
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
				//找直系上位PartOf1词
				public ISynset getOneHyperPart_1(IDictionary dict,IWordID wordID1){
					
					//IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
			        //IWordID wordID1 = idxWord1.getWordIDs().get(num); 
			        IWord word1 = dict.getWord(wordID1); //获取词
			        ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
					
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
				//找直系下位PartOf1词
				public List<ISynset> getOneHypoPart_1(IDictionary dict,IWordID wordID1){
					
					//IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
			        //IWordID wordID1 = idxWord1.getWordIDs().get(num); 
			        IWord word1 = dict.getWord(wordID1); //获取词
			        ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
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
				//找直系上位PartOf2词
				public ISynset getOneHyperPart_2(IDictionary dict,IWordID wordID1){
					
					//IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
			        //IWordID wordID1 = idxWord1.getWordIDs().get(num); 
			        IWord word1 = dict.getWord(wordID1); //获取词
			        ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
					
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
				//找直系下位PartOf2词
				public List<ISynset> getOneHypoPart_2(IDictionary dict,IWordID wordID1){
					
					//IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
			        //IWordID wordID1 = idxWord1.getWordIDs().get(num); 
			        IWord word1 = dict.getWord(wordID1); //获取词
			        ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
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
				//找直系上位PartOf3词
				public ISynset getOneHyperPart_3(IDictionary dict,IWordID wordID1){
					
					//IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
			        //IWordID wordID1 = idxWord1.getWordIDs().get(num); 
			        IWord word1 = dict.getWord(wordID1); //获取词
			        ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
					
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
				//找直系下位PartOf3词
				public List<ISynset> getOneHypoPart_3(IDictionary dict,IWordID wordID1){
					
					//IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
			        //IWordID wordID1 = idxWord1.getWordIDs().get(num); 
			        IWord word1 = dict.getWord(wordID1); //获取词
			        ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
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
				//找直系下位AttributeOf词
				public List<ISynset> getOneHypoAttribute(IDictionary dict,IWordID wordID1){
					
					//IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
			        //IWordID wordID1 = idxWord1.getWordIDs().get(num); 
			        IWord word1 = dict.getWord(wordID1); //获取词
			        ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
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
				
				
				
				
				//找下位词个数
				 public int getHyponymsNumber(IDictionary dict,IWordID wordID1){
					 POS pos = POS.NOUN;
					 //IIndexWord idxWord1=dict.getIndexWord(str, pos);
				     //IWordID wordID1 = idxWord1.getWordIDs().get(num); 
				     IWord word1 = dict.getWord(wordID1); //获取词
				     ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
				     if(!(synset1.getRelatedSynsets(Pointer.HYPONYM).isEmpty())){
						List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.HYPONYM);
						return hypernyms1_tamp.size();
						}
						else{
							return 0;
						}
				     }
				 
				 
				 
				//找Instance下位词个数
				 public int getInstanceHyponymsNumber(IDictionary dict,IWordID wordID1){
					 POS pos = POS.NOUN;
					 //IIndexWord idxWord1=dict.getIndexWord(str, pos);
				     //IWordID wordID1 = idxWord1.getWordIDs().get(num); 
				     IWord word1 = dict.getWord(wordID1); //获取词
				     ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
				     if(!(synset1.getRelatedSynsets(Pointer.HYPONYM_INSTANCE).isEmpty())){
						List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.HYPONYM_INSTANCE);
						return hypernyms1_tamp.size();
						}
						else{
							return 0;
						}
				     }
				 
				 
				//找Part1下位词个数
				 public int getPart1HyponymsNumber(IDictionary dict,IWordID wordID1){
					 POS pos = POS.NOUN;
					 //IIndexWord idxWord1=dict.getIndexWord(str, pos);
				     //IWordID wordID1 = idxWord1.getWordIDs().get(num); 
				     IWord word1 = dict.getWord(wordID1); //获取词
				     ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
				     if(!(synset1.getRelatedSynsets(Pointer.MERONYM_PART).isEmpty())){
						List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.MERONYM_PART);
						return hypernyms1_tamp.size();
						}
						else{
							return 0;
						}
				     }
				//找Part2下位词个数
				 public int getPart2HyponymsNumber(IDictionary dict,IWordID wordID1){
					 POS pos = POS.NOUN;
					 //IIndexWord idxWord1=dict.getIndexWord(str, pos);
				     //IWordID wordID1 = idxWord1.getWordIDs().get(num); 
				     IWord word1 = dict.getWord(wordID1); //获取词
				     ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
				     if(!(synset1.getRelatedSynsets(Pointer.MERONYM_MEMBER).isEmpty())){
						List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.MERONYM_MEMBER);
						return hypernyms1_tamp.size();
						}
						else{
							return 0;
						}
				     }
				//找Part3下位词个数
				 public int getPart3HyponymsNumber(IDictionary dict,IWordID wordID1){
					 POS pos = POS.NOUN;
					 //IIndexWord idxWord1=dict.getIndexWord(str, pos);
				     //IWordID wordID1 = idxWord1.getWordIDs().get(num); 
				     IWord word1 = dict.getWord(wordID1); //获取词
				     ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
				     if(!(synset1.getRelatedSynsets(Pointer.MERONYM_SUBSTANCE).isEmpty())){
						List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.MERONYM_SUBSTANCE);
						return hypernyms1_tamp.size();
						}
						else{
							return 0;
						}
				     }
				 
				 
				 
				//找Attribute下位词个数
				 public int getAttributeHyponymsNumber(IDictionary dict,IWordID wordID1){
					 POS pos = POS.NOUN;
					 //IIndexWord idxWord1=dict.getIndexWord(str, pos);
				     //IWordID wordID1 = idxWord1.getWordIDs().get(num); 
				     IWord word1 = dict.getWord(wordID1); //获取词
				     ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
				     if(!(synset1.getRelatedSynsets(Pointer.ATTRIBUTE).isEmpty())){
						List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.ATTRIBUTE);
						return hypernyms1_tamp.size();
						}
						else{
							return 0;
						}
				     }
				
				
				
				
				
	//=================================找关系_广度需要的方法结束=======================================
				 
			
				 
    //=================================找关系_深度需要的方法=======================================
				 
				 
					
				 
				 
				 
				 
				 
				 
				 
				 
	//=================================找关系_深度需要的方法结束=======================================
			 
				 
	

	//找到所有上位词
		//关于Pointer各种属性见https://wenku.baidu.com/view/18c6c532b90d6c85ec3ac6de.html
		public List<ISynset> getHypernyms(IDictionary dict,String str1,POS pos1) throws IOException{
			
			IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
	        IWordID wordID1 = idxWord1.getWordIDs().get(0); 
	        IWord word1 = dict.getWord(wordID1); //获取词
	        ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
	        
	        // 获取上位词集合
	        List<ISynset> hypernyms1 = new ArrayList<ISynset>();
	        hypernyms1.add(0,synset1);
	        while(!(synset1.getRelatedSynsets(Pointer.HYPERNYM).isEmpty())){
	        List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.HYPERNYM);
	        ISynsetID fulei1 = hypernyms1_tamp.get(0);
	        List<IWord> wordlist1 = dict.getSynset(fulei1).getWords();
	        IWord word11 = wordlist1.get(0);
	        ISynset fulei1_synset = word11.getSynset(); //获取该词所在的Synset
	        hypernyms1.add(fulei1_synset);
	        synset1 = fulei1_synset;
	        }
	        //System.out.println (str1+"的上位词："+hypernyms1);
	        
	        
	        return hypernyms1;
			
		}
	    
	    
	 
	    
	    //找到所有下位词
	    public void gethyponyms(IDictionary dict,String str){
	        //获取指定的synset
	        IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//获取dog的IndexWord
	        IWordID wordID = idxWord.getWordIDs().get(0); //取出第一个词义的词的ID号
	        IWord word = dict.getWord(wordID); //获取词
	        ISynset synset = word.getSynset(); //获取该词所在的Synset
	  
	        // 获取thyponyms
	        List<ISynsetID> hyponyms =synset.getRelatedSynsets(Pointer.HYPONYM);//通过指针类型来获取相关的词集，其中Pointer类型为HYPERNYM
	        // print out each hyponyms id and synonyms
	        List <IWord > words ;
	        System.out.println (str+"的下位词：");
	        for( ISynsetID sid : hyponyms ){
	            words = dict.getSynset(sid).getWords(); //从synset中获取一个Word的list
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
	    
	    
	    
	    
	    
	  //找到所有上位词
	  		//关于Pointer各种属性见https://wenku.baidu.com/view/18c6c532b90d6c85ec3ac6de.html
	  		public List<ISynset> getHypernymsInstance(IDictionary dict,String str1,POS pos1) throws IOException{
	  			
	  			IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
	  	        IWordID wordID1 = idxWord1.getWordIDs().get(1); 
	  	        IWord word1 = dict.getWord(wordID1); //获取词
	  	        ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
	  	        
	  	        // 获取上位词集合
	  	        List<ISynset> hypernyms1 = new ArrayList<ISynset>();
	  	        hypernyms1.add(0,synset1);
	  	        while(!(synset1.getRelatedSynsets(Pointer.HYPERNYM_INSTANCE).isEmpty())){
	  	        List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.HYPERNYM_INSTANCE);
	  	        ISynsetID fulei1 = hypernyms1_tamp.get(0);
	  	        List<IWord> wordlist1 = dict.getSynset(fulei1).getWords();
	  	        IWord word11 = wordlist1.get(0);
	  	        ISynset fulei1_synset = word11.getSynset(); //获取该词所在的Synset
	  	        hypernyms1.add(fulei1_synset);
	  	        synset1 = fulei1_synset;
	  	        }
	  	        System.out.println (str1+"的上位词instance："+hypernyms1);
	  	        
	  	        
	  	        return hypernyms1;
	  			
	  		}
		    
		    
		 
		    
		    //找到所有下位词Instance
		    public void gethyponymsInstance(IDictionary dict,String str){
		        //获取指定的synset
		        IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//获取dog的IndexWord
		        IWordID wordID = idxWord.getWordIDs().get(0); //取出第一个词义的词的ID号
		        IWord word = dict.getWord(wordID); //获取词
		        ISynset synset = word.getSynset(); //获取该词所在的Synset
		  
		        // 获取thyponyms
		        List<ISynsetID> hyponyms =synset.getRelatedSynsets(Pointer.HYPONYM_INSTANCE);//通过指针类型来获取相关的词集，其中Pointer类型为HYPERNYM
		        // print out each hyponyms id and synonyms
		        List <IWord > words ;
		        System.out.println (str+"的下位词Instance：");
		        for( ISynsetID sid : hyponyms ){
		            words = dict.getSynset(sid).getWords(); //从synset中获取一个Word的list
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
	        //获取指定的synset
	        IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//获取dog的IndexWord
	        IWordID wordID = idxWord.getWordIDs().get(0); //取出第一个词义的词的ID号
	        IWord word = dict.getWord(wordID); //获取词
	        ISynset synset = word.getSynset(); //获取该词所在的Synset
	        
	  
	        // 获取hypernyms
	        List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.ALSO_SEE);//通过指针类型来获取相关的词集，其中Pointer类型为HYPERNYM
	        // print out each hypernyms id and synonyms
	        List <IWord> words ;
	        System.out.println (str+"的see also：");
	        for( ISynsetID sid : hypernyms ){
	            words = dict.getSynset(sid).getWords(); //从synset中获取一个Word的list
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
//获取指定的synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//获取dog的IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //取出第一个词义的词的ID号
IWord word = dict.getWord(wordID); //获取词
ISynset synset = word.getSynset(); //获取该词所在的Synset


// 获取hypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.ANTONYM);//通过指针类型来获取相关的词集，其中Pointer类型为HYPERNYM
// print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"的Antonym反义词：");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //从synset中获取一个Word的list
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
//获取指定的synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//获取dog的IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //取出第一个词义的词的ID号
IWord word = dict.getWord(wordID); //获取词
ISynset synset = word.getSynset(); //获取该词所在的Synset


//获取hypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.ATTRIBUTE);//通过指针类型来获取相关的词集，其中Pointer类型为HYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"的Attribute属性词：");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //从synset中获取一个Word的list
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
//获取指定的synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//获取dog的IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //取出第一个词义的词的ID号
IWord word = dict.getWord(wordID); //获取词
ISynset synset = word.getSynset(); //获取该词所在的Synset


//获取hypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.CAUSE);//通过指针类型来获取相关的词集，其中Pointer类型为HYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"的Cause：");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //从synset中获取一个Word的list
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
//获取指定的synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//获取dog的IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //取出第一个词义的词的ID号
IWord word = dict.getWord(wordID); //获取词
ISynset synset = word.getSynset(); //获取该词所在的Synset


//获取hypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.DERIVATIONALLY_RELATED);//通过指针类型来获取相关的词集，其中Pointer类型为HYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"的Derivationally_related：");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //从synset中获取一个Word的list
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
//获取指定的synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//获取dog的IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //取出第一个词义的词的ID号
IWord word = dict.getWord(wordID); //获取词
ISynset synset = word.getSynset(); //获取该词所在的Synset


//获取hypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.DERIVED_FROM_ADJ);//通过指针类型来获取相关的词集，其中Pointer类型为HYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"的DerivedFromADJ：");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //从synset中获取一个Word的list
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
//获取指定的synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//获取dog的IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //取出第一个词义的词的ID号
IWord word = dict.getWord(wordID); //获取词
ISynset synset = word.getSynset(); //获取该词所在的Synset


//获取hypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.DOMAIN);//通过指针类型来获取相关的词集，其中Pointer类型为HYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"的Domain：");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //从synset中获取一个Word的list
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
//获取指定的synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//获取dog的IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //取出第一个词义的词的ID号
IWord word = dict.getWord(wordID); //获取词
ISynset synset = word.getSynset(); //获取该词所在的Synset


//获取hypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.ENTAILMENT);//通过指针类型来获取相关的词集，其中Pointer类型为HYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"的Entailment蕴含词：");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //从synset中获取一个Word的list
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
//获取指定的synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//获取dog的IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //取出第一个词义的词的ID号
IWord word = dict.getWord(wordID); //获取词
ISynset synset = word.getSynset(); //获取该词所在的Synset


//获取hypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.HOLONYM_MEMBER);//通过指针类型来获取相关的词集，其中Pointer类型为HYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"的HolonymMember整体部分：");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //从synset中获取一个Word的list
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
//获取指定的synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//获取dog的IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //取出第一个词义的词的ID号
IWord word = dict.getWord(wordID); //获取词
ISynset synset = word.getSynset(); //获取该词所在的Synset


//获取hypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.HOLONYM_PART);//通过指针类型来获取相关的词集，其中Pointer类型为HYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"的HolonymPart整体部分：");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //从synset中获取一个Word的list
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
//获取指定的synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//获取dog的IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //取出第一个词义的词的ID号
IWord word = dict.getWord(wordID); //获取词
ISynset synset = word.getSynset(); //获取该词所在的Synset


//获取hypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.HOLONYM_SUBSTANCE);//通过指针类型来获取相关的词集，其中Pointer类型为HYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"的HolonymSubstance整体部分：");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //从synset中获取一个Word的list
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
//获取指定的synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//获取dog的IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //取出第一个词义的词的ID号
IWord word = dict.getWord(wordID); //获取词
ISynset synset = word.getSynset(); //获取该词所在的Synset


//获取hypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.MEMBER);//通过指针类型来获取相关的词集，其中Pointer类型为HYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"的member：");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //从synset中获取一个Word的list
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
//获取指定的synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//获取dog的IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //取出第一个词义的词的ID号
IWord word = dict.getWord(wordID); //获取词
ISynset synset = word.getSynset(); //获取该词所在的Synset


//获取hypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.MERONYM_MEMBER);//通过指针类型来获取相关的词集，其中Pointer类型为HYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"的MeronymMember：");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //从synset中获取一个Word的list
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
//获取指定的synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//获取dog的IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //取出第一个词义的词的ID号
IWord word = dict.getWord(wordID); //获取词
ISynset synset = word.getSynset(); //获取该词所在的Synset


//获取hypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.MERONYM_PART);//通过指针类型来获取相关的词集，其中Pointer类型为HYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"的MeronymPart：");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //从synset中获取一个Word的list
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
//获取指定的synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//获取dog的IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //取出第一个词义的词的ID号
IWord word = dict.getWord(wordID); //获取词
ISynset synset = word.getSynset(); //获取该词所在的Synset


//获取hypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.MERONYM_SUBSTANCE);//通过指针类型来获取相关的词集，其中Pointer类型为HYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"的MeronymSubstance：");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //从synset中获取一个Word的list
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
//获取指定的synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//获取dog的IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //取出第一个词义的词的ID号
IWord word = dict.getWord(wordID); //获取词
ISynset synset = word.getSynset(); //获取该词所在的Synset


//获取hypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.PARTICIPLE);//通过指针类型来获取相关的词集，其中Pointer类型为HYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"的Participle：");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //从synset中获取一个Word的list
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
//获取指定的synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//获取dog的IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //取出第一个词义的词的ID号
IWord word = dict.getWord(wordID); //获取词
ISynset synset = word.getSynset(); //获取该词所在的Synset


//获取hypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.PERTAINYM);//通过指针类型来获取相关的词集，其中Pointer类型为HYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"的Pertainym：");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //从synset中获取一个Word的list
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
//获取指定的synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//获取dog的IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //取出第一个词义的词的ID号
IWord word = dict.getWord(wordID); //获取词
ISynset synset = word.getSynset(); //获取该词所在的Synset


//获取hypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.REGION);//通过指针类型来获取相关的词集，其中Pointer类型为HYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"的Region区域：");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //从synset中获取一个Word的list
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
//获取指定的synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//获取dog的IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //取出第一个词义的词的ID号
IWord word = dict.getWord(wordID); //获取词
ISynset synset = word.getSynset(); //获取该词所在的Synset


//获取hypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.REGION_MEMBER);//通过指针类型来获取相关的词集，其中Pointer类型为HYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"的RegionMember：");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //从synset中获取一个Word的list
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
//获取指定的synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//获取dog的IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //取出第一个词义的词的ID号
IWord word = dict.getWord(wordID); //获取词
ISynset synset = word.getSynset(); //获取该词所在的Synset


//获取hypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.SIMILAR_TO);//通过指针类型来获取相关的词集，其中Pointer类型为HYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"的SimilarTo：");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //从synset中获取一个Word的list
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
//获取指定的synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//获取dog的IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //取出第一个词义的词的ID号
IWord word = dict.getWord(wordID); //获取词
ISynset synset = word.getSynset(); //获取该词所在的Synset


//获取hypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.TOPIC);//通过指针类型来获取相关的词集，其中Pointer类型为HYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"的Topic：");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //从synset中获取一个Word的list
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
//获取指定的synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//获取dog的IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //取出第一个词义的词的ID号
IWord word = dict.getWord(wordID); //获取词
ISynset synset = word.getSynset(); //获取该词所在的Synset


//获取hypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.TOPIC_MEMBER);//通过指针类型来获取相关的词集，其中Pointer类型为HYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"的TopicMmeber：");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //从synset中获取一个Word的list
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
//获取指定的synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//获取dog的IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //取出第一个词义的词的ID号
IWord word = dict.getWord(wordID); //获取词
ISynset synset = word.getSynset(); //获取该词所在的Synset


//获取hypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.USAGE);//通过指针类型来获取相关的词集，其中Pointer类型为HYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"的Usage：");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //从synset中获取一个Word的list
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
//获取指定的synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//获取dog的IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //取出第一个词义的词的ID号
IWord word = dict.getWord(wordID); //获取词
ISynset synset = word.getSynset(); //获取该词所在的Synset


//获取hypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.USAGE_MEMBER);//通过指针类型来获取相关的词集，其中Pointer类型为HYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"的UsageMmebe：");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //从synset中获取一个Word的list
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
//获取指定的synset
IIndexWord idxWord = dict.getIndexWord(str, POS.NOUN);//获取dog的IndexWord
IWordID wordID = idxWord.getWordIDs().get(0); //取出第一个词义的词的ID号
IWord word = dict.getWord(wordID); //获取词
ISynset synset = word.getSynset(); //获取该词所在的Synset


//获取hypernyms
List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.VERB_GROUP);//通过指针类型来获取相关的词集，其中Pointer类型为HYPERNYM
//print out each hypernyms id and synonyms
List <IWord> words ;
System.out.println (str+"的VerbGroup：");
for( ISynsetID sid : hypernyms ){
words = dict.getSynset(sid).getWords(); //从synset中获取一个Word的list
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







//找到所有上位词
	//关于Pointer各种属性见https://wenku.baidu.com/view/18c6c532b90d6c85ec3ac6de.html
	public List<ISynset> FindAllFather(IDictionary dict,String str1,POS pos1,int num1) throws IOException{
		
		IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
      IWordID wordID1 = idxWord1.getWordIDs().get(num1); 
      IWord word1 = dict.getWord(wordID1); //获取词
      ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
      
      // 获取上位词集合
      List<ISynset> hypernyms1 = new ArrayList<ISynset>();
      hypernyms1.add(0,synset1);
      while(!(synset1.getRelatedSynsets(Pointer.HYPERNYM).isEmpty())){
      List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.HYPERNYM);
      ISynsetID fulei1 = hypernyms1_tamp.get(0);
      List<IWord> wordlist1 = dict.getSynset(fulei1).getWords();
      IWord word11 = wordlist1.get(0);
      ISynset fulei1_synset = word11.getSynset(); //获取该词所在的Synset
      hypernyms1.add(fulei1_synset);
      synset1 = fulei1_synset;
      }
      //System.out.println (str1+"的上位词集合："+hypernyms1);
      
      
      return hypernyms1;
		
	}
	
	
	
	
	
	
	public List<ISynset> FindAllPath_Two(List<ISynset> hypernyms1,List<ISynset> hypernyms2) throws IOException{

		   //找最近公共上位词
		    List<ISynset> hypernyms_temp = new ArrayList<ISynset>(hypernyms1);
		    hypernyms_temp.retainAll(hypernyms2);
		    
		    List<ISynset> hypernyms1_new = new ArrayList<ISynset>();
		    //防止找不到报错
		    if(hypernyms_temp.size()==0){
		    	hypernyms1_new.add(hypernyms1.get(0)); 
		    	return hypernyms1_new;
		    }
	        ISynset SameFather = hypernyms_temp.get(0);
	        //System.out.println ("最近公共上位词："+SameFather);
	        
	        
	        //整理出所有的枝
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
	        //System.out.println ("修剪第一枝："+hypernyms1_new);
	        
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
	        //System.out.println ("修剪第二枝："+hypernyms2_new);
	        
	       
	      //去掉重复的路径
		   CutRepetition cut = new CutRepetition();
		   cut.CutRepetition_Two(hypernyms1_new, hypernyms2_new);
		   //System.out.println ("修剪第一枝："+hypernyms1_new);
		   //System.out.println ("修剪第二枝："+hypernyms2_new);
		   
		   
		   //整合两条路径
		   if(hypernyms2_new.size()==1){    }
		   else{
			   for(int m=1;m<=hypernyms2_new.size()-1;m++){
				   hypernyms1_new.add(hypernyms2_new.get(hypernyms2_new.size()-m-1));
			   }
		   }
		   //System.out.println ("Is-a整合后："+hypernyms1_new);

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
      IWord word1 = dict.getWord(wordID1); //获取词
      ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
      
      // 获取上位词集合
      List<ISynset> hypernyms1 = new ArrayList<ISynset>();
      hypernyms1.add(0,synset1);
      while(!(synset1.getRelatedSynsets(Pointer.HYPERNYM_INSTANCE).isEmpty())){
      List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.HYPERNYM_INSTANCE);
      ISynsetID fulei1 = hypernyms1_tamp.get(0);
      List<IWord> wordlist1 = dict.getSynset(fulei1).getWords();
      IWord word11 = wordlist1.get(0);
      ISynset fulei1_synset = word11.getSynset(); //获取该词所在的Synset
      hypernyms1.add(fulei1_synset);
      synset1 = fulei1_synset;
      }
      //System.out.println (str1+"的instance上位集合："+hypernyms1);
      
      
      return hypernyms1;
		
	}




public List<ISynset> FindAllPath_Two_InstanceOf(List<ISynset> hypernyms1,List<ISynset> hypernyms2) throws IOException{

	   //找最近公共上位词
	    List<ISynset> hypernyms_temp = new ArrayList<ISynset>(hypernyms1);
	    hypernyms_temp.retainAll(hypernyms2);
	    
	    List<ISynset> hypernyms1_new = new ArrayList<ISynset>();
	    //防止找不到报错
	    if(hypernyms_temp.size()==0){
	    	hypernyms1_new.add(hypernyms1.get(0));
	    	return hypernyms1_new;
	    }
     ISynset SameFather = hypernyms_temp.get(0);
     //System.out.println ("最近公共上位词："+SameFather);
     
     
     //整理出所有的枝
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
     //System.out.println ("修剪第一枝："+hypernyms1_new);
     
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
     //System.out.println ("修剪第二枝："+hypernyms2_new);
     
    
   //去掉重复的路径
	   CutRepetition cut = new CutRepetition();
	   cut.CutRepetition_Two(hypernyms1_new, hypernyms2_new);
	   //System.out.println ("修剪第一枝："+hypernyms1_new);
	   //System.out.println ("修剪第二枝："+hypernyms2_new);
	   
	   
	   //整合两条路径
	   if(hypernyms2_new.size()==1){    }
	   else{
		   for(int m=1;m<=hypernyms2_new.size()-1;m++){
			   hypernyms1_new.add(hypernyms2_new.get(hypernyms2_new.size()-m-1));
		   }
	   }
	   //System.out.println ("InstanceOf整合后："+hypernyms1_new);

    return hypernyms1_new;

 }






//====================PartOf====================================================





public List<ISynset> FindAllFather_PartOf1(IDictionary dict,String str1,POS pos1,int num1) throws IOException{
	
	IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
  IWordID wordID1 = idxWord1.getWordIDs().get(num1); 
  IWord word1 = dict.getWord(wordID1); //获取词
  ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
  
  // 获取上位词集合
  List<ISynset> hypernyms1 = new ArrayList<ISynset>();
  hypernyms1.add(0,synset1);
  while(!(synset1.getRelatedSynsets(Pointer.HOLONYM_PART).isEmpty())){
  List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.HOLONYM_PART);
  ISynsetID fulei1 = hypernyms1_tamp.get(0);
  List<IWord> wordlist1 = dict.getSynset(fulei1).getWords();
  IWord word11 = wordlist1.get(0);
  ISynset fulei1_synset = word11.getSynset(); //获取该词所在的Synset
  hypernyms1.add(fulei1_synset);
  synset1 = fulei1_synset;
  }
  //System.out.println (str1+"的part_part上位集合："+hypernyms1);
  
  
  return hypernyms1;
	
}
public List<ISynset> FindAllFather_PartOf2(IDictionary dict,String str1,POS pos1,int num1) throws IOException{
	
	IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
  IWordID wordID1 = idxWord1.getWordIDs().get(num1); 
  IWord word1 = dict.getWord(wordID1); //获取词
  ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
  
  // 获取上位词集合
  List<ISynset> hypernyms1 = new ArrayList<ISynset>();
  hypernyms1.add(0,synset1);
  while(!(synset1.getRelatedSynsets(Pointer.HOLONYM_MEMBER).isEmpty())){
  List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.HOLONYM_MEMBER);
  ISynsetID fulei1 = hypernyms1_tamp.get(0);
  List<IWord> wordlist1 = dict.getSynset(fulei1).getWords();
  IWord word11 = wordlist1.get(0);
  ISynset fulei1_synset = word11.getSynset(); //获取该词所在的Synset
  hypernyms1.add(fulei1_synset);
  synset1 = fulei1_synset;
  }
  //System.out.println (str1+"的part_member上位集合："+hypernyms1);
  
  
  return hypernyms1;
	
}
public List<ISynset> FindAllFather_PartOf3(IDictionary dict,String str1,POS pos1,int num1) throws IOException{
	
	IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
  IWordID wordID1 = idxWord1.getWordIDs().get(num1); 
  IWord word1 = dict.getWord(wordID1); //获取词
  ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
  
  // 获取上位词集合
  List<ISynset> hypernyms1 = new ArrayList<ISynset>();
  hypernyms1.add(0,synset1);
  while(!(synset1.getRelatedSynsets(Pointer.HOLONYM_SUBSTANCE).isEmpty())){
  List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.HOLONYM_SUBSTANCE);
  ISynsetID fulei1 = hypernyms1_tamp.get(0);
  List<IWord> wordlist1 = dict.getSynset(fulei1).getWords();
  IWord word11 = wordlist1.get(0);
  ISynset fulei1_synset = word11.getSynset(); //获取该词所在的Synset
  hypernyms1.add(fulei1_synset);
  synset1 = fulei1_synset;
  }
  //System.out.println (str1+"的part_substance上位集合："+hypernyms1);
  
  
  return hypernyms1;
	
}





public List<ISynset> FindAllPath_Two_PartOf(List<ISynset> hypernyms1,List<ISynset> hypernyms2) throws IOException{

	   //找最近公共上位词
	    List<ISynset> hypernyms_temp = new ArrayList<ISynset>(hypernyms1);
	    hypernyms_temp.retainAll(hypernyms2);
	    
	    List<ISynset> hypernyms1_new = new ArrayList<ISynset>();
	    //防止找不到报错
	    if(hypernyms_temp.size()==0){
	    	hypernyms1_new.add(hypernyms1.get(0));
	    	return hypernyms1_new;
	    }
  ISynset SameFather = hypernyms_temp.get(0);
  //System.out.println ("最近公共上位词："+SameFather);
  
  
  //整理出所有的枝
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
  //System.out.println ("修剪第一枝："+hypernyms1_new);
  
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
  //System.out.println ("修剪第二枝："+hypernyms2_new);
  
 
//去掉重复的路径
	   CutRepetition cut = new CutRepetition();
	   cut.CutRepetition_Two(hypernyms1_new, hypernyms2_new);
	   //System.out.println ("修剪第一枝："+hypernyms1_new);
	   //System.out.println ("修剪第二枝："+hypernyms2_new);
	   
	   
	   //整合两条路径
	   if(hypernyms2_new.size()==1){    }
	   else{
		   for(int m=1;m<=hypernyms2_new.size()-1;m++){
			   hypernyms1_new.add(hypernyms2_new.get(hypernyms2_new.size()-m-1));
		   }
	   }
	   //System.out.println ("PartOf整合后："+hypernyms1_new);

 return hypernyms1_new;

}







//===================AttributeOf=================================================

	


public List<ISynset> FindAllFather_AttributeOf(IDictionary dict,String str1,POS pos1,int num1) throws IOException{
	
	IIndexWord idxWord1=dict.getIndexWord(str1, pos1);
    IWordID wordID1 = idxWord1.getWordIDs().get(num1); 
    IWord word1 = dict.getWord(wordID1); //获取词
    ISynset synset1 = word1.getSynset(); //获取该词所在的Synset
  
    // 获取上位词集合
    List<ISynset> hypernyms1 = new ArrayList<ISynset>();
    hypernyms1.add(0,synset1);
    if(!(synset1.getRelatedSynsets(Pointer.ATTRIBUTE).isEmpty())){
    List<ISynsetID> hypernyms1_tamp =synset1.getRelatedSynsets(Pointer.ATTRIBUTE);
    ISynsetID fulei1 = hypernyms1_tamp.get(0);
    List<IWord> wordlist1 = dict.getSynset(fulei1).getWords();
    IWord word11 = wordlist1.get(0);
    ISynset fulei1_synset = word11.getSynset(); //获取该词所在的Synset
    hypernyms1.add(fulei1_synset);
    }
    else{    }
    //System.out.println (str1+"的Attribute集合："+hypernyms1);
  
  
  return hypernyms1;
	
}











	    
	
	
	
}
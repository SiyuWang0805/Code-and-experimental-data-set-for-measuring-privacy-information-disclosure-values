package TryCalculate;

import java.util.List;

import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.IWordID;

import java.util.ArrayList;



public class Node {
    public String NodeName;
    public double NodeValue;
    public ISynset NodeSynset;
    public IWordID NodeWordID;
    public String NodeFrom;
    public String NodeFromWord;
    public boolean NodeHasCalculated; 
    public boolean NodeIsDisclo;


    
    //下面是初始化用的
    public String getNodeName() {
        return NodeName;
    }

    public void setNodeName(String nodename) {
        this.NodeName = nodename;
    }

    public double getNodeValue() {
        return NodeValue;
    }

    public void setNodeValue(double nodevalue) {
        this.NodeValue = nodevalue;
    }
    
    public ISynset getNodeSynset() {
        return NodeSynset;
    }

    public void setNodeSynset(ISynset nodesynset) {
        this.NodeSynset = nodesynset;
    }
    
    public IWordID getNodeWordID() {
        return NodeWordID;
    }

    public void setNodeWordID(IWordID nodewordid) {
        this.NodeWordID = nodewordid;
    }
    
    public String getNodeFrom() {
        return NodeFrom;
    }

    public void setNodeFrom(String nodefrom) {
        this.NodeFrom = nodefrom;
    }
    
    public String getNodeFromWord() {
        return NodeFromWord;
    }

    public void setNodeFromWord(String nodefromword) {
        this.NodeFromWord = nodefromword;
    }
    
    public boolean getNodeHasCalculated() {
        return NodeHasCalculated;
    }

    public void setNodeHasCalculated(boolean nodehascalculated) {
        this.NodeHasCalculated = nodehascalculated;
    }
    
    public boolean getNodeIsDisclo() {
        return NodeIsDisclo;
    }

    public void setNodeIsDisclo(boolean nodehasdisclo) {
        this.NodeIsDisclo = nodehasdisclo;
    }
   
    
    

}
package TryCalculate;

import java.util.List;

import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.IWordID;

import java.util.ArrayList;



public class Node_Digui{
    public String NodeName;
    public double NodePrivacyValue;
    public double NodeIsBoom;
    public IWordID NodeWordID;
    public List<Node_path> IsAFather;
    public List<Node_path> IsAChild;
    public List<Node_path> InstanceFather;
    public List<Node_path> InstanceChild;
    public List<Node_path> Part1Father;
    public List<Node_path> Part1Child;
    public List<Node_path> Part2Father;
    public List<Node_path> Part2Child;
    public List<Node_path> Part3Father;
    public List<Node_path> Part3Child;
    public List<Node_path> AttributeChild;
    public boolean NodeFlag;
    
  


    
    //下面是初始化用的
    public String getNodeName() {
        return NodeName;
    }

    public void setNodeName(String nodename) {
        this.NodeName = nodename;
    }

    public double getNodePrivacyValue() {
        return NodePrivacyValue;
    }

    public void setNodePrivacyValue(double NodePrivacyValue) {
        this.NodePrivacyValue = NodePrivacyValue;
    }
    
    public double getNodeIsBoom() {
        return NodeIsBoom;
    }

    public void setNodeIsBoom(double NodeIsBoom) {
        this.NodeIsBoom = NodeIsBoom;
    }
    
    
    public IWordID getNodeWordID() {
        return NodeWordID;
    }

    public void setNodeWordID(IWordID nodewordid) {
        this.NodeWordID = nodewordid;
    }
    
    public List<Node_path> getIsAFather() {
        return IsAFather;
    }

    public void setIsAFather(List<Node_path> IsAFather) {
        this.IsAFather = IsAFather;
    }
    
    public List<Node_path> getIsAChild() {
        return IsAChild;
    }

    public void setIsAChild(List<Node_path> IsAChild) {
        this.IsAChild = IsAChild;
    }
    
    public List<Node_path> getInstanceFather() {
        return InstanceFather;
    }

    public void setInstanceFather(List<Node_path> InstanceFather) {
        this.InstanceFather = InstanceFather;
    }
    
    public List<Node_path> getInstanceChild() {
        return InstanceChild;
    }

    public void setInstanceChild(List<Node_path> InstanceChild) {
        this.InstanceChild = InstanceChild;
    }
    
    public List<Node_path> getPart1Father() {
        return Part1Father;
    }

    public void setPart1Father(List<Node_path> Part1Father) {
        this.Part1Father = Part1Father;
    }
    
    public List<Node_path> getPart1Child() {
        return Part1Child;
    }

    public void setPart1Child(List<Node_path> Part1Child) {
        this.Part1Child = Part1Child;
    }
    
    public List<Node_path> getPart2Father() {
        return Part2Father;
    }

    public void setPart2Father(List<Node_path> Part2Father) {
        this.Part2Father = Part2Father;
    }
    
    public List<Node_path> getPart2Child() {
        return Part2Child;
    }

    public void setPart2Child(List<Node_path> Part2Child) {
        this.Part2Child = Part2Child;
    }
    
    public List<Node_path> getPart3Father() {
        return Part3Father;
    }

    public void setPart3Father(List<Node_path> Part3Father) {
        this.Part3Father = Part3Father;
    }
    
    public List<Node_path> getPart3Child() {
        return Part3Child;
    }

    public void setPart3Child(List<Node_path> Part3Child) {
        this.Part3Child = Part3Child;
    }
    
    public List<Node_path> getAttributeChild() {
        return AttributeChild;
    }

    public void setAttributeChild(List<Node_path> AttributeChild) {
        this.AttributeChild = AttributeChild;
    }
    
    public boolean getNodeFlag() {
        return NodeFlag;
    }

    public void setNodeFlag(boolean nodeflag) {
        this.NodeFlag = nodeflag;
    }
    
    
   
    
    

}
package cn.mars.sensitiveword;

import java.util.HashMap;
import java.util.Map;

/**
 * Description：
 * Created by Mars on 2017/11/6.
 */
public class TrieNode {

    //当前节点是否为词末
    private boolean end = false;

    Map<Character, TrieNode> subNode = new HashMap<>();

    public void addNode(Character key, TrieNode node){
        subNode.put(key, node);
    }

    TrieNode getSubNode(Character key){
        return subNode.get(key);
    }

    void setKeywordEnd(boolean end){
        this.end = end;
    }
    boolean isKeywordEnd(){
        return end;
    }
}

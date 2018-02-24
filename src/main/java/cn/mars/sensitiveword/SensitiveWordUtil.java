package cn.mars.sensitiveword;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Description：
 * Created by Mars on 2017/11/6.
 */
@Component
public class SensitiveWordUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(SensitiveWordUtil.class);

    private TrieNode root = new TrieNode();

    @PostConstruct
    private void initDictionary(){
        LOGGER.info("开始初始化敏感词库...");
        try{
            Resource resource = new ClassPathResource("./sensitive_words.txt");
            InputStream in = resource.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                addWord(line);
            }
            reader.close();
        }catch(IOException e){
            LOGGER.error("初始化敏感词库异常 {}", e);
        }
        LOGGER.info("初始化敏感词库成功...");
    }

    public void addWord(String lineText){
        TrieNode tmpNode = root;
        for(int i = 0;i < lineText.length();i ++){
            Character word = lineText.charAt(i);
            TrieNode node = tmpNode.getSubNode(word);
            if(node == null){
                node = new TrieNode();
                tmpNode.addNode(word, node);
            }
            tmpNode = node;
            if(i == lineText.length() - 1){
                tmpNode.setKeywordEnd(true);
            }
        }
    }

    public String filter(String text){
        if(text==null||text.length()==0) return text;
        StringBuilder result = new StringBuilder();
        String replace = "**";
        TrieNode tempNode = root;
        int begin = 0;
        int position = 0;
        while(position<text.length()){
            char word = text.charAt(position);
            tempNode = tempNode.getSubNode(word);
            if (tempNode == null){
                result.append(text.charAt(begin));
                position = begin+1;
                begin = position;
                tempNode = root;
            } else if (tempNode.isKeywordEnd()){
                result.append(replace);
                position = position+1;
                begin = position;
                tempNode = root;
            } else{
                position++;
            }
        }
        result.append(text.substring(begin));
        return result.toString();
    }


}

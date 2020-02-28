package cn.mars.demo;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Description：
 * Created by Mars on 2020/2/25.
 */
public class lRUCacheMap extends LinkedHashMap {

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return super.removeEldestEntry(eldest);
    }
}

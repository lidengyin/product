package cn.hctech2006.product.product.common;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;


/**
 * 本地化Token缓存
 */
public class TokenCache {
    private static Logger logger =  LoggerFactory.getLogger(TokenCache.class);

    public static String TOKEN_PREFIC="token_";

    private static LoadingCache<String,Object> loadingCache = CacheBuilder.newBuilder()
            .initialCapacity(1000)
            .maximumSize(10000).expireAfterAccess(12,TimeUnit.DAYS)
            .build(new CacheLoader<String, Object>() {

                @Override
                public Object load(String s) throws Exception {
                    return null;
                }
            });

    /**
     * 设置本地token缓存
     * @param key
     * @param value
     */
    public static void setKey(String key, Object value){
        loadingCache.put(key,value);
    }

    /**
     * 得到本地token缓存
     * @param key
     * @return
     */
    public static Object getKey(String key)  {
        Object value = null;
        try{
            value = loadingCache.get(key);
            if ("null".equals(value)){
                return null;
            }
            return value;
        }catch (Exception e){
           logger.error("localCache get error", e);
        }
        return null;
    }

}

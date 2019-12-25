package top.xugx.xmi.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

public class ECardSignUtil {

    private static final Logger log = LoggerFactory.getLogger(ECardSignUtil.class);

    private final static String ENCODING = "UTF-8";


    public static void main(String[] args){
        Map<String,String> m = new HashMap<String,String>();
        m.put("abc","123");
        sign(m);
    }

    /**
     * 签名
     *
     * @param map
     * @return
     */
    public static String sign(Map<String, String> map) {
        List<String> keys = new ArrayList<String>(map.keySet());
        Collections.sort(keys);
        StringBuffer sf = new StringBuffer();
        for (String key : keys) {
            String value = map.get(key);
            if (StringUtils.isNotBlank(value)) {
                sf.append(buildKeyValue(key, value, false)).append("&");
            }
        }
        if (sf.length() > 0) {
            sf.deleteCharAt(sf.length() - 1);
        }
        String signData = sf.toString();
        String signature = DigestUtils.sha1Hex(signData);
        return signature;
    }

    /**
     * 验签
     *
     * @param map
     * @param sign
     * @return
     * @throws Exception
     */
    public static boolean verify(Map<String, String> map, String sign) {
        String signature = sign(map);
        log.info("签名结果signature={}，传入的sign={}", signature, sign);
        boolean validate = sign.equals(signature) ? true : false;
        return validate;
    }

    private static String buildKeyValue(String key, String value, boolean isEncode) {
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        sb.append("=");
        if (isEncode) {
            try {
                sb.append(URLEncoder.encode(value, ENCODING));
            } catch (UnsupportedEncodingException e) {
                sb.append(value);
            }
        } else {
            sb.append(value);
        }
        return sb.toString();
    }
}

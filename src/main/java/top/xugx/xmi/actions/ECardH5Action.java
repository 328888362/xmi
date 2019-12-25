package top.xugx.xmi.actions;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import top.xugx.xmi.dto.CodeReQuest;
import top.xugx.xmi.utils.ECardSignUtil;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ECardH5Action {
    @Autowired
    private RestTemplate rest;

    @Value(value="${ecard.neusoft.code-url}")
    private String codeUrl;

    @RequestMapping("/code")
    public Object code() throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        CodeReQuest reQuest = new CodeReQuest();
        HttpEntity<String> entity = new HttpEntity<String>(JSON.toJSONString(reQuest), headers);
        System.out.println(JSON.toJSONString(reQuest));
        System.out.println(codeUrl);
        JSONObject ret = rest.postForEntity(codeUrl,entity, JSONObject.class).getBody();
        /*Map<String,String> mm = new HashMap<>();
        mm.put("key","OJIaZHktRhmVZ0LnjIaKHA000");
        mm.put("clientId","340601wst");
        mm.put("noncestr",ret.getString("noncestr"));*/
        //System.out.println(ECardSignUtil.verify(mm,ret.getString("sign")));
        Map<String,Object> m = new HashMap<>();
        m.put("请求参数",reQuest);
        m.put("返回结果",ret);
        /*m.put("返回结果签名验证结果",ECardSignUtil.verify(mm,ret.getString("sign")));*/
        if(!"F".equals(ret.getJSONObject("resultInfo").getString("resultStatus"))){
            String code = ret.getJSONObject("biz_content").getString("code");
            Map<String,String> p = new HashMap<>();
            p.put("redirect_url",URLEncoder.encode("http://223.244.235.176:8081/wechat/out/view/chuzhou/view/updateMyself.html","UTF-8"));
            p.put("channel","alipay");
            String ps = URLEncoder.encode(JSON.toJSONString(p),"UTF-8") ;
            String URL = "http://223.244.235.176:8081/wechat/view/chuzhou/init.html?t="+code+"&p="+ps;
            m.put("url",URL);
        }

        return m;
    }
}

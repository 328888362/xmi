package top.xugx.xmi.actions;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import top.xugx.xmi.dto.CodeReQuest;
import top.xugx.xmi.utils.ECardSignUtil;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ECardH5Action {
    @Autowired
    private RestTemplate rest;

    @Value(value="${ecard.neusoft.code-url}")
    private String codeUrl;

    @RequestMapping("/code/{password}/{type}")
    public ModelAndView code(@PathVariable(value="type") int type,@PathVariable(value="password") String password) throws Exception{
        if(!"neusoft".equals(password)){
            return null;
        }
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
        String URL = "";
        if(!"F".equals(ret.getJSONObject("resultInfo").getString("resultStatus"))){
            String code = ret.getJSONObject("biz_content").getString("code");
            Map<String,String> p = new HashMap<>();
            String red = "";
            switch (type){
                case 1:
                    red = "http://60.172.198.16:8081/wechat/view/fuyang/views/personBaseInfoView.html";
                    break;
                case 2:
                    red="http://60.172.198.16:8081/wechat/view/fuyang/views/personInsuredInfoView.html";
                    break;
                case 3:
                    red="http://60.172.198.16:8081/wechat/view/fuyang/views/ylInsuredPayInfoView.html";
                    break;
                case 4:
                    red="http://60.172.198.16:8081/wechat/view/fuyang/views/ylInsuredTreatInfoView.html";
                    break;
                case 5:
                    red="http://60.172.198.16:8081/wechat/view/fuyang/views/medicalInsuredCalculateView.html";
                    break;
                case 6:
                    red=" http://60.172.198.16:8081/wechat/view/fuyang/views/medicalInsuredPayInfoView.html";
                    break;
                case 7:
                    red="http://60.172.198.16:8081/wechat/view/fuyang/views/medicalYearAccount.html";
                    break;
                case 8:
                    red="http://60.172.198.16:8081/wechat/view/fuyang/views/indexListView.html";
                    break;
                default:
                    red = "http://60.172.198.16:8081/wechat/view/fuyang/views/personBaseInfoView.html";
                    break;
            }
            //p.put("redirect_url",URLEncoder.encode("http://223.244.235.176:8081/wechat/out/view/chuzhou/view/updateMyself.html","UTF-8"));
            p.put("redirect_url",URLEncoder.encode(red,"UTF-8"));
            p.put("channel","alipay");
            String ps = URLEncoder.encode(JSON.toJSONString(p),"UTF-8") ;
            //String URL = "http://223.244.235.176:8081/wechat/view/chuzhou/init.html?t="+code+"&p="+ps;
            URL = "http://60.172.198.16:8081/wechat/wst/view/fuyang/init.html?t="+code+"&p="+ps;
            m.put("url",URL);
        }
        ModelAndView md = new ModelAndView();
        md.setViewName("ecard");
        md.addObject("rurl",URL);
        return md;
    }
}

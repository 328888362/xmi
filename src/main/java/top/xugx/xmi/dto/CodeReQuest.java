package top.xugx.xmi.dto;

import com.alibaba.fastjson.JSON;
import top.xugx.xmi.utils.ECardSignUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CodeReQuest {
    private String clientId;
    private String noncestr;
    private String sign;
    private BizContent biz_content;


    public CodeReQuest() {
        this.clientId = "340601wst";
        //this.clientId = "341101";
        this.noncestr = (new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date());
        //this.noncestr = "20191224122800";
        this.biz_content = new BizContent();
        Map<String,String> map = new HashMap<>();
        map.put("clientId",this.clientId);
        map.put("noncestr",this.noncestr);
        map.put("key","OJIaZHktRhmVZ0LnjIaKHA000");
        //map.put("key","YKL0K3Dd0iojikk89765Q4YJc");
        //map.put("biz_content", "");
        map.put("out_sign_no",this.biz_content.getOut_sign_no());
        map.put("out_channel",this.biz_content.getOut_channel());
        map.put("out_busi_seq","");
        System.out.println(JSON.toJSONString(map));
        this.sign = ECardSignUtil.sign(map);
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public BizContent getBiz_content() {
        return biz_content;
    }

    public void setBiz_content(BizContent biz_content) {
        this.biz_content = biz_content;
    }
}

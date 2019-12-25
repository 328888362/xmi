package top.xugx.xmi.dto;

public class BizContent {
    private String out_sign_no;
    private String out_channel;
    private String out_busi_seq;

    public BizContent() {
        //1DC9540AACD6E527ADECAAF7589575C7
        //this.out_sign_no = "1DC9540AACD6E527ADECAAF7589575C7";
        //this.out_channel = "3411000001";
        this.out_sign_no="7489F162038625240B30AF3BDED8A0D7";
        this.out_channel = "3400000001";
        //this.out_channel = "9200000002";
        this.out_busi_seq = "";
    }

    public String getOut_sign_no() {
        return out_sign_no;
    }

    public void setOut_sign_no(String out_sign_no) {
        this.out_sign_no = out_sign_no;
    }

    public String getOut_channel() {
        return out_channel;
    }

    public void setOut_channel(String out_channel) {
        this.out_channel = out_channel;
    }

    public String getOut_busi_seq() {
        return out_busi_seq;
    }

    public void setOut_busi_seq(String out_busi_seq) {
        this.out_busi_seq = out_busi_seq;
    }
}

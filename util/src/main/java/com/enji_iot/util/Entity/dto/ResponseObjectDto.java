package com.enji_iot.util.Entity.dto;
/**
 * @author TsinghuaLee
 * this's entity object by response info
 * @date 2020-11-9
 */
public class ResponseObjectDto {
    private String responseId;
    private String responseMsg;

    public ResponseObjectDto(String responseId, String responseMsg) {
        this.responseId = responseId;
        this.responseMsg = responseMsg;
    }

    public ResponseObjectDto() {
    }

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }
}

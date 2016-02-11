package org.tekCorp.api.response;

/**
 * Created by FRERES Thierry on 05/02/2016.
 */
public class JsonResponse {
    private int returnCode;
    private String message;
    private Object info;

    public int getReturnCode() {
        return returnCode;
    }
    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public JsonResponse(int returnCode, String message) {
        super();
        this.returnCode = returnCode;
        this.message = message;
    }
    public JsonResponse() {
        super();
        // TODO Auto-generated constructor stub
    }
    public JsonResponse(int returnCode, String message, Object info) {
        super();
        this.returnCode = returnCode;
        this.message = message;
        this.info = info;
    }
    @Override
    public String toString() {
        return "JsonResponse [returnCode=" + returnCode + ", message=" + message + "]";
    }
    public Object getInfo() {
        return info;
    }
    public void setInfo(Object info) {
        this.info = info;
    }
}

package pojo;

public class FriendsMsg {
    String userName;
    String nickName;
    String sex;
    String sig;

    public FriendsMsg(String userName, String nickName, String sex, String sig) {
        this.userName = userName;
        this.nickName = nickName;
        this.sex = sex;
        this.sig = sig;
    }

    public FriendsMsg() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSig() {
        return sig;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }
}

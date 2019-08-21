package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import pojo.FriendsMsg;
import util.GetMsg;
import util.PyWriteUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Controller {
    //public static String  rootPath = System.getProperty("user.dir");
    public static String  rootPath ="D:\\wechatsup";
    Map<String ,Process> procMap = new HashMap();
    List<FriendsMsg> currentFriendsList= new ArrayList();
    Process proc;
    @FXML private TableView ProcessList;
    @FXML private TableColumn ProcessName;
    @FXML private TableColumn ProcessAddress;

    @FXML private TableView FriendsList;
    @FXML private TableColumn FriendsListName;
    @FXML private TableColumn FriendsListSex;
    @FXML private TableColumn FriendsListSig;

    @FXML private TableView FriendsList1;
    @FXML private TableColumn FriendsListName1;
    @FXML private TableColumn FriendsListSex1;
    @FXML private TableColumn FriendsListSig1;

    @FXML private TableView FriendsList2;
    @FXML private TableColumn FriendsListName2;
    @FXML private TableColumn FriendsListSex2;
    @FXML private TableColumn FriendsListSig2;

    @FXML private TableView FriendsList3;
    @FXML private TableColumn FriendsListName3;
    @FXML private TableColumn FriendsListSex3;
    @FXML private TableColumn FriendsListSig3;

    @FXML private TextField HarassContent2_2;
    @FXML private TextField HarassNum2_2;


    @FXML private TextField ProcessName_3_1;
    @FXML private TextField Tuling_key3_3;
    @FXML private TextField Resp_content3_2;
    @FXML private Text RemindUserText;




    @FXML private TextArea Reply_All_Textarea;

    //登录并且把信息好友信息打印到EXCEL表格里
    @FXML protected void Login(ActionEvent event) throws Exception{
        //写脚本
        PyWriteUtil.writeGetFriendsMsg();
        //执行脚本
        System.out.println(rootPath+"\\python_script\\getFriendsMsg.py");
        proc = Runtime.getRuntime().exec("python "+rootPath+"\\python_script\\getFriendsMsg.py");
        proc.waitFor();
        showAndUpdateFriends();
        proc.destroy();
        System.out.println("登录完成，currentFriendsList填充完毕...并且脚本已摧毁");
    }
    //骚扰
    @FXML protected void Harass(ActionEvent event) throws Exception{
        String text = HarassContent2_2.getText();
        String repeat_num = HarassNum2_2.getText();
        FriendsMsg fromUser = (FriendsMsg) FriendsList2.getSelectionModel().getSelectedItem();
        String fromNickName = fromUser.getNickName();
        PyWriteUtil.writeHarass(text,repeat_num,fromNickName);
        //执行脚本
        proc = Runtime.getRuntime().exec("python "+rootPath+"\\python_script\\harass.py");
        proc.waitFor();
        proc.destroy();
        System.out.println("骚扰信息已发送");
    }


    //全体自动回复信息
    @FXML protected void AutoReplyStart(ActionEvent event) throws Exception{
        String text = Reply_All_Textarea.getText();
        System.out.println("自动回复内容为："+text);
        PyWriteUtil.writeReplyAllFriends(text);
        proc = Runtime.getRuntime().exec("python "+rootPath+"\\python_script\\autoReplyStart.py");// 执行py文件

        if(ProcessName_3_1.getText().length()==0){
            procMap.put("全体自动回复脚本（默认）",proc);
        }else {
            procMap.put(ProcessName_3_1.getText(),proc);
        }
        System.out.println(procMap);
        RemindUserText.setText("自动回复已开启...");

    }
    //待删除
    @FXML protected void CloseAutoReply(ActionEvent event) throws Exception{
        proc.destroy();
        RemindUserText.setText("自动回复已关闭...");
    }
    //把excel信息打印出来
    @FXML protected void GetAllFriendsMsg(ActionEvent event) throws Exception{

        //填充表格
        showAndUpdateFriends();
        proc.destroy();
        System.out.println("脚本已摧毁");
    }
    //显示进程
    @FXML protected void UpdateAllProcess(ActionEvent event) throws Exception{
        ObservableList<pojo.ProcessList> processList = GetMsg.getProcess(procMap);
        ProcessList.setItems(processList);
        ProcessName.setCellValueFactory(new PropertyValueFactory("processName"));
        ProcessAddress.setCellValueFactory(new PropertyValueFactory("processAddress"));
    }
    //自动回复某人信息
    @FXML protected void AutoReplyOneFriendStart(ActionEvent event) throws Exception{
        String text = Resp_content3_2.getText();
        FriendsMsg fromUser = (FriendsMsg) FriendsList.getSelectionModel().getSelectedItem();
        String fromUserName = fromUser.getUserName();
        PyWriteUtil.writeReplyOneFriend(text,fromUserName);
        proc = Runtime.getRuntime().exec("python "+rootPath+"\\python_script\\autoReplyOneFriend.py");// 执行py文件
        procMap.put("自动回复某人信息脚本",proc);
    }

    //变身图灵机器人
    @FXML protected void ChangeTheRobot(ActionEvent event) throws Exception{
        String tuling_key = Tuling_key3_3.getText();
        FriendsMsg fromUser = (FriendsMsg) FriendsList1.getSelectionModel().getSelectedItem();
        String fromUserName = fromUser.getUserName();
        PyWriteUtil.writeRobot(tuling_key,fromUserName);
        //运行脚本，把脚本加入进程列表
        proc = Runtime.getRuntime().exec("python "+rootPath+"\\python_script\\robot.py");// 执行py文件
        procMap.put("图灵机器人脚本",proc);

    }

    //复读机
    @FXML protected void RepeatMachine(ActionEvent event) throws Exception{
        PyWriteUtil.writeRepeatMachine();
        proc = Runtime.getRuntime().exec("python "+rootPath+"\\python_script\\repeatMachine.py");// 执行py文件
        procMap.put("复读机脚本",proc);
    }
    //关闭所有进程
    @FXML protected void CloseAllProcess(ActionEvent event) throws Exception{
        for(String mapkey : procMap.keySet()){
            procMap.get(mapkey).destroy();
        }
        procMap.clear();
        System.out.println(procMap);
    }
    //关闭特定进程
    @FXML protected void test111(ActionEvent event) throws Exception{
        pojo.ProcessList processMsg = (pojo.ProcessList)ProcessList.getSelectionModel().getSelectedItem();
        String processName = processMsg.getProcessName();
        System.out.println(processName);
        for(String mapkey : procMap.keySet()){
            if(processName.equals(mapkey)) {
                procMap.get(mapkey).destroy();
                procMap.remove(mapkey);
            }
        }
        //重新显示进程
        ObservableList<pojo.ProcessList> processList = GetMsg.getProcess(procMap);
        ProcessList.setItems(processList);
        ProcessName.setCellValueFactory(new PropertyValueFactory("processName"));
        ProcessAddress.setCellValueFactory(new PropertyValueFactory("processAddress"));

    }



    private void showAndUpdateFriends() throws Exception {
        ObservableList<FriendsMsg> fridentsMsg = GetMsg.getFridentsMsg();
        //currentFriendsList填充
        currentFriendsList = fridentsMsg;
        FriendsList.setItems(fridentsMsg);
        FriendsListName.setCellValueFactory(new PropertyValueFactory("nickName"));
        FriendsListSex.setCellValueFactory(new PropertyValueFactory("sex"));
        FriendsListSig.setCellValueFactory(new PropertyValueFactory("sig"));
        FriendsList1.setItems(fridentsMsg);
        FriendsListName1.setCellValueFactory(new PropertyValueFactory("nickName"));
        FriendsListSex1.setCellValueFactory(new PropertyValueFactory("sex"));
        FriendsListSig1.setCellValueFactory(new PropertyValueFactory("sig"));
        FriendsList2.setItems(fridentsMsg);
        FriendsListName2.setCellValueFactory(new PropertyValueFactory("nickName"));
        FriendsListSex2.setCellValueFactory(new PropertyValueFactory("sex"));
        FriendsListSig2.setCellValueFactory(new PropertyValueFactory("sig"));
        FriendsList3.setItems(fridentsMsg);
        FriendsListName3.setCellValueFactory(new PropertyValueFactory("nickName"));
        FriendsListSex3.setCellValueFactory(new PropertyValueFactory("sex"));
        FriendsListSig3.setCellValueFactory(new PropertyValueFactory("sig"));
    }
}

package util;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PyWriteUtil {
    //public static String  rootPath = System.getProperty("user.dir");
    public static String  rootPath ="D:\\wechatsup";
    //import itchat
    //
    //@itchat.msg_register(itchat.content.TEXT)
    //def print_content(msg):
    //        return "love you"
    //
    //        itchat.auto_login(hotReload=True)
    //        itchat.run()
    public static void writeReplyAllFriends(String text){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(rootPath+"\\python_script\\autoReplyStart.py"));
            out.write("#encoding=utf-8\r\n");
            out.write("import itchat\r\n");
            out.write("@itchat.msg_register(itchat.content.TEXT)\r\n");
            out.write("def print_content(msg):\r\n");
            out.write("     return '"+text+"'\r\n");
            out.write("itchat.auto_login(hotReload=True)\r\n");
            out.write("itchat.run()\r\n");
            out.close();
            System.out.println("用于全体自动回复的脚本文件创建成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//#coding=GB2312
//import itchat, time, json, xlsxwriter
//
//itchat.auto_login(True)
//
//    SINCERE_WISH = u"Friend:%s"
//
//    friendList = itchat.get_friends(update=True)[:]
//#读取创建excel
//            workbook = xlsxwriter.Workbook('user_data.xlsx')
//    worksheet = workbook.add_worksheet()
//    row = 0
//    col = 0
//
//            for friend in friendList:
//            # 如果是演示目的，把下面的方法改为print即可
//            sex='女'
//	if friend['Sex']==1:
//    sex = '男'
//    temp_list = {'NickName':friend['NickName'],'Sex':sex,'Signature':friend['Signature']}
//    print(temp_list)
//
//	#写入excel
//	worksheet.write_string(row, col, friend['NickName'])
//            worksheet.write_string(row, col+1, sex)
//            worksheet.write_string(row, col+2, friend['Signature'])
//
//    col = 0
//    row = row + 1
//            #	time.sleep(5)
//
//            workbook.close()
    public static void writeGetFriendsMsg(){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(rootPath+"\\python_script\\getFriendsMsg.py"));
            out.write("#coding=GB2312\r\n");
            out.write("import itchat, time, json, xlsxwriter\r\n");
            out.write("itchat.auto_login(hotReload=True)\r\n");
            out.write("friendList = itchat.get_friends(update=True)[:]\r\n");
            out.write("workbook = xlsxwriter.Workbook('D:\\\\wechatsup\\\\user_data.xlsx')\r\n");
            out.write("worksheet = workbook.add_worksheet()\r\n");
            out.write("row = 0\r\n");
            out.write("col = 0\r\n");
            out.write("for friend in friendList:\r\n");
            out.write("\tsex='女'\r\n");
            out.write("\tif friend['Sex']==1:\r\n");
            out.write("\t\tsex = '男'\r\n");
            out.write("\ttemp_list = {'NickName':friend['NickName'],'Sex':sex,'Signature':friend['Signature']}\r\n");
            out.write("\tworksheet.write_string(row, col, friend['UserName'])\r\n");
            out.write("\tworksheet.write_string(row, col+1, friend['NickName'])\r\n");
            out.write("\tworksheet.write_string(row, col+2, sex)\r\n");
            out.write("\tworksheet.write_string(row, col+3, friend['Signature'])\r\n");
            out.write("\tcol = 0\r\n");
            out.write("\trow = row + 1\r\n");
            out.write("workbook.close()\r\n");
            out.write("\r\n");
            out.close();
            System.out.println("用于打印好友信息的EXCEL脚本文件创建成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeRepeatMachine(){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(rootPath+"\\python_script\\repeatMachine.py"));
            out.write("#encoding=utf-8\r\n");
            out.write("import itchat\r\n");
            out.write("@itchat.msg_register(itchat.content.TEXT)\r\n");
            out.write("def print_content(msg):\r\n");
            out.write("\treturn msg['Content']\r\n");
            out.write("itchat.auto_login(hotReload=True)\r\n");
            out.write("itchat.run()\r\n");
            out.close();
            System.out.println("用于编程复读机的脚本文件创建成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeRobot(String key,String resp_user){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(rootPath+"\\python_script\\robot.py"));
            out.write("#coding=utf-8\r\n");
            out.write("import requests\r\n");
            out.write("import itchat\r\n");
            out.write("KEY = '"+key+"'\r\n");
            out.write("resp_user = '"+resp_user+"'\r\n");
            out.write("def get_response(msg):\r\n");
            out.write("    apiUrl = 'http://www.tuling123.com/openapi/api'\r\n");
            out.write("    data = {\r\n");
            out.write("        'key'    : KEY,\r\n");
            out.write("        'info'   : msg,\r\n");
            out.write("        'userid' : 'wechat-robot',\r\n");
            out.write("    }\r\n");
            out.write("    try:\r\n");
            out.write("        r = requests.post(apiUrl, data=data).json()\r\n");
            out.write("        return r.get('text')\r\n");
            out.write("    except:\r\n");
            out.write("        return\r\n");
            out.write("@itchat.msg_register(itchat.content.TEXT)\r\n");
            out.write("def tuling_reply(msg):\r\n");
            out.write("    if(msg['FromUserName']==resp_user):\r\n");
            out.write("        defaultReply = 'I received: ' + msg['Text']\r\n");
            out.write("        reply = get_response(msg['Text'])\r\n");
            out.write("        return reply or defaultReply\r\n");
            out.write("    else:\r\n");
            out.write("        return\r\n");
            out.write("itchat.auto_login(hotReload=True)\r\n");
            out.write("itchat.run()\r\n");
            out.write("\r\n");
            out.close();
            System.out.println("用于机器人的脚本文件创建成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void writeReplyOneFriend(String text,String resp_user){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(rootPath+"\\python_script\\autoReplyOneFriend.py"));
            out.write("#encoding=utf-8\r\n");
            out.write("import itchat\r\n");
            out.write("resp_user = '"+resp_user+"'\r\n");
            out.write("text = '"+text+"'\r\n");
            out.write("@itchat.msg_register(itchat.content.TEXT)\r\n");
            out.write("def print_content(msg):\r\n");
            out.write("\tif(msg['FromUserName']==resp_user):\r\n");
            out.write("\t\treturn text\r\n");
            out.write("itchat.auto_login(hotReload=True)\r\n");
            out.write("itchat.run()\r\n");
            out.close();
            System.out.println("用于自动回复某人信息的脚本文件创建成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeHarass(String text,String repeat_num,String to_user){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(rootPath+"\\python_script\\harass.py"));
            out.write("#coding=GB2312\r\n");
            out.write("import itchat\r\n");
            out.write("to_user = '"+to_user+"'\r\n");
            out.write("repeat_num = "+repeat_num+"\r\n");
            out.write("text = '"+text+"'\r\n");
            out.write("itchat.auto_login(True)\r\n");
            out.write("friendList = itchat.get_friends(update=True)[1:]\r\n");
            out.write("for friend in friendList:\r\n");
            out.write("\tif(friend['NickName']==to_user):\r\n");
            out.write("\t\tfor i in range(repeat_num):\r\n");
            out.write("\t\t\titchat.send(text,friend['UserName'])\r\n");
            out.close();
            System.out.println("用于骚扰的脚本文件创建成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


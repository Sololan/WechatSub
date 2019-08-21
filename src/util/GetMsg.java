package util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pojo.FriendsMsg;
import pojo.ProcessList;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Map;

public class GetMsg {
    //public static String  rootPath = System.getProperty("user.dir");
    public static String  rootPath ="D:\\wechatsup";
    public static ObservableList<FriendsMsg> getFridentsMsg() throws Exception{
        ArrayList<FriendsMsg> friList = new ArrayList<FriendsMsg>();
        ObservableList<FriendsMsg> friendsMsgs = FXCollections.observableArrayList();
        //处理表格
        File xlsFile = new File(rootPath+"\\user_data.xlsx");
        /**
         * 这里根据不同的excel类型
         * 可以选取不同的处理类：
         *          1.XSSFWorkbook
         *          2.HSSFWorkbook
         */
        // 获得工作簿
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(xlsFile));

        // 获得工作表
        XSSFSheet sheet = workbook.getSheetAt(0);

        int rows = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < rows; i++) {
            FriendsMsg msg = new FriendsMsg();
            // 获取第i行数据
            XSSFRow sheetRow = sheet.getRow(i);
            // 获取第0格数据
            XSSFCell cell = sheetRow.getCell(0);
            msg.setUserName(cell.toString());
            cell = sheetRow.getCell(1);
            msg.setNickName(cell.toString());
            cell = sheetRow.getCell(2);
            msg.setSex(cell.toString());
            cell = sheetRow.getCell(3);
            msg.setSig(cell.toString());
            friendsMsgs.add(msg);
            friList.add(msg);
        }
        return friendsMsgs;
    }
    public static ObservableList<ProcessList> getProcess(Map<String,Process> procMap) throws Exception{
        ObservableList<ProcessList> list = FXCollections.observableArrayList();
        for(String mapkey : procMap.keySet()){
            ProcessList processList = new ProcessList(mapkey,procMap.get(mapkey).toString());
            list.add(processList);
        }
        System.out.println(list);
        return list;
    }
}

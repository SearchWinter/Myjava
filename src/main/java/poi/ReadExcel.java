package poi;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.IOException;

/**
 * Created by anjunli on  2022/5/20
 * POI 读取Excel文件
 * https://blog.csdn.net/qq_34474324/article/details/97627474
 **/
public class ReadExcel {
    public static void main(String[] args) throws IOException {
        //创建Excel，读取文件内容
        File file = new File("E:\\poi_test.xls");
        HSSFWorkbook workbook = new HSSFWorkbook(FileUtils.openInputStream(file));

        //两种方式读取工作表
        //  HSSFSheet sheet=workbook.getSheet("Sheet0");
        HSSFSheet sheetAt = workbook.getSheetAt(0);

        //获取sheet中最后一行行号
        int lastRowNum = sheetAt.getLastRowNum();
        System.out.println("lastRowNum: " + lastRowNum);

        for (int i = 0; i <= lastRowNum; i++) {
            HSSFRow row = sheetAt.getRow(i);
            //获取当前行最后单元格列号
            short lastCellNum = row.getLastCellNum();
            if (i == 0) {
                System.out.println("lastCellNum: " + lastCellNum);
            }
            for (int j = 0; j < lastCellNum; j++) {
                HSSFCell cell = row.getCell(j);
                String cellValue = cell.getStringCellValue();
                System.out.print(cellValue + " ");
            }
            System.out.println();
        }
    }
}

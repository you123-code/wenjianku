package com.demo.testdatasource.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

@Slf4j
public class ExcelUtils {
    public static String[][] parseExcel(MultipartFile file, int firstrow, String dateFmt) throws Exception {
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        if (!".xlsx".equalsIgnoreCase(suffix)) {
            throw new Exception( "请上传xlsx格式文件");
        }
        List<String[]> result = new ArrayList<>();
        int rowSize = 0;
        BufferedInputStream in = new BufferedInputStream(file.getInputStream());
        XSSFWorkbook wb = new XSSFWorkbook(in);
        XSSFCell cell = null;
        //没有工作表直接返回
        if (wb.getNumberOfSheets() > 0) {
            XSSFSheet st;
            if (wb.getNumberOfSheets() > 1) {
                //若解析到多个sheet表，取名为Sheet0那一个
                st = wb.getSheet("Sheet0");
            } else {
                st = wb.getSheetAt(0);
            }
            if (Objects.isNull(st)) {
                throw new Exception("检测到多个工作表并且找不到默认表名为Sheet0的工作表");
            }
            for (int rowIndex = firstrow; rowIndex <= st.getLastRowNum(); rowIndex++) {
                XSSFRow row = st.getRow(rowIndex);
                if (row == null) {
                    continue;
                }
                int tempRowSize = row.getLastCellNum();
                if (tempRowSize > rowSize) {
                    rowSize = tempRowSize;
                }
                String[] values = new String[rowSize];
                Arrays.fill(values, "");
                boolean hasValue = false;
                for (short columnIndex = 0; columnIndex < row.getLastCellNum(); columnIndex++) {
                    String value = "";
                    cell = row.getCell(columnIndex);
                    if (cell != null) {
                        switch (cell.getCellTypeEnum()) {
                            case STRING://读取的格式为字符串
                                value = cell.getStringCellValue();
                                //去空判断
                                String trim = value.trim();
                                if ("是".equals(value) || "√".equals(value)) {
                                    value = "Y";
                                }
                                if ("否".equals(value) || "X".equals(value)) {
                                    value = "N";
                                }

                                if (Pattern.matches("\\d{4}-\\d{2}-\\d{2}", trim) || Pattern.matches("\\d{4}-\\d{1}-\\d{2}", trim)
                                        || Pattern.matches("\\d{4}-\\d{2}-\\d{1}", trim)) {
                                    value = "dateFormatError";
                                }


                                ////转化区划
                                //if (!Objects.isNull(RequestToColumn.changeRegionToColum(trim))) {
                                //    value = RequestToColumn.changeRegionToColum(trim);
                                //}

                                break;
                            case NUMERIC://读取的格式为数组
                                //如果格式为日期格式，自定义格式输出
                                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                    Date date = cell.getDateCellValue();
                                    if (date != null) {
//                                        value = new SimpleDateFormat(dateFmt).format(date);
//                                        value = new SimpleDateFormat("yyyy/MM/dd").format(date);
                                        //转换为时间戳
                                        value = String.valueOf(date.getTime());
                                    } else {
                                        value = "日期错误";
                                    }
                                } else {
                                    //如果格式为数值，自定义格式输出
                                    //去掉小数
                                    DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
                                    value = decimalFormat.format(cell.getNumericCellValue());
                                }
                                break;
                            case FORMULA:
                                // 导入时如果为公式生成的数据则无值
                                value = "";
                                break;
                            // 导入时如果为空
                            case BLANK:
                                break;
                            case ERROR:
                                value = "";
                                break;
                            // 导入时如果为BOOLEAN型 自定义格式输出
                            case BOOLEAN:
                                value = (cell.getBooleanCellValue() ? "Y" : "N");
                                break;
                            default:
                                value = "";
                        }
                        String dateString = cell.toString();
                        if (Pattern.matches("\\d{4}/\\d{2}/\\d{2}", dateString)) {
                            SimpleDateFormat form = new SimpleDateFormat("yyyy/MM/dd");
                            Date ifDate = null;
                            try {
                                ifDate = form.parse(dateString);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            value = String.valueOf(ifDate.getTime());
                        }
                    }
                    values[columnIndex] = trim(value);
                    hasValue = true;
                }
                if (hasValue && !StringUtils.isAllEmpty(values)) {
                    result.add(values);
                }
            }

            String[][] returnArray = new String[result.size()][rowSize];
            for (int i = 0; i < returnArray.length; i++) {
                returnArray[i] = result.get(i);
            }
            in.close();
            return returnArray;
        }
        in.close();//关闭文件流
        return null;
    }

    /**
     * 去掉字符串右边的空格
     *
     * @param str 要处理的字符串
     * @return 处理后的字符串
     */
    public static String rightTrim(String str) {
        if (str == null) {
            return "";
        }
        int length = str.length();
        for (int i = length - 1; i >= 0; i--) {
            if (str.charAt(i) != 0x20) {
                break;
            }
            length--;
        }
        return str.substring(0, length);
    }

    /**
     * 去掉字符串两边的空格
     *
     * @param str 要处理的字符串
     * @return 处理后的字符串
     */
    public static String trim(String str) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        return str.trim();
    }

    public static int getColumnIndex(String[] heads, String name) {
        int index = 0;
        for (String head : heads) {
            if (name.equalsIgnoreCase(head.trim())) {
                return index;
            }
            index++;
        }
        return -1;
    }

    /**
     * 检查必须导入列是否导入
     *
     * @param heads
     * @param args
     */
    public static void checkImpHeads(String[] heads, String... args) throws Exception {
        List<String> errStr = new ArrayList<>();
        for (int i = 0; i < args.length; i += 1) {
            String heandColumn = String.valueOf(args[i]);
            if (getColumnIndex(heads, heandColumn) == -1) {
                errStr.add(args[i]);
            }
        }
        if (errStr.size() > 0) {
            throw new Exception( String.format("[%s]为必须导入列。", String.join("、", errStr)));
        }
    }

    public static void checkDemoImpData(String[] demoTitles, String[][] data) throws Exception {
        if (demoTitles.length != data[0].length) {
            log.error("导入数据与模板不匹配: {} | {}", demoTitles, data[0]);
            throw new Exception("导入数据与模板不匹配");
        }
        List<String> demoTitlesList = Arrays.asList(demoTitles);
        for (String dataTitle : data[0]) {
            if (!demoTitlesList.contains(dataTitle)) {
                log.error("导入数据与模板不匹配: {}", dataTitle);
                throw new Exception("导入数据与模板不匹配");
            }
        }
    }

    public static void checkDemoImpData(Set<String> demoTitlesSet, String[][] data, Set<String> checkTableHead) throws Exception {
        if (demoTitlesSet.size() != data[0].length) {
            log.error("导入数据与模板不匹配: {} | {}", demoTitlesSet, data[0]);
            throw new Exception("导入数据与模板不匹配");
        }
        Set<String> dataSet = new HashSet<>(Arrays.asList(data[0]));
        if (!dataSet.containsAll(demoTitlesSet)) {
            log.error("导入数据与模板不匹配,excel表头为: {},设置的表头为:{}", dataSet, demoTitlesSet);
            throw new Exception("导入数据与模板不匹配");
        }
        if (Objects.nonNull(checkTableHead)) {
            for (String s : checkTableHead) {
                if (!dataSet.contains(s)) {
                    throw new Exception("未设置" + s + "表头");
                }
            }
        }
    }

    //excel检查并返回错误提示专用方法
    public static boolean checkEmpty(Object object, String tips, List<String> checkReturnList, int i) {
        if (Objects.nonNull(object)) {
            if (object instanceof String) {
                String check = (String) object;
                if (StringUtils.isBlank(check)) {
                    checkReturnList.add(String.format("表格第%s行数据的" + tips + "为空，请检查数据。", i));
                    return false;
                }
            } else if (object instanceof Map) {
                Map<String, Object> check = (Map<String, Object>) object;
                for (Map.Entry<String, Object> entry : check.entrySet()) {
                    if (Objects.nonNull(entry.getValue())) {
                        if (StringUtils.isBlank(entry.getValue().toString())) {
                            checkReturnList.add(String.format("表格第%s行数据的" + entry.getKey() + "为空，请检查数据。", i));
                            return false;
                        }
                    } else {
                        checkReturnList.add(String.format("表格第%s行数据的" + entry.getKey() + "为空，请检查数据。", i));
                        return false;
                    }
                }
            } else {
                if (StringUtils.isBlank(object.toString())) {
                    checkReturnList.add(String.format("表格第%s行数据的" + tips + "为空，请检查数据。", i));
                    return false;
                }
            }
        } else {
            checkReturnList.add(String.format("表格第%s行数据的" + tips + "为空，请检查数据。", i));
            return false;
        }
        return true;

    }

    //excel检查并返回错误提示专用方法
    public static boolean checkMatchData(List objects, String Clunmtips, List<String> checkReturnList, int i) {
        if (objects.isEmpty()) {
            checkReturnList.add(String.format("表格第%s行" + Clunmtips + "需要与提交数据一致。", i));
            return false;
        }
        if (objects.size() > 1) {
            checkReturnList.add(String.format("表格第%s行" + Clunmtips + "只能匹配一条数据，请检查数据。", i));
            return false;
        }
        return true;
    }


    public static void exportExcel(Map<String, String> headName, List list, String excelName, HttpServletResponse response) throws Exception {
        int columnWidth = headName.size();
        String[] titles = headName.values().toArray(new String[headName.values().size()]);
        String[][] values = new String[list.size()][columnWidth];
        int offset = 0;
        int columnOffset = 0;
        OutputStream os = null;
        try {
            for (Object o : list) {
                Map<String, Object> map = null;
                if (o instanceof Map) {
                    map = (Map) o;
                } else {
                    try {
                        //map = Object2Map.Obj2Map(o);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                columnOffset = 0;
                for (Map.Entry<String, String> entry : headName.entrySet()) {
                    if (columnOffset == 0 && headName.containsKey("rowId")) {
                        values[offset][columnOffset++] = (offset + 1) + "";
                        continue;
                    }
                    if (map.get(entry.getKey()) != null) {
//                    String key = entry.getKey();
//                    if (!CollectionUtils.isEmpty(dateColumnToTime) &&dateColumnToTime.contains(key)){
//                        values[offset][columnOffset++] =  new SimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(map.get(entry.getKey()).toString()));
//                        continue;
//                    }
//                    if (!CollectionUtils.isEmpty(stateColumnChinese) && stateColumnChinese.contains(key)){
//                        if ("Y".equals(map.get(entry.getKey()).toString())){
//                            values[offset][columnOffset++] = "是";
//                        }
//                        if ("N".equals(map.get(entry.getKey()).toString())){
//                            values[offset][columnOffset++] = "否";
//                        }
//                        continue;
//                    }
//                    if (!CollectionUtils.isEmpty(stateColumnSymbol) &&stateColumnSymbol.contains(key)){
//                        if ("Y".equals(map.get(entry.getKey()).toString())){
//                            values[offset][columnOffset++] = "√";
//                        }
//                        if ("N".equals(map.get(entry.getKey()).toString())){
//                            values[offset][columnOffset++] = "X";
//                        }
//                        continue;
//                    }
                        Object val = map.get(entry.getKey());
                        if (val instanceof Double) {
                            DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
                            String format = decimalFormat.format(val);
                            values[offset][columnOffset++] = format;
                        } else {
                            values[offset][columnOffset++] = map.get(entry.getKey()).toString();
                        }
                    } else {
                        values[offset][columnOffset++] = "";
                    }
                }
/*            if (columnOffset != columnWidth) {
                //ExceptionEnum.BAD_REQUEST.throwException("数据错误 offset：" + offset);
                return;
            }*/
                offset++;
            }

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String date = format.format(new Date());
            String fileName;
            if (StringUtils.isNotEmpty(excelName)) {
                fileName = excelName + "_" + date + ".xlsx";
            } else {
                fileName = date + ".xlsx";
            }
            fileName = URLEncoder.encode(fileName, "UTF-8");
            XSSFWorkbook workbook = ExcelUtils.getXSSFWorkbookDemo(titles, values, "yyyy/MM/dd");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
            os = response.getOutputStream();
            workbook.write(os);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("{}", e.getMessage(), e);
            throw new Exception("ouou");
        } finally {
            try {
                os.flush();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static XSSFWorkbook getXSSFWorkbookDemo(String[] titles, String[][] values, String dateFmt) {
        XSSFWorkbook wb = new XSSFWorkbook();
        try {
            XSSFSheet sheet = wb.createSheet();
            sheet.createFreezePane(0, 1);// 冻结
            int width = 6000;
            for (int i = 0; i < titles.length; i++) {
                sheet.setColumnWidth(i, i == 0 ? width / 2 : width);
            }
            XSSFCellStyle sheetStyle = getSheetStyle(wb);
            XSSFCellStyle columnHeadStyle = getColumnHeadStyle(wb);
            XSSFCellStyle datestyle = getDateStyle(wb, dateFmt);
            XSSFCellStyle centerstyle = getCenterStyle(wb);
            centerstyle.setWrapText(true);
            // 设置列的样式
            for (int i = 0; i < titles.length; i++) {
                sheet.setDefaultColumnStyle((short) i, sheetStyle);
            }
            XSSFRow row = sheet.createRow(0);
            // 设置行高
            row.setHeight((short) 550);

            XSSFCell cell = null;
            for (int i = 0; i < titles.length; i++) {
                cell = row.createCell(i);
                cell.setCellType(CellType.STRING);
                cell.setCellValue(titles[i]);
                cell.setCellStyle(columnHeadStyle);
            }
            for (int i = 0; i < values.length; i++) {
                row = sheet.createRow(i + 1);
                for (int j = 0; j < values[i].length; j++) {
                    cell = row.createCell(j);
                    String val = values[i][j];
                    if (StringUtils.isNotBlank(val) && val.startsWith("|") && val.endsWith("|")) {//下拉框数据要求：|JAVA|C++|JS|
                        createDropDownList(sheet, val.split("\\|"), i + 1, i + 1, j, j);
                    } else if (DateUtils.isValidDate(val, dateFmt)) {
                        SimpleDateFormat sdf = new SimpleDateFormat(dateFmt);
                        Date date = null;
                        try {
                            date = sdf.parse(val);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        cell.setCellValue(date);
                        cell.setCellStyle(datestyle);
                    } else {
                        if (StringUtils.isNotBlank(val)) {//全数字设置为文本，避免科学计数
                            if (val.matches("[0-9]+") || val.matches("(\\d+\\.\\d+)")) {
                                DataFormat format = wb.createDataFormat();
                                centerstyle.setDataFormat(format.getFormat("@"));
                            }
                        }

                        cell.setCellType(CellType.STRING);
                        cell.setCellValue(val);
                        cell.setCellStyle(centerstyle);

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wb;
    }


    public static XSSFCellStyle getSheetStyle(XSSFWorkbook wb) {
        // Sheet样式
        XSSFCellStyle sheetStyle = wb.createCellStyle();
        // 背景色的设定
//        sheetStyle.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        // 前景色的设定
//        sheetStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        // 填充模式
//        sheetStyle.setFillPattern(FillPatternType.FINE_DOTS);
        return sheetStyle;
    }

    public static XSSFCellStyle getColumnHeadStyle(XSSFWorkbook wb) {
        // 列头
        XSSFFont columnHeadFont = wb.createFont();
        columnHeadFont.setFontName("黑体");
        columnHeadFont.setFontHeightInPoints((short) 13);
        columnHeadFont.setBold(true);
        // 列头的样式
        XSSFCellStyle columnHeadStyle = wb.createCellStyle();
        columnHeadStyle.setFont(columnHeadFont);
        columnHeadStyle.setAlignment(HorizontalAlignment.CENTER); // 左右居中
        columnHeadStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 上下居中
        columnHeadStyle.setLocked(true);
        columnHeadStyle.setWrapText(true);
        columnHeadStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());// 左边框的颜色
        columnHeadStyle.setBorderLeft(BorderStyle.THIN);// 边框的大小
        columnHeadStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());// 右边框的颜色
        columnHeadStyle.setBorderRight(BorderStyle.THIN);// 边框的大小
        columnHeadStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex()); // 设置单元格的边框颜色
        columnHeadStyle.setBorderBottom(BorderStyle.THICK); // 设置单元格的边框为粗体
        // 设置单元格的背景颜色（单元格的样式会覆盖列或行的样式）
        // 背景色的设定
        columnHeadStyle.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        // 前景色的设定
//        columnHeadStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        // 填充模式
//        columnHeadStyle.setFillPattern(FillPatternType.FINE_DOTS);
        return columnHeadStyle;
    }

    public static XSSFCellStyle getCenterStyle(XSSFWorkbook wb) {
        XSSFFont font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 10);
        // 普通样式
        XSSFCellStyle centerstyle = wb.createCellStyle();
        centerstyle.setFont(font);
        centerstyle.setAlignment(HorizontalAlignment.CENTER); // 左右居中
        centerstyle.setVerticalAlignment(VerticalAlignment.CENTER);// 上下居中
//        centerstyle.setWrapText(true);
        centerstyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        centerstyle.setBorderLeft(BorderStyle.THIN);
        centerstyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        centerstyle.setBorderRight(BorderStyle.THIN);
        centerstyle.setBorderBottom(BorderStyle.THIN); // 设置单元格的边框为粗体
        centerstyle.setBottomBorderColor(IndexedColors.BLACK.getIndex()); // 设置单元格的边框颜色．
        centerstyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());// 设置单元格的背景颜色．
        return centerstyle;
    }

    public static XSSFCellStyle getDateStyle(XSSFWorkbook wb, String dateFmt) {
        XSSFFont font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 10);
        // date样式
        XSSFCellStyle datestyle = wb.createCellStyle();
        datestyle.setFont(font);
        datestyle.setAlignment(HorizontalAlignment.CENTER); // 左右居中
        datestyle.setVerticalAlignment(VerticalAlignment.CENTER);// 上下居中
        datestyle.setWrapText(true);
        datestyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        datestyle.setBorderLeft(BorderStyle.THIN);
        datestyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        datestyle.setBorderRight(BorderStyle.THIN);
        datestyle.setBorderBottom(BorderStyle.THIN); // 设置单元格的边框为粗体
        datestyle.setBottomBorderColor(IndexedColors.BLACK.getIndex()); // 设置单元格的边框颜色．
        datestyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());// 设置单元格的背景颜色．
        CreationHelper createHelper = wb.getCreationHelper();
        if (dateFmt.equalsIgnoreCase("yyyy-MM-dd HH:mm:ss.SSS")) {
            dateFmt = "yyyy-MM-dd HH:mm:ss.000";
        }
        XSSFDataFormat format = wb.createDataFormat();
//        datestyle.setDataFormat(createHelper.createDataFormat().getFormat(dateFmt));
        datestyle.setDataFormat(format.getFormat(dateFmt));
        return datestyle;
    }

    /**
     * 下拉选择框
     */
    private static void createDropDownList(Sheet sheet, String[] values, int firstRow, int lastRow, int firstCol, int lastCol) {
        DataValidationHelper helper = sheet.getDataValidationHelper();
        CellRangeAddressList addressList = new CellRangeAddressList(firstRow, lastRow, firstCol, lastCol);
        DataValidationConstraint constraint = helper.createExplicitListConstraint(values);
        DataValidation dataValidation = helper.createValidation(constraint, addressList);
        dataValidation.setSuppressDropDownArrow(true);
        dataValidation.setShowErrorBox(true);
        sheet.addValidationData(dataValidation);
    }

    /**
     * demo导出
     */
    public static void demo(String[] titles, String[][] values, String fileName, HttpServletResponse response) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("_yyyy-MM-ddHH");
            String date = format.format(new Date());
            fileName = URLEncoder.encode(fileName + date + ".xlsx", "UTF-8");
            XSSFWorkbook workbook = ExcelUtils.getXSSFWorkbookDemo(titles, values, "yyyy/MM/dd");
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
            OutputStream os = response.getOutputStream();
            workbook.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object excel2Request(Map<String, String> headColumn, String[][] data, int rowIndex, Object object, List<String> checkReturnList, List<String> nuNullCheck) {
        for (Map.Entry<String, String> column : headColumn.entrySet()) {
            int columnIndex = ExcelUtils.getColumnIndex(data[0], column.getValue());
            if (-1 == columnIndex) continue;
            String fieldValue = data[rowIndex][columnIndex];
            if (Objects.isNull(nuNullCheck) || !nuNullCheck.contains(column.getValue())) {
                if (StringUtils.isBlank(fieldValue)) {
                    checkReturnList.add(String.format("表格第%s行[%s]列为空，请检查表格。", rowIndex, column.getValue()));
                    continue;
                }
                if (fieldValue.contains(" ")) {
                    checkReturnList.add(String.format("表格第%s行[%s]列数据有空格，请检查表格。", rowIndex, column.getValue()));
                    continue;
                }
            }
            if (fieldValue.equals("日期错误")) {
                checkReturnList.add(String.format("表格第%s行[%s]日期格式错误，请检查表格。", rowIndex, column.getValue()));
            }

            //ReflexUtils.setFieldValueByFieldName(column.getKey(), column.getValue(), true, fieldValue.trim(), object, checkReturnList, rowIndex, String.class, Long.class, Double.class, Integer.class);
        }
        return object;
    }


    public static Object excel3Request(Map<String, String> headColumn, String[][] data, int rowIndex, Object object, List<String> checkReturnList, List<String> nuNullCheck) {
        for (Map.Entry<String, String> column : headColumn.entrySet()) {
            int columnIndex = ExcelUtils.getColumnIndex(data[0], column.getValue());
            if (-1 == columnIndex) continue;
            String fieldValue = data[rowIndex][columnIndex];
            if (Objects.isNull(nuNullCheck) || !nuNullCheck.contains(column.getValue())) {
                if (StringUtils.isBlank(fieldValue)) {
                    checkReturnList.add(String.format("表格第%s行[%s]列为空，请检查表格。", rowIndex, column.getValue()));
                    continue;
                }
                if (fieldValue.contains(" ")) {
                    checkReturnList.add(String.format("表格第%s行[%s]列数据有空格，请检查表格。", rowIndex, column.getValue()));
                    continue;
                }
            }
            if (fieldValue.equals("日期错误")) {
                checkReturnList.add(String.format("表格第%s行[%s]日期格式错误，请检查表格。", rowIndex, column.getValue()));
            }

            //ReflexUtils.setFieldValueByFieldNameHedging(column.getKey(), column.getValue(), true, fieldValue.trim(), object, checkReturnList, rowIndex, String.class, Long.class, Double.class, Integer.class);
        }
        return object;
    }

    public static void arrayToObject(Map<String, String> headColumn, String[][] data, int rowIndex, Object object, List<String> checkReturnList,Set<String> checkColoum) {
        for (Map.Entry<String, String> column : headColumn.entrySet()) {
            int columnIndex = ExcelUtils.getColumnIndex(data[0], column.getValue());
            if (-1 == columnIndex) continue;
            String fieldValue = data[rowIndex][columnIndex];
            if (StringUtils.isBlank(fieldValue) || fieldValue.contains(" ")) {
                if(checkColoum.contains(column.getKey())){
                    checkReturnList.add(String.format("表格第%s行关键字段[%s]不能为空，请检查表格。", rowIndex,column.getValue()));
                    return;
                }
                continue;
            }
            if (fieldValue.equals("日期错误")) {
                checkReturnList.add(String.format("表格第%s行[%s]日期格式错误，请检查表格。", rowIndex, column.getValue()));
            }
            //ReflexUtils.setFieldValueByFieldName(column.getKey(), column.getValue(), true, fieldValue.trim(), object, checkReturnList, rowIndex, String.class, Long.class, Double.class, Integer.class);
        }
    }

    //public static String[][] fileToArray(MultipartFile file) {
    //    if (file == null) {
    //        throw new ZqlianException(RongDanErrorCodes.BAD_REQUEST);
    //    }
    //    String[][] data = null;
    //    try {
    //        data = ExcelUtils.parseExcel(file, 0, "yyyy/MM/dd");
    //        if (Objects.isNull(data)) {
    //            throw new ZqlianException(RongDanErrorCodes.BAD_REQUEST, "excel中解析不到sheet表,请检查excel文件或重新下载模板");
    //        }
    //        return data;
    //    } catch (IOException e) {
    //        log.error(e.getMessage(), e);
    //        throw new ZqlianException(RongDanErrorCodes.BAD_REQUEST);
    //    }
    //}
}
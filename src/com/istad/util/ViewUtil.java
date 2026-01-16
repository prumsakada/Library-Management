package com.istad.util;

import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;

public class ViewUtil {
    public static void printMenu() {
        Table table = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE);
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        table.addCell("App Menu", cellStyle);
        table.addCell("1) List All  2) Search  3) Add new  4) Update", cellStyle);
        table.addCell("5) Delete  0) Exit", cellStyle);
        table.setColumnWidth(0, 50, 100);

        System.out.println(table.render());
    }

    public static void print(String text, boolean isNewLine) {
        if (isNewLine)
            System.out.println(text);
        else
            System.out.print(text);
    }

    public static void printHeader(String text) {
        Table table = new Table(1,
                BorderStyle.UNICODE_ROUND_BOX_WIDE);
        table.addCell(text);
        print(table.render(), true);
    }
//    public static void printProductList(List<Product> productList) {
//
//        Table table = new Table(6, BorderStyle.UNICODE_ROUND_BOX_WIDE);
//
//
//        table.addCell("ID");
//        table.addCell("CODE");
//        table.addCell("NAME");
//        table.addCell("PRICE");
//        table.addCell("QTY");
//        table.addCell("STATUS");
//
//
//        for (Product products : productList) {
//            table.addCell(products.getId().toString());
//            table.addCell(products.getCode());
//            table.addCell(products.getName());
//            table.addCell(products.getPrice().toString());
//            table.addCell(products.getQty().toString());
//            table.addCell(products.getStatus().toString());
//        }
//
//        print(table.render(), true);
//    }
}

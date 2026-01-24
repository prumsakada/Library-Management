package com.istad.util;

import com.istad.model.Book;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;

public class ViewUtil {

    public static void showMenu() {
        Table table = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE);
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        table.addCell("Welcome to Library Management",cellStyle);
        table.addCell("1) Book Management");
        table.addCell("2) Member Management");
        table.addCell("0) Exit");
        table.setColumnWidth(0, 50, 100);

        System.out.println(table.render());
    }

    public static void bookMenu() {
        Table table = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE);
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        table.addCell("App Menu", cellStyle);
        table.addCell("1) List All  2) Search  3) Add new  4) Update", cellStyle);
        table.addCell("5) Delete  0) Back to System", cellStyle);
        table.setColumnWidth(0, 50, 100);

        System.out.println(table.render());
    }
    public static void memberMenu() {
        Table table = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE);
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        table.addCell("App Menu", cellStyle);
        table.addCell("1) List All  2) Search  3) Add new  4) Update", cellStyle);
        table.addCell("5) Activate/Deactivate member  0) Back to System", cellStyle);
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
    public static void printBookList(List<Book> bookList) {

        Table table = new Table(10, BorderStyle.UNICODE_ROUND_BOX_WIDE);


        table.addCell("ID");
        table.addCell("CODE");
        table.addCell("TITLE");
        table.addCell("AUTHOR");
        table.addCell("CATEGORY");
        table.addCell("PUBLISHER");
        table.addCell("PUBLISH_YEAR");
        table.addCell("PRICE");
        table.addCell("QTY");
        table.addCell("STATUS");


        for (Book books : bookList) {
            table.addCell(books.getBookId().toString());
            table.addCell(books.getBookCode());
            table.addCell(books.getTitle());
            table.addCell(books.getAuthor());
            table.addCell(books.getCategory());
            table.addCell(books.getPublisher());
            table.addCell(books.getPublishYear().toString());
            table.addCell(books.getPrice().toString());
            table.addCell(books.getQty().toString());
            table.addCell(books.getStatus());
        }

        print(table.render(), true);
    }
}

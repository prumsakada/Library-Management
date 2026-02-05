package com.istad.util;

import com.istad.model.Book;
import com.istad.model.Member;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;

public class ViewUtil {

    public static void showMenu() {
        Table table = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE);
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        table.addCell(ColorUtil.CYAN+"Welcome to Library Management"+ColorUtil.RESET, cellStyle);
        table.addCell(ColorUtil.BLUE+"1) Book Management"+ColorUtil.RESET);
        table.addCell(ColorUtil.BLUE+"2) Member Management"+ColorUtil.RESET);
        table.addCell(ColorUtil.RED+"0) Exit"+ColorUtil.RESET);
        table.setColumnWidth(0, 50, 100);

        System.out.println(table.render());
    }

    public static void bookMenu() {
        Table table = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE);
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        table.addCell(ColorUtil.CYAN + "App Menu" + ColorUtil.RESET, cellStyle);
        table.addCell(ColorUtil.BLUE + "1) List All  2) Search  3) Add new  4) Update" + ColorUtil.RESET, cellStyle);
        table.addCell(ColorUtil.BLUE + "5) Delete  6) Check Available" + ColorUtil.RESET + ColorUtil.RED + "  " + "0) Back"
                + ColorUtil.RESET, cellStyle);
        table.setColumnWidth(0, 50, 100);

        System.out.println(table.render());
    }

    public static void memberMenu() {
        Table table = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE);
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        table.addCell(ColorUtil.CYAN+"App Menu"+ColorUtil.RESET, cellStyle);
        table.addCell(ColorUtil.BLUE+"1) List All  2) Search  3) Add new  4) Update"+ColorUtil.RESET, cellStyle);
        table.addCell(ColorUtil.BLUE+"5) Activate/Deactivate member"+ColorUtil.RESET+"  "+
              ColorUtil.RED+ "  0) Back"+ColorUtil.RESET, cellStyle);
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

        if (bookList == null || bookList.isEmpty()) {
            print("No books found.", true);
            return;
        }

        int pageSize = 10;
        int currentPage = 0;
        int totalPages = (int) Math.ceil((double) bookList.size() / pageSize);

        while (true) {

            int startIndex = currentPage * pageSize;
            int endIndex = Math.min(startIndex + pageSize, bookList.size());

            Table table = new Table(10, BorderStyle.UNICODE_ROUND_BOX_WIDE);

            table.addCell(ColorUtil.BLUE+"ID"+ColorUtil.RESET);
            table.addCell(ColorUtil.BLUE+"CODE"+ColorUtil.RESET);
            table.addCell(ColorUtil.BLUE+"TITLE"+ColorUtil.RESET);
            table.addCell(ColorUtil.BLUE+"AUTHOR"+ColorUtil.RESET);
            table.addCell(ColorUtil.BLUE+"CATEGORY"+ColorUtil.RESET);
            table.addCell(ColorUtil.BLUE+"PUBLISHER"+ColorUtil.RESET);
            table.addCell(ColorUtil.BLUE+"YEAR"+ColorUtil.RESET);
            table.addCell(ColorUtil.BLUE+"PRICE"+ColorUtil.RESET);
            table.addCell(ColorUtil.BLUE+"QTY"+ColorUtil.RESET);
            table.addCell(ColorUtil.BLUE+"STATUS"+ColorUtil.RESET);

            for (int i = startIndex; i < endIndex; i++) {
                Book book = bookList.get(i);

                table.addCell(String.valueOf(book.getBookId()));
                table.addCell(book.getBookCode());
                table.addCell(book.getTitle());
                table.addCell(book.getAuthor());
                table.addCell(book.getCategory());
                table.addCell(book.getPublisher());
                table.addCell(String.valueOf(book.getPublishYear()));
                table.addCell(String.valueOf(book.getPrice()));
                table.addCell(String.valueOf(book.getQty()));
                table.addCell(book.getStatus());
            }

            print(table.render(), true);

            System.out.println("\nPage " + (currentPage + 1) + " of " + totalPages);
            System.out.println(
                    (startIndex + 1) + " - " + endIndex + " of " + bookList.size() + " books"
            );

            String choice = InputUtil
                    .getText("[N]ext | [P]revious | [B]ack")
                    .trim()
                    .toLowerCase();

            switch (choice) {
                case "n" -> {
                    if (currentPage < totalPages - 1) currentPage++;
                    else System.out.println("Already at last page.");
                }
                case "p" -> {
                    if (currentPage > 0) currentPage--;
                    else System.out.println("Already at first page.");
                }
                case "b" -> {
                    return;
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    public static void printMemberList(List<Member> memberList) {

        if (memberList == null || memberList.isEmpty()) {
            print("No books found.", true);
            return;
        }
        int pageSize = 10;
        int currentPage = 0;
        int totalPages = (int) Math.ceil((double) memberList.size() / pageSize);

        while (true) {

            int startIndex = currentPage * pageSize;
            int endIndex = Math.min(startIndex + pageSize, memberList.size());
            Table table = new Table(9, BorderStyle.UNICODE_ROUND_BOX_WIDE);
            table.addCell(ColorUtil.BLUE+"ID"+ColorUtil.RESET);
            table.addCell(ColorUtil.BLUE+"CODE"+ColorUtil.RESET);
            table.addCell(ColorUtil.BLUE+"NAME"+ColorUtil.RESET);
            table.addCell(ColorUtil.BLUE+"GENDER"+ColorUtil.RESET);
            table.addCell(ColorUtil.BLUE+"PHONE"+ColorUtil.RESET);
            table.addCell(ColorUtil.BLUE+"EMAIL"+ColorUtil.RESET);
            table.addCell(ColorUtil.BLUE+"ADDRESS"+ColorUtil.RESET);
            table.addCell(ColorUtil.BLUE+"JOIN_DATE"+ColorUtil.RESET);
            table.addCell(ColorUtil.BLUE+"STATUS"+ColorUtil.RESET);


            for (int i = startIndex; i < endIndex; i++) {
                Member members = memberList.get(i);
                table.addCell(members.getMemberId().toString());
                table.addCell(members.getMemberCode());
                table.addCell(members.getFullName());
                table.addCell(members.getGender());
                table.addCell(members.getPhone());
                table.addCell(members.getEmail());
                table.addCell(members.getAddress());
                table.addCell(members.getJoinDate().toString());
                table.addCell(members.getStatus());
            }

            print(table.render(), true);

            System.out.println("\nPage " + (currentPage + 1) + " of " + totalPages);
            System.out.println(
                    (startIndex + 1) + " - " + endIndex + " of " + memberList.size() + " books"
            );

            String choice = InputUtil
                    .getText("[N]ext | [P]revious | [B]ack")
                    .trim()
                    .toLowerCase();

            switch (choice) {
                case "n" -> {
                    if (currentPage < totalPages - 1) currentPage++;
                    else System.out.println("Already at last page.");
                }
                case "p" -> {
                    if (currentPage > 0) currentPage--;
                    else System.out.println("Already at first page.");
                }
                case "b" -> {
                    return;
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }
}

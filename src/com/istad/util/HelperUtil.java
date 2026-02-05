package com.istad.util;

import com.istad.model.Book;
import com.istad.model.Member;
import com.istad.service.BookService;
import com.istad.service.BookServiceImpl;
import com.istad.service.MemberService;
import com.istad.service.MemberServiceImpl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class HelperUtil {
    static BookService bookService = new BookServiceImpl();
    static MemberService memberService = new MemberServiceImpl();

    public static void listAllBook() {
        try {
            List<Book> bookList = bookService.findAll();
            ViewUtil.printBookList(bookList);
        } catch (RuntimeException e) {
            ViewUtil.printHeader(e.getMessage());
        }
    }

    public static void updateBook() {
        while (true) {
            ViewUtil.printHeader(ColorUtil.PURPLE + "Update a Book by code" + ColorUtil.RESET);
            String bookCode = InputUtil.getText(ColorUtil.RED + "(X | x) for cancel update"
                    + ColorUtil.GREEN + "\nEnter Code to update" + ColorUtil.RESET);
            if (bookCode.equalsIgnoreCase("x")) {
                ViewUtil.printHeader(ColorUtil.YELLOW + "Update cancelled.");
                return;
            }
            String title = InputUtil.getTextNullable(ColorUtil.GREEN+"Enter Title: ");
            String author = InputUtil.getTextNullable("Enter Author: ");
            String category = InputUtil.getTextNullable("Enter Category : ");
            String publisher = InputUtil.getTextNullable("Enter Publisher : ");
            Integer publishYear = InputUtil.getIntegerNullable("Enter Publish Year : ");
            BigDecimal price = InputUtil.getMoneyNullable("Enter Price: ");
            Integer qty = InputUtil.getIntegerNullable("Enter QTY: "+ColorUtil.RESET);


            Book book = new Book();
            book.setTitle(title);
            book.setAuthor(author);
            book.setCategory(category);
            book.setPublisher(publisher);
            book.setPublishYear(publishYear);
            book.setPrice(price);
            book.setQty(qty);

            try {
                bookService.updateByCode(bookCode, book);
                ViewUtil.printHeader("Book update successfully...!");
            } catch (RuntimeException e) {
                ViewUtil.printHeader(e.getMessage());
            }
        }
    }

    public static void deleteBook() {
        ViewUtil.printHeader("Delete a Book By Code");
        String bookCode = InputUtil.getText("Enter Code: ");

        String confirmation = InputUtil.getText("Are you sure to delete? [ Y / N ]");
        if (confirmation.equalsIgnoreCase("y") || confirmation.equalsIgnoreCase("yes"))
            try {
                bookService.deleteByCode(bookCode);
                ViewUtil.printHeader("Delete Successfully...!");
            } catch (RuntimeException e) {
                ViewUtil.printHeader(e.getMessage());
            }
        else
            ViewUtil.printHeader("Delete operation cancelled..!");
    }

    public static void addBook() {
        while (true) {
            ViewUtil.printHeader(ColorUtil.PURPLE + "Add Member to Database");
            String bookCode = InputUtil.getText(ColorUtil.RED + "(X | x) to cancel Add"
                    + ColorUtil.GREEN + "\nEnter Code to Add " + ColorUtil.RESET);
            if (bookCode.equalsIgnoreCase("x")) {
                ViewUtil.printHeader(ColorUtil.YELLOW + "Add book was cancelled.");
                return;
            }
            String title = InputUtil.getText("Enter Name: ");
            String author = InputUtil.getText("Enter Author: ");
            String category = InputUtil.getText("Enter Category : ");
            String publisher = InputUtil.getText("Enter Publisher : ");
            Integer publishYear = InputUtil.getInteger("Enter Publish Year : ");
            BigDecimal price = InputUtil.getMoney("Enter Price: ");
            Integer qty = InputUtil.getInteger("Enter QTY: ");
            String status = InputUtil.getText("Enter status" + ColorUtil.RESET);

            Book book = new Book(bookCode, title, author, category, publisher, publishYear, price, qty, status);
            try {
                bookService.save(book);
                ViewUtil.printHeader("Book saved successfully..!");
            } catch (RuntimeException e) {
                ViewUtil.printHeader(e.getMessage());
            }
        }
    }

    public static void listAllMember() {
        try {
            List<Member> memberList = memberService.findAll();
            ViewUtil.printMemberList(memberList);
        } catch (RuntimeException e) {
            ViewUtil.printHeader(e.getMessage());
        }
    }

    public static void addMember() {
        while (true) {
            ViewUtil.printHeader(ColorUtil.PURPLE + "Add Member to Database");
            String memberCode = InputUtil.getText(ColorUtil.RED + "(X | x) to cancel Add"
                    + ColorUtil.GREEN + "\nEnter Code to Add " + ColorUtil.RESET);
            if (memberCode.equalsIgnoreCase("x")) {
                ViewUtil.printHeader(ColorUtil.YELLOW + "Add Member was cancelled.");
                return;
            }
            String fullName = InputUtil.getText("Enter Name : ");
            String gender = InputUtil.getText("Enter Gender : ");
            String phone = InputUtil.getText("Enter Phone : ");
            String email = InputUtil.getGmail("Enter Email : ");
            String address = InputUtil.getText("Enter Address : ");
            LocalDate joinDate = InputUtil.getLocalDate("Enter Join Date : ");
            String status = InputUtil.getText("Enter status" + ColorUtil.RESET);

            Member member = new Member(memberCode, fullName, gender, phone
                    , email, address, joinDate, status);
            try {
                memberService.save(member);
                ViewUtil.printHeader("Member saved successfully..!");
            } catch (RuntimeException e) {
                ViewUtil.printHeader(e.getMessage());
            }
        }
    }

    public static void updateMember() {
        while (true) {
            ViewUtil.printHeader("Update Member to Database");
            String memberCode = InputUtil.getText(ColorUtil.RED + "(X | x) to cancel Update"
                    + ColorUtil.GREEN + "\nEnter Code to Update " + ColorUtil.RESET);
            if (memberCode.equalsIgnoreCase("x")) {
                ViewUtil.printHeader(ColorUtil.YELLOW + "Update Member was cancelled.");
                return;
            }
            String fullName = InputUtil.getTextNullable("Enter Name : ");
            String gender = InputUtil.getTextNullable("Enter Gender : ");
            String phone = InputUtil.getTextNullable("Enter Phone : ");
            String email = InputUtil.getGmailNullable("Enter Email : ");
            String address = InputUtil.getTextNullable("Enter Address : ");
            LocalDate joinDate = InputUtil.getLocalDateNullable("Enter Join Date : ");
            String status = InputUtil.getTextNullable("Enter status");

            Member member = new Member();
            member.setFullName(fullName);
            member.setGender(gender);
            member.setPhone(phone);
            member.setEmail(email);
            member.setAddress(address);
            member.setJoinDate(joinDate);
            member.setStatus(status);

            try {
                memberService.updateByCode(memberCode, member);
                ViewUtil.printHeader("Member update successfully...!");
            } catch (RuntimeException e) {
                ViewUtil.printHeader(e.getMessage());
            }
        }
    }

    public static void deleteMember() {
        ViewUtil.printHeader("Delete a Member By Code");
        String memberCode = InputUtil.getText("Enter Code: ");

        String confirmation = InputUtil.getText("Are you sure to delete? [ Y / N ]");
        if (confirmation.equalsIgnoreCase("y") || confirmation.equalsIgnoreCase("yes"))
            try {
                memberService.deleteByCode(memberCode);
                ViewUtil.printHeader("Delete Successfully...!");
            } catch (RuntimeException e) {
                ViewUtil.printHeader(e.getMessage());
            }
        else
            ViewUtil.printHeader("Delete operation cancelled..!");
    }

    public static void searchBooks() {
        while (true) {
            ViewUtil.printHeader(ColorUtil.PURPLE + "Search Book");
            String key = InputUtil.getText(ColorUtil.RED + "(X | x) to cancel search"
                    + ColorUtil.GREEN + "\nEnter Key Book to search" + ColorUtil.RESET);
            if (key.equalsIgnoreCase("x")) {
                ViewUtil.printHeader(ColorUtil.YELLOW + "Search book was cancelled.");
                return;
            }
            try {
                List<Book> bookList = bookService.searchBook(key);
                ViewUtil.printBookList(bookList);
            } catch (RuntimeException e) {
                ViewUtil.printHeader(e.getMessage());
            }
        }
    }

    public static void searchMember() {
        while (true) {
            ViewUtil.printHeader("search Member");
            String key = InputUtil.getText(ColorUtil.RED + "(X | x) to cancel Search"
                    + ColorUtil.GREEN + "\nEnter Key Member to Search" + ColorUtil.RESET);
            if (key.equalsIgnoreCase("x")) {
                ViewUtil.printHeader(ColorUtil.YELLOW + "Search Member was cancelled.");
                return;
            }
            try {
                List<Member> memberList = memberService.searchMember(key);
                ViewUtil.printMemberList(memberList);
            } catch (RuntimeException e) {
                ViewUtil.printHeader(e.getMessage());
            }
        }
    }

    public static void checkAvailable() {
        ViewUtil.printHeader("Check Available Book");
        try {
            String bookCode = InputUtil.getText(ColorUtil.RED + "(X | x) to cancel Check"
                    + ColorUtil.GREEN + "\nEnter Code to Check " + ColorUtil.RESET);
            if (bookCode.equalsIgnoreCase("x")) {
                ViewUtil.printHeader(ColorUtil.YELLOW + "Check Available book was cancelled.");
                return;
            }
            boolean available = bookService.checkAvailable(bookCode);
            if (available) {
                ViewUtil.printHeader("Book is AVAILABLE");
            }
            if (!available) {
                ViewUtil.printHeader("Reason: Out of stock or book is inactive.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setStatus() {
        while(true) {
            ViewUtil.statusMenu();
            int option = InputUtil.getInteger("Enter (0) to Exit " +
                    "\nEnter Option" );
            if (option == 0) return;
            switch (option) {
                case 1 -> {
                    try {
                        String memberCode = InputUtil.getText("Enter Code");
                        boolean success = memberService.activateMember(memberCode);
                        if (success) {
                            ViewUtil.printHeader("set Activate Successfully...");
                        }
                        if (!success) {
                            ViewUtil.printHeader("set Activate operation failed...!");
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                case 2 -> {
                    try {
                        String memberCode = InputUtil.getText("Enter Code");
                        boolean success = memberService.deactivateMember(memberCode);
                        if (success) {
                            ViewUtil.printHeader("set Deactivate Successfully...");
                        }
                        if (!success) {
                            ViewUtil.printHeader("set Deactivate operation failed...!");
                        }
                    } catch (RuntimeException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 3 -> listAllMember();
                default -> {
                    throw new RuntimeException("Invalid option");
                }
            }
        }

    }

}

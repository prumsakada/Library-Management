package com.istad;

import com.istad.config.Database;
import com.istad.util.HelperUtil;
import com.istad.util.InputUtil;
import com.istad.util.ViewUtil;

public class LibraryApp {

    public LibraryApp() {
        Database.init();
    }

    public static void main(String[] args) {
        LibraryApp system = new LibraryApp();
        system.start();
    }

    private void start() {
        do {
            ViewUtil.showMenu();
            int menu = InputUtil.getInteger("Enter Your Option");
            switch (menu) {
                case 0 -> System.exit(0);
                case 1 -> showBookMenu();
                case 2 -> showMemberMenu();
                default -> ViewUtil.printHeader("Invalid Option Please Try Again");
            }
        } while (true);
    }

    private void showBookMenu() {
        do {
            ViewUtil.bookMenu();
            int bookMenu = InputUtil.getInteger("Enter Your Option");
            switch (bookMenu) {
                case 0 -> returnBack();
                case 1 -> HelperUtil.listAllBook();
                case 2 -> HelperUtil.searchBooks();
                case 3 -> HelperUtil.addBook();
                case 4 -> HelperUtil.updateBook();
                case 5 -> HelperUtil.deleteBook();
                default -> ViewUtil.printHeader("Invalid Option Please Try Again");
            }

        } while (true);
    }

    private void showMemberMenu() {
        do {
            ViewUtil.memberMenu();
            int menu = InputUtil.getInteger("Enter Your Option");
            switch (menu) {
                case 0 -> returnBack();
                case 1 -> HelperUtil.listAllMember();
                case 2 -> {
                }
                case 3 -> HelperUtil.addMember();
                case 4 -> HelperUtil.updateMember();
                case 5 -> HelperUtil.deleteMember();
                default -> ViewUtil.printHeader("Invalid Option Please Try Again");
            }
        } while (true);
    }

    private void returnBack() {
        ViewUtil.printHeader("You has has return back");
        LibraryApp system = new LibraryApp();
        system.start();
    }
}

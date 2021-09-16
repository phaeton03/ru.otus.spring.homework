package ru.otus.spring2021.service;

import ru.otus.spring2021.service.exception.IncorrectNameOrSurname;

import java.util.Scanner;
import java.util.regex.Pattern;

public class UserService {
    public void signIn() {
        String regEx = "[a-zA-Z]+";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write your name : ");
        String name = scanner.nextLine();
        System.out.println("Write your surname : ");
        String surname = scanner.nextLine();
        if (!name.matches(regEx) || !surname.matches(regEx)) {
            throw new IncorrectNameOrSurname();
        }
        System.out.printf("Your name is %s and your surname is %s %n%n", name, surname);
    }
}

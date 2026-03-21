package com.homework2.userservice.ui;

import com.homework2.userservice.dto.UserDto;
import com.homework2.userservice.service.UserService;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {

    private final UserService userService;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleMenu(UserService userService) {
        this.userService = userService;
    }

    public void start() {

        while (true) {

            System.out.println("\n1 - Создать пользователя");
            System.out.println("2 - Показать пользователей");
            System.out.println("3 - Удалить пользователя");
            System.out.println("0 - Выход");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1 -> createUser();
                case 2 -> showUsers();
                case 3 -> deleteUser();
                case 0 -> System.exit(0);
            }
        }
    }

    private void createUser() {

        scanner.nextLine();

        System.out.println("Имя:");
        String name = scanner.nextLine();

        System.out.println("Email:");
        String email = scanner.nextLine();

        System.out.println("Возраст:");
        int age = scanner.nextInt();

        UserDto dto = new UserDto();

        dto.setName(name);
        dto.setEmail(email);
        dto.setAge(age);

        userService.create(dto);
    }

    private void showUsers() {

        List<UserDto> users = userService.getAll();

        for (UserDto u : users) {

            System.out.println(
                    u.getId() + " "
                            + u.getName() + " "
                            + u.getEmail() + " "
                            + u.getAge()
            );
        }
    }

    private void deleteUser() {

        System.out.println("Введите id:");

        Long id = scanner.nextLong();

        userService.delete(id);
    }
}

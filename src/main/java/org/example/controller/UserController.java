package org.example.controller;

import org.example.enums.OperationTypeEnum;
import org.example.model.User;
import org.example.service.UserService;
import org.example.service.UserServiceImpl;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserController {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        String decisions = "no";

        do {
            String operation = JOptionPane.showInputDialog("Enter operation: save | update | delete | list | get");
            switch (operation){
                case "save":
                    User savedUser = getUser(OperationTypeEnum.SAVE.name());
                    int saved = userService.saveUser(savedUser);
                    if(saved>=1){
                        JOptionPane.showMessageDialog(null, "User info is saved in db successfully");
                    }else{
                        JOptionPane.showMessageDialog(null, "Oops! Something went wrong");
                    }

                    break;
                case "update":
                    User updatedUser = getUser(OperationTypeEnum.UPDATE.name());
                    int updated = userService.updateUser(updatedUser);
                    if(updated>=1){
                        JOptionPane.showMessageDialog(null, "User info is updated in db successfully");

                    }else{
                        JOptionPane.showMessageDialog(null, "Oops! Something went wrong");
                    }
                    break;
                case "delete":
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID: "));
                    int deleted = userService.deleteUser(id);
                    if(deleted>=1){
                        JOptionPane.showMessageDialog(null, "User info is deleted in db successfully");

                    }else{
                        JOptionPane.showMessageDialog(null, "Oops! Something went wrong");
                    }
                    break;
                case "list":
                    userService.getAllUser().forEach(user -> {
                        System.out.println("User Id is : " +user.getId());
                        System.out.println("User name is : " +user.getUsername());
                        System.out.println("User password is : " +user.getPassword());
                        System.out.println("User mobile no is : " +user.getMobile_no());
                        System.out.println("User salary is : " +user.getSalary());
                        System.out.println("is user enable? : " +user.isEnable());
                        System.out.println("User Date of birth is :" +user.getDob());
                        System.out.println("=========================");
                    });
                    break;
                case "get":
                    id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID: "));
                    User user = userService.getUserById(id);
                    System.out.println("User Id is : " +user.getId());System.out.println("User name is : " +user.getUsername());
                    System.out.println("User password is : " +user.getPassword());
                    System.out.println("User mobile no is : " +user.getMobile_no());
                    System.out.println("User salary is : " +user.getSalary());
                    System.out.println("is user enable? : " +user.isEnable());
                    System.out.println("User Date of birth is :" +user.getDob());

                    break;
            }
             decisions = JOptionPane.showInputDialog("Do you want to continue?");
        }while(decisions.equalsIgnoreCase("yes"));

    }
    public static User getUser(String type){
        User user = new User();
        if(type.equalsIgnoreCase("update")){
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID: "));
            user.setId(id);
        }
        String username = JOptionPane.showInputDialog("Enter Username :");
        String password = JOptionPane.showInputDialog("Enter Password :");
        double salary = Double.parseDouble(JOptionPane.showInputDialog("Enter Salary"));
        long mobileNo = Long.parseLong(JOptionPane.showInputDialog("ENter mobile no"));
        boolean enable = Boolean.parseBoolean(JOptionPane.showInputDialog("is user enable?"));
        String dob = JOptionPane.showInputDialog("ENter dob : format => yyyy-MM-dd");
        LocalDate ld = LocalDate.parse(dob, DateTimeFormatter.BASIC_ISO_DATE.ofPattern("yyyy-MM-dd"));

        user.setUsername(username);
        user.setPassword(password);
        user.setMobile_no(mobileNo);
        user.setSalary(salary);
        user.setDob(ld);
        user.setEnable(enable);

        return user;


    }
}

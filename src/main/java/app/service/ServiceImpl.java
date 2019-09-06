package app.service;

import app.dao.DAO;
import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;

@Data
public class ServiceImpl implements Service {

    private BufferedReader inputReader;
    private DAO dao;
    private boolean mark = true;
    private String message = null;

    @Override
    public void startService() {
        try {
            while (mark) {
                System.out.println(message);
                System.out.print("Ввод: ");
                String result = inputReader.readLine();
                switch (result){
                    case "1": printList();
                        break;
                    case "2": getNestedList();
                        break;
                    case "3": delete();
                        break;
                    case "4": mark = false;
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        mark = true;
    }

    @Override
    public void printList() {
        dao.getAllList();
    }

    @Override
    public void getNestedList() {
        dao.getNestedList(getId());
    }


    @Override
    public void delete() {
        dao.delete(getId());
    }

    @Override
    public long getId() {
        try {
            System.out.print("Введите id : ");
            String temp = inputReader.readLine();
            try {
                return Long.parseLong(temp);
            } catch (NumberFormatException e){
                getId();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
}

package app.service;

import app.context.MainApp;
import app.dao.UserDAO;

import java.io.BufferedReader;
import java.io.IOException;

public class UserService implements Service {
    private BufferedReader inputReader;
    private UserDAO userDAO;
    private boolean userWork = true;

    private void init(){
        this.inputReader = MainApp.mainApp.getInputReader();
        this.userDAO = MainApp.mainApp.getUserDAO();
    }

    @Override
    public void startService() {
        init();
        try {
            while (userWork) {
                System.out.println("Введите: \n" +
                        "1 для отображения списка покупателей. \n" +
                        "2 для отображения списка продуктов купленных покупателем. \n" +
                        "3 для удаления продукта. \n" +
                        "4 для возврата.");
                System.out.print("Ввод: ");
                String result = inputReader.readLine();
                switch (result){
                    case "1": printList();
                        break;
                    case "2": getNestedList();
                        break;
                    case "3": delete();
                        break;
                    case "4": userWork = false;
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        userWork = true;
    }

    @Override
    public void printList(){
        userDAO.getAllList();
    }

    @Override
    public void getNestedList(){
        userDAO.getNestedList(getId());
    }

    @Override
    public void delete() {
        userDAO.delete(getId());
    }


    public long getId(){
        try {
            System.out.print("Введите id продукта: ");
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

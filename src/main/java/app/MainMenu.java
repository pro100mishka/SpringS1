package app;

import app.context.MainApp;
import app.service.ProductService;
import app.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;

public class MainMenu {

    private boolean startMark = true;
    private BufferedReader inputReader;
    private ProductService productService;
    private UserService userService;

    public MainMenu() {
        this.inputReader = MainApp.mainApp.getInputReader();
        this.productService = MainApp.mainApp.getProductService();
        this.userService = MainApp.mainApp.getUserService();
    }

    public void start() {
        try {
            while (startMark){
                System.out.println("Введите: \n" +
                        "1 для работы с покупателями. \n" +
                        "2 для работы с продуктами. \n" +
                        "3 для выхода.");
                System.out.print("Ввод: ");
                String result = inputReader.readLine();
                switch (result){
                    case "1": userService.startService();
                        break;
                    case "2": productService.startService();
                        break;
                    case "3": startMark = false;
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            MainApp.mainApp.shutdown();
        }
    }
}

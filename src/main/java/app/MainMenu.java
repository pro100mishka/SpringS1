package app;

import app.context.MainApp;
import app.service.OperationService;
import app.service.ProductService;
import app.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;

public class MainMenu {

    private boolean startMark = true;
    private BufferedReader inputReader;
    private ProductService productService;
    private UserService userService;
    private OperationService operationService;

    public MainMenu() {
        this.inputReader = MainApp.mainApp.getInputReader();
        this.productService = MainApp.mainApp.getProductService();
        this.userService = MainApp.mainApp.getUserService();
        this.operationService = MainApp.mainApp.getOperationService();
    }

    public void start() {
        try {
            while (startMark){
                System.out.println("Введите: \n" +
                        "1 для работы с покупателями. \n" +
                        "2 для работы с продуктами. \n" +
                        "3 для отображения сделок. \n" +
                        "4 для выхода.");
                System.out.print("Ввод: ");
                String result = inputReader.readLine();
                switch (result){
                    case "1": userService.startService();
                        break;
                    case "2": productService.startService();
                        break;
                    case "3": operationService.getOperationsDetails();
                        break;
                    case "4": startMark = false;
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

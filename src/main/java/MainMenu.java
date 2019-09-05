
import context.MainApp;
import service.ProductService;

import java.io.BufferedReader;
import java.io.IOException;

public class MainMenu {

    private boolean startMark = true;
    private boolean userWork = true;
    private BufferedReader inputReader;
    private ProductService productService;

    public MainMenu() {
        this.inputReader = MainApp.mainApp.getInputReader();
        this.productService = MainApp.mainApp.getProductService();
        System.out.println(inputReader.hashCode());
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
                    case "1": userWork();
                        break;
                    case "2": productService.startService();
                        break;
                    case "3": startMark = false;
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void userWork(){
        System.out.println("userWork");
    }

}

package service;

import context.MainApp;
import dao.DAO;
import entity.Product;
import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@Data
public class ProductService {

    private BufferedReader inputReader;
    private DAO DAO;
    private boolean productWork = true;

    private void init(){
        this.inputReader = MainApp.mainApp.getInputReader();
        this.DAO = MainApp.mainApp.getDAO();
        System.out.println(inputReader.hashCode());
    }

    public void startService(){
        init();
        try {
            while (productWork) {
                System.out.println("Введите: \n" +
                        "1 для отображения списка продуктов. \n" +
                        "2 для отображения списка клиентов купивший продукт. \n" +
                        "3 для возврата.");
                System.out.print("Ввод: ");
                String result = inputReader.readLine();
                switch (result){
                    case "1": printProductList();
                        break;
                    case "2": ;
                        break;
                    case "3": productWork = false;
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printProductList(){
        List<Product> productList = DAO.getList(Product.class);
        for (Product p: productList ) {
            System.out.println(p);
        }
    }

}

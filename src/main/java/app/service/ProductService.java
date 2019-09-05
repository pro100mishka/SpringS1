package app.service;

import app.context.MainApp;
import app.dao.ProductDAO;
import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;

@Data
public class ProductService implements Service{

    private BufferedReader inputReader;
    private ProductDAO productDAO;
    private boolean productWork = true;

    private void init(){
        this.inputReader = MainApp.mainApp.getInputReader();
        this.productDAO = MainApp.mainApp.getProductDAO();
    }

    @Override
    public void startService() {
        init();
        try {
            while (productWork) {
                System.out.println("Введите: \n" +
                        "1 для отображения списка продуктов. \n" +
                        "2 для отображения списка покупателей купивший продукт. \n" +
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
                    case "4": productWork = false;
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        productWork = true;
    }

    @Override
    public void printList(){
        productDAO.getAllList();
    }

    @Override
    public void getNestedList(){
        productDAO.getNestedList(getId());
    }

    @Override
    public void delete() {
        productDAO.delete(getId());
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

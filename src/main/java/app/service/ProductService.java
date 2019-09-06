package app.service;

import app.context.MainApp;
import app.dao.ProductDAO;
import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;

public class ProductService extends ServiceImpl{

    private void init(){
        super.setMessage("Введите: \n" +
                "1 для отображения списка продуктов. \n" +
                "2 для отображения списка покупателей купивший продукт. \n" +
                "3 для удаления продукта. \n" +
                "4 для возврата.");
        super.setInputReader(MainApp.mainApp.getInputReader());
        super.setDao(MainApp.mainApp.getProductDAO());
    }

    @Override
    public void startService() {
        init();
        super.startService();
    }
}

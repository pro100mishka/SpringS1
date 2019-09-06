package app.service;

import app.context.MainApp;
import app.dao.UserDAO;

import java.io.BufferedReader;
import java.io.IOException;

public class UserService extends ServiceImpl {

    private void init(){
        super.setMessage("Введите: \n" +
                "1 для отображения списка покупателей. \n" +
                "2 для отображения списка продуктов купленных покупателем. \n" +
                "3 для удаления покупателя. \n" +
                "4 для возврата.");
        super.setInputReader(MainApp.mainApp.getInputReader());
        super.setDao(MainApp.mainApp.getUserDAO());
    }

    @Override
    public void startService() {
        init();
        super.startService();
    }
}

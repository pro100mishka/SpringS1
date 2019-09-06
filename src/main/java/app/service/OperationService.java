package app.service;

import app.context.MainApp;
import app.dao.OperationsDao;

public class OperationService {
    private OperationsDao operationsDao;

    public void init(){
        this.operationsDao= MainApp.mainApp.getOperationsDao();
    }

    public void getOperationsDetails(){
        init();
        operationsDao.getOperationsDetailsList();
    }
}

package service;

import models.AccountingRecords;
import org.apache.log4j.Logger;
import repository.AccountingRecordsDao;

import java.util.List;

public class AccountingRecordsService {
    private AccountingRecordsDao accountingRecordsDao;
    private static final Logger log = Logger.getLogger(AccountingRecordsService.class);

    public AccountingRecordsService() {
        accountingRecordsDao = new AccountingRecordsDao();
    }

    public AccountingRecords findAccountingRecordsId(int id) {
        return accountingRecordsDao.findById(id);
    }

    public void saveAccountingRecords(AccountingRecords accountingRecords) {
        accountingRecordsDao.save(accountingRecords);
        log.info("Учетная запись добавлена");
    }

    public void deleteAccountingRecords(int id) {
        accountingRecordsDao.deleteById(id);
        log.info("Учетная запись удалена");
    }


    public void updateAccountingRecords(int id, int idclient, int idbook, int days){
        accountingRecordsDao.update(id, idclient, idbook, days);
        log.info("Учетная запись обновлена");
    }

    public List<AccountingRecords> findAllAccountingRecords() {
        return accountingRecordsDao.findAll();
    }
}

package service;

import exceptions.AccountingRecordNotFoundException;
import exceptions.BookNotFoundException;
import models.AccountingRecords;
import models.Books;
import models.Clients;
import models.Status;
import org.apache.log4j.Logger;
import repository.AccountingRecordsDao;
import repository.BooksDao;

import java.time.LocalDate;
import java.util.List;

public class AccountingRecordsService {
    private AccountingRecordsDao accountingRecordsDao;
    private static final Logger log = Logger.getLogger(AccountingRecordsService.class);

    public AccountingRecordsService() {
        accountingRecordsDao = new AccountingRecordsDao();
    }

    public void saveAccountingRecords(AccountingRecords accountingRecords) {
        accountingRecordsDao.save(accountingRecords);
        log.info("AccountingRecord save");
    }

    public void deleteAccountingRecords(int id) throws AccountingRecordNotFoundException {
        AccountingRecords accountingRecords = accountingRecordsDao.findAccountingRecordsById(id);
        if (accountingRecords != null) {
            accountingRecordsDao.deleteById(id);
            log.info("AccountingRecord delete");
        } else {
            throw new AccountingRecordNotFoundException("The AccountingRecord with id " + id + " was not found");
        }

    }

    public void updateClientAccountingRecordsById(int id, int idclient) throws AccountingRecordNotFoundException {
        AccountingRecords accountingRecords = accountingRecordsDao.findAccountingRecordsById(id);
        Clients clients = new ClientService().findClientById(id);
        if (accountingRecords != null) {
            accountingRecords.setClient(clients);
            accountingRecordsDao.update(accountingRecords);
            log.info("AccountingRecord update");
        } else {
            throw new AccountingRecordNotFoundException("The AccountingRecord with id " + id + " was not found");
        }
    }

    public void updateBookAccountingRecordsById(int id, int idbook) throws AccountingRecordNotFoundException {
        AccountingRecords accountingRecords = accountingRecordsDao.findAccountingRecordsById(id);

        if (accountingRecords != null) {
            Books newbook = new BooksService().findBooksById(idbook);
            Books currentbook = accountingRecords.getBook();
            if (newbook != currentbook) {
                currentbook.setStatus(Status.NOT_ISSUED);
                new BooksDao().update(currentbook);
                newbook.setStatus(Status.ISSUED);
                accountingRecords.setBook(newbook);
                accountingRecordsDao.update(accountingRecords);
                log.info("AccountingRecord update");
            }
        } else {
            throw new AccountingRecordNotFoundException("The AccountingRecord with id " + id + " was not found");
        }
    }

    public void updateDaysAccountingRecordsById(int id, int days) throws AccountingRecordNotFoundException {
        AccountingRecords accountingRecords = accountingRecordsDao.findAccountingRecordsById(id);

        if (accountingRecords != null) {
            accountingRecords = addDays(accountingRecords, days);
            accountingRecordsDao.update(accountingRecords);
            log.info("AccountingRecord update");
        } else {
            throw new AccountingRecordNotFoundException("The AccountingRecord with id " + id + " was not found");
        }
    }

    public AccountingRecords addDays(AccountingRecords accountingRecords, int days) {
        LocalDate newDate = accountingRecords.getReceiptDate().plusDays(days);
        accountingRecords.setReturnDate(newDate);
        return accountingRecords;
    }

    public List<AccountingRecords> findAllAccountingRecords() {
        return accountingRecordsDao.findAll();
    }
}

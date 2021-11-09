package service;

import junit.framework.TestCase;
import models.AccountingRecords;
import org.junit.Assert;

import java.time.LocalDate;

public class AccountingRecordsServiceTest extends TestCase {

    public void testAddDays() {

        LocalDate localDate = LocalDate.now();

        AccountingRecords actualAccountingRecords = AccountingRecords.builder().receiptDate(localDate).build();
        AccountingRecordsService accountingRecordsService = new AccountingRecordsService();
        accountingRecordsService.addDays(actualAccountingRecords,20);

        AccountingRecords expectedAccountingRecords = AccountingRecords.builder().receiptDate(localDate).build();
        expectedAccountingRecords.setReturnDate(localDate.plusDays(20));

        Assert.assertEquals(expectedAccountingRecords, actualAccountingRecords);
    }
}
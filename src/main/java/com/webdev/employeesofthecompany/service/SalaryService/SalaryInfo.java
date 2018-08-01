package com.webdev.employeesofthecompany.service.SalaryService;

import com.webdev.employeesofthecompany.domain.SettlementSheet;
import com.webdev.employeesofthecompany.service.jpa.SettlementSheetService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.webdev.employeesofthecompany.service.ParseDateService.parseDateToStringYyyyMmDd;

@Service
public class SalaryInfo {

    @Autowired
    private SettlementSheetService settlementSheetService;


    public List<String> salaryInfoByDateAndEmployeeId(Date dateFrom, Date dateTo, long employeeId){
        ArrayList<String> stringArrayList = new ArrayList<>();

       // stringArrayList.add()

        return stringArrayList;
    }
}

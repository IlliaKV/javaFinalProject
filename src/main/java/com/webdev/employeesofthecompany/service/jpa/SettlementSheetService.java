package com.webdev.employeesofthecompany.service.jpa;

import com.webdev.employeesofthecompany.domain.SettlementSheet;
import com.webdev.employeesofthecompany.repository.SettlementSheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.webdev.employeesofthecompany.service.ParseDateService.parseDateToStringYyyyMmDd;

@Service
public class SettlementSheetService {

    @Autowired
    private SettlementSheetRepository settlementSheetRepository;

    public SettlementSheet save(SettlementSheet settlementSheet){
        settlementSheetRepository.save(settlementSheet);
        return settlementSheet;
    }

    public SettlementSheet getById(long id){
        return settlementSheetRepository.findById(id).get();
    }

    public List<SettlementSheet> getAll(){
        return settlementSheetRepository.findAll();
    }

    public void delete(SettlementSheet settlementSheet){
        settlementSheetRepository.delete(settlementSheet);
    }

    public boolean exists(long settlementSheetId) {
        return settlementSheetRepository.existsById(settlementSheetId);
    }

    public void update(SettlementSheet settlementSheet){
        settlementSheetRepository.save(settlementSheet);
    }

    public List<SettlementSheet> findSalaryDataByDateAndEmployeeId(String dateFrom, String dateTo, long employeeId){
        return settlementSheetRepository.findSalaryDataByDateAndEmployeeId(dateFrom, dateTo, employeeId); }
}

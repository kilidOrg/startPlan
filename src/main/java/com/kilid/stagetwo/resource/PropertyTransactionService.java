package com.kilid.stagetwo.resource;

import com.kilid.stagetwo.model.PropertyTransaction;
import com.kilid.stagetwo.model.PropertyTransactionDto;
import com.kilid.stagetwo.model.RecivedItem;
import com.kilid.stagetwo.repository.PropertyTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class PropertyTransactionService {
    @Autowired
    PropertyTransactionRepository propertyTransactionRepository;

    public List<PropertyTransactionDto> searchAction(RecivedItem recivedItem , Integer pageNumber) throws SQLException {
        return propertyTransactionRepository.searchAction(recivedItem , pageNumber);
    }
}

package com.kilid.stagetwo.repository.impl;

import com.kilid.stagetwo.model.PropertyTransactionDto;
import com.kilid.stagetwo.model.RecivedItem;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

public interface PropertyTransactionInterface  {
    List<PropertyTransactionDto> searchAction(RecivedItem recivedItem) throws SQLException;
}

package com.kilid.stagetwo.controller;

import com.kilid.stagetwo.model.PropertyTransactionDto;
import com.kilid.stagetwo.model.RecivedItem;
import com.kilid.stagetwo.resource.PropertyTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/propertyTransaction")
public class PropertyTransactionController {

    @Autowired
    PropertyTransactionService propertyTransactionService;

    @RequestMapping("/searchAction")
    public ResponseEntity<List<PropertyTransactionDto>> searchAction(@RequestBody RecivedItem recivedItem ,@PathParam("pageNumber") Integer pageNumber /* , PaginationItem<PropertyTransactionDto> paginationItem*/) throws SQLException {

        return  ResponseEntity.ok(propertyTransactionService.searchAction(recivedItem , pageNumber)) ;
    }

        @RequestMapping("/propertyTransactionDtoResponseEntity")
        public ResponseEntity<RecivedItem> propertyTransactionDtoResponseEntity(){
                    RecivedItem recivedItem = new RecivedItem();
                    recivedItem.setProperty_type_id(1);
                    recivedItem.setNo_bedrooms(1);
                    recivedItem.setNo_bathrooms(1);
                    //recivedItem.setFloor_area(new Float(1));
                    recivedItem.setAge(1);
                    recivedItem.setPageNumber(10);
        return ResponseEntity.ok(recivedItem);
        }
}

package com.kilid.stagetwo.repository;

import com.kilid.stagetwo.config.ConnectionUtil;
import com.kilid.stagetwo.model.PaginationItem;
import com.kilid.stagetwo.model.PropertyTransactionDto;
import com.kilid.stagetwo.model.RecivedItem;
import com.kilid.stagetwo.repository.impl.PropertyTransactionInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PropertyTransactionRepository implements PropertyTransactionInterface {
    @Autowired
    ConnectionUtil connectionUtil;
    @Override
    public List<PropertyTransactionDto> searchAction(RecivedItem recivedItem , Integer pageNumber) throws SQLException {
        PaginationItem<PropertyTransactionDto> propertyTransactionDtoPaginationItem = new PaginationItem<>();
        String query =" select ppt.id as id ," +
                    "   ppt.uprn as uprn , " +
                    " ppt.landuse_type_id as landuse_type_id," +
                    " ppt.property_type_id as property_type_id ," +
                    " ppt.no_bedrooms as no_bedrooms," +
                    " ppt.no_bathrooms as no_bathrooms, " +
                    " ppt.floor_area as floor_area, " +
                    " ppt.floor_area_metric_id as floor_area_metric_id," +
                    " ppt.age as age," +
                    " ( select  Count(pt.date) from public.property_transaction_10 pt where 1=1" +
                "           group by pt.date)as date ," +
                    " ppt.currency_metric_id as currency_metric_id," +
                    " (select AVG(p.price_metric1)  from public.property_transaction_10 p where 1=1" +
                "          group by p.price_metric1 ) as price_metric1 ," +
                    " ppt.price_metric2 as price_metric2 , " +
                "     ( select count(*) as totalNumber  from public.property_transaction_10 " +
                "       where 1=1 " +
                "       group by id ) as totalNumber , " +
                "      ( select ROW_NUMBER() OVER( order by id desc ) from public.property_transaction_10  ) as rowNumber " +
                    " from public.property_transaction_10 ppt  where 1 = 1 " ;
        if(recivedItem.getProperty_type_id() != null){

            query += " and ppt.property_type_id = ?  ";
        }
        if(recivedItem.getNo_bedrooms() != null ){
            query += " and ppt.no_bedrooms = ?";
        }
        if(recivedItem.getNo_bathrooms() != null){
            query += " and ppt.no_bathrooms =?  ";
        }
        if(recivedItem.getFloor_area() != null ){
            query += " and ppt.floor_area =?  ";
        }
        if(recivedItem.getAge() != null){
            query += " and ppt.age =?  ";
        }
        List<PropertyTransactionDto> propertyTransactionDtoList = new ArrayList<>();
        String completeQuery = "with propertyTransaction as ( " +
                query  +
                "         ) select id,\n" +
                "                 uprn,\n" +
                "                 landuse_type_id,\n" +
                "                 property_type_id,\n" +
                "                 no_bedrooms\n" +
                "                 no_bathrooms,\n" +
                "                 floor_area ,\n" +
                "                 floor_area_metric_id,\n" +
                "                 age ,\n" +
                "                 date,\n" +
                "                 currency_metric_id,\n" +
                "                 price_metric1 ,\n" +
                "                 price_metric2 ,\n" +
                "                 totalNumber , " +
                "                 rowNumber\n" +
                "                 from propertyTransaction where  rowNumber > 1 and rowNumber < 12 ";
        PreparedStatement preparedStatement =  ConnectionUtil.connection.prepareStatement(query);
        if(recivedItem.getProperty_type_id() != null)
            preparedStatement.setInt(1,recivedItem.getProperty_type_id());
        if (recivedItem.getNo_bedrooms() != null)
            preparedStatement.setInt(2,recivedItem.getNo_bedrooms());
        if(recivedItem.getNo_bathrooms() != null)
            preparedStatement.setInt(3,recivedItem.getNo_bathrooms());
        if(recivedItem.getFloor_area() != null)
            preparedStatement.setFloat(4,recivedItem.getFloor_area());
        if(recivedItem.getAge() != null)
            preparedStatement.setInt(5,recivedItem.getAge());
        ResultSet resultSet =  preparedStatement.executeQuery();
        while (resultSet.next()){
            PropertyTransactionDto propertyTransactionDto = new PropertyTransactionDto();
            propertyTransactionDto.setId(resultSet.getInt("id"));
            propertyTransactionDto.setUprn(resultSet.getString("uprn"));
            propertyTransactionDto.setLanduse_type_id(resultSet.getInt("landuse_type_id"));
            propertyTransactionDto.setProperty_type_id(resultSet.getInt("property_type_id"));
            propertyTransactionDto.setNo_bedrooms(resultSet.getInt("no_bedrooms"));
            propertyTransactionDto.setNo_bathrooms(resultSet.getInt("no_bathrooms"));
            propertyTransactionDto.setFloor_area(resultSet.getFloat("floor_area"));
            propertyTransactionDto.setFloor_area_metric_id(resultSet.getInt("floor_area_metric_id"));
            propertyTransactionDto.setAge(resultSet.getInt("age"));
            propertyTransactionDto.setDate(resultSet.getDate("date"));
            propertyTransactionDto.setCurrency_metric_id(resultSet.getInt("currency_metric_id"));
            propertyTransactionDto.setPrice_metric1(resultSet.getFloat("price_metric1"));
            propertyTransactionDto.setPrice_metric2(resultSet.getFloat("price_metric2"));
            propertyTransactionDtoPaginationItem.setPageCount(resultSet.getInt(("totalNumber")));
            propertyTransactionDtoPaginationItem.setTotalNumber(resultSet.getInt("totalNumber"));
            propertyTransactionDtoPaginationItem.setDataList(propertyTransactionDtoList);
            propertyTransactionDtoList.add(propertyTransactionDto);
        }
        propertyTransactionDtoPaginationItem.setDataList(propertyTransactionDtoPaginationItem.getDataList());
        preparedStatement.close();
        return propertyTransactionDtoList;
    }
}

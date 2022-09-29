package com.appInfoSystem.dao;

import com.appInfoSystem.pojo.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BillMapper {
    Bill selectBillById(@Param("id") Integer id);
    int insertBill(Bill bill);
    int updateBillById(@Param("billCode") String billCode, @Param("id") Integer id);
    int deleteBillById(@Param("id") Integer id);
    List<Bill> selectAllBill();
    Bill getBillByNameIdPay(Map map);
    Bill showBillAndProvider(Map param);
    List<Bill> dynamicGetBill(Bill bill);
    int selectCountAll();
    List<Bill>selectByPage(Map<String, Object> params);
}

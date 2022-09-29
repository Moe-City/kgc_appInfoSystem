package com.appInfoSystem.service.impl;

import com.appInfoSystem.dao.BillMapper;
import com.appInfoSystem.pojo.Bill;
import com.appInfoSystem.pojo.PageBean;
import com.appInfoSystem.service.BillService;
import com.appInfoSystem.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("billService")
public class BillServiceImpl implements BillService {
    @Resource
    private BillMapper billMapper;

    @Override
    public List<Bill> selectAllBill() {
        return billMapper.selectAllBill();
    }

    @Override
    public Bill getBillByNameIdPay(Map map) {
        return billMapper.getBillByNameIdPay(map);
    }

    @Override
    public Bill showBillAndProvider(Map param) {
        return billMapper.showBillAndProvider(param);
    }

    @Override
    public List<Bill> dynamicGetBill(Bill bill) {
        return billMapper.dynamicGetBill(bill);
    }

    @Override
    public int selectCountAll() {
        return billMapper.selectCountAll();
    }

    @Override
    public List<Bill> selectByPage(Map<String, Object> params) {
        return billMapper.selectByPage(params);
    }

    @Override
    public PageBean<Bill> billByPage(int pageNow, int pageSize){
        PageBean<Bill> pageBean = new PageBean<Bill>();
        pageBean.setCurrentPageNo(pageNow);
        pageBean.setPageSize(pageSize);
        Map<String, Object> map  = new HashMap<String, Object>();
        map.put("start",(pageNow-1)*pageSize);
        map.put("size",pageSize);
        SqlSession sqlSession = MybatisUtil.open();
        BillMapper billMapper = sqlSession.getMapper(BillMapper.class);
        int count = billMapper.selectCountAll();
        pageBean.setTotalCount(count);
        int pages = count/pageSize;
        if (count%pageSize!=0)
            pages+=1;
        pageBean.setTotalPageCount(pages);
        List<Bill> list = billMapper.selectByPage(map);
        pageBean.setList(list);
        MybatisUtil.close(sqlSession);
        return pageBean;
    }

    @Override
    public Bill selectBillById(Integer id) {
        return billMapper.selectBillById(id);
    }

    @Override
    public int updateBillById(String billCode, Integer id) {
        return billMapper.updateBillById(billCode, id);
    }

    @Override
    public int deleteBillById(Integer id) {
        return billMapper.deleteBillById(id);
    }

    @Override
    public int insertBill(Bill bill) {
        return billMapper.insertBill(bill);
    }
}

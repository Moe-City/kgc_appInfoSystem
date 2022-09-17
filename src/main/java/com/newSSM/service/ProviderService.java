package com.newSSM.service;

import com.newSSM.pojo.PageBean;
import com.newSSM.pojo.Provider;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProviderService {
    List<Provider> selectAllProvider();

    List<Provider> getProviderByName(String proName);

    Provider getProviderByid(int id);

    int modifyNameByParam(@Param("proName") String proName, @Param("id") int id);

    int modifyProvider(Provider provider);

    int add1Provider(Provider provider);

    int delete1ProviderByid(@Param("id") int id);

    Provider selectAllBillByProviderid(int id);

    List<Provider> dynamicSelect(Provider provider);

    int dynamicUpdate(Provider provider);

    List selectByIds(int[] ids);

    int dynamicUpdateTrim(Provider provider);

    List<Provider> selectAllBillByIds(int[] ids);

    List<Provider> selectAllBillByListIds(List list);

    List<Provider> selectALlBillByCodeAndIds(Map map);

    List<Provider> chooseSelect(Provider provider);

    int selectCountAll();

    //分页
    PageBean<Provider> providerByPage(int pageNow, int pageSize);

    int delete1ProviderByIds(int[] ids);
}

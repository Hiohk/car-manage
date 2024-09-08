package com.hkgroup.bus.service;

import com.hkgroup.bus.vo.CheckVo;
import com.hkgroup.sys.utils.DataGridView;

import java.util.Map;

public interface CheckService {
    /**
     * 根据出租单号加载检测单的表单数据
     *
     * @param rentid
     * @return
     */
    Map<String, Object> initCheckFormData(String rentid);

    /**
     * 保存检查单数据
     */
     void addCheck(CheckVo checkVo);

    /**
     * 查询所有检查单
     * @return
     */
    DataGridView queryAllCheck(CheckVo checkVo);

    /**
     * 更新检查单
     * @param checkVo
     */
    void updateCheck(CheckVo checkVo);

    /**
     * 删除检查单
     * @param checkVo
     */
    void deleteCheck(CheckVo checkVo);

    /**
     * 批量删除检查单
     * @param ids
     */
    void deleteBatchCheck(String[] ids);
}

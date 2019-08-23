package com.juma.tgm.crm.service;

import com.giants.common.exception.BusinessException;

/**
 * 注意：此类只用于同步数据的补救，不可再业务场景中使用
 */

@Deprecated
public interface RemedySyncService {

    /**
     * 补救CRM数据库大量添加的数据
     * 不可用于正常的业务场景
     *
     * @param crmCustomerId
     * @param tenantId
     */
    void doSync(Integer crmCustomerId, Integer tenantId) throws BusinessException;
}

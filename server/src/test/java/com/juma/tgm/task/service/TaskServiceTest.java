package com.juma.tgm.task.service;

import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.task.domain.TaskCalendar;
import com.juma.tgm.task.domain.TaskFixedDelivery;
import com.juma.tgm.task.vo.gateway.InviteRequest;
import com.juma.tgm.tools.service.MessagePushService;
import com.juma.vms.external.service.VmsService;
import com.juma.vms.transport.domain.CapacityPool;
import org.testng.annotations.Test;
import testng.BaseTestNGTest;

import javax.annotation.Resource;
import java.util.List;

public class TaskServiceTest extends BaseTestNGTest {

    @Resource
    private TaskFacadeService facadeService;

    @Resource
    private TaskCalendarService taskCalendarService;

    @Resource
    private TaskScheduledService taskScheduledService;

    @Resource
    private MessagePushService messagePushService;

    @Resource
    private VmsService vmsService;

    @Resource
    private ProjectStatusChangeService projectStatusChangeService;

    @Test
    public void conflictChangeCapacity() {

        //facadeService.conflictChangeCapacity(1,2,2,2,new LoginUser(19,1));
        //taskCalendarService.updateByWaybillId(1522808);
        //List<TaskFixedDelivery> deliveryByFiler = taskScheduledService.findDeliveryByFiler(1, null, 16, null);
        //System.out.println(deliveryByFiler);

        //facadeService.doChangeCapacity(466,202,405,2,new LoginUser(19,8020));
        InviteRequest inviteRequest = new InviteRequest();
        inviteRequest.setTaskId(467);
        inviteRequest.setBillPeriod(60);
        inviteRequest.setBillPeriodReason("AAAAAAAAAAA");
        inviteRequest.setVendorId(16633);
        inviteRequest.setDriverId(27269);
        inviteRequest.setTruckId(425);
        //facadeService.inviteVendor(inviteRequest,new LoginUser(19,11600));


        facadeService.updateToVendorReceive(397,16637,new LoginUser(19,28502));
        //facadeService.changeVendor(inviteRequest,new LoginUser(19,8020));

        //messagePushService.inviteVendor(inviteRequest.getTaskId(),16633,new LoginUser(19,8020));


        //projectStatusChangeService.pause(8581);


    }
}

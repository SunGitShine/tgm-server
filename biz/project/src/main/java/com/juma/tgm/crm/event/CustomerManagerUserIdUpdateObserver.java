package com.juma.tgm.crm.event;

import com.juma.auth.employee.domain.Employee;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.employee.service.EmployeeService;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.event.TMSObservable;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.customerManager.domain.FixedDemand;
import com.juma.tgm.customerManager.domain.Task4Waybill;
import com.juma.tgm.customerManager.domain.TaskWaybillTemplate;
import com.juma.tgm.customerManager.service.FixedDemandService;
import com.juma.tgm.customerManager.service.Task4WaybillService;
import com.juma.tgm.customerManager.service.TaskWaybillTemplateService;
import com.juma.tgm.project.domain.Project;
import com.juma.tgm.project.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Observable;
import java.util.Observer;


/***
 * 客户所属客户经理 变化观察者
 *
 * @author  huangxing
 *
 *
 * @date 2018 - 08 - 28 下午 四点
 *
 *
 * */
public class CustomerManagerUserIdUpdateObserver implements Observer {

    private static final Logger LOGGER = LoggerFactory.getLogger( CustomerManagerUserIdUpdateObserver.class );

    private CustomerInfoService customerInfoService ;

    private ProjectService projectService;

    private FixedDemandService fixedDemandService;

    private EmployeeService employeeService;

    private Task4WaybillService task4WaybillService ;

    private TaskWaybillTemplateService taskWaybillTemplateService;

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    public void setFixedDemandService(FixedDemandService fixedDemandService) {
        this.fixedDemandService = fixedDemandService;
    }

    public void setCustomerInfoService(CustomerInfoService customerInfoService) {
        this.customerInfoService = customerInfoService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void setTask4WaybillService(Task4WaybillService task4WaybillService) {
        this.task4WaybillService = task4WaybillService;
    }

    public void setTaskWaybillTemplateService(TaskWaybillTemplateService taskWaybillTemplateService) {
        this.taskWaybillTemplateService = taskWaybillTemplateService;
    }

    @Override
    public void update(Observable o, Object arg) {
        if( !(arg instanceof CustomerInfo) ) {
            LOGGER.info("错误的观察者事件参数，这里应该是：" + CustomerInfo.class ,new RuntimeException());
            return;
        }
        // 最新的
        CustomerInfo customerInfoNew = (CustomerInfo) arg;
        if( customerInfoNew.getCustomerId() != null ){
            CustomerInfo customerInfo=  customerInfoService.findCusInfoById( customerInfoNew.getCustomerId());
            // 做null 判断 容错
            if(customerInfoNew.getCustomerManagerUserId()!= null) {
                log( o , customerInfo , customerInfoNew );
                //
                updateTask4ManagerUser( customerInfoNew );
                try {
                    updateProjectManagerUser( customerInfoNew );
                }
                catch ( Exception  e){
                    LOGGER.error("同步修改项目的客户经理发生异常", e  );
                }
                try {
                    updateFixedDemandManagerUser( customerInfoNew );
                }catch ( Exception e) {
                    LOGGER.error("同步修改固定需求的客户经理发生异常", e  );
                }
            }
        }
        else {
            // 如果为空 则为新增 ，不存在 经理人的变化,因此直接忽略就好
        }
    }

    private static void log(Observable o ,CustomerInfo customerInfo , CustomerInfo customerInfoNew) {
        TMSObservable tmsObservable = (TMSObservable) o;
        LOGGER.info(tmsObservable.getName() + ":" + "客户：" + customerInfo.getCustomerName() +"的经理发生变化，" + customerInfo.getCustomerManagerUserId() +"to" + customerInfoNew.getCustomerManagerUserId());
    }

    // 更新 项目 的 客户经理id
    private void updateProjectManagerUser( CustomerInfo customerInfoNew ) {
        List<Project> projects =  projectService.findProject( customerInfoNew.getCustomerId() );
        if( projects != null && !projects.isEmpty()) {
            for( Project project : projects) {
                project.setManagerId( customerInfoNew.getCustomerManagerUserId() );
                project.setAreaCode( customerInfoNew.getAreaCode() );
            }
            LoginUser loginUser = new LoginUser(customerInfoNew.getTenantId(), customerInfoNew.getLastUpdateUserId());
            this.projectService.update( projects ,  loginUser );

        }
    }

    // 更新客户固定需求的 客户经理id
    private void updateFixedDemandManagerUser( CustomerInfo customerInfoNew) {
        List<FixedDemand> fixedDemands = fixedDemandService.find( customerInfoNew.getCustomerId() );
        if( fixedDemands != null && !fixedDemands.isEmpty()){
            for( FixedDemand fixedDemand : fixedDemands ) {
                fixedDemand.setCustomerManagerId( customerInfoNew.getCustomerManagerUserId() );
            }
            fixedDemandService.updateBatch( fixedDemands);
        }
    }

    // 更新定时下单相关数据
    private void updateTask4ManagerUser( CustomerInfo customerInfoNew ) {
        LoginUser loginUser = new LoginUser(customerInfoNew.getTenantId(), customerInfoNew.getLastUpdateUserId());
        LoginEmployee loginEmployee = new LoginEmployee();
        BeanUtils.copyProperties( loginUser , loginEmployee );
        loginEmployee.setEmployeeId( customerInfoNew.getCustomerManagerUserId() );
        List<TaskWaybillTemplate> taskWaybillTemplates = taskWaybillTemplateService.findByCustomerId( customerInfoNew.getCustomerId() );
        if( taskWaybillTemplates != null && !taskWaybillTemplates.isEmpty()) {
            for( TaskWaybillTemplate taskWaybillTemplate : taskWaybillTemplates) {
                Task4Waybill task4Waybill = task4WaybillService.getByTemplateId( taskWaybillTemplate.getTaskWaybillTemplateId() );
                taskWaybillTemplate.setCustomerManagerId( customerInfoNew.getCustomerManagerUserId() );
                try{
                    taskWaybillTemplateService.updateTaskWaybillTemplate( taskWaybillTemplate , loginUser , true );
                }
                catch ( Exception e) {
                    LOGGER.error("更新taskWaybillTemplate的客户经理（经纪人）ID 异常" , e );
                }
                if( task4Waybill != null ) {
                    try {
                        Employee employee = employeeService.loadEmployee(customerInfoNew.getCustomerManagerUserId());
                        task4Waybill.setUserId(employee.getUserId());
                        task4Waybill.setEmployeeId( customerInfoNew.getCustomerManagerUserId() );
                        task4WaybillService.updateTask4Waybill( task4Waybill , loginEmployee );
                    }
                    catch ( Exception e ) {
                        LOGGER.error("同步更新 task4Waybill的employeeId和userId出现异常", e );
                    }
                }
            }
        }
    }

}

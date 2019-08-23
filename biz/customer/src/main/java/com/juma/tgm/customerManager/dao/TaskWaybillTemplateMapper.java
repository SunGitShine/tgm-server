package com.juma.tgm.customerManager.dao;

import com.juma.tgm.customerManager.domain.TaskWaybillTemplate;
import com.juma.tgm.customerManager.domain.TaskWaybillTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface TaskWaybillTemplateMapper {
    int countByExample(TaskWaybillTemplateExample example);

    int deleteByExample(TaskWaybillTemplateExample example);

    @Delete({
        "delete from task_waybill_template",
        "where task_waybill_template_id = #{taskWaybillTemplateId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer taskWaybillTemplateId);

    @Insert({
        "insert into task_waybill_template (customer_manager_id, customer_id, ",
        "truck_customer_id, business_branch, ",
        "required_min_temperature, required_max_temperature, ",
        "remark, estimate_freight, ",
        "show4_driver_freight, delivery_time_point, ",
        "finish_time_point, receipt_type, ",
        "require_json, vehicle_count, ",
        "need_delivery_point_note, project_id, ",
        "receive_way, only_load_cargo, ",
        "agency_take_freight, create_time, ",
        "create_user_id, last_update_time, ",
        "last_update_user_id, valuation_way, ",
        "project_freight_rule_json, road_map_id,department_code)",
        "values (#{customerManagerId,jdbcType=INTEGER}, #{customerId,jdbcType=INTEGER}, ",
        "#{truckCustomerId,jdbcType=INTEGER}, #{businessBranch,jdbcType=TINYINT}, ",
        "#{requiredMinTemperature,jdbcType=REAL}, #{requiredMaxTemperature,jdbcType=REAL}, ",
        "#{remark,jdbcType=VARCHAR}, #{estimateFreight,jdbcType=DECIMAL}, ",
        "#{show4DriverFreight,jdbcType=DECIMAL}, #{deliveryTimePoint,jdbcType=VARCHAR}, ",
        "#{finishTimePoint,jdbcType=INTEGER}, #{receiptType,jdbcType=TINYINT}, ",
        "#{requireJson,jdbcType=VARCHAR}, #{vehicleCount,jdbcType=INTEGER}, ",
        "#{needDeliveryPointNote,jdbcType=TINYINT}, #{projectId,jdbcType=INTEGER}, ",
        "#{receiveWay,jdbcType=TINYINT}, #{onlyLoadCargo,jdbcType=TINYINT}, ",
        "#{agencyTakeFreight,jdbcType=DECIMAL}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{createUserId,jdbcType=INTEGER}, #{lastUpdateTime,jdbcType=TIMESTAMP}, ",
        "#{lastUpdateUserId,jdbcType=INTEGER}, #{valuationWay,jdbcType=TINYINT}, ",
        "#{projectFreightRuleJson,jdbcType=VARCHAR}, #{roadMapId,jdbcType=INTEGER},",
        "#{departmentCode,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="taskWaybillTemplateId", before=false, resultType=Integer.class)
    int insert(TaskWaybillTemplate record);

    int insertSelective(TaskWaybillTemplate record);

    List<TaskWaybillTemplate> selectByExample(TaskWaybillTemplateExample example);

    @Select({
        "select",
        "task_waybill_template_id, customer_manager_id, customer_id, truck_customer_id, ",
        "business_branch, required_min_temperature, required_max_temperature, remark, ",
        "estimate_freight, show4_driver_freight, delivery_time_point, finish_time_point, ",
        "receipt_type, require_json, vehicle_count, need_delivery_point_note, project_id, ",
        "receive_way, only_load_cargo, agency_take_freight, create_time, create_user_id, ",
        "last_update_time, last_update_user_id, valuation_way, project_freight_rule_json, ",
        "road_map_id,department_code ",
        "from task_waybill_template",
        "where task_waybill_template_id = #{taskWaybillTemplateId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    TaskWaybillTemplate selectByPrimaryKey(Integer taskWaybillTemplateId);

    int updateByExampleSelective(@Param("record") TaskWaybillTemplate record, @Param("example") TaskWaybillTemplateExample example);

    int updateByExample(@Param("record") TaskWaybillTemplate record, @Param("example") TaskWaybillTemplateExample example);

    int updateByPrimaryKeySelective(TaskWaybillTemplate record);

    @Update({
        "update task_waybill_template",
        "set customer_manager_id = #{customerManagerId,jdbcType=INTEGER},",
          "customer_id = #{customerId,jdbcType=INTEGER},",
          "truck_customer_id = #{truckCustomerId,jdbcType=INTEGER},",
          "business_branch = #{businessBranch,jdbcType=TINYINT},",
          "required_min_temperature = #{requiredMinTemperature,jdbcType=REAL},",
          "required_max_temperature = #{requiredMaxTemperature,jdbcType=REAL},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "estimate_freight = #{estimateFreight,jdbcType=DECIMAL},",
          "show4_driver_freight = #{show4DriverFreight,jdbcType=DECIMAL},",
          "delivery_time_point = #{deliveryTimePoint,jdbcType=VARCHAR},",
          "finish_time_point = #{finishTimePoint,jdbcType=INTEGER},",
          "receipt_type = #{receiptType,jdbcType=TINYINT},",
          "require_json = #{requireJson,jdbcType=VARCHAR},",
          "vehicle_count = #{vehicleCount,jdbcType=INTEGER},",
          "need_delivery_point_note = #{needDeliveryPointNote,jdbcType=TINYINT},",
          "project_id = #{projectId,jdbcType=INTEGER},",
          "receive_way = #{receiveWay,jdbcType=TINYINT},",
          "only_load_cargo = #{onlyLoadCargo,jdbcType=TINYINT},",
          "agency_take_freight = #{agencyTakeFreight,jdbcType=DECIMAL},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "create_user_id = #{createUserId,jdbcType=INTEGER},",
          "last_update_time = #{lastUpdateTime,jdbcType=TIMESTAMP},",
          "last_update_user_id = #{lastUpdateUserId,jdbcType=INTEGER},",
          "valuation_way = #{valuationWay,jdbcType=TINYINT},",
          "project_freight_rule_json = #{projectFreightRuleJson,jdbcType=VARCHAR},",
          "road_map_id = #{roadMapId,jdbcType=INTEGER},",
          "department_code = #{departmentCode,jdbcType=VARCHAR}",
        "where task_waybill_template_id = #{taskWaybillTemplateId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TaskWaybillTemplate record);
}
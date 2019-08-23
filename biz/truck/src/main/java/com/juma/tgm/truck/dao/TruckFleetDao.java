package com.juma.tgm.truck.dao;

import com.giants.common.tools.PageQueryCondition;
import com.giants.dal.dao.mybatis.MybatisDao;
import com.juma.tgm.truck.domain.TruckFleet;
import com.juma.tgm.truck.domain.vo.TruckFleetDriverRelationVo;
import com.juma.tgm.truck.domain.vo.TruckFleetQueryVo;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author weilibin
 * @version V1.0
 * 车队DAO
 * @Description: 车队
 * @date 2016年5月12日 下午4:23:32
 */
public interface TruckFleetDao extends MybatisDao<TruckFleet> {

    /**
     * 根据客户经理账号ID解除客户经理与车队的关联
     *
     * @param truckFleet
     * @author Libin.Wei
     * @Date 2017年5月19日 下午3:41:34
     */
    void updateToUnbundlingFleetAndUser(TruckFleet truckFleet);

    /**
     * @param truckFleetIdList 车队ID集合
     * @return List<TruckFleet>
     * @Description 批量查询
     * @author Libin.Wei
     * @Date 2017年3月9日 下午4:17:08
     */
    List<TruckFleet> listTruckFleetByListTruckFleetId(List<Integer> truckFleetIdList);


    /**
     * 通过车队名称，司机名称，车牌号 搜索车队
     *
     * @param queryCondition
     * @return
     */
    @SelectProvider(type = SqlBuilder.class, method = "joinSelectTruck")
    List<TruckFleetDriverRelationVo> searchTruckFleet(@Param("queryCondition") PageQueryCondition queryCondition);

    /**
     * 搜索前count
     *
     * @param queryCondition
     * @return
     */
    @SelectProvider(type = SqlBuilder.class, method = "joinCountTruck")
    int searchTruckFleetCount(@Param("queryCondition") PageQueryCondition queryCondition);


    class SqlBuilder {
        public static String joinSelectTruck(@Param("queryCondition") PageQueryCondition<TruckFleetQueryVo> queryCondition) {
            TruckFleetQueryVo filters = queryCondition.getFilters();

            SQL sql = new SQL();
            sql.SELECT("tf.truck_fleet_id AS truckFleetId, tft.truck_id AS truckId");

            condition(filters, sql);

            sql.GROUP_BY("tft.truck_id");

            String str = sql.toString();
            str += " limit #{queryCondition.startOffSet}, #{queryCondition.endOffSet}";
            return str;
        }

        public static String joinCountTruck(@Param("queryCondition") PageQueryCondition<TruckFleetQueryVo> queryCondition) {
            TruckFleetQueryVo filters = queryCondition.getFilters();

            SQL sql = new SQL();
            sql.SELECT("COUNT(DISTINCT(tft.truck_id))");

            condition(filters, sql);

            return sql.toString();
        }

        private static void condition(TruckFleetQueryVo filters, SQL sql) {
            sql.FROM("truck_fleet tf");

            sql.LEFT_OUTER_JOIN("truck_fleet_truck tft ON tf.truck_fleet_id = tft.truck_fleet_id");

            sql.WHERE("tf.employee_id = #{queryCondition.filters.employeeId}");

            if (StringUtils.isNotBlank(filters.getKeyword())) {
                sql.AND();
                sql.WHERE("(tf.truck_fleet_name LIKE CONCAT(#{queryCondition.filters.keyword}, '%')" +
                        " or tft.plate_number LIKE CONCAT(#{queryCondition.filters.keyword}, '%'))");
            }
        }
    }
}

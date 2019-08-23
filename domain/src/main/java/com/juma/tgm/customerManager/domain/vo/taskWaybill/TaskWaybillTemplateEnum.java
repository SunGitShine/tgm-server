package com.juma.tgm.customerManager.domain.vo.taskWaybill;

import com.giants.common.exception.BusinessException;
import org.apache.commons.lang.math.NumberUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: TaskWaybillTemplateEnum
 * @Description:
 * @author: liang
 * @date: 2018-09-28 16:50
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class TaskWaybillTemplateEnum implements Serializable {

    public final static Map<Integer, String> dayOfWeek = new HashMap<>();
    static {
        dayOfWeek.put(1, "sun");
        dayOfWeek.put(2, "mon");
        dayOfWeek.put(3, "tue");
        dayOfWeek.put(4, "wen");
        dayOfWeek.put(5, "thu");
        dayOfWeek.put(6, "fri");
        dayOfWeek.put(7, "sta");
    }

    /**
     * 是否自动下单
     */
    public enum AutoCreateBillEnum {
        NO_AUTO_CREATE(Byte.valueOf("1"), "不自动下单"),
        AUTO_CREATE(Byte.valueOf("2"), "自动下单");
        private Byte code;
        private String desc;

        AutoCreateBillEnum(Byte code, String desc) {
            this.desc = desc;
            this.code = code;
        }

        public static TaskWaybillTemplateEnum.AutoCreateBillEnum getByCode(Byte code) {
            for (TaskWaybillTemplateEnum.AutoCreateBillEnum type : TaskWaybillTemplateEnum.AutoCreateBillEnum.values()) {
                if (NumberUtils.compare(type.code, code) == 0) {
                    return type;
                }
            }
            throw new BusinessException("paramError", "errors.common.prompt", "IsAutoCreateBillEnum 参数错误");
        }

        public Byte getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 自动下单执行情况
     */
    public enum TaskStatusEnum {
        TASK_STATUS_FAIL(Byte.valueOf("1"), "执行失败"),
        TASK_STATUS_SUCCESS(Byte.valueOf("2"), "执行成功"),
        TASK_STATUS_HANDLED(Byte.valueOf("3"), "已处理");

        private Byte code;
        private String desc;

        TaskStatusEnum(Byte code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public static TaskWaybillTemplateEnum.TaskStatusEnum getByCode(Byte code) {
            for (TaskWaybillTemplateEnum.TaskStatusEnum statusEnum : TaskWaybillTemplateEnum.TaskStatusEnum.values()) {
                if (NumberUtils.compare(statusEnum.code, code) == 0) {
                    return statusEnum;
                }
            }

            return null;
        }

        public Byte getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 运单报告已读状态
     */
    public enum TaskReportReadStatusEnum {
        report_read_status_none(Byte.valueOf("1"), "未读"),
        report_read_status_done(Byte.valueOf("2"), "已读");

        private Byte code;
        private String desc;

        TaskReportReadStatusEnum(Byte code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Byte getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }


        public static TaskWaybillTemplateEnum.TaskReportReadStatusEnum getByCode(Byte code) {
            for (TaskWaybillTemplateEnum.TaskReportReadStatusEnum statusEnum : TaskWaybillTemplateEnum.TaskReportReadStatusEnum.values()) {
                if (NumberUtils.compare(statusEnum.code, code) == 0) {
                    return statusEnum;
                }
            }

            return null;
        }
    }

}

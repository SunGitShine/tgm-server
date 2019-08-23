package com.juma.tgm.common;

import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;

import com.giants.common.exception.BusinessException;

public class ExceptionUtil extends BusinessException {

    private static final long serialVersionUID = -1289169429659110731L;
    // mysql违反唯一性的代码
    private static final int UNIQUENESS_MYSQL = 1062;

    /**
     * 处理异常，判断唯一性
     * 
     * @param exception
     *            异常
     * @return boolean
     */
    public static boolean businessException(Object exception) {
        if (null != exception) {
            if (exception instanceof SQLException) {
                String errorMsg = ((SQLException) exception).getMessage();
                if (StringUtils.isNotBlank(errorMsg) && errorMsg.contains("Duplicate entry")) {
                    return true;
                }
                Integer errorCode = ((SQLException) exception).getErrorCode();
                if (null != errorCode && errorCode.equals(UNIQUENESS_MYSQL)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 基本异常处理，抛出businessException
     * 
     * @param exception
     *            异常
     * @param argArray
     *            提示的字符名称
     * @return void
     */
    public static void businessException(Object exception, Object argArray ) {
        if (null != exception) {
            if (exception instanceof SQLException) {
                Integer errorCode = ((SQLException) exception).getErrorCode();
                if (null != errorCode && errorCode.equals(1062)) {
                    if (null != argArray) {
                        if (argArray instanceof String) {
                            if (StringUtils.isNotBlank(String.valueOf(argArray))) {
                                throw new BusinessException("uniqueness", "errors.uniqueness", argArray);
                            } 
                        } else {
                            throw new BusinessException("uniqueness", "errors.uniqueness", argArray);
                        }
                    }
                    throw new BusinessException("defaultUniqueness", "errors.defaultUniqueness");
                }
            }
        }
    }

}

package identify;

import com.juma.tgm.common.IDValidator.IDValidator;

/**
 * @ClassName: IdentifyNumTest
 * @Description:
 * @author: liang
 * @date: 2018-09-07 14:19
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class IdentifyNumTest {

    public static void main(String[] args) {
        IDValidator idValidator = new IDValidator();
        String idNum = idValidator.makeID(false);

        System.out.println("args = [" + idNum + "]");
    }
}

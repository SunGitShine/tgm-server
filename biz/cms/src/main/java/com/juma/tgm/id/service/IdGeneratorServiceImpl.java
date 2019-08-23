package com.juma.tgm.id.service;

import com.juma.tgm.common.id.IdGeneratorTable;
import com.juma.tgm.common.id.IdGeneratorTable.IdType;
import com.juma.tgm.id.dao.IdGeneratorMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class IdGeneratorServiceImpl implements IdGeneratorService {

    @Resource
    private IdGeneratorMapper idGeneratorMapper;

    @Override
    public String genId(IdGeneratorTable.IdType idType) {
        if (idType == null) return null;
        StringBuilder sequence = new StringBuilder(idType.name());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String df = dateFormat.format(new Date());
        sequence.append(df);
        Integer id = idGeneratorMapper.genId(idType.getTableName());
        Integer random = ThreadLocalRandom.current().nextInt(100, 999);
        sequence.append(random);
        DecimalFormat format = new DecimalFormat("000000");
        sequence.append(format.format(id == null ? 0 : id % 100000));
        return sequence.toString();
    }

    @Override
    public String genProjectNo(IdType idType) {
        if (idType == null) return null;
        StringBuilder sequence = new StringBuilder(idType.name());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMM");
        String df = dateFormat.format(new Date());
        sequence.append(df);
        Integer id = idGeneratorMapper.genId(idType.getTableName());
        sequence.append(leftPad(id + "", 4, '0'));
        return sequence.toString();
    }

    /**TZD+YYMMDD+00001**/
    @Override
    public String genAdjustFormMasterNo(final IdType idType) {
        if (idType == null) { return null; }
        StringBuilder sequence = new StringBuilder(idType.name());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        String df = dateFormat.format(new Date());
        sequence.append(df);
        Integer id = idGeneratorMapper.genId(idType.getTableName());
        sequence.append(leftPad(id + "", 5, '0'));
        return sequence.toString();
    }

    @Override
    public String genTaskScheduledNo(IdType idType) {
        if (idType == null) return null;
        StringBuilder sequence = new StringBuilder(idType.name());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMM");
        String df = dateFormat.format(new Date());
        sequence.append(df);
        Integer id = idGeneratorMapper.genId(idType.getTableName());
        sequence.append(leftPad(id + "", 6, '0'));
        return sequence.toString();
    }

    public static void main(String[] args) {
        System.out.println(leftPad("ABCDEF", 5, 'x'));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMM");
        String df = dateFormat.format(new Date());
        System.out.println(df);
    }

    public static String leftPad(String originalString, int length, char padCharacter) {
        String paddedString = originalString;
        while (paddedString.length() < length) {
            paddedString = padCharacter + paddedString;
        }
        return paddedString;
    }

	@Override
	public String genProjectDailyId(IdType idType) {
		 if (idType == null) return null;
	        StringBuilder sequence = new StringBuilder("RB");
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	        String df = dateFormat.format(new Date());
	        sequence.append(df);
	        Integer id = idGeneratorMapper.genId(idType.getTableName());
	        sequence.append(leftPad(id + "", 5, '0'));
	        return sequence.toString();
	}
}

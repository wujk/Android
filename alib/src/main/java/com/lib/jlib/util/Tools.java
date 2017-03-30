package com.lib.jlib.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Tools {

	/**
	 * 时间戳转换
	 * @param time
	 * @param unit
	 * @return
	 */
	public static String dateToStr(long time, String unit) {
		String[] units = {"year", "month", "day", "hour", "minute", "second"};
		String[] cunits = {"年", "月", "日", "小时", "分钟", "秒"};
		long[] divs = {365 * 24 * 3600 * 1000L, 30 * 24 * 3600 * 1000L, 24 * 3600 * 1000L, 3600 * 1000L, 60 * 1000L, 1000L};
		long[] zdivs = {12, 30, 24, 60, 60};
		int i = 0;
		for (int j = 0; j < units.length; j++) {
			if (units[j].equals(unit)) {
				i = j;
				break;
			}
		}
		long result = time / divs[i];
		if (Math.abs(result) != 0) {
			if (--i >= 0) {
				if (Math.abs(result) / zdivs[i] != 0) {
					if (i < 0) {
						return "转换失败";
					}
					return dateToStr(time, units[i]);
				}
			}
			long ys = 0;
			if (i + 1 <= units.length) {
				ys = time % divs[i+1];
			}
			if (ys > 0 && i > 0)
				++result;
			return result + cunits[i + 1];
		} else {
			if (++i >= units.length)
				return "转换失败";
			return dateToStr(time, units[i]);
		}
	}
	
	/**
	 * 一个或多个数组合并后添加到list中
	 * @param objects Object类型的可变数组参数
	 * @return 合并后的list
	 */
	public static List<Object> combineArrays(Object[]... objects) {
		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < objects.length; i++) {
			list.addAll(Arrays.asList(objects[i]));
		}
		return list;
	}
	
	/**
	 * 输入流转字符串
	 * @param inputStream
	 * @param encode
	 * @return
	 */
	public static String streamToString(InputStream inputStream, String encode) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] data = new byte[1024];
		int len = 0;
		String result = "";
		if (inputStream != null) {
			try {
				while ((len = inputStream.read(data)) != -1) {
					outputStream.write(data, 0, len);
				}
				result = new String(outputStream.toByteArray(), encode);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (outputStream != null) {
						outputStream.close();
					}
					if (inputStream != null) {
						inputStream.close();
					}  
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	/**
     * 根据生日获取时间
     * @param birth
     * @return
     */
    public static int birth2age(long birth) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        cal.setTime(new Date(birth));
        int y = cal.get(Calendar.YEAR);
        return year - y;
    }
}

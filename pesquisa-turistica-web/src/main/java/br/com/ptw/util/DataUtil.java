package br.com.ptw.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataUtil {
	
	public static final String DATE_PATTERN_DEFAULT = "yyyy-MM-dd HH-mm-ss";

	public static Date stringToData(String dataStr, String pattern) {
		Date d = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			d = sdf.parse(dataStr);
		} catch (Exception e) {
		}
		return d;
	}

	public static String dataToString(Date data, String pattern) {
		String str = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			str = sdf.format(data);
		} catch (Exception e) {
		}
		return str;
	}

	public static Date formatarData(Date data, String pattern) {
		Date d = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			d = sdf.getCalendar().getTime();
		} catch (Exception e) {
		}
		return d;
	}

	public static Boolean contemDataNula(Date... values) {
		for (Date value : values) {
			if (value == null) {
				return true;
			}
		}

		return false;
	}

	public static Integer diferencaEntreDatas(Date dataInicio, Date dataFim) throws Exception {
		long tempo1 = dataInicio.getTime();
		long tempo2 = dataFim.getTime();
		long difTempo = tempo2 - tempo1;
		return (int) ((difTempo + 60L * 60 * 1000) / (24L * 60 * 60 * 1000)) + 1;
	}

	// public static int diferencaEntreDatas(String data1, String data2) throws
	// ParseException{
	// GregorianCalendar ini = new GregorianCalendar();
	// GregorianCalendar fim = new GregorianCalendar();
	// SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
	// ini.setTime(sdf.parse(data1));
	// fim.setTime(sdf.parse(data2));
	// long dt1 = ini.getTimeInMillis();
	// long dt2 = fim.getTimeInMillis();
	// return (int) (((dt2 - dt1) / 1000 *60 * 60 * 24)+1);
	// }

	public static Date addDias(Date data, Integer qtdDias) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + qtdDias);
		return c.getTime();
	}

	public static Date zerarHorario(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		return c.getTime();
	}

}

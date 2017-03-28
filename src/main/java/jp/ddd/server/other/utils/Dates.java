package jp.ddd.server.other.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author noguchi_kohei
 */
public final class Dates {

  /**
   * コンストラクタ
   */
  private Dates() {
    // インスタンス生成不可
  }

  public static Integer convertNull(Integer num) {
    if (num == null) {
      return 0;
    } else {
      return num;
    }
  }

  /**
   * デフォルトの日付フォーマット文字列
   */
  public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

  /**
   * 日付フォーマット
   */
  public static final String DATE_FORMAT_YYYYMM = "yyyyMM";

  /**
   * 日付フォーマット
   */
  public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";

  /**
   * 日時フォーマット
   */
  public static final String DATE_FORMAT_YYYYMMDDHHMM = "yyyyMMddHHmm";

  /**
   * 日時フォーマット
   */
  public static final String FILE_NAME_DATETIME_FORMAT = "yyyyMMddHH";

  /**
   * 投稿日付フォーマット
   */
  public static final String REGIST_DATE_FORMAT = "M/d HH:mm";

  /**
   * 投稿時間フォーマット
   */
  public static final String REGIST_TIME_FORMAT = "HH:mm";

  /**
   * 可読性の高いフォーマット(yyyy/MM/dd HH:mm:ss)
   */
  public static final String VIEW_DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";

  /**
   * 世界標準フォーマット(yyyy/MM/dd)
   */
  public static final String STANDARD_DATE_FORMAT = "yyyy/MM/dd";

  /**
   * 誕生日ファーマット
   */
  public static final String BIRTHDAY_FORMAT = "yyyy-MM-dd";

  /**
   * 時間単位まで表すフォーマット
   */
  public static final String HOUR_FORMAT = "yyyy-MM-dd HH";

  /**
   * 日付フォーマットを行う。
   *
   * @param date 日時
   * @return String datePattern
   */
  public static String toString(Date date) {
    if (date == null) return null;

    SimpleDateFormat dateFormatter = new SimpleDateFormat(VIEW_DATE_FORMAT);
    return dateFormatter.format(date);
  }

  /**
   * 日付フォーマットを行う。
   *
   * @param date        日時
   * @param datePattern 日付パターン
   * @return String datePattern
   */
  public static String toString(Date date, String datePattern) {
    if (date == null) return null;

    SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
    return dateFormatter.format(date);
  }

  /**
   * 日付フォーマットを行う(GMT)。
   *
   * @param date
   * @param datePattern
   * @return
   */
  public static String toStringGmt(Date date, String datePattern) {
    if (date == null) return null;

    SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
    dateFormatter.setTimeZone(TimeZone.getTimeZone("GMT"));
    return dateFormatter.format(date);
  }

  /**
   * 日付フォーマットを行う。
   *
   * @param date 日時
   * @return Date
   */
  public static Date toDate(String date) {
    if (date == null) return null;

    return toDate(DATE_FORMAT_YYYYMMDD, date);
  }

  /**
   * 日付フォーマットを行う。
   *
   * @param date   日時
   * @param format パターン
   * @return Date
   */
  public static Date toDate(String format, String date) {
    if (date == null) return null;

    SimpleDateFormat dateFormatter = new SimpleDateFormat(format);
    try {
      return (Date) dateFormatter.parse(date);
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * String型の日付フォーマットを変更する。
   *
   * @param inputFormat  入力フォーマット
   * @param outputFormat 　出力フォーマット
   * @param date         　String型の日時
   * @return
   */
  public static String format(String inputFormat, String outputFormat, String date) {
    Date inputDate = toDate(inputFormat, date);
    if (inputDate == null) {
      return null;
    }
    return toString(inputDate, outputFormat);
  }

  public static Date toUsLocalDate(String format, String date) {
    if (date == null) return null;

    SimpleDateFormat dateFormatter = new SimpleDateFormat(format, Locale.US);
    try {
      return (Date) dateFormatter.parse(date);
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * 現在時刻を取得します。
   *
   * @return Date today
   */
  public static Date now() {
    Date nowTime = Calendar.getInstance().getTime();
    return nowTime;
  }

  /**
   * 現在日時の0時0分を取得します。
   *
   * @return Date today
   */
  public static Date getTodayZeroDate() {
    Calendar cal = Calendar.getInstance();
    cal.set(getCurrentYear(), getCurrentMonth(), getCurrentDay(), 0, 0, 0);
    return cal.getTime();
  }

  /**
   * 指定日時の0時0分を取得します。
   *
   * @return Calendar cal
   */
  public static Calendar getZeroDate(Date date) {
    if (date == null) return null;

    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 0);
    return cal;
  }

  /**
   * 現在時刻を取得します。
   *
   * @return System.currentTimeMillis
   */
  public static long getNowTimeStamp() {
    return System.currentTimeMillis();
  }

  /**
   * 1日　24*60*60*1000
   */
  public static final long daySec = 86400000;

  /**
   * 1時間 60*60*1000
   */
  public static final long hourSec = 3600000;

  /**
   * 1分 60*1000
   */
  public static final long minSec = 60000;

  /**
   * datetimeから年を取得する。
   *
   * @param datetime
   * @return
   */
  public static int getYear(long datetime) {
    Date date = new Date(datetime);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    return Integer.parseInt(sdf.format(date));
  }

  /**
   * datetimeから月を取得する。
   *
   * @param datetime
   * @return
   */
  public static int getMonth(long datetime) {
    Date date = new Date(datetime);
    SimpleDateFormat sdf = new SimpleDateFormat("MM");
    return Integer.parseInt(sdf.format(date));
  }

  /**
   * datetimeから日を取得する。
   *
   * @param datetime
   * @return
   */
  public static int getDay(long datetime) {
    Date date = new Date(datetime);
    SimpleDateFormat sdf = new SimpleDateFormat("dd");
    return Integer.parseInt(sdf.format(date));
  }

  /**
   * datetimeから時を取得する。
   *
   * @param datetime
   * @return
   */
  public static int getHour(long datetime) {
    Date date = new Date(datetime);
    SimpleDateFormat sdf = new SimpleDateFormat("HH");
    return Integer.parseInt(sdf.format(date));
  }

  /**
   * datetimeから分を取得する。
   *
   * @param datetime
   * @return
   */
  public static int getMin(long datetime) {
    Date date = new Date(datetime);
    SimpleDateFormat sdf = new SimpleDateFormat("mm");
    return Integer.parseInt(sdf.format(date));
  }

  /**
   * datetimeから秒を取得する。
   *
   * @param datetime
   * @return
   */
  public static int getSec(long datetime) {
    Date date = new Date(datetime);
    SimpleDateFormat sdf = new SimpleDateFormat("ss");
    return Integer.parseInt(sdf.format(date));
  }

  /**
   * 現在の年を取得する。
   *
   * @return
   */
  public static int getCurrentYear() {
    Calendar cal = Calendar.getInstance();
    return cal.get(Calendar.YEAR);

  }

  /**
   * 現在の月を取得する。
   *
   * @return
   */
  public static int getCurrentMonth() {
    Calendar cal = Calendar.getInstance();
    return cal.get(Calendar.MONTH) + 1;

  }

  /**
   * 過去の月を取得する。
   *
   * @return
   */
  public static int getPastMonth(int pastCount) {
    Calendar cal = add(Calendar.getInstance(), 0, -pastCount, 0, 0, 0, 0, 0);
    return cal.get(Calendar.MONTH) + 1;
  }

  /**
   * 現在の日にちを取得する。
   *
   * @return
   */
  public static int getCurrentDay() {
    Calendar cal = Calendar.getInstance();
    return cal.get(Calendar.DATE);
  }

  /**
   * 0埋めした月のリストを取得する。
   *
   * @return
   */
  public static List<String> getMonthList() {
    List<String> monthList = new ArrayList<String>();
    for (int i = 1; i <= 12; i++) {
      monthList.add(Strings.paddingZero(i, 2));
    }
    return monthList;
  }

  /**
   * 現在の日付・時刻から指定の【年数】を加算・減算した結果を返します。
   *
   * @param addYera 加算・減算する年数
   * @return 計算後の Calendar インスタンス。
   */
  public static Calendar addYear(int addYera) {
    return add(null, addYera, 0, 0, 0, 0, 0, 0);
  }

  /**
   * 現在の日付・時刻から指定の【月数】を加算・減算した結果を返します。
   *
   * @param addMonth 加算・減算する月数
   * @return 計算後の Calendar インスタンス。
   */
  public static Calendar addMonth(int addMonth) {
    return add(null, 0, addMonth, 0, 0, 0, 0, 0);
  }

  /**
   * 現在の日付・時刻から指定の【日数】を加算・減算した結果を返します。
   *
   * @param addDate 加算・減算する日数
   * @return 計算後の Calendar インスタンス。
   */
  public static Calendar addDate(int addDate) {
    return add(null, 0, 0, addDate, 0, 0, 0, 0);
  }

  /**
   * 現在の日付・時刻から指定の【時間】を加算・減算した結果を返します。
   *
   * @param addHour 加算・減算する時間
   * @return 計算後の Calendar インスタンス。
   */
  public static Calendar addHour(int addHour) {
    return add(null, 0, 0, 0, addHour, 0, 0, 0);
  }

  /**
   * 現在の日付・時刻から指定の【分】を加算・減算した結果を返します。
   *
   * @param addMinute 加算・減算する分
   * @return 計算後の Calendar インスタンス。
   */
  public static Calendar addMinute(int addMinute) {
    return add(null, 0, 0, 0, 0, addMinute, 0, 0);
  }

  /**
   * 現在の日付・時刻から指定の【秒】を加算・減算した結果を返します。
   *
   * @param addSecond 加算・減算する秒
   * @return 計算後の Calendar インスタンス。
   */
  public static Calendar addSecond(int addSecond) {
    return add(null, 0, 0, 0, 0, 0, addSecond, 0);
  }

  /**
   * 現在の日付・時刻から指定の時間量を加算・減算した結果を返します。 年、月、日、時間、分、秒、ミリ秒の各時間フィールドに対し、 任意の時間量を設定できます。 たとえば、現在の日付時刻から 10 日前を計算する場合は以下となります。 Calendar cal =
   * add(null,0,0,-10,0,0,0,0); 各時間フィールドの値がその範囲を超えた場合、次の大きい時間フィールドが 増分または減分されます。 たとえば、以下では1時間と5分進めることになります。 Calendar cal = add(null,0,0,0,0,65,0,0);
   * 各時間フィールドに設定する数量が0の場合は、現在の値が設定されます。 java.util.GregorianCalendarの内部処理では以下の分岐を行っている。 if (amount == 0) { return; }
   *
   * @param cal            日付時刻の指定があればセットする。 nullの場合、現在の日付時刻で新しいCalendarインスタンスを生成する。
   * @param addYear        加算・減算する年数
   * @param addMonth       加算・減算する月数
   * @param addDate        加算・減算する日数
   * @param addHour        加算・減算する時間
   * @param addMinute      加算・減算する分
   * @param addSecond      加算・減算する秒
   * @param addMillisecond 加算・減算するミリ秒
   * @return 計算後の Calendar インスタンス。
   */
  public static Calendar add(Calendar cal, int addYear, int addMonth, int addDate, int addHour, int addMinute,
                             int addSecond, int addMillisecond) {
    if (cal == null) {
      cal = Calendar.getInstance();
    }
    cal.add(Calendar.YEAR, addYear);
    cal.add(Calendar.MONTH, addMonth);
    cal.add(Calendar.DATE, addDate);
    cal.add(Calendar.HOUR_OF_DAY, addHour);
    cal.add(Calendar.MINUTE, addMinute);
    cal.add(Calendar.SECOND, addSecond);
    cal.add(Calendar.MILLISECOND, addMillisecond);
    return cal;
  }

  /**
   * ExpirationTimeを生成する<br>
   * メソッドが呼び出された日時から{amount}時間後のlong<br>
   * 返り値は、UTC秒のlong型(UTCミリ秒ではないので注意)
   *
   * @return {amount}日後のUTC long
   */
  public static long createXtInUTCWithHour(int amount) {

    Calendar cal = new GregorianCalendar(Dates.getCurrentYear(), Dates.getCurrentMonth(), Dates.getCurrentDay(),
      Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE),
      Calendar.getInstance().get(Calendar.SECOND));
    cal.add(Calendar.MONTH, -1);
    cal.add(Calendar.HOUR, amount);

    return cal.getTimeInMillis() / 1000;

  }

  /**
   * ExpirationTimeを生成する<br>
   * メソッドが呼び出された日時から{amount}日後の23時59分59秒のlong<br>
   * 返り値は、UTC秒のlong型(UTCミリ秒ではないので注意)
   * @return {amount}日後のUTC long
   */
  public static long createXtInUTC(int amount) {

    Calendar cal = new GregorianCalendar(Dates.getCurrentYear(), Dates.getCurrentMonth(), Dates.getCurrentDay(), 23,
      59, 59);
    cal.add(Calendar.MONTH, -1);
    cal.add(Calendar.DATE, amount);

    return cal.getTimeInMillis() / 1000;

  }

  /**
   * ExpirationTimeを生成する 指定した{date}から{amount}日後のlong 返り値は、UTC秒のlong型(UTCミリ秒ではないので注意)
   *
   * @param  date 基準日
   * @return {amount}日後のUTC long
   */
  public static long createXtInUTC(Date date, int amount) {

    Calendar cal = new GregorianCalendar(Dates.getYear(date.getTime()), Dates.getMonth(date.getTime()),
      Dates.getDay(date.getTime()), 23, 59, 59);
    cal.add(Calendar.MONTH, -1);
    cal.add(Calendar.DATE, amount);

    return cal.getTimeInMillis() / 1000;

  }

  /**
   * 誕生日から、dateの時に何歳かを調べる。 (法的には誕生日の前日に年齢として加算されるのが正しいのでシビアな案件には使わないでください)
   *
   * @param 誕生日     birthday
   * @param 起点となる日付 date
   * @return 年齢
   */
  public static Integer getAge(Date birthday, Date date) {
    return ((Integer) ((Integer.parseInt(toString(date)) - Integer.parseInt(toString(birthday))) / 10000));
  }

  /**
   * 小学校の学年を返す。0だったらそれ以下。7だったらそれ以上。
   *
   * @param birthday
   * @param date
   * @return
   */
  public static Integer getSchoolGrade(Date birthday) {
    // 今年度の4/2に6歳のユーザは小学1年生, 7歳のユーザは小学2年生…
    Calendar cal = Calendar.getInstance();
    if (cal.get(Calendar.MONTH) < 3) {
      cal.add(Calendar.YEAR, -1);
    }
    cal.set(Calendar.MONTH, 3);
    cal.set(Calendar.DATE, 2);
    int val = ((Integer) ((Integer.parseInt(toString(cal.getTime())) - Integer.parseInt(toString(birthday)))
      / 10000));
    if (val < 6) {
      return -1;
    } else if (val == 6) {
      return 1;
    } else if (val == 7) {
      return 2;
    } else if (val == 8) {
      return 3;
    } else if (val == 9) {
      return 4;
    } else if (val == 10) {
      return 5;
    } else if (val == 11) {
      return 6;
    }
    return 7;
  }

  /**
   * 0歳児の月齢を返す
   *
   * @param birthday
   * @param date
   * @return
   */
  public static Integer getZeroAgeMonth(Date birthday, Date date) {
    Calendar cal1 = Calendar.getInstance();
    cal1.setTime(birthday);
    cal1.set(Calendar.DATE, 1);
    Calendar cal2 = Calendar.getInstance();
    cal2.setTime(date);
    cal2.set(Calendar.DATE, 1);
    if (cal1.after(cal2)) {
      return -1;
    }
    int count = -1;
    while (cal2.after(cal1)) {
      cal2.add(Calendar.MONTH, -1);
      count++;
    }
    return count;
  }

  /**
   * 同じ年齢に該当する誕生日の開始日を取得する
   *
   * @param age
   * @return
   */
  public static Calendar getSameAgeStartDate(Date birthday) {
    Integer age = getAge(birthday, now());
    Calendar cal = add(addYear((age + 1) * -1), 0, 0, 1, 0, 0, 0, 0);
    cal.set(Calendar.HOUR, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    return cal;
  }

  /**
   * 同じ年齢に該当する誕生日の終了日を取得する
   *
   * @param age
   * @return
   */
  public static Calendar getSameAgeEndDate(Date birthday) {
    Integer age = getAge(birthday, now());
    Calendar cal = addYear(age * -1);
    cal.set(Calendar.HOUR, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    return cal;
  }

  /**
   * カレンダー表示用配列取得
   *
   * @param targetDate
   * @return
   */
  public static int[][] getCalendarMatrix(Date targetDate) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(targetDate);

    // 月始めの曜日を取得
    cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
    int startDay = cal.get(Calendar.DAY_OF_WEEK);

    // 月末の日付を取得
    int lastDate = cal.getActualMaximum(Calendar.DATE);

    // カレンダー表の作成
    int row = 0;
    int col = startDay - 1;
    int[][] calendarMatrix = new int[6][7];
    for (int date = 1; date <= lastDate; date++) {
      calendarMatrix[row][col] = date;
      if (col == 6) {
        row++;
        col = 0;
      } else {
        col++;
      }
    }
    return calendarMatrix;
  }

  /**
   * 指定日の月末日を取得する
   *
   * @param targetDate
   * @return
   */
  public static Date getLastDate(Date targetDate) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(targetDate);
    // 月末の日付を取得
    int lastDay = cal.getActualMaximum(Calendar.DATE);
    cal.set(Calendar.DATE, lastDay);
    return cal.getTime();
  }

  /**
   * 指定日の月初日を取得する
   *
   * @param targetDate
   * @return
   */
  public static Date getFirstDate(Date targetDate) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(targetDate);
    int firstDay = cal.getActualMinimum(Calendar.DATE);
    cal.set(Calendar.DATE, firstDay);
    return cal.getTime();
  }

  /**
   * 日付の妥当性チェック
   *
   * @param year
   * @param month
   * @param day
   * @return
   */
  public static boolean checkDate(Integer year, Integer month, Integer day) {
    try {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
      dateFormat.setLenient(false);
      dateFormat.parse(String.format("%04d", year) + String.format("%02d", month) + String.format("%02d", day));
      return true;

    } catch (ParseException e) {
      return false;
    }
  }

  /**
   * {@value date}の次の時間のHH時00分00秒を取得する
   *
   * @param date
   * @return
   */
  public static Date getNextHH0000(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.HOUR_OF_DAY, 1);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    return cal.getTime();
  }

  /**
   * {@value date}の1時間前のHH時00分00秒を取得する
   *
   * @param date
   * @return
   */
  public static Date getBeforeHH0000(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.HOUR_OF_DAY, -1);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    return cal.getTime();
  }

  /**
   * {@value date}のHH時00分00秒を取得する
   *
   * @param date
   * @return
   */
  public static Date getNowHH0000(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    return cal.getTime();
  }

  /**
   * 2つの日付の月数の差を求めます。 java.util.Date 型の日付 date1 - date2 が何ヵ月かを整数で返します。 ※端数の日数は無視します。
   *
   * @param date1 日付1 java.util.Date
   * @param date2 日付2 java.util.Date
   * @return 2つの日付の月数の差
   */
  public static int getDifferenceMonth(Date date1, Date date2) {
    Calendar cal1 = Calendar.getInstance();
    cal1.setTime(date1);
    cal1.set(Calendar.DATE, 1);
    Calendar cal2 = Calendar.getInstance();
    cal2.setTime(date2);
    cal2.set(Calendar.DATE, 1);
    int count = 0;
    if (cal1.before(cal2)) {
      while (cal1.before(cal2)) {
        cal1.add(Calendar.MONTH, 1);
        count--;
      }
    } else {
      count--;
      while (!cal1.before(cal2)) {
        cal1.add(Calendar.MONTH, -1);
        count++;
      }
    }
    return count;
  }

  /**
   * 2つの日付が同日かどうかの判定
   *
   * @param date1
   * @param date2
   * @return
   */
  public static boolean isSameDate(Date date1, Date date2) {
    String dateStr1 = toString(date1, "yyyyMMdd");
    String dateStr2 = toString(date2, "yyyyMMdd");
    return dateStr1.equals(dateStr2);
  }

  /**
   * {@value date}の00:00のdateを取得する
   */
  public static Calendar getDailyStartDate(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.HOUR_OF_DAY, 00);
    cal.set(Calendar.MINUTE, 00);
    cal.set(Calendar.SECOND, 00);
    cal.set(Calendar.MILLISECOND, 000);
    return cal;
  }

  /**
   * {@value date}の23:59のdateを取得する
   *
   * @param date
   * @return
   */
  public static Calendar getDailyEndDate(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.HOUR_OF_DAY, 23);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.SECOND, 59);
    cal.set(Calendar.MILLISECOND, 999);
    return cal;
  }

  /**
   * {@value date}の月初のdateを取得する
   *
   * @param date
   * @return
   */
  public static Calendar getMonthlyFirstDate(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.DATE, 1);
    return cal;
  }

  /**
   * {@value date}の月末のdateを取得する
   *
   * @param date
   * @return
   */
  public static Calendar getMonthlyEndDate(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.getActualMaximum(Calendar.DAY_OF_MONTH), 23, 59,
      59);
    return cal;
  }

  /**
   * 中高生か否か
   *
   * @param birthday
   * @return
   */
  public static boolean isJuniorAndSeniorHighSchoolStudent(Date birthday) {
    // これより未来に生まれたら高校生以下
    Calendar oldCal = Calendar.getInstance();
    if (oldCal.get(Calendar.MONTH) < 3) {
      oldCal.set(oldCal.get(Calendar.YEAR) - 19, 3, 1, 0, 0, 0);
    } else {
      oldCal.set(oldCal.get(Calendar.YEAR) - 18, 3, 1, 0, 0, 0);
    }

    // これより過去に生まれたら中学生以上
    Calendar youngCal = Calendar.getInstance();
    if (youngCal.get(Calendar.MONTH) < 3) {
      youngCal.set(youngCal.get(Calendar.YEAR) - 13, 3, 1, 0, 0, 0);
    } else {
      youngCal.set(youngCal.get(Calendar.YEAR) - 12, 3, 1, 0, 0, 0);
    }

    if (birthday.after(oldCal.getTime()) && birthday.before(youngCal.getTime())) {
      return true;
    }
    return false;
  }

  /**
   * {@value targetDate}から{@value index}日前の日付を取得
   *
   * @param index
   * @return
   */
  public static Date getPreviousDate(Date targetDate, int index) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(targetDate);
    cal.add(Calendar.DAY_OF_MONTH, -index);
    return cal.getTime();
  }

  /**
   * String型の時間の前後関係を精査します。
   *
   * @param beforeStrDate
   * @param afterStrDate
   * @param format        　このクラスのフィールド上にあるフォーマット
   * @return 前後関係が正しければtrue
   */
  public static boolean checkDateContext(String beforeStrDate, String afterStrDate, String format) {
    Date beforeDate = toDate(format, beforeStrDate);
    Date afterDate = toDate(format, afterStrDate);

    return beforeDate.before(afterDate);
  }

  /**
   * 時間のフォーマットを精査します。
   *
   * @param date
   * @param format
   * @return
   */
  public static boolean checkDateFormat(String date, String format) {
    if (date == null) return false;

    SimpleDateFormat dateFormatter = new SimpleDateFormat(format);
    try {
      dateFormatter.parse(date);
      return true;
    } catch (ParseException e) {
      e.printStackTrace();
      return false;
    }

  }

  /**
   * 時間のフォーマット及び前後関係を精査します
   *
   * @param beforeStrDate
   * @param afterStrDate
   * @param format
   * @return
   */
  public static boolean checkDateFormatAndContext(String beforeStrDate, String afterStrDate, String format) {

    if (!checkDateFormat(beforeStrDate, format) || !checkDateFormat(afterStrDate, format)) {
      return false;
    }
    if (!checkDateContext(beforeStrDate, afterStrDate, format)) {
      return false;
    }
    return true;
  }

  /**
   * dateが指定日当日（現在時間から算出）かどうかを判定します。
   *
   * @param date
   * @return
   */
  public static boolean isAppointedDay(Date date, Date AppointedDate) {
    Date startAppointedDate = getDailyStartDate(AppointedDate).getTime();
    Date endAppointedDate = getDailyEndDate(AppointedDate).getTime();

    return startAppointedDate.before(date) && date.before(endAppointedDate);
  }
}

package timer2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class XMLwriter {

	private static final String SCHEDULEDATA = "ScheduleData";
	private static final String BROADCASTDATA = "BroadcastData";
	private static final String SERVICE = "Service";
	private static final String attVERSION = "version";
	private static final String attVERSION_TEXT = "SBL v1.0";
	private static final String attCREATIONTIME = "creationtime";
	private static final String attDATE = "date";
	private static final String attSERVICE_NAME = "service_name";
	private static final String attSERVICE_ID = "service_id";
	private static final String EVENT = "Event";
	private static final String attDURATION = "duration";
	private static final String attENDTIME = "endtime";
	private static final String attBEGINTIME = "begintime";
	private static final String attEVENTID = "eventid";
	private static final String EPGTEXT = "EPGText";
	private static final String attLANGUAGE = "Language";
	private static final String attLANGUAGE_TEXT = "CHN";
	private static final String NAME = "Name";
	private static final String DESCRIPTION = "Description";
	private static final String ID_CATEGORY = "ID_category";
	private static final String attCAT1 = "cat1";
	private static final String attCAT2 = "cat2";
	private static final String CAT1_TEXT = "1";
	private static final String CAT2_TEXT = "0";
	private static final String DURATION_TEXT = "010000";
	
	public XMLwriter() {
		
		IOUtil.deleteDir(new File(ADDRESS_PREFFIX));
		deleteOldDirFinished = true;
	}

	private final static String[] timeArr = { "000000", "010000", "020000", "030000", "040000", "050000", "060000",
			"070000", "080000", "090000", "100000", "110000", "120000", "130000", "140000", "150000", "160000",
			"170000", "180000", "190000", "200000", "210000", "220000", "230000" };

	static final Format format = Format.getCompactFormat();
	{
		format.setEncoding("GB2312");
		format.setIndent("	");
	}


	static final String ADDRESS_PARENT = IOUtil.HOME_DIRECTORY + "\\央视TXT\\";
	static final String ADDRESS_PREFFIX = ADDRESS_PARENT + "\\new";
	private static final String ADDRESS_DIR  = ADDRESS_PREFFIX + "\\";
	
	private static final Calendar createTimeCalendar = Calendar.getInstance();
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd HH:mm:ss");
	private static final String attCREATIONTIME_TEXT = dateFormat.format(createTimeCalendar.getTime());
	private static final SimpleDateFormat ScheduleFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat fileSaveFormat = new SimpleDateFormat("yyyyMMdd");
	private static boolean deleteOldDirFinished = false;

	static int dirIndex = 1;

	public void writeXML(int n, Calendar c){

		while (!deleteOldDirFinished) {
			try {
				wait(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		int default_day = 7;
		default_day = n;
		IOUtil.checkDir(ADDRESS_PARENT);
		IOUtil.checkDir(ADDRESS_PREFFIX);
		final Calendar calendar = c;

		int zipSize = 7;

		
		for (int i = 0; i < default_day; i++) {

			String curr_day = getDateStringFromCalendar(calendar);

			Element root = new Element(BROADCASTDATA);
			root.setAttribute(attVERSION, attVERSION_TEXT);
			root.setAttribute(attCREATIONTIME, attCREATIONTIME_TEXT);
			Document doc = new Document(root);

			Element ScheduleData = new Element(SCHEDULEDATA);

			String attDATE_TEXT = ScheduleFormat.format(calendar.getTime());
			ScheduleData.setAttribute(attDATE, attDATE_TEXT);
			root.addContent(ScheduleData);

			calendar.add(Calendar.DATE, 1);
			String next_day = getDateStringFromCalendar(calendar);

			for (Channel channel : Channel.values()) {

				Element Service = new Element(SERVICE);

				Service.setAttribute(attSERVICE_NAME, channel.toString());
				Service.setAttribute(attSERVICE_ID, channel.getId());
				ScheduleData.addContent(Service);

				for (int eventid = 1; eventid < 25; eventid++) {
					Element Event = new Element(EVENT);
					Event.setAttribute(attEVENTID, "" + eventid);
					int timeIndex = eventid - 1;
					Event.setAttribute(attBEGINTIME, curr_day + timeArr[timeIndex]);

					if (eventid != 24) {
						Event.setAttribute(attENDTIME, curr_day + timeArr[eventid]);
					} else {
						Event.setAttribute(attENDTIME, next_day + timeArr[0]);
					}
					
					Event.setAttribute(attDURATION, DURATION_TEXT);
					Service.addContent(Event);

					Element EPGText = new Element(EPGTEXT);
					EPGText.setAttribute(attLANGUAGE, attLANGUAGE_TEXT);
					Event.addContent(EPGText);

					Element Name = new Element(NAME);
					String nameAndDesp = channel.getName();
					Name.setText(nameAndDesp);
					Element Description = new Element(DESCRIPTION);
					Description.setText(nameAndDesp);
					Element ID_category = new Element(ID_CATEGORY);
					ID_category.setAttribute(attCAT1, CAT1_TEXT);
					ID_category.setAttribute(attCAT2, CAT2_TEXT);

					EPGText.addContent(Name);
					EPGText.addContent(Description);
					EPGText.addContent(ID_category);
				}

			}

			XMLOutputter XMLOut = new XMLOutputter();
			XMLOut.setFormat(format);
			
			String dirPath = ADDRESS_DIR + dirIndex;
			IOUtil.checkDir(dirPath);

			if (i != 0 && i % zipSize == 0) {

				dirIndex++;
				if (dirIndex > 3) {
					dirIndex = 4;
				}
				dirPath = ADDRESS_DIR + dirIndex;
				IOUtil.checkDir(dirPath);
				
			}

			String filePath = dirPath + "\\" + curr_day + "cctv.xml";
			try {
				XMLOut.output(doc, new FileOutputStream(filePath));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}


	}


	private String getDateStringFromCalendar(Calendar calendar) {
		return fileSaveFormat.format(calendar.getTime());
	}

	private enum Channel {
		
		CCTV1("CCTV-1 综合",101),CCTV10("CCTV-10 科教",1604),
		CCTV11("CCTV-11 戏曲",1605),CCTV12("CCTV-12 社会与法",1606),
		CCTVNEWS("CCTV-13 新闻",308),CCTV1C("CCTV-14 少儿",1902),
		CCTV1M("CCTV-15 音乐",1903),CCTV2("CCTV-2 财经",102),
		CCTV3("CCTV-3 综艺",1601),CCTV5("CCTV-5 体育",103),
		CCTV6("CCTV-6 电影",104),CCTV7("CCTV-7 军事农业",1603),
		CCTV8("CCTV-8 电视剧",105),CCTV9("CCTV-9 纪录",106),
		CCTVYYNEWS("CCTV-NEWS",912),CCTVGQ("CCTV1HD",909),
		CCTVGW("CCTV高尔夫网球",913),DNWS("东南卫视",339),
		ZGJS("中国教视",339),YNWS("云南卫视",336),BTWS("兵团卫视",341),
		NMWS("内蒙卫视",325),BJWS("北京卫视",311),XMWS("厦门卫视",334),
		JLWS("吉林卫视",321),SCWS("四川卫视",331),TJWS("天津卫视",332),
		AHWS("安徽卫视",310),SDWS("山东卫视",328),SXWS("山西卫视",329),
		GDWS("广东卫视",314),GXWS("广西卫视",315),JSWS("江苏卫视",322),
		JXWS("江西卫视",323),HEBEIWS("河北卫视",317),HENANWS("河南卫视",318),
		ZJWS("浙江卫视",337),SZWS("深圳卫视",340),HBWS("湖北卫视",320),
		HNWS("湖南卫视",306),GSWS("甘肃卫视",313),FJWS("福建卫视",312),
		XZWS("西藏卫视",333),GZWS("贵州卫视",316),LNWS("辽宁卫视",324),
		CQWS("重庆卫视",338),SHANXIWS("陕西卫视",330),QHWS("青海卫视",327),
		HLJW("黑龙江卫",319);
		
		private String name;
		private int id;
		
		public String toString() {
			
			String acronym = super.toString();
			
			if (acronym.equals("CCTV1C")) {
				acronym = "CCTV-C";
			} else if (acronym.equals("CCTV1M")) {
				acronym = "CCTV-M";
			}
			
			return acronym;
		}

		private Channel(String name, int id) {
			this.name = name;
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public String getId() {
			return "" + id;
		}

	}
	
}

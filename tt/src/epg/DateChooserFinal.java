package epg;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

@SuppressWarnings("serial")
public class DateChooserFinal extends JPanel {
	
//	private static final XMLwriter XMLwriter = new XMLwriter();
//	
//	void writeXML(final int n, final Calendar c) {
//
//		XMLwriter.writeXML(n, c);
//		zipFiles();	
//	}
//	
//
//	static String ADDRESS_PARENT = timer2.XMLwriter.ADDRESS_PARENT;
//	static String ADDRESS_PREFFIX = timer2.XMLwriter.ADDRESS_PREFFIX;

//	private void zipFiles() {
//		 File root = new File(ADDRESS_PREFFIX);
//		 File[] files = root.listFiles();
//
//		 for (int i = 0; i < files.length; i++) {
//			 File[] xmls = files[i].listFiles();
//			 int size = xmls.length;
//			 String first = getFirstName(xmls[0].getName()); 
//			 String last = getLastName(xmls[size-1].getName());
//			 Zipper zipper = new Zipper(ADDRESS_PARENT + first + last);
//			 zipper.compressExe(ADDRESS_PREFFIX + "\\" + (i+1));
//		 }
// 
//	}
	
	ArrayList<Checkbox> checkBoxes = new ArrayList<Checkbox>();


	private class JP1 extends JPanel {
		JLabel left, right, center;

		public JP1() {
			super(new BorderLayout());
			this.setBackground(new Color(160, 185, 215));
			initJP1();
		}

		private void initJP1() {
			left = new JLabel(" << ", SwingConstants.CENTER);
			left.setToolTipText("上一月");
			right = new JLabel(" >> ", SwingConstants.CENTER);
			right.setToolTipText("下一月");
			left.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
			right.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
			center = new JLabel("", SwingConstants.CENTER);
			updateDate();
			this.add(left, BorderLayout.WEST);
			this.add(center, BorderLayout.CENTER);
			this.add(right, BorderLayout.EAST);
			this.setPreferredSize(new Dimension(295, 25));
			left.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(final MouseEvent me) {
					left.setCursor(new Cursor(Cursor.HAND_CURSOR));
					left.setForeground(Color.RED);
				}

				@Override
				public void mouseExited(final MouseEvent me) {
					left.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					left.setForeground(Color.BLACK);
				}

				@Override
				public void mousePressed(final MouseEvent me) {
					select.add(Calendar.MONTH, -1);
					left.setForeground(Color.WHITE);
					refresh();
				}

				@Override
				public void mouseReleased(final MouseEvent me) {
					left.setForeground(Color.BLACK);
				}
			});
			right.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(final MouseEvent me) {
					right.setCursor(new Cursor(Cursor.HAND_CURSOR));
					right.setForeground(Color.RED);
				}

				@Override
				public void mouseExited(final MouseEvent me) {
					right.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					right.setForeground(Color.BLACK);
				}

				@Override
				public void mousePressed(final MouseEvent me) {
					select.add(Calendar.MONTH, 1);
					right.setForeground(Color.WHITE);
					refresh();
				}

				@Override
				public void mouseReleased(final MouseEvent me) {
					right.setForeground(Color.BLACK);
				}
			});
		}

		private void updateDate() {
			center.setText(select.get(Calendar.YEAR) + "年" + (select.get(Calendar.MONTH) + 1) + "月");
		}
	}
	private class JP2 extends JPanel {
		public JP2() {
			this.setPreferredSize(new Dimension(295, 20));
		}

		@Override
		protected void paintComponent(final Graphics g) {
			g.setFont(font);
			g.drawString("星期日 星期一 星期二 星期三 星期四 星期五 星期六", 5, 10);
			g.drawLine(0, 15, getWidth(), 15);
		}
	}
	private class JP3 extends JPanel {
		public JP3() {
			super(new GridLayout(6, 7));
			this.setPreferredSize(new Dimension(295, 100));
			initJP3();
		}

		private void initJP3() {
			updateDate();
		}

		public void updateDate() {
			this.removeAll();
			lm.clear();
			final Date temp = select.getTime();
			final Calendar select = Calendar.getInstance();
			select.setTime(temp);
			select.set(Calendar.DAY_OF_MONTH, 1);
			final int index = select.get(Calendar.DAY_OF_WEEK);
			final int sum = (index == 1 ? 8 : index);
			select.add(Calendar.DAY_OF_MONTH, 0 - sum);
			for (int i = 0; i < 42; i++) {
				select.add(Calendar.DAY_OF_MONTH, 1);
				lm.addLabel(new MyLabel(select.get(Calendar.YEAR), select.get(Calendar.MONTH),
						select.get(Calendar.DAY_OF_MONTH)));
			}
			for (final MyLabel my : lm.getLabels()) {
				this.add(my);
			}
			select.setTime(temp);
		}
	}
	
	private class JP4 extends JPanel {
		public JP4() {
			super(new BorderLayout());
			this.setPreferredSize(new Dimension(295, 20));
			this.setBackground(new Color(160, 185, 215));
			final SimpleDateFormat sdf = new SimpleDateFormat("yy/MM//dd EEEE");
			final JLabel jl = new JLabel("今天: " + sdf.format(new Date()));
			jl.setToolTipText("点击回到今天日期");
			this.add(jl, BorderLayout.CENTER);
			jl.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(final MouseEvent me) {
					jl.setCursor(new Cursor(Cursor.HAND_CURSOR));
					jl.setForeground(Color.RED);
				}

				@Override
				public void mouseExited(final MouseEvent me) {
					jl.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					jl.setForeground(Color.BLACK);
				}

				@Override
				public void mousePressed(final MouseEvent me) {
					jl.setForeground(Color.WHITE);
					select.setTime(new Date());
					refresh();
					commit(select);
				}

				@Override
				public void mouseReleased(final MouseEvent me) {
					jl.setForeground(Color.BLACK);
				}
			});
		}
	}
	

	private class EPG extends JPanel {
	    Label egpLable = new Label("EPG AutoGen：");

	    public EPG() {

			Checkbox normalCB = new Checkbox("normal", false);
			Checkbox fridayCB = new Checkbox("Friday", false);
			Checkbox One_week_SD = new Checkbox("One-week-SD",false);
			Checkbox One_week_HD = new Checkbox("One-week-HD",false);
			checkBoxes.add(normalCB);
			checkBoxes.add(fridayCB);
			checkBoxes.add(One_week_SD);
			checkBoxes.add(One_week_HD);
			
	        setLayout(new GridLayout(6, 1));
	        add(egpLable);
	        add(normalCB);
	        add(fridayCB);
	        add(One_week_SD);
	        add(One_week_HD); 
	    }
	}
	
	private class LabelManager {
		private final List<MyLabel> list;

		public LabelManager() {
			list = new ArrayList<MyLabel>();
		}

		public void addLabel(final MyLabel my) {
			list.add(my);
		}

		public void clear() {
			list.clear();
		}

		public List<MyLabel> getLabels() {
			return list;
		}

		@SuppressWarnings("unused")
		public void setSelect(final MyLabel my, final boolean b) {
			for (final MyLabel m : list) {
				if (m.equals(my)) {
					m.setSelected(true, b);
				} else {
					m.setSelected(false, b);
				}
			}
		}

		public void setSelect(final Point p, final boolean b) {
			// 如果是拖动,则要优化一下,以提高效率
			if (b) {
				// 表示是否能返回,不用比较完所有的标签,能返回的标志就是把上一个标签和
				// 将要显示的标签找到了就可以了
				boolean findPrevious = false, findNext = false;
				for (final MyLabel m : list) {
					if (m.contains(p)) {
						findNext = true;
						if (m.getIsSelected()) {
							findPrevious = true;
						} else {
							m.setSelected(true, b);
						}
					} else if (m.getIsSelected()) {
						findPrevious = true;
						m.setSelected(false, b);
					}
					if (findPrevious && findNext) {
						return;
					}
				}
			} else {
				MyLabel temp = null;
				for (final MyLabel m : list) {
					if (m.contains(p)) {
						temp = m;
					} else if (m.getIsSelected()) {
						m.setSelected(false, b);
					}
				}
				if (temp != null) {
					temp.setSelected(true, b);
				}
			}
		}

	}
	private class MyLabel extends JLabel implements Comparator<MyLabel>, MouseListener, MouseMotionListener {
		private final int year, month, day;
		private boolean isSelected;

		public MyLabel(final int year, final int month, final int day) {
			super("" + day, SwingConstants.CENTER);
			this.year = year;
			this.day = day;
			this.month = month;
			this.addMouseListener(this);
			this.addMouseMotionListener(this);
			this.setFont(font);
			if (month == select.get(Calendar.MONTH)) {
				this.setForeground(Color.BLACK);
			} else {
				this.setForeground(Color.LIGHT_GRAY);
			}
			if (day == select.get(Calendar.DAY_OF_MONTH)) {
				this.setBackground(new Color(160, 185, 215));
			} else {
				this.setBackground(Color.WHITE);
			}
		}

		@Override
		public int compare(final MyLabel o1, final MyLabel o2) {
			final Calendar c1 = Calendar.getInstance();
			c1.set(o1.year, o2.month, o1.day);
			final Calendar c2 = Calendar.getInstance();
			c2.set(o2.year, o2.month, o2.day);
			return c1.compareTo(c2);
		}

		@Override
		public boolean contains(final Point p) {
			return this.getBounds().contains(p);
		}

		public boolean getIsSelected() {
			return isSelected;
		}

		@Override
		public void mouseClicked(final MouseEvent e) {
		}

		@Override
		public void mouseDragged(final MouseEvent e) {
			final Point p = SwingUtilities.convertPoint(this, e.getPoint(), jp3);
			lm.setSelect(p, true);
		}

		@Override
		public void mouseEntered(final MouseEvent e) {
		}

		@Override
		public void mouseExited(final MouseEvent e) {
		}

		@Override
		public void mouseMoved(final MouseEvent e) {
		}

		@Override
		public void mousePressed(final MouseEvent e) {
			isSelected = true;
			update();
		}

		@Override
		public void mouseReleased(final MouseEvent e) {
			final Point p = SwingUtilities.convertPoint(this, e.getPoint(), jp3);
			lm.setSelect(p, false);
			commit(select);
		}

		@Override
		protected void paintComponent(final Graphics g) {
			if (day == select.get(Calendar.DAY_OF_MONTH) && month == select.get(Calendar.MONTH)) {
				// 如果当前日期是选择日期,则高亮显示
				g.setColor(new Color(160, 185, 215));
				g.fillRect(0, 0, getWidth(), getHeight());
			}
			if (year == now.get(Calendar.YEAR) && month == now.get(Calendar.MONTH)
					&& day == now.get(Calendar.DAY_OF_MONTH)) {
				// 如果日期和当前日期一样,则用红框
				final Graphics2D gd = (Graphics2D) g;
				gd.setColor(Color.RED);
				final Polygon p = new Polygon();
				p.addPoint(0, 0);
				p.addPoint(getWidth() - 1, 0);
				p.addPoint(getWidth() - 1, getHeight() - 1);
				p.addPoint(0, getHeight() - 1);
				gd.drawPolygon(p);
			}
			if (isSelected) {// 如果被选中了就画出一个虚线框出来
				final Stroke s = new BasicStroke(1.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL, 1.0f,
						new float[] { 2.0f, 2.0f }, 1.0f);
				final Graphics2D gd = (Graphics2D) g;
				gd.setStroke(s);
				gd.setColor(Color.BLACK);
				final Polygon p = new Polygon();
				p.addPoint(0, 0);
				p.addPoint(getWidth() - 1, 0);
				p.addPoint(getWidth() - 1, getHeight() - 1);
				p.addPoint(0, getHeight() - 1);
				gd.drawPolygon(p);
			}
			super.paintComponent(g);
		}

		public void setSelected(final boolean b, final boolean isDrag) {
			isSelected = b;
			if (b && !isDrag) {
				final int temp = select.get(Calendar.MONTH);
				select.set(year, month, day);
				if (temp == month) {
					SwingUtilities.updateComponentTreeUI(jp3);
				} else {
					refresh();
				}
			}
			this.repaint();
		}

		private void update() {
			repaint();
		}
	}
	public static void main(final String[] args) {
		final DateChooserFinal mp = new DateChooserFinal();
		myEvent(mp);
	}
	private static void myEvent(final DateChooserFinal dt) {
		final JFrame jf = new JFrame("央视TXT生成工具");

		jf.add(dt, BorderLayout.CENTER);
		jf.pack();
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	private final Date initDate;
	private final Calendar now = Calendar.getInstance();
	public Calendar select;
	private JPanel monthPanel;// 月历

	private JP1 jp1;

	private JP3 jp3;

	private final Font font = new Font("宋体", Font.PLAIN, 12);

	private final LabelManager lm = new LabelManager();

	private JLabel showDate;// ,toSelect;

	private final SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd EEEE");

	private boolean isShow = false;

	private Popup pop;


	/**
	 * Creates a new instance of DateChooser
	 */
	public DateChooserFinal() {
		this(new Date());
	}

	public DateChooserFinal(final Date date) {
		initDate = date;
		select = Calendar.getInstance();
		select.setTime(initDate);
		initPanel();
		initLabel();
	}

	
	private Map<String,Boolean> cbStates = null;
	
	void commit(final Calendar c) {

		Map<String,Boolean> cbStates = getCheckBoxState();
		
		showDate.setText(sdf.format(c.getTime()));

		final Object[] options = { "OK", "重选" };
		
		final int response = JOptionPane.showOptionDialog(null, "起始日期为: " + sdf.format(c.getTime()), "消息提示",
				JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		if (response == 0) {
			setStates(cbStates);
			executeEPG(c,cbStates);
		} else {
			return;
		}
		
	}


	private void setStates(Map<String, Boolean> cbStates) {
		this.cbStates = cbStates;
	}

	private void executeEPG(Calendar c, Map<String, Boolean> cbStates) {
		
		DownloadManager dm = new DownloadManager(c,cbStates);
		dm.execute();
		StringBuilder sb = new StringBuilder("做");
		Set<String> state = cbStates.keySet();
		for(String e : state) {
			if (cbStates.get(e) == true) {
				sb.append(e+" ||");
			}
		}
		String time = sdf.format(c.getTime());
		System.out.println(time + sb.toString());
	}

	private Map<String,Boolean> getCheckBoxState() {
		int size = checkBoxes.size();
		Map<String,Boolean> states = new HashMap<String,Boolean>();
		for (int i = 0; i < size; i++) {
			String cbName = checkBoxes.get(i).getLabel();
			Boolean state = checkBoxes.get(i).getState();
			states.put(cbName,state); 
		}
		return states;
	}

	/**
	 * 得到当前选择框的日期
	 */
	public Date getDate() {
		return select.getTime();
	}

	private void hidePanel() {
		if (pop != null) {
			isShow = false;
			pop.hide();
			pop = null;
		}
	}

	// 初始化标签
	private void initLabel() {
		showDate = new JLabel(sdf.format(initDate));
		showDate.setRequestFocusEnabled(true);
		showDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent me) {
				showDate.requestFocusInWindow();
			}
		});

		this.setBackground(Color.WHITE);
		this.add(showDate, BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(90, 25));
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		showDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(final MouseEvent me) {
				if (showDate.isEnabled()) {
					showDate.setCursor(new Cursor(Cursor.HAND_CURSOR));
					showDate.setForeground(Color.RED);
				}
			}

			@Override
			public void mouseExited(final MouseEvent me) {
				if (showDate.isEnabled()) {
					showDate.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					showDate.setForeground(Color.BLACK);
				}
			}

			@Override
			public void mousePressed(final MouseEvent me) {
				if (showDate.isEnabled()) {
					showDate.setForeground(Color.CYAN);
					if (isShow) {
						hidePanel();
					} else {
						showPanel(showDate);
					}
				}
			}

			@Override
			public void mouseReleased(final MouseEvent me) {
				if (showDate.isEnabled()) {
					showDate.setForeground(Color.BLACK);
				}
			}
		});
		showDate.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(final FocusEvent e) {

			}

			@Override
			public void focusLost(final FocusEvent e) {
				hidePanel();
			}
		});
	}

	// 根据初始化的日期,初始化面板
	private void initPanel() {
		monthPanel = new JPanel(new BorderLayout());
		monthPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		final JPanel up = new JPanel(new BorderLayout());
		up.add(jp1 = new JP1(), BorderLayout.NORTH);
		up.add(new JP2(), BorderLayout.CENTER);
		monthPanel.add(jp3 = new JP3(), BorderLayout.CENTER);
		monthPanel.add(up, BorderLayout.NORTH);
		monthPanel.add(new JP4(), BorderLayout.SOUTH);
		monthPanel.add(new EPG(),BorderLayout.EAST);

		this.addAncestorListener(new AncestorListener() {
			@Override
			public void ancestorAdded(final AncestorEvent event) {
			}

			// 只要祖先组件一移动,马上就让popup消失
			@Override
			public void ancestorMoved(final AncestorEvent event) {
				hidePanel();
			}

			@Override
			public void ancestorRemoved(final AncestorEvent event) {
			}
		});
	}

	// 根据新的日期刷新
	void refresh() {
		jp1.updateDate();
		jp3.updateDate();
		SwingUtilities.updateComponentTreeUI(this);
	}

	@Override
	public void setEnabled(final boolean b) {
		super.setEnabled(b);
		showDate.setEnabled(b);
	}

	private void showPanel(final Component owner) {
		if (pop != null) {
			pop.hide();
		}
		final Point show = new Point(0, showDate.getHeight());
		SwingUtilities.convertPointToScreen(show, showDate);
		final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		int x = show.x;
		int y = show.y;
		if (x < 0) {
			x = 0;
		}
		if (x > size.width - 295) {
			x = size.width - 295;
		}
		if (y < size.height - 170) {
		} else {
			y -= 188;
		}
		pop = PopupFactory.getSharedInstance().getPopup(owner, monthPanel, x, y);
		pop.show();
		isShow = true;
	}



}

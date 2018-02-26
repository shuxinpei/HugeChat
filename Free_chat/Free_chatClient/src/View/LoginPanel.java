package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.qq.client.model.QqClientConServer;


import Common.User;

	public class LoginPanel extends JFrame{

		private JFrame frame;

		// 全局的位置变量，用于表示鼠标在窗口上的位置
		static Point origin = new Point();
		private JTextField txtName;
		private JPasswordField txtPass;
		private JLabel user;
		private JLabel redExit;
		private JLabel greenOk;
		private JLabel login;
		private JLabel login2;
		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						LoginPanel window = new LoginPanel();
						window.frame.setVisible(true);
						JDialog jdlg = new JDialog(window, "son", true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

		/**
		 * Create the application.
		 */
		public LoginPanel() {
			initialize();
		}


		/**
		 * Initialize the contents of the frame.
		 */


		private void initialize() {
			frame = new JFrame();
			frame.getContentPane().setBackground(new Color(255, 255, 255));
			frame.getContentPane().setForeground(UIManager.getColor("Button.background"));
			frame.setSize(350, 500);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
			frame.setUndecorated(true);
			Dimension size = Toolkit.getDefaultToolkit().getScreenSize();//取屏幕大小
			setLocation((size.width - getWidth()) / 2, (size.height - getHeight()) / 2);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			frame.getContentPane().setLayout(null);

			txtName = new JTextField();
			txtName.setHorizontalAlignment(SwingConstants.CENTER);
			txtName.setFont(new Font("微软雅黑 Light", Font.PLAIN, 17));
			txtName.setBounds(95, 270, 160, 35);
			txtName.setText("用户名");
			frame.getContentPane().add(txtName);
			txtName.setColumns(10);

			txtPass = new JPasswordField();
			txtPass.setEchoChar('*');
			txtPass.setHorizontalAlignment(SwingConstants.CENTER);
			txtPass.setText("密码");
			txtPass.setBounds(95, 320, 160, 35);
			frame.getContentPane().add(txtPass);

			JLabel exit = new JLabel(" ");
			exit.setBounds(310, 0, 40, 30);
			exit.setIcon(new ImageIcon("src/LoginImage/xx.png"));
			frame.getContentPane().add(exit);
			exit.setVisible(false);

			JLabel HugeChat = new JLabel(" ");
			HugeChat.setBounds(0, 0, 105, 35);
			HugeChat.setIcon(new ImageIcon("src/LoginImage/title.png"));
			frame.getContentPane().add(HugeChat);

			user = new JLabel("  ");
			user.setBounds(116, 120, 126, 124);
			user.setIcon(new ImageIcon("src/LoginImage/man.png"));
			frame.getContentPane().add(user);

			JLabel yes = new JLabel(" ");
			yes.setBounds(147, 377, 56, 44);
			yes.setIcon(new ImageIcon("src/LoginImage/ok.png"));
			frame.getContentPane().add(yes);

			redExit = new JLabel(" ");
			redExit.setBounds(310, 0, 40, 30);
			redExit.setIcon(new ImageIcon("src/LoginImage/x.png"));
			frame.getContentPane().add(redExit);

			greenOk = new JLabel(" ");
			greenOk.setBounds(120, 376, 134, 40);
			greenOk.setIcon(new ImageIcon("src/LoginImage/login.png"));
			frame.getContentPane().add(greenOk);
			greenOk.setVisible(false);

			login = new JLabel("注册");
			login.setForeground(Color.GRAY);
			login.setFont(new Font("微软雅黑", Font.PLAIN, 18));
			login.setHorizontalAlignment(SwingConstants.CENTER);
			login.setBounds(264, 465, 72, 18);
			frame.getContentPane().add(login);

			login2 = new JLabel("注册");
			login2.setForeground(new Color(192, 192, 192));
			login2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
			login2.setHorizontalAlignment(SwingConstants.CENTER);
			login2.setBounds(264, 465, 72, 18);
			frame.getContentPane().add(login2);
			login2.setVisible(false);

			JLabel user2 = new JLabel("  ");
			user2.setBounds(116, 120, 126, 124);
			user2.setIcon(new ImageIcon("src/LoginImage/manT.png"));
			frame.getContentPane().add(user2);

			frame.addMouseListener(new MouseAdapter() {
				// 按下（mousePressed 不是点击，而是鼠标被按下没有抬起）
				public void mousePressed(MouseEvent e) {
					// 当鼠标按下的时候获得窗口当前的位置
					origin.x = e.getX();
					origin.y = e.getY();

					if(e.getX()>138&&e.getX()<222&&e.getY()>375&&e.getY()<415){//当点击到了我们登录按钮
					//这边进行登录验证
						QqClientConServer qqClientUser=new QqClientConServer();
						User u=new User();
						u.setUsername(txtName.getText().toString().trim());
						System.out.println(txtName.getText().toString().trim());
						u.setPasswd(new String(txtPass.getPassword()));
						System.out.println(new String(txtPass.getPassword()));
						u.setIfregiste(false);
						int login =qqClientUser.sendLoginInfoToServer(u);
						if(login!=0){
							ChatPanel f2 = new ChatPanel();
							f2.setUserId(login);//将当前用户的id传入
							frame.setVisible(false);
						}
					}

					if(e.getX()>260&&e.getY()>460){//这边是我们的注册按钮，这边的效果还是需要改变一下
						login.setVisible(false);
						login2.setVisible(true);
						QqClientConServer qqClientUser=new QqClientConServer();
						User u=new User();
						u.setUsername(txtName.getText().toString().trim());
						System.out.println(txtName.getText().toString().trim());
						u.setPasswd(new String(txtPass.getPassword()));
						System.out.println(new String(txtPass.getPassword()));
						u.setIfregiste(true);
						int login =qqClientUser.sendLoginInfoToServer(u);//login如果成功返回的就是一个ID
						if(login!=0){//注册成功就进去了
							ChatPanel f2 = new ChatPanel();
							f2.setUserId(login);//将当前用户的id传入
							frame.setVisible(false);
						}
						repaint();
					}else{
						login.setVisible(true);
						login2.setVisible(false);
					}

					if(e.getX()>320&&e.getY()<30){
						System.exit(0);
					}
				}
			});

			frame.addMouseListener(new MouseAdapter() {
				// 按下（mousePressed 不是点击，而是鼠标被按下没有抬起）
				public void mouseClicked(MouseEvent e) {
					// 当鼠标按下的时候获得窗口当前的位置
					origin.x = e.getX();
					origin.y = e.getY();

					if(e.getX()>116&&e.getX()<242&&e.getY()>126&&e.getY()<250){
						user.setVisible(false);
						user2.setVisible(true);
						repaint();
					}else{
						user.setVisible(true);
						user2.setVisible(false);
					}
				}
			});


			frame.addMouseMotionListener(new MouseMotionAdapter() {
				// 拖动（mouseDragged 指的不是鼠标在窗口中移动，而是用鼠标拖动）
				public void mouseDragged(MouseEvent e) {
					// 当鼠标拖动时获取窗口当前位置
					Point p = frame.getLocation();
					// 设置窗口的位置
					// 窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
					frame.setLocation(p.x + e.getX() - origin.x, p.y + e.getY()- origin.y);

				}
				public void mouseMoved(MouseEvent e) {
					if(e.getX()>320&&e.getY()<30){
						redExit.setVisible(false);
						exit.setVisible(true);
						repaint();
					}else{
						redExit.setVisible(true);
						exit.setVisible(false);
					}

					if(e.getX()>138&&e.getX()<222&&e.getY()>375&&e.getY()<415){
						yes.setVisible(false);
						greenOk.setVisible(true);
						repaint();
					}else{
						yes.setVisible(true);
						greenOk.setVisible(false);
					}

					if(e.getX()>116&&e.getX()<242&&e.getY()>126&&e.getY()<250){
						user.setVisible(false);
						user2.setVisible(true);
						repaint();

					}else{
						user.setVisible(true);
						user2.setVisible(false);
					}
				}

			});
		}
	}



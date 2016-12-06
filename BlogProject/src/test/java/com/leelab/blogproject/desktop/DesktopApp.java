package com.leelab.blogproject.desktop;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DesktopApp extends JFrame {

	private static final long serialVersionUID = 1L;

	private NetworkAccessor accessor = new NetworkAccessor();
	
	public DesktopApp() {
		
		this.setTitle("Blog PC버전");
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		final ImageIcon io = new ImageIcon(accessor.getProfileImage("admin"));
		JPanel p = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				g.drawImage(io.getImage(), 0, 0, null);
				super.paintComponent(g);
			}
		};

		//this.add(p, BorderLayout.CENTER);
		this.add(new JButton("버튼"), BorderLayout.SOUTH);
		this.setContentPane(p);

		this.setVisible(true);
	}
	
}

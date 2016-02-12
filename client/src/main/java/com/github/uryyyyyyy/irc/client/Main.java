package com.github.uryyyyyyy.irc.client;

import com.sorcix.sirc.*;

import java.io.IOException;
import java.nio.charset.Charset;

public class Main extends IrcAdaptor{

	public static final String host = "your_team.irc.slack.com";
	public static final int port = 6667;
	public static final String password = "your_team.XXX";
	public static final String userName = "your_name";

	IrcConnection irc;

	Main(){
		irc = new IrcConnection(host, port, password);
		irc.setCharset(Charset.forName("UTF-8"));
		irc.setUsingSSL(true);
		irc.setUsername(userName);
		irc.setNick(userName);
		irc.addServerListener(this);
		irc.addMessageListener(this);
	}

	public static void main(String[] args) throws PasswordException, NickNameException, IOException, InterruptedException {
		Main main = new Main();
		main.irc.connect();
		boolean b = main.irc.isConnected();
		System.out.println(b);
		Channel channel = main.irc.createChannel("#general");
		Thread.sleep(100);
		channel.sendMessage("message from sIRC");

		// add comment on slack in this sleep time,
		// it will invoke onMessage method below.
		Thread.sleep(10000);
		main.irc.disconnect();
	}

	@Override
	public void onMessage(IrcConnection irc, User sender, Channel target, String message){
		System.out.println(sender.getNick() + ": " + message + "| from slack");
	}
}

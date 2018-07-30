package com.uganda.pru.payments.comm;

import java.io.File;
import java.io.FileInputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * The Class FTPManager.
 */
public class FTPManager {

	public static void send(File file) {        
	    /*Below we have declared and defined the SFTP HOST, PORT, USER
        and Local private key from where you will make connection */
        String SFTPHOST = "41.60.129.96";
        int    SFTPPORT = 1122;
        String SFTPUSER = "easypay";
        String SFTPPASS = "345yp@yZ4mb!@";

        JSch jSch = new JSch();
        Session     session     = null;
        Channel     channel     = null;
        ChannelSftp channelSftp = null;
        try {
            session = jSch.getSession(SFTPUSER,SFTPHOST,SFTPPORT);
            session.setPassword(SFTPPASS);
            System.out.println("session created.");

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            channel = session.openChannel("sftp");
            channel.connect();
            System.out.println("shell channel connected....");
            channelSftp = (ChannelSftp)channel;
            channelSftp.put(new FileInputStream(file), file.getName());
        } catch (JSchException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            if(channelSftp!=null){
                channelSftp.disconnect();
                channelSftp.exit();
            }
            if(channel!=null) channel.disconnect();

            if(session!=null) session.disconnect();
        }
    }	
}

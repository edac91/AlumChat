/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umg.cliclientxmpp;

import java.util.Scanner;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.chat2.IncomingChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.iqregister.AccountManager;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.Jid;
import org.jxmpp.jid.impl.JidCreate;

/**
 *
 * @author edvin
 */
public class ConsoleApp {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        try {
            XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                    //.setUsernameAndPassword("edac91", "edac91")
                    .setXmppDomain("alumchat.xyz")
                    .setHost("alumchat.xyz")
                    .setSecurityMode(SecurityMode.disabled) // Do not disable TLS except for test purposes!
                    .build();

            AbstractXMPPConnection connection = new XMPPTCPConnection(config);
            connection.connect()/*.login()*/;
            System.out.println("Has iniciado sesion!!!!");

            /*Presence presence = new Presence(Presence.Type.available);
            presence.setStatus("Redes");
            connection.sendStanza(presence);

            ChatManager chatManager = ChatManager.getInstanceFor(connection);
            chatManager.addIncomingListener((EntityBareJid from, Message message, Chat chat) -> {
                System.out.println("New message from " + from + ": " + message.getBody());
            });
            Chat chat = chatManager.chatWith(JidCreate.entityBareFrom("doac91@alumchat.xyz")); // pass XmppClient instance as listener for received messages.
            chat.send("Hola mundo");*/
            
            
            scan.nextLine();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}

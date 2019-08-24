/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umg.cliclientxmpp;

import java.util.Scanner;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.SASLAuthentication;
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
        try {
            XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                    .setXmppDomain("dariolap")
                    .setHost("dariolap")
                    .setSecurityMode(SecurityMode.disabled) // Do not disable TLS except for test purposes!
                    .build();

            SASLAuthentication.blacklistSASLMechanism("SCRAM-SHA-1");
            SASLAuthentication.blacklistSASLMechanism("DIGEST-MD5");
            SASLAuthentication.unBlacklistSASLMechanism("PLAIN");

            AbstractXMPPConnection connection = new XMPPTCPConnection(config);
            connection.connect();

            XMPPClient client = new XMPPClient(connection);
            menu(client);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private static void menu(XMPPClient client) {
        Scanner scan = new Scanner(System.in);
        int principalOption;

        System.out.println("*************************************************");
        System.out.println("Opciones");
        System.out.println("0. Iniciar Sesion");
        System.out.println("1. Cerrar Sesion");
        System.out.println("6. Crear Nuevo Usuario");
        System.out.println("7. Eliminar Cuenta Usuario");
        System.out.println("100. Salir del sistema");
        System.out.println("*************************************************");
        System.out.println("Indique la opcion deseada");
        principalOption = Integer.parseInt(scan.nextLine());
        switch (principalOption) {
            case 0:
                System.out.println("Usuario: ");
                String userJId = scan.nextLine();
                System.out.println("Contraseña: ");
                String password = scan.nextLine();
                client.login(userJId, password);
                break;
            case 1:
                System.out.println("Confirma que desea cerrar sesion Y/N");
                String cc = scan.nextLine();
                if (cc.equalsIgnoreCase("Y"))
                {
                    client.logout();
                }
                break;
            case 6:
                System.out.println("Por favor ingrese los datos solicitados");                
                System.out.println("Usuario: ");
                String userJIdna = scan.nextLine();
                System.out.println("Contraseña: ");
                String passwordna = scan.nextLine();
                client.createAccount(userJIdna, passwordna);
                break;
            case 7:
                System.out.println("Confirma que desea eliminar cuenta Y/N");
                String da = scan.nextLine();
                if (da.equalsIgnoreCase("Y"))
                {
                    client.deleteAccount();
                }
                break;
            case 100:
                System.exit(0);
            default:
                menu(client);
                break;
        }
        menu(client);

    }

}

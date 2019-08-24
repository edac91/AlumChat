/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umg.cliclientxmpp;

import javax.xml.bind.annotation.XmlID;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.iqregister.AccountManager;
import org.jxmpp.jid.impl.JidCreate;

/**
 *
 * @author edvin
 */
public class XMPPClient {

    AbstractXMPPConnection connection;

    public XMPPClient(AbstractXMPPConnection con) {
        this.connection = con;
    }

    public void createAccount(String userId, String password) {
        try {
            reconnect();
            AccountManager accountManager = AccountManager.getInstance(this.connection);
            if (accountManager.supportsAccountCreation()) {
                accountManager.sensitiveOperationOverInsecureConnection(true);
                accountManager.createAccount(JidCreate.bareFrom(userId).getLocalpartOrThrow(), password);
            }
            System.out.println("Cuenta creada exitosamente");
        } catch (Exception ex) {
            System.out.println(String.format("Ha ocurrido un error al crear el usuario: %s", userId));                       
        }

    }

    public void deleteAccount() {
        try {
            reconnect();
            if (this.connection.isAuthenticated()) {
                String authUser = this.connection.getUser().asBareJid().toString();
                AccountManager accountManager = AccountManager.getInstance(this.connection);
                accountManager.deleteAccount();
                System.out.println(String.format("Su cuenta '%s' ha sido eliminada exitosamente!!!", authUser));
            } else {
                System.out.println("Debe Iniciar Sesion para poder proceder");
            }

        } catch (Exception ex) {
            //System.out.println(String.format("Ha ocurrido un error al crear el usuario: %s", userId));            
            System.out.println("Ha ocurrido un error al eliminar su cuenta");
        }
    }

    public void login(String userId, String password) {
        try {
            reconnect();
            if (!this.connection.isAuthenticated()) {
                this.connection.login(userId, password);
                System.out.println(String.format("Ha iniciado sesion como %s", userId));
            } else if (this.connection.isAuthenticated() && this.connection.getUser().toString().equalsIgnoreCase(userId)) {
                System.out.println("Usted cuenta con una sesion activa");
            } else {
                System.out.println("Cierre sesion antes de continuar como otro usuario");
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
            System.out.println("Ha ocurrido un error al iniciar sesion");
        }
    }

    public void logout() {

        try {
            if (this.connection.isAuthenticated()) {
                this.connection.disconnect();
                System.out.println("Sesion cerrada exitosamente");
            } else
            {
                System.out.println("No hay sesion activa con el servidori");
            }
        } catch (Exception ex) {
            System.out.println("Ha ocurrido un error mientras se cerraba la sesion");
        }

    }

    private void reconnect() {
        try {
            if (!this.connection.isConnected()) {
                this.connection.connect();
            }
        } catch (Exception ex) {
            System.out.println("Ha ocurrido un error al reconectarse");
        }
    }
}

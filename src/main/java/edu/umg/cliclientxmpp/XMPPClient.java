/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umg.cliclientxmpp;

import javax.xml.bind.annotation.XmlID;
import org.jivesoftware.smack.AbstractXMPPConnection;
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

    private boolean createAccount(String userId, String password) {
        try {
            AccountManager accountManager = AccountManager.getInstance(this.connection);
            if (accountManager.supportsAccountCreation()) {
                accountManager.sensitiveOperationOverInsecureConnection(true);
                accountManager.createAccount(JidCreate.bareFrom(userId).getLocalpartOrThrow(), password);
            }
            return true;
        } catch (Exception ex) {
            //System.out.println(String.format("Ha ocurrido un error al crear el usuario: %s", userId));            
            return false;
        }

    }

}

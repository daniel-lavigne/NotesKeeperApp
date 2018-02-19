/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nk.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import nk.dataaccess.DBUtil;

/**
 * Web application lifecycle listener.
 *
 * @author 664978
 */
public class EMFListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       DBUtil.closeEmFactory();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merlobranco.ejb;

import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author henry
 */
@Stateless
public class TestTwoFacade implements TestTwoRemote, Serializable {

    @Override
    public String method() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}

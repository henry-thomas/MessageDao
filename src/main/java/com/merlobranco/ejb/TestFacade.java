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
 * @author brais
 */
@Stateless
public class TestFacade implements TestRemote, Serializable {

    private static final long serialVersionUID = 1L;
    
    private static Integer cont = 1;

    @Override
    public String method() {
        return "Remote EJB called successfully!!!";
    }

    @Override
    public Integer next() {
        return ++cont;
    }
}

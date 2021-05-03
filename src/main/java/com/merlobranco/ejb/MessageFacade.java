/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merlobranco.ejb;

import com.merlobranco.entity.Message;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;

/**
 *
 * @author brais
 */
@Stateless
public class MessageFacade extends AbstractFacade<Message> implements MessageRemote {

    private static final long serialVersionUID = 1L;

    public MessageFacade() {
        super(Message.class);
    }

    @Override
    public List<Message> findOrdered() {
        try {
            List<Message> list = getEntityManager().createNamedQuery("Message.findAllOrdered", Message.class).getResultList();
            return list;
        } catch (PersistenceException e) {
            return new ArrayList<>();
        }
    }
}

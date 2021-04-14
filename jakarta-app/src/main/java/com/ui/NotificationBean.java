package com.ui;

import com.facade.EmailFacade;
import com.facade.RewardFacade;
import com.model.Email;
import com.model.Reward;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class NotificationBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private EmailFacade emailFacade;

    public List<Email> getAll() {
        return emailFacade.getAll();
    }

    public void delete(Email email) {
        emailFacade.delete(email.getId());
    }
}

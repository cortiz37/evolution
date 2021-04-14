package com.ui;

import com.facade.RewardFacade;
import com.model.Reward;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class RewardBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private RewardFacade rewardFacade;

    public List<Reward> getAll() {
        return rewardFacade.getAll();
    }

    public void delete(Reward reward) {
        rewardFacade.delete(reward.getId());
    }
}

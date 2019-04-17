package com.example.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.spacetrader.entity.Player;
import com.example.spacetrader.model.Model;
import com.example.spacetrader.model.PlayerInteractor;

/**
 * View model supporting adding and updating an individual player
 */
public class CreatePlayerViewModel  extends AndroidViewModel {
    private final PlayerInteractor interactor = Model.getInstance().getPlayerInteractor();

    /**
     * Creates view model for player
     *
     * @param application current application
     */
    public CreatePlayerViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     *
     * @param player player to add to interactor
     */
    public void addPlayer(Player player) {
        interactor.newPlayer(player);
    }
}

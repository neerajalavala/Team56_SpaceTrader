package com.example.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.spacetrader.entity.Player;
import com.example.spacetrader.entity.Universe;
import com.example.spacetrader.model.Model;
import com.example.spacetrader.model.PlayerInteractor;

/**
 * View model supporting viewing a list of players either for a single course or
 * every player in the application.
 */
public class GetPlayerViewModel extends AndroidViewModel {

    private PlayerInteractor interactor = Model.getInstance().getPlayerInteractor();

    /**
     * Gets player view model
     *
     * @param application current application
     */
    public GetPlayerViewModel(@NonNull Application application) {
        super(application);
    }


    /**
     *
     * @return player to get from interactor
     */
    public Player getPlayer() {
        return interactor.getPlayer();
    }

    /**
     *
     * @return universe to get from interactor
     */
    public Universe getPlayerGame() {
        return interactor.getPlayerGame();
    }
}

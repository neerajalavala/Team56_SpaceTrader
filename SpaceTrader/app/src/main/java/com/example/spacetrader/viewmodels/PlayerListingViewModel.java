package com.example.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import com.example.spacetrader.entity.Player;
import com.example.spacetrader.model.Model;
import com.example.spacetrader.model.PlayerInteractor;

/**
 * View model supporting viewing a list of players either for a single course or
 * every player in the application.
 */
public class PlayerListingViewModel extends AndroidViewModel {

    private PlayerInteractor interactor;

    public PlayerListingViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getPlayerInteractor();
    }


    public List<Player> getPlayers() {
        return interactor.getAllPlayers();
    }

}

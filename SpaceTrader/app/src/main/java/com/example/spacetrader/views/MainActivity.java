package com.example.spacetrader.views;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.persistence.FirebaseInteractor;
import com.example.spacetrader.viewmodels.CreatePlayerViewModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;

/**
 * This is the startup activity.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button view_players = findViewById(R.id.view_players_button);
        final CreatePlayerViewModel viewModel =
                ViewModelProviders.of(this).get(CreatePlayerViewModel.class);
        final Context c = this;
//        FirebaseApp.initializeApp(this);
        view_players.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                try {
//                    FileInputStream fis = c.openFileInput("SpaceTrader.ser");
//                    ObjectInputStream is = new ObjectInputStream(fis);
//                    Player player = (Player) is.readObject();
//                    viewModel.addPlayer(player);
//                    is.close();
//                    fis.close();
//                } catch (IOException e) {e.printStackTrace();}
//                catch (ClassNotFoundException e) {e.printStackTrace();}
                FirebaseInteractor firebaseInteractor = new FirebaseInteractor("SpaceTrader.ser");
                firebaseInteractor.download().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Player player = documentSnapshot.toObject(Player.class);
                        viewModel.addPlayer(player);
                        Intent intent = new Intent(MainActivity.this,
                                ViewAllPlayersActivity.class);
                        startActivity(intent);
                    }
                });
//                Intent intent = new Intent(MainActivity.this,
//                        ViewAllPlayersActivity.class);
//                startActivity(intent);
            }
        });

        Button add_player = findViewById(R.id.add_player_button);
        add_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                        CreatePlayerActivity.class);
                startActivity(intent);
            }
        });
    }

}

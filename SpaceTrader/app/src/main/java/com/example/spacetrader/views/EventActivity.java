package com.example.spacetrader.views;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.MarketGood;
import com.example.spacetrader.entity.MarketGoodType;
import com.example.spacetrader.entity.MarketPlace;
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.entity.RandomEvent;
import com.example.spacetrader.entity.Resources;
import com.example.spacetrader.entity.ShipType;
import com.example.spacetrader.entity.TechLevel;
import com.example.spacetrader.entity.Universe;
import com.example.spacetrader.viewmodels.GetPlayerViewModel;

import android.app.ProgressDialog;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class EventActivity extends AppCompatActivity {

    private GetPlayerViewModel viewModel;

    private Player player;
    private Universe game;

    private ShipType[] ships = ShipType.values();
    private ShipType e_type;

    private TextView interaction_title;

    private ImageView p_ship;
    private ImageView e_ship;

    private TextView p_hlth;
    private TextView p_shld;
    private TextView e_hlth;
    private TextView e_shld;

    private Button flee;
    private Button ignore;
    private Button attack;
    private Button trade;
    private Button bribe;
    private Button inspect;
    private Button give_up;

    private RandomEvent type;

    private Integer e_shld_val;

    private Integer eh_val;
    private Integer eh_max;

    private Integer shield_val;

    private Integer ph;
    private Integer pmh;

    private Integer p_atk;

    private boolean buying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travel_interaction);

        this.viewModel = ViewModelProviders.of(this).get(GetPlayerViewModel.class);

        this.player = viewModel.getPlayer();

        this.game = viewModel.getPlayerGame();

        Intent in = getIntent();

        this.type = (RandomEvent) in.getSerializableExtra("E_TYPE");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(false);
        }

        this.interaction_title = findViewById(R.id.inter_type);

        this.p_ship = findViewById(R.id.ply_ship);
        this.e_ship = findViewById(R.id.nme_ship);

        this.p_hlth = findViewById(R.id.h1_val);
        this.p_shld = findViewById(R.id.s1_val);

        this.e_hlth = findViewById(R.id.h2_val);
        this.e_shld = findViewById(R.id.s2_val);

        this.flee = findViewById(R.id.flee);
        this.ignore = findViewById(R.id.ignore);
        this.attack = findViewById(R.id.attack);
        this.trade = findViewById(R.id.trade);
        this.bribe = findViewById(R.id.bribe);
        this.inspect = findViewById(R.id.inspect);
        this.give_up = findViewById(R.id.give_up);

        if (type == RandomEvent.CREDITS) {
            boolean done = false;
            interaction_title.setText("Found credits");
            player.addCredits(50);
            AlertDialog.Builder builder = new AlertDialog.Builder(EventActivity.this);
            builder.setMessage("Found Credits")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            System.out.println("pressed button");
                            finish();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();

        } else if (type == RandomEvent.TRADER) {
            interaction_title.setText("Trader Ship");
            ignore.setVisibility(View.VISIBLE);
            attack.setVisibility(View.VISIBLE);
            trade.setVisibility(View.VISIBLE);


        } else if (type == RandomEvent.PIRATE) {
            interaction_title.setText("Pirates!");
            flee.setVisibility(View.VISIBLE);
            attack.setVisibility(View.VISIBLE);
            give_up.setVisibility(View.VISIBLE);

        } else if (type == RandomEvent.COPS) {
            interaction_title.setText("Cops!");
            flee.setVisibility(View.VISIBLE);
            attack.setVisibility(View.VISIBLE);
            bribe.setVisibility(View.VISIBLE);
            inspect.setVisibility(View.VISIBLE);
        }
        // picking enemy ship
        int num = new Random().nextInt(ships.length - 1);
        this.e_type = ships[num];
        try {
            // setting ship pictures
            p_ship.setImageResource(player.getShipType().getPic_loc());
            e_ship.setImageResource(e_type.getPic_loc());
        } catch (Exception f) {
        }

        // calculating enemy shield and health

        if (num > 3 && num < 6) {
            this.e_shld_val = 50;
        } else if (num > 5) {
            this.e_shld_val = 100;
        } else {
            this.e_shld_val = 0;
        }
        this.eh_val = e_type.getMax_health();
        this.eh_max = e_type.getMax_health();

        // calculating player shield

        if (player.getHas_shield()) {
            this.shield_val = 100;
        } else {
            this.shield_val = 0;
        }

        // getting player health and attack
        this.ph = player.get_health();
        this.pmh = player.getShipType().getMax_health();

        this.p_atk = player.getShipType().getBaseAttack();
        if (player.getHas_weapons()) {
            this.p_atk *= 2;
        }

        // setting shield and health values
        p_hlth.setText(ph.toString() + "/" + pmh.toString());
        p_shld.setText(shield_val.toString() + "/100");

        e_hlth.setText(eh_val.toString() + "/" + eh_max.toString());
        e_shld.setText(e_shld_val.toString() + "/100");


        flee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = new Random().nextInt(10);
                int bound;
                if (player.getShipType().getSpeed() > e_type.getSpeed()) {
                    bound = 4;
                } else {
                    bound = 7;
                }

                if (i > bound) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(EventActivity.this);
                    builder.setMessage("Fled fight")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    finish();

                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(EventActivity.this);
                    builder.setMessage("Failed to flee")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    //do things
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });

        attack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ignore.setVisibility(View.GONE);
                flee.setVisibility(View.VISIBLE);

                int e_atk = e_type.getBaseAttack();

                int patk = new Random().nextInt(3);
                int eatk = new Random().nextInt(3);

                boolean phit = false;
                boolean ehit = false;

                if (patk > 1) {
                    phit = true;
                }

                if (eatk > 1) {
                    ehit = true;
                }
                if (ehit) {
                    if (e_shld_val > 0 ) {
                        if (e_shld_val - p_atk < 0) {
                            eh_val = eh_val - (p_atk - e_shld_val);
                            e_shld_val = 0;

                        } else {
                            e_shld_val = e_shld_val - p_atk;
                        }

                    } else {
                        if (eh_val - p_atk <= 0) {
                            player.addCredits(e_type.getPrice()/3);
                            AlertDialog.Builder builder = new AlertDialog.Builder(EventActivity.this);
                            builder.setMessage("Ship destroyed, credits recieved, next turn")
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int id) {
                                            finish();
                                        }
                                    });
                            AlertDialog alert = builder.create();
                            alert.show();
                        } else {
                            eh_val = eh_val - p_atk;
                        }
                    }
                }
                if (phit) {
                    if (shield_val > 0 ) {
                        if (shield_val - e_atk < 0) {
                            ph = ph - (e_atk - shield_val);
                            shield_val = 0;

                        } else {
                            shield_val = shield_val - e_atk;
                        }

                    } else {
                        if (ph - e_atk <= 0) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(EventActivity.this);
                            builder.setMessage("your ship was destroyed, game over")
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int id) {
                                            finish();
                                        }
                                    });
                            AlertDialog alert = builder.create();
                            alert.show();
                        } else {
                            ph = ph - e_atk;
                        }
                    }
                    player.set_health(ph);

                }

                String s1;
                if (ehit) {
                    s1 = "hit";
                } else {
                    s1 = "miss";
                }
                String s2;
                if (phit) {
                    s2 = "hit";
                } else {
                    s2 = "miss";
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(EventActivity.this);
                builder.setMessage("Player Attack: " + s1 + "\nEnemy Attack: " + s2)
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

                // setting shield and health values
                p_hlth.setText(ph.toString() + "/" + pmh.toString());
                p_shld.setText(shield_val.toString() + "/100");

                e_hlth.setText(eh_val.toString() + "/" + eh_max.toString());
                e_shld.setText(e_shld_val.toString() + "/100");

            }


        });

        ignore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EventActivity.this);
                builder.setMessage("Ship ignored, next turn")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                finish();

                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        trade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventActivity.this, SpaceTrade.class);
                MarketPlace ship_hold = new MarketPlace(TechLevel.HITECH, Resources.NONE);
                intent.putExtra("MARKET", ship_hold);
                startActivity(intent);
                finish();
            }
        });

        give_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player.getCredits() > 200) {
                    player.subCredits(200);
                } else {
                    player.subCredits((player.getCredits() - 1));
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(EventActivity.this);
                builder.setMessage("Lost credits to ship, next turn")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        bribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = new Random().nextInt(4);
                if ( i > 2.5) {
                    if (player.getCredits() > 200) {
                        player.subCredits(200);
                    } else {
                        player.subCredits((player.getCredits() - 1));
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(EventActivity.this);
                    builder.setMessage("Bribe Successful, next turn")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    finish();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(EventActivity.this);
                    builder.setMessage("Bribe not successful")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    //do things
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                    bribe.setVisibility(View.GONE);
                }

            }
        });

        inspect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<MarketGood> goods = player.getCargoHold().getSellableGoods(TechLevel.HITECH);
                boolean illegal = false;
                for (MarketGood good: goods) {
                    if (good.getMarketGoodType() == MarketGoodType.FIREARMS ||
                            good.getMarketGoodType() == MarketGoodType.NARCOTICS) {
                        illegal = true;
                        break;
                    }
                }
                String msg = "no illegal goods found, next turn";
                if (illegal) {
                    msg = "illegal goods food, fined, next turn";
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(EventActivity.this);
                builder.setMessage(msg)
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();

    }

}

package com.andgopa.tictactoemvvm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.andgopa.tictactoemvvm.R;
import com.andgopa.tictactoemvvm.databinding.ActivityGameBinding;
import com.andgopa.tictactoemvvm.model.Player;
import com.andgopa.tictactoemvvm.viewmodel.GameViewModel;

public class GameActivity extends AppCompatActivity {

    private static final String GAME_BEGING_DIALOG_TAG = "game_dialog_tag";
    private static final String GAME_ENDING_DIALOG_TAG = "game_end_dialog_tag";
    private static final String NO_WINNER = "No one";
    private ActivityGameBinding activityGameBinding;
    private GameViewModel gameViewModel;
//    private GameViewModel.ClickHandlers clickHandlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        promptForPlayers();
    }

    private void promptForPlayers() {
        initDataBinding("Jose", "Marcos");
    }


    private void initDataBinding(String player1, String player2) {
        activityGameBinding = DataBindingUtil.setContentView(this, R.layout.activity_game);
        gameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);
        gameViewModel.init(player1, player2);
        activityGameBinding.setGameViewModel(gameViewModel);
        activityGameBinding.executePendingBindings();
        activityGameBinding.setLifecycleOwner(this);
//        init();
        setUpOnGameEndListener();
    }

//    private void init(){
//        clickHandlers = new GameViewModel.ClickHandlers(this);
//        activityGameBinding.setGameClickHandler(clickHandlers);
//    }
//
////    public class ClickHandlers {
//
//        Context context;
//
//        public ClickHandlers(Context context) {
//            this.context = context;
//        }
//
//        public void onClick(int row, int column) {
//
//        }
//    }

    private void setUpOnGameEndListener() {
        gameViewModel.getWinner().observe(this, this::onGameWinnerChanged);
    }

    private void onGameWinnerChanged(Player winner) {
        String winnerName = winner == null || winner.name != null ? NO_WINNER : winner.name;
    }
}

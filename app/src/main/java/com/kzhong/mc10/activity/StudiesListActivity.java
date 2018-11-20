package com.kzhong.mc10.activity;

import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kzhong.mc10.R;
import com.kzhong.mc10.adapter.StudiesListAdapter;
import com.kzhong.mc10.intefaces.StudiesListView;
import com.kzhong.mc10.model.Studies;

import java.util.ArrayList;

public class StudiesListActivity extends AppCompatActivity implements StudiesListView {

    private StudiesListPresenter presenter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studies_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.rvStudies);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());

        recyclerView.addItemDecoration(divider);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String accountId = intent.getStringExtra("accountId");
        String accessToken = intent.getStringExtra("accessToken");

        presenter = new StudiesListPresenter(this, id, accountId, accessToken);

        checkForBluetooth();
    }

    private void checkForBluetooth() {

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            presenter.logOut();

        } else {
            if (!mBluetoothAdapter.isEnabled()) {
                // Bluetooth is not enabled
                presenter.logOut();
            } else {
                presenter.getStudiesList();
            }
        }
    }

    @Override
    public void onResponseSuccess(ArrayList<Studies> studiesList) {

        StudiesListAdapter adapter = new StudiesListAdapter(studiesList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResponseFailure(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("StudiesList Error");
        builder.setMessage(error);
        builder.setPositiveButton("Ok", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onLogOut(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Bluetooth Disabled");
        builder.setMessage(message);
        builder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}

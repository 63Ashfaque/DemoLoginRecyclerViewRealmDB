package com.ashfaque.demologinrecyclerview.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;


import com.ashfaque.demologinrecyclerview.adapter.ItemAdapter;
import com.ashfaque.demologinrecyclerview.model.Person;
import com.ashfaque.demologinrecyclerview.Utils;
import com.ashfaque.demologinrecyclerview.databinding.ActivityDashboardBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class DashboardActivity extends AppCompatActivity {

   ActivityDashboardBinding mBinding;
    Realm realm;
    List<Person> PersonList;
    ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding=ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.fabFilter.setOnClickListener(v->showSortOptionsDialog());

        // Initialize Realm
        realm = Realm.getDefaultInstance();

        // Set up the adapter
        itemAdapter = new ItemAdapter(PersonList);
        mBinding.recyclerView.setAdapter(itemAdapter);

        Utils.showToast(this,"Please Wait...");

        List<Person> dataModels = realm.where(Person.class).findAll();
        if (dataModels.isEmpty()) {
            Log.d("Ashu", "The Person table is empty.");
            firebaseInsert();
        } else {
            Log.d("Ashu", "Number of records in Person table: " + dataModels.size());
            showData();
        }

    }

    private void firebaseInsert() {

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    Person person = new Person();

                    Log.d("Ashu",userSnapshot.getKey()+""+userSnapshot.getValue());


                    person.setId(Integer.parseInt(userSnapshot.child("id").getValue().toString()));
                    person.setName(userSnapshot.child("name").getValue().toString());
                    person.setCity(userSnapshot.child("city").getValue().toString());
                    person.setAge(Integer.parseInt(userSnapshot.child("age").getValue().toString()));

                    //insert into realm database
                    realm.executeTransaction(realm -> realm.copyToRealm(person));

                }

                showData();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w("Ashu", "Failed to read value.", error.toException());
            }
        });
    }


    private void showSortOptionsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sort Options");

        // Options to display in the dialog
        String[] sortOptions = {"Sort By Name", "Sort By Age", "Sort By City"};

        builder.setItems(sortOptions, (dialog, which) -> {
            switch (which) {
                case 0:
                    // Handle "Sort By Name"
                    sortBy("name");
                    break;
                case 1:
                    // Handle "Sort By Age"
                    sortBy("age");
                    break;
                case 2:
                    // Handle "Sort By City"
                    sortBy("city");
                    break;
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void sortBy(String value) {
        // Implement sorting by name
        Utils.showToast(DashboardActivity.this,"Sort By "+value);
        // Query Realm for objects sorted by name
        RealmResults<Person> sortedItems = realm.where(Person.class)
                .findAll()
                .sort(value, Sort.ASCENDING);

        itemAdapter.updateList(sortedItems);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (realm != null) {
            realm.close();
        }
    }



    private void showData(){
        List<Person> dataModels=realm.where(Person.class).findAll();

        itemAdapter.updateList(dataModels);
    }

}
package com.ravan.repository;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ravan.mapper.FirebaseMapper;

import java.util.HashMap;
import java.util.List;

public abstract class FirebaseDatabaseRepository<Model> {

    protected DatabaseReference databaseReference;
    protected FirebaseDatabaseRepositoryCallback<Model> firebaseCallback;
    private BaseValueEventListener listener;
    private FirebaseMapper mapper;

    protected abstract String getRootNode();

    public FirebaseDatabaseRepository(FirebaseMapper mapper) {
        databaseReference = FirebaseDatabase.getInstance().getReference(getRootNode());
        this.mapper = mapper;
    }

    public void addListener(FirebaseDatabaseRepositoryCallback<Model> firebaseCallback) {
        this.firebaseCallback = firebaseCallback;
        listener = new BaseValueEventListener(mapper, firebaseCallback);
        databaseReference.addValueEventListener(listener);

    }

    public void removeListener() {
        databaseReference.removeEventListener(listener);
    }

    public interface FirebaseDatabaseRepositoryCallback<T> {
        void onSuccess(List<T> result);

        void onError(Exception e);
    }

    public void addValue(Model article) {

        String mGroupId = databaseReference.push().getKey();
        databaseReference.child(mGroupId).setValue(article);
    }

    public void put(String key, Model article) {

        databaseReference.child(key).setValue(article);
    }


    public void update(String key, String filed, String value) {

        databaseReference.child(key).child(filed).setValue(value);
    }

    public void deleteValue(String id) {

        databaseReference.child(id).getRef().removeValue();
    }


    public void fieldExist(String row, final String value, final FirebaseFiledExist exist) {
        databaseReference.child(row).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                // run some code
                exist.isExist(snapshot.hasChild(value));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void hasExist(String row, final List<String> values, final FirebaseFiledExist exist) {
        databaseReference.child(row).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                HashMap<String, Boolean> map = new HashMap<>();
                // run some code
                for (String value : values) {
                    map.put(value, snapshot.hasChild(value));
                }
                exist.has(map);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static class FirebaseFiledExist {
        public void isExist(boolean is) {
        }

        public void has(HashMap<String, Boolean> map) {
        }
    }


}

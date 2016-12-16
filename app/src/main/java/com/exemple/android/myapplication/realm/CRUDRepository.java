package com.exemple.android.myapplication.realm;


import io.realm.Realm;
import io.realm.RealmResults;

public class CRUDRepository {

    public static void deleteAllStudent() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<ListItem> results = realm.where(ListItem.class).findAll();
                results.deleteAllFromRealm();
            }
        });
    }
}


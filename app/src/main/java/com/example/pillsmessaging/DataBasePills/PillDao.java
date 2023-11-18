package com.example.pillsmessaging.DataBasePills;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface PillDao {
    @Query("SELECT * FROM pills")
    LiveData<List<ItemPill>>  getAll();

    @Query("SELECT * FROM pills WHERE id IN (:id)")
    List<ItemPill> loadAllByIds(int[] id);

    @Query("SELECT * FROM pills WHERE  checker = :isAvailable")
    LiveData<List<ItemPill>> loadAllBySelected(boolean isAvailable);

    @Insert
    void insertAll(ItemPill... itemPills);
    @Insert
    void insert(ItemPill itemPills);

    @Query("DELETE FROM pills")
    void delete();

    @Delete
    void deleteItem(ItemPill itemPill);

    @Update
    void updateOneItem(ItemPill itemPill);

}

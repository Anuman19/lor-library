package ch.bbcag.lorlibrary.model;

import androidx.room.Query;

import java.util.List;

public interface CardDao {
    @Query("Select * from card")
    List<Card> loadAll();
}

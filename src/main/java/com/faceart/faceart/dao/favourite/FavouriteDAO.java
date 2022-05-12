package com.faceart.faceart.dao.favourite;

import com.faceart.faceart.entities.Favourite;

import java.util.List;

public interface FavouriteDAO {

    Favourite getFavouriteById(long id);

    List getAll();

    void save(Favourite favourite);

    void remove(Favourite favourite);
}

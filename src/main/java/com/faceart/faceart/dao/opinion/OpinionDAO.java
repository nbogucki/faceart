package com.faceart.faceart.dao.opinion;

import com.faceart.faceart.entities.Opinion;

import java.util.List;

public interface OpinionDAO {

    Opinion getOpinionById(long id);

    List getAll();

    void save(Opinion opinion);

    void remove(Opinion opinion);
}

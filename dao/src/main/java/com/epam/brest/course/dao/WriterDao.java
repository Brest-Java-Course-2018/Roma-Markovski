package com.epam.brest.course.dao;

import com.epam.brest.course.model.Writer;

import java.util.Collection;

/**
 * Writer DAO Interface.
 */
public interface WriterDao {

    Collection<Writer> getWriters();

    Writer getWriterById(Integer writerId);


}

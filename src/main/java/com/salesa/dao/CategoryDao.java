package com.salesa.dao;

import com.salesa.entity.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDao {
     List<Category> getAll();
}

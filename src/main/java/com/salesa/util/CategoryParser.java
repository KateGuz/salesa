package com.salesa.util;

import com.salesa.entity.Category;
import com.salesa.util.mapper.RestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CategoryParser {
    @Autowired
    private RestMapper restMapper;

    public String toJSON(Category category) {
        return restMapper.toJSON(category);
    }
}

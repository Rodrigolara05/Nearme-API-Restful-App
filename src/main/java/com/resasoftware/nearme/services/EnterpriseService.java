package com.resasoftware.nearme.services;

import java.util.List;

import com.resasoftware.nearme.entities.Category;
import com.resasoftware.nearme.entities.Enterprise;

public interface EnterpriseService extends CRUDService<Enterprise> {
	List<Enterprise> enterpriseByCategory(Category category) throws Exception;
}

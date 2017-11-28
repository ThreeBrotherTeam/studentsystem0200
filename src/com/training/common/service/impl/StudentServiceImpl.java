package com.training.common.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.training.convert.Convert;
import com.training.dao.StudentDao;
import com.training.data.StudentData;
import com.training.form.StudentForm;
import com.training.model.StudentModel;
import com.training.page.Pagination;
import com.training.page.SearchResult;
import com.training.service.StudentService;


public class StudentServiceImpl implements StudentService {
	
	private StudentDao studentDao;
	private Convert<StudentModel, StudentData> convert;
	
	@Override
	public SearchResult<StudentData> loadStudentsByFields(StudentForm studentForm, Pagination page) {
		Map<String, Object> params = new HashMap<>();
		if (StringUtils.isNotBlank(studentForm.getName())) {
			params.put(StudentModel.NAME, studentForm.getName());
		}
		if (StringUtils.isNotBlank(studentForm.getClazz())) {
			params.put(StudentModel.CLASS, studentForm.getClazz());
		}

		SearchResult<StudentModel> searchResults = studentDao.queryStudentByFields(params, page);
		// studentConvert

		SearchResult<StudentData> results = new SearchResult<StudentData>();
		List<StudentData> datas = new ArrayList<>();
		for (StudentModel model : searchResults.getResult()) {
			datas.add(convert.convert(model));
		}
		results.setPagination(searchResults.getPagination());
		results.setResult(datas);
		return results;
	}

	public StudentDao getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public Convert<StudentModel, StudentData> getConvert() {
		return convert;
	}

	public void setConvert(Convert<StudentModel, StudentData> convert) {
		this.convert = convert;
	}

}

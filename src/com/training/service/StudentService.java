package com.training.service;

import com.training.data.StudentData;
import com.training.form.StudentForm;
import com.training.page.Pagination;
import com.training.page.SearchResult;

public interface StudentService {

	SearchResult<StudentData> loadStudentsByFields(StudentForm studentForm, Pagination page);

}

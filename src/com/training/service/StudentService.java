package com.training.service;

import com.training.data.StudentData;
import com.training.form.StudentForm;
import com.training.page.Pagination;
import com.training.page.SearchResult;

public interface StudentService {

	SearchResult<StudentData> loadStudentsByFields(StudentForm studentForm, Pagination page);

	void addStudent(StudentForm studentForm);

	void deleteStudent(StudentForm studentForm);

	StudentData queryStudnetById(StudentForm studentForm);

	void modifyStudent(StudentForm studentForm);

}

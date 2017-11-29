package com.training.convert;

import com.training.data.StudentData;
import com.training.model.StudentModel;

public class StudentConvert implements Convert<StudentModel, StudentData> {

	@Override
	public StudentData createTarget() {
		return new StudentData();
	}

	@Override
	public StudentData convert(StudentModel model) {
		StudentData data = createTarget();
		data.setId(model.getId());
		data.setName(model.getName());
		data.setBirthday(model.getBirthday());
		data.setClazz(model.getClazz());
		data.setCreateTime(model.getCreateTime());
		data.setModifyTime(model.getModifyTime());
		return data;
	}

}
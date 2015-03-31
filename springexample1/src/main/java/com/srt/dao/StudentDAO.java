package com.srt.dao;

import com.srt.model.Student;
 
public interface StudentDAO {
 
    public void insert(Student person);
    public void delete(Student student);
    public Student getValueforID(int id);
    public void update(Student student);

}